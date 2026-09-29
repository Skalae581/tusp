package de.tuhh.tusp.saeulen.dao;

import java.util.List;

import de.tuhh.tusp.saeulen.pojo.SaeuleUser;
import de.tuhh.tusp.utils.BaseDao;

public interface SaeuleUserDao extends BaseDao<SaeuleUser> {

	List<SaeuleUser> findEmailFromId(Long id);

	List<SaeuleUser> findIdByName(String name);

	List<SaeuleUser> getUsersForQuelle(String quelleId);

}
