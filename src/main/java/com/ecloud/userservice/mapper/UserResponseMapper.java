package com.ecloud.userservice.mapper;

import com.ecloud.userservice.dto.UserResponseDTO;
import com.ecloud.userservice.entity.User;

public class UserResponseMapper {

	// Convert User entity to UserResponseDTO
	public static UserResponseDTO toResponseDTO(User user) {

		return UserResponseDTO.builder()
				.id(user.getId())
				.username(user.getUsername())
				.active(user.isActive())
				.build();

	}

}
