package com.flightmanagementsystem.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightmanagementsystem.entity.Booking;
import com.flightmanagementsystem.entity.Passenger;
import com.flightmanagementsystem.exception.BookingManagementException;
import com.flightmanagementsystem.repository.BookingRepository;
import com.flightmanagementsystem.service.IBookingService;

@Service
public class IBookingServiceImpl implements IBookingService {

	@Autowired
	BookingRepository bookingRepository;

	// method to add booking
	@Override
	public String addBooking(Booking booking) {
		bookingRepository.save(booking);

		return "Booking successfull";
	}

	// method to cancel booking
	@Override
	public String cancelBooking(Integer bookingid) throws BookingManagementException {
		if (bookingRepository.findById(bookingid).isEmpty()) {
			throw new BookingManagementException("Booking does not exist.");
		} else
			bookingRepository.deleteById(bookingid);
		return "Booking cancelled";
	}

	// method to get list of all bookings
	@Override
	public List<Booking> viewBookings() {

		return bookingRepository.findAll();
	}

	// method to get list of bookings based on passenger id
	@Override
	public List<Booking> viewBookingByPassengerId(Long passengerId) throws BookingManagementException {
		List<Booking> bookingList = bookingRepository.findAll().stream()
				.filter(e -> hasMatchingPassenger(e, passengerId)).collect(Collectors.toList());
		if (bookingList.size() == 0) {
			throw new BookingManagementException("No booking for given passenger Id");
		} else
			return bookingList;

	}

	// Method to check for matching passenger for given ID
	private boolean hasMatchingPassenger(Booking booking, Long passengerId) {

		for (Passenger passenger : booking.getPassengerList()) {
			if (passenger.getPassengerUIN().equals(passengerId)) {
				return true;
			}
		}
		return false;
	}

	// method to get a booking based on booking Id
	@Override
	public Booking viewBookingByBookingId(Integer bookingid) throws BookingManagementException {

		if (!bookingRepository.existsById(bookingid)) {
			throw new BookingManagementException("Booking does not exist for given BookingId.");
		} else
			return bookingRepository.findById(bookingid).get();
	}

	// method to get a list of booking by booking date
	@Override
	public List<Booking> viewBookingByDate(LocalDate bookingdate) throws BookingManagementException {
		List<Booking> bookingList = bookingRepository.findAll().stream()
				.filter(e -> e.getBookingDate().isEqual(bookingdate)).collect(Collectors.toList());
		if (bookingList.size() == 0) {
			throw new BookingManagementException("No booking available for given bookingDate.");
		} else
			return bookingList;
	}

	// method to get a list of booking by flight id
	@Override
	public List<Booking> viewBookingsByFlightId(Integer flightid) throws BookingManagementException {
		List<Booking> bookingList = bookingRepository.findAll().stream()
				.filter(e -> e.getFlight().getFlightId() == flightid).collect(Collectors.toList());
		if (bookingList.size() == 0) {
			throw new BookingManagementException("No booking available for given flight.");
		} else
			return bookingList;
	}

	// method to update booking
	@Override
	public Booking updateBooking(Booking booking) {
		Booking object = new Booking();
		object.setBookingId(booking.getBookingId());
		object.setBookingDate(booking.getBookingDate());
		object.setPassengerList(booking.getPassengerList());
		object.setTotalCost(booking.getTotalCost());
		object.setFlight(booking.getFlight());
		bookingRepository.save(object);
		return object;
	}

}
