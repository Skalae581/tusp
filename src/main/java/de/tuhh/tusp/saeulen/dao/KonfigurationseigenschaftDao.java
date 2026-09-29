package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import de.tuhh.tusp.saeulen.pojo.Konfigurationseigenschaft;
import de.tuhh.tusp.utils.BaseDao;

public interface KonfigurationseigenschaftDao extends BaseDao<Konfigurationseigenschaft> {

	List<Konfigurationseigenschaft> findeAlleAndere();

	List<Konfigurationseigenschaft> findeAlleFuerDieQuelle();

	String findNamebyId(Long id);

}
