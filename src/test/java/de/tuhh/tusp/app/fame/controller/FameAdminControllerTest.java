package de.tuhh.tusp.app.fame.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.service.WorkflowService;
import de.tuhh.tusp.config.ApplicationIntegrationTestControllerConfig;
import de.tuhh.tusp.utils.TestUtils;
import net.jcip.annotations.NotThreadSafe;

@ContextConfiguration(classes = { ApplicationIntegrationTestControllerConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@NotThreadSafe
public class FameAdminControllerTest {

	private Long workflowId = 5L;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	private WorkflowService workflowService;

	@Autowired
	private FameAdminController controller;

	@Before
	public void before() {

		mockMvc = webAppContextSetup(this.context).build();

	}

	@Test
	public void overviewTest() throws Exception {

		// setup
		workflowService = TestUtils.mockAutowiredField(controller, WorkflowService.class);
		Workflow w = new Workflow();
		w.setName("Test Workflow");
		when(workflowService.fetchById(any())).then(x -> w);

		// test
		String url = "/fame/admin/overview?id=" + workflowId;
		mockMvc	.perform(get(url))

				.andExpect(status().isOk())

				.andExpect(model().attribute("workflow", hasProperty("name", is("Test Workflow"))))

				.andExpect(model().attribute("instances", instanceOf(List.class)))

				.andExpect(model().attribute("stati", instanceOf(List.class)))

				.andExpect(model().attribute("inputs", instanceOf(List.class)))

				.andExpect(view().name("fame/admin/overview"));

	}

	@Test
	public void startTest() throws Exception {

		String url = "/fame/admin/";
		mockMvc	.perform(get(url))

				.andExpect(status().isOk())

				.andExpect(model().attribute("objects", instanceOf(List.class)))

				.andExpect(view().name("fame/admin/start"));
	}

}
