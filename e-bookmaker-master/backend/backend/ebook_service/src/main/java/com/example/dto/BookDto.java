package com.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

	@NotEmpty(message = "Book name (id) cannot be empty")
	@Size(min = 1, max = 50, message = "Book name (id) should have 1 to 50 characters")
	private String id;

	private String authorId;

	private String authorName;

	@NotEmpty(message = "Description cannot be empty")
	private String description;

//	@NotEmpty(message = "Genre list cannot be empty")
//	@Size(min = 1, max = 5, message = "Genre list should have 1 to 5 items")
	private String genre;

//	private String content;

	private boolean isCompleted;

	private boolean isVerified;

}
