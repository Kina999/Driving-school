package com.DrivingSchool.service.interfaces;

import com.DrivingSchool.model.Worker;

public interface WorkerService {
	public Worker findWorkerByEmail(String workerEmail);
	public Worker checkIfWorkerExists(String email, String password);
}
