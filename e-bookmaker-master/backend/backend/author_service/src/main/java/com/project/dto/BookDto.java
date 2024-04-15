package com.project.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

	@NotEmpty(message = "Genre list cannot be empty")
	@Size(min = 1, max = 5, message = "Genre list should have 1 to 5 items")
	private List<String> genre;

	@NotEmpty(message = "Content cannot be empty")
	private String content;

	private boolean isCompleted;

	private boolean isVerified;

}
