package de.tuhh.tusp.app.vorabcheck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.dao.StudiengangsauswahlDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Ects;
import de.tuhh.tusp.app.vorabcheck.pojo.EctsForm;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;

@Component
public class StudiengangsauswahlServiceImpl implements StudiengangsauswahlService {

	@Autowired
	private StudiengangsauswahlDao studiengangsauswahlDao;

	@Override
	public int delete(Long id) {

		return studiengangsauswahlDao.delete(id);
	}

	@Override
	public Studiengangsauswahl fetchById(Long id) {

		return studiengangsauswahlDao.fetchById(id);
	}

	@Override
	public List<Studiengangsauswahl> findAll() {

		return studiengangsauswahlDao.findAll();
	}

	@Override
	public List<Map<String, Object>> findAllActiveVervollstWithoutStandardkz() {

		return studiengangsauswahlDao.findAllActiveVervollstWithoutStandardkz();
	}

	@Override
	public List<Map<String, Object>> findAllBereichById(Long stgId) {

		return studiengangsauswahlDao.findAllBereichById(stgId);
	}

	@Override
	public List<Map<String, Object>> findAllFachByIds(List<Long> bereichIds) {

		return studiengangsauswahlDao.findAllFachByIds(bereichIds);
	}

	@Override
	public List<Map<String, Object>> findAllStg() {

		return studiengangsauswahlDao.findAllStg();
	}

	@Override
	public List<Map<String, Object>> findAllStgAOS() {

		return studiengangsauswahlDao.findAllStgAOS();
	}

	@Override
	public List<Map<String, Object>> findAllStgMD() {

		return studiengangsauswahlDao.findAllStgMD();
	}

	@Override
	public List<Map<String, Object>> findBereichStgId() {

		return studiengangsauswahlDao.findBereichStgId();
	}

	@Override
	public String getColor(Double bereichPunkte, Double gesamtPunkte, Long stgId) {

		double highest = 90.0;
		Studiengangsauswahl stg = studiengangsauswahlDao.fetchById(stgId);
		double prozent = Double.valueOf(stg.getProzentsatz());

		double gruen = bereichPunkte * highest / 100.0;
		double gelb = bereichPunkte * prozent / 100.0;

		if (Double.compare(gesamtPunkte, gruen) == 0 || Double.compare(gesamtPunkte, gruen) > 0) {
			return "gruen";
		} else if (Double.compare(gesamtPunkte, gruen) < 0
				&& (Double.compare(gesamtPunkte, gelb) == 0 || Double.compare(gesamtPunkte, gelb) > 0)) {
			return "gelb";
		} else {
			return "rot";
		}
	}

	@Override
	public String getResult(EctsForm ectsForm, Long stgId) {

		List<String> ergArray = new ArrayList<>();

		for (Ects ects : ectsForm.getEcts()) {
			if (ects.getGesamtPunkte() != null && ects.getGesamtPunkte() != '0') {
				String farbe;
				farbe = getColor(ects.getBereichPunkte(), ects.getGesamtPunkte(), stgId);
				ergArray.add(farbe);
			}
		}

		int green = 0;
		int red = 0;

		for (int i = 0; i < ergArray.size(); i++) {
			if (ergArray.get(i) == "gruen") {
				green++;
			} else if (ergArray.get(i) == "rot") {
				red++;
			}
		}

		if (ergArray.size() != 0) {
			if (green == ergArray.size()) {
				return "gruen";
			} else if (red > 0) {
				return "rot";
			} else {
				return "gelb";
			}
		} else {
			return "rot";
		}
	}

	@Override
	public String getShorttextDe(Long courseofstudyId) {

		return studiengangsauswahlDao.getShorttextDe(courseofstudyId);
	}

	@Override
	public Long insert(Studiengangsauswahl w) {

		return studiengangsauswahlDao.insert(w);
	}

	@Override
	public void update(Studiengangsauswahl studiengangsauswahl) {

		studiengangsauswahlDao.update(studiengangsauswahl);
	}
}
