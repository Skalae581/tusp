package de.tuhh.tusp.app.fame.connector.pojo;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.File;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFile;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.app.fame.service.FileServiceImpl;
import de.tuhh.tusp.app.fame.service.ValueServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PdfFileTest {

	@Mock
	private ValueServiceImpl valueService = new ValueServiceImpl();

	@Mock
	private FileServiceImpl fileService = new FileServiceImpl();

	@InjectMocks
	PdfFile candidate = new PdfFile(valueService, fileService);

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveTest_machtInsert() throws PflichtfeldException, RegexException, ImportException {

		ExtendedInput ei = new ExtendedInput();
		ei.setInputTypeUniqName(InputTypeEnum.CONNECTOR_FILE_NAME);

		when(valueService.save(any(ExtendedInput.class), any(String.class), any(Boolean.class))).thenAnswer(i -> 555L);

		candidate.save(ei);
		verify(fileService, times(1)).fetchByValueId(555L);
		verify(fileService, times(1)).insert(any(File.class));
	}

	@Test
	public void saveTest_machtUpdate() throws PflichtfeldException, RegexException, ImportException {

		ExtendedInput ei = new ExtendedInput();
		ei.setInputTypeUniqName(InputTypeEnum.CONNECTOR_FILE_NAME);

		when(valueService.save(any(ExtendedInput.class), any(String.class), any(Boolean.class))).thenAnswer(i -> 555L);

		// Speichern sollte ein update machen
		ExtendedFile file = new ExtendedFile();
		String type = "some/type";
		candidate.setType(type);
		when(fileService.fetchByValueId(555L)).thenAnswer(i -> file);

		candidate.save(ei);

		verify(fileService, times(1)).update(any(File.class));
		assertEquals(type, file.getMimeType());
	}

	@Test(expected = ImportException.class)
	public void saveTest_sichertTyp() throws PflichtfeldException, RegexException, ImportException {

		ExtendedInput ei = new ExtendedInput();
		candidate.save(ei);
	}
}
