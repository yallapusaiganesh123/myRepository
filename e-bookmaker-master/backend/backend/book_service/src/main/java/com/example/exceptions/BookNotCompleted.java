package com.example.exceptions;

@SuppressWarnings("serial")
public class BookNotCompleted extends Exception{
	
	public BookNotCompleted(String msg){
		super(msg);
	}
	
}
