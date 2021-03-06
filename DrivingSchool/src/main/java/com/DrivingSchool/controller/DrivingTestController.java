package com.DrivingSchool.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrivingSchool.dto.DrivingTestDTO;
import com.DrivingSchool.dto.TerminClientDTO;
import com.DrivingSchool.mapper.DrivingTestMapper;
import com.DrivingSchool.model.DrivingTest;
import com.DrivingSchool.service.interfaces.DrivingTestService;

@RestController
@RequestMapping(value = "/drivingTest", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrivingTestController {
	
	@Autowired
	private DrivingTestService drivingTestService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addTest")
    public ResponseEntity<?> addTest(@RequestBody DrivingTestDTO drivingTest){
		return new ResponseEntity<>(drivingTestService.addTest(DrivingTestMapper.TestDTOToTest(drivingTest), drivingTest.categoryAndType), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/testDates")
    public ResponseEntity<?> getTestDates(){
		return new ResponseEntity<>(drivingTestService.getAllDrivingTestDates(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/testsForDate")
    public ResponseEntity<?> getTestsForDate(String date){
		List<DrivingTestDTO> tests = new ArrayList<>();
		for(DrivingTest dt : drivingTestService.getTestsForDate(date)) tests.add(DrivingTestMapper.TestToTestDTO(dt));
		return new ResponseEntity<>(tests, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/reservedTestForCandidate")
	public ResponseEntity<?> getReservedTestsForCandidate(String candidateEmail){
		return new ResponseEntity<>(DrivingTestMapper.TestToTestDTO(drivingTestService.getCandidateReservedDrivingTest(candidateEmail)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/testsForCandidate")
    public ResponseEntity<?> getTestsForCandidate(String candidateEmail, String date){
		List<DrivingTestDTO> tests = new ArrayList<>();
		for(DrivingTest dt : drivingTestService.getCandidateTestsForDate(candidateEmail, date))
			tests.add(DrivingTestMapper.TestToTestDTO(dt));
		return new ResponseEntity<>(tests, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/testsDatesForCandidate")
    public ResponseEntity<?> getTestDatesForCandidate(String candidateEmail){
		return new ResponseEntity<>(drivingTestService.getCandidateDrivingTestDates(candidateEmail), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/scheduleTest")
	public ResponseEntity<?> scheduleTest(@RequestBody TerminClientDTO dto){
		drivingTestService.scheduleTest(dto.clientEmail, dto.terminId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/deleteTest/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		return new ResponseEntity<>(drivingTestService.deleteTest(id), HttpStatus.OK);
    }
	
	@PutMapping("/passTest/{id}")
    public ResponseEntity<?> passById(@PathVariable("id") int id) {
		return new ResponseEntity<>(drivingTestService.passTest(id), HttpStatus.OK);
    }
	@PutMapping("/failTest/{id}")
    public ResponseEntity<?> failById(@PathVariable("id") int id) {
		return new ResponseEntity<>(drivingTestService.failTest(id), HttpStatus.OK);
    }
}
