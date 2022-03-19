package com.DrivingSchool.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.DrivingSchool.enumClasses.WorkerType;

@Entity
public class Worker {
	
	@Id
	private String email;
	private String password;
	private String name;
	private String lastName;
	private String phoneNumber;
	private WorkerType workerType;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "driving_school_id")
	private DrivingSchool drivingSchool = new DrivingSchool();
	
	public Worker() {
		super();
	}
	
	public Worker(String email, String password, String name, String lastName, String phoneNumber,
			WorkerType workerType) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.workerType = workerType;
	}



	public Worker(String email, String password, String name, String lastName, DrivingSchool drivingSchool) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.drivingSchool = drivingSchool;
	}

	public Worker(String email, String password, String name, String lastName, WorkerType workerType,
			DrivingSchool drivingSchool) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.workerType = workerType;
		this.drivingSchool = drivingSchool;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public DrivingSchool getDrivingSchool() {
		return drivingSchool;
	}

	public void setDrivingSchool(DrivingSchool drivingSchool) {
		this.drivingSchool = drivingSchool;
	}

	public WorkerType getWorkerType() {
		return workerType;
	}

	public void setWorkerType(WorkerType workerType) {
		this.workerType = workerType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
