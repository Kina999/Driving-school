package com.DrivingSchool.mapper;

import com.DrivingSchool.dto.InstructorDTO;
import com.DrivingSchool.enumClasses.WorkerType;
import com.DrivingSchool.model.Instructor;

public class InstructorMapper {
	public static Instructor InstructorDTOToInstructor(InstructorDTO dto) {
		Instructor instructor = new Instructor(dto.email, dto.password, dto.name, dto.lastName, null);
		instructor.setWorkerType(WorkerType.INSTRUCTOR);
		instructor.setGrade(0);
		instructor.setPhoneNumber(dto.phoneNumber);
		return instructor;
	}
}
