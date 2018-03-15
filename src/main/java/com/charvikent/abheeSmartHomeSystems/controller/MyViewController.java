package com.charvikent.abheeSmartHomeSystems.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.charvikent.abheeSmartHomeSystems.dao.UserDao;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.ReportIssueService;
//@Controller
public class MyViewController {
	@Autowired
	HttpSession session;
	@Autowired
	private ReportIssueService reportIssueService;
	
	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping("/myView")
	public String myview(Model model) {
		User objuserBean = (User) session.getAttribute("cacheUserBean");
		
	         if(objuserBean!=null)
	         {
		
		model.addAttribute("reportedByMe", reportIssueService.getIssuesByAssignBy(String.valueOf(objuserBean.getId())));
		model.addAttribute("assignToMe", reportIssueService.getIssuesByAssignTo(String.valueOf(objuserBean.getId())));
		model.addAttribute("assignToMeResolved", reportIssueService.getIssuesByAssignToResolved(String.valueOf(objuserBean.getId())));
		model.addAttribute("gapAndCount", reportIssueService.getGapAndCount());
		model.addAttribute("recentlyModified", reportIssueService.getRecentlyModified(String.valueOf(objuserBean.getId())));
	     model.addAttribute("statusCount" ,reportIssueService.getCountByStatusWise());
	    // model.addAttribute("monitoryBy",reportIssueService.getIssuesByAssignToUnderMonitor(String.valueOf(objuserBean.getId())));
		
		return "myView";
	         }
	         
	         else 
	        	 return "redirect:/"; 
		
	}
	
	
	/*@RequestMapping("*")
	public String fallbackMethod(){
		return "redirect:logoutHome";
	}*/
	

}
