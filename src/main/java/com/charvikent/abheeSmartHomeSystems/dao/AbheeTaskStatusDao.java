package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeTaskStatus;

@Repository
@Transactional
public class AbheeTaskStatusDao {
	

	@PersistenceContext
    private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	public List<AbheeTaskStatus> getTaskStatusList() {
		return (List<AbheeTaskStatus>)entityManager.createQuery(" from AbheeTaskStatus").getResultList();
		 	}
	
	
	public Map<Integer, String> getTaskStatusMap()
	{
		
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		List<AbheeTaskStatus> rolesList= getTaskStatusList();
		
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_BRANCHHEAD")))
		{
		for(AbheeTaskStatus bean: rolesList){
			rolesMap.put(bean.getId(), bean.getName());
		}
				
	} 
		else
		{
			for(AbheeTaskStatus bean: rolesList){
				if(bean.getName().equals("RESOLVED") ||bean.getName().equals("PAYMENT PENDING"))
				rolesMap.put(bean.getId(), bean.getName());
			}
			
		}
		return rolesMap;
				
		
	}
	
	

}
