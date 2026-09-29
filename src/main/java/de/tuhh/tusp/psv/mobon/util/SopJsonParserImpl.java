package de.tuhh.tusp.psv.mobon.util;

//import org.apache.log4j.Logger;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.JSONTokener;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//
//import com.sop.container.xsd.ApplicationDataElement;
//
//import de.tuhh.tusp.psv.mobon.pojo.SopArgumentsHolder;
//
//@Component
public class SopJsonParserImpl implements SopJsonParser {

//	private static final String AUTHORITY = "authority";
//	private static final String LASTNAME = "lastName";
//	private static final String SECONDLASTNAME = "secondLastName";
//	private static final String FIRSTNAME = "firstName";
//	private static final String BIRTHDAY = "birthday";
//	private static final String MATRICULATIONNUMBER = "matriculationNumber";
//	private static final String EMAIL = "email";
//	private static final String APPLICATIONTYPE = "applicationType";
//	private static final String PERSONTYPE = "personType";
//	private static final String EXCHANGEPROGRAMNUMBER = "exchangeProgramNumber";
//	private static final String ACADEMICYEAR = "academicYearDescription";
//	private static final String SEMESTERDESCRIPTION = "semesterDescription";
//	private static final String STUDYFIELDESCRIPTION = "studyFieldDescription";
//	private static final String LOGIN = "login";
//	private static final String FURTHERSEARCHRESTRICTIONS = "furtherSearchRestrictions";
//	private static final String SPECIFIEDELEMENTSFORRETURN = "specifiedElementsForReturn";
//
//	private static Logger logger = Logger.getLogger(SopJsonParser.class);
//
//	/**
//	 * Macht aus einem leeren String null
//	 *
//	 * @param val
//	 * @return null oder den String
//	 */
//	private String emptyToNull(String val) {
//
//		if ("".equals(val)) {
//			return null;
//		}
//		return val;
//	}
//
//	@Override
//	public ApplicationDataElement[] getApplicationDataElements(JSONObject json, String name) {
//
//		// TODO Ordnung reinbringen
//		JSONArray sefr = json.getJSONArray(name);
//		ApplicationDataElement[] ade = new ApplicationDataElement[sefr.toList().size()];
//		for (int i = 0; i < sefr.toList().size(); i++) {
//			JSONObject searchRestrictions = sefr.getJSONObject(i);
//			String elementName = searchRestrictions.getString("elementName");
//			String elementType = searchRestrictions.getString("elementType");
//			ade[i] = new ApplicationDataElement();
//			ade[i].setElementName(elementName);
//
//			if (FURTHERSEARCHRESTRICTIONS.equals(name)) {
//				String elementValue = searchRestrictions.getString("elementValue");
//				Boolean bElementValue = searchRestrictions.getBoolean("elementValueBoolean");
//				ade[i].setElementValue(elementValue);
//				ade[i].setElementValueBoolean(bElementValue);
//				ade[i].setElementType(elementType);
//			}
//		}
//
//		return ade;
//	}
//
//	@Override
//	public SopArgumentsHolder readJsonFile() {
//
//		JSONObject jObj = null;
//		SopArgumentsHolder sopArgs = new SopArgumentsHolder();
//		try {
//			ClassPathResource jsonPath = new ClassPathResource("sop\\serviceArguments.JSON");
//			Reader f = new FileReader(jsonPath.getFile());
//			JSONTokener tokener = new JSONTokener(f);
//			jObj = new JSONObject(tokener);
//			logger.debug("Lese Jsonfile von " + jsonPath.getPath());
//		}
//		catch (IOException e) {
//			logger.error(e.getMessage());
//		}
//
//		if (jObj != null) {
//			sopArgs.setAuthority(emptyToNull(jObj.getString(AUTHORITY)));
//			sopArgs.setBirthday(emptyToNull(jObj.getString(BIRTHDAY)));
//			sopArgs.setFirstName(emptyToNull(jObj.getString(FIRSTNAME)));
//			sopArgs.setSecondLastName(emptyToNull(jObj.getString(SECONDLASTNAME)));
//			sopArgs.setLastName(emptyToNull(jObj.getString(LASTNAME)));
//			sopArgs.setMatriculationNumber(emptyToNull(jObj.getString(MATRICULATIONNUMBER)));
//			sopArgs.setEmail(emptyToNull(jObj.getString(EMAIL)));
//			sopArgs.setApplicationType(emptyToNull(jObj.getString(APPLICATIONTYPE)));
//			sopArgs.setPersonType(emptyToNull(jObj.getString(PERSONTYPE)));
//			sopArgs.setExchangeProgramNumber(emptyToNull(jObj.getString(EXCHANGEPROGRAMNUMBER)));
//			sopArgs.setAcademicYearDescription(emptyToNull(jObj.getString(ACADEMICYEAR)));
//			sopArgs.setSemesterDescription(emptyToNull(jObj.getString(SEMESTERDESCRIPTION)));
//			sopArgs.setStudyFieldDescription(emptyToNull(jObj.getString(STUDYFIELDESCRIPTION)));
//			sopArgs.setLogin(emptyToNull(jObj.getString(LOGIN)));
//			sopArgs.setFurtherSearchRestrictions(getApplicationDataElements(jObj, FURTHERSEARCHRESTRICTIONS));
//			sopArgs.setSpecifiedElementsForReturn(getApplicationDataElements(jObj, SPECIFIEDELEMENTSFORRETURN));
//		}
//
//		return sopArgs;
//	}

}
