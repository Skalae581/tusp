package de.tuhh.tusp.utils;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class XssFilteredRequestTest {

	@InjectMocks
	private XssFilteredRequest request;

	@Mock
	private XssSanitizer sanitizer;

	@Mock
	private HttpServletRequest requestMock;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		// request = new XssFilteredRequest(requestMock);

		when(sanitizer.sanitize(any())).then(x -> "ok");
	}

	@Test
	public void getParameterTest() {

		when(requestMock.getParameter(any())).thenAnswer(x -> "asdf");

		String answer = request.getParameter("test");

		verify(sanitizer, times(1)).sanitize("asdf");
		assertEquals("ok", answer);
	}

	@Test
	public void getParameterValuesTest() {

		when(requestMock.getParameterValues(any())).thenAnswer(x -> new String[] { "", "1", "3" });

		String[] answer = request.getParameterValues("test");

		verify(sanitizer, times(3)).sanitize(any());
		assertEquals("ok", answer[0]);
		assertEquals("ok", answer[1]);
		assertEquals("ok", answer[2]);
	}
}
