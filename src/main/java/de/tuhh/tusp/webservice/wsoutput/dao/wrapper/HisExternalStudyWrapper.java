package de.tuhh.tusp.webservice.wsoutput.dao.wrapper;

import de.his.ws.studentservice.StudentExternalsType;
import de.his.ws.studentservice.StudentexternalDto;

public class HisExternalStudyWrapper {

	private StudentExternalsType hisType;

	/**
	 * Construct
	 *
	 * @param result
	 *            Das HIO Objekt
	 */
	public HisExternalStudyWrapper(StudentExternalsType result) {
		hisType = result;
	}

	/**
	 * Gibt die MTKNR der Hochschule zurück oder null, wenn nicht gefunden.
	 *
	 * @param universityId
	 * @return MTKNR / null
	 */
	public String getRegistrationNumber(Long universityId) {

		if (universityId == null) {
			return null;
		}

		for (StudentexternalDto r : hisType.getStudentExternal()) {
			if (universityId.equals(r.getUniversityId())) {
				return r.getRegistrationnumber();
			}
		}
		return null;
	}
}
