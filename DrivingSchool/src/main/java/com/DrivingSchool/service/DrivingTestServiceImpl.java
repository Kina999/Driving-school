package com.DrivingSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.model.DrivingTest;
import com.DrivingSchool.repository.DrivingTestRepository;
import com.DrivingSchool.service.interfaces.DrivingTestService;
import com.DrivingSchool.service.interfaces.LicenceService;

@Service
public class DrivingTestServiceImpl implements DrivingTestService{

	@Autowired
	private LicenceService licenceService;
	
	@Autowired
	private DrivingTestRepository drivingTestRepository;
	
	@Override
	public boolean addTest(DrivingTest drivingTest, String categoryAndType) {
		drivingTest.setLicence(licenceService.findLicenceByCategoryAndType(categoryAndType));
		drivingTestRepository.save(drivingTest);
		return true;
	}

}
