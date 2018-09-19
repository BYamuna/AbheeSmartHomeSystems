package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.controller.AbheeBranchController;
/*import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;*/
import com.charvikent.abheeSmartHomeSystems.model.DashBoardByCategory;
import com.charvikent.abheeSmartHomeSystems.model.DashBoardByStatus;
/*import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;*/
import com.charvikent.abheeSmartHomeSystems.model.User;

@Repository
@Transactional
public class DashBoardDao {

	private static final Logger LOGGER = LoggerFactory.getLogger( AbheeBranchController.class); 
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserDao userDao;
	@Autowired
	AbheeTaskDao abheeTaskDao;
	
	
	public HashMap<String, String>  getTasksCountBySeverity()
	{
		
		
		
		
		
		HashMap<String,String> tasksSeverityCounts =new LinkedHashMap<String,String>();
		
		String hql ="select abheeseverity.severity ,COUNT(abhee_task.severity)as number  FROM abhee_task RIGHT  JOIN abheeseverity "
                +"ON (abheeseverity.id=abhee_task.severity)  and abhee_task.kstatus <> '4' ";
		
		 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
			
			if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			{
			
		
		
		 hql =hql+" GROUP BY abheeseverity.id ";
			}
			
			else
			{
		    hql =hql+" and abhee_task.assignto='"+objuserBean.getId()+"'  GROUP BY abheeseverity.id" ;	
				
			}
			System.out.println("counts");
			System.out.println(hql);
			
			@SuppressWarnings("unchecked")
			List<Object[]> rows = entityManager.createNativeQuery(hql).getResultList();
			for (Object[] row : rows) {
				tasksSeverityCounts.put((String)row[0], String.valueOf(row[1]));
	    }
		
		
			return tasksSeverityCounts;
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getSeverityCountsUnderReportTo()
	{
		//LOGGER.debug("In getSeverityCountsUnderReportTo calling createnativeQuery");

		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		List<String> monitorList=userDao.getUsersUnderReportTo(id);
		
		Map<String,Integer> severityList=new LinkedHashMap<String,Integer>();
		
		
		String hql ="select severity,count(*) as scount from abhee_task r where r.assignto=:id and r.kstatus<>'4'  group by severity";
		
		Integer minor=0;
		Integer major=0;
		Integer critical=0;
		
		for(String id2:monitorList)
		{
			List<Object[]> rows= entityManager.createNativeQuery(hql).setParameter("id", id2).getResultList();
			
	         for (Object[] row : rows) {
	        	 if(row[0].equals("1"))
	        	 minor=minor+Integer.parseInt(String.valueOf(row[1]));
	        	 else if(row[0].equals("2"))
	        	 major=major+Integer.parseInt(String.valueOf(row[1]));
	        	 else if(row[0].equals("3"))
	        	 critical=critical+Integer.parseInt(String.valueOf(row[1]));
			}
		
		}
		
		severityList.put("Critical",critical);
		severityList.put("Major",major);
		severityList.put("Minor",minor);
		severityList.put("Total",critical+major+minor);
		
		System.out.println(severityList);
		
		
		return severityList;
		
	}
	
	public Set<Map<String, Object>> GetTaskBySeverityUnderReportTo(String sev)
    {
   	 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
   	 
   	 
		Set<Map<String, Object>> listissue=  getIssuesByAssignToUnderMonitor(id);
		
		
		Set<Map<String, Object>> secondset= new LinkedHashSet<Map<String,Object>>();
		
		
		
			
		
		 for (Map<String, Object> entry1 : listissue) 
		{
			 System.out.println(entry1);
			 if(!entry1.get("kstatus").equals("4"))
			 {
			if( entry1.get("severityid").equals(sev))
			{
			
			 secondset.add(entry1);
			}
			 }
			
		}
		 
		 System.out.println(secondset);
		return secondset;
		
   	 
    }
	
public Set<Map<String, Object>> getIssuesByAssignToUnderMonitor(String id) {
		
		
		List<String> monitorList=userDao.getUsersUnderReportTo(id);
		
		Set<Map<String, Object>> listissue= new LinkedHashSet<Map<String,Object>>();
		
		for(String id2:monitorList)
		{
			listissue.addAll((Collection<? extends Map<String, Object>>) abheeTaskDao.getTasksListAssignToMeById(id2));
		
		}
		
		
		return listissue;
	}



public HashMap<String, String>  getTasksCountBystatus()
{
	
	HashMap<String,String> tasksStatusCounts =new LinkedHashMap<String,String>();
	
	String hql ="select abheetaskstatus.name ,COUNT(abhee_task.severity)as number  FROM abhee_task RIGHT  JOIN abheetaskstatus "
               +" ON (abheetaskstatus.id=abhee_task.kstatus) and abhee_task.kstatus <> '4' ";
	
	
	 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
		{
		
	
	
	 hql =hql+" GROUP BY abheetaskstatus.id "; 
		}
		
		else
		{
	    hql =hql+" and abhee_task.assignto='"+objuserBean.getId()+"'  GROUP BY abheetaskstatus.id " ;	
			
		}
		System.out.println(hql);
		
		@SuppressWarnings("unchecked")
		List<Object[]> rows = entityManager.createNativeQuery(hql).getResultList();
		for (Object[] row : rows) {
			tasksStatusCounts.put((String)row[0], String.valueOf(row[1]));
    }
	
	
		return tasksStatusCounts;
	
}

public HashMap<String,Object> getAllCountBystatus() {
	
	HashMap<String,Object> alTtasksStatusCounts =new LinkedHashMap<String,Object>();
	
	
	User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	
	Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
	
	String hql="";
	
	if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
	{
		hql ="select count(*) from abhee_task t, abheetaskstatus s where t.kstatus=s.id and t.kstatus <>'7' and t.kstatus <>'4' ";
		//List<Object[]> rows = entityManager.createNativeQuery(hql).getResultList();
	}
	else
	{
		hql ="select count(*) from abhee_task t, abheetaskstatus s where t.kstatus=s.id and t.kstatus <>'7' and t.kstatus <>'4'  and  t.assignto='"+objuserBean.getId()+"'";
	}
	
	
	
	
	
	//for (Object[] row : rows) 
		alTtasksStatusCounts.put("allServiceCounts",  entityManager.createNativeQuery(hql).getResultList().get(0));
	
	return alTtasksStatusCounts;
}


@SuppressWarnings("unchecked")
public List<DashBoardByStatus> getStatusList() {
	LOGGER.debug("In getStatusList for Dashboard Status Table");

	List<DashBoardByStatus> dashBoardStatusList=new ArrayList<>();
	
	try {
	
	
	String hqladmin ="select  kstatus, ks.name,GROUP_CONCAT(ks.name) from abhee_task ri,abheecategory kc,abheetaskstatus ks where ri.category=kc.id and ks.id=kstatus  and ri.kstatus in(2,3,4) group by ri.kstatus ORDER BY ri.kstatus";
		
	List<Object[]> rows =null;
	
	
		rows= entityManager.createNativeQuery(hqladmin).getResultList();
			
			for (Object[] row : rows) {
				DashBoardByStatus dashBordByStatus= new DashBoardByStatus();
				dashBordByStatus.setStatusId((String)row[0]);
				dashBordByStatus.setStatusName((String) row[1]);
				dashBordByStatus.setStatusConcatination((String) row[2]);
				dashBoardStatusList.add(dashBordByStatus);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

	
	return dashBoardStatusList;
	}


@SuppressWarnings("unchecked")
public List<DashBoardByCategory> getCategory() {
	
	LOGGER.debug("In getSeverityCountsUnderReportTo calling createnativeQuery");

	
	
	List<DashBoardByCategory> dashBoardCategoryList=new ArrayList<>();
	try {
	
	
	String hqladmin ="select ri.category,kc.category as categoryname,kstatus  ,GROUP_CONCAT(ks.name) from abhee_task ri,abheecategory kc,abheetaskstatus ks where ri.category=kc.id and ks.id=kstatus  and ri.kstatus in(2,3,4) group by ri.category ORDER BY ri.category";
		
	List<Object[]> rows =null;
	
		rows= entityManager.createNativeQuery(hqladmin).getResultList();
		
			for (Object[] row : rows) {
				DashBoardByCategory dashBoardByCategory= new DashBoardByCategory();
				dashBoardByCategory.setCategoryId((String)row[0]);
				dashBoardByCategory.setCategoryName((String) row[1]);
				dashBoardByCategory.setkStatus((String) row[2]);
				dashBoardByCategory.setkStatusNameWithId((String) row[3]);
				dashBoardCategoryList.add(dashBoardByCategory);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

	
	return dashBoardCategoryList;
	
}

public List<Map<String,Object>> getSalesRequestByStatusListDashBord(String status)
{
	 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	 
	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		String sql ="";
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
		{
		
	 sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
			 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id"
			+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
			+" where  t.kstatus='"+status+"' and t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='1'  order by t.created_time desc ";
}
		else
		{
			sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
					 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id"
					+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
					+" where  t.kstatus<>'4' and  t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='1'and  t.assignto='"+objuserBean.getId()+" ' order by t.created_time desc " ;
		}
		
		System.out.println(sql);
	
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
	System.out.println(retlist);
	return retlist;
	
}

public List<Map<String, Object>> getSalesRequestByCategoryListDashBord(String status, String categoryId) {
 User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	 
	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		String sql ="";
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
		{
		
	 sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
			 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id"
			+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
			+" where  t.kstatus='"+status+"'and t.category='"+categoryId+"' and t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='1'  order by t.created_time desc ";
}
		else
		{
			sql="select t.id,t.assignto,u.username,t.category as categoryid,s.servicetypename,t.created_time,t.description,t.kstatus,ts.name as statusname,t.priority as priorityid,p.priority,t.severity as severityid,sev.severity, "
					 + "t.status,t.subject,t.taskdeadline,t.taskno,ab.category,abp.name as modelname,t.customer_id"
					+" FROM abhee_task t,abheeusers u,abheeservicetype s,abheetaskstatus ts,abheepriority p,abheeseverity sev,abheecategory ab ,abhee_product abp"
					+" where  t.kstatus<>'4' and  t.assignto=u.id and t.category=ab.id and t.kstatus=ts.id and t.priority=p.id and t.severity=sev.id and t.service_type=s.id and abp.id=t.modelid and t.status='1'and  t.assignto='"+objuserBean.getId()+" ' order by t.created_time desc " ;
		}
		
		System.out.println(sql);
	
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
	System.out.println(retlist);
	return retlist;
			
}




/**
 * @param user get severity counts for logged valid user
 * @return
 */
	public List<Map<String, Object>>  getTasksCountBySeverityforRest(User user)
		{
		String hql ="select abheeseverity.severity ,COUNT(abhee_task.severity)as number  FROM abhee_task RIGHT  JOIN abheeseverity "
	            +"ON (abheeseverity.id=abhee_task.severity)  and abhee_task.kstatus <> '4' ";
			if(user.getDesignation().equals("2") || user.getDesignation().equals("1")) 
			{
		 hql =hql+" GROUP BY abheeseverity.id ";
			}
			else
			{
		    hql =hql+" and abhee_task.assignto='"+user.getId()+"'  GROUP BY abheeseverity.id" ;		
			}
			List<Map<String, Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
			return retlist;
		}

	public List<Map<String, Object>> getTasksCountByStatusforRest(User user) 
		{
		String hql="select abheetaskstatus.name as status ,COUNT(abhee_task.severity)as number  FROM abhee_task RIGHT  JOIN abheetaskstatus ON (abheetaskstatus.id=abhee_task.kstatus) and abhee_task.kstatus <> '4'";
		if(user.getDesignation().equals("2") || user.getDesignation().equals("1")) 
			{
			hql =hql+" GROUP BY abheetaskstatus.id";
			}
			else
			{
		    hql =hql+" and abhee_task.assignto='"+user.getId()+"'  GROUP BY abheetaskstatus.id" ;		
			}
			List<Map<String, Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
			return retlist;
		}
}
	





	

