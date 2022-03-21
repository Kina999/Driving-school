package com.DrivingSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, String>{
	
	@Query(value = "SELECT * FROM WORKER WHERE EMAIL=?1 AND PASSWORD=?2", nativeQuery = true)
	public Worker findWorkerByEmailAndPassword(String email, String password);
	public Worker findWorkerByEmail(String WorkerEmail);
}
