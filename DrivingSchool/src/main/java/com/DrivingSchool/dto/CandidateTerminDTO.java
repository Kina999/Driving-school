package com.DrivingSchool.dto;

import java.util.Date;
import java.util.Set;
 
public class CandidateTerminDTO {
	
	public Integer id;
	public Date startTime;
	public Date endTime;
	public boolean cancelable;
	public String categoryAndType;
	public Set<CandidateRegistrationDTO> candidates;
	
	public CandidateTerminDTO() {
		super();
	}

	public CandidateTerminDTO(Integer id, Date startTime, Date endTime, String categoryAndType,
			Set<CandidateRegistrationDTO> candidates, boolean cancelable) {
		super();
		this.id = id;
		this.cancelable = cancelable;
		this.startTime = startTime;
		this.endTime = endTime;
		this.categoryAndType = categoryAndType;
		this.candidates = candidates;
	}
	
	
}
