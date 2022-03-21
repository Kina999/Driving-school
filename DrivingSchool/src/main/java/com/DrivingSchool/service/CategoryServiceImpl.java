package com.DrivingSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.model.Category;
import com.DrivingSchool.repository.CategoryRepository;
import com.DrivingSchool.service.interfaces.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category getCategory(String category) {
		return categoryRepository.findCategory(category); 
	}

}
