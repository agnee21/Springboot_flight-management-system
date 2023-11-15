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

import com.flightmanagementsystem.entity.Booking;
import com.flightmanagementsystem.exception.BookingManagementException;
import com.flightmanagementsystem.serviceimpl.IBookingServiceImpl;

@ExtendWith(MockitoExtension.class)
class BookingControllerUnitTest {

	@Mock
	private IBookingServiceImpl bookingService;

	@InjectMocks
	private BookingController bookingController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddBooking() {
		Booking booking = new Booking();
		when(bookingService.addBooking(booking)).thenReturn("Booking added successfully");
		String result = bookingController.addBooking(booking);
		assertEquals("Booking added successfully", result);
	}

	@Test
	void testDeleteBooking() throws BookingManagementException {
		int bookingId = 123;
		when(bookingService.cancelBooking(bookingId)).thenReturn("Booking canceled successfully");
		String result = bookingController.deleteBooking(bookingId);
		assertEquals("Booking canceled successfully", result);
	}

	@Test
	void testViewBookings() {
		List<Booking> bookings = new ArrayList<>();
		when(bookingService.viewBookings()).thenReturn(bookings);
		List<Booking> result = bookingController.viewBookings();
		assertEquals(bookings, result);
	}

	@Test
	void testGetBookingByPassengerId() throws BookingManagementException {
		long passengerId = 456L;
		List<Booking> bookings = new ArrayList<>();
		when(bookingService.viewBookingByPassengerId(passengerId)).thenReturn(bookings);
		List<Booking> result = bookingController.getBookingByPassengerId(passengerId);
		assertEquals(bookings, result);
	}

	@Test
	void testViewBookingByBookingId() throws BookingManagementException {
		int bookingId = 789;
		Booking booking = new Booking();
		when(bookingService.viewBookingByBookingId(bookingId)).thenReturn(booking);
		Booking result = bookingController.viewBookingByBookingId(bookingId);
		assertEquals(booking, result);
	}

	@Test
	void testViewBookingByDate() throws BookingManagementException {
		LocalDate bookingDate = LocalDate.now();
		List<Booking> bookings = new ArrayList<>();
		when(bookingService.viewBookingByDate(bookingDate)).thenReturn(bookings);
		List<Booking> result = bookingController.viewBookingByDate(bookingDate);
		assertEquals(bookings, result);
	}

	@Test
	void testViewBookingsByFlightId() throws BookingManagementException {
		int flightId = 321;
		List<Booking> bookings = new ArrayList<>();
		when(bookingService.viewBookingsByFlightId(flightId)).thenReturn(bookings);
		List<Booking> result = bookingController.viewBookingsByFlightId(flightId);
		assertEquals(bookings, result);
	}

	@Test
	void testUpdateBooking() {
		Booking booking = new Booking();
		when(bookingService.updateBooking(booking)).thenReturn(booking);
		Booking result = bookingController.updateBooking(booking);
		assertEquals(booking, result);
	}
}

