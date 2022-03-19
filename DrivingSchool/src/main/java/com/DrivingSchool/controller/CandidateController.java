package com.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrivingSchool.dto.CandidateRegistrationDTO;
import com.DrivingSchool.dto.EditCandidateProfileDTO;
import com.DrivingSchool.service.interfaces.CandidateService;

@RestController
@RequestMapping(value = "/candidates", produces = MediaType.APPLICATION_JSON_VALUE)
public class CandidateController {

	@Autowired 
	private CandidateService candidateService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/registration")
    public ResponseEntity<?> registerClient(@RequestBody CandidateRegistrationDTO candidate){
		return new ResponseEntity<>(candidateService.RegisterCandidate(candidate), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/editProfile")
    public ResponseEntity<?> editClientProfile(@RequestBody EditCandidateProfileDTO candidate){
		return new ResponseEntity<>(candidateService.EditCandidateProfile(candidate), HttpStatus.OK);
	}
}
