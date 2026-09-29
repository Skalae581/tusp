package de.tuhh.tusp.app.precheck.dao;

import java.util.List;
import java.util.Map;

import org.jooq.Record1;

import de.tuhh.tusp.app.precheck.pojo.Studiengang;
import de.tuhh.tusp.utils.BaseDao;

public interface StudiengangDao extends BaseDao<Studiengang> {

	Studiengang fetchByCoursOfStudyId(Long l);

	List<Studiengang> findByAktiv();

	List<Map<String, Object>> findLabeldAll();

	Record1<String> findLabelNameById(Long id);

	List<Map<String, Object>> findLabelNames();
}
