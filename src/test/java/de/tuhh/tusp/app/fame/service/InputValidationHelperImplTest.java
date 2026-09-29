package de.tuhh.tusp.app.fame.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.tuhh.tusp.app.fame.exception.ValidationException;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.global.service.ConfigService;

public class InputValidationHelperImplTest {

	@Mock
	private ValueService valueService;

	@Mock
	private ConfigService config;

	@Mock
	private VorgangService vorgangService;

	@InjectMocks
	private InputValidationHelperImpl candidate;

	List<ExtendedInput> inputs = null;
	String validationCode = "if(valueOf('value_1') != '1') {fail('value_1 ist incorrect')} "
			+ "if(valueOf('value_2') != '2') {fail('value_2 ist incorrect')} "
			+ "if(valueOf('value_3') != '3') {fail('value_3 ist incorrect')} ";

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

		inputs = new ArrayList<>();
		for (long i = 1; i <= 4; i++) {
			ExtendedInput e = new ExtendedInput();
			e.setId(i);
			inputs.add(e);
		}
	}

	@Test
	public void jsAllowOptionsTest() throws Exception {

		Map<String, String[]> map = new HashMap<>();
		map.put("statusId", new String[] { "1" });
		candidate.validateAndSave(	new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), map,
									"allowOptions('statusId', new Array(1,2))", null, 0L);

		try {
			candidate.validateAndSave(	new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), map,
										"allowOptions('statusId', new Array(3,2))", null, 0L);
			fail("Unerlaubter Wert wird nicht erkannt");
		}
		catch (ValidationException e) {

		}
	}

	@Test
	public void jsEmptyTest() throws Exception {

		candidate.validateAndSave(	new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(),
									"if(empty(1)) {fail('1 wurde als empty erkannt');}", null, 0L);
		candidate.validateAndSave(	new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(),
									"if(!empty(0)) {fail('0 wurde als empty erkannt');}", null, 0L);

		candidate.validateAndSave(	new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(),
									"if(empty('a')) {fail('\\'\\' wurde als empty erkannt');}", null, 0L);
		candidate.validateAndSave(	new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(),
									"if(!empty('')) {fail('\\'\\' wurde als empty erkannt');}", null, 0L);

		candidate.validateAndSave(	new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(),
									"if(!empty(null)) {fail('null wurde als empty erkannt');}", null, 0L);
	}

	@Test
	public void validateAndSave_canSetInput() throws Exception {

		Vorgang vorgang = new Vorgang();
		Mockito.when(vorgangService.fetchById(inputs.get(0).getVorgangId())).then(x -> vorgang);

		Map<String, String[]> parameterMap = new HashMap<>();
		parameterMap.put("value_1", new String[] { "1" });
		parameterMap.put("value_2", new String[] { "2" });

		candidate.validateAndSave(inputs, inputs, new ArrayList<>(), parameterMap, "setValue('value_1', 3);", null, 5L);

		// Methode soll auch den Statusübergang machen
		assertEquals("2", parameterMap.get("value_2")[0]);
		assertEquals("3", parameterMap.get("value_1")[0]);
	}

	@Test
	public void validateAndSave_succeedesWithCorrectInput() throws Exception {

		Vorgang vorgang = new Vorgang();
		Mockito.when(vorgangService.fetchById(inputs.get(0).getVorgangId())).then(x -> vorgang);

		Map<String, String[]> parameterMap = new HashMap<>();
		parameterMap.put("value_1", new String[] { "1" });
		parameterMap.put("value_2", new String[] { "2" });
		parameterMap.put("value_3", new String[] { "3" });

		candidate.validateAndSave(inputs, inputs, new ArrayList<>(), parameterMap, validationCode, null, 5L);

		// Methode soll auch den Statusübergang machen
		Mockito.verify(vorgangService, Mockito.times(1)).statusUebergang(vorgang, 5L);
	}

	@Test(expected = ScriptException.class)
	public void validateAndSave_throwsScriptException() throws Exception {

		Map<String, String[]> parameterMap = new HashMap<>();
		String validationCode = "Syntaxfehler!";
		candidate.validateAndSave(inputs, inputs, new ArrayList<>(), parameterMap, validationCode, null, 0L);
	}

	@Test
	public void validateAndSave_throwsValidationException() throws Exception {

		try {
			Map<String, String[]> parameterMap = new HashMap<>();
			candidate.validateAndSave(	inputs, inputs, new ArrayList<>(), parameterMap, "fail('dies ist ein Test')",
										null, 0L);

			fail("Es hätte eine ValidationException geworfen werden müssen");
		}
		catch (ValidationException e) {
			assertEquals("dies ist ein Test", e.getErrors().get(0));
		}
	}
}
