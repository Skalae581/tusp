package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.dao.FachArchiveDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.app.vorabcheck.pojo.FachArchive;

@Component
public class FachArchiveServiceImpl implements FachArchiveService {

	@Autowired
	private FachArchiveDao fachArchiveDao;

	@Override
	public int delete(Long id) {

		return fachArchiveDao.delete(id);
	}

	@Override
	public FachArchive fetchById(Long id) {

		return fachArchiveDao.fetchById(id);
	}

	@Override
	public List<FachArchive> findAll() {

		return fachArchiveDao.findAll();
	}

	@Override
	public Long insert(FachArchive b) {

		return fachArchiveDao.insert(b);
	}

	/**
	 * Erstellt ein Objekt der Klasse FachArchive aus einem Objekt der Klasse Fach
	 *
	 * @return Objekt der Klasse StudiengangsauswahlArchive
	 */
	@Override
	public FachArchive setArchive(FachArchive fachArchive, Fach fach, Long newBereichId, Long sessionId) {

		fachArchive.setBereichId(newBereichId);
		fachArchive.setName(fach.getName());
		fachArchive.setPunkte(fach.getPunkte());
		fachArchive.setZusatz(fach.getZusatz());
		fachArchive.setSessionId(sessionId);

		return fachArchive;
	}

	// @Override
	// public FachArchive setClassObject(Fach object, Long newBereichId, Long sessionId) {
	// FachArchive fachArchive = new FachArchive();
	//
	// fachArchive.setBereichId(newBereichId);
	// fachArchive.setName(object.getName());
	// fachArchive.setPunkte(object.getPunkte());
	// fachArchive.setZusatz(object.getZusatz());
	// fachArchive.setSessionId(sessionId);
	//
	// return fachArchive;
	// }

	@Override
	public void update(FachArchive object) {

		fachArchiveDao.update(object);

	}

}
