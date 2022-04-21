package com.DrivingSchool.dto;

public class CandidateCancelingDTO {
	
	public int numberOfCancelations;
	public int numberOfDays;
	public int notShownTermins;
	
	public CandidateCancelingDTO() {super();}
	
	public CandidateCancelingDTO(int numberOfCancelations, int numberOfDays, int notShownTermins) {
		super();
		this.numberOfCancelations = numberOfCancelations;
		this.numberOfDays = numberOfDays;
		this.notShownTermins = notShownTermins;
	}
	
}
