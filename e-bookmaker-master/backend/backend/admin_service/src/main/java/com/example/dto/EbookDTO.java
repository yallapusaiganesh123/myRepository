package com.example.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EbookDTO {


		@NotBlank(message = "Book ID cannot be blank")
		@Size(min = 1, max = 50, message = "Book ID must be between 1 and 50 characters")
		private String bookId;
		
	    @NotBlank(message = "Author ID cannot be blank")
	    @Size(min = 1, max = 50, message = "Author ID must be between 1 and 50 characters")
		private String authorId;
		
	    @NotBlank(message = "Author Name cannot be blank")
	    @Size(min = 1, max = 100, message = "Author Name must be between 1 and 100 characters")
		private String authorName;
		
	    @NotBlank(message = "Description cannot be blank")
	    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
		private String description;
		
	    @Size(min = 1, max = 10, message = "Genre list must have 1 to 10 items")
		private List<String> genre;
		
	    @NotBlank(message = "Content cannot be blank")
	    @Size(min = 1, max = 10000, message = "Content must be between 1 and 10000 characters")
		private String content;
		
		

}
