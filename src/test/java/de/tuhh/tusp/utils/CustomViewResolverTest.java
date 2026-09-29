package de.tuhh.tusp.utils;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import de.tuhh.tusp.config.ApplicationIntegrationTestControllerConfig;

@ContextConfiguration(classes = { ApplicationIntegrationTestControllerConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CustomViewResolverTest {

	@Autowired
	private CustomViewResolver resolver;

	@Test
	public void buildView_existingView() throws Exception {

		AbstractUrlBasedView view = resolver.buildView("index");
		assertEquals("/WEB-INF/views/index.jsp", view.getUrl());
	}

	@Test
	public void buildView_nonExistingView() throws Exception {

		AbstractUrlBasedView view = resolver.buildView("undefinied");
		assertTrue(view.getClass().getName().endsWith("NonExistentView"));

		// Der Rest ist nur für die Testabdeckung
		boolean res = true;
		view.getClass().getDeclaredMethod("isUrlRequired").setAccessible(true);
		res = (boolean) view.getClass().getDeclaredMethod("isUrlRequired").invoke(view);
		assertFalse(res);

		// Der Rest ist nur für die Testabdeckung
		view.getClass().getDeclaredMethod("checkResource", Locale.class).setAccessible(true);
		res = (boolean) view.getClass().getDeclaredMethod("checkResource", Locale.class).invoke(view, Locale.CHINA);
		assertFalse(res);

		// Der Rest ist nur für die Testabdeckung
		Method m = view.getClass().getDeclaredMethod(	"renderMergedOutputModel", Map.class, HttpServletRequest.class,
														HttpServletResponse.class);
		m.setAccessible(true);
		m.invoke(view, null, null, null);
	}
}
