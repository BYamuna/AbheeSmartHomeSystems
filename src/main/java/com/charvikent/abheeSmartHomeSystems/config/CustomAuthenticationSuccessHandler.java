package com.charvikent.abheeSmartHomeSystems.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.UserDao;
import com.charvikent.abheeSmartHomeSystems.model.Category;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired UserService userService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	UserDao userDao;
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("AT onAuthenticationSuccess(...) function!");
		// set our response to OK status
		response.setStatus(HttpServletResponse.SC_OK);
		// Add save record here
		
		List<Category> listOrderBeans = categoryDao.getCategoryNames();
		
		
		
		
		
		Object objUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(objUser instanceof User)
		{
			
			User objuserBean = (User)objUser;
		
			session.setAttribute("lastLoginTime", userDao.getLastloginTime());
		 userService.setLoginRecord(objuserBean.getId(),"login");
		
			
			User userDesignation= userService.getUserDesignationById(objuserBean.getId());
				
				 session.setAttribute("userDesignationSession", userDesignation);
				 
				 session.setAttribute("sessionUser", objuserBean.getFirstname());
				 response.sendRedirect("dashBoard");
				 
		}
		else
			if(objUser instanceof Customer)
			{
				
				Customer objuserBean = (Customer)objUser;
					
				session.setAttribute("sCategorylist",listOrderBeans);
					 session.setAttribute("sessionUser", objuserBean.getFirstname());
					 response.sendRedirect("customerDashBoard");
		            	
			}
		
            
		
	}

}
