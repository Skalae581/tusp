package de.tuhh.tusp.app.fame.dao;

import java.util.List;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.utils.BaseDao;

public interface ValueDao extends BaseDao<Value> {

	/**
	 * Löscht alle Eingaben eines Inputs
	 *
	 * @param id
	 */
	void deleteByInput(Long id);

	/**
	 * Holt den Wert eines Inputs
	 *
	 * @param vorgangId
	 * @param inputId
	 * @return den Wert oder null
	 */
	Value fetchByVorgangAndInput(Long vorgangId, Long inputId);

	/**
	 * Holt die Liste aller Inputs eines Typs
	 *
	 * @param vorgangId
	 * @param inputTypeId
	 * @return Die Liste
	 */
	List<Value> fetchByVorgangAndInputType(Long vorgangId, InputTypeEnum inputType);
}
