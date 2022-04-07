package com.DrivingSchool.service.interfaces;

import java.util.List;
import java.util.Set;

import com.DrivingSchool.model.DrivingTest;

public interface DrivingTestService {
	public boolean passTest(int id);
	public boolean failTest(int id);
	public boolean deleteTest(int id);
	public Set<String> getAllDrivingTestDates();
	public List<DrivingTest> getTestsForDate(String date);
	public void scheduleTest(String candidateEmail, int id);
	public Set<String> getCandidateDrivingTestDates(String candidateEmail);
	public boolean addTest(DrivingTest drivingTest, String categoryAndType);
	public DrivingTest getCandidateReservedDrivingTest(String candidateEmail);
	public List<DrivingTest> getCandidateTestsForDate(String candidateEmail, String date);
	
}
