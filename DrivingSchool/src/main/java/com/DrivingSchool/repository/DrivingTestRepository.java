package com.DrivingSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.DrivingTest;

import javax.transaction.Transactional;

@Repository
public interface DrivingTestRepository extends JpaRepository<DrivingTest, Integer>{
    
	 @Modifying
	 @Transactional
	 @Query(value = "UPDATE DRIVING_TEST SET DELETED = true WHERE ID = ?1", nativeQuery = true)
	 public void deleteTest(int testId);
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE DRIVING_TEST SET CANDIDATE_ID = ?1 WHERE ID = ?2", nativeQuery = true)
    public void scheduleTest(String candidateEmail, int testId);

}
