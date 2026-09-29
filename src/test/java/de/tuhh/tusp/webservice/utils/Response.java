package de.tuhh.tusp.webservice.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;

@Data
class Response {

	private static Pattern KEY_PATTERN = Pattern.compile("^(\\d+) (.*)");
	private Integer requestNr;
	private String key;
	private Pattern regex;
	private Object response;

	public Response(String key, Object response) {

		Matcher matcher = KEY_PATTERN.matcher(key);

		if (matcher.matches()) {
			requestNr = Integer.parseInt(matcher.group(1));
			this.key = matcher.group(2);
		}
		else {
			requestNr = null;
			this.key = key;
		}

		this.regex = Pattern.compile(this.key	.replace("(", "\\(").replace(")", "\\)").replace("{", "\\{")
												.replace("}", "\\}").replace("|", "\\|"));
		this.response = response;
	}
}
