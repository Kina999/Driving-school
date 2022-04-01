package com.DrivingSchool.service.interfaces;

import java.util.List;
import java.util.Set;

import com.DrivingSchool.model.Instructor;
import com.DrivingSchool.model.Licence;

public interface LicenceService {
	public List<Licence> getAll(String email);
	public List<Licence> findInstructorLicences(String instructorEmail);
	public Licence findLicenceByCategoryAndType(String categoryAndType);
	public Set<Instructor> getAllInstructorsWithCategory(String category);
	public boolean addLicenceToInstructor(String email, Licence licence, String category);
}
