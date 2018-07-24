package org.openmrs.module.tebowcuredataimport.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;

public class HospitalRunObservationUtil {
	
	/* Logger for this class and subclasses*/
	protected final static Log log = LogFactory.getLog(HospitalRunObservationUtil.class);
	
	/**
	 * Creates an observation for the given concept on the given encounter
	 * 
	 * @param obsConcept the observation value
	 * @param obsComment
	 * @param obsAnswer
	 * @return
	 */
	public static Obs createObs(Concept question, String obsValue, Encounter e) {
		Obs o = null;
		if (question.getDatatype().isText()) {
			o = new Obs();
			o.setConcept(question);
			o.setValueText(obsValue);
		}
		if (question.getDatatype().isCoded()) {
			try {
				Concept valueCoded = getConceptFormText(obsValue);
				if (valueCoded != null) {
					o = new Obs();
					o.setConcept(question);
					o.setValueCoded(valueCoded);
				}
			}
			catch (Exception e2) {}
			
		}
		if (question.getDatatype().isDate()) {
			Date d = parseDate(obsValue);
			if (d != null) {
				o = new Obs();
				o.setConcept(question);
				o.setValueDate(d);
			}
		}
		if (question.getDatatype().isNumeric()) {
			double value = parseToDouble(obsValue);
			if (value != -1) {
				o = new Obs();
				o.setConcept(question);
				o.setValueNumeric(value);
			}
			
		}
		if (o != null) {
			o.setObsDatetime(e.getEncounterDatetime());
			o.setLocation(e.getLocation());
			e.addObs(o);
		}
		return o;
	}
	
	public static Concept getConceptFormText(String text) {
		return Context.getConceptService().getConcept(text);
	}
	
	public static Date parseDate(String givenDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(givenDate);
		}
		catch (ParseException e) {
			return null;
		}
		return date;
	}
	
	public static double parseToDouble(String obsValue) {
		double value = -1;
		try {
			value = Double.parseDouble(obsValue);
		}
		catch (Exception e) {
			value = -1;
		}
		return value;
	}
	
	/**
	 * Creates an observation for the given concept on the given encounter
	 * 
	 * @param oldEncounterUUID the encounter for which the observation should be created for
	 * @param obsValue the observation value
	 * @param columnConcept the concept for the observation
	 * @return
	 */
	public static void setEncounterObsForm(Encounter e, Concept c) {
		
		Obs o = new Obs();
		o.setConcept(c);
		o.setPerson(e.getPatient().getPerson());
		o.setObsDatetime(e.getEncounterDatetime());
		o.setDateCreated(e.getDateCreated());
		o.setCreator(e.getCreator());
		o.setLocation(e.getLocation());
		o.setEncounter(e);
		
		for (Obs obs2 : e.getAllObs()) {
			if (!obs2.getConcept().equals(c)) {
				o.addGroupMember(obs2);
			}
			
		}
		
		Context.getObsService().saveObs(o, "Adding Form Id");
		
	}
}
