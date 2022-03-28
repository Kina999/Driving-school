package com.DrivingSchool.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Override
	public List<Termin> getAllInstructorTermins(String instructorEmail) {
		return terminRepository.findInstructorTermins(instructorEmail);
	}

	@Override
	public Set<String> getAllInstructorTerminDates(String instructorEmail) {
		Set<String> dates = new HashSet<String>();
		List<Date> d = new ArrayList<Date>();
		for(Termin t: terminRepository.findInstructorTermins(instructorEmail)) {
			d.add(t.getStartTime());
		}
		Collections.sort(d, new Comparator<Date>() {
			@Override
			public int compare(final Date d1, final Date d2) {return d1.compareTo(d2);}});
		Collections.reverse(d);
		for(Date termin : d) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
			String strDate = dateFormat.format(termin);  
			dates.add(strDate.split(" ")[0]);
		}
		return dates;
	}

	@Override
	public List<Termin> getAllInstructorTerminTimes(String instructorEmail, String date) {
		List<Termin> termins = new ArrayList<Termin>();
		for(Termin termin : terminRepository.findInstructorTermins(instructorEmail)) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
			String strDate = dateFormat.format(termin.getStartTime());  
			if(strDate.split(" ")[0].equals(date)) {
				termins.add(termin);
			}
		}
		Collections.sort(termins, new Comparator<Termin>() {
			@Override
			public int compare(final Termin d1, final Termin d2) {return d1.getStartTime().compareTo(d2.getStartTime());}});
		return termins;

	}

}
