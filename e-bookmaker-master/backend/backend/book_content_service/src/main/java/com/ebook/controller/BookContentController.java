package com.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ebook.dto.BookContentDTO;
import com.ebook.exception.BookAlreadyExists;
import com.ebook.exception.BookContentAlreadyExists;
import com.ebook.exception.BookNotFound;
import com.ebook.service.BookContentService;

@RestController
@CrossOrigin
@RequestMapping("/api/bookcontent")
public class BookContentController {
    @Autowired
    BookContentService bookcontentservice;

    @GetMapping("/getbookcontent/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookContentDTO getBookContentById(@PathVariable String id) throws BookNotFound {
        return bookcontentservice.getBookContentById(id);
    }

    @PostMapping("/add")
    public String addBookContentById(@RequestBody BookContentDTO bookdto) throws BookContentAlreadyExists, BookAlreadyExists {
        return bookcontentservice.addbookContent(bookdto);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateBookContentById(@RequestBody BookContentDTO updatedBookContent) throws BookNotFound {
    	try {
    		bookcontentservice.updateBookContentById(updatedBookContent);
	        return ResponseEntity.ok("{\"message\": \"Book updated\"}");
	    } catch (BookNotFound e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Book not found\"}");
	    }
    	
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBookContentById(@PathVariable String id) throws BookNotFound {
        return bookcontentservice.deletBookContentById(id);
    }
}
