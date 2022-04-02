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
		for(Licence l : licenceService.findInstructorLicences(instructorEmail)) {
			for(Termin t : terminRepository.findInstructorTermins(l.getId())) {if(terminOverlapsWithAnother(termin, t)){return false;}}
		}
		if(licenceService.findLicenceByCategoryAndType(categoryAndType).getExpirationDate().before(termin.getEndTime())) {return false;}
		termin.setLicence(licenceService.findLicenceByCategoryAndType(categoryAndType));
		terminRepository.save(termin);
		return true;
	}
	
	@Override
	public List<Termin> getAllInstructorTermins(String instructorEmail) {
		List<Termin> termins = new ArrayList<Termin>();
		for(Licence l : licenceService.findInstructorLicences(instructorEmail)) {
			for(Termin t: terminRepository.findInstructorTermins(l.getId())) {if(terminIsValid(t)) {termins.add(t);}}
		}
		return termins;
	}
	
	@Override
	public Set<String> getAllInstructorTerminDates(String instructorEmail) {
		List<Date> d = new ArrayList<Date>();
		for(Licence l : licenceService.findInstructorLicences(instructorEmail)) {
			for(Termin t: terminRepository.findInstructorTermins(l.getId())) {if(terminIsValid(t)) {d.add(t.getStartTime());}}
		}
		return sortDates(d);
	}
	
	@Override
	public List<Termin> getAllInstructorTerminTimes(String instructorEmail, String date) {
		List<Termin> termins = new ArrayList<Termin>();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
		for(Licence l : licenceService.findInstructorLicences(instructorEmail)) {
			for(Termin termin : terminRepository.findInstructorTermins(l.getId())) {
				if(terminIsValid(termin)) { 
					String strDate = dateFormat.format(termin.getStartTime());  
					if(strDate.split(" ")[0].equals(date)) {termins.add(termin);}
				}
			}
		}
		return sortTimes(termins);
	}

	@Override
	public Set<String> getAllCandidatePossibleTerminDates(String candidateEmail) {
		List<Date> d = new ArrayList<Date>();
		Candidate candidate = candidateService.findCandidateByEmail(candidateEmail);
		for(Licence l : licenceService.findInstructorLicences(candidateService.findCandidateByEmail(candidateEmail).getInstructor().getEmail())) {
			for(Termin t: terminRepository.findInstructorTermins(l.getId())) {
				if(terminIsValid(t) && !alreadyReserved(t, candidateEmail) 
						&& t.getLicence().getCategory().getCategoryType().equals(candidate.getCategory())
						&& t.getLicence().getLicenceType().equals(candidate.getClassType())) {
						if(candidate.getClassType().equals(TestType.PRACTICAL)) {
							if(t.getStartTime().after(getLatestCandidateTheoreticalClass(candidateEmail).getEndTime())
									&& getLatestCandidateTheoreticalClass(candidateEmail).getEndTime().before(new Date())) {d.add(t.getStartTime());}
						}else {d.add(t.getStartTime());}
				}
			}
		}
		return sortDates(d);
	}
	
	
	
	@Override
	public List<Termin> getAllCandidatePossibleTerminForDate(String candidateEmail, String date) {
		List<Termin> termins = new ArrayList<Termin>();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Candidate candidate = candidateService.findCandidateByEmail(candidateEmail);
		for(Licence l : licenceService.findInstructorLicences(candidateService.findCandidateByEmail(candidateEmail).getInstructor().getEmail())) {
			for(Termin t : terminRepository.findInstructorTermins(l.getId())) {
				if(terminIsValid(t) && t.getLicence().getCategory().getCategoryType().equals(candidate.getCategory())
						&& t.getLicence().getLicenceType().equals(candidate.getClassType()) && !alreadyReserved(t, candidateEmail)) {
					if(candidate.getClassType().equals(TestType.PRACTICAL)) {
						if(t.getStartTime().after(getLatestCandidateTheoreticalClass(candidateEmail).getEndTime()) 
								&& getLatestCandidateTheoreticalClass(candidateEmail).getEndTime().before(new Date())) {  
							String strDate = dateFormat.format(t.getStartTime());  
							if(strDate.split(" ")[0].equals(date)) {termins.add(t);}
						}
					}else {
						String strDate = dateFormat.format(t.getStartTime());  
						if(strDate.split(" ")[0].equals(date)) {termins.add(t);}
					}
				}
			}
		}
		return sortTimes(termins);
	}
	
	@Override
	public boolean addClientToTermin(String clientEmail, Integer terminId) {
		Termin termin = terminRepository.findTerminById(terminId);
		Candidate candidate = candidateService.findCandidateByEmail(clientEmail);
		candidateService.increaseClassNumber(clientEmail);
		termin.getCandidate().add(candidate);
		terminRepository.save(termin);
		return true;
	}

	@Override
	public boolean deleteTermin(Integer id) {
		if(terminRepository.findTerminById(id) != null) {
			terminRepository.deleteTermin(id);
			return true;
		}
		return false;
	}
	
	private boolean terminOverlapsWithAnother(Termin termin, Termin anotherTermin) {
		LocalDate newTermin = termin.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate oldTermin = anotherTermin.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return (oldTermin.getDayOfYear() == newTermin.getDayOfYear() && oldTermin.getYear() == newTermin.getYear()) 
				&& (termin.getStartTime().getTime() <= anotherTermin.getStartTime().getTime() 
				&& termin.getEndTime().getTime() >= anotherTermin.getEndTime().getTime())
				&& (termin.getStartTime().getTime() <= anotherTermin.getStartTime().getTime() && termin.getEndTime().getTime() <= anotherTermin.getEndTime().getTime())
				&& (termin.getStartTime().getTime() >= anotherTermin.getStartTime().getTime() && termin.getEndTime().getTime() >= anotherTermin.getEndTime().getTime());
	}
	
	private boolean terminIsValid(Termin t) {
		return !t.isDeleted() && t.getStartTime().after(new Date());
	}

	private boolean alreadyReserved(Termin termin, String candidateEmail) {
		if(termin.getLicence().getLicenceType().equals(TestType.THEORETICAL)) {
			for(Candidate candidate : termin.getCandidate()) {
				if(candidate.getEmail().equals(candidateEmail)) {
					return true;
				}
			}
			return false;
		}else {
			if(termin.getCandidate().size() != 0) {
				return true;
			}
			return false;
		}
	}
	
	private Termin getLatestCandidateTheoreticalClass(String candidateEmail) {
		List<Integer> ids = terminRepository.findCandidateClassesIds(candidateEmail);
		List<Termin> classes = new ArrayList<Termin>();
		for(Integer i : ids) {
			Termin termin = terminRepository.findCandidateClasses(i);
			if(termin.getLicence().getLicenceType().equals(TestType.THEORETICAL)){
				classes.add(termin);
			}
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
	
	private Set<String> sortDates(List<Date> d){
		Set<String> dates = new LinkedHashSet<String>();
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
	
	private List<Termin> sortTimes(List<Termin> termins){
		Collections.sort(termins, new Comparator<Termin>() {
			@Override
			public int compare(final Termin d1, final Termin d2) {return d1.getStartTime().compareTo(d2.getStartTime());}});
		return termins;
	}
}
