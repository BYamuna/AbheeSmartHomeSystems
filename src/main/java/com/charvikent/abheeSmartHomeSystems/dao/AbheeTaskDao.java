package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeBranch;
import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.User;

@Repository
@Transactional
public class AbheeTaskDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	
	
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
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		String sql ="";
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
		{
		 sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
				+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.severity='"+sev+"'";
		
		}
		else
		{
			sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
					 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
					+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
					+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.severity='"+sev+"'";
			
			
		}
		 
		 
		 System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	
	
	
	public List<Map<String,Object>> getTasksListAssignToMe()
	{
		 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
			
			String sql ="";
			if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			{
			
		 sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
				+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='1' ";
	}
			else
			{
				sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
						 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
						+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
						+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='1'and  t.assignto='"+objuserBean.getId()+"'";
			}
			
			System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
		
	}



	public List<Map<String, Object>> getOpenTasks(String id) {
		 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
					 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
					+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
					+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and  t.additionalinfo ='0'and  t.assignto='"+objuserBean.getId()+"'";
			System.out.println(sql);
			
			List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
			System.out.println(retlist);
			return retlist;
	}



	public List<Map<String, Object>> getAbheeTaskById(String id) {
		
		String sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
				+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid";
		System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
		
	}
	
	
	public List<Map<String, Object>> getInActiveList() {
		
		String sql="select * from abhee_task where status='0'";
		List<Map<String,Object>>  inActivelist = jdbcTemplate.queryForList(sql,new Object[]{});
		return inActivelist;
	}

	
public void  openTask(String taskno) {
		
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		try{
			
			AbheeTask task=  (AbheeTask) getAbheeTaskById(taskno).get(0);
			
			if(!task.getAdditionalinfo().equals("1"))
			{
			   task.setAdditionalinfo("1");
			   task.setKstatus("3");
			   entityManager.merge(task);
			
			

			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

}
