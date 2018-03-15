package com.charvikent.abheeSmartHomeSystems.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.charvikent.abheeSmartHomeSystems.config.KptsUtil;
import com.charvikent.abheeSmartHomeSystems.model.AbheeCustomer;
import com.charvikent.abheeSmartHomeSystems.model.User;

@Repository
public class AbheeCustomerDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	KptsUtil utilities;
	

	public void saveAbheeCustomer(AbheeCustomer cabheeCustomer ) {
		logger.info("Registering AbheeCustomer");
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String randomNum = utilities.randNum();
		cabheeCustomer.setCustomerId(randomNum);
		
		entityManager.persist(cabheeCustomer);

	}
}
