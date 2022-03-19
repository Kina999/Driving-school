package com.DrivingSchool.dto;

public class CurrentUserDTO {
	public String email;
	public String password;
	public String name;
	public String lastName;
	public String phoneNumber;
	public String role;
	
	public CurrentUserDTO(String email, String password, String name, String lastName, String phoneNumber, String role) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}
	
}
