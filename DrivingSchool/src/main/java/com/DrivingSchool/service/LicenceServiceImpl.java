package com.DrivingSchool.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.enumClasses.TestType;
import com.DrivingSchool.model.Instructor;
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
		if(email.equals("")) {
			return licenceRepository.findAll();
		}
		return licenceRepository.findInstructorLicences(email);
	}
	@Override
	public Licence findLicenceByCategoryAndType(String categoryAndType) { 
		return licenceRepository.findLicenceByCategoryAndType(categoryService.getCategory((categoryAndType.split("-")[0]).toString().trim()).getId(), (((categoryAndType.split("-")[1]).toString().trim()).equals("THEORETICAL") ? 0 : 1));
	}

	@Override
	public Set<Instructor> getAllInstructorsWithCategory(String category) {
		List<Instructor> instructors = new ArrayList<Instructor>();
		for(Licence licence : licenceRepository.findInstructorForCategory(categoryService.getCategory(category).getId())) {
			if(licence.getExpirationDate().after(new Date())) {
				instructors.add(licence.getInstructor());
			}
		}
		Set<Instructor> ret = new HashSet<Instructor>();
		for(Instructor i : instructors) {
			int numOfCounts = 0;
			for(Instructor ins : instructors) {
				if(ins.getEmail().equals(i.getEmail())) {
					numOfCounts++;
				}
			}
			if(numOfCounts == 2) {
				ret.add(i);
			}
		}
		return ret;
	}
	@Override
	public List<Licence> findInstructorLicences(String instructorEmail) {
		return licenceRepository.findInstructorLicences(instructorEmail);
	}
}
	