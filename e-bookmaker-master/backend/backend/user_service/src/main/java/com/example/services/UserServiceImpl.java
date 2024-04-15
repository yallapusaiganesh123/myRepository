package com.example.services;

import java.util.List;
import java.util.Optional;

import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.exceptions.InvalidCredentials;
import com.example.exceptions.UserAlreadyExists;
import com.example.exceptions.UserNotFound;
import com.example.model.User;
import com.example.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    UserRepository userrepo;
    PasswordEncoder passEncoder;
    ModelMapper modelmapper;
    WebClient.Builder webclient;

    /**
     * Get user by ID.
     * 
     * @param id The email ID of the user to retrieve.
     * @return The mapped UserDTO if the user exists.
     * @throws UserNotFound If the user with the given ID does not exist.
     */
    public UserDTO getUserByID(String id) throws UserNotFound {
        Optional<User> user = userrepo.findById(id);
        if (user.isEmpty())
            throw new UserNotFound("User Doesn't exist");

        return modelmapper.map(user.get(), UserDTO.class);
    }

    /**
     * Update user information.
     * 
     * @param userDTO The UserDTO object containing updated user details.
     * @return The updated UserDTO if the user exists.
     * @throws UserNotFound If the user with the given email does not exist.
     */
    public UserDTO updateUser(UserDTO userDTO) throws UserNotFound {
        if (userrepo.existsById(userDTO.getEmail())) {
            userrepo.save(modelmapper.map(userDTO, User.class));
            return userDTO;
        }
        throw new UserNotFound("User does not exist");
    }

    /**
     * Register a new user.
     * 
     * @param userdto The UserDTO object containing user details for registration.
     * @return A success message if the user is registered successfully.
     * @throws UserAlreadyExists If a user with the same email already exists.
     */
    public String registerUser(UserDTO userdto) throws UserAlreadyExists {
        User user = modelmapper.map(userdto, User.class);
        user.setPassword((user.getPassword()));
        if (!userrepo.existsById(user.getUsername())) {
        	
        	userrepo.save(user);
            return "User Registered Successfully";
        	
        }
        
        throw new UserAlreadyExists("User Already exists");
        
    }
    
    /**
     * Delete a user by ID.
     * 
     * @param id The email ID of the user to delete.
     * @return A success message if the user is deleted successfully.
     * @throws UserNotFound If the user with the given ID does not exist.
     */
    public String deleteUser(String id) throws UserNotFound {
        if (userrepo.existsById(id)) {
            userrepo.deleteById(id);
            return "User deleted Successfully";
        }
        throw new UserNotFound("User does not exist");
    }

    /**
     * Get a list of all users.
     * 
     * @return List of UserDTO objects representing all users in the database.
     */
    public List<UserDTO> getAllUsers() {
        List<User> user = (List<User>) userrepo.findAll();
        return user.stream().map(x -> modelmapper.map(x, UserDTO.class)).toList();
    }

    @Override
    public String login(LoginDTO logindto) throws InvalidCredentials {
        Optional<User> adminOptional = userrepo.findById(logindto.getEmail());
         if (adminOptional.isPresent()) {
            User user = adminOptional.get();
            if (logindto.getPassword().equals(user.getPassword())) {
                return "Login success"; // Password matches
            }
        }
        throw new InvalidCredentials("Invalid Credentials");
    }

    /**
     * Perform user login and retrieve a JWT token.
     * 
     * @param logindto The LoginDTO object containing user login credentials.
     * @return JWT token if login is successful, otherwise null.
     * @throws InvalidCredentialsException 
     */
    // public String login(LoginDTO logindto) throws InvalidCredentialsException {
    //     Optional<User> user = userrepo.findById(logindto.getEmail());

    //     if (user.isPresent() && passEncoder.matches(logindto.getPassword(), user.get().getPassword())) {
    //         return webclient.build().post()
    //                 .uri("http://localhost:8080/realms/api-gateway-realm/protocol/openid-connect/token")
    //                 .contentType(MediaType.APPLICATION_FORM_URLENCODED)
    //                 .body(BodyInserters.fromFormData("grant_type", "password").with("client_id", "user_api")
    //                         .with("username", "user").with("password", "user01"))
    //                 .retrieve().bodyToMono(String.class).block();

    //     }
    //     throw new InvalidCredentialsException("Invalid User Credentials");
    // }
    // @Override
    // public String login(LoginDTO logindto) throws InvalidCredentials {
    	
    //     Optional<User> adminOptional = userrepo.findById(logindto.getEmail());
    //     if (adminOptional.isPresent()) {
    //         User user = adminOptional.get();
    //         if (logindto.getPassword().equals(user.getPassword())) {
    //             return "Login success"; // Password matches
    //         }
    //     }
    //     throw new InvalidCredentials("Invalid Credentials"); // Password doesn't match or user not found
    // }
}

