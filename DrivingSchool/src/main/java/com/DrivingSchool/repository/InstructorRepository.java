package com.DrivingSchool.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, String>{
	
	public Instructor findInstructorByEmail(String instructorEmail);

	@Modifying
	@Transactional
	@Query(value = "UPDATE WORKER SET GRADE = ?2 WHERE EMAIL = ?1", nativeQuery = true)
	public void leaveGrade(String instructorEmail, double grade);	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE WORKER SET PASSWORD = ?2, NAME = ?3, LAST_NAME = ?4, PHONE_NUMBER = ?5 WHERE EMAIL = ?1", nativeQuery = true)
	public void updateInstructor(String email, String password, String name, String lastName, String phoneNumber);
	
}
