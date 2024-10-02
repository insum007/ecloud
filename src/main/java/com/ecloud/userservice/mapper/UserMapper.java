package com.ecloud.userservice.mapper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ecloud.userservice.dto.UserRequestDTO;
import com.ecloud.userservice.entity.User;

public class UserMapper {

	// Convert UserRequestDTO to User entity
	public static User toUserEntity(UserRequestDTO userRequestDTO) {
		
		return User.builder()
		    .username(userRequestDTO.getUsername())
		    .active(userRequestDTO.isActive())
		    .build();
	}

	// Method to hash password using SHA-256
	public static String hashPasswordSHA256(String password) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = messageDigest.digest(password.getBytes());
			return bytesToHex(hashedBytes);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password", e);
		}
	}

	// Convert byte array to hexadecimal string
	private static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
