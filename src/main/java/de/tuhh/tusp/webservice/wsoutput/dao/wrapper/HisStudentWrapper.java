package de.tuhh.tusp.webservice.wsoutput.dao.wrapper;

import java.text.ParseException;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.NotImplementedException;

import de.his.ws.studentservice.DegreeProgramProgressDto70;
import de.his.ws.studentservice.Student70;
import de.his.ws.studentservice201812.DegreeProgram;
import de.his.ws.studentservice201812.DegreeProgramProgress;
import de.his.ws.studentservice201812.DegreeProgramsType;
import de.his.ws.studentservice201812.ProgressType;
import de.his.ws.studentservice201812.RequestForDisenrollment;
import de.his.ws.studentservice201812.Student;
import de.tuhh.tusp.webservice.exception.PflichtfeldException;
import de.tuhh.tusp.webservice.sync.dao.CountryDao;
import de.tuhh.tusp.webservice.sync.pojo.Country;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;
import de.tuhh.tusp.webservice.sync.service.CourseOfStudyService;
import de.tuhh.tusp.webservice.utils.NullBehavior;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAddressHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisStudentHelperDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.AddressTag;
import de.tuhh.tusp.webservice.wsoutput.pojo.EAdressType;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisEAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPostAddress;
import de.tuhh.tusp.webservice.wsoutput.pojo.NotificationCategory;
import de.tuhh.tusp.webservice.wsoutput.pojo.PersonAdressesWrapper;

public class HisStudentWrapper {

	private HisRoleHelperDao hisRoleHelperDao;
	private CourseOfStudyService courseOfStudyDao;
	private HisAddressHelperDao hisAddressHelperDao;
	private CountryDao countryDao;
	private HisStudentHelperDao studentDao;

	private Student student;
	private DegreeProgramProgressWrapper[] newestDppCache = new DegreeProgramProgressWrapper[2];
	private PersonAdressesWrapper addresses;
	private HisExternalStudyWrapper studyExternal;

	// neu test
	private de.his.ws.studentservice.Student exmaStudent;

	/**
	 * Construct
	 *
	 * @param student
	 */
	public HisStudentWrapper(Student student, HisRoleHelperDao hisRoleHelperDao,
			CourseOfStudyService courseOfStudyService, HisAddressHelperDao hisAddressHelperDao, CountryDao countryDao,
			HisStudentHelperDao studentDao) {

		this.student = student;
		this.hisRoleHelperDao = hisRoleHelperDao;
		this.courseOfStudyDao = courseOfStudyService;
		this.hisAddressHelperDao = hisAddressHelperDao;
		this.countryDao = countryDao;
		this.studentDao = studentDao;
	}

	/**
	 * Entscheidet, wie bei einem Null-Valu umgegangen werden soll
	 *
	 * @param onNull
	 * @return null oder ""
	 */
	private String empty(NullBehavior onNull) {

		switch (onNull) {
		case RETURN_EMPTY:
			return "";
		case RETURN_NULL:
			return null;
		case THROW_EXCEPTION:
			throw new PflichtfeldException();

		default:
			throw new NotImplementedException("Nicht implementiertes NullBehavior");
		}
	}

	/**
	 * Entscheidet, wie bei einem Null-Valu umgegangen werden soll
	 *
	 * @param onNull
	 * @return null oder ""
	 */
	private Long emptyLong(NullBehavior onNull) {

		switch (onNull) {
		case RETURN_EMPTY:
			return 0L;
		case RETURN_NULL:
			return null;
		case THROW_EXCEPTION:
			throw new PflichtfeldException();

		default:
			throw new NotImplementedException("Nicht implementiertes NullBehavior");
		}
	}

