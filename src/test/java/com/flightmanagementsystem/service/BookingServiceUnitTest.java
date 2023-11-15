package com.flightmanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightmanagementsystem.entity.Booking;
import com.flightmanagementsystem.entity.Flight;
import com.flightmanagementsystem.entity.Passenger;
import com.flightmanagementsystem.exception.BookingManagementException;
import com.flightmanagementsystem.repository.BookingRepository;
import com.flightmanagementsystem.serviceimpl.IBookingServiceImpl;

@ExtendWith(MockitoExtension.class)
class BookingServiceUnitTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private IBookingServiceImpl bookingService;

    // Sample data for testing
    private Booking sampleBooking;
    private Passenger samplePassenger;
    private Flight sampleFlight;

    @BeforeEach
    void setUp() {
        // Initialize or set up resources before each test
    	
        samplePassenger = new Passenger(1L, "John Doe", 12);
        sampleFlight = new Flight(1, "Sample Flight",  100, 12.0,null);
        sampleBooking = new Booking(1, LocalDate.now(), Arrays.asList(samplePassenger), 100.0, sampleFlight,3);
    }

    @AfterEach
    void tearDown() {
        // Clean up or release resources after each test
    	 sampleBooking  = null;
    	 sampleFlight = null;
    	 samplePassenger = null;
    }

    @Test
    void addBooking() {
        // Mock behavior
        when(bookingRepository.save(any())).thenReturn(sampleBooking);

        // Method invocation
        String result = bookingService.addBooking(sampleBooking);

        // Assertions
        assertEquals("Booking successfull", result);

        // Verifications
        verify(bookingRepository, times(1)).save(sampleBooking);
    }

    @Test
    void cancelBooking() throws BookingManagementException {
        // Mock behavior
        when(bookingRepository.findById(1)).thenReturn(java.util.Optional.of(sampleBooking));

        // Method invocation
        String result = bookingService.cancelBooking(1);

        // Assertions
        assertEquals("Booking cancelled", result);

        // Verifications
        verify(bookingRepository, times(1)).deleteById(1);
    }

    @Test
    void viewBookings() {
        // Mock behavior
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(sampleBooking));

        // Method invocation
        List<Booking> bookings = bookingService.viewBookings();

        // Assertions
        assertEquals(1, bookings.size());
        assertEquals(sampleBooking, bookings.get(0));

        // Verifications
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void viewBookingByPassengerId() throws BookingManagementException {
        // Mock behavior
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(sampleBooking));

        // Method invocation
        List<Booking> bookings = bookingService.viewBookingByPassengerId(1L);

        // Assertions
        assertEquals(1, bookings.size());
        assertEquals(sampleBooking, bookings.get(0));

        // Verifications
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void viewBookingByBookingId() throws BookingManagementException {
        // Mock behavior
        when(bookingRepository.existsById(1)).thenReturn(true);
        when(bookingRepository.findById(1)).thenReturn(java.util.Optional.of(sampleBooking));

        // Method invocation
        Booking booking = bookingService.viewBookingByBookingId(1);

        // Assertions
        assertEquals(sampleBooking, booking);

        // Verifications
        verify(bookingRepository, times(1)).existsById(1);
        verify(bookingRepository, times(1)).findById(1);
    }

    @Test
    void viewBookingByDate() throws BookingManagementException {
        // Mock behavior
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(sampleBooking));

        // Method invocation
        List<Booking> bookings = bookingService.viewBookingByDate(LocalDate.now());

        // Assertions
        assertEquals(1, bookings.size());
        assertEquals(sampleBooking, bookings.get(0));

        // Verifications
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void viewBookingsByFlightId() throws BookingManagementException {
        // Mock behavior
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(sampleBooking));

        // Method invocation
        List<Booking> bookings = bookingService.viewBookingsByFlightId(1);

        // Assertions
        assertEquals(1, bookings.size());
        assertEquals(sampleBooking, bookings.get(0));

        // Verifications
        verify(bookingRepository, times(1)).findAll();
    }

   
}

