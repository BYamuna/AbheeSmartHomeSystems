package com.charvikent.abheeSmartHomeSystems.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.charvikent.abheeSmartHomeSystems.model.Admin;
@Repository
public class AdminDao {
	
	 @PersistenceContext
	    private EntityManager em;
	 
	 public void saveAdmin(Admin admin) {
			em.persist(admin);
			
		}


}
