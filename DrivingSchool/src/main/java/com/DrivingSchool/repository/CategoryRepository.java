package com.DrivingSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DrivingSchool.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Query(value = "SELECT * FROM CATEGORY WHERE CATEGORY_TYPE=?1", nativeQuery = true)
	public Category findCategory(String category);
	
}
