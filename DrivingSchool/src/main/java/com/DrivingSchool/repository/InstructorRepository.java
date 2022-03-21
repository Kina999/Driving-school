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
	@Query(value = "update worker set password = ?2, name = ?3, last_name = ?4, phone_number = ?5 where email = ?1", nativeQuery = true)
	public void updateInstructor(String email, String password, String name, String lastName, String phoneNumber);	
}
