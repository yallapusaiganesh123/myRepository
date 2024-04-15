package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.exceptions.UserAlreadyExists;
import com.example.exceptions.UserNotFound;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.services.UserServiceImpl;

@SpringBootTest
class UserServiceApplicationTests {
	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private WebClient.Builder webClientBuilder;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	 void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	

	@Test
	 void testGetUserByID_UserNotFound_ThrowsUserNotFound() {
		// Mocking
		String userId = "nonexistent@example.com";
		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		// Test and Verify
		assertThrows(UserNotFound.class, () -> userService.getUserByID(userId));
	}

	@Test
	 void testUpdateUser_UserExists_ReturnsUpdatedUserDTO() throws UserNotFound {
		// Mocking
		String userId = "test@example.com";
		UserDTO updatedUserDTO = new UserDTO();
		updatedUserDTO.setEmail(userId);
		updatedUserDTO.setUsername("updatedusername");

		User user = new User();
		user.setEmail(userId);
		user.setUsername("testuser");

		when(userRepository.existsById(userId)).thenReturn(true);
		when(userRepository.save(any(User.class))).thenReturn(user);
		when(modelMapper.map(updatedUserDTO, User.class)).thenReturn(user);

		// Test
		UserDTO result = userService.updateUser(updatedUserDTO);

		// Verify
		assertEquals(updatedUserDTO.getUsername(), result.getUsername());
	}

	@Test
	 void testUpdateUser_UserNotFound_ThrowsUserNotFound() {
		// Mocking
		String userId = "nonexistent@example.com";
		UserDTO updatedUserDTO = new UserDTO();
		updatedUserDTO.setEmail(userId);
		updatedUserDTO.setUsername("updatedusername");

		when(userRepository.existsById(userId)).thenReturn(false);

		// Test and Verify
		assertThrows(UserNotFound.class, () -> userService.updateUser(updatedUserDTO));
	}

	@Test
	 void testRegisterUser_NewUser_ReturnsSuccessMessage() throws UserAlreadyExists {
		// Mocking
		String userId = "test@example.com";
		UserDTO newUserDTO = new UserDTO();
		newUserDTO.setEmail(userId);
		newUserDTO.setUsername("testuser");

		User user = new User();
		user.setEmail(userId);
		user.setUsername("testuser");
		user.setPassword("password"); // The actual password should be encoded before saving

		when(userRepository.existsById(userId)).thenReturn(false);
		when(modelMapper.map(newUserDTO, User.class)).thenReturn(user);

		// Test
		String result = userService.registerUser(newUserDTO);

		// Verify
		assertEquals("User Registered Successfully", result);
	}

	

	@Test
	 void testDeleteUser_UserExists_ReturnsSuccessMessage() throws UserNotFound {
		// Mocking
		String userId = "test@example.com";
		when(userRepository.existsById(userId)).thenReturn(true);

		// Test
		String result = userService.deleteUser(userId);

		// Verify
		assertEquals("User deleted Successfully", result);
	}

	@Test
	 void testDeleteUser_UserNotFound_ThrowsUserNotFound() {
		// Mocking
		String userId = "nonexistent@example.com";
		when(userRepository.existsById(userId)).thenReturn(false);

		// Test and Verify
		assertThrows(UserNotFound.class, () -> userService.deleteUser(userId));
	}

	@Test
	 void testGetAllUsers_ReturnsListOfUserDTO() {
		// Mocking
		User user1 = new User();
		user1.setEmail("user1@example.com");
		user1.setUsername("user1");

		User user2 = new User();
		user2.setEmail("user2@example.com");
		user2.setUsername("user2");

		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);

		when(userRepository.findAll()).thenReturn(userList);

		// Test
		List<UserDTO> result = userService.getAllUsers();

		// Verify
		assertEquals(2, result.size());
	}


}
