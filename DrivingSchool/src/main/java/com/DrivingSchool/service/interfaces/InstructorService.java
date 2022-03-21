package com.DrivingSchool.service.interfaces;

import java.util.List;

import com.DrivingSchool.model.Instructor;

public interface InstructorService {
	public List<Instructor> getAll();
	public boolean EditInstructorProfile(Instructor instructor);
	public boolean AddNewInstructor(Instructor instructor);
	public Instructor getInstructorByMail(String email);
}
