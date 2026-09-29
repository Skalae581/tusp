package de.tuhh.tusp.app.precheck.service;

import java.util.List;
import java.util.Map;

import org.jooq.Record1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.precheck.dao.StudiengangDao;
import de.tuhh.tusp.app.precheck.pojo.Studiengang;
import de.tuhh.tusp.webservice.sync.dao.CourseOfStudyDao;
import de.tuhh.tusp.webservice.sync.main.event.StudiengangRefreshEvent;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;

@Component
public class StudiengangServiceImpl implements StudiengangService {

	@Autowired
	private StudiengangDao studiengangDao;

	@Autowired
	private CourseOfStudyDao courseOfStudyDao;

	@Override
	public void delete(Long id) {

		studiengangDao.delete(id);
	}

	@Override
	public Studiengang fetchByCoursOfStudyId(Long id) {

		return studiengangDao.fetchByCoursOfStudyId(id);
	}

	@Override
	public Studiengang fetchById(Long id) {

		return studiengangDao.fetchById(id);
	}

	@Override
	public List<Studiengang> findAll() {

		return studiengangDao.findAll();
	}

	@Override
	public List<Studiengang> findByAktiv() {

		return studiengangDao.findByAktiv();
	}

	@Override
	public List<Map<String, Object>> findLabeldAll() {

		return studiengangDao.findLabeldAll();
	}

	@Override
	public Record1<String> findLabelNameById(Long id) {

		return studiengangDao.findLabelNameById(id);
	}

	@Override
	public List<Map<String, Object>> findLabelNames() {

		return studiengangDao.findLabelNames();
	}

	@Override
	public void insert(Studiengang studiengang) {

		studiengangDao.insert(studiengang);
	}

	@EventListener
	public void refresh(StudiengangRefreshEvent evt) {

		for (CourseOfStudy course : courseOfStudyDao.findAll()) {
			try {
				studiengangDao.fetchByCoursOfStudyId(course.getId());
			} catch (DataAccessException e) {
				Studiengang studiengang = new Studiengang();
				studiengang.setAktiv(0);
				studiengang.setCourseofstudyId(course.getId());
				studiengangDao.insert(studiengang);
			}
		}
	}

	@Override
	public void update(Studiengang studiengang) {

		studiengangDao.update(studiengang);
	}

}
