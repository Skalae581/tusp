package de.tuhh.tusp.app.fame.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.fame.dao.ValueDaoImpl;
import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.utils.MessageContainer;

@RunWith(MockitoJUnitRunner.class)
public class ValueServiceImplTest {

	private static final Long INPUT_ID_WITH_EXISTING_VALUE = 1L;
	private static final Long INPUT_ID_WITHOUT_EXISTING_VALUE = 2L;

	@InjectMocks
	private ValueServiceImpl service;

	@Mock
	private ValueDaoImpl valueDao;

	@Mock
	private MessageContainer messageContainer;

	@Mock
	private LogService logService;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		resetValueDao();

		when(messageContainer.getString(any(String.class))).then(i -> "%s");
	}

	private void resetValueDao() {

		reset(valueDao);

		// Wenn Input ID = 1 gibt es schon einen Value, sonst nicht
		when(valueDao.fetchByVorgangAndInput(1L, 1L)).then(i -> (Long) i.getArguments()[1] == INPUT_ID_WITH_EXISTING_VALUE
				? new Value() : null);
	}

	@Test
	public void saveTest() throws PflichtfeldException, RegexException {

		ExtendedInput input = new ExtendedInput();
		input.setInputTypeUniqName(InputTypeEnum.TEXT);
		input.setVorgangId(INPUT_ID_WITH_EXISTING_VALUE);
		input.setRecht(InputRechteEnum.WRITE);
		input.setName("Testinput");
		input.setPflicht(0);

		// Wenn Value Objekt existiert, erwarte update
		input.setId(INPUT_ID_WITH_EXISTING_VALUE);
		service.save(input, "wasd", true);
		verify(valueDao, times(1)).update(any(Value.class));
		verify(logService, times(1)).logValueChange(any(Input.class), any(Value.class));

		// Wenn bisher kein Value Objekt, erwarte insert
		input.setId(INPUT_ID_WITHOUT_EXISTING_VALUE);
		service.save(input, "wasd", true);
		verify(valueDao, times(1)).insert(any(Value.class));

		// Wird Regex-Prüfung durchgeführt?
		input.setRegex("w[abc]sd");
		try {
			service.save(input, "wdsd", true);
			fail();
		}
		catch (RegexException e) {}
		service.save(input, "wasd", true);

		// Wird Pflichtfeld-Prüfung durchgeführt?
		input.setRegex("");
		service.save(input, "", true);
		input.setPflicht(1);
		service.save(input, "asdf", true);
		try {
			service.save(input, "", true);
			fail();
		}
		catch (PflichtfeldException e) {}

		// Werden Checkboxes auf 0 gesetzt, wenn sie nicht 1 seid?
		resetValueDao();
		ArgumentCaptor<Value> argumentCaptor = ArgumentCaptor.forClass(Value.class);
		input.setInputTypeUniqName(InputTypeEnum.CHECKBOX);
		service.save(input, null, true);
		service.save(input, "1", true);
		verify(valueDao, times(2)).insert(argumentCaptor.capture());
		assertEquals("0", argumentCaptor.getAllValues().get(0).getEingabe());
		assertEquals("1", argumentCaptor.getAllValues().get(1).getEingabe());

		// Berechtigungsprüfung testen UPDATE
		resetValueDao();
		input.setId(INPUT_ID_WITH_EXISTING_VALUE);
		// Readonly
		input.setRecht(InputRechteEnum.READ);
		service.save(input, "1", true);
		verify(valueDao, times(0)).update(any(Value.class));
		// SetOnly
		input.setRecht(InputRechteEnum.SET);
		service.save(input, "1", true);
		verify(valueDao, times(0)).update(any(Value.class));
		// Write
		input.setRecht(InputRechteEnum.WRITE);
		service.save(input, "1", true);
		verify(valueDao, times(1)).update(any(Value.class));

		// Berechtigungsprüfung testen INSERT
		resetValueDao();
		input.setId(INPUT_ID_WITHOUT_EXISTING_VALUE);
		// Readonly
		input.setRecht(InputRechteEnum.READ);
		service.save(input, "1", true);
		verify(valueDao, times(0)).insert(any(Value.class));
		// SetOnly
		input.setRecht(InputRechteEnum.SET);
		service.save(input, "1", true);
		verify(valueDao, times(1)).insert(any(Value.class));
		resetValueDao();
		// Write
		input.setRecht(InputRechteEnum.WRITE);
		service.save(input, "1", true);
		verify(valueDao, times(1)).insert(any(Value.class));

	}

}
