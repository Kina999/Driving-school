package com.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrivingSchool.dto.InstructorLicenceDTO;
import com.DrivingSchool.mapper.LicenceMapper;
import com.DrivingSchool.service.interfaces.LicenceService;

@RestController
@RequestMapping(value = "/licences", produces = MediaType.APPLICATION_JSON_VALUE)
public class LicenceController {

	@Autowired 
	private LicenceService licenceService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<?> getAllLicences(String email){
		return new ResponseEntity<>(licenceService.getAll(email), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/allWithCategory")
    public ResponseEntity<?> getAllInstructorsWithCategory(String category){	
		return new ResponseEntity<>(licenceService.getAllInstructorsWithCategory(category), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addLicence")
    public ResponseEntity<?> addInstructorLicence(@RequestBody InstructorLicenceDTO licence){
		return new ResponseEntity<>(licenceService.addLicenceToInstructor(licence.email, LicenceMapper.InstructorLicenceDTOToLicence(licence), licence.category), HttpStatus.OK);
	}
}
