package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import de.tuhh.tusp.saeulen.pojo.Zustimmungen;
import de.tuhh.tusp.utils.BaseDao;

public interface ZustimmungenDao extends BaseDao<Zustimmungen> {

	List<Zustimmungen> findZustimmungenFromUID(Long UID);

}
