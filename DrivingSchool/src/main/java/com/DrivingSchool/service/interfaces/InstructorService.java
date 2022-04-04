package com.DrivingSchool.service.interfaces;

import java.util.List;

import com.DrivingSchool.model.Instructor;

public interface InstructorService {
	public List<Instructor> getAll();
	public Instructor getInstructorByMail(String email);
	public boolean addNewInstructor(Instructor instructor);
	public boolean editInstructorProfile(Instructor instructor);
	public boolean leaveGrade(String instructorEmail, String grade);
}
