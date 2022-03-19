package com.DrivingSchool.service.interfaces;

import com.DrivingSchool.model.Worker;

public interface WorkerService {
	public Worker CheckIfWorkerExists(String email, String password);
}
