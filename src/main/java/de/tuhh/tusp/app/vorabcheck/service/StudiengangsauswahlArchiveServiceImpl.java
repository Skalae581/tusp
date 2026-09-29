package de.tuhh.tusp.app.vorabcheck.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.controllers.VorabcheckAdminController;
import de.tuhh.tusp.app.vorabcheck.dao.StudiengangsauswahlArchiveDao;
import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.BereichArchive;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.app.vorabcheck.pojo.FachArchive;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.app.vorabcheck.pojo.StudiengangsauswahlArchive;

@Component
public class StudiengangsauswahlArchiveServiceImpl implements StudiengangsauswahlArchiveService {

	private static Logger logger = LogManager.getLogger(VorabcheckAdminController.class);

	@Autowired
	private StudiengangsauswahlArchiveDao studiengangsauswahlArchiveDao;

	@Autowired
	private StudiengangsauswahlService studiengangsauswahlService;

	@Autowired
	private BereichService bereichService;

	@Autowired
	private FachService fachService;

	@Autowired
	private BereichArchiveService bereichArchiveService;

	@Autowired
	private FachArchiveService fachArchiveService;

	@Override
	public void archive(Long stgId, String grund) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String bearbeiter = auth.getName();

		Studiengangsauswahl stg = studiengangsauswahlService.fetchById(stgId);

		try {
			StudiengangsauswahlArchive studiengangsauswahlArchive = new StudiengangsauswahlArchive();
			studiengangsauswahlArchive = setArchive(studiengangsauswahlArchive, stg, bearbeiter, grund);

			Long newStgId = insert(studiengangsauswahlArchive);

			StudiengangsauswahlArchive newArchive = getNewData(newStgId);
			Long sessionId = newArchive.getSessionId();

			List<Bereich> bereichList = bereichService.findAllByStgId(stgId);

			for (Bereich bereich : bereichList) {
				Long bereichId = bereich.getId();

				BereichArchive bereichArchive = new BereichArchive();
				bereichArchiveService.setArchive(bereichArchive, bereich, newStgId, sessionId);

				try {

					Long newBereichId = bereichArchiveService.insert(bereichArchive);

					List<Fach> fachList = fachService.findAllByBereichId(bereichId);

					for (Fach fach : fachList) {
						FachArchive fachArchive = new FachArchive();
						fachArchiveService.setArchive(fachArchive, fach, newBereichId, sessionId);

						try {
							fachArchiveService.insert(fachArchive);
						} catch (Exception e) {
							logger.error("Fach konnte nicht archiviert werden", e);
						}
					}
				} catch (Exception e) {
					logger.error("Bereich konnte nicht archiviert werden", e);
				}
			}
		} catch (Exception e) {
			logger.error("Studiengang konnte nicht archiviert werden", e);
		}

	}

	@Override
	public int delete(Long id) {

		return studiengangsauswahlArchiveDao.delete(id);
	}

	@Override
	public StudiengangsauswahlArchive fetchById(Long id) {

		return studiengangsauswahlArchiveDao.fetchById(id);
	}

	@Override
	public StudiengangsauswahlArchive fetchByOriginId(Long originId) {

		return studiengangsauswahlArchiveDao.fetchByOriginId(originId);
	}

	// @Override
	// public StudiengangsauswahlArchive fetchByOriginId(Long stgId) {
	//
	// return studiengangsauswahlArchiveDao.fetchByOriginId(stgId);
	// }

	@Override
	public List<StudiengangsauswahlArchive> findAll() {

		return studiengangsauswahlArchiveDao.findAll();
	}

	@Override
	public List<Map<String, Object>> findAllByOriginId(Long stgId) {

		return studiengangsauswahlArchiveDao.findAllByOriginId(stgId);
	}

	@Override
	public List<Map<String, Object>> findArchiveOriginIds() {

		return studiengangsauswahlArchiveDao.findArchiveOriginIds();
	}

	@Override
	public StudiengangsauswahlArchive getNewData(Long newStgId) {

		return studiengangsauswahlArchiveDao.getNewData(newStgId);
	}

	@Override
	public Long insert(StudiengangsauswahlArchive object) {

		return studiengangsauswahlArchiveDao.insert(object);
	}

	/**
	 * Erstellt ein Objekt der Klasse StudiengangsauswahlArchive aus einem Objekt
	 * der Klasse Studiengangsauswahl
	 *
	 * @return Objekt der Klasse StudiengangsauswahlArchive
	 */
	@Override
	public StudiengangsauswahlArchive setArchive(StudiengangsauswahlArchive studiengangsauswahlArchive,
			Studiengangsauswahl stg, String bearbeiter, String grund) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		studiengangsauswahlArchive.setOriginId(stg.getId());
		studiengangsauswahlArchive.setCourseofstudyId(stg.getCourseofstudyId());
		studiengangsauswahlArchive.setAktivVorab(stg.getAktivVorab());
		studiengangsauswahlArchive.setAktivVervollst(stg.getAktivVervollst());
		studiengangsauswahlArchive.setProzentsatz(stg.getProzentsatz());
		studiengangsauswahlArchive.setStandardkz(stg.getStandardkz());
		studiengangsauswahlArchive.setZeitstempel(timestamp.toLocalDateTime());
		studiengangsauswahlArchive.setBearbeiter(bearbeiter);
		studiengangsauswahlArchive.setGrund(grund);

		return studiengangsauswahlArchive;
	}

	@Override
	public void update(StudiengangsauswahlArchive object) {

		studiengangsauswahlArchiveDao.update(object);
	}
}
