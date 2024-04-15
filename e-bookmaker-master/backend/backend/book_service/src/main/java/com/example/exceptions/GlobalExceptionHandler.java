package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleBookNotFoundException() {
		return "Book not found by the name.";
	}


	@ExceptionHandler(BookAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleBookAlreadyExistsException() {
		return "Book already exists by same name.";
	}

	@ExceptionHandler(NoBooksAvailableException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNoBooksAvailableException() {
		return "There is no book available.";
	}
	
	@ExceptionHandler(GenreNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleGenreNotFoundException() {
		return "Books not available for the genre.";
	}
	
	@ExceptionHandler(AuthorNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleAuthorNotFoundException() {
		return "Books not available for the author.";
	}

	@ExceptionHandler(BookNotCompleted.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleBookNotCompleted() {
		return "The has not been completed by author to be verified by admin.";
	}
	
	@ExceptionHandler(ThreeInCompleteBooksException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleThreeInCompleteBooksException() {
		return "You have 3 incomplete books please comlpete any one before creating other";
	}
}
