package com.example.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

	@NotEmpty(message = "Email ID can't be empty!")
	@Size(min = 2, max = 30, message = "Invalid Email ID please enter a vaild email ID")
	@Email(message = "Not the proper Email ID format! enter again")
	private String email;
	
	@NotEmpty(message = "Customer Name can't be empty!")
	@Size(min = 3, max = 25, message = "Invalid Customer Name please enter a vaild Customer Name!")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the name")
	private String authorname;
	
	@NotEmpty(message = "Password can't be empty!")
	@Size(min = 8, max = 20, message = "Password has to be of minimum 8 chars!")
	private String password;
	
	@Size(min = 1,max = 3,message = "Age Should be between 1 and 3 characters")
	private String age;
	
	@Size(min = 10,max = 100,message = "Description Should be between 10 and 100 characters")
	private String about;
}

