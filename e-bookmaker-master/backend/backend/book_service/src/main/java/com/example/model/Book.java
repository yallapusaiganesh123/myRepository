package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="books")
public class Book {
	
	@Id
	@Column(name="book_name")
	private String id;
	
	private String authorId;
	
	private String authorName;
	
	private String description;
	
	private String genre;
	
	private boolean isCompleted;
	
	private boolean isVerified;

}
