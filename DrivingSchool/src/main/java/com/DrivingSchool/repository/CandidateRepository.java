package com.DrivingSchool.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String>{
	
	public Candidate findCandidateByEmail(String candidateEmail);
	
	@Query(value = "SELECT * FROM CANDIDATE WHERE EMAIL=?1 AND PASSWORD=?2", nativeQuery = true)
	public Candidate findCanidateByEmailAndPassword(String email, String password);
	
	@Query(value = "SELECT * FROM CANDIDATE WHERE INSTRUCTOR_ID=?1", nativeQuery = true)
	public List<Candidate> findInstructorCandidates(String email);
	
	@Modifying
	@Transactional
	@Query(value = "update candidate set number_of_classes = 1 where email = ?1", nativeQuery = true)
	public void resetClassNumber(String candidateEmail);
	
	@Modifying
	@Transactional
	@Query(value = "update candidate set blocked = true where email = ?1", nativeQuery = true)
	public void blockUser(String candidateEmail);
	
	@Modifying
	@Transactional
	@Query(value = "update candidate set blocked = false where email = ?1", nativeQuery = true)
	public void unblockUser(String candidateEmail);
	
	@Modifying
	@Transactional
	@Query(value = "update candidate set class_type = 1 where email = ?1", nativeQuery = true)
	public void setClassType(String candidateEmail);
	
	@Modifying
	@Transactional
	@Query(value = "update candidate set number_of_classes = ?2 where email = ?1", nativeQuery = true)
	public void incrementClassNumber(String candidateEmail, int classNumber);
	
	
	@Modifying
	@Transactional
	@Query(value = "update candidate set password = ?2, name = ?3, last_name = ?4, phone_number = ?5 where email = ?1", nativeQuery = true)
	public void updateClient(String email, String password, String name, String lastName, String phoneNumber);	
	
	@Modifying
	@Transactional
	@Query(value = "update candidate set instructor_id = ?2, category = ?3 where email = ?1", nativeQuery = true)
	public void setCandidateInstructor(String candidateEmail, String instructorEmail, String category);	
}
