package de.tuhh.tusp.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.stereotype.Component;

@Component
public class XssSanitizer {

	private static Logger logger = LogManager.getLogger(XssSanitizer.class);
	PolicyFactory policy;

	public XssSanitizer() {

		// Definition der Policy
		HtmlPolicyBuilder builder = new HtmlPolicyBuilder();

		// see
		// https://github.com/OWASP/java-html-sanitizer/blob/master/src/main/java/org/owasp/html/HtmlPolicyBuilder.java
		builder.allowStandardUrlProtocols();
		builder.allowCommonBlockElements();
		builder.allowCommonInlineFormattingElements();

		// Links erlauben
		builder.allowElements("a").allowAttributes("href").onElements("a").requireRelNofollowOnLinks();

		// Images erlauben
		builder.allowElements("img").allowAttributes("src", "alt").onElements("img");

		// Title erlauben
		builder.allowAttributes("title").globally();

		policy = builder.toFactory();
	}

	/**
	 * Bereinigt den Input potenziell gefährlichem Code.
	 *
	 * Erlaubt sind: title-Attribut auf allen HTML-Elementen; href-Attribut auf
	 * allen Link-Elementen; srv- und alt-Attribut auf allen Image-Elementen
	 *
	 * @param input
	 * @return Berienigte Eingaben
	 */
	public String sanitize(String input) {

		if (input == null || input.length() == 0) {
			return input;
		}

		String result = policy.sanitize(input);

		// Das @ überall erlauben
		result = result.replaceAll("&#64;", "@");

		if (!input.equals(result)) {
			logger.warn("XssFilter changed input from {" + input + "} to {" + result + "}");
		}

		return result;
	}
}
