package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeRequestTime;

@Repository
@Transactional
public class AbheeRequestTimeDao 
{
	@PersistenceContext private EntityManager entityManager;
	@Autowired private JdbcTemplate jdbcTemplate;
	
	public Map<Integer, String> getRequestTimesMap() 
	{
		String hql =" from AbheeRequestTime";
		Query query =entityManager.createQuery(hql);  
		 List<AbheeRequestTime> list= query.getResultList();
		 
		Map<Integer, String> abheeBranchHeadMap = new LinkedHashMap<Integer, String>();
		for(AbheeRequestTime abheeRequestTime:list)
		 {
			abheeBranchHeadMap .put(abheeRequestTime.getId(),abheeRequestTime.getRequesttime()); 
		 } 
		return abheeBranchHeadMap;
	}
}
