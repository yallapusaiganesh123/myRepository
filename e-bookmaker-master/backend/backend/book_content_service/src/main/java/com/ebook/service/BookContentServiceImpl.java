package com.ebook.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ebook.dto.BookContentDTO;
import com.ebook.exception.BookAlreadyExists;
import com.ebook.exception.BookContentAlreadyExists;
import com.ebook.exception.BookNotFound;
import com.ebook.model.BookContent;
import com.ebook.repository.BookContentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookContentServiceImpl implements BookContentService {

	private final BookContentRepository bookcontentrepo;
	private final ModelMapper modelmapper;
	private WebClient.Builder webclient;

	@Override
	public List<BookContentDTO> getAllBooks() {
		List<BookContent> books = (List<BookContent>) bookcontentrepo.findAll();
		return books.stream().map(x -> modelmapper.map(x, BookContentDTO.class)).toList();
	}

	@Override
	public BookContentDTO getBookContentById(String id) throws BookNotFound {
		Optional<BookContent> book = bookcontentrepo.findById(id);
		if (book.isEmpty())
			throw new BookNotFound("Book Doesn't exist");

		return modelmapper.map(book.get(), BookContentDTO.class);
	}

	@Override
	public String addbookContent(BookContentDTO bookdto) throws BookContentAlreadyExists, BookAlreadyExists {

		if (!webclient.build().get().uri("http://localhost:8093/api/book/hasbook/" + bookdto.getBookname()).retrieve()
				.bodyToMono(Boolean.class).block()) {
			throw new BookAlreadyExists();
		} else {
			BookContent bookcon = modelmapper.map(bookdto, BookContent.class);
			if (bookcontentrepo.existsById(bookcon.getBookname())) {
				throw new BookContentAlreadyExists();
			} else {
				bookcontentrepo.save(bookcon);
				return "Book content added successfully";
			}
		}
	}

	@Override
	public String deletBookContentById(String id) throws BookNotFound {
		if (bookcontentrepo.existsById(id)) {
			bookcontentrepo.deleteById(id);
			return "Deleted the book content successfully";
		} else {
			throw new BookNotFound("Book not found by id - " + id);
		}
	}

	@Override
	public BookContentDTO updateBookContentById(BookContentDTO updatedBookContent) throws BookNotFound {
		if (!webclient.build().get().uri("http://localhost:8093/api/book/hasbook/" + updatedBookContent.getBookname())
				.retrieve().bodyToMono(Boolean.class).block()) {
			throw new BookNotFound("book does not exists");
		} else {
			Optional<BookContent> bookOptional = bookcontentrepo.findById(updatedBookContent.getBookname());
			if (bookOptional.isEmpty())
				throw new BookNotFound("Book doesn't exist");
			else {
				bookOptional.get().setBookcontent(updatedBookContent.getBookcontent());
				;
				bookcontentrepo.save(bookOptional.get());
				return modelmapper.map(bookOptional.get(), BookContentDTO.class);
			}
		}
	}

}
