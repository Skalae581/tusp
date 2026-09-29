package de.tuhh.tusp.app.fame.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jooq.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.dao.InputBerechtigungDao;
import de.tuhh.tusp.app.fame.dao.InputDao;
import de.tuhh.tusp.app.fame.dao.ValueDao;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;

@Component
public class InputServiceImpl implements InputService {

	@Autowired
	private InputDao dao;

	@Autowired
	private InputBerechtigungDao berechtigungDao;

	@Autowired
	private ValueDao valueDao;

	@Override
	public int delete(Long id) {

		berechtigungDao.deleteByInput(id);
		valueDao.deleteByInput(id);

		return dao.delete(id);
	}

	@Override
	public Input fetchById(Long id) {

		return dao.fetchById(id);
	}

	@Override
	public List<Input> findAll() {

		return dao.findAll();
	}

	@Override
	public List<Input> findByWorkflow(Long id, Field<?> order) {

		return dao.findByWorkflow(id, order);
	}

	@Override
	public List<Input> findByWorkflowAndRoles(Long wokflowInstanceId, List<String> roles) {

		return dao.findByWorkflowAndRoles(wokflowInstanceId, roles);
	}

	@Override
	public List<ExtendedInput> findByWorkflowAndVorgang(Long workflowId, Long vorgangId) {

		return dao.findByWorkflowAndVorgang(workflowId, vorgangId);
	}

	@Override
	public List<ExtendedInput> findByWorkflowInstanceStatusAndRoles(Long instanceId, Long statusId,
																	List<String> roles) {

		return dao.findByWorkflowInstanceStatusAndRoles(instanceId, statusId, roles);
	}

	@Override
	public List<ExtendedInput> findByWorkflowInstanceVorgangAndRoles(	Long wokflowInstanceId, Long vorgangId,
																		List<String> roles) {

		return dao.findByWorkflowInstanceVorgangAndRoles(wokflowInstanceId, vorgangId, roles);
	}

	@Override
	public Long insert(Input s) {

		// Testen ob Options richtig formatiert seind
		parseOptions(s);

		// Der Rexex darf nicht escaped sein
		s.setRegex(StringEscapeUtils.unescapeHtml4(s.getRegex()));
		return dao.insert(s);
	}

	@Override
	public List<OptionsType> parseOptions(Input input) {

		if (input.getOptions() == null || input.getOptions().isEmpty()) {
			return null;
		}

		List<OptionsType> options = new ArrayList<>();
		for (String line : input.getOptions().split("\n")) {
			if (line.isEmpty()) {
				continue;
			}

			String[] parts = line.trim().split(";");

			if (parts.length != 2 && parts.length != 3) {
				throw new IllegalArgumentException("Die Options sind nicht richtig formatiert");
			}

			OptionsType ot = new OptionsType();
			ot.setId(parts[0]);
			ot.setName(parts[1]);
			if (parts.length == 3) {
				if (parts[2].equals("disabled")) {
					ot.setDisabled(true);
				}
				else {
					ot.setName(ot.getName() + parts[2]);
				}
			}
			options.add(ot);
		}

		return options;
	}

	@Override
	public void update(Input s) {

		// Testen ob Options richtig formatiert seind
		parseOptions(s);

		// Der Rexex darf nicht escaped sein
		s.setRegex(StringEscapeUtils.unescapeHtml4(s.getRegex()));
		dao.update(s);

	}
}
