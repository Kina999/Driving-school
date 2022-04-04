package com.DrivingSchool.dto;

public class CandidateProgressDTO {
	
	public int theoreticalDone;
	public int practicalDone;
	public int necessaryClasses;
	
	public CandidateProgressDTO() {super();}

	public CandidateProgressDTO(int theoreticalDone, int theoreticalNecessary, int practicalDone,
			int necessaryClasses) {
		super();
		this.theoreticalDone = theoreticalDone;
		this.practicalDone = practicalDone;
		this.necessaryClasses = necessaryClasses;
	}
	
}
