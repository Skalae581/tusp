package de.tuhh.tusp.psv.mobon.util;

//import org.json.JSONObject;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//import com.sop.container.xsd.ApplicationDataElement;

//import de.tuhh.tusp.psv.mobon.pojo.SopArgumentsHolder;

//@RunWith(JUnit4.class)
public class SopJsonParserTest {
//
//	private JSONObject jsonTestFile;
//	private ApplicationDataElement[] furtherSearchRestrictionsExpected;
//	private ApplicationDataElement[] exampleExpected;
//	private ApplicationDataElement[] wrongExample;
//
//	SopJsonParser jsonParser = new SopJsonParserImpl();
//
//	@Test
//	public void getApplicationDataElementsTest() {
//
//		ApplicationDataElement[] furtherSearchRestrictionsActual =
//				jsonParser.getApplicationDataElements(jsonTestFile, "furtherSearchRestrictions");
//		ApplicationDataElement[] exampleActual = jsonParser.getApplicationDataElements(jsonTestFile, "example");
//
//		Assert.assertArrayEquals(furtherSearchRestrictionsExpected, furtherSearchRestrictionsActual);
//		Assert.assertArrayEquals(exampleExpected, exampleActual);
//		Assert.assertNotEquals(wrongExample, exampleActual);
//	}
//
//	@Test
//	public void readJsonFileTest() {
//
//		SopArgumentsHolder holder = jsonParser.readJsonFile();
//
//		Assert.assertNotNull("nullwert geliefert", holder);
//		Assert.assertNotNull("authority nicht gefunden", holder.getAuthority());
//	}
//
//	@Before
//	public void testFileErstellen() {
//
//		// generate TestFile
//		String jsonText;
//		jsonText = "{\"furtherSearchRestrictions\":[{\"elementName\":\"is_geprueft\","
//				+ "\"elementType\":\"boolean\",\"elementValue\":\"true\",\"elementValueBoolean\":true}],"
//				+ "\"example\":[{\"elementName\":\"Test\",\"elementType\":\"boolean\",\"elementValue\":\"true\"}]}";
//		jsonTestFile = new JSONObject(jsonText);
//
//		// generate first Object from TestFile
//		ApplicationDataElement fSR = new ApplicationDataElement();
//		fSR.setElementName("is_geprueft");
//		fSR.setElementType("boolean");
//		fSR.setElementValue("true");
//		fSR.setElementValueBoolean(true);
//		furtherSearchRestrictionsExpected = new ApplicationDataElement[] { fSR };
//
//		// generate second Object from TestFile
//		ApplicationDataElement ex = new ApplicationDataElement();
//		ex.setElementName("Test");
//		exampleExpected = new ApplicationDataElement[] { ex };
//
//		// generate wrong second Object
//		ApplicationDataElement exa = new ApplicationDataElement();
//		exa.setElementName("Test");
//		exa.setElementType("boolean");
//		exa.setElementValue("true");
//		wrongExample = new ApplicationDataElement[] { ex };
//
//	}

}
