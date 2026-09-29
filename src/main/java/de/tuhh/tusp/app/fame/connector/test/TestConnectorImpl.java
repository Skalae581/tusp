package de.tuhh.tusp.app.fame.connector.test;

import java.util.ArrayList;
import java.util.List;

import de.tuhh.tusp.app.fame.connector.Case;
import de.tuhh.tusp.app.fame.connector.pojo.PdfFile;
import de.tuhh.tusp.app.fame.connector.pojo.SingleLineText;
import de.tuhh.tusp.app.fame.service.FileService;
import de.tuhh.tusp.app.fame.service.ValueService;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService.ImportFilter;

/**
 * Dummy Klasse, die erstmal als Testschnittstelle herhalten muss
 *
 * @author rztjm
 *
 * @todo Richtige Implementierung erzeugen
 */
public class TestConnectorImpl implements TestConnector {

	private ValueService valueService = null;
	private FileService fileService = null;

	private List<Case> cases;
	private String parameter;

	/**
	 * Construct
	 * 
	 * @param valueService
	 * @param fileService
	 */
	public TestConnectorImpl(ValueService valueService, FileService fileService) {
		this.valueService = valueService;
		this.fileService = fileService;

		cases = new ArrayList<>();

		addTestCase("20172-1", "Alfons", "Antilope", "ICS", "geschl");
		addTestCase("20172-2", "Benno", "Bär", "ICS", "m");
		addTestCase("20172-3", "Carla", "Chamäleon", "ICS", "f");
		addTestCase("20172-4", "Dennis", "Dromeda", "MEM", "m");
		addTestCase("20172-5", "Emiel", "Elefant", "MEM", "m");
		addTestCase("20172-6", "Franz", "Fuchs", "MEM", "m");
		addTestCase("20172-7", "Gustav", "Gans", "MEM", "m");
		addTestCase("20172-8", "Harri", "Hirsch", "MEM", "m");
		addTestCase("20172-9", "Ida", "Iltis", "MEM", "m");
	}

	/**
	 * Erstellt einen Testfall
	 *
	 * @param id
	 * @param vorN
	 * @param nachN
	 * @param stg
	 * @param geschl
	 */
	private void addTestCase(String id, String vorN, String nachN, String stg, String geschl) {

		TestCase c = new TestCase();
		c.setId(id);
		c.getProperties().put("firstname", new SingleLineText(vorN, valueService));
		c.getProperties().put("lastname", new SingleLineText(nachN, valueService));
		c.getProperties().put("bewnr", new SingleLineText(id, valueService));
		c.getProperties().put("stg", new SingleLineText(stg, valueService));
		c.getProperties().put("geschl", new SingleLineText(geschl, valueService));
		cases.add(c);
	}

	@Override
	public Case findCase(String caseId) {

		for (Case c : this.findCases(ImportFilter.Alle)) {
			if (caseId.equals(c.getId())) {
				return c;
			}
		}
		return null;
	}

	@Override
	public List<Case> findCases(ImportFilter filter) {

		List<Case> ret = new ArrayList<>();

		for (Case c : cases) {
			if (((SingleLineText) c.getPropertyByName("stg")).getValue().equals(parameter)) {
				ret.add(c);
			}
		}

		return ret;
	}

	@Override
	public Case readExapleCase() {

		PdfFile tor = new PdfFile(valueService, fileService);
		tor.setId("124");
		tor.setFileName("Transcript of Records Mustermann.pdf");
		tor.setContent("asdf".getBytes());

		PdfFile abi = new PdfFile(valueService, fileService);
		abi.setId("123");
		abi.setFileName("Abizeugnis Mustermann.pdf");
		abi.setContent("asdf".getBytes());

		TestCase case1 = new TestCase();
		case1.setId("20212-1");
		case1.getProperties().put("firstname", new SingleLineText("Max", valueService));
		case1.getProperties().put("lastname", new SingleLineText("Muster", valueService));
		case1.getProperties().put("tor", tor);
		case1.getProperties().put("abi", abi);
		cases.add(case1);

		return case1;
	}

	@Override
	public List<Case> search(String searchword) {

		List<Case> ret = new ArrayList<>();

		for (Case c : this.cases) {
			if (c.getId().equals(searchword)) {
				ret.add(c);
			}
		}

		return ret;
	}

	@Override
	public void setParameter(String parameter) {

		this.parameter = parameter;
	}

	@Override
	public void setWorkflowInstanceId(Long id) {
		// hier irrelevant
	}

	@Override
	public void writeCase(Case c) {

	}

}
