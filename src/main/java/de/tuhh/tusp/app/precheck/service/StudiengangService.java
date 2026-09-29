package de.tuhh.tusp.app.precheck.service;

import java.util.List;
import java.util.Map;

import org.jooq.Record1;

import de.tuhh.tusp.app.precheck.pojo.Studiengang;

public interface StudiengangService {

	void delete(Long id);

	Studiengang fetchByCoursOfStudyId(Long id);

	Studiengang fetchById(Long id);

	List<Studiengang> findAll();

	List<Studiengang> findByAktiv();

	List<Map<String, Object>> findLabeldAll();

	Record1<String> findLabelNameById(Long stgId);

	List<Map<String, Object>> findLabelNames();

	void insert(Studiengang studiengang);

	void update(Studiengang studiengang);
}
