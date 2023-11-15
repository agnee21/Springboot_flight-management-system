package com.flightmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightmanagementsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//custom method to get user by user name
	User findByUserName(String name);
}
