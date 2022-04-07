package com.DrivingSchool.dto;

public class CandidateProgressDTO {
	
	public int theoreticalDone;
	public int practicalDone;
	public int necessaryClasses;
	public boolean practicalTestDone;
	public boolean theoreticalTestDone;
	
	public CandidateProgressDTO() {super();}

	public CandidateProgressDTO(int theoreticalDone, int practicalDone, int necessaryClasses, boolean practicalTestDone,
			boolean theoreticalTestDone) {
		super();
		this.theoreticalDone = theoreticalDone;
		this.practicalDone = practicalDone;
		this.necessaryClasses = necessaryClasses;
		this.practicalTestDone = practicalTestDone;
		this.theoreticalTestDone = theoreticalTestDone;
	}
	
}
