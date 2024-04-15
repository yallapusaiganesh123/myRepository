package com.example.exceptions;

@SuppressWarnings("serial")
public class AuthorNotFoundException extends Exception{
	
	public AuthorNotFoundException(String msg){
		super(msg);
	}
	
}
