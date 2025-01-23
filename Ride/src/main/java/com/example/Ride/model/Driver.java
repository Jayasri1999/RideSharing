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
public class Driver implements Serializable {
	
	@Id
	private Long id;
	private String name;
	private int[] location;
	private boolean status;

}
