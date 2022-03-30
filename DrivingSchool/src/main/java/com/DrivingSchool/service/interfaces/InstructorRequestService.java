package com.DrivingSchool.service.interfaces;

import java.util.List;

import com.DrivingSchool.model.Candidate;
import com.DrivingSchool.model.InstructorRequest;

public interface InstructorRequestService {
	public InstructorRequest getInstructorRequest(String candidateEmail);
	public List<Candidate> getInstructorRequests(String instructorEmail);
	public boolean refuseRequest(String instructorEmail, String candidateEmail);
	public String approveRequest(String instructorEmail, String candidateEmail);
	public boolean addInstructorRequest(String instructorEmail, String candidateEmail, String category);
}
