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
public class DrivingTest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date testDateTime;
	private boolean deleted;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "licence_id")
	private Licence licence = new Licence();
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "candidate_id")
	private Candidate candidate = new Candidate();
	
	public DrivingTest() {
		super();
	}

	public DrivingTest(Date testDateTime, boolean deleted, Licence licence, Candidate candidate) {
		super();
		this.testDateTime = testDateTime;
		this.deleted = deleted;
		this.licence = licence;
		this.candidate = candidate;
	}

	public Date getTestDateTime() {
		return testDateTime;
	}
	public void setTestDateTime(Date testDateTime) {this.testDateTime = testDateTime;}
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}
	public boolean isDeleted() {return deleted;}
	public void setDeleted(boolean deleted) {this.deleted = deleted;}
	public Licence getLicence() {return licence;}
	public void setLicence(Licence licence) {this.licence = licence;}
	public Candidate getCandidate() {return candidate;}
	public void setCandidate(Candidate candidate) {this.candidate = candidate;}
	
}
