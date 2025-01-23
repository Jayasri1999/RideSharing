package com.example.Ride.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passenger implements Serializable {
	@Id
	public Long id;
	public String name;
	public int[] location;
	public int[] destination;
}
