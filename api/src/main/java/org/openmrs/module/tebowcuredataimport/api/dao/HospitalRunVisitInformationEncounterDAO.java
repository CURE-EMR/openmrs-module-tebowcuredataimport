package org.openmrs.module.tebowcuredataimport.api.dao;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.tebowcuredataimport.domain.HospitaRunVisitInformationEncounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HospitalRunVisitInformationEncounterDAO {
	
	/* Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@SuppressWarnings("unchecked")
	public List<HospitaRunVisitInformationEncounter> getAllVisitInformationEncounters() {
		Session session = getCurrentSession();
		Criteria cr = session.createCriteria(HospitaRunVisitInformationEncounter.class);
		List<HospitaRunVisitInformationEncounter> visitInformationEncounters = cr.list();
		return visitInformationEncounters;
	}
	
	public HospitaRunVisitInformationEncounter getVisitInformationEncounterByVisitUUID(String visitUUID) {
		Session session = getCurrentSession();
		Criteria cr = session.createCriteria(HospitaRunVisitInformationEncounter.class);
		cr.add(Restrictions.like("visit", visitUUID));
		HospitaRunVisitInformationEncounter visitInformationEncounter = (HospitaRunVisitInformationEncounter) cr.list().get(0);
		return visitInformationEncounter;
	}
	
	public HospitaRunVisitInformationEncounter addVisitInformationEncounter(HospitaRunVisitInformationEncounter encounter) {
		Session session = getCurrentSession();
		try {
			session.save(encounter);
		}
		catch (Exception e) {
			System.exit(1);
		}
		
		return encounter;
	}
	
	public void updateVisitInformationEncounter(HospitaRunVisitInformationEncounter encounter) {
		Session session = getCurrentSession();
		session.update(encounter);
	}
	
	public void deleteVisitInformationEncounter(int id) {
		Session session = getCurrentSession();
		HospitaRunVisitInformationEncounter p = (HospitaRunVisitInformationEncounter) session.load(HospitaRunVisitInformationEncounter.class, new Integer(id));
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
