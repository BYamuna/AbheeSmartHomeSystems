package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeServiceType;
/*import com.charvikent.abheeSmartHomeSystems.model.User;*/

@Repository
@Transactional
public class ServiceTypeDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	public Map<Integer, String> getServiceRequestTypes() 
	{
		
		String hql =" from AbheeServiceType";
		Query query =entityManager.createQuery(hql);  
		 List<AbheeServiceType> list= query.getResultList();
		 
		Map<Integer, String> abheeBranchHeadMap = new LinkedHashMap<Integer, String>();
		for(AbheeServiceType abheeServiceType:list)
		 {
			abheeBranchHeadMap .put(abheeServiceType.getId(), abheeServiceType.getServicetypename());
			 
			 
		 } 
		return abheeBranchHeadMap;
	}

}
