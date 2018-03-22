package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.runtime.directive.Break;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeCustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.OTPDetailsDao;
import com.charvikent.abheeSmartHomeSystems.model.OTPDetails;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class AbheeCustomerRestController {
	
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
	
	@RequestMapping(value="/saveRestCustomer", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public HashMap<String, String>  SaveAbheeCustomer( @RequestBody User user) {
		
		String code =null;
		String regSuccessMsg =user.getFirstname()+" "+user.getLastname()+",  Successfully registered with ABhee Smart Homes. \n You can login using  \n UserId:  "+user.getMobilenumber()+" or "+user.getEmail()+"\n password: "+user.getPassword();

		try {
			userService.saveUser(user);
			sendSMS.sendSMS(regSuccessMsg,user.getMobilenumber());
			code = "OK";
		} catch (IOException e) {
			code="NOT_FOUND";
			e.printStackTrace();
		}
		
HashMap<String,String> hm =new HashMap<String,String>();
		
		hm.put("status", code);
		return hm;
	}
	
	@RequestMapping(value="/requestsms", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public ResponseEntity<String>  getOTP( @RequestBody User user) {
		
		user.setDesignation("9");
		String custMobile=user.getMobilenumber();
		Random random = new Random();
		String  otpnumber = String.format("%04d", random.nextInt(10000));
		
		HttpStatus code =null;
		try {
			String status = sendSMS.sendSMS(otpnumber,custMobile);
			if(status.equals("OK")){
				code = HttpStatus.OK;
			}else{
				code=HttpStatus.NOT_FOUND;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OTPDetails oTPDetails =new OTPDetails();
		
		oTPDetails.setMobileno(custMobile);
		oTPDetails.setOTPnumber(otpnumber);
		
		oTPDetailsDao.saveOTPdetails(oTPDetails);
		ResponseEntity<String> response = new ResponseEntity<String>(otpnumber,code);
		return response;
		

		
	}
	
	
	@RequestMapping(value="/requestsms2", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public HashMap<String, String> VerifyingAndSendOTP( @RequestBody User user) {
		
		String custMobile=user.getMobilenumber();
		
		String custemail=user.getEmail();
		Random random = new Random();
		String  otpnumber = String.format("%04d", random.nextInt(10000));
		
		
		String code =null;
		
         User custbean =userService.checkCustomerExistOrNotbyMobile(custMobile);
         User custbeanemail =userService.checkCustomerExistOrNotbyEmail(custemail);
		
		if(custbean != null)
		{
			code ="already exists Mobile";
		}
		else if(custbeanemail !=null)
		{
			
				code  ="already exists Email";
		}
		else
		{
		try {
			String status = sendSMS.sendSMS(otpnumber,custMobile);
			if(status.equals("OK")){
				code = "OK";
				OTPDetails oTPDetails =new OTPDetails();  
				
				oTPDetails.setMobileno(custMobile);
				oTPDetails.setOTPnumber(otpnumber);
				
				oTPDetailsDao.saveOTPdetails(oTPDetails);
				
				
			}else{
				code="NOT_FOUND";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		HashMap<String,String> hm =new HashMap<String,String>();
		
		hm.put("otpnumber", otpnumber);
		hm.put("statuscode", code);
		
	
		return hm;
		
		

		
	}
	
	
	@RequestMapping(value="/logincredentials", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public HashMap<String, String>  checkingLogincredentials( @RequestBody User user) {
		
		String code =null;
		String regSuccessMsg =user.getFirstname()+" "+user.getLastname()+",  Successfully registered with ABhee Smart Homes. \n You can login using  \n UserId:  "+user.getMobilenumber()+" or "+user.getEmail()+"\n password: "+user.getPassword();

		
			User userBean =userService.checkuserExistOrNot(user);
			
			
			if(null != userBean)
			{
				code = "OK";
			}
			else
				code="NOT_FOUND";
		
			System.out.println("rest call user status:  "+code);
		
HashMap<String,String> hm =new HashMap<String,String>();
		
		hm.put("status", code);
		return hm;
	}
	
	

}
