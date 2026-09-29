package de.tuhh.tusp.webservice.sync.service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.dao.AcademicdegreeDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Academicdegree;
import de.tuhh.tusp.webservice.sync.pojo.Person;

@Component
public class AcademicdegreeServiceImpl extends AbstractHioSyncImpl implements AcademicdegreeService {

	@Autowired
	protected AcademicdegreeDao dao;

	@Override
	public String academicdegreeAsString(Person person) {

		return academicdegreeAsString(person, LocaleContextHolder.getLocale());
	}

	@Override
	public String academicdegreeAsString(Person person, Locale locale) {

		if (person == null) {
			return null;
		}
		if (person.getAcademicdegreeId() == null) {
			return null;
		}

		Academicdegree degree = dao.fetchById(person.getAcademicdegreeId());
		String localizedDegree;
		if (Locale.ENGLISH.equals(locale)) {
			localizedDegree = degree.getDefaulttextEn();
		}
		else {
			localizedDegree = degree.getDefaulttextDe();
		}

		if (person.getAcademicdegreeSuffix() == null) {
			return localizedDegree;
		}

		return localizedDegree + " " + person.getAcademicdegreeSuffix();
	}

	@Override
	public Academicdegree fetchByString(String degree) {

		if (degree == null || degree.isEmpty()) {
			return null;
		}

		List<Academicdegree> degrees = dao.findAll();

		List<Academicdegree> results;

		// Folgender Ablauf:
		// 1. null ausfiltern
		// 2. sortieren (längste zuerst)
		// 3. Einträge suchen mit denen der degree beginnt
		// 4. den ersten Treffer liefern.

		// getShorttextDe
		results = degrees	.stream().filter(i -> i.getShorttextDe() != null)
							.sorted((i, j) -> i.getShorttextDe().length() < j.getShorttextDe().length() ? 1 : -1)
							.filter(i -> startsWith(degree, i.getShorttextDe())).collect(Collectors.toList());
		if (results.size() > 0) {
			return results.get(0);
		}

		// getUniquename
		results = degrees	.stream().filter(i -> i.getUniquename() != null)
							.sorted((i, j) -> i.getUniquename().length() < j.getUniquename().length() ? 1 : -1)
							.filter(i -> startsWith(degree, i.getUniquename())).collect(Collectors.toList());
		if (results.size() > 0) {
			return results.get(0);
		}

		// getDefaulttextDe
		results = degrees	.stream().filter(i -> i.getDefaulttextDe() != null)
							.sorted((i, j) -> i.getDefaulttextDe().length() < j.getDefaulttextDe().length() ? 1 : -1)
							.filter(i -> startsWith(degree, i.getDefaulttextDe())).collect(Collectors.toList());
		if (results.size() > 0) {
			return results.get(0);
		}

		// getShorttextEn
		results = degrees	.stream().filter(i -> i.getShorttextEn() != null)
							.sorted((i, j) -> i.getShorttextEn().length() < j.getShorttextEn().length() ? 1 : -1)
							.filter(i -> startsWith(degree, i.getShorttextEn())).collect(Collectors.toList());
		if (results.size() > 0) {
			return results.get(0);
		}

		// getDefaulttextEn
		results = degrees	.stream().filter(i -> i.getDefaulttextEn() != null)
							.sorted((i, j) -> i.getDefaulttextEn().length() < j.getDefaulttextEn().length() ? 1 : -1)
							.filter(i -> startsWith(degree, i.getDefaulttextEn())).collect(Collectors.toList());
		if (results.size() > 0) {
			return results.get(0);
		}

		// getLongtextDe
		results = degrees	.stream().filter(i -> i.getLongtextDe() != null)
							.sorted((i, j) -> i.getLongtextDe().length() < j.getLongtextDe().length() ? 1 : -1)
							.filter(i -> startsWith(degree, i.getLongtextDe())).collect(Collectors.toList());
		if (results.size() > 0) {
			return results.get(0);
		}

		// getLongtextEn
		results = degrees	.stream().filter(i -> i.getLongtextEn() != null)
							.sorted((i, j) -> i.getLongtextEn().length() < j.getLongtextEn().length() ? 1 : -1)
							.filter(i -> startsWith(degree, i.getLongtextEn())).collect(Collectors.toList());
		if (results.size() > 0) {
			return results.get(0);
		}

		throw new RuntimeException("Kein Academicdegree gefunden für " + degree);
	}

	@Override
	public String fetchSuffixFromString(String title, Academicdegree degree) {

		if (title == null || title.isEmpty() || degree == null) {
			return title;
		}

		String prefix = "";
		if (startsWith(title, degree.getDefaulttextDe())) {
			prefix = degree.getDefaulttextDe();
		}
		else if (startsWith(title, degree.getDefaulttextEn())) {
			prefix = degree.getDefaulttextEn();
		}
		else if (startsWith(title, degree.getShorttextDe())) {
			prefix = degree.getShorttextDe();
		}
		else if (startsWith(title, degree.getShorttextEn())) {
			prefix = degree.getShorttextEn();
		}
		else if (startsWith(title, degree.getLongtextDe())) {
			prefix = degree.getLongtextDe();
		}
		else if (startsWith(title, degree.getLongtextEn())) {
			prefix = degree.getLongtextEn();
		}
		else if (startsWith(title, degree.getUniquename())) {
			prefix = degree.getUniquename();
		}
		else {
			throw new RuntimeException("Soll Academicdegree von [" + title + "] abspalten. Er ist aber nicht enthalten."
					+ degree);
		}

		return title.substring(prefix.length()).trim();
	}

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Academicdegree();
	}

	@Override
	protected String getValueClass() {

		return "AcademicdegreeValue";
	}

	@Override
	public int orderNum() {

		return 5;
	}

	@Override
	public void setAcademicdegree(Person person, String degree) {

		if (person == null) {
			return;
		}
		if (degree == null) {
			person.setAcademicdegreeId(null);
			person.setAcademicdegreeSuffix(null);
			return;
		}

		Academicdegree academicdegree = fetchByString(degree);
		person.setAcademicdegreeId(academicdegree.getId());
		person.setAcademicdegreeSuffix(fetchSuffixFromString(degree, academicdegree));

	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

	}

	/**
	 * Prüft ob ein haystack mit needle beginnt
	 *
	 * @param haystack
	 * @param needle
	 * @return true wenn ja
	 */
	private boolean startsWith(String haystack, String needle) {

		if (needle == null || haystack == null) {
			return false;
		}
		return haystack.startsWith(needle);
	}

}
