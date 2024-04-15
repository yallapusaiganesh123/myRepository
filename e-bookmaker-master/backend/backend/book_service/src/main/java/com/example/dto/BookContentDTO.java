package com.example.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookContentDTO {
	
	@Size(min = 6, max = 25, message = "Book name should be between 6 and 25 characters")
	private String bookname;
	private String bookcontent;

}
