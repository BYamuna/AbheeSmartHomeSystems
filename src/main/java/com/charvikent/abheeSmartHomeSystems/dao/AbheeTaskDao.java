package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.User;

@Repository
@Transactional
public class AbheeTaskDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	
	public List<Map<String,Object>> getTasksList()
	{
		String sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
				+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid";
		System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
		
	}



	public List<Map<String, Object>> getTasksListBySeverityId(String sev) {
		String sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
				+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.severity='"+sev+"'";
		System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	
	
	
	public List<Map<String,Object>> getTasksListAssignToMe()
	{
		 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
		String sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
				+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='1'and  t.assignto='"+objuserBean.getId()+"'";
		System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
		
	}



	public List<Map<String, Object>> getInActiveList() {
		
		 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
					 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
					+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
					+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='0'and  t.assignto='"+objuserBean.getId()+"'";
			System.out.println(sql);
		List<Map<String,Object>>  inActivelist = jdbcTemplate.queryForList(sql,new Object[]{});
		return inActivelist;
	}

	
	
	

}
