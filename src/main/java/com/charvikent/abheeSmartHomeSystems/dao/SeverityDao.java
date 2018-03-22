package com.charvikent.abheeSmartHomeSystems.dao;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.charvikent.abheeSmartHomeSystems.model.Severity;


@Repository
public class SeverityDao {


	@PersistenceContext
    private EntityManager entityManager;

	public void saveSeverity(Severity severity ) {
		entityManager.persist(severity);

	}

	@SuppressWarnings("unchecked")
	public List<Severity> getSeverityNames()
	 {

		return entityManager.createQuery("SELECT severity FROM Severity severity").getResultList();

	 }

	public Severity getSeverityNameById(Integer id) {

		return entityManager.find(Severity.class, id);
	}
	
	
	public Map<Integer, String> getSeverityMap()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Severity> rolesList= getSeverityNames();
		for(Severity bean: rolesList){
			rolesMap.put(bean.getId(), bean.getSeverity());
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;


	}
}
