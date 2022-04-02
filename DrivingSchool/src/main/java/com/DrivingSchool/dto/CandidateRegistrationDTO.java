package com.DrivingSchool.dto;

public class CandidateRegistrationDTO {
	public String email;
	public String password;
	public String name;
	public String lastname;
	public String phoneNumber;
	
	public CandidateRegistrationDTO() {
		super();
	}

	public CandidateRegistrationDTO(String email, String password, String name, String lastname, String phoneNumber) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
	}
}
