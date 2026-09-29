package de.tuhh.tusp.autovl.dao;

import static org.jooq.generated.Tables.AVL_USER;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.autovl.pojo.AvlUser;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class AvlUserDaoImpl extends BaseDaoImpl<AvlUser> implements AvlUserDao {

	public AvlUserDaoImpl() {
		super(AvlUser.class, AVL_USER, AVL_USER.ID);
	}
}
