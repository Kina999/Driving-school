package com.DrivingSchool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.enumClasses.TestType;
import com.DrivingSchool.model.Licence;
import com.DrivingSchool.repository.LicenceRepository;
import com.DrivingSchool.service.interfaces.CategoryService;
import com.DrivingSchool.service.interfaces.InstructorService;
import com.DrivingSchool.service.interfaces.LicenceService;

@Service
public class LicenceServiceImpl implements LicenceService{

	@Autowired
	private LicenceRepository licenceRepository;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private InstructorService instructorService;
	@Override
	public boolean addLicenceToInstructor(String email, Licence licence, String category) {
		Integer licenceType = 0;
		if(licence.getLicenceType().equals(TestType.PRACTICAL)) {
			licenceType = 1;
		}
		if(licenceRepository.findInstructorLicence(email, categoryService.getCategory(category).getId(), licenceType) == null) {
			licence.setInstructor(instructorService.getInstructorByMail(email));
			licence.setCategory(categoryService.getCategory(category));
			licenceRepository.save(licence);
			return true;
		}
		return false;
	}
	@Override
	public List<Licence> getAll(String email) {
		return licenceRepository.findInstructorLicences(email);
	}
	@Override
	public Licence findLicenceByCategoryAndType(String categoryAndType) { 
		return licenceRepository.findLicenceByCategoryAndType(categoryService.getCategory((categoryAndType.split("-")[0]).toString().trim()).getId(), (((categoryAndType.split("-")[1]).toString().trim()).equals("THEORETICAL") ? 0 : 1));
	}

}
	