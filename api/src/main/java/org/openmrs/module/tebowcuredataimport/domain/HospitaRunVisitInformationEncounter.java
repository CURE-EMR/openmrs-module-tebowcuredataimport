package org.openmrs.module.tebowcuredataimport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * This class represents "visit information" observation objects as they were stored in the
 * HospitalRun system that was used by Tebow CURE Hospital. The aim is to export the data from the
 * HospitalRun instance into XLSX files and with this class, read/load the files into a MysQL
 * database table. We'll then source the table into the OpenMRS database and leverage Hibernate and
 * the OperMRS API to load instances of this class and create encounters and observations in
 * OpernMRS database.
 * 
 * @author rubailly
 */
@Entity
@Table(name = "visit_information_encounter")
@Proxy(lazy = false)
public class HospitaRunVisitInformationEncounter {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "visit")
	private String visit;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "examiner")
	private String examiner;
	
	@Column(name = "primary_diagnosis")
	private String primaryDiagnosis;
	
	@Column(name = "primary_billing_diagnosisId")
	private String primaryBillingDiagnosisId;
	
	@Column(name = "history")
	private String history;
	
	@Column(name = "history_since")
	private String historySince;
	
	public HospitaRunVisitInformationEncounter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HospitaRunVisitInformationEncounter(String visit, String location, String examiner, String primaryDiagnosis, String primaryBillingDiagnosisId, String history, String historySince) {
		super();
		this.visit = visit;
		this.location = location;
		this.examiner = examiner;
		this.primaryDiagnosis = primaryDiagnosis;
		this.primaryBillingDiagnosisId = primaryBillingDiagnosisId;
		this.history = history;
		this.historySince = historySince;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getVisit() {
		return visit;
	}
	
	public void setVisit(String visit) {
		this.visit = visit;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getExaminer() {
		return examiner;
	}
	
	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}
	
	public String getPrimaryDiagnosis() {
		return primaryDiagnosis;
	}
	
	public void setPrimaryDiagnosis(String primaryDiagnosis) {
		this.primaryDiagnosis = primaryDiagnosis;
	}
	
	public String getPrimaryBillingDiagnosisId() {
		return primaryBillingDiagnosisId;
	}
	
	public void setPrimaryBillingDiagnosisId(String primaryBillingDiagnosisId) {
		this.primaryBillingDiagnosisId = primaryBillingDiagnosisId;
	}
	
	public String getHistory() {
		return history;
	}
	
	public void setHistory(String history) {
		this.history = history;
	}
	
	public String getHistorySince() {
		return historySince;
	}
	
	public void setHistorySince(String historySince) {
		this.historySince = historySince;
	}
	
}
