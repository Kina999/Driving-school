package com.DrivingSchool.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Termin {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date startTime;
	private Date endTime;
	private boolean deleted;
	private Date cancelationDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "licence_id")
	private Licence licence = new Licence();
	@ManyToMany
	@JoinTable(name = "class_candidate", 
	  joinColumns = @JoinColumn(name = "class_id"),
	  inverseJoinColumns = @JoinColumn(name = "candidate_id"))
	private Set<Candidate> candidates = new HashSet<Candidate>();
	
	public Termin() {
		super();
	}
	public Termin(Date startTime, Date endTime , Set<Candidate> candidate, boolean deleted, Date cancelationDate) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.candidates = candidate;
		this.deleted = deleted;
		this.cancelationDate = cancelationDate;
	}

	public Termin(Date startTime, Date endTime, boolean deleted, Date cancelationDate, Licence licence, Set<Candidate> candidates) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.deleted = deleted;
		this.cancelationDate = cancelationDate;
		this.licence = licence;
		this.candidates = candidates;
	}

	public Termin(Date startTime, Date endTime, boolean deleted, Licence licence,
				  Set<Candidate> candidate) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.deleted = deleted;
		this.licence = licence;
		this.candidates = candidate;
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
	public Set<Candidate> getCandidate() {
		return candidates;
	}
	public void setCandidate(Set<Candidate> candidate) {
		this.candidates = candidate;
	}
	public Licence getLicence() {
		return licence;
	}
	public void setLicence(Licence licence) {
		this.licence = licence;
	}
	public Date getCancelationDate() {
		return cancelationDate;
	}
	public void setCancelationDate(Date cancelationDate) {
		this.cancelationDate = cancelationDate;
	}

}
