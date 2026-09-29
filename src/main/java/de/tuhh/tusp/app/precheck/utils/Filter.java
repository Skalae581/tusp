package de.tuhh.tusp.app.precheck.utils;

import java.io.Serializable;

import lombok.Data;

@Data
public class Filter implements Serializable {

	private static final long serialVersionUID = -1251544306933457636L;

	private String countryValue;
	private String statusValue;
	private String studyValue;
	private String doneValue;
	private int elementList;
	private String search;
	private String order;
	private boolean doubles;
	private boolean doublesMail;

	@Override
	public String toString() {

		return getCountryValue() + "|" + getStatusValue() + "|" + getStudyValue() + "|" + getDoneValue() + "|"
				+ getElementList() + "|" + getSearch() + "|" + getOrder() + "|" + isDoubles() + "|" + isDoublesMail();
	}
}
