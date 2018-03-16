package com.charvikent.abheeSmartHomeSystems.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.dao.PriorityDao;
import com.charvikent.abheeSmartHomeSystems.model.Priority;

@Service
@Transactional
public class PriorityService {
	
	@Autowired
	private PriorityDao priorityDao;
	
	
	public Map<Integer, String> getPriorityNames()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Priority> rolesList= priorityDao.getPriorityNames();
		for(Priority bean: rolesList){
			rolesMap.put(bean.getId(), bean.getPriority());
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;
				
		
	}

}
