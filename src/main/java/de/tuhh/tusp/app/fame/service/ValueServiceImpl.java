package de.tuhh.tusp.app.fame.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.dao.ValueDao;
import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageContainer;

@Component
public class ValueServiceImpl implements ValueService {

	@Autowired
	private ValueDao dao;

	@Autowired
	private MessageContainer messageContainer;

	@Autowired
	private LogService logService;

	@Autowired
	private AuthorityUtils authUtils;

	@Override
	public int delete(Long valueId) {

		return dao.delete(valueId);
	}

	@Override
	public Value fetchById(Long id) {

		return dao.fetchById(id);
	}

	/**
	 * Prüft ob ein Feld beschreibbar ist
	 *
	 * @param input
	 * @param value
	 * @param connectorReadonly
	 * @return true wenn es beschrieben werden darf
	 */
	private boolean isWritable(ExtendedInput input, Value value, boolean connectorReadonly) {

		// Connector-Kram ist readonly
		if (connectorReadonly && (InputTypeEnum.CONNECTOR_TEXT.equals(input.getInputTypeUniqName())
				|| InputTypeEnum.CONNECTOR_FILE_NAME.equals(input.getInputTypeUniqName()))) {
			return false;
		}

		// Berechtigung prüfen
		if (InputRechteEnum.SET.equals(input.getRecht()) && value == null) {
			return true;
		}

		// Berechtigung prüfen
		if (InputRechteEnum.WRITE.equals(input.getRecht())) {
			return true;
		}

		return false;
	}

	@Override
	public Long save(ExtendedInput input, String wert, boolean connectorReadonly)	throws PflichtfeldException,
																					RegexException {

		// Bisheriges Value Objekt
		Value value = dao.fetchByVorgangAndInput(input.getVorgangId(), input.getId());

		if (!isWritable(input, value, connectorReadonly)) {
			return null;
		}

		// Checkboxes deaktiviert setzen
		if (InputTypeEnum.CHECKBOX.equals(input.getInputTypeUniqName()) && wert == null) {
			wert = "0";
		}

		if (wert == null) {
			return 0l;
		}

		// Pflichtfeldprüfung
		if (input.getPflicht() != null && input.getPflicht() == 1 && (wert == null || "".equals(wert))) {
			String msg = String.format(messageContainer.getString("fame.user.pflichtfeld_empty"), input.getName());
			throw new PflichtfeldException(msg);
		}

		// Regex prüfen
		if (input.getRegex() != null && !input.getRegex().isEmpty() && wert != null
				&& !wert.matches(input.getRegex())) {
			String msg = String.format(	messageContainer.getString("fame.user.regex_no_match"), input.getName(),
										input.getRegex());
			throw new RegexException(msg);
		}

		// Insert or Update
		Long id;
		if (value == null) {
			value = new Value();
			value.setId(0L);
			value.setEingabe(wert);
			value.setInputId(input.getId());
			value.setVorgangId(input.getVorgangId());
			value.setCreateTime(new Date());
			id = dao.insert(value);
			if (!"".equals(wert) && !"0".equals(wert)) {
				logService.logValueChange(input, value);
			}
		}
		else {
			id = value.getId();
			if (wert.equals(value.getEingabe())) {
				return id;
			}
			value.setEingabe(wert);
			dao.update(value);
			logService.logValueChange(input, value);
		}
		return id;
	}

	@Override
	public void setLetzteBearbeitung(Long vorgangId) {

		List<Value> vals = dao.fetchByVorgangAndInputType(vorgangId, InputTypeEnum.BEARB_DATE);
		for (Value val : vals) {
			val.setEingabe(CalendarUtils.format(new Date(), CalendarUtils.DATETIMEFORMAT_GERMAN));
			if (val.getId() != null) {
				dao.update(val);
			}
			else {
				val.setCreateTime(new Date());
				dao.insert(val);
			}
		}
	}

	@Override
	public void setLetzterBearbeiter(Long vorgangId) {

		List<Value> vals = dao.fetchByVorgangAndInputType(vorgangId, InputTypeEnum.BEARB_NAME);
		for (Value val : vals) {
			val.setEingabe(authUtils.getCurrentUserDisplayName());
			if (val.getId() != null) {
				dao.update(val);
			}
			else {
				val.setCreateTime(new Date());
				dao.insert(val);
			}
		}
	}

}
