package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.TaskHistoryLogs;
import com.charvikent.abheeSmartHomeSystems.model.User;

@Repository
@Transactional
public class TaskHistoryLogsDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public void savetaskhistorylogs(TaskHistoryLogs taskHistoryLogs) 
	{
		entityManager.persist(taskHistoryLogs);
		
	}
	
	
	public void historyLog(AbheeTask reportIssue)
	{
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		TaskHistoryLogs taskHistoryLogs=new TaskHistoryLogs();
		taskHistoryLogs.setTaskid(reportIssue.getId());
		taskHistoryLogs.setServiceType(reportIssue.getServiceType());
		taskHistoryLogs.setAdditionalinfo(reportIssue.getAdditionalinfo());
		taskHistoryLogs.setAssignby(reportIssue.getCustomerId());
		taskHistoryLogs.setAssignto(reportIssue.getAssignto());
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
		
		taskHistoryLogs.setModifiedBy(String.valueOf(objuserBean.getId()));
		taskHistoryLogs.setCommunicationaddress(reportIssue.getCommunicationaddress());
		
		
		
		taskHistoryLogs.setUploadfile(reportIssue.getUploadfile());
		
		entityManager.persist(taskHistoryLogs);
	}
	
	
	
	/**
	 * @param reportIssue
	 * 
	 * to track service request details here.
	 * if  there is no logged used 
	 */
	public void historyLogForcustomerEntry(AbheeTask reportIssue)
	{
		
		TaskHistoryLogs taskHistoryLogs=new TaskHistoryLogs();
		
		taskHistoryLogs.setTaskid(reportIssue.getId());
		taskHistoryLogs.setServiceType(reportIssue.getServiceType());
		taskHistoryLogs.setAdditionalinfo(reportIssue.getAdditionalinfo());
		taskHistoryLogs.setAssignby(reportIssue.getCustomerId());
		taskHistoryLogs.setAssignto(reportIssue.getAssignto());
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
		taskHistoryLogs.setCommunicationaddress(reportIssue.getCommunicationaddress());
		
		
		
		
		taskHistoryLogs.setUploadfile(reportIssue.getUploadfile());
		
		entityManager.persist(taskHistoryLogs);
	}


	public void historyLog1(Object object) {
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		AbheeTask abheeTask=	entityManager.find(AbheeTask.class, object);
		TaskHistoryLogs taskHistoryLogs=new TaskHistoryLogs();
		
		
		taskHistoryLogs.setTaskid(abheeTask.getId());
		taskHistoryLogs.setServiceType(abheeTask.getServiceType());
		taskHistoryLogs.setAdditionalinfo(abheeTask.getAdditionalinfo());
		taskHistoryLogs.setAssignby(abheeTask.getCustomerId());
		taskHistoryLogs.setAssignto(abheeTask.getAssignto());
		taskHistoryLogs.setCategory(abheeTask.getCategory());
		taskHistoryLogs.setDescription(abheeTask.getDescription());
		taskHistoryLogs.setKstatus(abheeTask.getKstatus());
		taskHistoryLogs.setModelid(abheeTask.getModelid());
		taskHistoryLogs.setPriority(abheeTask.getPriority());
		taskHistoryLogs.setSeverity(abheeTask.getSeverity());
		taskHistoryLogs.setStatus(abheeTask.getStatus());
		taskHistoryLogs.setSubject(abheeTask.getSubject());
		taskHistoryLogs.setTaskdeadline(abheeTask.getTaskdeadline());
		taskHistoryLogs.setTaskno(abheeTask.getTaskno());
		taskHistoryLogs.setAddComment("serverice request marked as read");
		
		taskHistoryLogs.setModifiedBy(String.valueOf(objuserBean.getId()));
		
		taskHistoryLogs.setUploadfile(abheeTask.getUploadfile());
		taskHistoryLogs.setCommunicationaddress(abheeTask.getCommunicationaddress());
		
		entityManager.persist(taskHistoryLogs);

		
		
	}
	@SuppressWarnings("unused")
	public List<Map<String, Object>> showAssignedTasksNotification()
	{
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String hql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity," 
					+ "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.add_comment,u1.username as musername" 
					+ "	FROM task_history_logs t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp,abheeusers u1"
					+ "	where t.kstatus<>'4' and t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.modified_by=u1.id order by t.created_time desc " ;
		System.out.println(hql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
}
