package com.DrivingSchool.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.enumClasses.TestType;
import com.DrivingSchool.model.Class;
import com.DrivingSchool.model.Termin;
import com.DrivingSchool.repository.TerminRepository;
import com.DrivingSchool.service.interfaces.CandidateService;
import com.DrivingSchool.service.interfaces.ClassService;
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
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private ClassService classService;
	
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
		Set<String> dates = new LinkedHashSet<String>();
		List<Date> d = new ArrayList<Date>();
		for(Termin t: terminRepository.findInstructorTermins(instructorEmail)) {
			d.add(t.getStartTime());
		}
		Collections.sort(d, new Comparator<Date>() {
			@Override
			public int compare(final Date d1, final Date d2) {return d1.compareTo(d2);}});
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

	@Override
	public Set<String> getAllCandidatePossibleTerminDates(String candidateEmail) {
		Set<String> dates = new LinkedHashSet<String>();
		List<Date> d = new ArrayList<Date>();
		for(Termin t: terminRepository.findInstructorTermins(candidateService.findCandidateByEmail(candidateEmail).getInstructor().getEmail())) {
			if(t.getLicence().getCategory().getCategoryType().equals(candidateService.findCandidateByEmail(candidateEmail).getCategory())
					&& t.getLicence().getLicenceType().equals(getLicenceType(candidateEmail))) {
				d.add(t.getStartTime());
			}
		}
		Collections.sort(d, new Comparator<Date>() {
			@Override
			public int compare(final Date d1, final Date d2) {return d1.compareTo(d2);}});
		for(Date termin : d) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
			String strDate = dateFormat.format(termin);  
			dates.add(strDate.split(" ")[0]);
		}
		return null;
	}
	
	private TestType getLicenceType(String candidateEmail) {
		Class latestClass = classService.getLatestCandidateClass(candidateEmail);
		if(latestClass.getNumberOfClass() < latestClass.getLicence().getCategory().getNumberOfClasses()) {
			return latestClass.getLicence().getLicenceType();
		}else {
			if(latestClass.getLicence().getLicenceType().equals(TestType.THEORETICAL)) {
				return TestType.PRACTICAL;
			}else {
				return null; //nema vise sve casove zavrsiooo
			}
		}
	}

	@Override
	public List<Termin> getAllCandidatePossibleTerminForDate(String candidateEmail, String date) {
		List<Termin> termins = new ArrayList<Termin>();
		for(Termin t : terminRepository.findInstructorTermins(candidateService.findCandidateByEmail(candidateEmail).getInstructor().getEmail())) {
			if(t.getLicence().getCategory().getCategoryType().equals(candidateService.findCandidateByEmail(candidateEmail).getCategory())
					&& t.getLicence().getLicenceType().equals(getLicenceType(candidateEmail))) {
			
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
				String strDate = dateFormat.format(t.getStartTime());  
				if(strDate.split(" ")[0].equals(date)) {
					termins.add(t);
				}
			}
		}
		Collections.sort(termins, new Comparator<Termin>() {
			@Override
			public int compare(final Termin d1, final Termin d2) {return d1.getStartTime().compareTo(d2.getStartTime());}});
		return termins;
	}

}
