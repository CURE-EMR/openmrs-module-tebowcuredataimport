package org.openmrs.module.tebowcuredataimport.api.dao;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.tebowcuredataimport.domain.HospitalRunPatientNoteEncounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HospitalRunPatientNoteEncounterDAO {
	
	/*Logger for this class and subclasses*/
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@SuppressWarnings("unchecked")
	public List<HospitalRunPatientNoteEncounter> getAllPatientNoteEncounters() {
		Session session = getCurrentSession();
		Criteria cr = session.createCriteria(HospitalRunPatientNoteEncounter.class);
		List<HospitalRunPatientNoteEncounter> patientNoteEncounters = cr.list();
		return patientNoteEncounters;
	}
	
	public HospitalRunPatientNoteEncounter getPatientNoteEncounterByVisitUUID(String visitUUID) {
		Session session = getCurrentSession();
		Criteria cr = session.createCriteria(HospitalRunPatientNoteEncounter.class);
		cr.add(Restrictions.like("visit", visitUUID));
		HospitalRunPatientNoteEncounter patientNoteEncounter = (HospitalRunPatientNoteEncounter) cr.list().get(0);
		return patientNoteEncounter;
	}
	
	public HospitalRunPatientNoteEncounter addPatientNoteEncounter(HospitalRunPatientNoteEncounter encounter) {
		Session session = getCurrentSession();
		try {
			session.save(encounter);
		}
		catch (Exception e) {
			System.exit(1);
		}
		
		return encounter;
	}
	
	public void updatePatientNoteEncounter(HospitalRunPatientNoteEncounter encounter) {
		Session session = getCurrentSession();
		session.update(encounter);
	}
	
	public void deletePatientNoteEncounter(int id) {
		Session session = getCurrentSession();
		HospitalRunPatientNoteEncounter p = (HospitalRunPatientNoteEncounter) session.load(HospitalRunPatientNoteEncounter.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}
	
	private org.hibernate.Session getCurrentSession() {
		try {
			return sessionFactory.getCurrentSession();
		}
		catch (NoSuchMethodError ex) {
			try {
				Method method = sessionFactory.getClass().getMethod("getCurrentSession", null);
				return (org.hibernate.Session) method.invoke(sessionFactory, null);
			}
			catch (Exception e) {
				throw new RuntimeException("Failed to get the current hibernate session", e);
			}
		}
	}
	
}
