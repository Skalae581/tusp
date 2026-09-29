package de.tuhh.tusp.stu.timsl.dao;

import java.util.List;

import de.tuhh.tusp.stu.timsl.pojo.TimSonderStudent;
import de.tuhh.tusp.utils.BaseDao;

public interface TimSonderStudentDao extends BaseDao<TimSonderStudent> {

	/**
	 * Liefert die Datensätze die gerade gültig sind
	 * 
	 * @return Studneten
	 */
	List<TimSonderStudent> findValid();

}
