package com.flightmanagementsystem.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightmanagementsystem.dto.PassengerDTO;
import com.flightmanagementsystem.entity.Passenger;
import com.flightmanagementsystem.exception.PassengerManagementException;
import com.flightmanagementsystem.service.IPassengerService;

@ExtendWith(MockitoExtension.class)
class PassengerControllerUnitTest {

    @Mock
    private IPassengerService passengerService;

    @InjectMocks
    private PassengerController passengerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPassenger() {
        Passenger passenger = new Passenger();
        when(passengerService.addPassenger(passenger)).thenReturn("Passenger added successfully");
        String result = passengerController.addPassenger(passenger);
        assertEquals("Passenger added successfully", result);
    }

    @Test
    void testGetAllPassengers() throws PassengerManagementException {
        List<PassengerDTO> passengers = new ArrayList<>();
        when(passengerService.viewAllPassenger()).thenReturn(passengers);
        List<PassengerDTO> result = passengerController.getAllPassenger();
        assertEquals(passengers, result);
    }

    @Test
    void testGetByUin() throws PassengerManagementException {
        long uin = 123456789;
        PassengerDTO passengerDTO = new PassengerDTO();
        when(passengerService.viewPassengerByUIN(uin)).thenReturn(passengerDTO);
        PassengerDTO result = passengerController.getByUin(uin);
        assertEquals(passengerDTO, result);
    }

    @Test
    void testGetByMobileNumber() throws PassengerManagementException {
        long mobileNumber = 987654321;
        PassengerDTO passengerDTO = new PassengerDTO();
        when(passengerService.viewPassengerByMobileNo(mobileNumber)).thenReturn(passengerDTO);
        PassengerDTO result = passengerController.getByMobileNumber(mobileNumber);
        assertEquals(passengerDTO, result);
    }
}

