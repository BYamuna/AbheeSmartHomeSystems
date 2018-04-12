package com.charvikent.abheeSmartHomeSystems.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.TaskHistory;

@Repository
@Transactional
public class TaskHistoryDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	public void savetaskhistory(TaskHistory taskHistory) {
		entityManager.persist(taskHistory);
		
	}
	

}
