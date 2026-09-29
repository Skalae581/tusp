package de.tuhh.tusp.utils.auth;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import de.tuhh.tusp.global.service.ConfigService;

public class ShibbolethAuthenticationFilterTest {

	@InjectMocks
	private ShibbolethAuthenticationFilter filter;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private HttpSession session;

	@Mock
	private FilterChain chain;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private ConfigService configService;

	@Mock
	private SecurityContext securityContext;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		when(request.getSession()).thenAnswer(i -> session);
		when(session.getAttribute("SPRING_SECURITY_CONTEXT")).thenAnswer(i -> securityContext);
		when(request.getRequestURI()).thenAnswer(i -> "/es/ist/ein/langer.weg");
	}

	@Test
	public void doFilter_authentificatedUserWirdIgnoriert() throws Exception {

		Authentication auth = Mockito.mock(Authentication.class);
		when(securityContext.getAuthentication()).thenAnswer(i -> auth);
		when(auth.isAuthenticated()).thenReturn(true);

		filter.doFilter(request, response, chain);

		verify(authenticationManager, times(0)).authenticate(any());
	}

	@Test
	public void doFilter_backdoorDisabledTest() throws Exception {

		when(request.getParameter("uid")).thenAnswer(i -> "rztjm");
		when(securityContext.getAuthentication()).thenReturn(null);
		when(configService.get(any(String.class), any(String.class), any(Boolean.class))).then(i -> "was != true");
		SecurityContextHolder.setContext(securityContext);

		filter.doFilter(request, response, chain);
		verify(securityContext, times(0)).setAuthentication(any());
	}

	@Test
	public void doFilter_backdoorEnabledTest() throws Exception {

		when(request.getParameter("uid")).thenAnswer(i -> "rztjm");
		when(securityContext.getAuthentication()).thenReturn(null);
		when(configService.get(any(String.class), any(String.class), any(Boolean.class))).then(i -> "true");
		SecurityContextHolder.setContext(securityContext);

		filter.doFilter(request, response, chain);
		verify(securityContext).setAuthentication(any(UsernamePasswordAuthenticationToken.class));
	}

	@Test
	public void doFilter_StrangeRequestTypeIgnored() throws Exception {

		ServletRequest request = Mockito.mock(ServletRequest.class);

		filter.doFilter(request, response, chain);

		verify(authenticationManager, times(0)).authenticate(any());
	}
}
