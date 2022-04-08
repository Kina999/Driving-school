package com.DrivingSchool.mapper;

import com.DrivingSchool.dto.InstructorLicenceDTO;
import com.DrivingSchool.enumClasses.TestType;
import com.DrivingSchool.model.Licence;

public class LicenceMapper {
	public static Licence InstructorLicenceDTOToLicence(InstructorLicenceDTO dto) {
		Licence licence = new Licence();
		licence.setExpirationDate(dto.expirationDate);
		if(dto.licenceType.equals("PRAKTICNA")) licence.setLicenceType(TestType.PRACTICAL);
		else licence.setLicenceType(TestType.THEORETICAL);
		return licence;
	}
}
