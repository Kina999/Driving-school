package com.DrivingSchool.dto;

import java.util.Date;

public class DrivingTestDTO {
	public int id;
	public Date dateAndTime;
	public String categoryAndType;
	
	public DrivingTestDTO() {super();}

	public DrivingTestDTO(int id, Date dateAndTime, String categoryAndType) {
		super();
		this.id = id;
		this.dateAndTime = dateAndTime;
		this.categoryAndType = categoryAndType;
	}
	
}
