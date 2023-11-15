package com.flightmanagementsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightmanagementsystem.entity.Airport;
import com.flightmanagementsystem.exception.AirportManagementException;
import com.flightmanagementsystem.serviceimpl.IAirportServiceImpl;

@ExtendWith(MockitoExtension.class)
class AirportControllerUnitTest {

	@Mock
	private IAirportServiceImpl airportService;

	@InjectMocks
	private AirportController airportController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddAirport() {
		Airport airport = new Airport(); // Create a sample Airport object for testing
		when(airportService.addAirportDetails(airport)).thenReturn("Airport added successfully");

		String result = airportController.addAirport(airport);

		assertEquals("Airport added successfully", result);
	}

	@Test
	void testGetAllAirport() {
		List<Airport> airports = new ArrayList<>(); // Create a sample list of airports for testing
		when(airportService.viewAirports()).thenReturn(airports);

		List<Airport> result = airportController.getAllAirport();

		assertEquals(airports, result);
	}

	@Test
	void testGetByCountry() throws AirportManagementException {
		List<Airport> airports = new ArrayList<>(); // Create a sample list of airports for testing
		String country = "TestCountry";
		when(airportService.viewByCountry(country)).thenReturn(airports);

		List<Airport> result = airportController.getByCountry(country);

		assertEquals(airports, result);
	}

	@Test
	void testGetByCity() throws AirportManagementException {
		List<Airport> airports = new ArrayList<>(); // Create a sample list of airports for testing
		String city = "TestCity";
		when(airportService.viewByCity(city)).thenReturn(airports);

		List<Airport> result = airportController.getByCity(city);

		assertEquals(airports, result);
	}

	@Test
	void testGetByAirportName() throws AirportManagementException {
		List<Airport> airports = new ArrayList<>(); // Create a sample list of airports for testing
		String airportName = "TestAirport";
		when(airportService.viewByAirportName(airportName)).thenReturn(airports);

		List<Airport> result = airportController.getByAirportName(airportName);

		assertEquals(airports, result);
	}

	@Test
	void testUpdateAirport() {
		Airport airport = new Airport(); // Create a sample Airport object for testing
		when(airportService.updateAirportDetails(airport)).thenReturn(airport);

		Airport result = airportController.updateAirport(airport);

		assertEquals(airport, result);
	}

	@Test
	void testExceptionHandling() throws AirportManagementException {
		String country = "InvalidCountry";
		when(airportService.viewByCountry(country)).thenThrow(new AirportManagementException("Country not found"));

		assertThrows(AirportManagementException.class, () -> airportController.getByCountry(country));
	}
}
