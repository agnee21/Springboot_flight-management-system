package com.flightmanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightmanagementsystem.entity.Airport;
import com.flightmanagementsystem.entity.Flight;
import com.flightmanagementsystem.entity.Schedule;
import com.flightmanagementsystem.exception.FlightManagementException;
import com.flightmanagementsystem.repository.FlightRepository;
import com.flightmanagementsystem.serviceimpl.IFlightServiceImpl;

@ExtendWith(MockitoExtension.class)
class FlightServiceUnitTest {

	@Mock
	private FlightRepository flightRepository;

	@InjectMocks
	private IFlightServiceImpl flightService;

	// Sample data for testing
	private Flight sampleFlight;
	private Schedule sampleSchedule;
	

	@BeforeEach
	void setUp() {
		// Initialize or set up resources before each test
		
		sampleSchedule = new Schedule(1, new Airport(), new Airport(), LocalDateTime.now(),
				LocalDateTime.now().plusHours(1));

		sampleFlight = new Flight(1, "Sample Flight", 100, 12.0, Arrays.asList(sampleSchedule));
	}

	@AfterEach
	void tearDown() {
		// Clean up or release resources after each test
		sampleFlight = null;
		sampleSchedule = null;
	}

	@Test
    void addFlight() {
        // Mock behavior
        when(flightRepository.save(any())).thenReturn(sampleFlight);

        // Method invocation
        String result = flightService.addFlight(sampleFlight);

        // Assertions
        assertEquals("Flight addded successfully", result);

        // Verifications
        verify(flightRepository, times(1)).save(sampleFlight);
    }

	@Test
    void viewByFlightId() throws FlightManagementException {
        // Mock behavior
        when(flightRepository.existsById(1)).thenReturn(true);
        when(flightRepository.findById(1)).thenReturn(java.util.Optional.of(sampleFlight));

        // Method invocation
        Flight result = flightService.viewByFlightId(1);

        // Assertions
        assertEquals(sampleFlight, result);

        // Verifications
        verify(flightRepository, times(1)).existsById(1);
        verify(flightRepository, times(1)).findById(1);
    }

	@Test
    void viewAllFlights() {
        // Mock behavior
        when(flightRepository.findAll()).thenReturn(Arrays.asList(sampleFlight));

        // Method invocation
        List<Flight> result = flightService.viewAllFlights();

        // Assertions
        assertEquals(1, result.size());
        assertEquals(sampleFlight, result.get(0));

        // Verifications
        verify(flightRepository, times(1)).findAll();
    }

	@Test
    void viewByFlightName() {
        // Mock behavior
        when(flightRepository.findAll()).thenReturn(Arrays.asList(sampleFlight));

        // Method invocation
        List<Flight> result = flightService.viewByFlightName();

        // Assertions
        assertEquals(1, result.size());
        assertEquals(sampleFlight, result.get(0));

        // Verifications
        verify(flightRepository, times(1)).findAll();
    }

	@Test
    void viewByFlightSeatCapacity() {
        // Mock behavior
        when(flightRepository.findAll()).thenReturn(Arrays.asList(sampleFlight));

        // Method invocation
        List<Flight> result = flightService.viewByFlightSeatCapacity();

        // Assertions
        assertEquals(1, result.size());
        assertEquals(sampleFlight, result.get(0));

        // Verifications
        verify(flightRepository, times(1)).findAll();
    }

}

