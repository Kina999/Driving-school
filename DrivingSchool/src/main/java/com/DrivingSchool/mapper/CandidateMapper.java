package com.DrivingSchool.mapper;

import java.util.HashSet;
import java.util.Set;

import com.DrivingSchool.dto.CandidateRegistrationDTO;
import com.DrivingSchool.model.Candidate;

public class CandidateMapper {
	public static Set<CandidateRegistrationDTO> CandidateSetToCandidateSetDTO(Set<Candidate> candidates) {
		Set<CandidateRegistrationDTO> candidatesDTO = new HashSet<CandidateRegistrationDTO>();
		for(Candidate candidate : candidates) {
			candidatesDTO.add(CandidateToCandidateRegistrationDTO(candidate));
		}
		return candidatesDTO;
	}
	
	public static CandidateRegistrationDTO CandidateToCandidateRegistrationDTO(Candidate candidate) {
		return new CandidateRegistrationDTO(candidate.getEmail(), candidate.getPassword(), candidate.getName(), candidate.getLastName(), candidate.getPhoneNumber());
	}
}
