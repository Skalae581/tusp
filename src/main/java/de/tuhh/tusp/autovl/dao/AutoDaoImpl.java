package de.tuhh.tusp.autovl.dao;

import static org.jooq.generated.Tables.AVL_AUTO;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.autovl.pojo.Auto;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class AutoDaoImpl extends BaseDaoImpl<Auto> implements AutoDao {

	public AutoDaoImpl() {
		super(Auto.class, AVL_AUTO, AVL_AUTO.ID);
	}

}
