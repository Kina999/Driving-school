package com.DrivingSchool.service.interfaces;

import com.DrivingSchool.model.Class;

public interface ClassService {

	public Class getLatestCandidateClass(String candidateEmail);
	
}
