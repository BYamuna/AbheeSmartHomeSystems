package com.charvikent.abheeSmartHomeSystems.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.OTPDetails;

@Repository
@Transactional
public class OTPDetailsDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public void saveOTPdetails(OTPDetails oTPDetails) {
		entityManager.persist(oTPDetails);
		
	}
	
	
	
	/*public String getOTPByMobileNumber(String mobileno)
	{
		
		String hql ="from OTPDetails where custID='"+mobileno+"'";
		
		List<AbheeCustRegistration> custlist =	entityManager.createQuery(hql).getResultList();
	    
		if(custlist.size()>0)
	   return custlist.get(0);
	    else
		return null;
		return null;
		
	}*/

}
