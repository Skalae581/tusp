package de.tuhh.tusp.exa.massmails.service;

import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.massmails.dao.HioPersonDao;
import de.tuhh.tusp.exa.massmails.dao.ReceiverDao;
import de.tuhh.tusp.exa.massmails.pojo.Receiver;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.utils.CalendarUtils;

@Component
public class ReceiverSyncServiceImpl implements ReceiverSyncService {

	private static Logger logger = LogManager.getLogger(ReceiverSyncServiceImpl.class);

	@Autowired
	private HioPersonDao personDao;

	@Autowired
	private ReceiverDao receiverDao;

	private void addPidToReceivers(List<Long> pids, String list, Long semester, JobLogger jobLogger) {

		logger.info("Verteiler " + list + "(" + semester + ") " + " bekommt " + pids.size() + " Einträge.");

		for (Long pid : pids) {
			try {
				Receiver r = personDao.getPersonData(pid);

				if (r == null) {
					logger.warn("Konnte person " + pid + " nicht zu Verteiler " + list
							+ " hinzufügen, weil sie nicht aus HIO geladen werden konnte.");
					continue;
				}

				r.setVerteilername(list);
				r.setSemester(semester);
				receiverDao.insert(r);
			} catch (Exception e) {
				logger.error("Sch*", e);
			}
		}
	}

	@Override
	public void sync(JobLogger jobLogger) {

		try {

			receiverDao.deleteAll();

			Long semester = Long.valueOf(CalendarUtils.getSemester(new GregorianCalendar()));
			syncSemester(semester, jobLogger);

			// nächstes semester
			GregorianCalendar nextSem = new GregorianCalendar();
			nextSem.add(GregorianCalendar.MONTH, 6);
			semester = Long.valueOf(CalendarUtils.getSemester(nextSem));
			syncSemester(semester, jobLogger);

			// vorheriges semester
			GregorianCalendar prevSem = new GregorianCalendar();
			prevSem.add(GregorianCalendar.MONTH, -6);
			semester = Long.valueOf(CalendarUtils.getSemester(prevSem));
			syncSemester(semester, jobLogger);

			// Ohne Semesterbezug
			List<Long> pids;

			pids = personDao.getLehrpersonIds();
			addPidToReceivers(pids, "Personen mit Rolle Lehrperson", 0l, jobLogger);

			pids = personDao.getLehrpersonVertreterIds();
			addPidToReceivers(pids, "Personen mit Rolle Lehrperson-Vertreter", 0l, jobLogger);

			pids = personDao.getPrueferIds();
			addPidToReceivers(pids, "Personen mit Rolle Prüfer", 0l, jobLogger);

			pids = personDao.getPrueferVertreterIds();
			addPidToReceivers(pids, "Personen mit Rolle Prüfervertreter", 0l, jobLogger);

		} finally {
			personDao.closeDbCon();
		}
	}

	private void syncSemester(Long semester, JobLogger jobLogger) {

		Long termYear = semester / 10;
		Long termtypeId = semester - 10 * termYear + 29;

		List<Long> pids;

		pids = personDao.getPrueferIds(termtypeId, termYear);
		addPidToReceivers(pids, "Personen mit Prüfungstätigkeit im Semeser", semester, jobLogger);

		pids = personDao.getPrueferVertreterIds(termtypeId, termYear);
		addPidToReceivers(pids, "Prüfervertreter mit Aktivität im Semester", semester, jobLogger);

		pids = personDao.getLehrendenIds(termtypeId, termYear);
		addPidToReceivers(pids, "Personen mit Lehrtätigkeit im Semester", semester, jobLogger);

		// TODO implement und in GUI freischalten
		// pids = personDao.getLehrpersonIds();
		// addPidToReceivers(pids, "Lehrbeauftragte im Semester", semester);

	}

}
