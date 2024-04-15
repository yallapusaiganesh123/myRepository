package com.project.exceptions;

public class AuthorNotFound extends Exception{
	
	public AuthorNotFound() {
		super();
	}

	public AuthorNotFound(String msg) {
		super(msg);
	}
}