	/**
	 * Erstellt die Anmerkung zu dem Student anhand seines Status und Studiengangs
	 *
	 * @param student
	 * @param onNull  Legt fest, was passieren soll, wenn ein Wert nicht gesetzt ist
	 *                oder nicht umgewandelt werden kann
	 * @return den Status
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public String getAnmerkung(NullBehavior onNull) throws PflichtfeldException {

		if (hisRoleHelperDao.roleForPersonWithNameExists(getPersonId(), "candidate_student")) { // vorläufige
																								// Studenten
			return "B";
		}

		String stg = getStgName(onNull, 1);
		if (stg.equals("TM")) { // NIT Studenten
			return "N";
		}

		stg = getStgName(NullBehavior.RETURN_EMPTY, 2);
		if (stg.equals("TM")) { // NIT Studenten
			return "N";
		}

		return "";
	}

	public String getCountry() {

		return student.getCountry();
	}

	/**
	 * Gibt den Studiengang des Studenten zurück
	 *
	 * @param stgnummer
	 *
	 * @return Stg
	 */
	public CourseOfStudy getCourseOfStudy(long stgnummer) {

		DegreeProgramProgressWrapper progres = getNewestEnrollment(stgnummer);

		if (null == progres) {
			return null;
		}

		return courseOfStudyDao.fetchById(progres.getCourseOfStudyId());
	}

	/**
	 * Gibt das Geburtsdatum in deutscher Schreibweise zurück
	 *
	 * @param student
	 * @param onNull  Legt fest, was passieren soll, wenn ein Wert nicht gesetzt ist
	 *                oder nicht umgewandelt werden kann
	 * @return Das Geburtsdatum
	 * @throws ParseException       Wenn das Geburtsdatum des Studenten falsch
	 *                              formatiert ist.
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public XMLGregorianCalendar getDateOfBirth(NullBehavior onNull) throws ParseException, PflichtfeldException {

		if (student.getDateofbirth() == null && onNull == NullBehavior.THROW_EXCEPTION) {
			throw new PflichtfeldException();
		}

		return student.getDateofbirth();
	}

	/**
	 * Ließt den Abschluss
	 *
	 * @param student
	 * @param onNull    Legt fest, was passieren soll, wenn ein Wert nicht gesetzt
	 *                  ist oder nicht umgewandelt werden kann
	 * @param stgnummer
	 * @return Abschluss
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public String getDegree(NullBehavior onNull, long stgnummer) throws PflichtfeldException {

		CourseOfStudy cos = getCourseOfStudy(stgnummer);
		if (null == cos) {
			return empty(onNull);
		}

		String degreeName = courseOfStudyDao.getAbschlussFromCourseOfStudy(cos);
		if (degreeName == null || "-".equals(degreeName)) {
			return empty(onNull);
		}

		return degreeName;
	}

	/**
	 * Liefert die Mailadresse eines Studenten
	 *
	 * @param onNull
	 * @param tag
	 * @param notificationCategories
	 * @return Die Adresse
	 */
	public String getEmail(NullBehavior onNull, AddressTag tag, NotificationCategory[] notificationCategories) {

		if (addresses == null) {
			addresses = hisAddressHelperDao.readPersonAddresses(getPersonId());
		}

		for (NotificationCategory cat : notificationCategories) {

			HisEAddress address = addresses.getEAddress(cat, EAdressType.EMAIL, tag);
			if (address.getValue() != null) {
				return address.getValue();
			}
		}

		return empty(onNull);
	}

	/**
	 * Liefert das DegreeProgramProgress-Objekt zu einem Semesterzeitraum oder null
	 * wenn keines grfunden wird
	 *
	 * @param periodId
	 * @return Objekt oder null
	 */
	public DegreeProgramProgressWrapper getEnrollmentByPeriodId(Long periodId) {

		if (periodId == null) {
			return null;
		}

		DegreeProgramsType degreeProgramsType = student.getDegreePrograms();
		List<DegreeProgram> dtoList = degreeProgramsType.getDegreeProgram();
		for (DegreeProgram dto : dtoList) {
			ProgressType pprogres = dto.getProgress();
			List<DegreeProgramProgress> dppList = pprogres.getDegreeProgramProgress();
			for (DegreeProgramProgress dpp : dppList) {
				if (periodId.equals(dpp.getPeriodId())) {
					return new DegreeProgramProgressWrapper(dpp);
				}
			}
		}

		return null;
	}

