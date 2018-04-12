package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AbheeTaskDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	
	public List<Map<String,Object>> getTasksList()
	{
		String sql="select t.id,t.assignto,u.username,t.category,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category "
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab "
				+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id";
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
		
	}
	
	
	
	

}
