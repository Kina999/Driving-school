package com.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrivingSchool.dto.TerminClientDTO;
import com.DrivingSchool.dto.TerminDTO;
import com.DrivingSchool.mapper.TerminMapper;
import com.DrivingSchool.service.interfaces.TerminService;

@RestController
@RequestMapping(value = "/termins", produces = MediaType.APPLICATION_JSON_VALUE)
public class TerminController {

	@Autowired
	private TerminService terminService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addTermin")
    public ResponseEntity<?> addTermin(@RequestBody TerminDTO termin){
		return new ResponseEntity<>(terminService.addTerminToInstructor(TerminMapper.TerminDTOToTermin(termin), termin.instructorEmail, termin.categoryAndType), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addClientToTermin")
    public ResponseEntity<?> addClientToTermin(@RequestBody TerminClientDTO termin){
		return new ResponseEntity<>(terminService.addClientToTermin(termin.clientEmail, termin.terminId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAllInstructorTermins")
    public ResponseEntity<?> getAllInstructorTermins(String instructorEmail){	
		return new ResponseEntity<>(terminService.getAllInstructorTermins(instructorEmail), HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAllInstructorTerminDates")
    public ResponseEntity<?> getAllInstructorTerminDates(String instructorEmail){	
		return new ResponseEntity<>(terminService.getAllInstructorTerminDates(instructorEmail), HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAllInstructorTerminTimesForDate")
    public ResponseEntity<?> getAllInstructorTerminTimes(String instructorEmail, String date){	
		return new ResponseEntity<>(terminService.getAllInstructorTerminTimes(instructorEmail, date), HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAllCandidatePossibleTerminDates")
    public ResponseEntity<?> getAllCandidatePossibleTerminDates(String candidateEmail){	
		return new ResponseEntity<>(terminService.getAllCandidatePossibleTerminDates(candidateEmail), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAllCandidatePossibleTerminForDate")
    public ResponseEntity<?> getAllCandidatePossibleTerminForDate(String candidateEmail, String date){	
		return new ResponseEntity<>(terminService.getAllCandidatePossibleTerminForDate(candidateEmail, date), HttpStatus.OK);
	}
}
