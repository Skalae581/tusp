package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import de.tuhh.tusp.saeulen.pojo.Saeuleaktualisierung;
import de.tuhh.tusp.utils.BaseDao;

public interface SaeuleaktualisierungDao extends BaseDao<Saeuleaktualisierung> {

	List<Saeuleaktualisierung> findByUID(Long UID);

	/**
	 * finde alle Aktualisierungsn für die Säule
	 *
	 * @param zielQuelleId
	 * @return
	 */
	List<Saeuleaktualisierung> findeAktualisierungenFuerDieQuelle(Long zielQuelleId);

}
