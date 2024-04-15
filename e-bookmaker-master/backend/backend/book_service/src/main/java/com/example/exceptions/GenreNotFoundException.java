package com.example.exceptions;

@SuppressWarnings("serial")
public class GenreNotFoundException extends Exception{
	
	public GenreNotFoundException(String msg){
		super(msg);
	}
	
}
