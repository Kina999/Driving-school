package com.DrivingSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.model.Worker;
import com.DrivingSchool.repository.WorkerRepository;
import com.DrivingSchool.service.interfaces.WorkerService;

@Service
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerRepository workerRepository;
	
	@Override
	public Worker checkIfWorkerExists(String email, String password) {
		return workerRepository.findWorkerByEmailAndPassword(email, password);
	}

	@Override
	public Worker findWorkerByEmail(String workerEmail) {
		return workerRepository.findWorkerByEmail(workerEmail);
	}

}
