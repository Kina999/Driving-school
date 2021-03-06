package com.DrivingSchool.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.DrivingSchool.dto.CandidateTerminDTO;
import com.DrivingSchool.dto.TerminClientDTO;
import com.DrivingSchool.dto.TerminDTO;
import com.DrivingSchool.mapper.TerminMapper;
import com.DrivingSchool.model.Termin;
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

	@RequestMapping(method = RequestMethod.GET, value = "/allInstructorTermins")
    public ResponseEntity<?> getAllInstructorTermins(String instructorEmail){
		List<CandidateTerminDTO> termins = new ArrayList<CandidateTerminDTO>();
		for(Termin termin : terminService.getAllInstructorTermins(instructorEmail))
			termins.add(TerminMapper.TerminToTerminDTO(termin));
		return new ResponseEntity<>(termins, HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/allInstructorTerminDates")
    public ResponseEntity<?> getAllInstructorTerminDates(String instructorEmail){	
		return new ResponseEntity<>(terminService.getAllInstructorTerminDates(instructorEmail), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/passedInstructorTerminDates")
	public ResponseEntity<?> getAllInstructorPassedTerminDates(String instructorEmail){
		return new ResponseEntity<>(terminService.getAllInstructorPassedTerminDates(instructorEmail), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/passedInstructorTerminsForDate")
	public ResponseEntity<?> getAllInstructorPassedTerminsForDate(String instructorEmail, String date){
		List<CandidateTerminDTO> termins = new ArrayList<CandidateTerminDTO>();
		for(Termin termin : terminService.getAllInstructorPassedTerminsForDate(instructorEmail, date))
			termins.add(TerminMapper.TerminToTerminDTO(termin));
		return new ResponseEntity<>(termins, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/candidateCancelingData")
    public ResponseEntity<?> getCandidateCanceling(String email){	
		return new ResponseEntity<>(terminService.getCandidateCanceling(email), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/allCandidateTermins")
	public ResponseEntity<?> getAllCandidateTermins(String candidateEmail){
		return new ResponseEntity<>(terminService.getAllCandidateTermins(candidateEmail), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/allInstructorTerminTimesForDate")
    public ResponseEntity<?> getAllInstructorTerminTimes(String instructorEmail, String date){	
		List<CandidateTerminDTO> termins = new ArrayList<CandidateTerminDTO>();
		for(Termin termin : terminService.getAllInstructorTerminTimes(instructorEmail, date))
			termins.add(TerminMapper.TerminToTerminDTO(termin));
		return new ResponseEntity<>(termins, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/allCandidatePossibleTerminDates")
    public ResponseEntity<?> getAllCandidatePossibleTerminDates(String candidateEmail){	
		return new ResponseEntity<>(terminService.getAllCandidatePossibleTerminDates(candidateEmail), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/allCandidatePossibleTerminForDate")
    public ResponseEntity<?> getAllCandidatePossibleTerminsForDate(String candidateEmail, String date){
		List<CandidateTerminDTO> termins = new ArrayList<CandidateTerminDTO>();
		for(Termin termin : terminService.getAllCandidatePossibleTerminForDate(candidateEmail, date))
			termins.add(TerminMapper.TerminToTerminDTO(termin));
		return new ResponseEntity<>(termins, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/allCandidateTerminDates")
	public ResponseEntity<?> getAllCandidateTerminDates(String candidateEmail){
		return new ResponseEntity<>(terminService.getAllCandidateTerminDates(candidateEmail), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/allCandidateTerminForDate")
	public ResponseEntity<?> getAllCandidateTerminForDate(String candidateEmail, String date){
		List<CandidateTerminDTO> termins = new ArrayList<CandidateTerminDTO>();
		for(Termin termin : terminService.getAllCandidateTerminForDate(candidateEmail, date))
			termins.add(TerminMapper.TerminToTerminDTO(termin));
		return new ResponseEntity<>(termins, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteTermin/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		return new ResponseEntity<>(terminService.deleteTermin(id), HttpStatus.OK);
    }

	@PutMapping("/notShowed/{id}/{candidate}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id, @PathVariable("candidate") String candidate ) {
		return new ResponseEntity<>(terminService.candidateNotShown(id, candidate), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cancelTermin")
	public ResponseEntity<?> cancelTerminById(int id, String candidateEmail) {
		return new ResponseEntity<>(terminService.cancelTermin(id, candidateEmail), HttpStatus.OK);
	}
}
