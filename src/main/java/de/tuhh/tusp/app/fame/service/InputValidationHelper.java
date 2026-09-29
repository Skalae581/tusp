package de.tuhh.tusp.app.fame.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.exception.StatusuebergangVerbotenException;
import de.tuhh.tusp.app.fame.exception.ValidationException;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;

public interface InputValidationHelper {

	/**
	 * Prüft, ob die Daten valide sind und speichert sie falls sie valide sind.
	 *
	 * @param inputs
	 * @param allInputs
	 * @param parameterMap
	 *            Parameter des Reqests
	 * @param validationCode
	 *            Javascript-Code mit dem die Eingaben validiert werden sollen
	 * @param nextStatusId
	 * @throws PflichtfeldException
	 *             Sollte ein Pflichtfeld nicht gesetzt sein
	 * @throws RegexException
	 *             Sollte ein Feld nicht richtig formartiert sein
	 * @throws ValidationException
	 *             Wenn der validationCode auf Fehler hinweist
	 * @throws ScriptException
	 *             Wenn der validationCode selbst Fehler enthält
	 * @throws IOException
	 *             Bei Problemen mit Dateien
	 * @throws StatusuebergangVerbotenException
	 *             wenn dem Benutzer die Rechte fehlen
	 *
	 */
	void validateAndSave(	List<ExtendedInput> userInputs, List<ExtendedInput> allInputs, List<Status> status,
							Map<String, String[]> parameterMap, String validationCode,
							MultipartHttpServletRequest mrequest,
							Long nextStatusId)	throws PflichtfeldException, RegexException, ValidationException,
												ScriptException, IOException, StatusuebergangVerbotenException;

}
