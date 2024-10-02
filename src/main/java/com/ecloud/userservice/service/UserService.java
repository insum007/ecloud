package com.ecloud.userservice.service;

import java.util.List;
import java.util.Optional;

import com.ecloud.userservice.dto.UserRequestDTO;
import com.ecloud.userservice.dto.UserResponseDTO;

public interface UserService {

	public UserResponseDTO createUser(UserRequestDTO userRequestDTO);

	public List<UserResponseDTO> getAllUsers();

	public Optional<UserResponseDTO> getUserById(Long id);

	public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

	public boolean deleteUser(Long id);

}
