package de.tuhh.tusp.utils;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

public class XssFilterTest {

	@InjectMocks
	private XssFilter filter;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private FilterChain chain;

	@Mock
	private ApplicationContext context;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void doFilterTest() throws IOException, ServletException {

		when(request.getContextPath()).then(i -> "/some/path");
		when(request.getAttribute(any())).then(i -> "/some/path");

		ArgumentCaptor<HttpServletRequest> requestCaptor = ArgumentCaptor.forClass(HttpServletRequest.class);
		filter.doFilter(request, response, chain);

		verify(chain, times(1)).doFilter(requestCaptor.capture(), any());

		assertTrue(requestCaptor.getAllValues().get(0) instanceof XssFilteredRequest);

	}
}
