package com.example.controller;

import java.util.ArrayList;
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

import com.example.dto.BookDto;
import com.example.exceptions.AuthorNotFoundException;
import com.example.exceptions.BookAlreadyExistsException;
import com.example.exceptions.BookNotCompleted;
import com.example.exceptions.BookNotFoundException;
import com.example.exceptions.GenreNotFoundException;
import com.example.exceptions.NoBooksAvailableException;
import com.example.exceptions.ThreeInCompleteBooksException;
import com.example.service.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
@AllArgsConstructor
public class BookController {
	
	private BookService bookService;

	@PostMapping("/createbook")
	 ResponseEntity<String> addBook(@Valid @RequestBody BookDto book) throws BookAlreadyExistsException, ThreeInCompleteBooksException {
		return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	 ResponseEntity<BookDto> getBookById(@PathVariable("id") String bookId) throws BookNotFoundException {
		BookDto book = bookService.getBookById(bookId);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@GetMapping("/allbooks")
	public ResponseEntity<List<BookDto>> getAllBooks() throws NoBooksAvailableException {
	    try {
	        List<BookDto> books = bookService.getAllBooks();
	        return ResponseEntity.ok(books);
	    } catch (NoBooksAvailableException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
	    }
	}
	
	@GetMapping("/allbooks/genre/{genre}")
	 ResponseEntity<List<BookDto>> getAllBooksByGenre(@PathVariable("genre") String gn) throws NoBooksAvailableException, GenreNotFoundException {
		List<BookDto> books = bookService.getAllBooksByGenre(gn);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	@GetMapping("/getbyauthid/{userid}")
	 ResponseEntity<List<BookDto>> getAllBooksByAuthorName(@PathVariable String userid) throws NoBooksAvailableException, AuthorNotFoundException {
		List<BookDto> books = bookService.getAllBooksByAuthorId(userid);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@PutMapping("/editbook/{id}")
	 ResponseEntity<BookDto> updateBook(@PathVariable("id") String bookId,@Valid @RequestBody BookDto book) throws BookNotFoundException {
		book.setId(bookId);
		BookDto updatedBook = bookService.updateBook(book);
		return new ResponseEntity<>(updatedBook, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletebook/{id}")
	 ResponseEntity<String> deleteBook(@PathVariable("id") String bookId) throws BookNotFoundException {
		try {
			bookService.deleteBook(bookId);
	        return ResponseEntity.ok("{\"message\": \"Book Deleted\"}");
	    } catch (BookNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Book not found\"}");
	    }
		
	}
	
	@GetMapping("/nonverified/books")
	ResponseEntity<List<BookDto>> getNonverifiedBooks() throws NoBooksAvailableException {
		try {
			List<BookDto> books = bookService.getNonverifiedBooks();
			return new ResponseEntity<>(books, HttpStatus.OK);
	    } catch (NoBooksAvailableException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
	    }
	}
	
	@GetMapping("/verified/books")
	ResponseEntity<List<BookDto>> getVerifiedBooks() throws NoBooksAvailableException {
		try {
			List<BookDto> books = bookService.getVerifiedBooks();
			return new ResponseEntity<>(books, HttpStatus.OK);
	    } catch (NoBooksAvailableException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
	    }
	}
	
	@GetMapping("/setcomplete/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	ResponseEntity<String> setBookCompleted(@PathVariable String id) throws BookNotFoundException {
		try {
			bookService.setBookCompleted(id);
	        return ResponseEntity.ok("{\"message\": \"Book completed\"}");
	    } catch (BookNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Book not found\"}");
	    }
	}
	
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	ResponseEntity<String> setBookAuthorized(@PathVariable String id) throws BookNotCompleted {
		
		try {
	        bookService.setAuthorized(id);
	        return ResponseEntity.ok("{\"message\": \"Book Authorized\"}");
	    } catch (BookNotCompleted e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Book not completed\"}");
	    }
	}
	
	@GetMapping("/hasbook/{id}")
	@ResponseStatus(HttpStatus.OK)
	Boolean hasBook(@PathVariable String id) throws BookNotFoundException {
		return bookService.hasBook(id);
	}
	
	
}
