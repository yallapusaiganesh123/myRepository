package com.example.exceptions;

@SuppressWarnings("serial")
public class NoBooksAvailableException extends Exception{
	
	public NoBooksAvailableException(String msg){
		super(msg);
	}
	
}
