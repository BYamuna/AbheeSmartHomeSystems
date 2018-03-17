package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.charvikent.abheeSmartHomeSystems.dao.AbheeCustomerDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeCustRegistration;
@Controller
public class AbheeCustRegistrationController 
{
	@Autowired
	AbheeCustomerDao adao;
	@RequestMapping("/custRegistration")	
	public String AbheeCustRegistrationPage(Model model)
	{
	  model.addAttribute("custReg",new AbheeCustRegistration());
	  return "custRegistration";
	  
	}
	@RequestMapping(value = "/custreg", method = RequestMethod.POST)
	public String saveAbheeCustRegistration(@Validated @ModelAttribute  AbheeCustRegistration abheecustregistration,Model model) throws IOException 
	{
		adao.saveabheecustregistration(abheecustregistration);
		return "redirect:custRegistration";
		
	}
}
