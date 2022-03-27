package com.DrivingSchool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.dto.CandidateRegistrationDTO;
import com.DrivingSchool.dto.EditCandidateProfileDTO;
import com.DrivingSchool.model.Candidate;
import com.DrivingSchool.model.Worker;
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
	public boolean registerCandidate(CandidateRegistrationDTO candidate) {
		if(candidateRepository.findCandidateByEmail(candidate.email) == null && workerService.findWorkerByEmail(candidate.email) == null) {
			Candidate newCandidate = new Candidate(candidate.email, candidate.password, candidate.name, candidate.lastname, candidate.phoneNumber, false, null, null);
			candidateRepository.save(newCandidate);
			return true;
		}
		return false;
	}

	@Override
	public Candidate checkIfCandidateExists(String email, String password) {
		return candidateRepository.findCanidateByEmailAndPassword(email, password);
	}

	@Override
	public boolean editCandidateProfile(EditCandidateProfileDTO candidate) {
		candidateRepository.updateClient(candidate.email, candidate.password, candidate.name, candidate.lastName, candidate.phoneNumber);
		return true;
	}

	@Override
	public Candidate findCandidateByEmail(String candidateEmail) {
		return candidateRepository.findCandidateByEmail(candidateEmail);
	}

	@Override
	public boolean addInstructorToCandidate(String instructorEmail, String candidateEmail) {
		Candidate candidate = candidateRepository.findCandidateByEmail(candidateEmail);
		Worker worker = workerService.findWorkerByEmail(instructorEmail);
		if(candidate != null && worker != null) {
			candidateRepository.setCandidateInstructor(candidateEmail, instructorEmail);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Worker getInstructor(String email) {
		Candidate candidate = candidateRepository.findCandidateByEmail(email);		
		if(candidate != null) {
			return candidateRepository.findCandidateByEmail(email).getInstructor();
		}else {
			return null;
		}
	}

	@Override
	public List<Candidate> getInstructorCandidates(String email) {
		return candidateRepository.findInstructorCandidates(email);
	}

}
