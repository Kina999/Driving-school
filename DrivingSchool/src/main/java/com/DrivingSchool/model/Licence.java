 package com.DrivingSchool.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.DrivingSchool.enumClasses.TestType;

@Entity
public class Licence {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date expirationDate;
	private TestType licenceType;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id")
	private Category category = new Category();
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "instructor_id")
	private Instructor instructor = new Instructor();
	
	
	public Licence() {
		super();
	}
	
	public Licence(Date expirationDate, TestType licenceType, Category category, Instructor instructor) {
		super();
		this.expirationDate = expirationDate;
		this.licenceType = licenceType;
		this.category = category;
		this.instructor = instructor;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public TestType getLicenceType() {
		return licenceType;
	}
	public void setLicenceType(TestType licenceType) {
		this.licenceType = licenceType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
}
