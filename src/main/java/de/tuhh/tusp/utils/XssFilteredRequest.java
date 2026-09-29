package de.tuhh.tusp.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Kapselt einen Request und stellt sicher, dass alle Zugriffe auf den Filter von XSS-Angriffen bereinigt werden.
 *
 * @author rztjm
 *
 */
class XssFilteredRequest extends HttpServletRequestWrapper {

	private XssSanitizer sanitizer;

	/**
	 * Erstellt eine Instanz
	 *
	 * @param request
	 * @param sanitizer
	 */
	public XssFilteredRequest(ServletRequest request, XssSanitizer sanitizer) {
		super((HttpServletRequest) request);

		this.sanitizer = sanitizer;
	}

	@Override
	public String getParameter(String paramName) {

		// hier könnten Ausnahmen zulassen werden, wenn es garnicht anders geht
		String value = super.getParameter(paramName);
		return sanitizer.sanitize(value);
	}

	@Override
	public String[] getParameterValues(String paramName) {

		String[] values = super.getParameterValues(paramName);

		if (values == null) {
			return null;
		}

		for (int index = 0; index < values.length; index++) {
			values[index] = sanitizer.sanitize(values[index]);
		}
		return values;
	}

}