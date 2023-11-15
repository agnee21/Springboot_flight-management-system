package com.flightmanagementsystem.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightmanagementsystem.dto.UserDTO;
import com.flightmanagementsystem.entity.User;
import com.flightmanagementsystem.exception.UserManagementException;
import com.flightmanagementsystem.repository.UserRepository;
import com.flightmanagementsystem.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	// method to add a user
	@Override
	public UserDTO registerUser(User user) {

		UserDTO object = toUserDto(user);

		userRepository.save(user);

		return object;
	}

	// method for sign in
	@Override
	public UserDTO signIn(String userName, String password) throws UserManagementException {
		if (userRepository.findByUserName(userName) == null) {
			throw new UserManagementException("Invalid Username");
		}

		User user = userRepository.findByUserName(userName);

		if (!user.getPassword().equals(password)) {
			throw new UserManagementException("Invalid password");
		} else {
			UserDTO userdto = toUserDto(user);
			return userdto;
		}
	}

	// method to get a list of all users
	@Override
	public List<UserDTO> viewAllUsers() {
		return userRepository.findAll().stream().map(e -> toUserDto(e)).collect(Collectors.toList());
	}

	//method to delete a user
	@Override
	public String deleteUser(Long userId) throws UserManagementException {
		if (!userRepository.existsById(userId)) {
			throw new UserManagementException("User does not exist.");
		} else
			userRepository.deleteById(userId);

		return "User deleted.";
	}

	// method for sign out
	@Override
	public String signOut() {

		return "You have been logged out.";
	}

	// method to convert user to userDTO
	private UserDTO toUserDto(User user) {
		UserDTO object = new UserDTO();
		object.setUserId(user.getUserId());
		object.setEmail(user.getEmail());
		object.setMobileNumber(user.getMobileNumber());
		object.setUserName(user.getUserName());
		object.setUserRole(user.getUserRole());

		return object;
	}

}
