package com.DrivingSchool.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String categoryType;
	private int numberOfClasses;
	
	public Category() {
		super();
	}
	
	public Category(String categoryType, int numberOfClasses) {
		super();
		this.categoryType = categoryType;
		this.numberOfClasses = numberOfClasses;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public int getNumberOfClasses() {
		return numberOfClasses;
	}
	public void setNumberOfClasses(int numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
	}
}
