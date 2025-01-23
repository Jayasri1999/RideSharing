package com.example.Ride.service;

import static org.junit.jupiter.api.Assertions.*;
import com.example.Ride.model.Driver;
import com.example.Ride.model.Passenger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class RideServiceTest {

    @InjectMocks
    private RideService rideService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDriverSuccess() throws Exception {

        Driver driver = new Driver(1L, "John", new int[]{10, 15}, true);

        String result = rideService.addDriver(driver);

        assertEquals("Driver Added:John", result);
        assertTrue(rideService.drivers.contains(driver));
    }

    @Test
    void testAddDriverInvalidLocation() {

        Driver driver = new Driver(1L, "John", new int[]{-5, 15}, true);


        Exception exception = assertThrows(Exception.class, () -> rideService.addDriver(driver));
        assertEquals("Invalid Location", exception.getMessage());
    }

    @Test
    void testRequestRideSuccess() throws Exception {

        Driver driver1 = new Driver(1L, "John", new int[]{10, 15}, true);
        Driver driver2 = new Driver(2L, "Alice", new int[]{20, 25}, true);
        Passenger passenger = new Passenger(1L, "Bob", new int[]{12, 14}, new int[]{30, 40});
        rideService.addDriver(driver1);
        rideService.addDriver(driver2);

        String result = rideService.requestRide(passenger);

        assertTrue(result.contains("Trip Started"));
        assertTrue(result.contains("Driver: John")); // Closest driver
        assertTrue(result.contains("Fare:"));
        assertFalse(driver1.isStatus()); // Driver status updated to unavailable
    }

    @Test
    void testRequestRideNoDriverAvailable() throws Exception {

        Passenger passenger = new Passenger(1L, "Bob", new int[]{12, 14}, new int[]{30, 40});


        String result = rideService.requestRide(passenger);


        assertEquals("Driver Not available", result);
    }

    @Test
    void testRequestRideInvalidPassengerLocation() {

        Passenger passenger = new Passenger(1L, "Bob", new int[]{-1, 14}, new int[]{30, 40});

        Exception exception = assertThrows(Exception.class, () -> rideService.requestRide(passenger));
        assertEquals("Invalid Location", exception.getMessage());
    }

    @Test
    void testRequestRideInvalidPassengerDestination() {

        Passenger passenger = new Passenger(1L, "Bob", new int[]{12, 14}, new int[]{-30, 40});

        Exception exception = assertThrows(Exception.class, () -> rideService.requestRide(passenger));
        assertEquals("Invalid Location", exception.getMessage());
    }
}