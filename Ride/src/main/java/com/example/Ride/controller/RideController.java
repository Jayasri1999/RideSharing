package com.example.Ride.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ride.model.Driver;
import com.example.Ride.model.Passenger;
import com.example.Ride.service.RideService;

@RestController
@RequestMapping("/api/rides")
public class RideController {
	@Autowired
	RideService rideService;
	
	@PostMapping("/addDriver")
	public String addDriver(@RequestBody Driver driver) throws Exception {
		return rideService.addDriver(driver);
	}
	
	@PostMapping("/requestRide")
	public String requestRide(@RequestBody Passenger passenger) throws Exception {
		return rideService.requestRide(passenger);
	}

}
