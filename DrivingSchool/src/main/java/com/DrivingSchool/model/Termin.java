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
	private boolean canceled;
	private int numberOfClass;
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
	public Termin(Date startTime, Date endTime , Set<Candidate> candidate, boolean deleted, int numberOfClass, boolean canceled) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.candidates = candidate;
		this.deleted = deleted;
		this.numberOfClass = numberOfClass;
		this.canceled = canceled;
	}
	
	public Termin(Date startTime, Date endTime, boolean deleted, Licence licence,
					Set<Candidate> candidate, int numberOfClass) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.deleted = deleted;
		this.licence = licence;
		this.candidates = candidate;
		this.numberOfClass = numberOfClass;
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
	public int getNumberOfClass() {
		return numberOfClass;
	}
	public void setNumberOfClass(int numberOfClass) {
		this.numberOfClass = numberOfClass;
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

}
