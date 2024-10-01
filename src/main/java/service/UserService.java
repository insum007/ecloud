package service;

import java.util.List;
import java.util.Optional;

import dto.UserRequestDTO;
import entity.User;

public interface UserService {

	public User createUser(UserRequestDTO userRequestDTO);

	public List<User> getAllUsers();

	public Optional<User> getUserById(Long id);

	public User updateUser(Long id, UserRequestDTO userRequestDTO);

	public boolean deleteUser(Long id);

}
