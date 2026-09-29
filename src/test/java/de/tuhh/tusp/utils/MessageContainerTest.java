package de.tuhh.tusp.utils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RunWith(MockitoJUnitRunner.class)
public class MessageContainerTest {

	@InjectMocks
	private MessageContainer messageContainer = new MessageContainer();

	private Locale locale = LocaleContextHolder.getLocale();

	@Mock
	private BindingResult bindingResult;

	private StaticMessageSource messageSource = new StaticMessageSource();

	@Test
	public void addBindingResultTest() {

		messageSource.addMessage("test.name", locale, "Namensfeld");

		List<FieldError> errors = new ArrayList<>();
		errors.add(new FieldError("Paul", "name", "Der Name von Paul ist zu kurz"));
		when(bindingResult.getFieldErrors()).then(x -> errors);

		messageContainer.addBindingResult("test.", bindingResult);

		assertEquals(1, messageContainer.getErrors().size());
	}

	@Test
	public void addErrorKeyTest() {

		messageContainer.addErrorKey("insert.one.arg", new Object[] { "1" });
		messageContainer.addErrorKey("insert.two.arg", new Object[] { "1", "Test" });

		assertEquals("1", messageContainer.getErrors().get(0));
		assertEquals("1,Test", messageContainer.getErrors().get(1));
	}

	@Test
	public void addInfosKeyTest() {

		messageContainer.addInfosKey("insert.one.arg", new Object[] { "1" });
		messageContainer.addInfosKey("insert.two.arg", new Object[] { "1", "Test" });

		assertEquals("1", messageContainer.getInfos().get(0));
		assertEquals("1,Test", messageContainer.getInfos().get(1));
	}

	@Test
	public void addSuccessKeyTest() {

		messageContainer.addSuccessKey("insert.one.arg", new Object[] { "1" });
		messageContainer.addSuccessKey("insert.two.arg", new Object[] { "1", "Test" });

		assertEquals("1", messageContainer.getSuccesses().get(0));
		assertEquals("1,Test", messageContainer.getSuccesses().get(1));
	}

	@Test
	public void addWarningKeyTest() {

		messageContainer.addWarningKey("insert.one.arg", new Object[] { "1" });
		messageContainer.addWarningKey("insert.two.arg", new Object[] { "1", "Test" });

		assertEquals("1", messageContainer.getWarnings().get(0));
		assertEquals("1,Test", messageContainer.getWarnings().get(1));
	}

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		TestUtils.setField(messageContainer, "messageSource", messageSource);

		messageSource.addMessage("insert.one.arg", locale, "{0}");
		messageSource.addMessage("insert.two.arg", locale, "{0},{1}");
	}
}
