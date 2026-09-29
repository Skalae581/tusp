package de.tuhh.tusp.app.fame.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.exception.StatusuebergangVerbotenException;
import de.tuhh.tusp.app.fame.exception.ValidationException;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.global.service.ConfigService;

@Component
public class InputValidationHelperImpl implements InputValidationHelper {

	private static Logger logger = LogManager.getLogger(InputValidationHelperImpl.class);

	private static final String FUNCTIONS = "var ArrayList = Java.type('java.util.ArrayList');\nvar errors = new ArrayList(); \n"

			+ "var HashMap = Java.type('java.util.HashMap');\nvar changedValues = new HashMap(); \n"

			+ "function valueOf(id) {return eval(id);}\n"

			+ "function fail(message) {errors.add(message);}\n"

			+ "function empty(value) {return value === null || value === '' || value === 0;}\n"

			+ "function allowOptions(fieldId, options) {\n"

			+ "if(options==='all'){return;}\n"

			+ "for(i = 0; i<options.length; i++) {if(options[i] == valueOf(fieldId)) {return;}}\n"

			+ "fail(getName(fieldId) + ' enthält einen nicht erlaubten Wert.');"

			+ "}\n"

			+ "function getName(id) {return names[id]}\n"

			+ "function log(msg) {logger.debug(msg)}\n"

			+ "function setValue(id, value) {changedValues.put(id, '' + value);}\n";

	@Autowired
	private ValueService valueService;

	@Autowired
	private FileService fileService;

	@Autowired
	private ConfigService config;

	@Autowired
	private VorgangService vorgangService;

	@Override
	public void validateAndSave(List<ExtendedInput> userInputs, List<ExtendedInput> allInputs, List<Status> status,
			Map<String, String[]> parameterMap, String validationCode, MultipartHttpServletRequest mrequest,
			Long nextStatusId) throws PflichtfeldException, RegexException, ValidationException, ScriptException,
			IOException, StatusuebergangVerbotenException {

		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		HashMap<String, String> names = new HashMap<>();

		for (Status s : status) {
			String id = "status_" + s.getId();
			logger.debug("put(" + id + ", " + s.getId() + ")");
			engine.put(id, s.getId());
		}

		for (ExtendedInput input : allInputs) {
			String id = "value_" + input.getId();
			String[] value = parameterMap.get(id);
			if (value != null) {
				logger.debug("put(" + id + ", " + value[0] + ")");
				engine.put(id, value[0]);
			} else {
				logger.debug("put(" + id + ", " + input.getEingabe() + ")");
				engine.put(id, input.getEingabe());
			}
			names.put(id, input.getName());
		}

		engine.put("statusId", parameterMap.get("statusId") != null ? parameterMap.get("statusId")[0] : null);
		names.put("statusId", "Status");
		engine.put("names", names);
		engine.put("logger", logger);

		String code = FUNCTIONS + validationCode;
		if (logger.isDebugEnabled()) {
			String[] lines = code.split("\n");
			for (int i = 0; i < lines.length; i++) {

				logger.debug(i + 1 + "\t" + lines[i].replaceAll("\\s+$", ""));
			}
		}
		engine.eval(code);
		@SuppressWarnings("unchecked")
		ArrayList<String> errors = (ArrayList<String>) engine.get("errors");

		@SuppressWarnings("unchecked")
		HashMap<String, String> changedValues = (HashMap<String, String>) engine.get("changedValues");

		if (errors != null && errors.size() > 0) {
			logger.debug(errors);
			throw new ValidationException(errors);
		}

		// Alle Werte die der Nutzer ändern darf in die parameterMap schreiben
		for (String id : changedValues.keySet()) {
			for (ExtendedInput input : userInputs) {
				if (("value_" + input.getId()).equals(id)) {
					parameterMap.put(id, new String[] { changedValues.get(id) });
					input.setRecht(InputRechteEnum.WRITE);
				}
			}
		}

		if (!allInputs.isEmpty()) {
			Vorgang vorgang = vorgangService.fetchById(allInputs.get(0).getVorgangId());
			vorgangService.statusUebergang(vorgang, nextStatusId);
		}

		Long maxFileSize = config.get("de.tuhh.tusp.app.fame.max_upload_size_kb", 1024 * 10L, true);
		for (ExtendedInput input : userInputs) {
			String paramName = "value_" + input.getId();

			if (input.getInputTypeUniqName() == InputTypeEnum.FILE_NAME) {
				if (mrequest == null) {
					continue;
				}

				MultipartFile file = mrequest.getFile(paramName);
				if (file == null) {
					continue;
				}

				if (file.getSize() > maxFileSize) {
					errors.add("File size exceeded " + file.getOriginalFilename() + " (max allowed "
							+ maxFileSize / 1024 + "MB)");
					throw new ValidationException(errors);
				}
				if (file != null && file.getSize() > 0) {
					fileService.saveFile(input.getVorgangId(), input.getId(), file);
				}
				continue;
			}

			String[] value = parameterMap.get(paramName);
			if (value != null) {
				valueService.save(input, value[0], true);
			}
		}

		// Zuletzt aktualisiert setzen
		if (!userInputs.isEmpty()) {
			valueService.setLetzterBearbeiter(userInputs.get(0).getVorgangId());
			valueService.setLetzteBearbeitung(userInputs.get(0).getVorgangId());
		}
	}

}
