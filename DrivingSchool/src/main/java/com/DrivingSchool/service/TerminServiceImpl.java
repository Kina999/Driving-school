package com.DrivingSchool.service;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.model.Termin;
import com.DrivingSchool.repository.TerminRepository;
import com.DrivingSchool.service.interfaces.InstructorService;
import com.DrivingSchool.service.interfaces.LicenceService;
import com.DrivingSchool.service.interfaces.TerminService;

@Service
public class TerminServiceImpl implements TerminService{

	@Autowired
	private TerminRepository terminRepository;
	@Autowired
	private InstructorService instructorService;
	@Autowired
	private LicenceService licenceService;
	
	@Override
	public boolean addTerminToInstructor(Termin termin, String instructorEmail, String categoryAndType) {
		LocalDate newTermin = termin.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		for(Termin t : terminRepository.findInstructorTermins(instructorEmail)) {
			LocalDate oldTermin = t.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if((oldTermin.getDayOfYear() == newTermin.getDayOfYear() && oldTermin.getYear() == newTermin.getYear()) && (termin.getStartTime().getTime() <= t.getStartTime().getTime() && termin.getEndTime().getTime() >= t.getEndTime().getTime())
					&& (termin.getStartTime().getTime() <= t.getStartTime().getTime() && termin.getEndTime().getTime() <= t.getEndTime().getTime())
					&& (termin.getStartTime().getTime() >= t.getStartTime().getTime() && termin.getEndTime().getTime() >= t.getEndTime().getTime())){
					return false;
			}
		}
		if(licenceService.findLicenceByCategoryAndType(categoryAndType).getExpirationDate().before(termin.getEndTime())) {
			return false;
		}
		termin.setLicence(licenceService.findLicenceByCategoryAndType(categoryAndType));
		termin.setInstructor(instructorService.getInstructorByMail(instructorEmail));
		terminRepository.save(termin);
		return true;
	}

}
