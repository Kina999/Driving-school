package com.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrivingSchool.dto.CandidateInstructorDTO;
import com.DrivingSchool.service.interfaces.InstructorRequestService;

@RestController
@RequestMapping(value = "/instructorRequests", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructorRequestController {
	
	@Autowired
	private InstructorRequestService instructorRequestService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addInstructorRequest")
    public ResponseEntity<?> addInstuctorToCandidate(@RequestBody CandidateInstructorDTO instructor){
		return new ResponseEntity<>(instructorRequestService.addInstructorRequest(instructor.instructorEmail, instructor.candidateEmail), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getInstructorRequest")
    public ResponseEntity<?> getInstructorRequest(String email){	
		return new ResponseEntity<>(instructorRequestService.getInstructorRequest(email), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getInstructorRequests")
    public ResponseEntity<?> getInstructorAllRequests(String instructorEmail){	
		return new ResponseEntity<>(instructorRequestService.getInstructorRequests(instructorEmail), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/refuseRequest")
    public ResponseEntity<?> refuseCandidateRequest(@RequestBody CandidateInstructorDTO instructor){
		return new ResponseEntity<>(instructorRequestService.refuseRequest(instructor.instructorEmail, instructor.candidateEmail), HttpStatus.OK);
	}
}
