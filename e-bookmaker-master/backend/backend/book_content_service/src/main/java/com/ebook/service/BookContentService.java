package com.ebook.service;

import java.util.List;

import com.ebook.dto.BookContentDTO;
import com.ebook.exception.BookAlreadyExists;
import com.ebook.exception.BookContentAlreadyExists;
import com.ebook.exception.BookNotFound;

public interface BookContentService {

	public List<BookContentDTO> getAllBooks();

	// public String addbookContent(BookContentService bookdto) ;
	public BookContentDTO getBookContentById(String id) throws BookNotFound;

	public String deletBookContentById(String id) throws BookNotFound;

	//public BookContentDTO updateBookContentById(String id);

	//public String addbookContent(BookContentService bookdto) throws BookContentAlreadyExists;

	String addbookContent(BookContentDTO bookdto) throws  BookContentAlreadyExists, BookAlreadyExists;

	BookContentDTO updateBookContentById(BookContentDTO updatedBookContent) throws BookNotFound;
}
