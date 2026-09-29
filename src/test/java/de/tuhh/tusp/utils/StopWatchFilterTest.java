package de.tuhh.tusp.utils;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.context.WebApplicationContext;

import de.tuhh.tusp.global.service.ConfigService;

@RunWith(MockitoJUnitRunner.class)
public class StopWatchFilterTest {

	@InjectMocks
	private StopWatchFilter filter = new StopWatchFilter(null);

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private ConfigService config;

	@Mock
	private WebApplicationContext ctx;

	@Mock
	private FilterChain chain;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void doFilterTest() throws IOException, ServletException {

		when(ctx.getBean(ConfigService.class)).then(x -> config);
		when(config.get(any(String.class), any(String.class), any(Boolean.class))).then(x -> "true");
		when(request.getRequestURL()).then(x -> new StringBuffer("StopWatchFilter test url!!"));

		filter.doFilter(request, response, chain);

		verify(chain, times(1)).doFilter(any(), any());
	}

}
