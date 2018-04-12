package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeServiceType;

@Repository
@Transactional
public class ServiceDao 
{
	@PersistenceContext
    private EntityManager entityManager;
	
	public Map<Integer, String> getServicemap()
	{
		Map<Integer, String> servicesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<AbheeServiceType> serviceList= getServiceNames();
		System.out.println(serviceList);
		for(AbheeServiceType bean: serviceList){
			servicesMap.put(bean.getId(), bean.getServicetypename());
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return servicesMap;
				
		
	}

	@SuppressWarnings("unchecked")
	private List<AbheeServiceType> getServiceNames() 
	{
		
		return entityManager.createQuery("  from AbheeServiceType where status='1'").getResultList();
	}
}
