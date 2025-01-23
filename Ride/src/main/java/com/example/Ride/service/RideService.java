package com.example.Ride.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Ride.model.Driver;
import com.example.Ride.model.Passenger;

@Service
public class RideService {
	
	List<Driver> drivers;
	Integer noOfTrips=0;
	public RideService() {
		this.drivers=new ArrayList<Driver>();
	}
	public String addDriver(Driver driver) throws Exception{
		validateLocation(driver.getLocation());
		drivers.add(driver);
		return "Driver Added:"+driver.getName();
	}
	public String requestRide(Passenger passenger) throws Exception {
		//can be handled by try catch as well. Not used to show the propagation of the error.
		validateLocation(passenger.getLocation());
		validateLocation(passenger.getDestination());
		Driver driverAssigned=assignDriver(passenger);
		Double fairCollected=0.0;
		if(driverAssigned!=null) {
			//collectFair
			fairCollected=collectFair(passenger.getLocation(),passenger.getDestination());
			//set driver status
			driverAssigned.setStatus(false);
			noOfTrips++;
		}else {
			return "Driver Not available";
		}
		
		return "Trip Started: "+noOfTrips+", Driver: "+driverAssigned.getName()+" Fare: "+fairCollected;
	}
	private Driver assignDriver(Passenger passenger) {
		int passengerX=passenger.getLocation()[0];
		int passengerY=passenger.getLocation()[1];
		double minDistance=Double.MAX_VALUE;
		int ind=-1;
		for(int i=0;i<drivers.size();i++) {
			int driverX=drivers.get(i).getLocation()[0];
			int driverY=drivers.get(i).getLocation()[1];
			double distance=Math.sqrt(Math.pow((driverX-passengerX),2)+Math.pow((driverY-passengerY),2));
			//if available- true, not available-false
			if(drivers.get(i).isStatus() && distance<minDistance) {
				ind=i;
				minDistance = distance;
			}
		}
		if(ind==-1) return null;
		
		return drivers.get(ind);
	}
	private Double collectFair(int[] currLocation, int[] destination) {
		int currX=currLocation[0];
		int currY=currLocation[1];
		int destX=destination[0];
		int destY=destination[1];
		double distance=Math.sqrt(Math.pow((destX-currX),2)+Math.pow((currY-destY),2));
		//per unit distance, fair is fixed as 2 dollars. can change if required
		return distance*2;
	}
	
	private Boolean validateLocation(int[] location) throws Exception{
		if(location[0]<0 || location[1]<0) {
			throw new Exception("Invalid Location");
		}
		return true;
	}
	

}
