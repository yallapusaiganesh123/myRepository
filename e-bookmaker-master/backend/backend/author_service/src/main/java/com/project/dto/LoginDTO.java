package com.project.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
	
	@Email(message = "Not the proper Email ID format! enter again")
	private String email;
	
	@Size(min = 8, max = 20, message = "Password has to be of minimum 8 chars!")
	private String password;
	
}