package de.tuhh.tusp.app.fame.dao;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.InputType;
import de.tuhh.tusp.utils.BaseDao;

public interface InputTypeDao extends BaseDao<InputType> {

	/**
	 * Holt einne Inputtype anhand des Names
	 *
	 * @param name
	 * @return InputType
	 */
	InputType fetchByName(String string);

	/**
	 * Holt einne Inputtype anhand des Uniquenames
	 *
	 * @param uniquename
	 * @return InputType
	 */
	InputType fetchByUniqueName(InputTypeEnum uniquename);

	/**
	 *
	 * Gibt die ID des InputTypes wieder aus einem internen Cache wieder
	 *
	 * @param uniquename
	 * @return die id
	 */
	Long getId(InputTypeEnum uniquename);

}
