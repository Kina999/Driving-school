package com.DrivingSchool.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.DrivingSchool.enumClasses.CategoryType;

@Entity
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private CategoryType categoryType;
	private int numberOfClasses;
	
	public Category() {
		super();
	}
	
	public Category(CategoryType categoryType, int numberOfClasses) {
		super();
		this.categoryType = categoryType;
		this.numberOfClasses = numberOfClasses;
	}
	
	public CategoryType getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}
	public int getNumberOfClasses() {
		return numberOfClasses;
	}
	public void setNumberOfClasses(int numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
	}
}
