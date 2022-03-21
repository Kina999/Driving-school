package com.DrivingSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.dto.CandidateRegistrationDTO;
import com.DrivingSchool.dto.EditCandidateProfileDTO;
import com.DrivingSchool.model.Candidate;
import com.DrivingSchool.repository.CandidateRepository;
import com.DrivingSchool.service.interfaces.CandidateService;
import com.DrivingSchool.service.interfaces.WorkerService;

@Service
public class CandidateServiceImpl implements CandidateService{

	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private WorkerService workerService;
	
	@Override
	public boolean RegisterCandidate(CandidateRegistrationDTO candidate) {
		if(candidateRepository.findCandidateByEmail(candidate.email) == null && workerService.findWorkerByEmail(candidate.email) == null) {
			Candidate newCandidate = new Candidate(candidate.email, candidate.password, candidate.name, candidate.lastname, candidate.phoneNumber, false, null, null);
			candidateRepository.save(newCandidate);
			return true;
		}
		return false;
	}

	@Override
	public Candidate CheckIfCandidateExists(String email, String password) {
		return candidateRepository.findCanidateByEmailAndPassword(email, password);
	}

	@Override
	public boolean EditCandidateProfile(EditCandidateProfileDTO candidate) {
		candidateRepository.updateClient(candidate.email, candidate.password, candidate.name, candidate.lastName, candidate.phoneNumber);
		return true;
	}

	@Override
	public Candidate findCandidateByEmail(String candidateEmail) {
		return candidateRepository.findCandidateByEmail(candidateEmail);
	}

}
