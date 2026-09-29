package de.tuhh.tusp.app.vorabcheck.controller;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import de.tuhh.tusp.app.vorabcheck.VorabcheckConfig;
import de.tuhh.tusp.app.vorabcheck.controllers.VorabcheckUserController;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.app.vorabcheck.pojo.VorabInteressent;
import de.tuhh.tusp.app.vorabcheck.service.BewerberService;
import de.tuhh.tusp.app.vorabcheck.service.StudiengangsauswahlService;
import de.tuhh.tusp.config.ApplicationIntegrationTestControllerConfig;
import de.tuhh.tusp.utils.TestUtils;

@ContextConfiguration(classes = { ApplicationIntegrationTestControllerConfig.class, VorabcheckConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class VorabcheckUserControllerTest {

	private String stgName = "Test-Stg";
	private String stgId = "21";
	private String email = "test@test.de";
	private String datenspeichern;
	private String codenummer;
	private long zeitstempel;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	private StudiengangsauswahlService studiengangsauswahlService;

	@Autowired
	private VorabcheckUserController vorabcheckController;

	@Autowired
	private BewerberService bewerberService;

	@Mock
	private HttpServletRequest request;

	@Before
	public void before() {

		mockMvc = webAppContextSetup(this.context).build();
	}

	// @Test
	// public void ectsPageTest() throws Exception {
	//
	// String url = "/vorabcheck/ects_page";
	// mockMvc.perform(post(url)
	//
	// .param("stgName", stgName)
	//
	// .param("stgId", stgId)
	//
	// .param("email", email)
	//
	// .flashAttr("vorabInteressent", new VorabInteressent()))
	//
	// // .andDo(MockMvcResultHandlers.print())
	//
	// // .andExpect(model().attribute("bereich", instanceOf(List.class)))
	//
	// // .andExpect(model().attribute("fach", instanceOf(List.class)))
	//
	// .andExpect(view().name("vorabcheck/user/ects_page"))
	//
	// .andExpect(status().isOk());
	//
	// }

	@Test
	public void homeTest() throws Exception {

		String url = "/vorabcheck/";
		mockMvc.perform(get(url))

				// .andDo(MockMvcResultHandlers.print())

				.andExpect(status().isOk())

				.andExpect(model().attribute("stg", instanceOf(List.class)))

				.andExpect(view().name("vorabcheck/user/start_page"));
	}

	@Test
	public void infoPageTest_withCodenummer() throws Exception {

		studiengangsauswahlService = TestUtils.mockAutowiredField(vorabcheckController,
				StudiengangsauswahlService.class);
		Studiengangsauswahl s = new Studiengangsauswahl();
		s.setName("Test Stg");
		s.setStandardkz("N");

		datenspeichern = "c";

		when(studiengangsauswahlService.fetchById(any())).then(x -> s);

		if (datenspeichern.equals("c")) {
			zeitstempel = System.currentTimeMillis();
			codenummer = bewerberService.hashCode(email, stgName, zeitstempel);
		}

		String url = "/vorabcheck/info_page";
		mockMvc.perform(post(url)

				.param("stgName", stgName)

				.param("stgId", stgId)

				.param("email", email)

				.param("datenspeichern", datenspeichern)

				.flashAttr("vorabInteressent", new VorabInteressent()))

				// .andDo(MockMvcResultHandlers.print())

				.andExpect(model().attribute("standardkz", instanceOf(String.class)))

				.andExpect(view().name("vorabcheck/user/info_page"))

				.andExpect(status().isOk());

		assertNotNull(codenummer);
	}

	@Test
	public void infoPageTest_withoutCodenummer() throws Exception {

		studiengangsauswahlService = TestUtils.mockAutowiredField(vorabcheckController,
				StudiengangsauswahlService.class);
		Studiengangsauswahl s = new Studiengangsauswahl();
		s.setName("Test Stg");
		s.setStandardkz("N");

		datenspeichern = "";

		when(studiengangsauswahlService.fetchById(any())).then(x -> s);

		// If wird nie betreten
		// if (datenspeichern.equals("c")) {
		// zeitstempel = new Date();
		// codenummer = bewerberService.hashCode(email, stgName, zeitstempel);
		// }

		String url = "/vorabcheck/info_page";
		mockMvc.perform(post(url)

				.param("stgName", stgName)

				.param("stgId", stgId)

				.param("email", email)

				.param("datenspeichern", datenspeichern)

				.flashAttr("vorabInteressent", new VorabInteressent()))

				// .andDo(MockMvcResultHandlers.print())

				.andExpect(model().attribute("standardkz", instanceOf(String.class)))

				.andExpect(view().name("vorabcheck/user/info_page"))

				.andExpect(status().isOk());

		assertNull(codenummer);
	}

	// @Test
	// public void resultPageTest() throws Exception {
	// studiengangsauswahlService = TestUtils.mockAutowiredField(vorabController, StudiengangsauswahlService.class);
	// Studiengangsauswahl s = new Studiengangsauswahl();
	// s.setId(studId);
	// s.setName("Test Stg");
	// s.setStandardkz("N");
	// s.setProzentsatz(75);
	// when(studiengangsauswahlService.fetchById(any())).then(x -> s);
	//
	// VorabInteressent v = new VorabInteressent();
	// v.setEmail(email);
	//
	// datenspeichern = "c";
	//
	// List<Ects> ectsList = new ArrayList<>();
	// ectsList.add(VorabcheckTestDaoConfig.ects);
	//
	// EctsForm ectsForm = new EctsForm();
	// ectsForm.setEcts(ectsList);
	//
	// String result = studiengangsauswahlService.getResult(ectsForm, s.getId());
	//
	// verify(studiengangsauswahlService).getResult(ectsForm, s.getId());
	//
	// if (datenspeichern.equals("c")) {
	// zeitstempel = new Date();
	// codenummer = bewerberService.hashCode(email, stgName, zeitstempel);
	//
	// Bewerber b = new Bewerber(codenummer, stgName, s.getId(), null, null, null);
	// Long bewId = null;
	//
	// try {
	//
	// bewId = bewerberService.insert(b);
	//
	// } catch (Exception e) {
	// System.out.println("Konnte Bewerber nicht einfügen: " + e);
	// }
	//
	// b.setId(bewId);
	// for (Ects ects : ectsForm.getEcts()) {
	// ects.setBewId(bewId);
	// ectsService.insert(ects);
	// }
	//
	// studiengangsauswahlService.sendErgebnisMail(codenummer, result, v.getEmail(), "nobody@tuhh.de");
	// }
	//
	// // TODO: Test fixen, ECTS-Form übermitteln
	// String url = "/vorabcheck/result_page";
	// mockMvc.perform(post(url)
	//
	// .param("stgName", stgName)
	//
	// .param("stgId", stgId)
	//
	// .param("email", email)
	//
	// .param("datenspeichern", datenspeichern)
	//
	// .flashAttr("vorabInteressent", new VorabInteressent()));
	//
	// // .andDo(MockMvcResultHandlers.print())
	//
	// // .andExpect(view().name("/vorabcheck/user/result_page"))
	//
	// // .andExpect(model().attributeExists("ectsForm"))
	//
	// // .andExpect(status().isOk());
	//
	// verify(studiengangsauswahlService).sendErgebnisMail(codenummer, result, v.getEmail(), "nobody@tuhh.de");
	// }
}
