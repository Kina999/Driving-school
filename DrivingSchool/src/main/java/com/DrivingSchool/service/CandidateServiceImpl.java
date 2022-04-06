package com.DrivingSchool.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.dto.CandidateRegistrationDTO;
import com.DrivingSchool.dto.EditCandidateProfileDTO;
import com.DrivingSchool.enumClasses.TestType;
import com.DrivingSchool.model.Candidate;
import com.DrivingSchool.model.Category;
import com.DrivingSchool.model.Worker;
import com.DrivingSchool.repository.CandidateRepository;
import com.DrivingSchool.service.interfaces.CandidateService;
import com.DrivingSchool.service.interfaces.CategoryService;
import com.DrivingSchool.service.interfaces.WorkerService;

@Service
public class CandidateServiceImpl implements CandidateService{

	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public boolean registerCandidate(CandidateRegistrationDTO candidate) {
		if(candidateRepository.findCandidateByEmail(candidate.email) == null && workerService.findWorkerByEmail(candidate.email) == null) {
			Candidate newCandidate = new Candidate(candidate.email, candidate.password, candidate.name, candidate.lastname, candidate.phoneNumber, false, false, false, null, TestType.THEORETICAL, 0, null, null);
			candidateRepository.save(newCandidate);
			return true;
		}
		return false;
	}

	@Override
	public Candidate checkIfCandidateExists(String email, String password) {
		Candidate candidate = candidateRepository.findCanidateByEmailAndPassword(email, password);
		if(candidate != null && !candidate.isBlocked()) {
			return candidate;
		}
		return null;
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
	public boolean addInstructorToCandidate(String instructorEmail, String candidateEmail, String category) {
		Candidate candidate = candidateRepository.findCandidateByEmail(candidateEmail);
		Worker worker = workerService.findWorkerByEmail(instructorEmail);
		if(candidate != null && worker != null) {
			candidateRepository.setCandidateInstructor(candidateEmail, instructorEmail, category);
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

	@Override
	public boolean resetClassNumber(String candidateEmail) {
		if(candidateRepository.findCandidateByEmail(candidateEmail) != null) {
			candidateRepository.resetClassNumber(candidateEmail);
			return true;
		}
		return false;
	}

	@Override
	public boolean increaseClassNumber(String candidateEmail) {
		Candidate candidate = candidateRepository.findCandidateByEmail(candidateEmail);
		if(candidate != null) {
			candidateRepository.incrementClassNumber(candidateEmail, candidate.getNumberOfClasses() + 1);
			if(candidate.getNumberOfClasses() + 1 == categoryService.getCategory(candidate.getCategory()).getNumberOfClasses()) {
				if(!candidate.getClassType().equals(TestType.PRACTICAL)) {
					resetClassNumber(candidateEmail);
					candidateRepository.setClassType(candidateEmail);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean decreaseClassNumber(String candidateEmail) {
		if(candidateRepository.findCandidateByEmail(candidateEmail) != null) {
			candidateRepository.incrementClassNumber(candidateEmail, candidateRepository.findCandidateByEmail(candidateEmail).getNumberOfClasses() + 1);
			if(candidateRepository.findCandidateByEmail(candidateEmail).getNumberOfClasses() + 1 == categoryService.getCategory(candidateRepository.findCandidateByEmail(candidateEmail).getCategory()).getNumberOfClasses()) {
				resetClassNumber(candidateEmail);
				candidateRepository.setClassType(candidateEmail);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isCandidateDone(String candidateEmail) {
		Candidate candidate = candidateRepository.findCandidateByEmail(candidateEmail);
		Category category = categoryService.getCategory(candidate.getCategory());
		return candidate.isPracticalDone();
	}

	@Override
	public List<Candidate> getAllCandidates() {
		return candidateRepository.findAll();
	}

	@Override
	public boolean blockCandidate(String email) {
		if(candidateRepository.findCandidateByEmail(email) != null) {
			candidateRepository.blockUser(email);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean unblockCandidate(String email) {
		if(candidateRepository.findCandidateByEmail(email) != null) {
			candidateRepository.unblockUser(email);
			return true;
		}
		return false;
	}

}
