package com.DrivingSchool.service.interfaces;

import java.util.List;

import com.DrivingSchool.model.Licence;

public interface LicenceService {
	public List<Licence> getAll(String email);
	public boolean AddLicenceToInstructor(String email, Licence licence, String category);
}
