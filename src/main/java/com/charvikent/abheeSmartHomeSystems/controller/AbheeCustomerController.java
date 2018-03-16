package com.charvikent.abheeSmartHomeSystems.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charvikent.abheeSmartHomeSystems.dao.AbheeCustomerDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeCustomer;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class AbheeCustomerController {
	
	@Autowired
	AbheeCustomerDao abheeCustomerDao;
	
	@RequestMapping("/AbheeCustomer")
	public String showCustomerRegistrationForm(Model model,HttpServletRequest request) throws JsonProcessingException
	{
		return null;
		
	}
	
	
	
	//@RequestMapping(value = "/api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	
	@RequestMapping(value="/test", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})  
	public void  SaveAbheeCustomer( @RequestBody AbheeCustomer cabheeCustomer) {

		abheeCustomerDao.saveAbheeCustomer(cabheeCustomer);
	}
	

}
