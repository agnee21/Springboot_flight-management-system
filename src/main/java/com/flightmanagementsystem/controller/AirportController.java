package com.flightmanagementsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightmanagementsystem.entity.Airport;
import com.flightmanagementsystem.exception.AirportManagementException;
import com.flightmanagementsystem.serviceimpl.IAirportServiceImpl;

@RestController
@RequestMapping("/airport")
public class AirportController {
	@Autowired
	IAirportServiceImpl airportService;

	@PostMapping("/")
	public String addAirport(@Valid @RequestBody Airport airport) {
		return airportService.addAirportDetails(airport);
	}

	@GetMapping("/airports")
	public List<Airport> getAllAirport() {
		return airportService.viewAirports();
	}

	@GetMapping("/bycountry/{country}")
	public List<Airport> getByCountry(@PathVariable("country") String country) throws AirportManagementException {
		return airportService.viewByCountry(country);
	}

	@GetMapping("/bycity/{city}") 
	public List<Airport> getByCity(@PathVariable("city") String city) throws AirportManagementException {
		return airportService.viewByCity(city);
	}

	@GetMapping("/byname/{airportname}")
	public List<Airport> getByAirportName(@PathVariable("airportname") String airportName)
			throws AirportManagementException {
		return airportService.viewByAirportName(airportName);
	}

	@PutMapping("/update")
	public Airport updateAirport(@RequestBody Airport airport)  {
		return airportService.updateAirportDetails(airport);
	}

}
