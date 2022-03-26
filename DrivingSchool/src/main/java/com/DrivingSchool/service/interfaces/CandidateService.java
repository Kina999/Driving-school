package com.DrivingSchool.service.interfaces;

import java.util.List;

import com.DrivingSchool.dto.CandidateRegistrationDTO;
import com.DrivingSchool.dto.EditCandidateProfileDTO;
import com.DrivingSchool.model.Candidate;
import com.DrivingSchool.model.Worker;

public interface CandidateService {
	public Worker getInstructor(String email);
	public List<Candidate> getInstructorCandidates(String email);
	public Candidate findCandidateByEmail(String candidateEmail);
	public boolean RegisterCandidate(CandidateRegistrationDTO candidate);
	public boolean EditCandidateProfile(EditCandidateProfileDTO candidate);
	public Candidate CheckIfCandidateExists(String email, String password);
	public boolean addInstructorToCandidate(String instructorEmail, String candidateEmail);
}
