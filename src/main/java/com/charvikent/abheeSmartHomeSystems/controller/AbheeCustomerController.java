package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeCustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.OTPDetailsDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.charvikent.abheeSmartHomeSystems.model.OTPDetails;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;

@RestController
public class AbheeCustomerController {
	
	@Autowired
	AbheeCustomerDao abheeCustomerDao;
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	SendSMS sendSMS;
	
	
	@Autowired
	OTPDetailsDao oTPDetailsDao;

	
	@RequestMapping("/AbheeCustomer")
	public String showCustomerRegistrationForm(Model model,HttpServletRequest request) throws JsonProcessingException
	{
		return null;
		
	}
	
	
	
	//@RequestMapping(value = "/api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	
	@RequestMapping(value="/test", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public void  SaveAbheeCustomer( @RequestBody User user) {

		try {
			userService.saveUser(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/requestsms", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String getOTP( @RequestBody User user) {
		
		String custMobile=user.getMobilenumber();
		Random random = new Random();
		String  otpnumber = String.format("%04d", random.nextInt(10000));
		
		try {
			sendSMS.sendSMS(otpnumber,custMobile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OTPDetails oTPDetails =new OTPDetails();
		
		oTPDetails.setMobileno(custMobile);
		oTPDetails.setOTPnumber(otpnumber);
		
		oTPDetailsDao.saveOTPdetails(oTPDetails);
		return otpnumber;
		

		
	}
	

}
