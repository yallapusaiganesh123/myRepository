package com.example.testcase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.dto.UserAdminDTO;
import com.example.exceptions.UserNotFoundException;
import com.example.model.UserAdmin;
import com.example.repository.UserAdminRepository;
import com.example.services.UserAdminServiceImpl;

public class UserAdminServiceImplTest {

	@Mock
	private UserAdminRepository userAdminRepository;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserAdminServiceImpl userAdminService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUpdateUser_ExistingUser() {
		// Mock data
		UserAdminDTO userAdminDTO = new UserAdminDTO();
		userAdminDTO.setEmail("test@example.com");
		userAdminDTO.setUsername("newUsername");
		userAdminDTO.setPassword("newPassword");

		UserAdmin existingUser = new UserAdmin();
		existingUser.setEmail("test@example.com");
		existingUser.setUsername("oldUsername");
		existingUser.setPassword("oldPassword");

		UserAdmin updatedUser = new UserAdmin();
		updatedUser.setEmail("test@example.com");
		updatedUser.setUsername("newUsername");
		updatedUser.setPassword("encodedNewPassword");

		// Mock repository methods
		when(userAdminRepository.existsById("test@example.com")).thenReturn(true);
		when(userAdminRepository.findById("test@example.com")).thenReturn(Optional.of(existingUser));
		when(userAdminRepository.save(existingUser)).thenReturn(updatedUser);

		// Mock modelMapper
		when(modelMapper.map(updatedUser, UserAdminDTO.class)).thenReturn(userAdminDTO);

		// Mock passwordEncoder
		when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

		// Call the method
		UserAdminDTO result = userAdminService.updateUser(userAdminDTO);

		// Verify the result
		assertEquals("test@example.com", result.getEmail());
		assertEquals("newUsername", result.getUsername());
		assertEquals("encodedNewPassword", result.getPassword());
	}

	@Test
	public void testUpdateUser_NonExistingUser() {
		// Mock data
		UserAdminDTO userAdminDTO = new UserAdminDTO();
		userAdminDTO.setEmail("test@example.com");
		userAdminDTO.setUsername("newUsername");
		userAdminDTO.setPassword("newPassword");

		// Mock repository methods
		when(userAdminRepository.existsById("test@example.com")).thenReturn(false);

		// Call the method and verify the exception
		assertThrows(UserNotFoundException.class, () -> userAdminService.updateUser(userAdminDTO));
	}

	@Test
	public void testGetUserByEmail_ExistingUser() {
		// Mock data
		UserAdmin userAdmin = new UserAdmin();
		userAdmin.setEmail("test@example.com");
		userAdmin.setUsername("testUser");
		userAdmin.setPassword("encodedPassword");

		// Mock repository methods
		when(userAdminRepository.findById("test@example.com")).thenReturn(Optional.of(userAdmin));

		// Mock modelMapper
		when(modelMapper.map(userAdmin, UserAdminDTO.class))
				.thenReturn(new UserAdminDTO("test@example.com", "testUser", "encodedPassword"));

		// Call the method
		UserAdminDTO result = userAdminService.getUserByEmail("test@example.com");

		// Verify the result
		assertEquals("test@example.com", result.getEmail());
		assertEquals("testUser", result.getUsername());
		assertEquals("encodedPassword", result.getPassword());
	}

	@Test
    public void testGetUserByEmail_NonExistingUser() {
        // Mock repository methods
        when(userAdminRepository.findById("test@example.com")).thenReturn(Optional.empty());

        // Call the method and verify the exception
        assertThrows(UserNotFoundException.class, () -> userAdminService.getUserByEmail("test@example.com"));
    }
}