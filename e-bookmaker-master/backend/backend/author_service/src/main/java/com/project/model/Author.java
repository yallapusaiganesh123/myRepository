package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="author_details")
public class Author {

	@Id
	private String email;
	
	private String authorname;
	
	private String password;
	
	private String age;
	
	private boolean isAuthorised;
	
	private String about;
	
	public String getAuthorname() {
		// TODO Auto-generated method stub
		return this.authorname;
	}
	
}

