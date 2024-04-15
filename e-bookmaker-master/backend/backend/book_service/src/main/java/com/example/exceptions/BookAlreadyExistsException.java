package com.example.exceptions;

@SuppressWarnings("serial")
public class BookAlreadyExistsException extends Exception{
	
	public BookAlreadyExistsException(String msg){
		super(msg);
	}
	
}
