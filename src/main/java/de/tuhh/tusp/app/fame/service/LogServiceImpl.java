package de.tuhh.tusp.app.fame.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.dao.LogDao;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.Log;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedLog;
import de.tuhh.tusp.utils.AuthorityUtils;

@Component
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao dao;

	@Autowired
	private AuthorityUtils authorityUtils;

	@Override
	public List<ExtendedLog> findByVorgang(Long vorgangId) {

		return dao.findByVorgang(vorgangId);
	}

	@Override
	public void logChange(String message, Long vorgangId) {

		Log log = new Log();
		log.setChangeTime(new Date());
		log.setSubject(message);
		log.setUser(authorityUtils.getCurrentUserName());
		log.setValueId(0l);
		log.setVorgangId(vorgangId);
		dao.insert(log);
	}

	@Override
	public void logStatusChange(Status from, Status to, Long vorgangId) {

		Log log = new Log();
		log.setChangeTime(new Date());
		log.setSubject(String.format("Statusänderung von %s nach %s", from.getName(), to.getName()));
		log.setUser(authorityUtils.getCurrentUserName());
		log.setValueId(-1l);
		log.setVorgangId(vorgangId);
		dao.insert(log);
	}

	@Override
	public void logValueChange(Input input, Value value) {

		Log log = new Log();
		log.setChangeTime(new Date());
		log.setSubject("'" + input.getName() + "' wurde aktualisiert");
		log.setUser(authorityUtils.getCurrentUserName());
		log.setValueId(value.getId());
		log.setVorgangId(value.getVorgangId());
		dao.insert(log);
	}

}
