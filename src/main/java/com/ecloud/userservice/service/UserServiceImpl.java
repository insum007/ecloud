package com.ecloud.userservice.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecloud.userservice.dto.UserRequestDTO;
import com.ecloud.userservice.dto.UserResponseDTO;
import com.ecloud.userservice.entity.User;
import com.ecloud.userservice.mapper.UserMapper;
import com.ecloud.userservice.mapper.UserResponseMapper;
import com.ecloud.userservice.repository.UserRepository;

/**
 * 
 * @author Musni
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		User user = UserMapper.toUserEntity(userRequestDTO);
		userRepository.save(user);
		UserResponseDTO userResponseDTO = UserResponseMapper.toResponseDTO(user);
		return userResponseDTO;
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
	    List<User> users = userRepository.findAll();

	    // Use the UserResponseMapper to convert the list of User entities to UserResponseDTO
	    return users.stream()
	                .map(UserResponseMapper::toResponseDTO)
	                .collect(Collectors.toList());
	}

	@Override
	public Optional<UserResponseDTO> getUserById(Long id) {
	    return userRepository.findById(id)
	                         .map(UserResponseMapper::toResponseDTO);
	}
	
	@Override
	public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
		String hashedPassword = UserMapper.hashPasswordSHA256(userRequestDTO.getPassword());
		Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			User updatedUser = existingUser.get();
			updatedUser.setUsername(userRequestDTO.getUsername());
			if (!userRequestDTO.getPassword().isEmpty()) {
				updatedUser.setPassword(hashedPassword);
			}
			updatedUser.setActive(userRequestDTO.isActive());
			userRepository.save(updatedUser);
			UserResponseDTO userResponseDTO = UserResponseMapper.toResponseDTO(updatedUser);
			return userResponseDTO;
		}
		return null;
	}

	@Override
	public boolean deleteUser(Long id) {
		// Find the user by ID
		Optional<User> userOptional = userRepository.findById(id);

		// Check if the user exists
		if (userOptional.isPresent()) {
			userRepository.delete(userOptional.get()); // Delete the user
			return true; // User deleted successfully
		}

		return false;
	}

}
