package com.charvikent.abheeSmartHomeSystems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.abheeSmartHomeSystems.dao.ContactDao;
import com.charvikent.abheeSmartHomeSystems.model.Contact;

@Controller
public class ContactController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
	@Autowired ContactDao contactDao;
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String showContactPage(Model model)
	{
		model.addAttribute("contact",new Contact());
		return "contact";	
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String saveContact(@ModelAttribute Contact contact,RedirectAttributes redir)
	{		
		LOGGER.debug("Calling contactus at controller");
		contactDao.saveContact(contact);
		redir.addFlashAttribute("msg", "Contact details Saved Successfully");
		redir.addFlashAttribute("cssMsg", "success");
		return "redirect:contact";
	}
}
