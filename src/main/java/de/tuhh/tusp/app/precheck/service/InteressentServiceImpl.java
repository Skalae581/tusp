package de.tuhh.tusp.app.precheck.service;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Record1;
import org.jooq.TableField;
import org.jooq.generated.tables.records.PrecheckInteressentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.precheck.dao.GradeDao;
import de.tuhh.tusp.app.precheck.dao.InteressentDao;
import de.tuhh.tusp.app.precheck.dao.InteressentDao.ExtendedInteressent;
import de.tuhh.tusp.app.precheck.dao.PrecheckStatusDao;
import de.tuhh.tusp.app.precheck.pojo.Interessent;
import de.tuhh.tusp.app.precheck.utils.BFormel;
import de.tuhh.tusp.utils.MD5;
import de.tuhh.tusp.utils.MailUtils;
import de.tuhh.tusp.utils.MessageContainer;

@Component
public class InteressentServiceImpl implements InteressentService {

	private static Logger logger = LogManager.getLogger(InteressentServiceImpl.class);
	@Autowired
	private InteressentDao interessentDao;

	@Autowired
	private StudiengangService stgService;

	@Autowired
	public GradeDao gradeDao;

	@Autowired
	public PrecheckStatusDao statusDao;

	@Autowired
	private MailUtils mailUtils;

	@Autowired
	private MessageContainer messageContainer;

	@Autowired
	private BFormel formel;

	@Autowired
	private MD5 md5Utils;

	@Override
	public void delete(Long id) {

		interessentDao.delete(id);
	}

	@Override
	public Interessent fetchById(Long id) {

		return interessentDao.fetchById(id);
	}

	@Override
	public List<Interessent> findAll() {

		return interessentDao.findAll();
	}

	@Override
	public List<ExtendedInteressent> findAllFilteredExtended(String countryId, String statusId, String stgId,
			String done, String zwilinge, String search, int elementList, int currentPage,
			TableField<PrecheckInteressentRecord, ?> order, boolean doubles, boolean doubles_mail) {

		return interessentDao.findAllFilteredExtended(countryId, statusId, stgId, done, zwilinge, search, elementList,
				currentPage, order, doubles, doubles_mail);
	}

	@Override
	public List<Map<String, Object>> findAllStatuses() {

		return interessentDao.findAllStatuses();
	}

	@Override
	public Record1<String> findCountryNameById(Long id) {

		return interessentDao.findCountryNameById(id);
	}

	@Override
	public Record1<String> findGenderNameById(Integer id) {

		return interessentDao.findGenderNameById(id);
	}

	@Override
	public List<Map<String, Object>> findGenderNames() {

		return interessentDao.findGenderNames();
	}

	@Override
	public boolean getCodeIfExists(String code) {

		return interessentDao.getCodeIfExists(code);
	}

	@Override
	public int getCount() {

		return interessentDao.getCount();
	}

	@Override
	public String getZiffer(Interessent interessent) {

		return md5Utils.md5(interessent.getEmail() + interessent.getStatus(), 12);
	}

	@Override
	public void insert(Interessent interessent) {

		float grade = formel.getGrade(interessent.getStudienland(), interessent.getInstitution());
		float max = interessent.getMaxNote().floatValue();
		float min = interessent.getMinNote().floatValue();
		float average = interessent.getErreichteNote().floatValue();

		Record1<String> record = stgService.findLabelNameById(interessent.getStgTuhh());
		if (record != null && "TIE".equals(record.get(0))) {
			interessent.setStatus(8L); // TIE
		} else {
			Long statusId = formel.calculateStatus(grade, max, min, average);
			interessent.setStatus(statusId);
		}
		float tuhhGrade = formel.calculateGrade(grade, max, min, average);
		interessent.setTuhhNote(tuhhGrade);

		String status = statusDao.fetchById(interessent.getStatus()).getName();
		if ("AKZEPTIERT".equals(status) || "FRAGLICH".equals(status)) { // ACCEPTED
			interessent.setVerifierCode(getZiffer(interessent));
		} else {
			interessent.setVerifierCode(null);
		}

		logger.debug("Deusche Note: " + interessent.getTuhhNote());

		interessentDao.insert(interessent);

	}

	@Override
	public void sendErgebnisMail(String ergebnis, String to, String from, Interessent interessent)
			throws AddressException, MessagingException {

		String mailTxt = "";

		String first = "Dear " + interessent.getVorname() + " " + interessent.getNachname() + ",<br />";
		mailTxt += first;

		if ("AKZEPTIERT".equals(ergebnis)) {
			mailTxt += messageContainer.getString("precheck.body.accepted");

			mailTxt += "<br /><br />";
			mailTxt += "Code: ";
			mailTxt += getZiffer(interessent);
			mailTxt += "<br /><br />";

			mailTxt += messageContainer.getString("precheck.body.accepted.rest");

		} else if ("FRAGLICH".equals(ergebnis)) {
			mailTxt += messageContainer.getString("precheck.body.pendant");

			mailTxt += "<br /><br />";
			mailTxt += "Code: ";
			mailTxt += getZiffer(interessent);
			mailTxt += "<br /><br />";

			mailTxt += messageContainer.getString("precheck.body.pendant.rest");
		} else if ("ABGELEHNT WG. NOTE".equals(ergebnis)) {
			mailTxt += messageContainer.getString("precheck.body.dec1");
		} else if ("ABGELEHNT WG. STG".equals(ergebnis)) {
			mailTxt += messageContainer.getString("precheck.body.dec2");
		} else if ("ABGELEHNT WG. (NT./STG./HS.)".equals(ergebnis)) {
			mailTxt += messageContainer.getString("precheck.body.dec3");
		} else if ("SONDERFALL TIE".equals(ergebnis)) {
			mailTxt += messageContainer.getString("precheck.body.tie");
		}

		String subject = messageContainer.getString("precheck.subject");
		subject += "   " + stgService.findLabelNameById(interessent.getStgTuhh()).value1();

		if (!"UNBEKANNT".equals(ergebnis)) {
			mailUtils.sendHTMLMail(to, from, subject, mailTxt);
		}

	}

	@Override
	public void update(Interessent interessent) {

		interessentDao.update(interessent);
	}

}
