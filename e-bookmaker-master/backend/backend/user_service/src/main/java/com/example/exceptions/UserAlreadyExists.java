package com.example.exceptions;

public class UserAlreadyExists extends Exception{
	
	public UserAlreadyExists() {
		super();
	}

	public UserAlreadyExists(String msg) {
		super(msg);
	}
}
