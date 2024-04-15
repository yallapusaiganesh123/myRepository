package com.project.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.project.dto.AuthorDTO;
import com.project.dto.BookDto;
import com.project.dto.LoginDTO;
import com.project.exceptions.AuthorAlreadyExists;
import com.project.exceptions.AuthorNotAuthorized;
import com.project.exceptions.AuthorNotFound;
import com.project.exceptions.BookIsEmpty;
import com.project.exceptions.InvalidCredentials;
import com.project.model.Author;
import com.project.repository.AuthorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorService {

	AuthorRepository authrepo;
	PasswordEncoder passEncoder;
	ModelMapper modelmapper;
	WebClient.Builder webclient;

	public AuthorDTO getAuthorByID(String id) throws AuthorNotFound {

		@SuppressWarnings("null")
		Optional<Author> author = authrepo.findById(id);
		if (author.isEmpty())
			throw new AuthorNotFound("Author Doesn't exist");

		return modelmapper.map(author.get(), AuthorDTO.class);
	}

	@SuppressWarnings("null")
	public String registerAuthor(AuthorDTO authordto) throws AuthorAlreadyExists {

		Author author = modelmapper.map(authordto, Author.class);
		author.setPassword((author.getPassword()));
		if (authrepo.existsById(author.getEmail())) {
			throw new AuthorAlreadyExists("Author Already exists");
		}
		authrepo.save(author);
		return "Author Registered Successfully";

	}

	public List<AuthorDTO> getAllAuthors() {

		List<Author> author = (List<Author>) authrepo.findAll();
		return author.stream().map(x -> modelmapper.map(x, AuthorDTO.class)).toList();

	}

	@SuppressWarnings("null")
	public String deleteAuthor(String id) throws AuthorNotFound {

		if (authrepo.existsById(id)) {
			authrepo.deleteById(id);
			return "Author deleted successfully";
		}

		throw new AuthorNotFound("Author doesn't exists of given id");

	}

	public AuthorDTO updateAuthor(AuthorDTO author) throws AuthorNotFound {
		author.setPassword(passEncoder.encode(author.getPassword()));
		if (!authrepo.existsById(author.getEmail())) {
			throw new AuthorNotFound("Author not found");
		}
		authrepo.save(modelmapper.map(author, Author.class));
		return modelmapper.map(authrepo.findById(author.getEmail()).get(), AuthorDTO.class);

	}

	public String validateAuthor(String id) throws AuthorNotFound {
		if (authrepo.existsById(id)) {
			Author author = authrepo.findById(id).get();
			author.setAuthorised(true);
			authrepo.save(author);
			return "User Authorized";
		}
		throw new AuthorNotFound("Author does not exist");
	}

	public List<AuthorDTO> unauthorizedAuthors() {
		return authrepo.findAll().stream().filter(author -> author.isAuthorised() == false)
				.map(auth -> modelmapper.map(auth, AuthorDTO.class)).toList();
	}

	public String addBook(BookDto bookdto) throws AuthorNotAuthorized {

		@SuppressWarnings("null")
		Optional<Author> author = authrepo.findById(bookdto.getAuthorId());
		if (author.isPresent()&& author.get().isAuthorised()) {
			bookdto.setAuthorId(author.get().getEmail());
			bookdto.setAuthorName(author.get().getAuthorname());
			return webclient.build().post().uri("http://localhost:8093/api/book/createbook").bodyValue(bookdto).retrieve()
					.bodyToMono(String.class).block();
		}
		throw new AuthorNotAuthorized("author does not exists or is not yet authorized");

	}

	public String setBookComplete(String bookid) throws BookIsEmpty {
		
		Boolean bookcontentisvalid=webclient.build().get().uri("http://localhost:8094/api/bookcontent/validcontent/" + bookid).retrieve()
				.bodyToMono(Boolean.class).block();
		if(bookcontentisvalid) {
		return webclient.build().get().uri("http://localhost:8093/api/book/setcomplete/" + bookid).retrieve()
				.bodyToMono(String.class).block();
		}
		throw new BookIsEmpty(bookid);
	}

	public List<BookDto> getAuhtorBooks(String id) {
		return webclient.build().get().uri("http://localhost:8093/api/book/getbyauthid/" + id).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<BookDto>>() {
				}).block();
	}

	
	// @SuppressWarnings("null")
	// public String login(LoginDTO logindto) throws InvalidCredentials {
	// 	Optional<Author> author =authrepo.findById(logindto.getEmail());
	// 	if (author.isPresent() && passEncoder.matches(logindto.getPassword(), author.get().getPassword())) {
    //         return webclient.build().post()
    //                 .uri("http://localhost:8080/realms/api-gateway-realm/protocol/openid-connect/token")
    //                 .contentType(MediaType.APPLICATION_FORM_URLENCODED)
    //                 .body(BodyInserters.fromFormData("grant_type", "password").with("client_id", "user_api")
    //                         .with("username", "user").with("password", "user01"))
    //                 .retrieve().bodyToMono(String.class).block();

    //     }
    //     throw new InvalidCredentials("Invalid credentials or user does not exists");
	// }
	public String login(LoginDTO logindto) throws InvalidCredentials {
	    	
		Optional<Author> adminOptional = authrepo.findById(logindto.getEmail());
		  
		  if (adminOptional.isPresent()) {
			  
			  Author author = adminOptional.get();
			  if (logindto.getPassword().equals(author.getPassword())) {
				  
				  return "Login success"; // Password matches
			  }
		  }
		  throw new InvalidCredentials("Invalid Credentials"); // Password doesn't match or user not found
	  }

}
