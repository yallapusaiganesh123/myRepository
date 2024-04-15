package com.example.services;
 
import java.util.List;
 
import org.apache.hc.client5.http.auth.InvalidCredentialsException;
 
import com.example.dto.AuthorDTO;
import com.example.dto.UserAdminDTO;
import com.example.dto.UserAdminLoginDTO;
import com.example.exceptions.UserNotFoundException;
 
public interface UserAdminService {
    public UserAdminDTO getUserByEmail(String email) throws UserNotFoundException;
 
    public UserAdminDTO updateUser(UserAdminDTO userAdmin) throws UserNotFoundException;
 
    public List<AuthorDTO> getUnauthorizedAuthors();
 
    String authorizeAuthor(String id);
 
    public String login(UserAdminLoginDTO logindto) throws InvalidCredentialsException;
 
    public UserAdminDTO registerUser(UserAdminDTO userAdminDTO);
 
    public String authorizeBook(String bookid);
 
 
}