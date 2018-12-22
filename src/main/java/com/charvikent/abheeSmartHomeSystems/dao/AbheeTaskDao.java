package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.User;

@Repository
@Transactional
public class AbheeTaskDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	TaskHistoryLogsDao taskHistoryLogsDao;
	
	
	
	public List<Map<String,Object>> getTasksList()
	{
		String sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, t.communicationaddress "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id"
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
				+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid order by t.updated_time desc";
		System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
		
	}



	public List<Map<String, Object>> getTasksListBySeverityId(String sev) {
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		String sql ="";
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
		{
		 sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id,t.communicationaddress "
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
				+" where t.kstatus<>'4' and t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.severity='"+sev+"' order by t.updated_time desc ";
		
		}
		else
		{
			sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
					 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id, t.communicationaddress,t.amountreceived,t.discount,t.tax,t.total "
					+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"

					+" where t.kstatus<>'4'and  t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.severity='"+sev+"' and t.assignto='"+objuserBean.getId()+"' order by t.updated_time desc ";
			
			
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
			
		 sql="select t.id,t.assignto,u.username,t.uploadfile,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,t.service_type as servicetypeid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id, t.communicationaddress,t.amountreceived,t.discount,t.tax,t.total,ar.requesttime"
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp,abheerequesttime ar"
				+" where  t.kstatus<>'4' and t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and  t.requesttime=ar.requesttimeid and t.status='1'  order by t.created_time desc ";
	}
			else
			{
				sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
						 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id , t.communicationaddress,t.amountreceived,t.discount,t.tax,t.total,ar.requesttime "
						+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp,abheerequesttime ar"
						+" where  t.kstatus<>'4' and  t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.requesttime=ar.requesttimeid and t.status='1'and  t.assignto='"+objuserBean.getId()+" ' order by t.created_time desc " ;
			}
			
			System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
		
	}



	public List<Map<String, Object>> getInActiveList() {
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
			
			String sql ="";
			if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			{
			
		 sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id,t.communicationaddress,ar.requesttime"
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp,abheerequesttime ar"
				+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.requesttime=ar.requesttimeid and t.status='0'  order by t.created_time desc ";
	}
			else
			{
				sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
						 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id,t.communicationaddress,t.amountreceived,t.discount,t.tax,t.total,ar.requesttime"
						+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp,abheerequesttime ar"
						+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.requesttime=ar.requesttimeid and t.status='0'and  t.assignto='"+objuserBean.getId()+" ' order by t.created_time desc ";
			}
			
			System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}

	
	public List<Map<String, Object>> getOpenTasks(String id) {
		 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
					 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id ,t.communicationaddress"
					+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
					+" where  t.kstatus ='1' and t.kstatus ='6' and  t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and  t.additionalinfo ='0'and  t.assignto='"+objuserBean.getId()+"' order by t.updated_time desc ";
			System.out.println(sql);
			
			List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
			System.out.println(retlist);
			return retlist;
	}
