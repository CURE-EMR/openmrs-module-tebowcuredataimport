package org.openmrs.module.tebowcuredataimport.api.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.tebowcuredataimport.api.dao.HospitalRunVisitInformationEncounterDAO;
import org.openmrs.module.tebowcuredataimport.domain.HospitaRunVisitInformationEncounter;
import org.openmrs.module.tebowcuredataimport.utils.HospitalRunObservationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("hospitaRunVisitInformationEncounterService")
public class HospitaRunVisitInformationEncounterService {
	
	/* Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	HospitalRunVisitInformationEncounterDAO hospitaVisitInformationEncounterDAO;
	
	@Transactional
	public List<HospitaRunVisitInformationEncounter> getAllProcedureEncounters() {
		return hospitaVisitInformationEncounterDAO.getAllVisitInformationEncounters();
	}
	
	@Transactional
	public HospitaRunVisitInformationEncounter getVisitInformationEncounterByVisitUUID(String visitUUID) {
		return hospitaVisitInformationEncounterDAO.getVisitInformationEncounterByVisitUUID(visitUUID);
	}
	
	@Transactional
	public void addVisitInformationEncounter(HospitaRunVisitInformationEncounter encounter) {
		hospitaVisitInformationEncounterDAO.addVisitInformationEncounter(encounter);
	}
	
	@Transactional
	public void updateVisitInformationEncounter(HospitaRunVisitInformationEncounter encounter) {
		hospitaVisitInformationEncounterDAO.updateVisitInformationEncounter(encounter);
		
	}
	
	@Transactional
	public void deleteVisitInformationEncounter(int id) {
		hospitaVisitInformationEncounterDAO.deleteVisitInformationEncounter(id);
	}
	
	@Transactional
	public void createVisitInformationEncounters(String formConcept) {
		ConceptService cs = Context.getService(ConceptService.class);
		List<Obs> obs = Context.getObsService().getObservationsByPersonAndConcept(null, cs.getConcept("Visit Information Encounter Tag"));
		int i = 0;
		
		try {
			
			for (Obs obs2 : obs) {
				
				if (obs2.getValueText() != null && obs2.getValueText() != "") {
					Encounter e = obs2.getEncounter();
					
					HospitaRunVisitInformationEncounter hv = getVisitInformationEncounterByVisitUUID(obs2.getValueText());
					
					String location = hv.getLocation();
					HospitalRunObservationUtil.createObs(cs.getConcept("Visit Location"), location, e);
					
					String examiner = hv.getExaminer();
					HospitalRunObservationUtil.createObs(cs.getConcept("Examiner"), examiner, e);
					
					String primaryDiagnosis = hv.getPrimaryDiagnosis();
					HospitalRunObservationUtil.createObs(cs.getConcept("Admitting Diagnosis"), primaryDiagnosis, e);
					
					String primaryBillingDiagnosisId = hv.getPrimaryBillingDiagnosisId();
					HospitalRunObservationUtil.createObs(cs.getConcept("Final/Billing Diagnosis"), primaryBillingDiagnosisId, e);
					
					String history = hv.getHistory();
					HospitalRunObservationUtil.createObs(cs.getConcept("Patient History"), history, e);
					
					String historySince = hv.getHistorySince();
					HospitalRunObservationUtil.createObs(cs.getConcept("History since last seen"), historySince, e);
					
					Encounter encounter = Context.getEncounterService().saveEncounter(e);
					setEncounterObsForm(encounter, cs.getConcept(formConcept));
					
					log.error(++i + ".===Turangije encounter " + e.getId());
				}
			}
			
		}
		catch (Exception e) {
			log.error("There was an error building the encounter", e);
		}
		
	}
	
	public void setEncounterObsForm(Encounter e, Concept c) {
		HospitalRunObservationUtil.setEncounterObsForm(e, c);
		
	}
	
}
