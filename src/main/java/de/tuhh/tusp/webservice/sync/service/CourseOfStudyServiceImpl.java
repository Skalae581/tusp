package de.tuhh.tusp.webservice.sync.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import de.his.ws.courseofstudyservice.CourseOfStudyServicePortType;
import de.his.ws.courseofstudyservice.SearchedCos;
import de.his.ws.courseofstudyservice.SearchedCosType;
import de.his.ws.courseofstudyservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.sync.dao.CourseOfStudyDao;
import de.tuhh.tusp.webservice.sync.main.event.StudiengangRefreshEvent;
import de.tuhh.tusp.webservice.sync.pojo.BasePojo;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class CourseOfStudyServiceImpl extends WebserviceSyncImpl implements CourseOfStudyService {

	private static final int ORDER_NUM = 6;

	@Autowired
	protected CourseOfStudyDao dao;

	@Autowired
	private WebserviceFactory webserviceFactory;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public CourseOfStudy fetchById(Long hisId) {

		return dao.fetchById(hisId);
	}

	@Override
	public List<CourseOfStudy> findByStgUndAbschl(String stg, String abschl) {

		return dao.findByStgUndAbschl(stg, abschl);
	}

	@Override
	public String getAbschlussFromCourseOfStudy(CourseOfStudy courseOfStudy) {

		String abschluss = null;

		if (courseOfStudy != null) {
			String uname = courseOfStudy.getUniquename();

			String[] splitted = uname.split("\\|");
			abschluss = splitted[0];
		}

		return abschluss;

	}

	@Override
	public String getPOVersionFromCourseOfStudy(CourseOfStudy courseOfStudy) {

		String year = null;

		if (courseOfStudy != null) {
			String uname = courseOfStudy.getUniquename();

			String[] splitted = uname.split("\\|");
			year = splitted[5];
		}

		return year;

	}

	@Override
	public String getStgNameFromCourseOfStudy(CourseOfStudy courseOfStudy) {

		String name = null;

		if (courseOfStudy != null) {
			String uname = courseOfStudy.getUniquename();

			String[] splitted = uname.split("\\|");
			name = splitted[1];
		}

		return name;

	}

	@Override
	public String getVertiefungFromCourseOfStudy(CourseOfStudy courseOfStudy) {

		String vertiefung = null;

		if (courseOfStudy != null) {
			String uname = courseOfStudy.getUniquename();

			String[] splitted = uname.split("\\|");
			vertiefung = splitted[2];
		}

		return vertiefung;

	}

	@Override
	public int orderNum() {

		return ORDER_NUM;
	}

	/**
	 * {@inheritDoc}
	 *
	 * Löst ein {@link StudiengangRefreshEvent} aus.
	 */
	@Override
	public void sync() {

		CourseOfStudyServicePortType port =
				webserviceFactory.getWebservicePort(de.his.ws.courseofstudyservice.CourseOfStudyService.class);

		SearchedCosType courses;
		try {
			courses = port.findCourseOfStudy("", "", "", "", "", "");
		}
		catch (ServiceFault_Exception e) {
			throw new RuntimeException("Konnte CourseOfStudies nicht abrufen.", e);
		}
		List<BasePojo> serviceData = new ArrayList<>();
		for (SearchedCos cos : courses.getSearchedCos()) {

			de.his.ws.courseofstudyservice.CourseOfStudy courseOfStudy;
			try {
				// Leider muss jeder Datensatz einzeln geleaden werden
				courseOfStudy = port.getCourseOfStudyById(cos.getCourseofstudyId());
			}
			catch (ServiceFault_Exception e) {
				throw new RuntimeException("Konnte CourseOfStudy nicht abrufen.", e);
			}

			CourseOfStudy c = new CourseOfStudy();
			c.setId(courseOfStudy.getId());
			c.setLid(courseOfStudy.getLid());
			c.setUniquename(courseOfStudy.getUniquename());
			c.setDegreeid(courseOfStudy.getDegreeId());
			c.setDefaulttextDe(courseOfStudy.getDefaulttext());
			c.setShorttextDe(courseOfStudy.getShorttext());
			c.setLongtextDe(courseOfStudy.getLongtext());
			c.setDefaulttextEn(courseOfStudy.getDefaulttext());
			c.setShorttextEn(courseOfStudy.getShorttext());
			c.setLongtextEn(courseOfStudy.getLongtext());
			c.setValid(true);

			serviceData.add(c);
		}

		syncData(serviceData, dao);
		applicationEventPublisher.publishEvent(new StudiengangRefreshEvent());

	}

}
