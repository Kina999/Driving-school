package com.DrivingSchool.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {
	
	@Id
	private String tables; //id
	private int manufactureYear;
	private LocalDateTime lastRegistrationDate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "driving_school_id")
	private DrivingSchool drivingSchool = new DrivingSchool();
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id")
	private Category category = new Category();
	
	@ManyToMany(mappedBy = "vehicle")
	private Set<CarMechanic> carMechanics = new HashSet<CarMechanic>();
	
	public Vehicle() {
		super();
	}

	public Vehicle(String tables, int manufactureYear, LocalDateTime lastRegistrationDate,
			DrivingSchool drivingSchool, Category category, Set<CarMechanic> carMechanics) {
		super();
		this.tables = tables;
		this.manufactureYear = manufactureYear;
		this.lastRegistrationDate = lastRegistrationDate;
		this.drivingSchool = drivingSchool;
		this.category = category;
		this.carMechanics = carMechanics;
	}

	public String getTables() {
		return tables;
	}
	public void setTables(String tables) {
		this.tables = tables;
	}
	public int getManufactureYear() {
		return manufactureYear;
	}
	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}
	public LocalDateTime getLastRegistrationDate() {
		return lastRegistrationDate;
	}
	public void setLastRegistrationDate(LocalDateTime lastRegistrationDate) {
		this.lastRegistrationDate = lastRegistrationDate;
	}

	public DrivingSchool getDrivingSchool() {
		return drivingSchool;
	}

	public void setDrivingSchool(DrivingSchool drivingSchool) {
		this.drivingSchool = drivingSchool;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<CarMechanic> getCarMechanics() {
		return carMechanics;
	}

	public void setCarMechanics(Set<CarMechanic> carMechanics) {
		this.carMechanics = carMechanics;
	}
	
	
}
