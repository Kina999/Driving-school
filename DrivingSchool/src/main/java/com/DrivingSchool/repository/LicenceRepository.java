package com.DrivingSchool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.Licence;

@Repository
public interface LicenceRepository extends JpaRepository<Licence, Integer>{
	@Query(value = "SELECT * FROM LICENCE WHERE INSTRUCTOR_ID=?1 AND CATEGORY_ID = ?2 AND LICENCE_TYPE=?3", nativeQuery = true)
	public Licence findInstructorLicence(String email, Integer category, Integer licenceType);
	@Query(value = "SELECT * FROM LICENCE WHERE INSTRUCTOR_ID=?1", nativeQuery = true)
	public List<Licence> findInstructorLicences(String email);
	@Query(value = "SELECT * FROM LICENCE WHERE CATEGORY_ID=?1 AND LICENCE_TYPE=?2", nativeQuery = true)
	public Licence findLicenceByCategoryAndType(Integer categoryId, Integer licenceType);
	@Query(value = "SELECT * FROM LICENCE WHERE CATEGORY_ID=?1", nativeQuery = true)
	public List<Licence> findInstructorForCategory(Integer categoryId);
	
}
