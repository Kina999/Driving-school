package com.DrivingSchool.mapper;

import com.DrivingSchool.dto.CandidateTerminDTO;
import com.DrivingSchool.dto.TerminDTO;
import com.DrivingSchool.model.Termin;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import static java.lang.Math.abs;

public class TerminMapper {
	
	public static Termin TerminDTOToTermin(TerminDTO dto) {
		Termin termin = new Termin(dto.startTime, dto.endTime, false, null, null, null, null);
		return termin;
	}
	
	public static CandidateTerminDTO TerminToTerminDTO(Termin termin) {

		boolean cancelable = false;

		LocalDate terminDate = termin.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		if(abs(Period.between(terminDate, now).getDays()) > 3) cancelable = true;

		return new CandidateTerminDTO(termin.getId(), termin.getStartTime(), termin.getEndTime(), termin.getLicence().getCategory().getCategoryType() + " - " + termin.getLicence().getLicenceType(), CandidateMapper.CandidateSetToCandidateSetDTO(termin.getCandidate()), cancelable);
	}
	
}
