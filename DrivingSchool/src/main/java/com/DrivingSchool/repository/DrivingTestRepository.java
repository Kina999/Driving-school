package com.DrivingSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.DrivingTest;

@Repository
public interface DrivingTestRepository extends JpaRepository<DrivingTest, Integer>{

}
