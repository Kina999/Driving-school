package com.DrivingSchool.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InstructorRequest {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String instructorEmail;
	private String candidateEmail;
	private boolean approved;
	private boolean refused;
	
	public InstructorRequest() {
		super();
	}
	
	public InstructorRequest(String instructorEmail, String candidateEmail, boolean approved, boolean refused) {
		super();
		this.instructorEmail = instructorEmail;
		this.candidateEmail = candidateEmail;
		this.approved = approved;
		this.refused = refused;
	}

	public String getInstructorEmail() {
		return instructorEmail;
	}

	public void setInstructorEmail(String instructorEmail) {
		this.instructorEmail = instructorEmail;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isRefused() {
		return refused;
	}

	public void setRefused(boolean refused) {
		this.refused = refused;
	}
	
}
