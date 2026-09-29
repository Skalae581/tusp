package de.tuhh.tusp.utils;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import de.tuhh.tusp.config.ApplicationIntegrationTestControllerConfig;
import de.tuhh.tusp.global.dao.ConfigDao;
import de.tuhh.tusp.global.pojo.Config;
import de.tuhh.tusp.global.service.ConfigService;
import net.jcip.annotations.NotThreadSafe;

@ContextConfiguration(classes = { ApplicationIntegrationTestControllerConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@NotThreadSafe
public class XssFilterIT {

	private static Logger logger = LogManager.getLogger(XssFilterIT.class);

	private static final String CONFIG_KEY = "TEST_KEY";
	private static final String GOOD = "some value";
	private static final String EVIL = "<script>123</script>";

	@Autowired
	private WebApplicationContext context;

	private ConfigDao configDao = new ConfigDao() {

		long id = 1;
		List<Config> list = new ArrayList<>();

		@Override
		public int delete(Long id) {

			int size = list.size();
			list.remove(fetchById(id));
			return size - list.size();
		}

		@Override
		public void deleteAll() {

			list.clear();
		}

		@Override
		public Config fetchById(Long id) {

			if (id == null) {
				return null;
			}

			for (Config c : list) {
				if (id.equals(c.getId())) {
					return c;
				}
			}
			return null;
		}

		@Override
		public Config fetchByKey(String key) {

			for (Config c : list) {
				if (c.getKey().equals(key)) {
					return c;
				}
			}
			return null;
		}

		@Override
		public List<Config> findAll() {

			return list;
		}

		@Override
		public List<Config> findByKeyPrefix(String prefix) {

			throw new NotImplementedException();
		}

		@Override
		public int getCount() {

			return list.size();
		}

		@Override
		public Long insert(Config object) {

			list.add(object);
			object.setId(id);
			return id++;
		}

		@Override
		public List<Config> search(String keyPattern, String valuePattern) {

			throw new NotImplementedException();
		}

		@Override
		public void testConnection() {

			throw new NotImplementedException();
		}

		@Override
		public void update(Config object) {

		}
	};

	@Autowired
	private ConfigService configService;

	private MockMvc mockMvc;
	private Config configValue;

	@Before
	public void before()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		mockMvc = webAppContextSetup(this.context).addFilters(new XssFilter(context)).build();

		Field field = configService.getClass().getDeclaredField("dao");
		field.setAccessible(true);
		field.set(configService, configDao);
	}

	@Test
	public void doFilterTest() throws Exception {

		// Testdaten vorbereiten
		configService.get(CONFIG_KEY, "", true);
		configValue = configDao.fetchByKey(CONFIG_KEY);

		String url = "/global/admin/config/edit/?prefix=&id=" + configValue.getId();
		logger.debug(url);
		ResultActions result = mockMvc.perform(post(url).param("value", GOOD + EVIL));

		result.andExpect(status().isOk());

		configValue = configDao.fetchByKey(CONFIG_KEY);
		assertEquals(GOOD, configValue.getValue());
	}
}
