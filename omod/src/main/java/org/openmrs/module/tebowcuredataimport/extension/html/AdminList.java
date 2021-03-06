/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.tebowcuredataimport.extension.html;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

/**
 * This class defines the links that will appear on the administration page under the
 * "tebowcuredataimport.title" heading. This extension is enabled by defining (uncommenting) it in
 * the config.xml file.
 */
public class AdminList extends AdministrationSectionExt {
	
	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getMediaType()
	 */
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}
	
	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getTitle()
	 */
	public String getTitle() {
		return "tebowcuredataimport.title";
	}
	
	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getLinks()
	 */
	public Map<String, String> getLinks() {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("module/tebowcuredataimport/tebowcuredataimport.form", "tebowcuredataimport.title");
		map.put("module/tebowcuredataimport/addDates.form", "tebowcuredataimport.adddate");
		map.put("module/tebowcuredataimport/addIds.form", "tebowcuredataimport.addIds");
		map.put("module/tebowcuredataimport/addPersonAttributes.form", "tebowcuredataimport.addPersonAttributes");
		map.put("module/tebowcuredataimport/addTypedAddress.form", "tebowcuredataimport.addTypedAddress");
		map.put("module/tebowcuredataimport/addMiddleName.form", "tebowcuredataimport.addMiddleName");
		map.put("module/tebowcuredataimport/saveAllPatients.form", "tebowcuredataimport.saveAllPatients");
		map.put("module/tebowcuredataimport/saveAllNullDoBs.form", "tebowcuredataimport.saveAllNullDoBs");
		
		map.put("module/tebowcuredataimport/createProcedureEncounters.form", "tebowcuredataimport.createProcedureEncounters");
		map.put("module/tebowcuredataimport/createVisitInformationEncounters.form", "tebowcuredataimport.createVisitInformationEncounters");
		map.put("module/tebowcuredataimport/createPatientNoteEncounters.form", "tebowcuredataimport.createPatientNoteEncounters");
		
		return map;
	}
	
}
