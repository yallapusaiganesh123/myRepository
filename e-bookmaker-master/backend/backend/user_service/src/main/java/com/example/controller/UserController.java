package com.example.controller;

import java.util.List;

import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.exceptions.InvalidCredentials;
import com.example.exceptions.UserAlreadyExists;
import com.example.exceptions.UserNotFound;
import com.example.services.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    UserServiceImpl service;

    /**
     * Get user by ID.
     *
     * @param id The email ID of the user to retrieve.
     * @return The UserDTO representing the user if found.
     * @throws UserNotFound If the user with the given ID does not exist.
     */
    @GetMapping("/getuser/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    UserDTO getUserById(@PathVariable String id) throws UserNotFound {
        return service.getUserByID(id);
    }

    /**
     * Register a new user.
     *
     * @param user The UserDTO object containing user details for registration.
     * @return A success message if the user is registered successfully.
     * @throws UserAlreadyExists If a user with the same email already exists.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    String registerUser(@Valid @RequestBody UserDTO user) throws UserAlreadyExists {
        return service.registerUser(user);
    }

    /**
     * Get a list of all users.
     *
     * @return List of UserDTO objects representing all users in the database.
     */
    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<UserDTO> getAllUser() {
        return service.getAllUsers();
    }

    /**
     * Delete a user by ID.
     *
     * @param id The email ID of the user to delete.
     * @return A success message if the user is deleted successfully.
     * @throws UserNotFound If the user with the given ID does not exist.
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    String deleteUser(@PathVariable String id) throws UserNotFound {
        return service.deleteUser(id);
    }

    /**
     * Update user information.
     *
     * @param userdto The UserDTO object containing updated user details.
     * @return The updated UserDTO if the user exists.
     * @throws UserNotFound If the user with the given email does not exist.
     */
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    UserDTO updateUser(@RequestBody UserDTO userdto) throws UserNotFound{
        return service.updateUser(userdto);
    }

    /**
     * Perform user login and retrieve a JWT token.
     *
     * @param logindto The LoginDTO object containing user login credentials.
     * @return JWT token if login is successful, otherwise null.
     * @throws InvalidCredentialsException 
     */
    // @PostMapping("/login")
    // String login(@RequestBody LoginDTO logindto) throws InvalidCredentialsException {
    //     return service.login(logindto);
    // }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO logindto) {
        try {
            String result = service.login(logindto);
            return ResponseEntity.ok().body("{\"message\": \"" + result + "\"}");
        } catch (InvalidCredentials e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid credentials\"}");
        }
    }
}

