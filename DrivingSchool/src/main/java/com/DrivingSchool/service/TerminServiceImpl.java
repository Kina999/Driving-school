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
import com.DrivingSchool.model.Candidate;
import com.DrivingSchool.model.Licence;
import com.DrivingSchool.model.Termin;
import com.DrivingSchool.repository.TerminRepository;
import com.DrivingSchool.service.interfaces.CandidateService;
import com.DrivingSchool.service.interfaces.LicenceService;
import com.DrivingSchool.service.interfaces.TerminService;

@Service
public class TerminServiceImpl implements TerminService{

	@Autowired
	private TerminRepository terminRepository;
	@Autowired
	private LicenceService licenceService;
	@Autowired
	private CandidateService candidateService;
	
	@Override
	public boolean addTerminToInstructor(Termin termin, String instructorEmail, String categoryAndType) {
		LocalDate newTermin = termin.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		for(Licence l : licenceService.findInstructorLicences(instructorEmail)) {
			for(Termin t : terminRepository.findInstructorTermins(l.getId())) {
				LocalDate oldTermin = t.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if((oldTermin.getDayOfYear() == newTermin.getDayOfYear() && oldTermin.getYear() == newTermin.getYear()) && (termin.getStartTime().getTime() <= t.getStartTime().getTime() && termin.getEndTime().getTime() >= t.getEndTime().getTime())
						&& (termin.getStartTime().getTime() <= t.getStartTime().getTime() && termin.getEndTime().getTime() <= t.getEndTime().getTime())
						&& (termin.getStartTime().getTime() >= t.getStartTime().getTime() && termin.getEndTime().getTime() >= t.getEndTime().getTime())){
						return false;
				}
			}
		}
		if(licenceService.findLicenceByCategoryAndType(categoryAndType).getExpirationDate().before(termin.getEndTime())) {
			return false;
		}
		termin.setLicence(licenceService.findLicenceByCategoryAndType(categoryAndType));
		terminRepository.save(termin);
		return true;
	}

	@Override
	public List<Termin> getAllInstructorTermins(String instructorEmail) {
		List<Termin> termins = new ArrayList<Termin>();
		for(Licence l : licenceService.findInstructorLicences(instructorEmail)) {
			for(Termin t: terminRepository.findInstructorTermins(l.getId())) {
				if(t.getStartTime().after(new Date())) {
					termins.add(t);
				}
			}
		}
		return termins;
	}

	@Override
	public Set<String> getAllInstructorTerminDates(String instructorEmail) {
		Set<String> dates = new LinkedHashSet<String>();
		List<Date> d = new ArrayList<Date>();
		for(Licence l : licenceService.findInstructorLicences(instructorEmail)) {
			for(Termin t: terminRepository.findInstructorTermins(l.getId())) {
				if(t.getStartTime().after(new Date())) {
					d.add(t.getStartTime());
				}
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
		return dates;
	}

	@Override
	public List<Termin> getAllInstructorTerminTimes(String instructorEmail, String date) {
		List<Termin> termins = new ArrayList<Termin>();
		for(Licence l : licenceService.findInstructorLicences(instructorEmail)) {
			for(Termin termin : terminRepository.findInstructorTermins(l.getId())) {
				if(termin.getStartTime().after(new Date())) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
					String strDate = dateFormat.format(termin.getStartTime());  
					if(strDate.split(" ")[0].equals(date)) {
						termins.add(termin);
					}
				}
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
		for(Licence l : licenceService.findInstructorLicences(candidateService.findCandidateByEmail(candidateEmail).getInstructor().getEmail())) {
			for(Termin t: terminRepository.findInstructorTermins(l.getId())) {
				if(t.getStartTime().after(new Date())) {
					if(t.getLicence().getCategory().getCategoryType().equals(candidateService.findCandidateByEmail(candidateEmail).getCategory())
							&& t.getLicence().getLicenceType().equals(getLicenceType(candidateEmail))) {
						System.out.println("dasdasdasdassd");
						d.add(t.getStartTime());
					}
				}
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
		return dates;
	}
	
	private TestType getLicenceType(String candidateEmail) {
		Termin latestClass = getLatestCandidateClass(candidateEmail);
		if(latestClass == null) {
			return TestType.THEORETICAL;
		}else if(latestClass.getNumberOfClass() < latestClass.getLicence().getCategory().getNumberOfClasses()) {
			return latestClass.getLicence().getLicenceType();
		}else {
			if(latestClass.getLicence().getLicenceType().equals(TestType.THEORETICAL)) {
				return TestType.PRACTICAL;
			}else {
				return null; //nema vise sve casove zavrsiooo
			}
		}
	}

	private Termin getLatestCandidateClass(String candidateEmail) {
		List<Integer> ids = terminRepository.findCandidateClassesIds(candidateEmail);
		List<Termin> classes = new ArrayList<Termin>();
		for(Integer i : ids) {
			classes.add(terminRepository.findCandidateClasses(i));
		}
		Collections.sort(classes, new Comparator<Termin>() {
			@Override
			public int compare(final Termin d1, final Termin d2) {return d1.getStartTime().compareTo(d2.getStartTime());}});
		if(classes.size() != 0) {
			return classes.get(classes.size() - 1);
		}else {
			return null;
		}
	}
	
	@Override
	public List<Termin> getAllCandidatePossibleTerminForDate(String candidateEmail, String date) {
		List<Termin> termins = new ArrayList<Termin>();
		for(Licence l : licenceService.findInstructorLicences(candidateService.findCandidateByEmail(candidateEmail).getInstructor().getEmail())) {
			for(Termin t : terminRepository.findInstructorTermins(l.getId())) {
				if(t.getLicence().getCategory().getCategoryType().equals(candidateService.findCandidateByEmail(candidateEmail).getCategory())
						&& t.getLicence().getLicenceType().equals(getLicenceType(candidateEmail)) && t.getStartTime().after(new Date())) {
				
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
					String strDate = dateFormat.format(t.getStartTime());  
					if(strDate.split(" ")[0].equals(date)) {
						termins.add(t);
					}
				}
			}
		}
		Collections.sort(termins, new Comparator<Termin>() {
			@Override
			public int compare(final Termin d1, final Termin d2) {return d1.getStartTime().compareTo(d2.getStartTime());}});
		return termins;
	}

	@Override
	public boolean addClientToTermin(String clientEmail, Integer terminId) {
		Termin termin = terminRepository.findTerminById(terminId);
		Candidate candidate = candidateService.findCandidateByEmail(clientEmail);
		termin.getCandidate().add(candidate);
		terminRepository.save(termin);
		return true;
	}

}
