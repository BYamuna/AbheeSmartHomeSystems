package com.charvikent.abheeSmartHomeSystems.dao;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.charvikent.abheeSmartHomeSystems.model.Priority;



@Repository
public class PriorityDao {
	
	@PersistenceContext
    private EntityManager entityManager;


	public void savePriority(Priority priority ) {
		entityManager.persist(priority);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Priority> getPriorityNames()
	 {
		  
		return (List<Priority>)entityManager.createQuery("SELECT priority FROM Priority priority").getResultList();
		 
	 }
	
	
	public Map<Integer, String> getPriorityMap()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Priority> rolesList= getPriorityNames();
		for(Priority bean: rolesList){
			rolesMap.put(bean.getId(), bean.getPriority());
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;
				
		
	}
}
