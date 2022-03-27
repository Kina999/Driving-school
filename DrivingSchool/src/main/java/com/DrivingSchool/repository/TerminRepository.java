package com.DrivingSchool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.Termin;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Integer>{
	
	@Query(value = "SELECT * FROM TERMIN WHERE INSTRUCTOR_ID=?1", nativeQuery = true)
	public List<Termin> findInstructorTermins(String instructorEmail);
	
}
