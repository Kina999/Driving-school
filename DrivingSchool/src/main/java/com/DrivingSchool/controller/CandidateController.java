package com.DrivingSchool.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrivingSchool.dto.CandidateInstructorDTO;
import com.DrivingSchool.dto.CandidateRegistrationDTO;
import com.DrivingSchool.dto.EditCandidateProfileDTO;
import com.DrivingSchool.mapper.CandidateMapper;
import com.DrivingSchool.model.Candidate;
import com.DrivingSchool.service.interfaces.CandidateService;
import com.DrivingSchool.service.interfaces.InstructorRequestService;

@RestController
@RequestMapping(value = "/candidates", produces = MediaType.APPLICATION_JSON_VALUE)
public class CandidateController {

	@Autowired 
	private CandidateService candidateService;
	@Autowired
	private InstructorRequestService instructorRequestService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getInstructor")
    public ResponseEntity<?> getInstructor(String email){	
		return new ResponseEntity<>(candidateService.getInstructor(email), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/registration")
    public ResponseEntity<?> registerClient(@RequestBody CandidateRegistrationDTO candidate){
		return new ResponseEntity<>(candidateService.registerCandidate(candidate), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/editProfile")
    public ResponseEntity<?> editClientProfile(@RequestBody EditCandidateProfileDTO candidate){
		return new ResponseEntity<>(candidateService.editCandidateProfile(candidate), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addInstructor")
    public ResponseEntity<?> addInstuctorToCandidate(@RequestBody CandidateInstructorDTO instructor){
		String category = instructorRequestService.approveRequest(instructor.instructorEmail, instructor.candidateEmail);
		return new ResponseEntity<>(candidateService.addInstructorToCandidate(instructor.instructorEmail, instructor.candidateEmail, category), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getInstructorCandidates")
    public ResponseEntity<?> getInstructorCandidates(String email){	
		List<CandidateRegistrationDTO> candidates = new ArrayList<CandidateRegistrationDTO>();
		for(Candidate candidate : candidateService.getInstructorCandidates(email)) {
			candidates.add(CandidateMapper.CandidateToCandidateRegistrationDTO(candidate));
		}
		return new ResponseEntity<>(candidates, HttpStatus.OK);
	}
}