	/**
	 * gibt Exmatrikulations-Datum zurück
	 *
	 * @param onNull
	 * @return Datum oder null enum txt
	 */
	public String getExmaDatum() {

		RequestForDisenrollment request = student.getRequestForDisenrollment();
		XMLGregorianCalendar date = null;
		if (request != null) {
			date = request.getDisenrollmentDate();
			if (date != null) {
				return date.toString();
			}
		}
		return "";
	}

	public String getGeburtsName() {

		return student.getBirthname();
	}

	public String getGeburtsOrt() {

		return student.getBirthcity();
	}

	public String getGender() {

		return student.getGender();
	}

	/**
	 * Liest den Vornamen eies Studenten
	 *
	 * @param student
	 * @param onNull  Legt fest, was passieren soll, wenn ein Wert nicht gesetzt ist
	 *                oder nicht umgewandelt werden kann
	 * @return Vorname
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public String getGivenname(NullBehavior onNull) throws PflichtfeldException {

		if (student.getFirstname() == null) {
			return empty(onNull);
		}
		return student.getFirstname();
	}

	/**
	 * Ließt die Nationalität
	 *
	 * @param student
	 * @param onNull  Legt fest, was passieren soll, wenn ein Wert nicht gesetzt ist
	 *                oder nicht umgewandelt werden kann
	 * @return Nationalität
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public Country getNationality(NullBehavior onNull) throws PflichtfeldException {

		String nat = student.getNationality();
		Country country;
		if (nat == null) {
			country = null;
		} else {
			country = countryDao.fetchByUniquename(nat);
		}

		if (country == null) {
			if (onNull == NullBehavior.THROW_EXCEPTION) {
				throw new PflichtfeldException();
			}
			if (onNull == NullBehavior.RETURN_EMPTY) {
				country = new Country();
			}
		}

		return country;
	}

	/**
	 * Sucht den jüngsten Studienverlaufssatz
	 *
	 * @param stgnummer
	 *
	 * @return das DegreeProgrssProgram oder null, wenn keine gefunden
	 */
	public DegreeProgramProgressWrapper getNewestEnrollment(long stgnummer) {

		if (newestDppCache[(int) (stgnummer - 1)] != null) {
			return newestDppCache[(int) (stgnummer - 1)];
		}

		DegreeProgramsType degreeProgramsType = student.getDegreePrograms();

		if (degreeProgramsType.getDegreeProgram().isEmpty()) {
			// Das kommt leider bei candidate_student vor oO
			Student70 stu70 = studentDao.fetchStudentByStudentId(student.getStudentId(), null);

			// wir nehmen an, dass candidate_student nur genau einen DPP hat.
			// Sonst knallt es
			DegreeProgramProgressDto70 dpp70 = stu70.getDegreePrograms70().getDegreeProgram70().get(0).getProgress70()
					.getDegreeProgramProgress70().get(0);

			// Konvertieren
			DegreeProgramProgress dpp = new DegreeProgramProgress();
			dpp.setCourseOfStudyId(dpp70.getCourseOfStudyId());
			dpp.setStudysemester(dpp70.getStudysemester());
			dpp.setStudynumber(dpp70.getStudynumber());

			newestDppCache[(int) (stgnummer - 1)] = new DegreeProgramProgressWrapper(dpp);
			return newestDppCache[(int) (stgnummer - 1)];
		}

		List<DegreeProgram> dtoList = degreeProgramsType.getDegreeProgram();
		for (DegreeProgram dto : dtoList) {
			ProgressType pprogres = dto.getProgress();
			List<DegreeProgramProgress> dppList = pprogres.getDegreeProgramProgress();

			if (dppList.isEmpty()) {
				return null;
			}

			for (DegreeProgramProgress dpp : dppList) {

				// Betrachte nur den mit stgnummer
				if (dpp.getStudynumber().longValueExact() != stgnummer) {
					continue;
				}

				DegreeProgramProgressWrapper wrapper = new DegreeProgramProgressWrapper(dpp);

				newestDppCache[(int) (stgnummer - 1)] = wrapper;
				return newestDppCache[(int) (stgnummer - 1)];
			}
		}

		return null;
	}

