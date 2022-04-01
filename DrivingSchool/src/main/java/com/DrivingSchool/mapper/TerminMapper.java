package com.DrivingSchool.mapper;

import com.DrivingSchool.dto.TerminDTO;
import com.DrivingSchool.model.Termin;

public class TerminMapper {
	public static Termin TerminDTOToTermin(TerminDTO dto) {
		Termin termin = new Termin(dto.startTime, dto.endTime, null, false, 0, false);
		return termin;
	}
}
