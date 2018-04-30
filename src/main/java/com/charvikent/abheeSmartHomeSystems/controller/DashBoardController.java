package com.charvikent.abheeSmartHomeSystems.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.charvikent.abheeSmartHomeSystems.dao.DashBoardDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.charvikent.abheeSmartHomeSystems.model.DashBoardByCategory;
import com.charvikent.abheeSmartHomeSystems.model.DashBoardByStatus;
@Controller
public class DashBoardController {
	
	@Autowired DashBoardDao dashBoardDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger( AbheeBranchController.class);
	/*@RequestMapping("/dashBoard")
	public String showDashBoard(Model model,HttpServletRequest request) 
	{
		LOGGER.debug("Calling dashBoard  at controller");
		 return "dashBoard";
		
	}
   */
	@RequestMapping("/dashBoard")
	public String showDashBoard(Model model,HttpServletRequest request) throws JsonProcessingException
	{
		
		
		LOGGER.debug("Calling dashBoard  at controller");
		
		 ObjectMapper deptmapper =new ObjectMapper();
		;	
		List<DashBoardByCategory> list=null;
		List<DashBoardByStatus> byStatusList=null;
		try {
			String json = null;
			list = dashBoardDao.getCategory();
			byStatusList=dashBoardDao.getStatusList();
			ObjectMapper objmapper = new ObjectMapper();
			if(list !=null ) {
				json = objmapper.writeValueAsString(list);
				
				request.setAttribute("list", json);
				}
			if(byStatusList !=null) {
			json =objmapper.writeValueAsString(byStatusList);
			request.setAttribute("byStatusList", json);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		 
		 //model.addAttribute("notifications", kpHistoryService.getHeaderNotifications());
		// model.addAttribute("acknotification", kpHistoryService.getHeaderNotificationsforack());
		 
		// session.setAttribute("acknotification", kpHistoryService.getHeaderNotificationsforack());
		 
		 //session.setAttribute("notifications", kpHistoryService.getHeaderNotifications());
		 
		 
		// System.out.println( kpHistoryService.getHeaderNotificationsforack());
		 
		 return "dashBoard";
		
	}
	
/*	
	@RequestMapping(value = "/severity")
	public String  tasksFilterByseverityOnAssignedTo(@RequestParam(value="id", required=true) String sev,Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				listOrderBeans = dashBoardService.getTasksBySeverity(sev);
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
	
	
	
	@RequestMapping(value = "/severityby")
	public String tasksFilterByseverityOnAssignedBY(	@RequestParam(value="id", required=true) String sev,Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				listOrderBeans = dashBoardService.getTasksBySeverityOnAssignedBy(sev);
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
	
	
	@RequestMapping(value = "/severityReportTo")
	public String tasksFilterByseverityOnReportTo(	@RequestParam(value="id", required=true) String sev,Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				listOrderBeans =  dashBoardService.GetTaskBySeverityUnderReportTo(sev);
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
	
	
	
	*//**
	 * timeline is string and 0 is loop iterate value in ddashboard,sjp page
	 * 
	 *  loop interate using jstl tag with varstatus property
	 *
	 *//*
	@RequestMapping(value = "/timeline0")
	public String timelineTasksByOpen(	@RequestParam(value="range", required=true) String range,Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				listOrderBeans = dashBoardService.getAllTasks(range);
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
	
	
	@RequestMapping(value = "/statusDashBord")
	public String tasksByStatus(	Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		String status=null;
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				status = request.getParameter("status");
				listOrderBeans =  dashBoardService.getTasksByStatusListDashBord(status);
				if (listOrderBeans != null && listOrderBeans.size() > 0) {
					objectMapper = new ObjectMapper();
					sJson = objectMapper.writeValueAsString(listOrderBeans);
					request.setAttribute("allOrders1", sJson);
				 //System.out.println("##############3"+sJson);
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
	
	
	@RequestMapping(value = "/categoryDashBord")
	public String taskscategoryOnReportTo(	Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		String status=null;
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				status = request.getParameter("status");
				String categoryId =request.getParameter("categoryId");
				listOrderBeans =  dashBoardService.getTasksByCategoryListDashBord(status,categoryId);
				if (listOrderBeans != null && listOrderBeans.size() > 0) {
					objectMapper = new ObjectMapper();
					sJson = objectMapper.writeValueAsString(listOrderBeans);
					request.setAttribute("allOrders1", sJson);
				 //System.out.println("##############3"+sJson);
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
	*//**
	 * timeline is string and 1 is loop iterate value in ddashboard,sjp page
	 * 
	 *  loop interate using jstl tag with varstatus property
	 *
	 *//*
	
	@RequestMapping(value = "/timeline1")
	public String timelineTasksByclose(	@RequestParam(value="range", required=true) String range,Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				listOrderBeans = dashBoardService.getAllTasksByclosed(range);
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
	

	
	*//**
	 * timeline is string and 2 is loop iterate value in ddashboard,sjp page
	 * 
	 *  loop interate using jstl tag with varstatus property
	 *
	 *//*
	@RequestMapping(value = "/timeline2")
	public String timelineTasksByBalaenced(	@RequestParam(value="range", required=true) String range,Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				listOrderBeans = dashBoardService.getAllTasksByBalenced(range);
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
	
	
	@RequestMapping(value = "/departmentwise")
	public String tasksFilterByDepartmentWise(	@RequestParam(value="id", required=true) String dept,Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				listOrderBeans = dashBoardService.getTasksByDepartmentWise(dept);
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
	
	
	@RequestMapping(value = "/deptAll")
	public String DepartmentwisedataByAll(	@RequestParam(value="id", required=true) String deptname,Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				listOrderBeans = dashBoardService.getTasksByDepartmentWise(deptname);
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
	
	@RequestMapping(value = "/deptClosed")
	public String DepartmentwisedataByClosed(	@RequestParam(value="id", required=true) String deptname,Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				listOrderBeans = dashBoardService.getTasksBydepartmentClosed(deptname);
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
	
	@RequestMapping(value = "/deptBalanced")
	public String DepartmentwisedataByBalanced(	@RequestParam(value="id", required=true) String deptname,Model model,HttpServletRequest request,HttpSession session){
		Set<ReportIssue> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		
		model.addAttribute("taskf", new ReportIssue());
		model.addAttribute("subTaskf", new KpStatusLogs());   // model attribute for formmodel popup
		model.addAttribute("severity", severityService.getSeverityNames());
		model.addAttribute("priority", priorityService.getPriorityNames());
		model.addAttribute("userNames", userService.getUserName());
		//model.addAttribute("category", categoryService.getCategoryNames());
		//model.addAttribute("departmentNames", mastersService.getDepartmentNames());
		model.addAttribute("kpstatuses", mastersService.getKpStatues());
		model.addAttribute("tasksSelection", tasksSelectionService.getTasksSelectionMap());
		
		model.addAttribute("departmentNames", mastersService.getSortedDepartments());
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
			
		
			try {
				listOrderBeans = dashBoardService.getTasksBydepartmentBalanced(deptname);
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

	}*/
	
	
	
	
	}
	
	

