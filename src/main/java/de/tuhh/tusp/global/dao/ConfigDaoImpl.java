package de.tuhh.tusp.global.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.pojo.Config;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class ConfigDaoImpl extends BaseDaoImpl<Config> implements ConfigDao {

	/**
	 * Construct
	 */
	public ConfigDaoImpl() {
		super(Config.class, GLOBAL_CONFIG, GLOBAL_CONFIG.ID);
	}

	@Override
	public Config fetchByKey(String key) {

		return context.select().from(GLOBAL_CONFIG).where(GLOBAL_CONFIG.KEY.equal(key)).fetchAnyInto(Config.class);
	}

	@Override
	public List<Config> findByKeyPrefix(String prefix) {

		if (prefix == null) {
			prefix = "";
		}

		return context	.select().from(GLOBAL_CONFIG).where(GLOBAL_CONFIG.KEY.like(prefix + "%"))
						.orderBy(GLOBAL_CONFIG.KEY).fetchInto(Config.class);
	}

	@Override
	public List<Config> search(String keyPattern, String valuePattern) {

		if (keyPattern == null) {
			keyPattern = "%";
		}
		if (valuePattern == null) {
			valuePattern = "%";
		}

		return context	.select().from(GLOBAL_CONFIG).where(GLOBAL_CONFIG.KEY.like(keyPattern))
						.and(GLOBAL_CONFIG.VALUE.like(valuePattern)).orderBy(GLOBAL_CONFIG.KEY).fetchInto(Config.class);
	}

	@Override
	public void testConnection() {

		context.query("SELECT 21").execute();
	}
}
