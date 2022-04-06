package com.DrivingSchool.mapper;

import com.DrivingSchool.dto.DrivingTestDTO;
import com.DrivingSchool.model.DrivingTest;

public class DrivingTestMapper {
	public static DrivingTest TestDTOToTest(DrivingTestDTO dto) {
		return new DrivingTest(dto.dateAndTime, false, null, null);
	}
}
