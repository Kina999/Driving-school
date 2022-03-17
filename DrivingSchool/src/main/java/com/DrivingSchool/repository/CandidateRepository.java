package com.DrivingSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String>{
	
	public Candidate findCandidateByEmail(String candidateEmail);
	
}
