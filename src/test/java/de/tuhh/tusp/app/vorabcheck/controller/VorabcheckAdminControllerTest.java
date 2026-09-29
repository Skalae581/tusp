package de.tuhh.tusp.app.vorabcheck.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import de.tuhh.tusp.app.vorabcheck.controllers.VorabcheckAdminController;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.app.vorabcheck.service.StudiengangsauswahlService;
import de.tuhh.tusp.app.vorabcheck.service.StudiengangsauswahlServiceImpl;
import de.tuhh.tusp.config.ApplicationIntegrationTestControllerConfig;
import de.tuhh.tusp.utils.TestUtils;

@ContextConfiguration(classes = { ApplicationIntegrationTestControllerConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class VorabcheckAdminControllerTest {

	private String shorttext = "Test-Stg";
	private String stgId = "21";

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@InjectMocks
	private StudiengangsauswahlServiceImpl injectService;

	@Autowired
	private StudiengangsauswahlService studiengangsauswahlService;

	@Autowired
	private VorabcheckAdminController adminController;

	@Before
	public void before() {

		mockMvc = webAppContextSetup(this.context).build();
	}

	@Test
	public void homeTest() throws Exception {

		String url = "/vorabcheck/admin/studiengaenge";
		mockMvc	.perform(get(url))

				// .andDo(MockMvcResultHandlers.print())

				.andExpect(status().isOk())

				.andExpect(model().attribute("bereichStgIds", instanceOf(List.class)))

				.andExpect(model().attribute("stg", instanceOf(List.class)))

				.andExpect(model().attribute("locale", instanceOf(Locale.class)))

				.andExpect(model().attribute("archivOriginIds", instanceOf(List.class)))

				.andExpect(view().name("vorabcheck/admin/studiengaenge"));
	}

	@Test
	public void insertTest() throws Exception {

		studiengangsauswahlService = TestUtils.mockAutowiredField(adminController, StudiengangsauswahlService.class);
		Studiengangsauswahl s = new Studiengangsauswahl();
		s.setName("Test-Stg");
		s.setProzentsatz(75);
		s.setStandardkz("");
		when(studiengangsauswahlService.fetchById(any())).then(x -> s);

		String url = "/vorabcheck/admin/insert?stgId=" + stgId + "&shorttext=" + shorttext;
		mockMvc	.perform(get(url))

				// .andDo(MockMvcResultHandlers.print())

				.andExpect(status().isOk())

				.andExpect(model().attribute("stgId", instanceOf(Long.class)))

				.andExpect(model().attribute("stgName", instanceOf(String.class)))

				.andExpect(model().attribute("bereiche", instanceOf(List.class)))

				.andExpect(model().attribute("faecher", instanceOf(List.class)))

				.andExpect(model().attribute("leistungen", instanceOf(List.class)))

				.andExpect(model().attribute("prozentsatz", instanceOf(Integer.class)))

				.andExpect(view().name("vorabcheck/admin/insert"));
	}

	@Test
	public void show_mitExtraTest() throws Exception {

		studiengangsauswahlService = TestUtils.mockAutowiredField(adminController, StudiengangsauswahlService.class);
		Studiengangsauswahl s = new Studiengangsauswahl();
		s.setName("Test-Stg");
		s.setProzentsatz(75);
		s.setStandardkz("");
		s.setExtrakz("N");
		s.setExtratitel("Test");
		when(studiengangsauswahlService.fetchById(any())).then(x -> s);

		String url = "/vorabcheck/admin/show?stgId=" + stgId + "&shorttext=" + shorttext;
		mockMvc	.perform(get(url))

				// .andDo(MockMvcResultHandlers.print())

				.andExpect(status().isOk())

				.andExpect(model().attribute("stgId", instanceOf(Long.class)))

				.andExpect(model().attribute("stgName", instanceOf(String.class)))

				.andExpect(model().attribute("bereich", instanceOf(List.class)))

				.andExpect(model().attribute("fach", instanceOf(List.class)))

				.andExpect(model().attribute("leistungen", instanceOf(List.class)))

				.andExpect(model().attribute("prozentsatz", instanceOf(Integer.class)))

				.andExpect(model().attribute("extrakz", instanceOf(String.class)))

				.andExpect(model().attribute("extraTitel", instanceOf(String.class)))

				.andExpect(view().name("vorabcheck/admin/show"));

	}

	@Test
	public void show_ohneExtra_Test() throws Exception {

		studiengangsauswahlService = TestUtils.mockAutowiredField(adminController, StudiengangsauswahlService.class);
		Studiengangsauswahl s = new Studiengangsauswahl();
		s.setName("Test-Stg");
		s.setProzentsatz(75);
		s.setStandardkz("");
		when(studiengangsauswahlService.fetchById(any())).then(x -> s);

		String url = "/vorabcheck/admin/show?stgId=" + stgId + "&shorttext=" + shorttext;
		mockMvc	.perform(get(url))

				// .andDo(MockMvcResultHandlers.print())

				.andExpect(status().isOk())

				.andExpect(model().attribute("stgId", instanceOf(Long.class)))

				.andExpect(model().attribute("stgName", instanceOf(String.class)))

				.andExpect(model().attribute("bereich", instanceOf(List.class)))

				.andExpect(model().attribute("fach", instanceOf(List.class)))

				.andExpect(model().attribute("leistungen", instanceOf(List.class)))

				.andExpect(model().attribute("prozentsatz", instanceOf(Integer.class)))

				.andExpect(view().name("vorabcheck/admin/show"));
	}
}
