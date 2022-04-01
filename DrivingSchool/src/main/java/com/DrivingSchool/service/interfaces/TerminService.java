package com.DrivingSchool.service.interfaces;

import java.util.List;
import java.util.Set;

import com.DrivingSchool.model.Termin;

public interface TerminService { 
	public List<Termin> getAllInstructorTermins(String instructorEmail);
	public boolean addClientToTermin(String clientEmail, Integer terminId);
	public Set<String> getAllInstructorTerminDates(String instructorEmail);
	public Set<String> getAllCandidatePossibleTerminDates(String candidateEmail);
	public List<Termin> getAllInstructorTerminTimes(String instructorEmail, String date);
	public List<Termin> getAllCandidatePossibleTerminForDate(String candidateEmail, String date);
	public boolean addTerminToInstructor(Termin termin, String instructorEmail, String categoryAndType);
}
