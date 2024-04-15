package com.example.services;

import java.util.List;

import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.exceptions.InvalidCredentials;
import com.example.exceptions.UserAlreadyExists;
import com.example.exceptions.UserNotFound;

public interface UserService {
	UserDTO getUserByID(String id) throws UserNotFound;

	UserDTO updateUser(UserDTO userDTO) throws UserNotFound;

	String registerUser(UserDTO userdto) throws UserAlreadyExists;

	String deleteUser(String id) throws UserNotFound;

	List<UserDTO> getAllUsers();

	//String login(LoginDTO logindto);
	public String login(LoginDTO logindto) throws InvalidCredentials;

}
