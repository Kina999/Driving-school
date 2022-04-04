package com.DrivingSchool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DrivingSchool.model.Instructor;
import com.DrivingSchool.repository.InstructorRepository;
import com.DrivingSchool.service.interfaces.CandidateService;
import com.DrivingSchool.service.interfaces.InstructorService;
import com.DrivingSchool.service.interfaces.WorkerService;

@Service
public class InstructorServiceImpl implements InstructorService{

	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private CandidateService candidateService;
	
	@Override
	public boolean addNewInstructor(Instructor instructor) {
		if(workerService.findWorkerByEmail(instructor.getEmail()) == null && candidateService.findCandidateByEmail(instructor.getEmail()) == null) {
			instructorRepository.save(instructor);
			return true;
		}
		return false;
	}

	@Override
	public List<Instructor> getAll() {
		return instructorRepository.findAll();
	}

	@Override
	public boolean editInstructorProfile(Instructor instructor) {
		instructorRepository.updateInstructor(instructor.getEmail(), instructor.getPassword(), instructor.getName(), instructor.getLastName(), instructor.getPhoneNumber());
		return true;
	}

	@Override
	public Instructor getInstructorByMail(String email) {
		return instructorRepository.findInstructorByEmail(email);
	}

	@Override
	public boolean leaveGrade(String instructorEmail, String grade) {
		try {
			double oldGrade = instructorRepository.findInstructorByEmail(instructorEmail).getGrade();
			if(oldGrade == 0) {
				oldGrade = Integer.parseInt(grade);
			}
			instructorRepository.leaveGrade(instructorEmail, (oldGrade + Integer.parseInt(grade)) / 2);
		}catch(Exception ex) {
			return false;
		}
		return true;
	}

}
