package de.tuhh.tusp.webservice.wsoutput.dao;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.TuHHStg;

/**
 * Verwaltet die Übersetzungstabelle für Studiengangsbezeichnungen von TUHH unid UHH
 * 
 * @author rztjj
 *
 */
public interface TuHHStgDao extends BaseDao<TuHHStg> {

	/**
	 * Holt einen Studiengangsbezeichner anhand der astst-Id
	 *
	 * @param astatUni
	 * @return die Bezeichnung
	 */
	String getStgByAstatUni(String astatUni);

}