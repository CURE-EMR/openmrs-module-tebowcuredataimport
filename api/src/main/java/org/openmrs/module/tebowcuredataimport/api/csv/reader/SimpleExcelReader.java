package org.openmrs.module.tebowcuredataimport.api.csv.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.Visit;
import org.openmrs.api.APIException;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;

public class SimpleExcelReader {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	private String duplicateIds = "160892";
	
	public SimpleExcelReader() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void preparePersonAttributesImport() throws APIException, IOException {
		String mapping = Context.getAdministrationService().getGlobalProperty("tebowcuredataimport.fileNamePersonAttributeMap");
		log.error("===================Ndi muri prepare kandi global property nayibonye. Ni: " + mapping);
		String[] values = mapping.split("\\|");
		for (String s : values) {
			if (s.indexOf(":") > 0) {
				String fileName = s.substring(0, s.indexOf(":"));
				PersonAttributeType personAttributeType = Context.getPersonService().getPersonAttributeTypeByName(s.substring(s.indexOf(":") + 1, s.length()));
				if (personAttributeType != null) {
					addPersonAttributes(fileName, personAttributeType);
				}
			}
		}
		
	}
	
	public void addDateofBirth() throws IOException {
		String excelFilePath = "/opt/openmrs/modules/dof.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		
		PatientIdentifierType it = Context.getPatientService().getPatientIdentifierTypeByName("Old Identification Number");
		List<PatientIdentifierType> oldIds = new ArrayList<PatientIdentifierType>();
		oldIds.add(it);
		
		// Do we have to load all patient!
		List<Patient> patients = Context.getPatientService().getAllPatients();
		
		while (iterator.hasNext()) {
			try {
				Row nextRow = iterator.next();
				String patientUUID = nextRow.getCell(0).getStringCellValue();
				String dateOfBirth = nextRow.getCell(1).getStringCellValue();
				if (dateOfBirth != null && dateOfBirth.length() > 0 && !dateOfBirth.equalsIgnoreCase("NULL")) {
					Date dob = parseDate(dateOfBirth);
					if (dob != null) {
						for (Patient patient : patients) {
							if (patient.getPatientIdentifier(it).getIdentifier().equalsIgnoreCase(patientUUID)) {
								patient.setBirthdate(dob);
								Context.getPatientService().savePatient(patient);
							}
						}
					}
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		inputStream.close();
	}
	
	public void addPersonAttributes(String fileName, PersonAttributeType personAttributeType) throws IOException {
		String excelFilePath = "/opt/openmrs/modules/" + fileName + ".xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		
		PatientService ps = Context.getPatientService();
		PatientIdentifierType it = ps.getPatientIdentifierTypeByName("Identification Number");
		List<PatientIdentifierType> oldIds = new ArrayList<PatientIdentifierType>();
		oldIds.add(it);
		
		List<PatientIdentifier> identifiers = Context.getPatientService().getPatientIdentifiers(null, oldIds, null, null, null); // Do we really have to load all patients!!
		log.error("================================Ndi muri addPersonAttributes kandi indentifiers nazibonye. Nabonye " + identifiers.size());
		while (iterator.hasNext()) {
			try {
				Row nextRow = iterator.next();
				String patientUUID = null;
				
				Cell c0 = nextRow.getCell(0);
				if (c0 != null) {
					c0.setCellType(Cell.CELL_TYPE_STRING);
					patientUUID = c0.getStringCellValue();
				}
				
				Cell c1 = nextRow.getCell(1);
				String personAttributeValue = null;
				
				if (c1 != null) {
					c1.setCellType(Cell.CELL_TYPE_STRING);
					personAttributeValue = c1.getStringCellValue();
				}
				
				if (patientUUID != null && patientUUID.length() > 0 && personAttributeValue != null && personAttributeValue.length() > 0 && !personAttributeValue.equalsIgnoreCase("NULL")) {
					for (PatientIdentifier patientIdentifier : identifiers) {
						if (patientIdentifier.getIdentifier().equalsIgnoreCase(patientUUID)) {
							Patient p = patientIdentifier.getPatient();
							PersonAttribute pa = new PersonAttribute(personAttributeType, personAttributeValue);
							p.getPerson().addAttribute(pa);
							log.error("======================================= " + patientUUID + "=============" + personAttributeType.getName());
							Context.getPersonService().savePerson(p);
						}
					}
				}
			}
			catch (Exception e) {
				log.error("Habaye ikibazo=======================" + e);
			}
			log.error("Ndacyasomaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa=======================");
		}
		inputStream.close();
	}
	
	public void addIds() throws IOException {
		String excelFilePath = "/opt/openmrs/modules/ids.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		
		PatientIdentifierType it = Context.getPatientService().getPatientIdentifierTypeByName("Old Identification Number");
		List<PatientIdentifierType> oldIds = new ArrayList<PatientIdentifierType>();
		oldIds.add(it);
		
		PatientIdentifierType patientIdentifier = Context.getPatientService().getPatientIdentifierTypeByUuid("81433852-3f10-11e4-adec-0800271c1b75");
		
		// Do we really have to load all patients!!
		List<PatientIdentifier> oldIdentifiers = Context.getPatientService().getPatientIdentifiers(null, oldIds, null, null, null);
		while (iterator.hasNext()) {
			try {
				Row nextRow = iterator.next();
				String patientUUID = nextRow.getCell(0).getStringCellValue();
				Cell c = nextRow.getCell(1);
				c.setCellType(Cell.CELL_TYPE_STRING);
				String idValue = c.getStringCellValue();
				
				if (idValue != null && idValue.length() > 0 && !idValue.equalsIgnoreCase("NULL")) {
					for (PatientIdentifier id : oldIdentifiers) {
						if (id.getIdentifier().equalsIgnoreCase(patientUUID)) {
							PatientIdentifier identifier = new PatientIdentifier(idValue, patientIdentifier, null);
							identifier.setPatient(id.getPatient());
							Context.getPatientService().savePatientIdentifier(identifier);
						}
					}
				}
			}
			catch (Exception e) {}
			
		}
		inputStream.close();
	}
	
	public Date parseDate(String givenDate) {
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
	
	public double parseToDouble(String obsValue) {
		double value = -1;
		try {
			value = Double.parseDouble(obsValue);
		}
		catch (Exception e) {
			value = -1;
		}
		return value;
	}
	
	public void saveAllPatients() {
		PatientService ps = Context.getPatientService();
		PatientIdentifierType oldIdentifierType = ps.getPatientIdentifierTypeByUuid("8d79403a-c2cc-11de-8d13-0010c6dffd0f");
		PatientIdentifierType cchuIdentifierType = ps.getPatientIdentifierTypeByUuid("81433852-3f10-11e4-adec-0800271c1b75");
		
		List<Patient> allPatients = ps.getAllPatients();
		for (Patient patient : allPatients) {
			
			Visit v = new Visit();
			v.getCreator().getDisplayString();
			
			try {
				PatientIdentifier oldId = patient.getPatientIdentifier(oldIdentifierType);
				PatientIdentifier id = patient.getPatientIdentifier(cchuIdentifierType);
				
				if (id != null && !isDuplicate(id)) {
					id.setPreferred(true);
					oldId.setPreferred(false);
				} else {
					id.setPreferred(false);
					oldId.setPreferred(true);
				}
				ps.savePatientIdentifier(id);
				ps.savePatientIdentifier(oldId);
			}
			catch (Exception e) {}
			
			ps.savePatient(patient);
		}
	}
	
	public boolean isDuplicate(PatientIdentifier id) {
		for (String duplicate : duplicateIds.split(",")) {
			if (duplicate.equalsIgnoreCase(id.getIdentifier())) {
				return true;
			}
		}
		return false;
	}
	
	public void saveAllNullDoBs() {
		
		for (Patient patient : Context.getPatientService().getAllPatients()) {
			if (patient != null && patient.getBirthdate() == null) {
				patient.setBirthdate(parseDate("1960-01-01"));
				patient.setBirthdateEstimated(true);
				Context.getPatientService().savePatient(patient);
			}
		}
		
	}
	
}
