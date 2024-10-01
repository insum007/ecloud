package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dto.UserRequestDTO;
import entity.User;
import repository.UserRepository;

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
	public User createUser(UserRequestDTO userRequestDTO) {
		String hashedPassword = hashPasswordSHA256(userRequestDTO.getPassword());
		User user = new User();
		user.setUsername(userRequestDTO.getUsername());
		user.setPassword(hashedPassword);
		user.setActive(true);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User updateUser(Long id, UserRequestDTO userRequestDTO) {
		String hashedPassword = hashPasswordSHA256(userRequestDTO.getPassword());
		Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			User updatedUser = existingUser.get();
			updatedUser.setUsername(userRequestDTO.getUsername());
			if (!userRequestDTO.getPassword().isEmpty()) {
				updatedUser.setPassword(hashedPassword);
			}
			updatedUser.setActive(userRequestDTO.isActive());
			return userRepository.save(updatedUser);
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

	// Method to hash password using SHA-256
	private String hashPasswordSHA256(String password) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = messageDigest.digest(password.getBytes());
			return bytesToHex(hashedBytes);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password", e);
		}
	}

	// Convert byte array to hexadecimal string
	private String bytesToHex(byte[] bytes) {
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
