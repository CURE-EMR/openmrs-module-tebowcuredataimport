package org.openmrs.module.tebowcuredataimport.api.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.tebowcuredataimport.api.dao.HospitalRunProcedureEncounterDAO;
import org.openmrs.module.tebowcuredataimport.domain.HospitaRunProcedureEncounter;
import org.openmrs.module.tebowcuredataimport.domain.HospitaRunVisitInformationEncounter;
import org.openmrs.module.tebowcuredataimport.utils.HospitalRunObservationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("hospitaRunProcedureEncounterService")
public class HospitaRunProcedureEncounterService {
	
	/* Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	HospitalRunProcedureEncounterDAO hospitaRunProcedureEncounterDAO;
	
	@Transactional
	public List<HospitaRunProcedureEncounter> getAllProcedureEncounters() {
		return hospitaRunProcedureEncounterDAO.getAllProcedureEncounters();
	}
	
	@Transactional
	public HospitaRunProcedureEncounter getProcedureEncounterByVisitUUID(String visitUUID) {
		return hospitaRunProcedureEncounterDAO.getProcedureEncounterByVisitUUID(visitUUID);
	}
	
	@Transactional
	public void addProcedureEncounter(HospitaRunProcedureEncounter encounter) {
		hospitaRunProcedureEncounterDAO.addProcedureEncounter(encounter);
	}
	
	@Transactional
	public void updateProcedureEncounter(HospitaRunProcedureEncounter encounter) {
		hospitaRunProcedureEncounterDAO.updateProcedureEncounter(encounter);
		
	}
	
	@Transactional
	public void deleteProcedureEncounter(int id) {
		hospitaRunProcedureEncounterDAO.deleteProcedureEncounter(id);
	}
	
	@Transactional
	public void createProcedureEncounters(String formConcept) {
		ConceptService cs = Context.getService(ConceptService.class);
		List<Obs> obs = Context.getObsService().getObservationsByPersonAndConcept(null, cs.getConcept("Procedure Encounter Tag"));
		int i = 0;
		
		try {
			
			for (Obs obs2 : obs) {
				
				if (obs2.getValueText() != null && obs2.getValueText() != "") {
					Encounter e = obs2.getEncounter();
					
					HospitaRunProcedureEncounter he = getProcedureEncounterByVisitUUID(obs2.getValueText());
					
					String anesthesiaType = he.getAnesthesiaType();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), anesthesiaType, e);
					
					String anesthesiologist = he.getAnesthesiologist();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), anesthesiologist, e);
					
					String assistant = he.getAssistant();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), assistant, e);
					
					String description = he.getDescription();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), description, e);
					
					String cptCode = he.getCptCode();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), cptCode, e);
					
					String location = he.getLocation();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), location, e);
					
					String notes = he.getNotes();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), notes, e);
					
					String physician = he.getPhysician();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), physician, e);
					
					String procedureDate = he.getProcedureDate();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), procedureDate, e);
					
					String timeStarted = he.getTimeStarted();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), timeStarted, e);
					
					String timeEnded = he.getTimeEnded();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), timeEnded, e);
					
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
	
	@Transactional
	public void createPatientNoteEncounters(String formConcept) {
		ConceptService cs = Context.getService(ConceptService.class);
		List<Obs> obs = Context.getObsService().getObservationsByPersonAndConcept(null, cs.getConcept("Patient Note Encounter Tag"));
		int i = 0;
		
		try {
			
			for (Obs obs2 : obs) {
				
				if (obs2.getValueText() != null && obs2.getValueText() != "") {
					Encounter e = obs2.getEncounter();
					
					HospitaRunProcedureEncounter he = getProcedureEncounterByVisitUUID(obs2.getValueText());
					
					String anesthesiaType = he.getAnesthesiaType();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), anesthesiaType, e);
					
					String anesthesiologist = he.getAnesthesiologist();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), anesthesiologist, e);
					
					String assistant = he.getAssistant();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), assistant, e);
					
					String description = he.getDescription();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), description, e);
					
					String cptCode = he.getCptCode();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), cptCode, e);
					
					String location = he.getLocation();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), location, e);
					
					String notes = he.getNotes();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), notes, e);
					
					String physician = he.getPhysician();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), physician, e);
					
					String procedureDate = he.getProcedureDate();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), procedureDate, e);
					
					String timeStarted = he.getTimeStarted();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), timeStarted, e);
					
					String timeEnded = he.getTimeEnded();
					HospitalRunObservationUtil.createObs(cs.getConcept("Anesthesia Type"), timeEnded, e);
					
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
