package com.DrivingSchool.mapper;

import com.DrivingSchool.dto.DrivingTestDTO;
import com.DrivingSchool.model.DrivingTest;

public class DrivingTestMapper {
	public static DrivingTest TestDTOToTest(DrivingTestDTO dto) {
		return new DrivingTest(dto.dateAndTime, false, null, null);
	}
	
	public static DrivingTestDTO TestToTestDTO(DrivingTest test) {
		if(test.getCandidate() != null) {
			return new DrivingTestDTO(test.getId(), test.getTestDateTime(), test.getLicence().getCategory().getCategoryType() + " - " + test.getLicence().getLicenceType(), CandidateMapper.CandidateToCandidateRegistrationDTO(test.getCandidate()));
		}else {
			return new DrivingTestDTO(test.getId(), test.getTestDateTime(), test.getLicence().getCategory().getCategoryType() + " - " + test.getLicence().getLicenceType(), null);
		}
	}
}