@SuppressWarnings("unused")
public void  openTask(String taskno) {
		
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		try{
			
			AbheeTask task=   getAbheeTaskByTaskNo(taskno);
			
			if(!task.getAdditionalinfo().equals("1"))
			{
			   task.setAdditionalinfo("1");
			   task.setKstatus("2");
			   entityManager.merge(task);
			// taskHistoryLogsDao.historyLog(task);
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

@SuppressWarnings("unchecked")
private AbheeTask getAbheeTaskByTaskNo(String taskno) {
	
	String hql ="from AbheeTask where taskno='"+taskno+"'";
	
	List<AbheeTask> list =entityManager.createQuery(hql).getResultList();
	
	if(list.size()>0)
	{
		return list.get(0);
	}
	else
	{
	return null;
	}
}



public List<Map<String, Object>> getAbheeTaskById(String id) {
	
	String sql="select t.id,u.username,s.servicetypename,t.created_time,t.description,ts.name as Requeststatus,p.priority,sev.severity, "
			 + " t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id ,t.communicationaddress,t.imgfile as AttachedFiles  "
			+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
			+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.taskno='"+id+"' order by t.updated_time desc ";
	System.out.println(sql);
	
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
	System.out.println(retlist);
	return retlist;
	
}
public List<Map<String, Object>> getAbheeTaskByTaskId(String id) {
	
	String sql="select t.id,u.username,s.servicetypename,t.created_time,t.description,ts.name as Requeststatus,p.priority,sev.severity, "
			 + " t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id ,t.communicationaddress,t.imgfile as AttachedFiles  "
			+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
			+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.taskno="+id+" order by t.updated_time desc ";
	System.out.println(sql);
	
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
	System.out.println(retlist);
	return retlist;
	
}
public List<Map<String, Object>> getCustomerDetailsById(String id) {
	//return null;
	
	String sql="select c.customer_id,c.firstname,c.lastname,c.address,c.email,c.mobilenumber,CASE WHEN c.registedred_from_android IN ('0') THEN 'No' WHEN c.registedred_from_android IN ('1') THEN 'Yes' ELSE '-----' END AS enabled" + 
			" FROM abhee_customer c where c.customer_id='"+id+"' order by c.updated_time desc ";
	System.out.println(sql);
	
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
	System.out.println(retlist);
	return retlist;
	
}

@SuppressWarnings("unused")
public List<Map<String,Object>> getTasksListAssignToMeById(String id)
{
	 
	 
	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		String sql ="";
		/*if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
		{
		
	 sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
			 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname "
			+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
			+" where  t.kstatus<>'4' and t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='1' ";
}
		else*/
		{
			sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
					 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id ,t.communicationaddress "
					+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
					+" where  t.kstatus<>'4' and  t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='1'and  t.assignto='"+id+"' order by t.updated_time desc ";
		}
		
		System.out.println(sql);
	
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
	System.out.println(retlist);
	return retlist;
	
}

public List<Map<String, Object>> getTaskStatusHistoryByTaskNo(String taskno) {
	
	String hql= "select t.add_comment,u.username,s.name as servicestatus,p.name as productname, DATE_FORMAT(t.created_time,'%d-%b-%y %H:%i')as created_time,t.uploadfile as Attachfile,t.imgfile from task_history_logs  t,abheetaskstatus s ,abhee_product p ,abheeusers u where t.kstatus=s.id and  t.modelid =p.id and u.id=t.modified_by and t.taskno='"+taskno+" '  order by t.created_time desc";
	
         System.out.println(hql);
	
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
	System.out.println(retlist);
	return retlist;
	
}

public List<Map<String, Object>> getTasksByCustomerId(Customer customer) {
	
	String hql= "select t.id,t.additionalinfo,t.created_time,t.description,t.status,t.subject,t.taskdeadline,t.taskno,t.updated_time,t.uploadfile,t.add_comment,t.customer_id,t.communicationaddress,t.warranty,ts.name as kstatus,p.name as model,s.severity as severity,ap.priority as priority, st.servicetypename as servicetype,c.category as category,u.username as assignedby,u1.username as assignedto,ac.name as company " 
			+"from abhee_task t,abheetaskstatus ts,abhee_product p,abheeseverity s ,abheepriority ap,abheeservicetype st,abheecategory c,abheeusers u,abheeusers u1,abhee_company ac "
			+"where t.customer_id='"+customer.getCustomerId()+"' and t.kstatus=ts.id and t.modelid=p.id and t.priority=ap.id and t.severity=s.id and t.service_type=st.servicetype_id and t.category=c.id and t.assignby=u.id and t.assignto=u1.id and p.companyid=ac.id ";
	
     System.out.println(hql);
	
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
	System.out.println(retlist);
	return retlist;
	
}	
	public List<Map<String, Object>> getPriority() 
	{	
		String hql= "select * from abheepriority";
	     System.out.println(hql);
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
		
	}
	public List<Map<String, Object>> getSeverity() 
	{
		String hql= "select id,severity from abheeseverity";
	    System.out.println(hql);
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	
	public List<Map<String, Object>> getInActiveTasksList() 
	{
		String sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
				 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id,t.communicationaddress"
				+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
				+" where t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='0' order by t.created_time desc ";
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}

	public void deactiveTask(String status, Integer id) 
	{
		String sql="update abhee_task set status='"+status+"'where id='"+id+"'";
		jdbcTemplate.execute(sql);		
	}
	
	public List<Map<String, Object>> getCustomerResponseByCustomerId(String customer) {
		
		String hql= "select t.id,t.taskno,t.description,t.uploadfile,t.customer_id,t.communicationaddress,t.warranty,ar.requesttime ,ap.name as modelname ,ac.category,st.servicetypename as servicetype,c.name as companyname from abhee_task t,abheecategory ac,abhee_company c,abheerequesttime ar,abheeservicetype st,abhee_product ap where t.taskno='"+customer+"' and t.category=ac.id and t.requesttime=ar.requesttimeid and t.modelid=ap.id and t.service_type=st.id and t.company=c.id";
	     System.out.println(hql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
		
	}
public List<Map<String, Object>> getAdminResponseByCustomerId(String customer) {
		
		//String hql= "select ap.priority as priority,t.subject,t.kstatus,t.status,u.username as assignedto,t.taskdeadline,t.imgfile,t.description from abhee_task t,abheepriority ap,abheeusers u where t.taskno='"+customer+"' and t.priority=ap.id and t.assignto=u.id";
				
	String hql= "select ap.priority as priority,t.subject,t.kstatus as kstatusid,ts.name as kstatus,t.status,u.username as assignedto,t.taskdeadline,t.imgfile,t.description from abhee_task t,abheepriority ap,abheeusers u ,abheetaskstatus ts where t.taskno='"+customer+"' and t.priority=ap.id and t.assignto=u.id and t.kstatus=ts.id";		
	System.out.println(hql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
		
	}	
public List<Map<String, Object>> getAdminResponseByCustomerIdWhenStatusZero(String customer) {
	String hql ="select sr.status from abhee_task sr where taskno='"+customer+"'";
	 System.out.println(hql);
	
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	/*String hql= "select ap.priority as priority,t.subject,t.status,u.username as assignedto,t.taskdeadline,t.uploadfile,t.description from abhee_task t,abheepriority ap,abheeusers u where t.taskno='"+customer+"' and t.priority=ap.id and t.assignto=u.id";
			
			System.out.println(hql);
	
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
	System.out.println(retlist);
	return retlist;*/
	
}
@SuppressWarnings("unchecked")
public List<String> getTaskNoByCustomerId(Customer customer) {
	
String hql ="select t.taskno from AbheeTask t where t.customerId ='"+customer.getCustomerId()+"'";
	System.out.println(hql);
	List<String> list =entityManager.createQuery(hql).getResultList();
	return list;
}

public List<Map<String, Object>> getTasksListByCustomerId(String customerId) 
{
	String sql="select t.id,s.servicetypename,t.add_comment,t.taskno,abp.name as modelname,t.communicationaddress,t.description,t.uploadfile, DATE_FORMAT(t.created_time,'%d-%b-%y %H:%i')as created_time FROM abhee_task t,abheeservicetype s,abhee_product abp where  t.service_type=s.id and abp.id=t.modelid and t.customer_id='"+customerId+"'order by t.created_time desc ";
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
	System.out.println(retlist);
	return retlist;
}
public void updateTaskStatus(String taskstatus,String taskno)
{
	String hql="update abhee_task t set t.taskstatus='1',t.kstatus=2 where t.taskstatus='0' and t.taskno='"+taskno+"'";
	jdbcTemplate.execute(hql);
}



}
