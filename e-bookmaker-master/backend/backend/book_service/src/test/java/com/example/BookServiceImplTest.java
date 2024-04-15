package com.example;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.example.exceptions.BookNotFoundException;
import com.example.repository.BookRepository;
import com.example.service.BookServiceImpl;

public class BookServiceImplTest {

	@Mock
	private BookRepository bookRepository;

	@Mock
	private ModelMapper modelMapper; // Mocking the ModelMapper dependency

	@InjectMocks
	private BookServiceImpl bookService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetBookByIdBookNotFoundException() {
		// Mocking data
		String bookId = "1";

		// Mocking behavior
		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

		// Test the service method and expect an exception
		assertThrows(BookNotFoundException.class, () -> bookService.getBookById(bookId));
	}

	@Test
	public void testDeleteBookBookNotFoundException() {
		// Mocking data
		String bookId = "1";

		// Mocking behavior
		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

		// Test the service method and expect an exception
		assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(bookId));
	}
}
