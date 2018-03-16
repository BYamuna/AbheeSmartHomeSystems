package com.charvikent.abheeSmartHomeSystems.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.dao.TasksSelectionDao;
import com.charvikent.abheeSmartHomeSystems.model.TasksSelection;

@Service
@Transactional
public class TasksSelectionService {
	
	@Autowired
	TasksSelectionDao tasksSelectionDao;
	
	
	public Map<Integer, String> getTasksSelectionMap()
	{
		Map<Integer, String> tasksSelectionMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<TasksSelection> tasksSelectionList= tasksSelectionDao.getAllTasksSelections();
		for(TasksSelection bean: tasksSelectionList){
			tasksSelectionMap.put(bean.getId(), bean.getName());
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return tasksSelectionMap;
				
		
	}
	
	
	

}