	/**
	 * Ließt die PersonID
	 *
	 * @param student
	 * @return ID
	 */
	public Long getPersonId() {

		return student.getPersonId();
	}

	/**
	 * Ließt die Postadresse
	 *
	 * @param onNull
	 * @param categories
	 * @param tags
	 * @return die Adresse oder null
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public HisPostAddress getPostAddres(NullBehavior onNull, NotificationCategory[] categories, AddressTag[] tags)
			throws PflichtfeldException {

		if (addresses == null) {
			addresses = hisAddressHelperDao.readPersonAddresses(getPersonId());
		}

		HisPostAddress address = addresses.getPostAddress(categories, tags);
		if (onNull == NullBehavior.THROW_EXCEPTION && address.getAddressId() == null) {
			throw new PflichtfeldException();
		}
		return address;
	}

	/**
	 * Liest die Prüfungsordnungsversion
	 *
	 * @param student
	 * @param onNull    Legt fest, was passieren soll, wenn ein Wert nicht gesetzt
	 *                  ist oder nicht umgewandelt werden kann
	 * @param stgnummer
	 * @return PO-Version
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public String getPoVersion(NullBehavior onNull, long stgnummer) throws PflichtfeldException {

		CourseOfStudy cos = getCourseOfStudy(stgnummer);
		if (cos == null) {
			return empty(onNull);
		}

		String po = courseOfStudyDao.getPOVersionFromCourseOfStudy(cos);
		if (po == null) {
			return empty(onNull);
		}
		return po;
	}

	/**
	 * Ließt die MatrikelNR
	 *
	 * @param student
	 * @param onNull  Legt fest, was passieren soll, wenn ein Wert nicht gesetzt ist
	 *                oder nicht umgewandelt werden kann
	 * @return MTKNR
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public String getRegistrationNumber(NullBehavior onNull) {

		if (student.getRegistrationnumber() == null) {
			return empty(onNull);
		}
		return student.getRegistrationnumber().toString();
	}

	public String getStatus() {

		return getNewestEnrollment(1).getStatus();
	}

	/**
	 * Ließt den Studiengangsnamen
	 *
	 * @param student
	 * @param onNull    Legt fest, was passieren soll, wenn ein Wert nicht gesetzt
	 *                  ist oder nicht umgewandelt werden kann
	 * @param stgnummer
	 * @return stg
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public String getStgName(NullBehavior onNull, long stgnummer) throws PflichtfeldException {

		CourseOfStudy cos = getCourseOfStudy(stgnummer);
		if (cos == null) {
			return empty(onNull);
		}

		String stg = courseOfStudyDao.getStgNameFromCourseOfStudy(cos);
		if (stg == null) {
			return empty(onNull);
		}

		if (stg.equals("IWA") || stg.equals("IWB")) {
			stg = "IWI";
		}

		return stg;
	}

	/**
	 * Ließt das den letzten Studienverlaufssatz
	 *
	 * @param student
	 * @param onNull    Legt fest, was passieren soll, wenn ein Wert nicht gesetzt
	 *                  ist oder nicht umgewandelt werden kann
	 * @param stgnummer
	 * @return Semester
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public Long getStgSemester(NullBehavior onNull, long stgnummer) throws PflichtfeldException {

		DegreeProgramProgressWrapper progres = getNewestEnrollment(stgnummer);

		if (null == progres) {
			return emptyLong(onNull);
		}

		if (progres.getStudysemester() == null) {
			return emptyLong(onNull);
		}

		return progres.getStudysemester();

	}

	/**
	 * get Student object
	 *
	 * @return das His Studenten Objekt
	 */
	public Student getStudent() {

		return student;
	}

