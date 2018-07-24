package org.openmrs.module.tebowcuredataimport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * This class represents procedure encounter objects as they were stored in the HospitalRun system
 * that was used by Tebow CURE Hospital. The aim is to export the data from the HospitalRun instance
 * into XLSX files and with this class, read/load the files into a MysQL database table. We'll then
 * source the table into the OpenMRS database and leverage Hibernate and the OperMRS API to load
 * instances of this class and create encounters and observations in OpernMRS database.
 * 
 * @author rubailly
 */
@Entity
@Table(name = "procedure_encounter")
@Proxy(lazy = false)
public class HospitaRunProcedureEncounter {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "anesthesia_type")
	private String anesthesiaType;
	
	@Column(name = "anesthesiologist")
	private String anesthesiologist;
	
	@Column(name = "assistant")
	private String assistant;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "cptCode")
	private String cptCode;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "physician")
	private String physician;
	
	@Column(name = "procedure_date")
	private String procedureDate;
	
	@Column(name = "time_started")
	private String timeStarted;
	
	@Column(name = "time_ended")
	private String timeEnded;
	
	@Column(name = "visit")
	private String visit;
	
	public HospitaRunProcedureEncounter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HospitaRunProcedureEncounter(String anesthesiaType, String anesthesiologist, String assistant, String description, String cptCode, String location, String notes, String physician, String procedureDate, String timeStarted, String timeEnded, String visit) {
		super();
		this.anesthesiaType = anesthesiaType;
		this.anesthesiologist = anesthesiologist;
		this.assistant = assistant;
		this.description = description;
		this.cptCode = cptCode;
		this.location = location;
		this.notes = notes;
		this.physician = physician;
		this.procedureDate = procedureDate;
		this.timeStarted = timeStarted;
		this.timeEnded = timeEnded;
		this.visit = visit;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAnesthesiaType() {
		return anesthesiaType;
	}
	
	public void setAnesthesiaType(String anesthesiaType) {
		this.anesthesiaType = anesthesiaType;
	}
	
	public String getAnesthesiologist() {
		return anesthesiologist;
	}
	
	public void setAnesthesiologist(String anesthesiologist) {
		this.anesthesiologist = anesthesiologist;
	}
	
	public String getAssistant() {
		return assistant;
	}
	
	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCptCode() {
		return cptCode;
	}
	
	public void setCptCode(String cptCode) {
		this.cptCode = cptCode;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getPhysician() {
		return physician;
	}
	
	public void setPhysician(String physician) {
		this.physician = physician;
	}
	
	public String getProcedureDate() {
		return procedureDate;
	}
	
	public void setProcedureDate(String procedureDate) {
		this.procedureDate = procedureDate;
	}
	
	public String getTimeStarted() {
		return timeStarted;
	}
	
	public void setTimeStarted(String timeStarted) {
		this.timeStarted = timeStarted;
	}
	
	public String getTimeEnded() {
		return timeEnded;
	}
	
	public void setTimeEnded(String timeEnded) {
		this.timeEnded = timeEnded;
	}
	
	public String getVisit() {
		return visit;
	}
	
	public void setVisit(String visit) {
		this.visit = visit;
	}
	
}
