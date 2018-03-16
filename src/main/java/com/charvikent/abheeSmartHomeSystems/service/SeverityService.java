package com.charvikent.abheeSmartHomeSystems.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.dao.SeverityDao;
import com.charvikent.abheeSmartHomeSystems.model.Severity;

@Service
@Transactional
public class SeverityService {

	@Autowired
	private SeverityDao severityDao;

	public Map<Integer, String> getSeverityNames()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Severity> rolesList= severityDao.getSeverityNames();
		for(Severity bean: rolesList){
			rolesMap.put(bean.getId(), bean.getSeverity());
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;


	}

}
