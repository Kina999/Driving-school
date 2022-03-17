package com.DrivingSchool.model;

import javax.persistence.Entity;

@Entity
public class Instructor extends Worker{

	private double grade;
	
	public Instructor() {
		super();
	}

	public Instructor(double grade) {
		super();
		this.grade = grade;
	}
	
	public Instructor(String email, String password, String name, String lastName, DrivingSchool drivingSchool) {
		super(email, password, name, lastName, drivingSchool);
	}
	
	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
}
