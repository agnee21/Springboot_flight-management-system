package com.flightmanagementsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightmanagementsystem.entity.Flight;
import com.flightmanagementsystem.exception.FlightManagementException;
import com.flightmanagementsystem.serviceimpl.IFlightServiceImpl;

@ExtendWith(MockitoExtension.class)
class FlightControllerUnitTest {

	@Mock
	private IFlightServiceImpl flightService;

	@InjectMocks
	private FlightController flightController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddFlight() {
		Flight flight = new Flight();
		when(flightService.addFlight(flight)).thenReturn("Flight added successfully");
		String result = flightController.addFlight(flight);
		assertEquals("Flight added successfully", result);
	}

	@Test
	void testGetByFlightId() throws FlightManagementException {
		int flightId = 123;
		Flight flight = new Flight();
		when(flightService.viewByFlightId(flightId)).thenReturn(flight);
		Flight result = flightController.getByFlightId(flightId);
		assertEquals(flight, result);
	}

	@Test
	void testGetAllFlights() {
		List<Flight> flights = new ArrayList<>();
		when(flightService.viewAllFlights()).thenReturn(flights);
		List<Flight> result = flightController.getAllFlights();
		assertEquals(flights, result);
	}

	@Test
	void testGetByFlightName() {
		List<Flight> flights = new ArrayList<>();
		when(flightService.viewByFlightName()).thenReturn(flights);
		List<Flight> result = flightController.getByFlightName();
		assertEquals(flights, result);
	}

	@Test
	void testGetByFlightSeatCapacity() {
		List<Flight> flights = new ArrayList<>();
		when(flightService.viewByFlightSeatCapacity()).thenReturn(flights);
		List<Flight> result = flightController.getByFlightSeatCapacity();
		assertEquals(flights, result);
	}

	@Test
	void testGetBySourceDestination() throws FlightManagementException {
		String source = "Source";
		String destination = "Destination";
		List<Flight> flights = new ArrayList<>();
		when(flightService.viewBySourceDestination(source, destination)).thenReturn(flights);
		List<Flight> result = flightController.getBySourceDestination(source, destination);
		assertEquals(flights, result);
	}

	@Test
	void testGetBySourceDestinationDate() throws FlightManagementException {
		String source = "Source";
		String destination = "Destination";
		LocalDate departureDate = LocalDate.now();
		List<Flight> flights = new ArrayList<>();
		when(flightService.viewBySourceDestinationAndDepartureDate(source, destination, departureDate))
				.thenReturn(flights);
		List<Flight> result = flightController.getBySourceDestinationDate(source, destination, departureDate);
		assertEquals(flights, result);
	}

	@Test
	void testGetByDepartureDate() throws FlightManagementException {
		LocalDate departureDate = LocalDate.now();
		List<Flight> flights = new ArrayList<>();
		when(flightService.viewByDepartureDate(departureDate)).thenReturn(flights);
		List<Flight> result = flightController.getByDepartureDate(departureDate);
		assertEquals(flights, result);
	}

	@Test
	void testUpdateFlight() {
		Flight flight = new Flight();
		when(flightService.updateFlight(flight)).thenReturn(flight);
		Flight result = flightController.updateFlight(flight);
		assertEquals(flight, result);
	}
}

