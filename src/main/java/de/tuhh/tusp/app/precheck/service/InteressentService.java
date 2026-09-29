package de.tuhh.tusp.app.precheck.service;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.jooq.Record1;
import org.jooq.TableField;
import org.jooq.generated.tables.records.PrecheckInteressentRecord;

import de.tuhh.tusp.app.precheck.dao.InteressentDao.ExtendedInteressent;
import de.tuhh.tusp.app.precheck.pojo.Interessent;

public interface InteressentService {

	void delete(Long id);

	Interessent fetchById(Long id);

	List<Interessent> findAll();

	List<ExtendedInteressent> findAllFilteredExtended(String countryId, String statusId, String stgId, String done,
			String zwilinge, String search, int elementList, int currentPage,
			TableField<PrecheckInteressentRecord, ?> order, boolean doubles, boolean doubles_mail);

	List<Map<String, Object>> findAllStatuses();

	Record1<String> findCountryNameById(Long id);

	Record1<String> findGenderNameById(Integer id);

	List<Map<String, Object>> findGenderNames();

	boolean getCodeIfExists(String code);

	int getCount();

	String getZiffer(Interessent interessent);

	void insert(Interessent interessent);

	void sendErgebnisMail(String ergebnis, String to, String from, Interessent interessent)
			throws AddressException, MessagingException;

	void update(Interessent interessent);
}
