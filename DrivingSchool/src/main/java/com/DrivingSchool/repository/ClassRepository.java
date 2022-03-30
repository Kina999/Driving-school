package com.DrivingSchool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer>{
	@Query(value = "SELECT * FROM CLASS WHERE CANDIDATE_ID=?1", nativeQuery = true)
	public List<Class> findCandidateClasses(String candidateEmail);
	
}
