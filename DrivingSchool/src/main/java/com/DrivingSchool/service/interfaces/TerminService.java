package com.DrivingSchool.service.interfaces;

import java.util.List;
import java.util.Set;

import com.DrivingSchool.dto.CandidateCancelingDTO;
import com.DrivingSchool.model.Termin;

public interface TerminService {
	public boolean deleteTermin(Integer id);
	public boolean cancelTermin(int id, String candidateEmail);
	public List<Termin> getAllCandidateTermins(String candidateEmail);
	public List<Termin> getAllInstructorTermins(String instructorEmail);
	public Set<String> getAllCandidateTerminDates(String candidateEmail);
	public boolean candidateNotShown(int terminId, String candidateEmail);
	public boolean addClientToTermin(String clientEmail, Integer terminId);
	public Set<String> getAllInstructorTerminDates(String instructorEmail);
	public List<Termin> getCandidateCanceledTermins(String candidateEmail);
	public Termin getLatestCandidatePracticalClass(String candidateEmail);
	public Termin getLatestCandidateTheoreticalClass(String candidateEmail);	
	public CandidateCancelingDTO getCandidateCanceling(String candidateEmail);
	public Set<String> getAllCandidatePossibleTerminDates(String candidateEmail);
	public Set<String> getAllInstructorPassedTerminDates(String instructorEmail);
	public List<Termin> getAllCandidateTerminForDate(String candidateEmail, String date);
	public List<Termin> getAllInstructorTerminTimes(String instructorEmail, String date);
	public List<Termin> getAllCandidatePossibleTerminForDate(String candidateEmail, String date);
	public List<Termin> getAllInstructorPassedTerminsForDate(String instructorEmail, String date);
	public boolean addTerminToInstructor(Termin termin, String instructorEmail, String categoryAndType);
}
