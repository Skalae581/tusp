package de.tuhh.tusp.app.fame.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.dao.InputBerechtigungDao;
import de.tuhh.tusp.app.fame.dao.StatusBerechtigungDao;
import de.tuhh.tusp.app.fame.dao.StatusBerechtigungDao.ExtendedStatusBerechtigung;
import de.tuhh.tusp.app.fame.dao.StatusDao;
import de.tuhh.tusp.app.fame.enums.FameConstants;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedWorkflowInstance;
import de.tuhh.tusp.utils.AuthorityUtils;

@Component
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private StatusBerechtigungDao statusBerechtigungDao;

	@Autowired
	private InputBerechtigungDao inputBerechtigungDao;

	@Autowired
	private AuthorityUtils authorityUtils;

	@Override
	public int delete(Long id) {

		inputBerechtigungDao.deleteByStatus(id);
		statusBerechtigungDao.deleteByStatus(id);

		return statusDao.delete(id);
	}

	@Override
	public Status fetchById(Long id) {

		return statusDao.fetchById(id);
	}

	@Override
	public Status fetchFirstStatusByWorkflow(Long workflowId) {

		List<Status> stati = statusDao.findByWorkflow(workflowId, false);

		if (!stati.isEmpty()) {

			return stati.get(0);
		}
		return null;
	}

	@Override
	public Status fetchLastStatusByWorkflow(Long workflowId) {

		List<Status> stati = statusDao.findByWorkflow(workflowId, false);

		if (!stati.isEmpty()) {

			return stati.get(stati.size() - 1);
		}
		return null;
	}

	@Override
	public List<Status> findAll() {

		return statusDao.findAll();
	}

	@Override
	public List<Status> findByVorgangAndRoles(Vorgang vorgang, List<String> roles) {

		List<ExtendedStatusBerechtigung> rechte =
				statusBerechtigungDao.findByRollenAndWorkflowInstance(	authorityUtils.getRoles(),
																		vorgang.getWorkflowInstanceId());

		List<Long> erlaubte = new ArrayList<>();

		// Wenn keine Rechte da sind, darf er bleiben wo er ist
		erlaubte.add(vorgang.getStatusId());

		for (ExtendedStatusBerechtigung recht : rechte) {
			if (FameConstants.ALL_STATUS.equals(recht.getStatusFromName())) {
				if (recht.getStatusToHidden() != 1) {
					erlaubte.add(recht.getStatusToId());
				}
				else if (FameConstants.ALL_STATUS.equals(recht.getStatusToName())) {
					return statusDao.findByWorkflowInstance(vorgang.getWorkflowInstanceId(), false);
				}
			}
			else if (recht.getStatusFromId().equals(vorgang.getStatusId())) {
				if (recht.getStatusToHidden() != 1) {
					erlaubte.add(recht.getStatusToId());
				}
				else if (FameConstants.ALL_STATUS.equals(recht.getStatusToName())) {
					return statusDao.findByWorkflowInstance(vorgang.getWorkflowInstanceId(), false);
				}
			}
		}

		return statusDao.findByIds(erlaubte);
	}

	@Override
	public List<Status> findByWorkflow(Long workflowId, boolean includeHidden) {

		return statusDao.findByWorkflow(workflowId, includeHidden);
	}

	@Override
	public List<ExtendedWorkflowInstance> findDetaildByWorkflowInstancesAndRoles(	List<WorkflowInstance> userWorkflows,
																					List<String> roles) {

		return statusDao.findDetaildByWorkflowInstancesAndRoles(userWorkflows, roles);
	}

	@Override
	public Long insert(Status status) {

		return statusDao.insert(status);
	}

	@Override
	public void update(Status status) {

		statusDao.update(status);
	}

}
