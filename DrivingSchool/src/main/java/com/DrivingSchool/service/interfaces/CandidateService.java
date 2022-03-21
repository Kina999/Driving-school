package com.DrivingSchool.service.interfaces;

import com.DrivingSchool.dto.CandidateRegistrationDTO;
import com.DrivingSchool.dto.EditCandidateProfileDTO;
import com.DrivingSchool.model.Candidate;

public interface CandidateService {
	public Candidate findCandidateByEmail(String candidateEmail);
	public boolean RegisterCandidate(CandidateRegistrationDTO candidate);
	public boolean EditCandidateProfile(EditCandidateProfileDTO candidate);
	public Candidate CheckIfCandidateExists(String email, String password);
}
