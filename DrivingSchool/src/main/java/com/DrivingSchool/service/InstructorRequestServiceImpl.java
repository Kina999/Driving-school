package com.DrivingSchool.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.model.Candidate;
import com.DrivingSchool.model.InstructorRequest;
import com.DrivingSchool.repository.InstructorRequestRepository;
import com.DrivingSchool.service.interfaces.CandidateService;
import com.DrivingSchool.service.interfaces.InstructorRequestService;

@Service
public class InstructorRequestServiceImpl implements InstructorRequestService{

	@Autowired
	private InstructorRequestRepository instructorRequestRepository;
	@Autowired
	private CandidateService candidateService;
	
	@Override
	public boolean addInstructorRequest(String instructorEmail, String candidateEmail) {
		if(instructorRequestRepository.findInstructorRequest(candidateEmail, instructorEmail) == null) {
			InstructorRequest instructorRequest = new InstructorRequest(instructorEmail, candidateEmail, false, false);
			instructorRequestRepository.save(instructorRequest);
			return true;
		}
		return false;
	}

	@Override
	public InstructorRequest getInstructorRequest(String candidateEmail) {
		return instructorRequestRepository.findInstructorRequest(candidateEmail);
	}

	@Override
	public List<Candidate> getInstructorRequests(String instructorEmail) {
		List<Candidate> instructorRequestedCandidates = new ArrayList<Candidate>();
		for(InstructorRequest ir : instructorRequestRepository.findInstructorRequests(instructorEmail)) {
			instructorRequestedCandidates.add(candidateService.findCandidateByEmail(ir.getCandidateEmail()));
		}
		return instructorRequestedCandidates;
	}

	@Override
	public boolean approveRequest(String instructorEmail, String candidateEmail) {
		if(instructorRequestRepository.findInstructorRequest(candidateEmail, instructorEmail) != null) {
			instructorRequestRepository.approveRequest(instructorEmail, candidateEmail);
			return true;
		}
		return false;
	}

	@Override
	public boolean refuseRequest(String instructorEmail, String candidateEmail) {
		if(instructorRequestRepository.findInstructorRequest(candidateEmail, instructorEmail) != null) {
			instructorRequestRepository.refuseRequest(instructorEmail, candidateEmail);
			return true;
		}
		return false;
	}

}
