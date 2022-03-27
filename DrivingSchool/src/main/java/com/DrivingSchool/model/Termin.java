package com.DrivingSchool.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Termin {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date startTime;
	private Date endTime;
	private boolean deleted;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "licence_id")
	private Licence licence = new Licence();
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "instructor_id")
	private Instructor instructor = new Instructor();
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "candidate_id")
	private Candidate candidate = new Candidate();
	
	public Termin() {
		super();
	}
	public Termin(Date startTime, Date endTime, Instructor instructor, Candidate candidate, boolean deleted) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.instructor = instructor;
		this.candidate = candidate;
		this.deleted = deleted;
	}
	
	public Termin(Date startTime, Date endTime, boolean deleted, Licence licence,
			Instructor instructor, Candidate candidate) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.deleted = deleted;
		this.licence = licence;
		this.instructor = instructor;
		this.candidate = candidate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Licence getLicence() {
		return licence;
	}
	public void setLicence(Licence licence) {
		this.licence = licence;
	}
	
	
}
