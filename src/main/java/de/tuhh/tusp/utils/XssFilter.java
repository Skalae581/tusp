package de.tuhh.tusp.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

/**
 * Klasse wird als Filter vor jedem Request ausgeführt und säubert die Eingaben um XSS und CSRF abzufangen.
 *
 * @author rztjm
 *
 */
public final class XssFilter extends GenericFilterBean {

	private static List<String> disabledPaths = new ArrayList<>();

	/**
	 * Deaktiviert den XSS-Filter für eine URI
	 *
	 * @param path
	 *            Der Request-Pfad z.B. /global/admin/config/save_item
	 */
	public static void disableFor(String path) {

		disabledPaths.add(path);
	}

	ApplicationContext context;

	/**
	 * Erstellt eine Instanz
	 * 
	 * @param context
	 */
	public XssFilter(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)	throws IOException,
																								ServletException {

		String path = new UrlPathHelper().getPathWithinApplication((HttpServletRequest) request);
		if (disabledPaths.contains(path)) {
			chain.doFilter(request, response);
		}
		else {
			chain.doFilter(new XssFilteredRequest(request, context.getBean(XssSanitizer.class)), response);
		}
	}
}