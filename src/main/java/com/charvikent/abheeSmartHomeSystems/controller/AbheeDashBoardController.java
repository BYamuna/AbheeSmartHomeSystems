package com.charvikent.abheeSmartHomeSystems.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskDao;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskStatusDao;
import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.PriorityDao;
import com.charvikent.abheeSmartHomeSystems.dao.ReportIssueDao;
import com.charvikent.abheeSmartHomeSystems.dao.ServiceDao;
import com.charvikent.abheeSmartHomeSystems.dao.SeverityDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AbheeDashBoardController {
	
	@Autowired
	ReportIssueDao reportIssueDao;
	@Autowired
	private PriorityDao priorityDao;

	@Autowired
	private SeverityDao severityDao;
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ServiceDao  serviceDao ;
	
	@Autowired
	FilesStuff fileTemplate;
	@Autowired
	AbheeTaskDao abheeTaskDao;
	
	@Autowired
	AbheeTaskStatusDao abheeTaskStatusDao;
	
	
	@RequestMapping(value = "/severityBy")
	public String  tasksFilterByseverityOnAssignedTo(@RequestParam(value="id", required=true) String sev,Model model,HttpServletRequest request,HttpSession session,@ModelAttribute("taskf")  AbheeTask taskf){
		List<Map<String, Object>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("severity", severityDao.getSeverityMap());
		model.addAttribute("priority", priorityDao.getPriorityMap());
		model.addAttribute("userNames", userService.getUserName());
		model.addAttribute("category", serviceDao.getServicemap());
		model.addAttribute("taskstatus", abheeTaskStatusDao.getTaskStatusMap());
		
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
		try {
			listOrderBeans = abheeTaskDao.getTasksListBySeverityId(sev);
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

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		
		
		return "task";
	
	}

}
