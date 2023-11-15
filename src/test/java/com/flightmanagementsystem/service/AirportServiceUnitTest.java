package com.flightmanagementsystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
import com.flightmanagementsystem.exception.AirportManagementException;
import com.flightmanagementsystem.repository.AirportRepository;
import com.flightmanagementsystem.serviceimpl.IAirportServiceImpl;

@ExtendWith(MockitoExtension.class)
class AirportServiceUnitTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private IAirportServiceImpl airportService;

    // Sample data for testing
    private Airport sampleAirport;

    @BeforeEach
    void setUp() {
        // Initialize or set up resources before each test
        sampleAirport = new Airport(1, "Sample Airport", "Sample City", "Sample Country");
    }

    @AfterEach
    void tearDown() {
        // Clean up or release resources after each test
    	sampleAirport =  null;
    	
    	
    }

    @Test
    void addAirportDetails() {
        // Test data
        Airport airportToAdd = new Airport(2, "New Airport", "New City", "New Country");

        // Mock behavior
        when(airportRepository.save(airportToAdd)).thenReturn(airportToAdd);

        // Method invocation
        String result = airportService.addAirportDetails(airportToAdd);

        // Assertions
        assertEquals("Airport added.", result);

        // Verifications
        verify(airportRepository, times(1)).save(airportToAdd);
    }

    @Test
    void viewAirports() {
        // Mock behavior
        when(airportRepository.findAll()).thenReturn(Arrays.asList(sampleAirport));

        // Method invocation
        List<Airport> airports = airportService.viewAirports();

        // Assertions
        assertEquals(1, airports.size());
        assertEquals(sampleAirport, airports.get(0));

        // Verifications
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void viewByCountry() throws AirportManagementException {
        // Mock behavior
        when(airportRepository.findAll()).thenReturn(Arrays.asList(sampleAirport));

        // Method invocation
        List<Airport> airports = airportService.viewByCountry("Sample Country");

        // Assertions
        assertEquals(1, airports.size());
        assertEquals(sampleAirport, airports.get(0));

        // Verifications
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void viewByCity() throws AirportManagementException {
        // Mock behavior
        when(airportRepository.findAll()).thenReturn(Arrays.asList(sampleAirport));

        // Method invocation
        List<Airport> airports = airportService.viewByCity("Sample City");

        // Assertions
        assertEquals(1, airports.size());
        assertEquals(sampleAirport, airports.get(0));

        // Verifications
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void viewByAirportName() throws AirportManagementException {
        // Mock behavior
        when(airportRepository.findAll()).thenReturn(Arrays.asList(sampleAirport));

        // Method invocation
        List<Airport> airports = airportService.viewByAirportName("Sample Airport");

        // Assertions
        assertEquals(1, airports.size());
        assertEquals(sampleAirport, airports.get(0));

        // Verifications
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void updateAirportDetails() {
        // Test data
        Airport airportToUpdate = new Airport(1, "Updated Airport", "Updated City", "Updated Country");

        // Mock behavior
        when(airportRepository.save(any())).thenReturn(airportToUpdate);

        // Method invocation
        Airport updatedAirport = airportService.updateAirportDetails(airportToUpdate);

        // Assertions
        assertEquals(airportToUpdate, updatedAirport);

        // Verifications
        verify(airportRepository, times(1)).save(any());
    }
}

