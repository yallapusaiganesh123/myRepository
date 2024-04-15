package com.project.exceptions;

public class AuthorNotAuthorized extends Exception{
	
	public AuthorNotAuthorized() {
		super();
	}

	public AuthorNotAuthorized(String msg) {
		super(msg);
	}
}