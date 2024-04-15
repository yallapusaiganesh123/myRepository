package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user_details")
public class User {

	@Id
	private String email;
	
	private String username;
	
	private String password;
	
	private String age;

	public String getUsername() {

		return this.username;
	}
	
}
