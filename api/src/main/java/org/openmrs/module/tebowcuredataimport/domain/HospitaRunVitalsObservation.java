package org.openmrs.module.tebowcuredataimport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * This class represents observation objects as they were stored in the HospitalRun system that was
 * used by Tebow CURE Hospital. The aim is to export the data from the HospitalRun instance into
 * XLSX files and with this class, read/load the files into a MysQL database table. We'll then
 * source the table into the OpenMRS database and leverage Hibernate and the OperMRS API to load
 * instances of this class and create encounters and observations in OpernMRS database.
 * 
 * @author rubailly
 */
@Entity
@Table(name = "filemaker_obs")
@Proxy(lazy = false)
public class HospitaRunVitalsObservation {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "encounter")
	private String encounter;
	
	@Column(name = "concept")
	private String concept;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "form")
	private String form;
	
	public HospitaRunVitalsObservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HospitaRunVitalsObservation(String encounter, String concept, String answer, String comment, String form) {
		super();
		this.encounter = encounter;
		this.concept = concept;
		this.answer = answer;
		this.comment = comment;
		this.form = form;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEncounter() {
		return encounter;
	}
	
	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}
	
	public String getConcept() {
		return concept;
	}
	
	public void setConcept(String concept) {
		this.concept = concept;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getForm() {
		return form;
	}
	
	public void setForm(String form) {
		this.form = form;
	}
	
}
