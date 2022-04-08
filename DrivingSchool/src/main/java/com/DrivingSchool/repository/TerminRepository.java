package com.DrivingSchool.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.Termin;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Integer>{
	
	public Termin findTerminById(Integer terminId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE TERMIN SET DELETED = TRUE WHERE ID = ?1", nativeQuery = true)
	public void deleteTermin(Integer terminId);	
	
	@Query(value = "SELECT * FROM TERMIN WHERE LICENCE_ID=?1", nativeQuery = true)
	public List<Termin> findInstructorTermins(Integer licenceId);
	
	@Query(value = "SELECT * FROM TERMIN WHERE CLIENT_CANCELED=?1", nativeQuery = true)
	public List<Termin> getCandidateCanceledTermins(String candidateEmail);
	
	@Query(value = "SELECT class_id FROM class_candidate WHERE CANDIDATE_ID=?1", nativeQuery = true)
	public List<Integer> findCandidateClassesIds(String candidateEmail);
	
	@Query(value = "SELECT * FROM TERMIN WHERE ID=?1", nativeQuery = true)
	public Termin findCandidateClasses(Integer classId);
	
}
