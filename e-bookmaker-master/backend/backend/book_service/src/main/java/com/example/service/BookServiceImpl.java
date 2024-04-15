package com.example.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dto.BookContentDTO;
import com.example.dto.BookDto;
import com.example.dto.EbookDTO;
import com.example.exceptions.AuthorNotFoundException;
import com.example.exceptions.BookAlreadyExistsException;
import com.example.exceptions.BookNotCompleted;
import com.example.exceptions.BookNotFoundException;
import com.example.exceptions.GenreNotFoundException;
import com.example.exceptions.NoBooksAvailableException;
import com.example.exceptions.ThreeInCompleteBooksException;
import com.example.model.Book;
import com.example.repository.BookRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class BookServiceImpl implements BookService {

	private BookRepository bookRepo;

	private ModelMapper modelMapper;

	private WebClient.Builder webclient;

	
	@Override
	public String addBook(BookDto bookDto) throws BookAlreadyExistsException, ThreeInCompleteBooksException {

		List<Book> books = bookRepo.findAll();

		if (books.stream().filter(book -> book.getAuthorId().equals(bookDto.getAuthorId()))
				.filter(book -> !book.isCompleted()).count() == 3) {
			log.warn("You have 3 incomplete books. Please complete one.");
			throw new ThreeInCompleteBooksException(
					"You have 3 incomplete books please comlpete any one before creating other");
		} else {

			if (!bookRepo.existsById(bookDto.getId())) {
				bookRepo.save(modelMapper.map(bookDto, Book.class));
				BookContentDTO content = new BookContentDTO(bookDto.getId(), "");
				webclient.build().post().uri("http://localhost:8094/api/bookcontent/add").bodyValue(content).retrieve()
						.bodyToMono(String.class).block();
				log.info("Book added successfully by name - " + bookDto.getId());
				return "Book added into database";
			}
			log.warn("Book already exists by name - " + bookDto.getId());
			throw new BookAlreadyExistsException("Book already exists");
		}
	}

	
	
	@Override
	public BookDto updateBook(BookDto book) throws BookNotFoundException {
		Book existingBook = bookRepo.findById(book.getId())
				.orElseThrow(() -> new BookNotFoundException("Book not found by name - " + book.getId()));

		existingBook.setDescription(book.getDescription());
		existingBook.setGenre(book.getGenre());

		Book updatedBook = bookRepo.save(existingBook);
		log.info("Book updated successfully.");
		return modelMapper.map(updatedBook, BookDto.class);
	}
	
	

	@Override
	public BookDto getBookById(String bookId) throws BookNotFoundException {
		try {
			Book book = bookRepo.findById(bookId)
					.orElseThrow(() -> new BookNotFoundException("Book not found by ID - " + bookId));

			log.info("Successfully retrieved book with name - ", bookId);
			return modelMapper.map(book, BookDto.class);
		} catch (BookNotFoundException ex) {
			log.warn("BookNotFoundException while getting book with ID: {}. Message: {}", bookId, ex.getMessage());
			throw ex;
		}
	}

	
	
	@Override
	public List<BookDto> getAllBooks() throws NoBooksAvailableException {
		List<Book> books = bookRepo.findAll();

		if (books.isEmpty()) {
			log.warn("There is no books available.");
			throw new NoBooksAvailableException("No books available.");
		}

		return books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
	}
	
	

	@Override
	public List<BookDto> getAllBooksByGenre(String genre) throws GenreNotFoundException, NoBooksAvailableException {
		List<Book> books = bookRepo.findAll();
		if (books.isEmpty()) {
			log.warn("There are no books available.");
			throw new NoBooksAvailableException("No books available.");
		}

		List<BookDto> matchingBooks = books.stream()
				.filter(book -> book.getGenre() != null && book.getGenre().contains(genre))
				.map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());

		if (matchingBooks.isEmpty()) {
			log.warn("There are no books available by genre - " + genre);
			throw new GenreNotFoundException("No books available for genre - " + genre);
		}

		log.info("Successfully retrieved books with genre - ", genre);
		return matchingBooks;
	}
	
	

	@Override
	public List<BookDto> getAllBooksByAuthorId(String id) throws NoBooksAvailableException, AuthorNotFoundException {
		List<Book> books = bookRepo.findAll();
		if (books.isEmpty()) {
			log.warn("There are no books available.");
			throw new NoBooksAvailableException("No books available.");
		}
		List<BookDto> matchingBooks = books.stream().filter(book -> book.getAuthorId().equals(id))
				.map(book -> modelMapper.map(book, BookDto.class)).toList();
		log.info("Successfully retrieved books with author - ", id);
		return matchingBooks;
	}
	
	

	@Override
	public void deleteBook(String bookId) throws BookNotFoundException {
		Optional<Book> book = bookRepo.findById(bookId);
		if (book.isPresent()) {
			webclient.build().delete().uri("http://localhost:8094/api/bookcontent/delete/" + bookId).retrieve()
					.bodyToMono(String.class).block();
			bookRepo.deleteById(bookId);
			log.info("Successfully deleted book with name - " + bookId);
		} else {
			log.warn("There are no books available by name - " + bookId);
			throw new BookNotFoundException("Book not found by name - " + bookId);
		}
	}

	
	
	@Override
	public String setBookCompleted(String id) throws BookNotFoundException {

		Optional<Book> book = bookRepo.findById(id);
		if (book.isPresent()) {
			book.get().setCompleted(true);
			bookRepo.save(book.get());
			log.info("Successfully marked 'completed' the book with name - " + id);
			return "Book Completed";
		}
		log.warn("Book not exists by name - " + id);
		throw new BookNotFoundException("book does not exist");

	}

	
	
	@Override
	public String setAuthorized(String id) throws BookNotCompleted {
		Optional<Book> book = bookRepo.findById(id);
		if (book.isPresent() && book.get().isCompleted() == true) {
			book.get().setVerified(true);
			bookRepo.save(book.get());
			BookContentDTO bookcontent = webclient.build().get()
					.uri("http://localhost:8094/api/bookcontent/getbookcontent/" + id).retrieve()
					.bodyToMono(BookContentDTO.class).block();
			EbookDTO ebookdto = new EbookDTO();
			ebookdto.setAuthorId(book.get().getAuthorId());
			ebookdto.setAuthorName(book.get().getAuthorName());
			ebookdto.setBookId(id);
			ebookdto.setDescription(book.get().getDescription());
			ebookdto.setGenre(book.get().getGenre());
			ebookdto.setContent(bookcontent.getBookcontent());

			log.info("Book with name - " + id + " is marked as authorized.");
			return "Book authorized!";
		}

		log.warn("Book with name - " + id + " is incomplete.");
		throw new BookNotCompleted("book is not yet completed by author");

	}
	
	

	@Override
	public Boolean hasBook(String id) throws BookNotFoundException {
		if (bookRepo.existsById(id)) {
			log.info("Book exists with name - " + id);
			return true;
		} else
			log.warn("Book not exists by name - " + id);
		throw new BookNotFoundException("Book does not exists");
	}

	
	
	@Override
	public List<BookDto> getNonverifiedBooks() throws NoBooksAvailableException {
	    List<BookDto> list = bookRepo.findAll().stream()
	            .filter(book -> !book.isVerified()) 
	            .sorted(Comparator.comparing(Book::isCompleted) .reversed()
	                .thenComparing(Book::getId)) 
	            .map(a -> modelMapper.map(a, BookDto.class))
	            .toList();

	    if (list.isEmpty()) {
	        log.warn("Books not available.");
	        throw new NoBooksAvailableException("No books exist in the database!");
	    } else {
	        return list;
	    }
	}


	@Override
	public List<BookDto> getVerifiedBooks() throws NoBooksAvailableException {
		List<BookDto> list = bookRepo.findAll().stream()
	            .filter(book -> book.isVerified()) 
	            .map(a -> modelMapper.map(a, BookDto.class))
	            .toList();

	    if (list.isEmpty()) {
	        log.warn("Books not available.");
	        throw new NoBooksAvailableException("No books exist in the database!");
	    } else {
	        return list;
	    }
	}

}
