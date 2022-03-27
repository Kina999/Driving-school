package com.DrivingSchool.service.interfaces;

import com.DrivingSchool.model.Termin;

public interface TerminService {
	public boolean addTerminToInstructor(Termin termin, String instructorEmail, String categoryAndType);
}
