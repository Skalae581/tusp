package de.tuhh.tusp.app.vorabcheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.dao.BereichArchiveDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.BereichArchive;

@Component
public class BereichArchiveServiceImpl implements BereichArchiveService {

	@Autowired
	private BereichArchiveDao bereichArchiveDao;

	@Override
	public int delete(Long id) {

		return bereichArchiveDao.delete(id);
	}

	@Override
	public BereichArchive fetchById(Long id) {

		return bereichArchiveDao.fetchById(id);
	}

	@Override
	public List<BereichArchive> findAll() {

		return bereichArchiveDao.findAll();
	}

	@Override
	public Long insert(BereichArchive object) {

		return bereichArchiveDao.insert(object);
	}

	/**
	 * Erstellt ein Objekt der Klasse BereichArchive aus einem Objekt der Klasse Bereich
	 *
	 * @return Objekt der Klasse BereichArchive
	 */
	@Override
	public BereichArchive setArchive(BereichArchive bereichArchive, Bereich bereich, Long newStgId, Long sessionId) {

		bereichArchive.setStgId(newStgId);
		bereichArchive.setName(bereich.getName());
		bereichArchive.setPunkte(bereich.getPunkte());
		bereichArchive.setZusatz(bereich.getZusatz());
		bereichArchive.setOriginId(bereich.getId());
		bereichArchive.setSessionId(sessionId);

		return bereichArchive;
	}

	@Override
	public void update(BereichArchive object) {

		bereichArchiveDao.update(object);
	}

}
