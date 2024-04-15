package com.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import com.project.dto.AuthorDTO;
import com.project.exceptions.AuthorAlreadyExists;
import com.project.exceptions.AuthorNotFound;
import com.project.model.Author;
import com.project.repository.AuthorRepository;
import com.project.services.AuthorService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;



@SpringBootTest
class AuthorServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	private AuthorRepository authorRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;
	private AuthorService authorService;
	private Validator validator;
	private WebClient.Builder webclient;

	@BeforeEach
	public void setUp() {
		// Initialize and mock dependencies
		authorRepository = mock(AuthorRepository.class);
		passwordEncoder = mock(PasswordEncoder.class);
		modelMapper = mock(ModelMapper.class);
		webclient=mock(WebClient.Builder.class);
		authorService = new AuthorService(authorRepository, passwordEncoder, modelMapper, webclient);

		// Create a Validator instance
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetAuthorByID_ExistingID_ShouldReturnAuthorDTO() throws AuthorNotFound {
		// Arrange
		String authorId = "some_author_id";
		Author author = new Author();
		author.setEmail(authorId);
		author.setAuthorname("JohnDoe");
		when(authorRepository.findById(eq(authorId))).thenReturn(Optional.of(author));

		AuthorDTO expectedAuthorDTO = new AuthorDTO();
		expectedAuthorDTO.setEmail(authorId);
		expectedAuthorDTO.setAuthorname("JohnDoe");
		when(modelMapper.map(any(), eq(AuthorDTO.class))).thenReturn(expectedAuthorDTO);

		// Act
		AuthorDTO result = authorService.getAuthorByID(authorId);

		// Assert
		assertEquals(expectedAuthorDTO, result);
	}

	@Test
	public void testGetAuthorByID_NonExistingID_ShouldThrowAuthorNotFound() {
		// Arrange
		String nonExistingAuthorId = "non_existing_author_id";
		when(authorRepository.findById(eq(nonExistingAuthorId))).thenReturn(Optional.empty());

		// Act and Assert
		assertThrows(AuthorNotFound.class, () -> authorService.getAuthorByID(nonExistingAuthorId));
	}

	
	@Test
	public void testRegisterAuthor_AuthorAlreadyExists_ShouldThrowAuthorAlreadyExists() {
		// Arrange
		AuthorDTO existingAuthorDTO = new AuthorDTO();
		existingAuthorDTO.setEmail("jane.smith@example.com");
		existingAuthorDTO.setAuthorname("JaneSmith");
		existingAuthorDTO.setPassword("password456");
		// Add other valid fields

		Author author = new Author();
		when(modelMapper.map(eq(existingAuthorDTO), eq(Author.class))).thenReturn(author);
		when(authorRepository.existsById(eq(author.getAuthorname()))).thenReturn(true);

		// Act and Assert
		assertThrows(AuthorAlreadyExists.class, () -> authorService.registerAuthor(existingAuthorDTO));
	}

	@Test
	public void testGetAllAuthors_ShouldReturnListOfAuthorDTO() {
		// Arrange
		List<Author> authorList = new ArrayList<>();
		Author author1 = new Author();
		author1.setEmail("sourav@gmail.com");
		author1.setAuthorname("JohnDoe");
		// Add other valid fields
		Author author2 = new Author();
		author2.setEmail("deepak@gmail.com");
		author2.setAuthorname("JaneSmith");
		// Add other valid fields
		authorList.add(author1);
		authorList.add(author2);
		when(authorRepository.findAll()).thenReturn(authorList);

		AuthorDTO authorDTO1 = new AuthorDTO();
		authorDTO1.setEmail("sourav@gmail.com");
		authorDTO1.setAuthorname("JohnDoe");
		// Add other valid fields
		AuthorDTO authorDTO2 = new AuthorDTO();
		authorDTO2.setEmail("deepak@gmail.com");
		authorDTO2.setAuthorname("JaneSmith");
		// Add other valid fields
		when(modelMapper.map(any(), eq(AuthorDTO.class))).thenReturn(authorDTO1, authorDTO2);

		// Act
		List<AuthorDTO> result = authorService.getAllAuthors();

		// Assert
		assertEquals(2, result.size());
		assertEquals(authorDTO1, result.get(0));
		assertEquals(authorDTO2, result.get(1));
	}

	@Test
	public void testDeleteAuthor_ExistingID_ShouldReturnSuccessMessage() throws AuthorNotFound {
		// Arrange
		String authorId = "some_author_id";
		Author author = new Author();
		author.setEmail(authorId);
		when(authorRepository.existsById(eq(authorId))).thenReturn(true);

		// Act
		String result = authorService.deleteAuthor(authorId);

		// Assert
		assertEquals("Author deleted successfully", result);
	}

	@Test
	public void testDeleteAuthor_NonExistingID_ShouldThrowAuthorNotFound() {
		// Arrange
		String nonExistingAuthorId = "non_existing_author_id";
		when(authorRepository.existsById(eq(nonExistingAuthorId))).thenReturn(false);

		// Act and Assert
		assertThrows(AuthorNotFound.class, () -> authorService.deleteAuthor(nonExistingAuthorId));
	}

	@Test
	public void testUpdateAuthor_NonExistingAuthor_ShouldThrowAuthorNotFound() {
		// Arrange
		String nonExistingAuthorId = "non_existing_author_id";
		AuthorDTO nonExistingAuthorDTO = new AuthorDTO();
		nonExistingAuthorDTO.setEmail(nonExistingAuthorId);
		nonExistingAuthorDTO.setAuthorname("NonExistingAuthor");
		// Add other valid fields

		when(authorRepository.existsById(eq(nonExistingAuthorDTO.getEmail()))).thenReturn(false);

		// Act and Assert
		assertThrows(AuthorNotFound.class, () -> authorService.updateAuthor(nonExistingAuthorDTO));
	}

	@Test
	public void testAuthorDTOValidation_ValidAuthor_ShouldHaveNoViolations() {
		// Arrange
		AuthorDTO validAuthorDTO = new AuthorDTO();
		validAuthorDTO.setEmail("john.doe@example.com");
		validAuthorDTO.setAuthorname("JohnDoe");
		validAuthorDTO.setPassword("password123");
		// Add other valid fields

		// Act
		Set<ConstraintViolation<AuthorDTO>> violations = validator.validate(validAuthorDTO);

		// Assert
		assertTrue(violations.isEmpty());
	}
	
}

