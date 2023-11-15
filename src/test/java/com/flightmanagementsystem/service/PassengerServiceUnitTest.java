package com.flightmanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightmanagementsystem.dto.PassengerDTO;
import com.flightmanagementsystem.entity.Passenger;
import com.flightmanagementsystem.exception.PassengerManagementException;
import com.flightmanagementsystem.repository.PassengerRepository;
import com.flightmanagementsystem.serviceimpl.IPassengerServiceImpl;

@ExtendWith(MockitoExtension.class)
class PassengerServiceUnitTest {

	@Mock
	private PassengerRepository passengerRepository;

	@InjectMocks
	private IPassengerServiceImpl passengerService;

	// Sample data for testing
	private Passenger samplePassenger;

	@BeforeEach
	void setUp() {
		// Initialize or set up resources before each test
		samplePassenger = new Passenger(1L, "John Doe", 12);

	}

	@AfterEach
	void tearDown() {
		// Clean up or release resources after each test
		samplePassenger = null;
	}

	@Test
    void addPassenger() {
        // Mock behavior
        when(passengerRepository.save(any())).thenReturn(samplePassenger);

        // Method invocation
        String result = passengerService.addPassenger(samplePassenger);

        // Assertions
        assertEquals("Passenger Added Successfully", result);

        // Verifications
        verify(passengerRepository, times(1)).save(samplePassenger);
    }

	@Test
    void viewAllPassenger() {
        // Mock behavior
        when(passengerRepository.findAll()).thenReturn(Arrays.asList(samplePassenger));

        // Method invocation
        List<PassengerDTO> result = passengerService.viewAllPassenger();

        // Assertions
        assertEquals(1, result.size());
        PassengerDTO passengerDTO = result.get(0);
        assertEquals(samplePassenger.getPassengerUIN(), passengerDTO.getPassengerUIN());
        assertEquals(samplePassenger.getPassengerName(), passengerDTO.getPassengerName());
        assertEquals(samplePassenger.getAge(), passengerDTO.getAge());
        assertEquals(samplePassenger.getUserId(), passengerDTO.getUserId());
        assertEquals(samplePassenger.getUserName(), passengerDTO.getUserName());
        assertEquals(samplePassenger.getEmail(), passengerDTO.getEmail());
        assertEquals(samplePassenger.getMobileNumber(), passengerDTO.getMobileNumber());
        assertEquals(samplePassenger.getUserRole(), passengerDTO.getUserRole());

        // Verifications
        verify(passengerRepository, times(1)).findAll();
    }

	@Test
    void viewPassengerByUIN_InvalidUIN() {
        // Mock behavior
        when(passengerRepository.findByPassengerUIN(2L)).thenReturn(null);

        // Assertions
        assertThrows(PassengerManagementException.class, () -> passengerService.viewPassengerByUIN(2L));

        // Verifications
        verify(passengerRepository, times(1)).findByPassengerUIN(2L);
    }

	@Test
    void viewPassengerByMobileNo_InvalidMobileNo() {
        // Mock behavior
        when(passengerRepository.findByMobileNumber(9876543210L)).thenReturn(null);

        // Assertions
        assertThrows(PassengerManagementException.class, () -> passengerService.viewPassengerByMobileNo(9876543210L));

        // Verifications
        verify(passengerRepository, times(1)).findByMobileNumber(9876543210L);
    }

}

