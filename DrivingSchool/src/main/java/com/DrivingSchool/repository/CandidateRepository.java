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
	@Query(value = "UPDATE CANDIDATE SET NUMBER_OF_CLASSES = 0 WHERE EMAIL = ?1", nativeQuery = true)
	public void resetClassNumber(String candidateEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE CANDIDATE SET BLOCKED = true WHERE EMAIL = ?1", nativeQuery = true)
	public void blockUser(String candidateEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE CANDIDATE SET BLOCKED = false WHERE EMAIL = ?1", nativeQuery = true)
	public void unblockUser(String candidateEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE CANDIDATE SET CLASS_TYPE = 1 WHERE EMAIL = ?1", nativeQuery = true)
	public void setClassType(String candidateEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE CANDIDATE SET CLASS_TYPE = 0 WHERE EMAIL = ?1", nativeQuery = true)
	public void setOldClassType(String candidateEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE CANDIDATE SET NUMBER_OF_CLASSES = ?2 WHERE EMAIL = ?1", nativeQuery = true)
	public void incrementClassNumber(String candidateEmail, int classNumber);
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE CANDIDATE SET PASSWORD = ?2, NAME = ?3, LAST_NAME = ?4, PHONE_NUMBER = ?5 WHERE EMAIL = ?1", nativeQuery = true)
	public void updateClient(String email, String password, String name, String lastName, String phoneNumber);	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE CANDIDATE SET INSTRUCTOR_ID = ?2, CATEGORY = ?3 WHERE EMAIL = ?1", nativeQuery = true)
	public void setCandidateInstructor(String candidateEmail, String instructorEmail, String category);	
}
