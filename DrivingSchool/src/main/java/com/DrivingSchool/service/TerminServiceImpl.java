package com.DrivingSchool.service;

import static java.lang.Math.abs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.DrivingSchool.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.dto.CandidateCancelingDTO;
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
	@Autowired
	private CategoryService categoryService;

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
	public Set<String> getAllCandidateTerminDates(String candidateEmail) {
		List<Date> d = new ArrayList<Date>();
		for(Termin t: terminRepository.findAll()) {
			if(terminIsValid(t) && reservedByCandidate(t, candidateEmail)) {
				d.add(t.getStartTime());
			}
		}
		return sortDates(d);
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
		for(Licence l : licenceService.findInstructorLicences(candidate.getInstructor().getEmail())) {
			for(Termin t: terminRepository.findInstructorTermins(l.getId())) {
				if(terminIsValid(t) && !alreadyReserved(t, candidateEmail) 
						&& t.getLicence().getCategory().getCategoryType().equals(candidate.getCategory())
						&& t.getLicence().getLicenceType().equals(candidate.getClassType())) {
						if(candidate.getClassType().equals(TestType.PRACTICAL) && candidate.isTheoreticalDone() && candidate.getNumberOfClasses() < categoryService.getCategory(candidate.getCategory()).getNumberOfClasses()) {
							if(t.getStartTime().after(getLatestCandidateTheoreticalClass(candidateEmail).getEndTime())
									&& getLatestCandidateTheoreticalClass(candidateEmail).getEndTime().before(new Date())) {d.add(t.getStartTime());}
						}else if(candidate.getClassType().equals(TestType.THEORETICAL)){d.add(t.getStartTime());}
				}
			}
		}
		return sortDates(d);
	}

	@Override
	public List<Termin> getAllCandidateTerminForDate(String candidateEmail, String date) {
		List<Termin> termins = new ArrayList<Termin>();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		for(Termin t : terminRepository.findAll()) {
			if(terminIsValid(t) && reservedByCandidate(t, candidateEmail) && !termins.contains(t)) {
				String strDate = dateFormat.format(t.getStartTime());
				if(strDate.split(" ")[0].equals(date)) {termins.add(t);}
			}
		}
		return sortTimes(termins);
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
					if(candidate.getClassType().equals(TestType.PRACTICAL) && candidate.isTheoreticalDone()) {
						if(t.getStartTime().after(getLatestCandidateTheoreticalClass(candidateEmail).getEndTime()) 
								&& getLatestCandidateTheoreticalClass(candidateEmail).getEndTime().before(new Date())) {  
							String strDate = dateFormat.format(t.getStartTime());  
							if(strDate.split(" ")[0].equals(date)) {termins.add(t);}
						}
					}else if(candidate.getClassType().equals(TestType.THEORETICAL)){
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
	public boolean cancelTermin(int id, String candidateEmail) {
		Termin canceledTermin = terminRepository.findTerminById(id);
		canceledTermin.setCancelationDate(new Date());
		canceledTermin.setClientCanceled(candidateEmail);
		Set<Candidate> candidates = new HashSet<>();
		for(Candidate c : canceledTermin.getCandidate()){
			if(!c.getEmail().equals(candidateEmail)) candidates.add(c);
		}
		Termin newTermin = new Termin(canceledTermin.getStartTime(), canceledTermin.getEndTime(), false, null, null, canceledTermin.getLicence(), candidates);
		terminRepository.save(newTermin);
		terminRepository.save(canceledTermin);
		candidateService.decreaseClassNumber(candidateEmail);
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
		return !t.isDeleted() && t.getStartTime().after(new Date()) && t.getCancelationDate() == null;
	}

	private boolean alreadyReserved(Termin termin, String candidateEmail) {
		if(termin.getLicence().getLicenceType().equals(TestType.THEORETICAL)) {
			for(Candidate candidate : termin.getCandidate()) {if(candidate.getEmail().equals(candidateEmail)) {return true;}}
			return false;
		}else {
			if(termin.getCandidate().size() != 0) {return true;}
			return false;
		}
	}

	private boolean reservedByCandidate(Termin termin, String candidateEmail) {
		for(Candidate candidate : termin.getCandidate()) {if(candidate.getEmail().equals(candidateEmail)) {return true;}}
		return false;
	}

	@Override
	public Termin getLatestCandidateTheoreticalClass(String candidateEmail) {
		List<Integer> ids = terminRepository.findCandidateClassesIds(candidateEmail);
		List<Termin> classes = new ArrayList<Termin>();
		for(Integer i : ids) {
			Termin termin = terminRepository.findCandidateClasses(i);
			if(termin.getLicence().getLicenceType().equals(TestType.THEORETICAL) && !termin.isDeleted() && termin.getCancelationDate() == null){
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
	
	@Override
	public Termin getLatestCandidatePracticalClass(String candidateEmail) {
		List<Integer> ids = terminRepository.findCandidateClassesIds(candidateEmail);
		List<Termin> classes = new ArrayList<Termin>();
		for(Integer i : ids) {
			Termin termin = terminRepository.findCandidateClasses(i);
			if(termin.getLicence().getLicenceType().equals(TestType.PRACTICAL) && !termin.isDeleted() && termin.getCancelationDate() == null){
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

	@Override
	public List<Termin> getAllCandidateTermins(String candidateEmail) {
		List<Integer> ids = terminRepository.findCandidateClassesIds(candidateEmail);
		List<Termin> termins = new ArrayList<Termin>();
		for(Integer i : ids) {
			termins.add(terminRepository.findCandidateClasses(i));
		}
		return termins;
	}

	@Override
	public CandidateCancelingDTO getCandidateCanceling(String candidateEmail) {
		CandidateCancelingDTO candidateCancelingData = new CandidateCancelingDTO();
		List<Termin> canceledTermins = terminRepository.getCandidateCanceledTermins(candidateEmail);
		candidateCancelingData.numberOfCancelations = canceledTermins.size();
		LocalDate terminDate = sortTimes(canceledTermins).get(0).getCancelationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		candidateCancelingData.numberOfDays = abs(Period.between(terminDate, now).getDays());
		if(abs(Period.between(terminDate, now).getDays()) == 0) {
			candidateCancelingData.numberOfDays = 1;	
		}
		return candidateCancelingData;
	}

	@Override
	public List<Termin> getCandidateCanceledTermins(String candidateEmail) {
		return terminRepository.getCandidateCanceledTermins(candidateEmail);
	}
}
