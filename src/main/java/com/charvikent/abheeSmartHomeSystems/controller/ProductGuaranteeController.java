package com.charvikent.abheeSmartHomeSystems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.charvikent.abheeSmartHomeSystems.model.ProductGuarantee;

@Controller
public class ProductGuaranteeController 
{
	@RequestMapping(value = "/productGuarantee" ,method = RequestMethod.GET)
	public String serviceRequestsView(Model model)
	{
		model.addAttribute("guaranteef", new ProductGuarantee());
		return "productguarantee";
		
	}
}
