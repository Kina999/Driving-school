package com.DrivingSchool.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.DrivingSchool.model.InstructorRequest;

public interface InstructorRequestRepository extends JpaRepository<InstructorRequest, Integer>{
	@Query(value = "SELECT * FROM INSTRUCTOR_REQUEST WHERE CANDIDATE_EMAIL=?1 AND INSTRUCTOR_EMAIL=?2", nativeQuery = true)
	public InstructorRequest findInstructorRequest(String candidateEmail, String instructorEmail);
	
	@Query(value = "SELECT * FROM INSTRUCTOR_REQUEST WHERE CANDIDATE_EMAIL=?1", nativeQuery = true)
	public InstructorRequest findInstructorRequest(String candidateEmail);
	
	@Query(value = "SELECT * FROM INSTRUCTOR_REQUEST WHERE INSTRUCTOR_EMAIL=?1 AND APPROVED = FALSE AND REFUSED = FALSE", nativeQuery = true)
	public List<InstructorRequest> findInstructorRequests(String instructorEmail);
	
	@Modifying
	@Transactional
	@Query(value = "update instructor_request set approved = true where instructor_email = ?1 and candidate_email = ?2", nativeQuery = true)
	public void approveRequest(String instructorEmail, String candidateEmail);	
	
	@Modifying
	@Transactional
	@Query(value = "update instructor_request set refused = true where instructor_email = ?1 and candidate_email = ?2", nativeQuery = true)
	public void refuseRequest(String instructorEmail, String candidateEmail);	
	
}
