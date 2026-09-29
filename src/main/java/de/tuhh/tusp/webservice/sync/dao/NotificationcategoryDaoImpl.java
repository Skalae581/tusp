package de.tuhh.tusp.webservice.sync.dao;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Notificationcategory;

@Component
public class NotificationcategoryDaoImpl extends BaseDaoImpl<Notificationcategory>
		implements NotificationcategoryDao {

	/**
	 * Instanziert das Dao
	 */
	public NotificationcategoryDaoImpl() {
		super(	Notificationcategory.class, Tables.VALUESERVICE_NOTIFICATIONCATEGORYVALUE,
				Tables.VALUESERVICE_NOTIFICATIONCATEGORYVALUE.ID, false);
	}
}
