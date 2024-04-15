package com.example;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.dto.EbookDTO;
import com.example.exceptions.BookNotFoundException;
import com.example.model.Ebook;
import com.example.repository.EbookRepository;
import com.example.services.EbookService;

@RunWith(MockitoJUnitRunner.class)
public class EbookServiceApplicationTests {

    @MockBean
    private EbookRepository ebookRepositoryMock;

    @Mock
    private ModelMapper modelMapperMock;

    @InjectMocks
    private EbookService ebookService;

    @Before
    public void setUp() {
        // Mocking the behavior of modelMapperMock
        when(modelMapperMock.map(any(EbookDTO.class), any(Class.class))).thenReturn(new Ebook());

        // Mocking the behavior of ebookRepositoryMock
        when(ebookRepositoryMock.existsById("existingId")).thenReturn(true);
        when(ebookRepositoryMock.findById("existingId")).thenReturn(Optional.of(new Ebook()));
    }

   

    @Test
    public void testGetbookId() throws BookNotFoundException {
        EbookDTO ebookDTO = ebookService.getbookId("existingId");
        // Perform assertions as needed
    }

    @Test
    public void testDeleteById() throws BookNotFoundException {
        String result = ebookService.deleteById("existingId");
        assertEquals("Book deleted successfully", result);
    }

    @Test
    public void testDeleteByIdNonExistingId() throws BookNotFoundException {
        String result = ebookService.deleteById("nonExistingId");
        assertEquals("Book deleted successfully", result);
        // Even if the ID does not exist, the method should still return the same message.
    }
}
