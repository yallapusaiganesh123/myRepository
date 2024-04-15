package com.example.services;
import java.lang.System;
import java.util.List;
import java.util.Optional;
 
import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
 
import com.example.dto.AuthorDTO;
import com.example.dto.UserAdminDTO;
import com.example.dto.UserAdminLoginDTO;
import com.example.exceptions.UserNotFoundException;
import com.example.model.UserAdmin;
import com.example.repository.UserAdminRepository;
 
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
 
@Service
@AllArgsConstructor
@Log4j2
public class UserAdminServiceImpl implements UserAdminService {
    @Autowired
    private UserAdminRepository userAdminRepository;
 
    private ModelMapper modelMapper;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
   
    private WebClient.Builder webclient;
 
    @Override
public UserAdminDTO updateUser(UserAdminDTO userAdminDTO) {
    // No need to encode the password here
    if (userAdminRepository.existsById(userAdminDTO.getEmail())) {
        UserAdmin existingUser = userAdminRepository.findById(userAdminDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User Admin with email " + userAdminDTO.getEmail() + " not found."));
 
        existingUser.setUsername(userAdminDTO.getUsername());
        existingUser.setPassword(userAdminDTO.getPassword()); // Store password directly
 
        UserAdmin updatedUserAdmin = userAdminRepository.save(existingUser);
        log.info("User Admin with email " + userAdminDTO.getEmail() + " update successfully");
 
        return modelMapper.map(updatedUserAdmin, UserAdminDTO.class);
    } else {
        log.warn("User Admin with email " + userAdminDTO.getEmail() + " not found.");
        throw new UserNotFoundException("User Admin with email " + userAdminDTO.getEmail() + " not found.");
    }
}
 
 
 
    @Override
    public UserAdminDTO getUserByEmail(String email) {
        Optional<UserAdmin> userAdminOptional = userAdminRepository.findById(email);
 
        if (userAdminOptional.isPresent()) {
            UserAdmin userAdmin = userAdminOptional.get();
 
            log.info("Got the user admin by emailID successfully.");
            return modelMapper.map(userAdmin, UserAdminDTO.class);
        } else {
            log.warn("User Admin with email " + email + " not found.");
            throw new UserNotFoundException("User Admin with email " + email + " not found.");
        }
    }
 
    @Override
    public List<AuthorDTO> getUnauthorizedAuthors() {
        return webclient.build().get().uri("http://localhost:8091/api/author/unauthorized").retrieve().bodyToMono(new ParameterizedTypeReference<List<AuthorDTO>>() {
        }).block();
    }
   
    @Override
    public String authorizeAuthor(String id) {
        return webclient.build().post().uri("http://localhost:8091/api/author/authorize/"+id).retrieve().bodyToMono(String.class).block();
    }
 
    // UserAdminServiceImpl.java
    @Override
public String login(UserAdminLoginDTO logindto) throws InvalidCredentialsException {
    Optional<UserAdmin> adminOptional = userAdminRepository.findById(logindto.getEmail());
    if (adminOptional.isPresent()) {
        UserAdmin admin = adminOptional.get();
        if (logindto.getPassword().equals(admin.getPassword())) {
            return "Login success"; // Password matches
        }
    }
    throw new InvalidCredentialsException(); // Password doesn't match or user not found
}
 
@Override
public UserAdminDTO registerUser(UserAdminDTO userAdminDTO) {
    // No need to encode the password here
    // Save the user directly to the database
    UserAdmin newUserAdmin = modelMapper.map(userAdminDTO, UserAdmin.class);
    UserAdmin savedUserAdmin = userAdminRepository.save(newUserAdmin);
    log.info("User Admin with email " + userAdminDTO.getEmail() + " registered successfully");
 
    return modelMapper.map(savedUserAdmin, UserAdminDTO.class);
}
 
 
    @Override
    public String authorizeBook(String bookid) {
        return webclient.build().post().uri("http://localhost:8093/api/book/setauthorized/"+bookid).retrieve().bodyToMono(String.class).block();
 
    }
 
}
 