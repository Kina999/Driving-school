package com.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrivingSchool.dto.CurrentUserDTO;
import com.DrivingSchool.dto.UserLoginDTO;
import com.DrivingSchool.model.Candidate;
import com.DrivingSchool.model.Worker;
import com.DrivingSchool.service.interfaces.CandidateService;
import com.DrivingSchool.service.interfaces.WorkerService;

@RestController
@RequestMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private WorkerService workerService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<?> registerClient(@RequestBody UserLoginDTO user){
		CurrentUserDTO currentUser = null;
		if(candidateService.checkIfCandidateExists(user.email, user.password) != null) {
			Candidate c = candidateService.checkIfCandidateExists(user.email, user.password);
			currentUser = new CurrentUserDTO(c.getEmail(), c.getPassword(), c.getName(), c.getLastName(), c.getPhoneNumber(), "CANDIDATE");
		}else if(workerService.checkIfWorkerExists(user.email, user.password) != null) {
			Worker w = workerService.checkIfWorkerExists(user.email, user.password);
			currentUser = new CurrentUserDTO(w.getEmail(), w.getPassword(), w.getName(), w.getLastName(), w.getPhoneNumber(), w.getWorkerType().toString());
		}
		return new ResponseEntity<>(currentUser, HttpStatus.OK);
	}
}
