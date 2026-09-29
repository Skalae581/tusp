package de.tuhh.tusp.webservice.sync.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.BasePojo;

public abstract class WebserviceSyncImpl implements WebserviceSync {

	private static Logger logger = LogManager.getLogger(WebserviceSyncImpl.class);

	/**
	 * Aktualisiert und erzeugt Datensätze in der Datenbank entsprechend den Daten
	 * aus dem Webservice.
	 *
	 * @param serviceData Daten aus dem Webservice
	 * @param dao         Das Dao, dass die Daten verwaltet
	 * @param cacheList   Daten, die in der Db liegen
	 * @param daoClass    Name der Pojo-Klasse
	 */
	@SuppressWarnings("unchecked")
	protected void insertOrUpdateData(List<BasePojo> serviceData, BaseDao<?> dao, List<BasePojo> cacheList,
			String daoClass) {

		int updated = 0;
		int inserted = 0;
		int unchanged = 0;

		for (BasePojo c : serviceData) {

			// Suche ob Webservice-Eintrag in der DB vorhanden ist
			Optional<BasePojo> pojo = cacheList.stream().filter(x -> x.getId().equals(c.getId())).findAny();

			if (pojo.isPresent()) {
				BasePojo basePojo = pojo.get();
				if (!basePojo.equals(c)) {
					((BaseDao<BasePojo>) dao).update(c);
					logger.debug("Updated " + daoClass + " " + c.getId());
					updated++;
				} else {
					unchanged++;
					logger.debug("Unchanged " + daoClass + " " + c.getId());
				}
			} else {
				c.setValid(true);
				((BaseDao<BasePojo>) dao).insert(c);
				inserted++;
				logger.debug("Inserted  " + daoClass + " " + c.getId());
			}
		}
		logger.debug(this.getClass() + ": " + inserted + " new, " + updated + " updated and " + unchanged
				+ " unchanged Objects");
	}

	/**
	 * Setzt alle Daten, die nicht mehr vom Webservice geliefert wurden invalid
	 *
	 * @param serviceData Daten aus dem Webservice
	 * @param dao         Das Dao, dass die Daten verwaltet
	 * @param cacheList   Daten die in der Datenbank liegen
	 * @param daoClass    Name der Pojo-Klasse
	 */
	@SuppressWarnings("unchecked")
	protected void invalidateExpiredData(List<BasePojo> serviceData, BaseDao<?> dao, List<BasePojo> cacheList,
			String daoClass) {

		int invalidated = 0;
		for (BasePojo cachedPojo : cacheList) {
			// Suche nach Db-Einträgen die nicht in Service-Daten waren
			Optional<BasePojo> basePojo = serviceData.stream().filter(x -> x.getId().equals(cachedPojo.getId()))
					.findAny();

			if (!basePojo.isPresent()) {

				if (!(cachedPojo.isValid() == null)) {
					if (cachedPojo.isValid()) {
						cachedPojo.setValid(false);
						((BaseDao<BasePojo>) dao).update(cachedPojo);
						logger.debug("Invalidated " + daoClass + " " + cachedPojo.getId());
						invalidated++;
					}
				} else {
					cachedPojo.setValid(false);
					((BaseDao<BasePojo>) dao).update(cachedPojo);
					logger.debug("Invalidated " + daoClass + " " + cachedPojo.getId());
					invalidated++;
				}

			}
		}
		logger.debug(this.getClass() + ": " + invalidated + " invalidated");
	}

	@Override
	public abstract void sync();

	/**
	 * Aktualisiert die Daten und setzt alle Daten die nicht mehr aus dem Service
	 * kommen invalid.
	 *
	 * @param serviceData Daten aus dem Webservice
	 * @param dao         Das Dao, dass die Daten verwaltet
	 */
	@SuppressWarnings("unchecked")
	protected void syncData(List<BasePojo> serviceData, BaseDao<?> dao) {

		String daoClass = dao.getClass().getSimpleName().replace("DaoImpl", "");
		logger.debug("Updating " + daoClass + "...");

		List<BasePojo> cacheList = (List<BasePojo>) dao.findAll();

		insertOrUpdateData(serviceData, dao, cacheList, daoClass);

		invalidateExpiredData(serviceData, dao, cacheList, daoClass);

		logger.info("Updating " + daoClass + " Completed!");
	}

	/**
	 * Aktualisiert die Daten ohne die Datensätze als invalid zu markieren
	 *
	 * @param serviceData Daten die aus dem Webservice kommen
	 * @param dao         Das Dao, dass die Daten verwaltet
	 */
	@SuppressWarnings("unchecked")
	protected void syncDataWithoutExpiery(List<BasePojo> serviceData, BaseDao<?> dao) {

		String daoClass = dao.getClass().getSimpleName().replace("DaoImpl", "");
		logger.debug("Updating " + daoClass + "...");

		List<BasePojo> cacheList = (List<BasePojo>) dao.findAll();

		insertOrUpdateData(serviceData, dao, cacheList, daoClass);

		logger.info("Updating " + daoClass + " Completed!");
	}
}
