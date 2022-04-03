package com.DrivingSchool.service.interfaces;

import java.util.List;
import java.util.Set;

import com.DrivingSchool.model.Termin;

public interface TerminService {
	public boolean deleteTermin(Integer id);
	public boolean cancelTermin(int id, String candidateEmail);
	public List<Termin> getAllCandidateTermins(String candidateEmail);
	public List<Termin> getAllInstructorTermins(String instructorEmail);
	public Set<String> getAllCandidateTerminDates(String candidateEmail);
	public boolean addClientToTermin(String clientEmail, Integer terminId);
	public Set<String> getAllInstructorTerminDates(String instructorEmail);
	public Set<String> getAllCandidatePossibleTerminDates(String candidateEmail);
	public List<Termin> getAllCandidateTerminForDate(String candidateEmail, String date);
	public List<Termin> getAllInstructorTerminTimes(String instructorEmail, String date);
	public List<Termin> getAllCandidatePossibleTerminForDate(String candidateEmail, String date);
	public boolean addTerminToInstructor(Termin termin, String instructorEmail, String categoryAndType);
}
