package com.DrivingSchool.service.interfaces;

import java.util.List;

import com.DrivingSchool.model.Licence;

public interface LicenceService {
	public List<Licence> getAll(String email);
	public Licence findLicenceByCategoryAndType(String categoryAndType);
	public boolean addLicenceToInstructor(String email, Licence licence, String category);
}
