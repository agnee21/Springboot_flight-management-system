package com.flightmanagementsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.flightmanagementsystem.entity.Schedule;
import com.flightmanagementsystem.exception.ScheduleManagementException;
import com.flightmanagementsystem.repository.ScheduleRepository;
import com.flightmanagementsystem.serviceimpl.IScheduleServiceImpl;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceUnitTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private IScheduleServiceImpl scheduleService;

    // Sample data for testing
    private Schedule sampleSchedule;
    private Airport sourceAirport;
    private Airport destinationAirport;

    @BeforeEach
    void setUp() {
        // Initialize or set up resources before each test
        sourceAirport = new Airport(1, "Source Airport", "City1", "Country1");
        destinationAirport = new Airport(2, "Destination Airport", "City2", "Country2");
        sampleSchedule = new Schedule(1, sourceAirport, destinationAirport, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
    }

    @AfterEach
    void tearDown() {
        // Clean up or release resources after each test
        sampleSchedule = null;
        sourceAirport = null;
        destinationAirport = null;
    }

    @Test
    void addSchedule() {
        // Mock behavior
        when(scheduleRepository.save(any())).thenReturn(sampleSchedule);

        // Method invocation
        String result = scheduleService.addSchedule(sampleSchedule);

        // Assertions
        assertEquals("Schedule added.", result);

        // Verifications
        verify(scheduleRepository, times(1)).save(sampleSchedule);
    }

    @Test
    void updateSchedule() {
        // Test data
        Schedule scheduleToUpdate = new Schedule(1, sourceAirport, destinationAirport, LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        // Mock behavior
        when(scheduleRepository.save(any())).thenReturn(scheduleToUpdate);

        // Method invocation
        Schedule updatedSchedule = scheduleService.updateSchedule(scheduleToUpdate);

        // Assertions
        assertEquals(scheduleToUpdate, updatedSchedule);

        // Verifications
        verify(scheduleRepository, times(1)).save(any());
    }

    @Test
    void viewSchedules() {
        // Mock behavior
        when(scheduleRepository.findAll()).thenReturn(Arrays.asList(sampleSchedule));

        // Method invocation
        List<Schedule> result = scheduleService.viewSchedules();

        // Assertions
        assertEquals(1, result.size());
        assertEquals(sampleSchedule, result.get(0));

        // Verifications
        verify(scheduleRepository, times(1)).findAll();
    }

    @Test
    void viewBySourceAndDestination() throws ScheduleManagementException {
        // Mock behavior
        when(scheduleRepository.findAll()).thenReturn(Arrays.asList(sampleSchedule));

        // Method invocation
        List<Schedule> result = scheduleService.viewBySourceAndDestination("Source Airport", "Destination Airport");

        // Assertions
        assertEquals(1, result.size());
        assertEquals(sampleSchedule, result.get(0));

        // Verifications
        verify(scheduleRepository, times(1)).findAll();
    }

    @Test
    void viewBySourceDestinationAndDepartureDate() throws ScheduleManagementException {
        // Mock behavior
        when(scheduleRepository.findAll()).thenReturn(Arrays.asList(sampleSchedule));

        // Method invocation
        List<Schedule> result = scheduleService.viewBySourceDestinationAndDepartureDate("Source Airport", "Destination Airport", LocalDate.now());

        // Assertions
        assertEquals(1, result.size());
        assertEquals(sampleSchedule, result.get(0));

        // Verifications
        verify(scheduleRepository, times(1)).findAll();
    }

   
}

