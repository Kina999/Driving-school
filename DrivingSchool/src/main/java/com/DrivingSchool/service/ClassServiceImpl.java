package com.DrivingSchool.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.model.Class;
import com.DrivingSchool.repository.ClassRepository;
import com.DrivingSchool.service.interfaces.ClassService;

@Service	
public class ClassServiceImpl implements ClassService{

	@Autowired
	private ClassRepository classRepository;
	
	@Override
	public Class getLatestCandidateClass(String candidateEmail) {
		List<Class> classes = classRepository.findCandidateClasses(candidateEmail);
		Collections.sort(classes, new Comparator<Class>() {
			@Override
			public int compare(final Class d1, final Class d2) {return d1.getClassDateTime().compareTo(d2.getClassDateTime());}});
		return classes.get(classes.size() - 1);
	}

}
