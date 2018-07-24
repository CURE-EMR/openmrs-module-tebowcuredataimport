package org.openmrs.module.tebowcuredataimport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * This class represents Patient Note observations objects as they were stored in the HospitalRun
 * system that was used by Tebow CURE Hospital. The aim is to export the data from the HospitalRun
 * instance into XLSX files and with this class, read/load the files into a MysQL database table.
 * We'll then source the table into the OpenMRS database and leverage Hibernate and the OperMRS API
 * to load instances of this class and create encounters and observations in OpernMRS database.
 * 
 * @author rubailly
 */
@Entity
@Table(name = "patientnote_obs")
@Proxy(lazy = false)
public class HospitalRunPatientNoteEncounter {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "hospitalrun_id")
	private String hospitalRunId;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "note_type")
	private String noteType;
	
	@Column(name = "patient")
	private String patient;
	
	@Column(name = "visit")
	private String visit;
	
	public HospitalRunPatientNoteEncounter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HospitalRunPatientNoteEncounter(String hospitalRunId, String content, String createdBy, String date, String noteType, String patient, String visit) {
		super();
		this.hospitalRunId = hospitalRunId;
		this.content = content;
		this.createdBy = createdBy;
		this.date = date;
		this.noteType = noteType;
		this.patient = patient;
		this.visit = visit;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getHospitalRunId() {
		return hospitalRunId;
	}
	
	public void setHospitalRunId(String hospitalRunId) {
		this.hospitalRunId = hospitalRunId;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getNoteType() {
		return noteType;
	}
	
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	
	public String getPatient() {
		return patient;
	}
	
	public void setPatient(String patient) {
		this.patient = patient;
	}
	
	public String getVisit() {
		return visit;
	}
	
	public void setVisit(String visit) {
		this.visit = visit;
	}
	
}
