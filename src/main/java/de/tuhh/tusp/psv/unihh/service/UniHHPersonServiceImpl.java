package de.tuhh.tusp.psv.unihh.service;

import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.psv.UpdateStudentFileWriter;
import de.tuhh.tusp.psv.unihh.dao.UniHHPersonDao;
import de.tuhh.tusp.psv.unihh.main.UniHHImport;
import de.tuhh.tusp.psv.unihh.main.UniHHImportImpl;
import de.tuhh.tusp.psv.unihh.pojo.UniHHPerson;
import de.tuhh.tusp.psv.unihh.pojo.UniHHStud;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;
import de.tuhh.tusp.utils.ssh.SshSession;
import de.tuhh.tusp.utils.ssh.SshUtils;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;
import de.tuhh.tusp.webservice.wsoutput.service.HisCourseOfStudyServiceOutput;
import de.tuhh.tusp.webservice.wsoutput.service.HisDataServiceOutput;

@Component
public class UniHHPersonServiceImpl implements UniHHPersonService {

	private static Logger logger = LogManager.getLogger(UniHHPersonServiceImpl.class);

	@Autowired
	private UniHHPersonDao uniHHPersonDao;

	@Autowired
	private HisDataServiceOutput hisDataServiceOutput;

	@Autowired
	private HisCourseOfStudyServiceOutput hisCourseOfStudyHelper;

	@Autowired
	private UpdateStudentFileWriter updateStudentFileWriter;

	@Autowired
	private ConfigService config;

	@Autowired
	private SshUtils sshUtils;

	private void distributeFileToAppServers(WritableResource inFile) {

		int port = config.get("de.tuhh.tusp.psv.gwl.service.fileOutput.port", 22, true);
		String user = config.get("de.tuhh.tusp.psv.gwl.service.fileOutput.user", "", true);
		String remoteHosts = config.get("de.tuhh.tusp.psv.gwl.service.fileOutput.remoteHost", "", true);
		String remotePath = config.get("de.tuhh.tusp.psv.gwl.service.fileOutput.remotePath", "", true);

		if (user.isEmpty() || remoteHosts.isEmpty() || remotePath.isEmpty()) {
			throw new RuntimeException(
					"UniHH-Output ist nicht konfiguriert. Keys: de.tuhh.tusp.psv.gwl.service.fileOutput.* ");
		}

		if (inFile == null) {
			throw new NullPointerException("UniHH-Datei ist null");
		}

		MessageCollector collector = MessageCollectorImpl.getInstance(Thread.currentThread());

		// Dokument auf Zielsysteme verteilen
		String[] hosts = remoteHosts.split(",");
		for (String host : hosts) {
			try {
				SshSession ssh = sshUtils.getSession(user, host, port);
				ssh.copyToRemote(inFile, remotePath + inFile.getFilename());
				ssh.close();
			} catch (Exception e) {
				String message = "Konnte UniHH-File nicht an " + host + " übertragen";
				logger.error(message, e);
				collector.addErrorMessage(message + ": " + e.getMessage());
			}
		}

	}

	@Override
	public void importHHData(List<UniHHPerson> personList, List<UniHHStud> studList, UniHHImport threat) {

		MessageCollector mc = MessageCollectorImpl.getInstance(UniHHImportImpl.MESSAGE_COLLECTOR_NAME, null);

		GregorianCalendar now = new GregorianCalendar();
		String filename = "gewerbelehrer" + "-" + GregorianCalendar.DATE + "-" + now.get(GregorianCalendar.HOUR_OF_DAY)
				+ ".txt";
		InMemoryResource outFile = new InMemoryResource(filename);

		for (UniHHPerson person : personList) {

			HisPerson hisPerson = null;
			try {
				hisPerson = uniHHPersonDao.convertPersonData(person);
			} catch (Exception e) {
				String message = "Datensatzt fehlerhaft, kann nicht konvertiert werden: " + person.toShortString();
				logger.error(message, e);
				mc.addErrorMessage(message);
				continue;
			}

			Long mtknr = hisPerson.getIdExtern();

			for (UniHHStud studData : studList) {

				try {
					if (!mtknr.equals(studData.getUhhMtknr())) {
						continue;
					}

					String stgUniqueName = hisCourseOfStudyHelper
							.findUniquenameByUniHHAstat(studData.getStgUnihhAstat());

					if (stgUniqueName == null) {
						continue; // Abbrechen wenn kein Studienagng
					}

					HisStudent student = new HisStudent();
					student.setExterneMtknr(studData.getUhhMtknr());
					student.setUnihhAstatStg(studData.getStgUnihhAstat());
					student.setSurname(hisPerson.getSurname());
					student.setFirstname(hisPerson.getFirstname());
					student.setGenderHiskey(hisPerson.getGenderId());
					student.setIsCandidateStudent(Boolean.FALSE);
					student.setCourseOfStudyUniquename(stgUniqueName);
					student.setStgSemester(Long.valueOf(studData.getStgsem()));
					student.setAbschl(studData.getAbschl().toString());
					student.setBeugrund(studData.getBeugrund());
					student.setHrst(studData.getHrst());
					student.setKzfa(studData.getKzfa());
					student.setSemester(studData.getSemester().toString());
					student.setStatus(studData.getStatus());
					student.setStgnr(studData.getStgnr().toString());
					student.setStgsem(studData.getStgsem().toString());
					student.setStuart(studData.getStuart());
					student.setStutyp(studData.getStutyp());
					student.setStuform(studData.getStufrm().toString());

					hisDataServiceOutput.savePerson(hisPerson, student, mc, outFile, updateStudentFileWriter);

					mc.addOkMessage(hisPerson.toShortString() + " " + studData.getStgUnihhAstat());
				} catch (Exception e) {
					String message = "Person mit MTKNR {" + hisPerson.getIdExtern()
							+ "} konnte nicht bearbeitet werden!";
					logger.error(message, e);
					mc.addErrorMessage(message);
					continue;
				}
			}
		}

		distributeFileToAppServers(outFile);
	}
}
