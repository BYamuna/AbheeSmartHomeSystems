package com.charvikent.abheeSmartHomeSystems.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
import com.charvikent.abheeSmartHomeSystems.model.Category;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	
	@Autowired UserService userService;
	
	@Autowired CustomerDao customerDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	
	
	
	
	@RequestMapping("/admin")
	public String customlogin(Model model) {
		
		 /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		 if (null != auth){    
		        return "redirect:dashboard";
		    }
		 else*/
		
		return "login";
	}
	
	@RequestMapping("/userlogin")
	public String userLogin(Model model) {
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		return "userlogin";
	}
	
	@RequestMapping("*")
	public String erro404r(Model model) {
	//	System.out.println("login called at /");
		return "login";
	}
	@RequestMapping("/login")
	public String loginView(Model model) {
		System.out.println("login called at /login page");
		//User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 //userService.setLoginRecord(objuserBean.getId(),"login");

		return "login";
	}
	
	@RequestMapping("/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
	    userService.setLoginRecord(objuserBean.getId(),"logout");
	    
	   // userService.setLoginRecord(objuserBean.getId(),"logout");
	    if (null != auth){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    SecurityContextHolder.clearContext();
	    if(null != auth) {
	    	SecurityContextHolder.getContext().setAuthentication(null);
	    }
	    System.out.println("Called Logout");
	    return "redirect:/login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@RequestMapping("/403")
	public String failureLogin(Model model) {
		return "403";
	}
	@RequestMapping("/customerlogin")
	public String ShowCustomerLoginPage(Model model,HttpServletRequest request) {
		
		return "customerlogin";
	}
	
	@PostMapping("/customerlogin")
	public String validateCustomerLogin(Model model,HttpServletRequest request,HttpSession session) {
		
		String loginid=request.getParameter("username");
		String password=request.getParameter("password");
		
		Customer customer =customerDao.validateCustomer(loginid,password);
		
		if(null ==customer)
		{
			System.out.println("Customer does not exists");
		}
		
		session.setAttribute("customer", customer);
		
		String referalUrl=request.getHeader("referer");
		StringBuffer strings=request.getRequestURL();
		
		if(request.getRequestURL().equals(request.getHeader("referer")))
			return "redirect:/";
		else
			return "redirect:/";
		
		
	}
	
	@PostMapping("/test")
	public String test(Model model,HttpServletRequest request) {
		//URI str= hrequest.getURI();
		
		System.out.println(request.getContextPath());
		
		System.out.println(request.getRequestURL());
		
		System.out.println(request.getHeader("referer"));
		
		//System.out.println(hrequest.getHeaders());
		
		return "admin";
	}
	
	
	@RequestMapping("/")
	public String ShowAbhee(Model model,HttpServletRequest request,HttpSession session) throws JSONException, JsonProcessingException {
		List<Category> listOrderBeans = categoryDao.getCategoryNames();
		//model.addAttribute("categories", listOrderBeans);
		ObjectMapper objectMapper = new ObjectMapper();
		String sJson = objectMapper.writeValueAsString(listOrderBeans);	
		request.setAttribute("allOrders1", sJson);
		
		 
		return "abheeindex";
	}
	
	
	@RequestMapping("/signout")
	public String SignOut(Model model,HttpServletRequest request,HttpSession session) throws JSONException, JsonProcessingException {
		session.invalidate();
		 
		return "redirect:/";
	}
	
	
	
	

}
