package de.tuhh.tusp.app.vorabcheck.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.BereichArchive;
import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;
import de.tuhh.tusp.app.vorabcheck.pojo.Ects;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.app.vorabcheck.pojo.StudiengangsauswahlArchive;
import de.tuhh.tusp.utils.MessageContainer;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.webservice.sync.dao.CourseOfStudyDao;
import de.tuhh.tusp.webservice.sync.dao.DegreeDao;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;
import de.tuhh.tusp.webservice.sync.pojo.Degree;

@Configuration
@ComponentScan(basePackages = "de.tuhh.tusp.webservice.sync.dao")
@Transactional
public class VorabcheckTestDaoConfig {

	public static Long degId;
	public static CourseOfStudy course;
	public static Long cosId;
	public static String cosShorttextDe;
	public static List<CourseOfStudy> master_courses;
	public static List<Studiengangsauswahl> master_studiengaenge;
	public static Long stgId;
	public static Bewerber bewerber;
	public static List<Bereich> bereiche;
	public static Long bereichId;
	public static List<Long> bereichIdArray;
	public static List<Fach> faecher;
	public static Ects ects;
	public static List<StudiengangsauswahlArchive> archivStudiengaenge;
	public static List<BereichArchive> archivBereiche;
	public static List<String> codeArray;

	@Autowired
	private BereichDao bereichDao;

	@Autowired
	private FachDao fachDao;

	@Autowired
	private CourseOfStudyDao cosDao;

	@Autowired
	private DegreeDao degDao;

	@Autowired
	private StudiengangsauswahlDao stgDao;

	@Autowired
	private StudiengangsauswahlArchiveDao stgArchDao;

	@Autowired
	private BereichArchiveDao berArchDao;

	@Autowired
	private BewerberDao bewDao;

	@Autowired
	private EctsDao ectsDao;

	@Bean
	public MessageContainer getMessageContainer() {

		return new MessageContainer();
	}

	/**
	 * Funktion bereitet die Testdaten vor
	 *
	 * @param event
	 */
	@Bean
	public VorabcheckTestDaoConfig init() {

		degId = manufactureDeg().getId();
		course = manufactureCos(degId);
		cosId = course.getId();
		cosShorttextDe = course.getShorttextDe();
		master_courses = manufactureCosArray(degId, 8);
		master_studiengaenge = manufactureMasterStgArray(master_courses, 3);
		stgId = master_studiengaenge.get(1).getId();
		bewerber = manufactureBewerber(stgId);
		bereiche = manufactureBereichList(stgId);
		bereichId = bereiche.get(3).getId();
		bereichIdArray = manufactureBereichArray(bereiche);
		faecher = manufactureFachList(bereichIdArray);
		ects = manufactureEcts(bewerber.getId());
		archivStudiengaenge = manufactureStgArchList(master_studiengaenge.get(1), 3);
		archivBereiche = manufactureBerArchList(bereiche.get(1), 3);
		codeArray = new ArrayList<>();
		codeArray.add(bewerber.getCodenummer());

		return null;
	}

	public BereichArchive manufactureBerArch(Bereich ber) {

		BereichArchive berArch = TestUtils.manufacturePojo(BereichArchive.class);
		berArch.setOriginId(ber.getId());
		berArch.setStgId(ber.getStgId());
		berArch.setName(ber.getName());
		berArch.setSessionId(0L);
		berArch.setPunkte(ber.getPunkte());
		berArch.setId(berArchDao.insert(berArch));

		return berArch;
	}

	public List<BereichArchive> manufactureBerArchList(Bereich ber, int anzahl) {

		List<BereichArchive> berArchs = new ArrayList<>();

		for (int i = 0; i < anzahl; i++) {
			BereichArchive berArch = manufactureBerArch(ber);
			berArchs.add(berArch);
		}

		return berArchs;
	}

	public Bereich manufactureBereich(Long stgId) {

		Bereich bereich = TestUtils.manufacturePojo(Bereich.class);
		bereich.setStgId(stgId);
		bereich.setId(bereichDao.insert(bereich));

		return bereich;
	}

	public List<Long> manufactureBereichArray(List<Bereich> bereichList) {

		List<Long> test = new ArrayList<>();

		for (int i = 0; i < bereichList.size(); i++) {
			test.add(bereichList.get(i).getId());
		}

		return test;
	}

	public List<Bereich> manufactureBereichList(Long stgId) {

		List<Bereich> bereiche = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			bereiche.add(manufactureBereich(stgId));
		}

