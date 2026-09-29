package de.tuhh.tusp.webservice.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.dao.NotificationcategoryDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Notificationcategory;

@Component
public class NotificationcategoryServiceImpl extends AbstractHioSyncImpl implements NotificationcategoryService {

	@Autowired
	protected NotificationcategoryDao dao;

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Notificationcategory();
	}

	@Override
	protected String getValueClass() {

		return "NotificationCategoryValue";
	}

	@Override
	public int orderNum() {

		return 5;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

	}

}
