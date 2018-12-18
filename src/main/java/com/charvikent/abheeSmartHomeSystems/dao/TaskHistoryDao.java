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
		String hql ="select add_comment,kstatus from task_history_logs where assignby='"+history.getAssignby()+"' and notificationstatus=1";
		RowMapper<TaskHistoryLogs> rowMapper = new BeanPropertyRowMapper<TaskHistoryLogs>(TaskHistoryLogs.class);
	    return  this.jdbcTemplate.query(hql, rowMapper);
	}
	public void UpdateNotificationByCustomerId(TaskHistoryLogs history){
		String hql ="update task_history_logs set notificationstatus='0' where assignby='"+history.getAssignby()+"'";
		jdbcTemplate.execute(hql);
	}

}
