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
	 @Query(value = "update driving_test set deleted = true where id = ?1", nativeQuery = true)
	 public void deleteTest(int testId);
	
	@Modifying
    @Transactional
    @Query(value = "update driving_test set candidate_id = ?1 where id = ?2", nativeQuery = true)
    public void scheduleTest(String candidateEmail, int testId);

}
