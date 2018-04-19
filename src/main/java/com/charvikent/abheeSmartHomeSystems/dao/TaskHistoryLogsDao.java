package com.charvikent.abheeSmartHomeSystems.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.TaskHistoryLogs;

@Repository
@Transactional
public class TaskHistoryLogsDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	public void savetaskhistorylogs(TaskHistoryLogs taskHistoryLogs) 
	{
		entityManager.persist(taskHistoryLogs);
		
	}
	
	
	public void historyLog(AbheeTask reportIssue)
	{
		TaskHistoryLogs taskHistoryLogs=new TaskHistoryLogs();
		taskHistoryLogs.setTaskid(reportIssue.getId());
		taskHistoryLogs.setServiceType(reportIssue.getServiceType());
		taskHistoryLogs.setAdditionalinfo(reportIssue.getAdditionalinfo());
		taskHistoryLogs.setAssignby(reportIssue.getAssignby());
		taskHistoryLogs.setAssignto(reportIssue.getAdditionalinfo());
		taskHistoryLogs.setCategory(reportIssue.getCategory());
		taskHistoryLogs.setDescription(reportIssue.getDescription());
		taskHistoryLogs.setKstatus(reportIssue.getKstatus());
		taskHistoryLogs.setModelid(reportIssue.getModelid());
		taskHistoryLogs.setPriority(reportIssue.getPriority());
		taskHistoryLogs.setSeverity(reportIssue.getSeverity());
		taskHistoryLogs.setStatus(reportIssue.getStatus());
		taskHistoryLogs.setSubject(reportIssue.getSubject());
		taskHistoryLogs.setTaskdeadline(reportIssue.getTaskdeadline());
		taskHistoryLogs.setTaskno(reportIssue.getTaskno());
		taskHistoryLogs.setAddComment(reportIssue.getAddComment());
		
		taskHistoryLogs.setUploadfile(reportIssue.getUploadfile());
		
		entityManager.persist(taskHistoryLogs);
	}
}
