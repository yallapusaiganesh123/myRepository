package com.example.controller;
 
import java.util.List;
 
import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.dto.AuthorDTO;
import com.example.dto.UserAdminDTO;
import com.example.dto.UserAdminLoginDTO;
import com.example.exceptions.UserNotFoundException;
import com.example.services.UserAdminService;
 
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
 
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@CrossOrigin
public class UserAdminController {
 
    @Autowired
    private UserAdminService userAdminService;
 
 
    // Get a single user by Id
    @GetMapping("/{email}")
    ResponseEntity<UserAdminDTO> getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        UserAdminDTO user = userAdminService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
 
    // Update a user
    @PostMapping("/update")
    ResponseEntity<UserAdminDTO> updateUser(@Valid @RequestBody UserAdminDTO userAdminDto) throws UserNotFoundException {
        UserAdminDTO user = userAdminService.updateUser(userAdminDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
   
    @GetMapping("/getunauth")
    @ResponseStatus(HttpStatus.OK)
    List<AuthorDTO> getUnauthorizedAuthors(){
        return userAdminService.getUnauthorizedAuthors();
       
    }
   
    @PostMapping("/authorizeauth/{authorid}")
    @ResponseStatus(HttpStatus.OK)
    String authorizeAuthor(@PathVariable String authorid) {
        return userAdminService.authorizeAuthor(authorid);
    }
   
    @PostMapping("/authorizebook/{bookid}")
    @ResponseStatus(HttpStatus.OK)
    String authorizeBook(@PathVariable String bookid) {
        return userAdminService.authorizeBook(bookid);
    }
   
   
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody UserAdminLoginDTO logindto) {
    try {
        String result = userAdminService.login(logindto);
        return ResponseEntity.ok().body("{\"message\": \"" + result + "\"}");
    } catch (InvalidCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid credentials\"}");
    }
}
 
 
 
@PostMapping("/register")
ResponseEntity<UserAdminDTO> registerUser(@Valid @RequestBody UserAdminDTO userAdminDto) {
    UserAdminDTO user = userAdminService.registerUser(userAdminDto);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
}
 
 
 
 
}