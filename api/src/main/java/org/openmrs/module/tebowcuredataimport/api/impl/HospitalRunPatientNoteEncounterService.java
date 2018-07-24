package org.openmrs.module.tebowcuredataimport.api.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.tebowcuredataimport.api.dao.HospitalRunPatientNoteEncounterDAO;
import org.openmrs.module.tebowcuredataimport.domain.HospitalRunPatientNoteEncounter;
import org.openmrs.module.tebowcuredataimport.utils.HospitalRunObservationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("hospitalRunPatientNoteEncounterService")
public class HospitalRunPatientNoteEncounterService {
	
	/* Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	HospitalRunPatientNoteEncounterDAO hospitalRunPatientNoteEncounterDAO;
	
	@Transactional
	public List<HospitalRunPatientNoteEncounter> getAllPatientNoteEncounters() {
		return hospitalRunPatientNoteEncounterDAO.getAllPatientNoteEncounters();
	}
	
	@Transactional
	public HospitalRunPatientNoteEncounter getPatientNoteEncounterByVisitUUID(String visitUUID) {
		return hospitalRunPatientNoteEncounterDAO.getPatientNoteEncounterByVisitUUID(visitUUID);
	}
	
	@Transactional
	public void addPatientNoteEncounter(HospitalRunPatientNoteEncounter encounter) {
		hospitalRunPatientNoteEncounterDAO.addPatientNoteEncounter(encounter);
	}
	
	@Transactional
	public void updatePatientNoteEncounter(HospitalRunPatientNoteEncounter encounter) {
		hospitalRunPatientNoteEncounterDAO.updatePatientNoteEncounter(encounter);
		
	}
	
	@Transactional
	public void deleteVisitInformationEncounter(int id) {
		hospitalRunPatientNoteEncounterDAO.deletePatientNoteEncounter(id);
	}
	
	@Transactional
	public void createPatientNoteEncounters(String formConcept) {
		ConceptService cs = Context.getService(ConceptService.class);
		List<Obs> obs = Context.getObsService().getObservationsByPersonAndConcept(null, cs.getConcept("Patient Note Encounter Tag"));
		int i = 0;
		
		try {
			
			for (Obs obs2 : obs) {
				
				if (obs2.getValueText() != null && obs2.getValueText() != "") {
					Encounter e = obs2.getEncounter();
					
					HospitalRunPatientNoteEncounter pn = getPatientNoteEncounterByVisitUUID(obs2.getValueText());
					
					String content = pn.getContent();
					HospitalRunObservationUtil.createObs(cs.getConcept("Patient Notes, Note"), content, e);
					
					String createdBy = pn.getCreatedBy();
					HospitalRunObservationUtil.createObs(cs.getConcept("On Behalf Of"), createdBy, e);
					
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
