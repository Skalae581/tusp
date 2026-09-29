package de.tuhh.tusp.app.fame.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;

public class VorgangCsvCreator implements View {

	@Override
	public String getContentType() {

		return "application/csv";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void render(	Map<String, ?> model, HttpServletRequest request,
						HttpServletResponse response) throws Exception {

		response.setContentType(getContentType());
		response.setHeader("Content-Disposition", "attachment;filename=\"FaME-Export.csv\"");

		List<Vorgang> cases = (List<Vorgang>) model.get("cases");
		Map<Vorgang, List<ExtendedInput>> values = (Map<Vorgang, List<ExtendedInput>>) model.get("values");

		for (Vorgang v : cases) {

			for (ExtendedInput input : values.get(v)) {
				String val = input.getEingabe();

				// Label umwandelt
				if (InputTypeEnum.SELECT == input.getInputTypeUniqName()) {
					String[] lines = input.getOptions().split("\n");
					for (String line : lines) {
						String[] parts = line.split(";");
						if (parts[0].equals(val)) {
							val = parts[1];
							break;
						}
					}
				}

				// Zeichen escapen
				if (val != null) {
					val = val.replaceAll("\"", "\"\"");
				}

				response.getWriter().print("\"" + val + "\";");
			}
			response.getWriter().println();
		}
		response.flushBuffer();
	}

}
