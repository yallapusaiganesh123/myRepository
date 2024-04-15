package com.project.exceptions;

public class BookIsEmpty extends Exception{
	
	public BookIsEmpty() {
		super();
	}

	public BookIsEmpty(String msg) {
		super(msg);
	}
}
