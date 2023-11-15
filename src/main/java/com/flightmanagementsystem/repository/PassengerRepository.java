package com.flightmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightmanagementsystem.entity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
	 
	//custom method to get passenger by passenger UIN 
	Passenger findByPassengerUIN(Long uin);
	
	//custom method to get passenger by mobile number
	Passenger findByMobileNumber(Long contact);
}
