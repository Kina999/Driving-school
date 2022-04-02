package com.DrivingSchool.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.DrivingSchool.enumClasses.TestType;

@Entity
public class Candidate {
	
	@Id
	private String email;
	private String password;
	private String name;
	private String lastName;
	private String phoneNumber;
	private boolean blocked;
	private String category;
	private TestType classType;
	private int numberOfClasses;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "instructor_id")
	private Instructor instructor = new Instructor();
	
	@ManyToMany(mappedBy = "candidates")
	private Set<Termin> classes = new HashSet<Termin>();
	
	public Candidate() {
		super();
	}

	

	public Candidate(String email, String password, String name, String lastName, String phoneNumber, boolean blocked, int numberOfClasses, TestType classType) {
		super();
		this.classType = classType;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.blocked = blocked;
		this.numberOfClasses = numberOfClasses;
	}



	public Candidate(String email, String password, String name, String lastName, String phoneNumber, boolean blocked,
			Instructor instructor, Set<Termin> classes, int numberOfClasses, TestType classType) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.classType = classType;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.blocked = blocked;
		this.instructor = instructor;
		this.classes = classes;
		this.numberOfClasses = numberOfClasses;
	}

	public int getNumberOfClasses() {
		return numberOfClasses;
	}
	public void setNumberOfClasses(int numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Termin> getClasses() {
		return classes;
	}

	public void setClasses(Set<Termin> classes) {
		this.classes = classes;
	}

	public TestType getClassType() {
		return classType;
	}
	
	public void setClassType(TestType classType) {
		this.classType = classType;
	}

}
