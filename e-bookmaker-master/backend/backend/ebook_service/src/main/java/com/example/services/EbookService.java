package com.example.services;
 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
 
import com.example.dto.BookContentDto;
import com.example.dto.BookDto;
import com.example.dto.EbookDTO;
import com.example.exceptions.BookAlreadyExistException;
import com.example.exceptions.BookNotFoundException;
import com.example.model.Ebook;
import com.example.repository.EbookRepository;
 
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
 
@Service
@AllArgsConstructor
@Log4j2
public class EbookService {
 
	EbookRepository ebookRepo;
	ModelMapper modelMapper;
	WebClient.Builder webclient;
 
	public ResponseEntity<String> addEbook(String bookId) {
	    try {
	        Ebook ebook = new Ebook();
	        if (!webclient.build().get().uri("http://localhost:8093/api/book/hasbook/" + bookId).retrieve()
	                .bodyToMono(Boolean.class).block()) {
	            throw new BookNotFoundException("Book does not exist");
	        } else {
	            if (!ebookRepo.existsById(bookId)) {
	                BookDto book = webclient.build().get().uri("http://localhost:8093/api/book/" + bookId).retrieve()
	                        .bodyToMono(BookDto.class).block();
	                BookContentDto bookContent = webclient.build().get()
	                        .uri("http://localhost:8094/api/bookcontent/getbookcontent/" + bookId).retrieve()
	                        .bodyToMono(BookContentDto.class).block();
 
	                ebook.setBookId(bookId);
	                ebook.setAuthorId(book.getAuthorId());
	                ebook.setAuthorName(book.getAuthorName());
	                ebook.setGenre(book.getGenre());
	                ebook.setDescription(book.getDescription());
	                ebook.setContent(bookContent.getBookcontent());
 
	                ebookRepo.save(ebook);
	                log.info("Ebook added successfully!");
 
	                return ResponseEntity.ok("Ebook added successfully");
	            } else {
	                throw new BookAlreadyExistException("Book with ID " + ebook.getBookId() + " already exists.");
	            }
	        }
	    } catch (BookNotFoundException e1) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found: " + e1.getMessage());
	    } catch (BookAlreadyExistException e2) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Book already exists: " + e2.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
	    }
	}
	public EbookDTO getbookId(String id) throws BookNotFoundException {
		Optional<Ebook> ebookOptional = ebookRepo.findById(id);
		if (ebookOptional.isPresent()) {
			return modelMapper.map(ebookOptional.get(), EbookDTO.class);
		}
		throw new BookNotFoundException("Book with ID " + id + " not found.");
 
	}
 
	public String deleteById(String bookId) throws BookNotFoundException {
		Optional<Ebook> existingEbook = ebookRepo.findById(bookId);
		if (existingEbook.isPresent()) {
			ebookRepo.deleteById(bookId);
			return "Book deleted successfully";
		}
		throw new BookNotFoundException("Book with ID " + bookId + " not found for deletion.");
 
	}
 
	public List<EbookDTO> getAllBooks() {
		List<Ebook> ebooks = ebookRepo.findAll();
 
		if (ebooks.isEmpty()) {
			log.warn("There is no books available.");
		}
 
		return ebooks.stream().map(book -> modelMapper.map(book, EbookDTO.class)).collect(Collectors.toList());
	}
 
}