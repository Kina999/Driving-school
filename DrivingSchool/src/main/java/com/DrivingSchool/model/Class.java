package com.DrivingSchool.model;

import java.time.LocalDateTime;
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

import com.DrivingSchool.enumClasses.TestType;

@Entity
public class Class {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int numberOfClass;
	private TestType classType;
	private LocalDateTime classDateTime;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "licence_id")
	private Licence licence = new Licence();
	
	@ManyToMany
	@JoinTable(name = "class_candidate", 
	  joinColumns = @JoinColumn(name = "candidate_id"), 
	  inverseJoinColumns = @JoinColumn(name = "class_id"))
	private Set<Candidate> candidates = new HashSet<Candidate>();
	
	public Class() {
		super();
	}
	
	

	public Class(int numberOfClass, TestType classType, LocalDateTime classDateTime, Licence licence,
			Set<Candidate> candidates) {
		super();
		this.numberOfClass = numberOfClass;
		this.classType = classType;
		this.classDateTime = classDateTime;
		this.licence = licence;
		this.candidates = candidates;
	}



	public int getNumberOfClass() {
		return numberOfClass;
	}
	public void setNumberOfClass(int numberOfClass) {
		this.numberOfClass = numberOfClass;
	}
	public TestType getClassType() {
		return classType;
	}
	public void setClassType(TestType classType) {
		this.classType = classType;
	}
	public LocalDateTime getClassDateTime() {
		return classDateTime;
	}
	public void setClassDateTime(LocalDateTime classDateTime) {
		this.classDateTime = classDateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Licence getLicence() {
		return licence;
	}

	public void setLicence(Licence licence) {
		this.licence = licence;
	}



	public Set<Candidate> getCandidates() {
		return candidates;
	}



	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}
	
}
