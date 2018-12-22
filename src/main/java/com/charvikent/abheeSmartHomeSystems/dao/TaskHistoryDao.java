package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.TaskHistory;
import com.charvikent.abheeSmartHomeSystems.model.TaskHistoryLogs;

@Repository
@Transactional
public class TaskHistoryDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	@Autowired private JdbcTemplate jdbcTemplate;
	
	
	public void savetaskhistory(TaskHistory taskHistory) {
		entityManager.persist(taskHistory);
		
	}
	public List<TaskHistoryLogs> getNotificationByCustomerId(TaskHistoryLogs history){
		String hql ="select th.add_comment,ts.name as kstatus from task_history_logs th,abheetaskstatus ts where th.assignby='"+history.getAssignby()+"' and th.notificationstatus='1' and ts.id=th.kstatus";
		RowMapper<TaskHistoryLogs> rowMapper = new BeanPropertyRowMapper<TaskHistoryLogs>(TaskHistoryLogs.class);
	    return  this.jdbcTemplate.query(hql, rowMapper);
	}
	public void UpdateNotificationByCustomerId(TaskHistoryLogs history){
		String hql ="update task_history_logs set notificationstatus='0' where assignby='"+history.getAssignby()+"'";
		jdbcTemplate.execute(hql);
	}
	public void UpdateKstatusbyTaskNo(AbheeTask task){
		String hql ="update abhee_task set kstatus='6' where taskno='"+task.getTaskno()+"' ";
		jdbcTemplate.execute(hql);
	}
	
	public List<TaskHistoryLogs> getNotificationsListByCustomerId(TaskHistoryLogs history){
		String hql ="select th.description,th.taskno,st.servicetypename as servicetype from task_history_logs th ,abheeservicetype st where assignby='"+history.getAssignby()+"' and st.id=th.service_type group by taskno";
		RowMapper<TaskHistoryLogs> rowMapper = new BeanPropertyRowMapper<TaskHistoryLogs>(TaskHistoryLogs.class);
	    return  this.jdbcTemplate.query(hql, rowMapper);
	}
	public List<AbheeTask> getServiceDetailsByTaskno(AbheeTask task){
		String hql ="select t.id,t.taskno,t.description,t.uploadfile,t.customer_id,t.communicationaddress,t.warranty,ar.requesttime ,ap.name as modelname ,ac.category,st.servicetypename as servicetype,c.name as companyname ,a.priority as priority,t.subject,u.username as assignedto,t.taskdeadline,t.imgfile from abhee_task t,abheecategory ac,abhee_company c,abheerequesttime ar,abheeservicetype st,abhee_product ap,abheepriority a,abheeusers u ,abheetaskstatus ts where t.taskno='FGPP1'  and t.category=ac.id and t.requesttime=ar.requesttimeid and t.modelid=ap.id  and t.service_type=st.id and t.company=c.id and t.kstatus=ts.id and t.priority=a.id  and t.assignto=u.id ";
		RowMapper<AbheeTask> rowMapper = new BeanPropertyRowMapper<AbheeTask>(AbheeTask.class);
	    return  this.jdbcTemplate.query(hql, rowMapper);
	}
	public List<TaskHistoryLogs> getNotificationsListByCustomerId1(String history,String customerid){
		String hql ="select th.description,th.taskno,th.add_comment,st.servicetypename as service from task_history_logs th ,abheeservicetype st where th.assignby='"+customerid+"' and st.id=th.service_type and th.taskno='"+history+"'";
		System.out.println(hql);
		RowMapper<TaskHistoryLogs> rowMapper = new BeanPropertyRowMapper<TaskHistoryLogs>(TaskHistoryLogs.class);
	    return  this.jdbcTemplate.query(hql, rowMapper);
	}
	
}
