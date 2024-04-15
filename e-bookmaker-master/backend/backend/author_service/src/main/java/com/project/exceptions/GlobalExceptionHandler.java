package com.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AuthorNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String authorNotFoundException() {
		return "Author not Found";		
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(AuthorAlreadyExists.class)
	public String AuthorAlreadyExistException() {
		return "Author Already exists";
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(AuthorNotAuthorized.class)
	public String AuthorNotAuthorized() {
		return "Author Not authorized by admin yet to create books";
	}
	
}
