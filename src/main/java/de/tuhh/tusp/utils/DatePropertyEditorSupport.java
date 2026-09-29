package de.tuhh.tusp.utils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * Klasse dient Spring zum Verknüpfen von Properties der Pojos aus dem Controller mit der View.
 *
 * @author rztjm
 *
 */
public class DatePropertyEditorSupport extends PropertyEditorSupport {

	@Override
	public String getAsText() {

		try {
			return CalendarUtils.dateToDatumsString((Date) getValue());
		}
		catch (NullPointerException e) { /* ok */ } // NOPMD
		return "";
	}

	@Override
	public void setAsText(String value) {

		try {
			setValue(CalendarUtils.stringToDate(value));
		}
		catch (ParseException e) {
			setValue(null);
		}
	}

}
