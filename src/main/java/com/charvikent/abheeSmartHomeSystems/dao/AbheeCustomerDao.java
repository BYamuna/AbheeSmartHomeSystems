package com.charvikent.abheeSmartHomeSystems.dao;

import java.io.IOException;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.config.KptsUtil;
import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.model.AbheeCustRegistration;
import com.charvikent.abheeSmartHomeSystems.model.AbheeCustomer;

@Repository
@Transactional
public class AbheeCustomerDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	KptsUtil utilities;
	@Autowired
	SendSMS sendSMS;

	public void saveAbheeCustomer(AbheeCustomer cabheeCustomer ) {
		logger.info("Registering AbheeCustomer");
		//User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String randomNum = utilities.randNum();
		cabheeCustomer.setCustomerId(randomNum);
		
		entityManager.persist(cabheeCustomer);
		
		

	}

	public void saveabheecustregistration(AbheeCustRegistration abheecustregistration) {
		abheecustregistration.setStatus("1"); //set customer active or not .default set as 1
		String randomNum = utilities.randNum();
		abheecustregistration.setCustID(randomNum);  //set customer id
		Random random = new Random();
		String optnumber = String.format("%04d", random.nextInt(10000));
		
		abheecustregistration.setOTP(optnumber);
		entityManager.persist(abheecustregistration);
		
		Sendsms(optnumber,abheecustregistration.getMobileno()); //send otp to customer
		
	}
	
	public void Sendsms(String message,String mobileNumber)
	{
		try {
			sendSMS.sendSMS(message, mobileNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
