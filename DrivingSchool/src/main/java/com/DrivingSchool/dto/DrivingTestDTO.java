package com.DrivingSchool.dto;

import java.util.Date;

public class DrivingTestDTO {
	public int id;
	public boolean passed;
	public Date dateAndTime;
	public String categoryAndType;
	public CandidateRegistrationDTO candidate;
	
	public DrivingTestDTO() {super();}

	public DrivingTestDTO(int id, Date dateAndTime, String categoryAndType, CandidateRegistrationDTO candidate) {
		super();
		this.id = id;
		this.passed = (dateAndTime).before(new Date()) ? true : false;
		this.dateAndTime = dateAndTime;
		this.categoryAndType = categoryAndType;
		this.candidate = candidate;
	}
	
}
