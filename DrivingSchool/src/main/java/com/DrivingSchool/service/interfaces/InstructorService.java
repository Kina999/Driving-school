package com.DrivingSchool.service.interfaces;

import java.util.List;

import com.DrivingSchool.model.Instructor;

public interface InstructorService {
	public List<Instructor> getAll();
	public Instructor getInstructorByMail(String email);
	public boolean AddNewInstructor(Instructor instructor);
	public boolean EditInstructorProfile(Instructor instructor);
}
