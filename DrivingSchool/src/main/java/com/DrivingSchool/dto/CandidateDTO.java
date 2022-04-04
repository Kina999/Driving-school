package com.DrivingSchool.dto;

public class CandidateDTO {
	public String email;
	public String password;
	public String name;
	public String lastname;
	public String phoneNumber;
	public boolean blocked;
	
	public CandidateDTO() {super();}

	public CandidateDTO(String email, String password, String name, String lastname, String phoneNumber, boolean blocked) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.blocked = blocked;
	}
}
