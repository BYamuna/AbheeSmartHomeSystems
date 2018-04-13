package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<AbheeTaskStatus> rolesList= getTaskStatusList();
		for(AbheeTaskStatus bean: rolesList){
			rolesMap.put(bean.getId(), bean.getName());
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;
				
		
	}
	
	

}
