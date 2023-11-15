package com.flightmanagementsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightmanagementsystem.dto.UserDTO;
import com.flightmanagementsystem.entity.User;
import com.flightmanagementsystem.exception.UserManagementException;
import com.flightmanagementsystem.serviceimpl.IUserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserServiceImpl userService;

	@PostMapping("/signup")
	public UserDTO signUp(@Valid @RequestBody User user) {
		return userService.registerUser(user);
	}

	@GetMapping("/signin/{username}/{password}")
	public UserDTO logIn(@PathVariable("username") String userName, @PathVariable("password") String password)
			throws UserManagementException {
		return userService.signIn(userName, password);
	}
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/allusers")
	public List<UserDTO> getAllUsers() {
		return userService.viewAllUsers();
	}
	@DeleteMapping("/delete/{userid}")
	public String deleteUser(@PathVariable("userid") Long userId) throws UserManagementException {
		return userService.deleteUser(userId);
	}

	@GetMapping("/signout")
	public String logOut() {
		return userService.signOut();
	} 
}
