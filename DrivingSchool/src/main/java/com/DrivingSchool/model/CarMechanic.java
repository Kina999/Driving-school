package com.DrivingSchool.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class CarMechanic extends Worker{
	
	@ManyToMany
	@JoinTable(name = "mechanic_vehicle", 
			  joinColumns = @JoinColumn(name = "vehicle_id"), 
			  inverseJoinColumns = @JoinColumn(name = "mechanic_id"))
	private Set<Vehicle> vehicle = new HashSet<Vehicle>();
	
	public CarMechanic() {
		super();
	}

	public CarMechanic(String email, String password, String name, String lastName, DrivingSchool drivingSchool) {
		super(email, password, name, lastName, drivingSchool);
	}

	public CarMechanic(Set<Vehicle> vehicle) {
		super();
		this.vehicle = vehicle;
	}

	public Set<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(Set<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	
}
