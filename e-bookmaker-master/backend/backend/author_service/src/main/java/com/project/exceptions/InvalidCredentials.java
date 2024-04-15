package com.project.exceptions;

public class InvalidCredentials extends Exception{
	
	public InvalidCredentials() {
		super();
	}

	public InvalidCredentials(String msg) {
		super(msg);
	}
}