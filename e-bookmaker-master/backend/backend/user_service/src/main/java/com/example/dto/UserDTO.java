package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	@Email
	private String email;
	
	@Size(min = 4,max = 25,message = "Username Should be min 4 characters")
	private String username;
	
	@Size(min = 4,max = 25,message = "Password Should be min 4 characters")
	private String password;
	
	@Size(min = 1,max = 3,message = "Age Should be between 1 and 3 characters")
	private String age;
	
}
