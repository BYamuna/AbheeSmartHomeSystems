package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.User;

@Repository
@Transactional
public class DashBoardDao {

	@PersistenceContext
    private EntityManager entityManager;
	
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
	
}
