package com.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrivingSchool.dto.DrivingTestDTO;
import com.DrivingSchool.mapper.DrivingTestMapper;
import com.DrivingSchool.service.interfaces.DrivingTestService;

@RestController
@RequestMapping(value = "/drivingTest", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrivingTestController {
	
	@Autowired
	private DrivingTestService drivingTestService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addTest")
    public ResponseEntity<?> addTermin(@RequestBody DrivingTestDTO drivingTest){
		return new ResponseEntity<>(drivingTestService.addTest(DrivingTestMapper.TestDTOToTest(drivingTest), drivingTest.categoryAndType), HttpStatus.OK);
	}

}
