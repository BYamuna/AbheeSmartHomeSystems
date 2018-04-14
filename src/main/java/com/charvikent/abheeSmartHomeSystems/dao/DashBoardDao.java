package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.User;

@Repository
@Transactional
public class DashBoardDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	
	public HashMap<String, String>  getTasksCountBySeverity()
	{
		
		HashMap<String,String> tasksSeverityCounts =new LinkedHashMap<String,String>();
		
		String hql ="select abheeseverity.severity ,COUNT(abhee_task.severity)as number  FROM abhee_task RIGHT  JOIN abheeseverity "
                +"ON (abheeseverity.id=abhee_task.severity)   ";
		
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
	
}
