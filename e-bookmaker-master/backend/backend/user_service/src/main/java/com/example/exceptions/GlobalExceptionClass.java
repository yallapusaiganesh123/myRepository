package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionClass {

	@ExceptionHandler(UserNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFound() {
		return "User does not exists";
	}
	@ExceptionHandler(UserAlreadyExists.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	String userAlredyExists() {
		return "User already exists";
	}
	@ExceptionHandler(InvalidCredentials.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	String invalidCredentials() {
		return "Invalid User credentials";
	}
	
}