	/**
	 * Liest den Nachnamen eines Studenten
	 *
	 * @param student
	 * @param onNull  Legt fest, was passieren soll, wenn ein Wert nicht gesetzt ist
	 *                oder nicht umgewandelt werden kann
	 * @return Nachname
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public String getSurname(NullBehavior onNull) throws PflichtfeldException {

		if (student.getSurname() == null) {
			return empty(onNull);
		}
		// Falls Namezusatz da ist mit nachname binden
		if (student.getNameprefix() != null && !student.getNameprefix().isEmpty()) {
			return student.getNameprefix() + " " + student.getSurname();
		}
		return student.getSurname();
	}

	/**
	 * Ließt die erste Telefonnummer
	 *
	 * @param student
	 * @param onNull  Legt fest, was passieren soll, wenn ein Wert nicht gesetzt ist
	 *                oder nicht umgewandelt werden kann
	 * @return Telefonnummer
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public String getTelephoneNumber(NullBehavior onNull, EAdressType type, AddressTag[] addressTags,
			NotificationCategory[] notificationCategories) throws PflichtfeldException {

		if (addresses == null) {
			addresses = hisAddressHelperDao.readPersonAddresses(getPersonId());
		}

		for (NotificationCategory cat : notificationCategories) {
			for (AddressTag tag : addressTags) {
				HisEAddress address = addresses.getEAddress(cat, type, tag);
				if (address.getValue() != null) {
					return address.getValue();
				}
			}
		}

		return empty(onNull);
	}

	public String getTitel() {

		return student.getTitle();
	}

	/**
	 * gibt die UHH Matrikelnummer zurück
	 *
	 * @param returnEmpty
	 * @return MTKNR der UHH
	 */
	public String getUhhRegistrationNumber(NullBehavior returnEmpty) {

		if (studyExternal == null) {
			studyExternal = studentDao.readStudyExternal(getPersonId());
		}

		// 333L = UHH sollte in Config. Es ist die Feldnummer in der die MTKNR
		// steht
		String nr = studyExternal.getRegistrationNumber(333L);

		if (nr == null) {
			return empty(returnEmpty);
		}

		return nr;
	}

	/**
	 * Ließt das den letzten Studienverlaufssatz
	 *
	 * @param student
	 * @param onNull    Legt fest, was passieren soll, wenn ein Wert nicht gesetzt
	 *                  ist oder nicht umgewandelt werden kann
	 * @param stgnummer
	 * @return Semester
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public Long getUniSemester(NullBehavior onNull, long stgnummer) throws PflichtfeldException {

		if (student.getUniversitysemester() == null || student.getUniversitysemester().intValueExact() == 0) {
			return emptyLong(onNull);
		}

		return (long) student.getUniversitysemester().intValueExact();

	}

	/**
	 * Ließt die Vertiefung
	 *
	 * @param student
	 * @param onNull    Legt fest, was passieren soll, wenn ein Wert nicht gesetzt
	 *                  ist oder nicht umgewandelt werden kann
	 * @param stgnummer
	 * @return Vertiefung
	 * @throws PflichtfeldException Wenn onNull = THROW_EXCEPTION und das Feld null
	 *                              ist oder nicht umgewandelt werden kann
	 */
	public String getVertiefung(NullBehavior onNull, long stgnummer) throws PflichtfeldException {

		CourseOfStudy cos = getCourseOfStudy(stgnummer);
		if (null == cos) {
			return empty(onNull);
		}

		String vertiefung = courseOfStudyDao.getVertiefungFromCourseOfStudy(cos);
		if (vertiefung == null || "-".equals(vertiefung)) {
			return empty(onNull);
		}

		return vertiefung;
	}

	/**
	 * Gibt zurück ob DegreePrograms vorhanden sind
	 *
	 * @return true/false
	 */
	public boolean hasDegreeProgramms() {

		return !student.getDegreePrograms().getDegreeProgram().isEmpty();
	}

	@Override
	public String toString() {

		return "HisStudentWrapper (" + student.getFirstname() + " " + student.getSurname() + " [mtknr="
				+ student.getRegistrationnumber() + "]" + " [personId=" + student.getPersonId() + "]" + " [studentId="
				+ student.getStudentId() + "])";
	}
}