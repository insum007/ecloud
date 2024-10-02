package com.ecloud.userservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecloud.userservice.dto.UserRequestDTO;
import com.ecloud.userservice.dto.UserResponseDTO;
import com.ecloud.userservice.entity.User;
import com.ecloud.userservice.service.UserService;

import jakarta.validation.Valid;

/**
 * 
 * @author Musni
 *
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO createdUser = userService.createUser(userRequestDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
		List<UserResponseDTO> response = userService.getAllUsers();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<UserResponseDTO>> getUserById(@PathVariable Long id) {
		Optional<UserResponseDTO> response = userService.getUserById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
			@RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO response = userService.updateUser(id, userRequestDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		// Check if the user exists before attempting to delete
		boolean isDeleted = userService.deleteUser(id);
		if (isDeleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}