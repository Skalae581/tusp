package de.tuhh.tusp.app.precheck.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Setzt Cookie Werte für die Filter
 *
 * @author rztjj
 *
 */
@Component
public class CookieUtils {

	private static final int DEFAULT_PAGE_ELEMENTS = 15;
	private static Logger logger = LogManager.getLogger(CookieUtils.class);

	private Filter emptyFilter() {

		Filter filter = new Filter();

		filter.setCountryValue("0");
		filter.setStatusValue("0");
		filter.setStudyValue("0");
		filter.setDoneValue("b");
		filter.setElementList(DEFAULT_PAGE_ELEMENTS);
		filter.setSearch("");
		filter.setOrder(null);
		filter.setDoubles(false);
		filter.setDoublesMail(false);

		return filter;
	}

	public Filter getCookieFilter(HttpServletResponse response, HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			Filter filter = emptyFilter();
			setCookieValues("precheck_filter", filter.toString(), response);
			return filter;
		}

		Cookie filterCookie = null;
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];

			if (c.getName().equals("precheck_filter")) {
				filterCookie = c;
				break;
			}
		}

		Filter filter = restoreFilter(filterCookie);
		setCookieValues("precheck_filter", filter.toString(), response);
		return filter;
	}

	public String[] getCookieValues(String data) {

		logger.info("PrecheckCookie: " + data);
		String[] values = data.split("\\|");
		return values;
	}

	private Filter restoreFilter(Cookie filterCookie) {

		if (filterCookie == null) {
			return emptyFilter();
		}

		try {
			String[] cookieData = getCookieValues(filterCookie.getValue());

			Filter filter = new Filter();

			filter.setCountryValue(cookieData[0].trim());
			filter.setStatusValue(cookieData[1].trim());
			filter.setStudyValue(cookieData[2].trim());
			filter.setDoneValue(cookieData[3].trim());
			filter.setElementList(Integer.parseInt(cookieData[4].trim()));
			filter.setSearch(cookieData[5].trim());
			filter.setOrder(cookieData[6].trim());
			filter.setDoubles(Boolean.parseBoolean(cookieData[7].trim()));
			filter.setDoublesMail(Boolean.parseBoolean(cookieData[8].trim()));

			return filter;
		} catch (Exception e) {
			logger.error("Cookie kaputt", e);
			return emptyFilter();
		}
	}

	public void setCookieValues(String cookieName, String data, HttpServletResponse response) {

		Cookie filterCookie = new Cookie(cookieName, data);
		filterCookie.setMaxAge(48 * 60 * 60);

		response.addCookie(filterCookie);
	}
}
