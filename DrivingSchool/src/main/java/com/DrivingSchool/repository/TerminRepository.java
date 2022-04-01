package com.DrivingSchool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.Termin;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Integer>{
	
	public Termin findTerminById(Integer terminId);
	
	@Query(value = "SELECT * FROM TERMIN WHERE LICENCE_ID=?1", nativeQuery = true)
	public List<Termin> findInstructorTermins(Integer licanceId);
	
	@Query(value = "SELECT class_id FROM class_candidate WHERE CANDIDATE_ID=?1", nativeQuery = true)
	public List<Integer> findCandidateClassesIds(String candidateEmail);
	
	@Query(value = "SELECT * FROM TERMIN WHERE ID=?1", nativeQuery = true)
	public Termin findCandidateClasses(Integer classId);
	
}
