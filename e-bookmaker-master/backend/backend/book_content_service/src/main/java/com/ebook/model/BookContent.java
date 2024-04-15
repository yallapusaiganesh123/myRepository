package com.ebook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "book_content")

public class BookContent {
	@Id
	private String bookname;
	
	@Column(columnDefinition = "TEXT")
	private String bookcontent;
}
