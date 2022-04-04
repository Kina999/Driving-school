package com.DrivingSchool.dto;

public class CandidateCancelingDTO {
	
	public int numberOfCancelations;
	public int numberOfDays;
	
	public CandidateCancelingDTO() {super();}
	
	public CandidateCancelingDTO(int numberOfCancelations, int numberOfDays) {
		super();
		this.numberOfCancelations = numberOfCancelations;
		this.numberOfDays = numberOfDays;
	}
	
}
