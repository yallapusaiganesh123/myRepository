package com.example.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.dto.EbookDTO;
import com.example.exceptions.BookAlreadyExistException;
import com.example.exceptions.BookNotFoundException;
import com.example.services.EbookService;
 
@CrossOrigin
@RestController
@RequestMapping("/api/ebook")
public class EbookController {
	@Autowired
	private EbookService ebookService;
	@PostMapping("/addebook/{bookId}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String>  addEbook(@PathVariable String bookId) throws BookAlreadyExistException, BookNotFoundException
	{
		ebookService.addEbook(bookId);
		return ResponseEntity.ok("{\"message\": \"Book Added To Ebooks\"}");
	}
	@GetMapping("/allebooks")
	 ResponseEntity<List<EbookDTO>> getAllBooks() {
		List<EbookDTO> books = ebookService.getAllBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
 
	
	    @GetMapping("/{bookId}")
	    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<EbookDTO> getBookById(@PathVariable String bookId) throws BookNotFoundException{
	    	EbookDTO book = ebookService.getbookId(bookId);
			return new ResponseEntity<>(book, HttpStatus.OK);
    }
	@DeleteMapping("/delete/{bookId}")
	public String deleteEbook(@PathVariable String bookId) throws BookNotFoundException{
		return ebookService.deleteById(bookId);		
	}
}