package com.project.exceptions;

public class AuthorAlreadyExists extends Exception{
	
	public AuthorAlreadyExists() {
		super();
	}

	public AuthorAlreadyExists(String msg) {
		super(msg);
	}
}
