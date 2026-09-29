package de.tuhh.tusp.webservice.wsoutput.dao.wrapper;

import de.his.ws.studentservice201812.DegreeProgramProgress;

public class DegreeProgramProgressWrapper {

	private DegreeProgramProgress degreeProgramProgres;

	/**
	 * Construct
	 *
	 * @param dpp
	 */
	public DegreeProgramProgressWrapper(DegreeProgramProgress dpp) {
		this.degreeProgramProgres = dpp;

	}

	public Long getCourseOfStudyId() {

		return degreeProgramProgres.getCourseOfStudyId();
	}

	public String getStatus() {

		return degreeProgramProgres.getStudystatus();
	}

	public Long getStudysemester() {

		return degreeProgramProgres.getStudysemester().longValueExact();
	}

	@Override
	public String toString() {

		return "DegreeProgramProgressWrapper(periodId=" + degreeProgramProgres.getPeriodId() + ", year="
				+ degreeProgramProgres.getYear() + ", semester=" + degreeProgramProgres.getStudysemester() + ", status="
				+ degreeProgramProgres.getStudystatus() + ")";

	}
}
