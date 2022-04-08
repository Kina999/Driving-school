package com.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrivingSchool.dto.InstructorDTO;
import com.DrivingSchool.dto.InstructorGradeDTO;
import com.DrivingSchool.mapper.InstructorMapper;
import com.DrivingSchool.service.interfaces.InstructorService;

@RestController
@RequestMapping(value = "/instructors", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/registration")
    public ResponseEntity<?> registerInstructor(@RequestBody InstructorDTO instructor){
		return new ResponseEntity<>(instructorService.addNewInstructor(InstructorMapper.InstructorDTOToInstructor(instructor)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/leaveGrade")
    public ResponseEntity<?> leaveGrade(@RequestBody InstructorGradeDTO instructor){	
		return new ResponseEntity<>(instructorService.leaveGrade(instructor.instructorEmail, instructor.grade), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<?> getAllInstructors(){	
		return new ResponseEntity<>(instructorService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/instructor")
    public ResponseEntity<?> getInstructor(String instructorEmail){	
		return new ResponseEntity<>(instructorService.getInstructorByMail(instructorEmail), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/editProfile")
    public ResponseEntity<?> editClientProfile(@RequestBody InstructorDTO instructor){
		return new ResponseEntity<>(instructorService.editInstructorProfile(InstructorMapper.InstructorDTOToInstructor(instructor)), HttpStatus.OK);
	}
	
}
