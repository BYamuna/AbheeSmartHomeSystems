package com.charvikent.abheeSmartHomeSystems.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskDao;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskStatusDao;
import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.DashBoardDao;
import com.charvikent.abheeSmartHomeSystems.dao.PriorityDao;
import com.charvikent.abheeSmartHomeSystems.dao.ReportIssueDao;
import com.charvikent.abheeSmartHomeSystems.dao.ServiceDao;
import com.charvikent.abheeSmartHomeSystems.dao.SeverityDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
	@Autowired
	DashBoardDao dashBoardDao;
	
	
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
	
	
	@RequestMapping(value = "/setNotifyData",method = RequestMethod.POST)
	public @ResponseBody Object setNotificationData(@RequestParam(value = "ttypeid", required = true) String ttypeid,Model model,HttpServletRequest request, HttpSession session) throws JSONException, JsonProcessingException {
		
		
		
		List<Map<String, Object>> listOrderBeans = null;
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		ObjectMapper objectMapper = null;
		String sJson = null;
		JSONObject jsonObj = new JSONObject();
		
		listOrderBeans = abheeTaskDao.getOpenTasks(id);
		
		 objectMapper = new ObjectMapper();
		if (listOrderBeans != null && listOrderBeans.size() > 0) {
			
			objectMapper = new ObjectMapper();
			sJson = objectMapper.writeValueAsString(listOrderBeans);
			request.setAttribute("allOrders1", sJson);
			jsonObj.put("allOrders1", listOrderBeans);
			// System.out.println(sJson);
		} else {
			objectMapper = new ObjectMapper();
			sJson = objectMapper.writeValueAsString(listOrderBeans);
			request.setAttribute("allOrders1", "''");
			jsonObj.put("allOrders1", listOrderBeans);
		}
		
		
		
		return String.valueOf(jsonObj);

	}
	
	
	@RequestMapping(value = "/viewTicket")
	public String viewIssue(@RequestParam(value = "id", required = true) String taskId,
			@RequestParam(value = "pgn", required = true) String pgn,Model model,HttpSession session) 
	{
		 
		if(pgn.equals("1"))
		{
			abheeTaskDao.openTask(taskId);
		}
		
			List<Map<String, Object>> viewtaskBean = abheeTaskDao.getAbheeTaskById(taskId);
			model.addAttribute("test2",viewtaskBean);
			
			
		
		return "ViewTicket";

	}
	@RequestMapping(value = "/viewCustomerDetails")
	public String viewDetails(@RequestParam(value = "id", required = true) String customerId,
			@RequestParam(value = "pgn", required = true) String pgn,Model model,HttpSession session) 
	{
		 
		/*if(pgn.equals("1"))
		{
			abheeTaskDao.openTask(taskId);
		}
		*/
			List<Map<String, Object>> viewcustomerBean = abheeTaskDao.getCustomerDetailsById(customerId);
			model.addAttribute("test2",viewcustomerBean);
			
			
		
		return "viewcustomerdetails";

	}
	
	@RequestMapping(value = "/severityByReportTo")
	public String  tasksFilterByseverityOnReportTo(@RequestParam(value="id", required=true) String sev,Model model,HttpServletRequest request,HttpSession session,@ModelAttribute("taskf")  AbheeTask taskf){
		Set<Map<String, Object>> listOrderBeans = null;
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
			listOrderBeans = dashBoardDao.GetTaskBySeverityUnderReportTo(sev);
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
