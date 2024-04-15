package com.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.project.dto.AuthorDTO;
import com.project.dto.BookDto;
import com.project.dto.LoginDTO;
import com.project.exceptions.AuthorAlreadyExists;
import com.project.exceptions.AuthorNotAuthorized;
import com.project.exceptions.AuthorNotFound;
import com.project.exceptions.BookIsEmpty;
import com.project.exceptions.InvalidCredentials;
import com.project.services.AuthorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/author")
public class AuthorController {
	
    AuthorService service;
    
	WebClient.Builder web;
	
	@GetMapping("/getauthor/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	AuthorDTO getAuthorById(@PathVariable String id) throws AuthorNotFound {
		return service.getAuthorByID(id);	
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	String registerAuthor(@Valid @RequestBody AuthorDTO author) throws AuthorAlreadyExists {
		
		return service.registerAuthor(author);
	}
	
	@GetMapping("/getall")
	@ResponseStatus(HttpStatus.ACCEPTED)
	List<AuthorDTO> getAllAuthors(){
		
		return service.getAllAuthors();
	}

	
	@PutMapping("/update")
	 AuthorDTO updateAuthor(@RequestBody AuthorDTO author) throws AuthorNotFound {
		
		return service.updateAuthor(author);
	}
	
	@DeleteMapping("/delete/{email}")
	 String deleteAuthor(@PathVariable String email) throws AuthorNotFound {
		
		return service.deleteAuthor(email);
	}
	
	@GetMapping("/unauthorized")
	 List<AuthorDTO> getUnauthorizedUsers(){
		return service.unauthorizedAuthors();
	}
	
	@PostMapping("/authorize/{id}")
	public ResponseEntity<String> validateAuthor(@PathVariable String id) throws AuthorNotFound {
	    try {
	        service.validateAuthor(id);
	        return ResponseEntity.ok("{\"message\": \"User Authorized\"}");
	    } catch (AuthorNotFound e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Author not found\"}");
	    }
	}
	
	@PostMapping("/book/add")
	String addBook(@RequestBody BookDto bookdto) throws AuthorNotAuthorized  {
		return service.addBook(bookdto);
	}
	
	@PostMapping("/bookcomplete/{bookid}")
	String setBookComplete(@PathVariable String bookid) throws BookIsEmpty {
		return service.setBookComplete(bookid);
	}
	
	@GetMapping("/getauthorbooks/{id}")
	List<BookDto> getAuthorBooks(@PathVariable String id){
		return service.getAuhtorBooks(id);
	}

	// @PostMapping("/login")
	// String loginAuthor(@RequestBody LoginDTO logindto) throws InvalidCredentials {
	// 	return service.login(logindto);
	// }
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO logindto) {
        try {
            String result = service.login(logindto);
            return ResponseEntity.ok().body("{\"message\": \"" + result + "\"}");
        } catch (InvalidCredentials e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid credentials\"}");
        }
    }
	
}
