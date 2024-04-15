package com.example.service;

import java.util.List;

import com.example.dto.BookDto;
import com.example.exceptions.AuthorNotFoundException;
import com.example.exceptions.BookAlreadyExistsException;
import com.example.exceptions.BookNotCompleted;
import com.example.exceptions.BookNotFoundException;
import com.example.exceptions.GenreNotFoundException;
import com.example.exceptions.NoBooksAvailableException;
import com.example.exceptions.ThreeInCompleteBooksException;

public interface BookService {
	
	public String addBook(BookDto book)throws BookAlreadyExistsException, ThreeInCompleteBooksException;
	
	public BookDto getBookById(String bookId)throws BookNotFoundException;
	
	public BookDto updateBook(BookDto book)throws BookNotFoundException;
	
	public List<BookDto> getAllBooks() throws NoBooksAvailableException;
	
	public void deleteBook(String bookId)throws BookNotFoundException;

	public List<BookDto> getAllBooksByGenre(String genre) throws NoBooksAvailableException, GenreNotFoundException;
	
	public String setBookCompleted(String id) throws BookNotFoundException;

	public List<BookDto> getAllBooksByAuthorId(String authorName) throws NoBooksAvailableException, AuthorNotFoundException;

	public String setAuthorized(String id) throws BookNotCompleted;

	public Boolean hasBook(String id) throws BookNotFoundException;

	public List<BookDto> getNonverifiedBooks() throws NoBooksAvailableException;

	public List<BookDto> getVerifiedBooks() throws NoBooksAvailableException;

}
