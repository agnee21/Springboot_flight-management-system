package com.flightmanagementsystem.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightmanagementsystem.entity.Schedule;
import com.flightmanagementsystem.exception.ScheduleManagementException;
import com.flightmanagementsystem.serviceimpl.IScheduleServiceImpl;

@ExtendWith(MockitoExtension.class)
class ScheduleControllerUnitTest {

    @Mock
    private IScheduleServiceImpl scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddSchedule() {
        Schedule schedule = new Schedule();
        when(scheduleService.addSchedule(schedule)).thenReturn("Schedule added successfully");
        String result = scheduleController.addSchedule(schedule);
        assertEquals("Schedule added successfully", result);
    }

    @Test
    void testUpdateSchedule() {
        Schedule schedule = new Schedule();
        when(scheduleService.updateSchedule(schedule)).thenReturn(schedule);
        Schedule result = scheduleController.updateSchedule(schedule);
        assertEquals(schedule, result);
    }

    @Test
    void testGetAllSchedules() throws ScheduleManagementException {
        List<Schedule> schedules = new ArrayList<>();
        when(scheduleService.viewSchedules()).thenReturn(schedules);
        List<Schedule> result = scheduleController.getAllPassenger();
        assertEquals(schedules, result);
    }

    @Test
    void testGetBySourceAndDestination() throws ScheduleManagementException {
        String source = "Source";
        String destination = "Destination";
        List<Schedule> schedules = new ArrayList<>();
        when(scheduleService.viewBySourceAndDestination(source, destination)).thenReturn(schedules);
        List<Schedule> result = scheduleController.getBySourceAndDestination(source, destination);
        assertEquals(schedules, result);
    }

    @Test
    void testGetBySourceDestinationAndDepartureDate() throws ScheduleManagementException {
        String source = "Source";
        String destination = "Destination";
        LocalDate date = LocalDate.now();
        List<Schedule> schedules = new ArrayList<>();
        when(scheduleService.viewBySourceDestinationAndDepartureDate(source, destination, date)).thenReturn(schedules);
        List<Schedule> result = scheduleController.getBySourceDestinationAndDepartureDate(source, destination, date);
        assertEquals(schedules, result);
    }

    @Test
    void testGetByDepartureTime() throws ScheduleManagementException {
        LocalDateTime datetime = LocalDateTime.now();
        List<Schedule> schedules = new ArrayList<>();
        when(scheduleService.viewByDepartureTime(datetime)).thenReturn(schedules);
        List<Schedule> result = scheduleController.getByDepartureTime(datetime);
        assertEquals(schedules, result);
    }
}

