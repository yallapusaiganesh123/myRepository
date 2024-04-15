package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAdminDTO {

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Username is required")
	@Size(max = 10, min = 4, message = "username must be min of 4 character")
	private String username;

	@NotBlank(message = "Password is required")
	@Size(max = 10, min = 4, message = "username must be min of 4 character")
	private String password;

}
