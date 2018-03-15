package com.charvikent.abheeSmartHomeSystems.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class AbheeCustomerController {
	
	
	@RequestMapping("/AbheeCustomer")
	public String showCustomerRegistrationForm(Model model,HttpServletRequest request) throws JsonProcessingException
	{
		return null;
		
	}
	

}
