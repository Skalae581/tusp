package de.tuhh.tusp.global.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.config.PropertiesFactoryBean;

import de.tuhh.tusp.global.dao.ConfigDaoImpl;
import de.tuhh.tusp.global.pojo.Config;

@RunWith(MockitoJUnitRunner.class)
public class ConfigServiceImplTest {

	private static final String KEY = "key";

	@Mock
	private ConfigDaoImpl dao;

	@Mock
	private Properties properties;

	@InjectMocks
	private ConfigServiceImpl service;

	public ConfigServiceImplTest() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = mock(PropertiesFactoryBean.class);
		when(propertiesFactoryBean.getObject()).then(i -> properties);

		this.service = new ConfigServiceImpl(propertiesFactoryBean);
	}

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	private Config config(String value) {

		Config config = new Config();
		config.setKey(KEY);
		config.setValue(value);
		config.setId(0L);
		return config;
	}

	@Test
	public void getTest() {

		/*
		 * Überladung get(String)
		 */
		Mockito.when(dao.fetchByKey(KEY)).thenReturn(config("test"));
		assertEquals("test", service.get(KEY));

		// Fehlerfall: Key existiert nicht
		Mockito.when(dao.fetchByKey(KEY)).thenReturn(null);
		assertNull(service.get(KEY));

		/*
		 * Überladung get(String, String)
		 */
		Mockito.when(dao.fetchByKey(KEY)).thenReturn(config("test"));
		assertEquals("test", service.get(KEY, "asdf"));

		// Fehlerfall: Key existiert nicht
		Mockito.when(dao.fetchByKey(KEY)).thenReturn(null);
		assertEquals("asdf", service.get(KEY, "asdf"));

		/*
		 * Überladung get(String, int)
		 */
		Mockito.when(dao.fetchByKey(KEY)).thenReturn(config("15"));
		assertEquals(15, service.get(KEY, 16));

		// Fehlerfall: Key existiert nicht
		Mockito.when(dao.fetchByKey(KEY)).thenReturn(null);
		assertEquals(16, service.get(KEY, 16));

		// Fehlerfall: Wert nicht Konvertierbar
		Mockito.when(dao.fetchByKey(KEY)).thenReturn(config("wasd"));
		assertEquals(16, service.get(KEY, 16));

		/*
		 * Überladung get(String, int, boolean)
		 */
		// Fehlerfall: Wert existerit nicht in DB
		Mockito.when(dao.fetchByKey(KEY)).thenReturn(null);
		assertEquals(16, service.get(KEY, 16, true));
		verify(dao, Mockito.times(1)).insert(config("16"));
	}
}
