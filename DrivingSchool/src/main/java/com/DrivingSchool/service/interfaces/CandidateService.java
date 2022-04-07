package com.DrivingSchool.service.interfaces;

import java.util.List;

import com.DrivingSchool.dto.CandidateRegistrationDTO;
import com.DrivingSchool.dto.EditCandidateProfileDTO;
import com.DrivingSchool.model.Candidate;
import com.DrivingSchool.model.Worker;

public interface CandidateService {
	public void saveCandidate(Candidate c);
	public List<Candidate> getAllCandidates();
	public Worker getInstructor(String email);
	public boolean blockCandidate(String email);
	public boolean unblockCandidate(String email);
	public boolean isCandidateDone(String candidateEmail);
	public boolean resetClassNumber(String candidateEmail);
	public boolean increaseClassNumber(String candidateEmail);
	public boolean decreaseClassNumber(String candidateEmail);
	public List<Candidate> getInstructorCandidates(String email);
	public Candidate findCandidateByEmail(String candidateEmail);
	public boolean registerCandidate(CandidateRegistrationDTO candidate);
	public boolean editCandidateProfile(EditCandidateProfileDTO candidate);
	public Candidate checkIfCandidateExists(String email, String password);
	public boolean addInstructorToCandidate(String instructorEmail, String candidateEmail, String category);
}
