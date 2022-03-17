package com.DrivingSchool.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.DrivingSchool.enumClasses.TestType;

@Entity
public class DrivingTest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private TestType testType;
	private LocalDateTime testDateTime;
	
	public DrivingTest() {
		super();
	}

	public DrivingTest(TestType testType, LocalDateTime testDateTime) {
		super();
		this.testType = testType;
		this.testDateTime = testDateTime;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	public LocalDateTime getTestDateTime() {
		return testDateTime;
	}

	public void setTestDateTime(LocalDateTime testDateTime) {
		this.testDateTime = testDateTime;
	}
}
