package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.courseofstudyservice.CourseOfStudy;
import de.his.ws.courseofstudyservice.CourseOfStudyService;
import de.his.ws.courseofstudyservice.CourseOfStudyServicePortType;
import de.his.ws.courseofstudyservice.SearchedCos;
import de.his.ws.courseofstudyservice.SearchedCosType;
import de.his.ws.courseofstudyservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.sync.dao.DegreeDao;
import de.tuhh.tusp.webservice.sync.dao.SubjectDao;
import de.tuhh.tusp.webservice.sync.pojo.Subject;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.service.TuHHStgService;

/**
 *
 * @author jjakus Diese Klasse sucht nach einem vorhandenen Studiengang bei HIO
 *         über Subject ID
 *
 */
@Component
public class HisCourseOfStudyHelperDaoImpl implements HisCourseOfStudyHelperDao {

	private static Logger logger = LogManager.getLogger(HisCourseOfStudyHelperDaoImpl.class);

	@Autowired
	private WebserviceFactory serviceFactory;

	private CourseOfStudyServicePortType courseOfStudyService;

	@Autowired
	private DegreeDao degreeDao;

	@Autowired
	private TuHHStgService tuHHStgService;

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private ConfigService config;

	@Override
	public String findUniquenameByUniHHAstat(String astatUnihh) {

		if (astatUnihh == null) {
			return null;
		}

		String stg = tuHHStgService.getStgByAstatUni(astatUnihh);

		if (stg == null) {
			logger.warn("TUHH-Studiengang konnte nicht gefunden werden für astat " + astatUnihh);
			return null;
		}

		Subject subject = subjectDao.fetchByUniquename(stg);

		if (subject == null) {
			logger.warn("Subject konnte nicht gefunden werden für astat " + astatUnihh);
			return null;
		}

		String courseOfStudyName = findUniqunameBySubjectLid(subject.getLid());

		logger.debug("Für UHH-Astat-ID [" + astatUnihh + "] wurde Studiengang [" + courseOfStudyName
				+ "] mit  SubjectID [" + subject.getLid() + "] gefunden");
		return courseOfStudyName;
	}

	@Override
	public String findUniquenameForMobilityOnline() {

		String mobilityonlineStg = config.get("de.tuhh.tusp.psv.mobon.ImportStudiengang", "IO", true);
		Long subjectLid = subjectDao.fetchByUniquename(mobilityonlineStg).getLid();
		Long degreeLid = degreeDao.fetchByUniquename(mobilityonlineStg).getLid();

		SearchedCosType cosType = null;
		try {
			cosType = getPort().findCourseOfStudy("", "", subjectLid + "", degreeLid + "", "", "");
		} catch (ServiceFault_Exception e) {
			logger.error("Error! Course Of Study does not exist!");
			throw new ServiceFault(e);
		}

		if (cosType.getSearchedCos().isEmpty()) {
			throw new IllegalStateException("Kein Studiengang für MobilityOnline gefunden");
		}

		return getNeuestePO(cosType);

	}

	@Override
	public String findUniqunameBySubjectLid(Long subjectLid) {

		if (subjectLid == null) {
			return null;
		}

		SearchedCosType cosType = null;
		try {
			// TODO Das sollte in die courseofstudyservice_courseofstudy-Tabelle
			// aufgenommen werden zum Cachen!
			cosType = getPort().findCourseOfStudy("", "", subjectLid + "", "", "", "");
			logger.debug("Gefundene Studiengänge Typen: [" + cosType.getSearchedCos().size() + "]");
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}

		return getNeuestePO(cosType);
	}

	@Override
	public List<Long> getAllCourseOfStudiesIds() throws ServiceFault_Exception {

		List<Long> cosIds = new ArrayList<>();

		SearchedCosType cosType = getPort().findCourseOfStudy("", "", "", "", "", "");

		for (SearchedCos obj : cosType.getSearchedCos()) {
			cosIds.add(obj.getCourseofstudyId());
		}
		return cosIds;

	}

	@Override
	public CourseOfStudy getCourseOfStudyById(Long id) throws ServiceFault_Exception {
		return getPort().getCourseOfStudyById(id);
	}

	private List<SearchedCos> getHaugb(SearchedCosType searchedCosType) {

		List<SearchedCos> hauptgb = new ArrayList<>();
		for (SearchedCos cos : searchedCosType.getSearchedCos()) {

			String uname = cos.getUniquename();
			String[] splitted = uname.split("\\|");

			if (splitted[6].equals("1")) {
				hauptgb.add(cos);
			} else if (splitted[6].equals("-")) {
				logger.debug("Es gibt nur Szudiengänge ohne Hauptstandort");
			}

		}
		// wenn keiener mit hauptstandort = 1 gib einfach alle wieder zurück
		if (hauptgb.isEmpty()) {
			for (SearchedCos cos : searchedCosType.getSearchedCos()) {
				hauptgb.add(cos);
			}

			return hauptgb;
		}

		return hauptgb;

	}

	/**
	 * Sucht aus der Liste der POs die mit der höchsten Jahreszahl.
	 *
	 * @param searchedCosType
	 * @return der Uniquename oder null wenn Liste leer ist
	 */
	private String getNeuestePO(SearchedCosType searchedCosType) {

		if (searchedCosType.getSearchedCos().isEmpty()) {
			return null;
		}

		List<SearchedCos> hauptgb = getHaugb(searchedCosType);

		List<Long> years = new ArrayList<>();
		Map<Long, String> map = new HashMap<>();

		for (SearchedCos cos : hauptgb) {

			String uname = cos.getUniquename();

			try {
				String[] splitted = uname.split("\\|");

				map.put(Long.parseLong(splitted[5]), uname);
				years.add(Long.parseLong(splitted[5]));

			} catch (NumberFormatException e) {
				throw new NumberFormatException("HIO PO-Version ist keine Zahl in:" + uname);
			}

		}

		Collections.sort(years);
		String ret = map.get(years.get(years.size() - 1));
		logger.debug("Gefunden wurde Studiengang " + ret);
		return ret;
	}

	/**
	 * ServicePort Initialisierung
	 *
	 * @return der Port
	 */
	private CourseOfStudyServicePortType getPort() {

		if (courseOfStudyService == null) {
			courseOfStudyService = serviceFactory.getWebservicePort(CourseOfStudyService.class);
		}

		return courseOfStudyService;
	}

}
