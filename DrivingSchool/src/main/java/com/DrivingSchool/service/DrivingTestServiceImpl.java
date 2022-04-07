package com.DrivingSchool.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.DrivingSchool.model.DrivingTest;
import com.DrivingSchool.repository.DrivingTestRepository;
import com.DrivingSchool.service.interfaces.CandidateService;
import com.DrivingSchool.service.interfaces.CategoryService;
import com.DrivingSchool.service.interfaces.DrivingTestService;
import com.DrivingSchool.service.interfaces.LicenceService;
import com.DrivingSchool.service.interfaces.TerminService;

@Service
public class DrivingTestServiceImpl implements DrivingTestService{

	@Autowired
	private LicenceService licenceService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private DrivingTestRepository drivingTestRepository;
	@Autowired
	private TerminService terminService;
	
	@Override
	public boolean addTest(DrivingTest drivingTest, String categoryAndType) {
		drivingTest.setLicence(licenceService.findLicenceByCategoryAndType(categoryAndType));
		drivingTestRepository.save(drivingTest);
		return true;
	}

	@Override
	public Set<String> getAllDrivingTestDates() {
		List<Date> dates = new ArrayList<Date>();
		for(DrivingTest drivingTest : drivingTestRepository.findAll()) {
			if(!drivingTest.isDeleted()) {
				dates.add(drivingTest.getTestDateTime());
			}
		}
		return sortDates(dates);
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

	@Override
	public List<DrivingTest> getTestsForDate(String date) {
		List<DrivingTest> termins = new ArrayList<DrivingTest>();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		for(DrivingTest drivingTest : drivingTestRepository.findAll()) {
			if(!drivingTest.isDeleted()) {
				String strDate = dateFormat.format(drivingTest.getTestDateTime());
				if(strDate.split(" ")[0].equals(date)) {termins.add(drivingTest);}
			}
		}
		return sortTimes(termins);
	}

	@Override
	public void scheduleTest(String candidateEmail, int id) {
		drivingTestRepository.scheduleTest(candidateEmail, id);
	}

	private List<DrivingTest> sortTimes(List<DrivingTest> termins){
		Collections.sort(termins, new Comparator<DrivingTest>() {
			@Override
			public int compare(final DrivingTest d1, final DrivingTest d2) {return d1.getTestDateTime().compareTo(d2.getTestDateTime());}});
		return termins;
	}

	@Override
	public Set<String> getCandidateDrivingTestDates(String candidateEmail) {
		List<Date> dates = new ArrayList<Date>();
		Candidate candidate = candidateService.findCandidateByEmail(candidateEmail);
		if(candidate.getClassType().equals(TestType.PRACTICAL) && !candidate.isTheoreticalDone()){
			for(DrivingTest drivingTest : drivingTestRepository.findAll()) {
				if(drivingTest.getLicence().getCategory().getCategoryType().equals(candidate.getCategory())
						&& drivingTest.getLicence().getLicenceType().equals(TestType.THEORETICAL) 
						&& drivingTest.getTestDateTime().after(new Date())
						&& terminService.getLatestCandidateTheoreticalClass(candidateEmail).getEndTime().before(new Date())
						&& !alreadyReserved(candidateEmail)
						&& !drivingTest.isDeleted()
						&& !candidate.isTheoreticalDone()) {
					dates.add(drivingTest.getTestDateTime());
				}
			}
		}else if(terminService.getLatestCandidatePracticalClass(candidateEmail).getStartTime().before(new Date())
				&& candidate.getNumberOfClasses() == categoryService.getCategory(candidate.getCategory()).getNumberOfClasses()
				&& candidate.isTheoreticalDone()) {
			for(DrivingTest drivingTest : drivingTestRepository.findAll()) {
				if(drivingTest.getLicence().getCategory().getCategoryType().equals(candidate.getCategory())
						&& drivingTest.getLicence().getLicenceType().equals(TestType.PRACTICAL) 
						&& drivingTest.getTestDateTime().after(new Date())
						&& terminService.getLatestCandidatePracticalClass(candidateEmail).getEndTime().before(new Date())
						&& !alreadyReserved(candidateEmail)
						&& !drivingTest.isDeleted()
						&& !candidate.isPracticalDone()) {
					dates.add(drivingTest.getTestDateTime());
				}
			}
		}
		return sortDates(dates);
	}

	private boolean alreadyReserved(String candidateEmail) {
		for(DrivingTest drivingTest : drivingTestRepository.findAll()) {
			if(drivingTest.getCandidate() != null) {
				if(drivingTest.getCandidate().getEmail().equals(candidateEmail) && drivingTest.getTestDateTime().after(new Date())) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public List<DrivingTest> getCandidateTestsForDate(String candidateEmail, String date) {
		List<DrivingTest> tests = new ArrayList<DrivingTest>();
		Candidate candidate = candidateService.findCandidateByEmail(candidateEmail);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		if(candidate.getClassType().equals(TestType.PRACTICAL) && !candidate.isTheoreticalDone()){
			for(DrivingTest drivingTest : drivingTestRepository.findAll()) {
				String strDate = dateFormat.format(drivingTest.getTestDateTime());
				if(drivingTest.getLicence().getCategory().getCategoryType().equals(candidate.getCategory())
						&& drivingTest.getLicence().getLicenceType().equals(TestType.THEORETICAL) 
						&& drivingTest.getTestDateTime().after(new Date())
						&& !alreadyReserved(candidateEmail)
						&& !drivingTest.isDeleted()
						&& !candidate.isTheoreticalDone()
						&& strDate.split(" ")[0].equals(date)) {
					tests.add(drivingTest);
				}
			}
		}else if(terminService.getLatestCandidatePracticalClass(candidateEmail).getStartTime().before(new Date())
				&& candidate.getNumberOfClasses() == categoryService.getCategory(candidate.getCategory()).getNumberOfClasses()) {
			for(DrivingTest drivingTest : drivingTestRepository.findAll()) {
				String strDate = dateFormat.format(drivingTest.getTestDateTime());
				if(drivingTest.getLicence().getCategory().getCategoryType().equals(candidate.getCategory())
						&& drivingTest.getLicence().getLicenceType().equals(TestType.PRACTICAL) 
						&& drivingTest.getTestDateTime().after(new Date())
						&& !alreadyReserved(candidateEmail)
						&& !drivingTest.isDeleted()
						&& !candidate.isPracticalDone()
						&& strDate.split(" ")[0].equals(date)) {
					tests.add(drivingTest);
				}
			}
		}
		return sortTimes(tests);
	}

	@Override
	public boolean deleteTest(int id) {
		drivingTestRepository.deleteTest(id);
		return true;
	}

	@Override
	public boolean passTest(int id) {
		DrivingTest test = drivingTestRepository.getById(id);
		Candidate candidate = candidateService.findCandidateByEmail(test.getCandidate().getEmail());
		drivingTestRepository.deleteTest(id);
		if(test.getLicence().getLicenceType().equals(TestType.THEORETICAL)) {candidate.setTheoreticalDone(true);}
		else {candidate.setPracticalDone(true);}
		candidateService.saveCandidate(candidate);
		return true;
	}

	@Override
	public boolean failTest(int id) {
		drivingTestRepository.deleteTest(id);
		return false;
	}
	
}
