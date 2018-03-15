package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.charvikent.abheeSmartHomeSystems.model.TasksSelection;

@Repository
public class TasksSelectionDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<TasksSelection> getAllTasksSelections()
	{
		
		String hql="from TasksSelection ";
		
		Query query =em.createQuery(hql);  
		
		List<TasksSelection> list=query.getResultList();
		
		return list;
		
	}

}
