package com.DrivingSchool.service.interfaces;

import java.util.List;
import java.util.Set;

import com.DrivingSchool.model.Termin;

public interface TerminService { 
	public List<Termin> getAllInstructorTermins(String instructorEmail);
	public Set<String> getAllInstructorTerminDates(String instructorEmail);
	public List<Termin> getAllInstructorTerminTimes(String instructorEmail, String date);
	public boolean addTerminToInstructor(Termin termin, String instructorEmail, String categoryAndType);
}
