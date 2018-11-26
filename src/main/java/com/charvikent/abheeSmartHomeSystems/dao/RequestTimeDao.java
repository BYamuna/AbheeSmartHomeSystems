package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeRequestTime;
@Repository
@Transactional
public class RequestTimeDao 
{
	@PersistenceContext private EntityManager entityManager;
	//@Autowired private JdbcTemplate jdbcTemplate;
	
	public Map<Integer, String> RequestTimesMap() 
	{
		Map<Integer, String> RequesttimeMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<AbheeRequestTime> RequestTimesList= getRequestTimes();
		System.out.println(RequestTimesList);
		for( AbheeRequestTime bean: RequestTimesList){
			RequesttimeMap.put(bean.getId(),bean.getRequesttime() );
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return RequesttimeMap;
	}
	
	@SuppressWarnings("unchecked")
	public List<AbheeRequestTime> getRequestTimes() 
	{
		return entityManager.createQuery(" from AbheeRequestTime").getResultList();
	}
}
