package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.charvikent.abheeSmartHomeSystems.dao.AbheeCustomerDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeCustRegistration;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class AbheeCustRegistrationController 
{
	@Autowired
	AbheeCustomerDao adao;
	@RequestMapping("/custRegistration")	
	public String AbheeCustRegistrationPage(Model model,HttpServletRequest request)
	{
	  model.addAttribute("custReg",new AbheeCustRegistration());
	  List<AbheeCustRegistration> listOrderBeans = null;
	  ObjectMapper objectMapper = null;
	  String sJson = null;
	  try 
	  {
			listOrderBeans = adao.getAbheeCustomerNames();
			System.out.println(listOrderBeans);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} 
	  catch (Exception e) 
	  	{
			e.printStackTrace();
			System.out.println(e);
		}
	  return "custRegistration";
	  
	}
	@RequestMapping(value = "/custreg", method = RequestMethod.POST)
	public String saveAbheeCustRegistration(@Validated @ModelAttribute  AbheeCustRegistration abheecustregistration,Model model) throws IOException 
	{
		
		AbheeCustRegistration custbean =adao.checkCustomerExistOrNot(abheecustregistration);
		
		if(custbean == null)
		{
		adao.saveabheecustregistration(abheecustregistration);
		}
		else
		{
			// send only otp screen
		}
		return "redirect:custRegistration";
		
	}
	
	@RequestMapping(value = "/checkCustExst", method = RequestMethod.POST)
	public @ResponseBody  Boolean checkCustomerExistence(@Validated @ModelAttribute  AbheeCustRegistration abheecustregistration,Model model,HttpServletRequest request) throws IOException 
	{
		System.out.println("enter to checkCustExst");
		
		String custMobile=request.getParameter("cmobile");
		
		AbheeCustRegistration custbean =adao.checkCustomerExistOrNotbyMobile(custMobile);
		
		if(custbean != null)
		{
			return true;
		}
		else
		
		return false;
		
	}
	
	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public String showtaskPage(@Validated @ModelAttribute Model model,HttpServletRequest request) throws IOException 
	{
		return "task";
		
	}
	
	@RequestMapping(value = "/modelSubmit", method = RequestMethod.POST)
	public @ResponseBody  Boolean modelSubmit(@Validated @ModelAttribute  AbheeCustRegistration abheecustregistration,Model model,HttpServletRequest request) throws IOException 
	{
		System.out.println("enter to modelSubmit");
		
		String custMobile=request.getParameter("cmobile");
		String cemail=request.getParameter("cemail");
		String csname=request.getParameter("csname");
		String cname=request.getParameter("cname");
		
		AbheeCustRegistration abcust =new AbheeCustRegistration();
		
		abcust.setMobileno(custMobile);
		abcust.setName(cname);
		abcust.setSurname(csname);
		abcust.setEmail(cemail);
		
		adao.saveabheecustregistration(abcust);
		
		
		
		return false;
		
	}
}