		return bereiche;
	}

	public Bewerber manufactureBewerber(Long stgId) {

		Bewerber bewerber = TestUtils.manufacturePojo(Bewerber.class);
		bewerber.setStgId(stgId);
		bewerber.setStgName(cosShorttextDe);
		bewerber.setCodenummer("123456789012");
		bewerber.setSemester("MD-20191");
		bewerber.setId(bewDao.insert(bewerber));

		return bewerber;
	}

	public CourseOfStudy manufactureCos(Long degreeId) {

		CourseOfStudy cos = TestUtils.manufacturePojo(CourseOfStudy.class);
		cos.setDegreeid(degreeId);
		cos.setId(cosDao.insert(cos));

		return cos;
	}

	public List<CourseOfStudy> manufactureCosArray(Long degreeId, int anzahl) {

		List<CourseOfStudy> cosList = new ArrayList<>();

		for (int i = 0; i < anzahl; i++) {
			CourseOfStudy cos = manufactureCos(degreeId);
			cosList.add(cos);
		}

		return cosList;
	}

	public Degree manufactureDeg() {

		Degree deg = TestUtils.manufacturePojo(Degree.class);
		deg.setId(degDao.insert(deg));

		return deg;
	}

	public Ects manufactureEcts(Long bewId) {

		Ects ects = TestUtils.manufacturePojo(Ects.class);
		ects.setBewId(bewId);
		ects.setFachId(faecher.get(0).getId());
		ects.setBereichId(faecher.get(0).getBereichId());
		ects.setBereichPunkte(null);
		ects.setGesamtPunkte(null);
		ects.setId(ectsDao.insert(ects));

		return ects;
	}

	public Fach manufactureFach(Long bereichId) {

		Fach fach = TestUtils.manufacturePojo(Fach.class);
		fach.setBereichId(bereichId);
		fach.setId(fachDao.insert(fach));

		return fach;
	}

	public List<Fach> manufactureFachList(List<Long> bereichIds) {

		List<Fach> faecher = new ArrayList<>();

		for (int i = 0; i < bereichIds.size(); i++) {
			Fach fach1 = manufactureFach(bereichIds.get(i));
			Fach fach2 = manufactureFach(bereichIds.get(i));
			faecher.add(fach1);
			faecher.add(fach2);
		}

		return faecher;
	}

	public Degree manufactureMasterDeg() {

		Degree deg = TestUtils.manufacturePojo(Degree.class);
		degDao.insert(deg);

		return deg;
	}

	public List<Studiengangsauswahl> manufactureMasterStgArray(List<CourseOfStudy> courses, int anzahl) {

		List<Studiengangsauswahl> stgs = new ArrayList<>();

		for (CourseOfStudy cos : courses) {
			for (int i = 0; i < anzahl; i++) {
				Studiengangsauswahl stg = manufactureStg(cos.getId());
				stg.setShorttextDe(cos.getShorttextDe());
				stgs.add(stg);
			}
		}

		return stgs;
	}

	public Studiengangsauswahl manufactureStg(Long cosId) {

		Studiengangsauswahl stg = TestUtils.manufacturePojo(Studiengangsauswahl.class);
		stg.setCourseofstudyId(cosId);
		stg.setStandardkz(null);
		stg.setExtrakz(null);
		stg.setId(stgDao.insert(stg));

		return stg;
	}

	public Studiengangsauswahl manufactureStg(	Long cosId, Integer aktiv_vorab, Integer aktiv_vervollst,
												String cosShorttextDe) {

		Studiengangsauswahl stg = TestUtils.manufacturePojo(Studiengangsauswahl.class);
		stg.setStandardkz(null);
		stg.setCourseofstudyId(cosId);
		stg.setExtrakz(null);
		stg.setAktivVorab(aktiv_vorab);
		stg.setAktivVervollst(aktiv_vervollst);
		stg.setAos(false);
		stg.setShorttextDe(cosShorttextDe);
		stg.setId(stgDao.insert(stg));

		return stg;
	}

	public StudiengangsauswahlArchive manufactureStgArch(Studiengangsauswahl stg) {

		StudiengangsauswahlArchive stgArch = TestUtils.manufacturePojo(StudiengangsauswahlArchive.class);
		stgArch.setOriginId(stg.getId());
		stgArch.setCourseofstudyId(stg.getCourseofstudyId());
		stgArch.setAktivVorab(stg.getAktivVorab());
		stgArch.setAktivVervollst(stg.getAktivVervollst());
		stgArch.setName(stg.getName());
		stgArch.setProzentsatz(stg.getProzentsatz());
		stgArch.setStandardkz(stg.getStandardkz());
		stgArch.setExtrakz(stg.getExtrakz());
		stgArch.setId(stgArchDao.insert(stgArch));

		return stgArch;
	}

	public List<StudiengangsauswahlArchive> manufactureStgArchList(Studiengangsauswahl stg, int anzahl) {

		List<StudiengangsauswahlArchive> stgArchs = new ArrayList<>();

		for (int i = 0; i < anzahl; i++) {
			StudiengangsauswahlArchive stgArch = manufactureStgArch(stg);
			stgArchs.add(stgArch);
		}

		return stgArchs;
	}

	public List<Studiengangsauswahl> manufactureStgArray(Long cosId) {

		List<Studiengangsauswahl> stgs = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			Studiengangsauswahl stg1 = manufactureStg(cosId, 1, 1, cosShorttextDe);
			Studiengangsauswahl stg2 = manufactureStg(cosId, 0, 0, cosShorttextDe);
			stgs.add(stg1);
			stgs.add(stg2);
		}

		return stgs;
	}
}
