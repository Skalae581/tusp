package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import de.tuhh.tusp.saeulen.pojo.Konfigurationstransfer;
import de.tuhh.tusp.utils.BaseDao;

public interface KonfigurationstransferDao extends BaseDao<Konfigurationstransfer> {

	List<Konfigurationstransfer> findByUID(Long UID);

	List<Konfigurationstransfer> findeKonfigurationstransferFuerDieQuelle(int saueleId);

}
