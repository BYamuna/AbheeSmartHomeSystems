package com.charvikent.abheeSmartHomeSystems.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskDao;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskStatusDao;
import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
/*import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;*/
import com.charvikent.abheeSmartHomeSystems.dao.DashBoardDao;
import com.charvikent.abheeSmartHomeSystems.dao.PriorityDao;
import com.charvikent.abheeSmartHomeSystems.dao.ReportIssueDao;
import com.charvikent.abheeSmartHomeSystems.dao.SalesRequestDao;
import com.charvikent.abheeSmartHomeSystems.dao.ServiceDao;
import com.charvikent.abheeSmartHomeSystems.dao.SeverityDao;
import com.charvikent.abheeSmartHomeSystems.dao.TaskHistoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.TaskHistoryLogsDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;
import com.charvikent.abheeSmartHomeSystems.model.TaskHistoryLogs;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AbheeDashBoardController {
	private static final Logger LOGGER = LoggerFactory.getLogger( AbheeDashBoardController .class);
	@Autowired
	ReportIssueDao reportIssueDao;
	@Autowired
	private PriorityDao priorityDao;
	@Autowired
	TaskHistoryDao taskHistorydao;
	@Autowired
	private SeverityDao severityDao;
	@Autowired
	private UserService userService;
	@Autowired
	private SalesRequestDao srequestDao;
	/*@Autowired
	private CategoryDao categoryDao;*/
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
	@Autowired
	TaskHistoryLogsDao taskHistoryLogsDao;
	@Autowired
	CustomerDao customerDao;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/severityBy")
	public String  tasksFilterByseverityOnAssignedTo(@RequestParam(value="id", required=true) String sev,Model model,HttpServletRequest request,HttpSession session,@ModelAttribute("taskf")  AbheeTask taskf){
		LOGGER.debug("Calling  severityBy at controller");
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
		
		LOGGER.debug("Calling  setNotifyData at controller");
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
							@RequestParam(value = "pgn", required = true) String pgn,
							@RequestParam(value = "id", required = true) String taskstatus,
							@RequestParam(value = "id", required = true) String taskno,
							Model model,HttpSession session,
							HttpServletRequest request
							) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling  viewTicket at controller");
		
		List<TaskHistoryLogs> listOrderBeansForNotification = null;
		
		//List<SalesRequest> listOrderBeansForNotification1 = null;
		
		List<TaskHistoryLogs> listOrderBeans = null;
		
		//List<SalesRequest> listOrderBeans1 = null;
		
		ObjectMapper objectMapperNotificaton = null;
		
	/*	ObjectMapper objectMapperNotificaton1 = null;
		
		String sJsonNotificaton1 = null;
		*/
		String sJsonNotificaton = null;
			
		
		
		List<Map<String, Object>> viewtaskBean = abheeTaskDao.getAbheeTaskById(taskId);
			model.addAttribute("test2",viewtaskBean);
			if(pgn.equals("1"))
			{
				abheeTaskDao.openTask(taskId);
				 
				
				
				taskHistoryLogsDao.historyLog1(viewtaskBean.get(0).get("id"));
				
			}
			
			User objuserBean = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String id = String.valueOf(objuserBean.getId());
			
			if(!id.equals("1") && !id.equals("2")) {
			
			listOrderBeansForNotification = taskHistorydao.getNotificationByCustomerIds(id);
			taskHistorydao.UpdateNotificationByCustomerIds(taskId);
			
			}
			else {
				listOrderBeansForNotification = taskHistorydao.getNotificationforAdmin();
			//	listOrderBeansForNotification1= taskHistorydao.getQuotationNotificationforAdmin();
				
				taskHistorydao.UpdateNotificationByCustomerIds(taskId);
				
				
			}
			
			if (listOrderBeansForNotification != null && listOrderBeansForNotification.size() > 0) //&& ( listOrderBeansForNotification1 !=null && listOrderBeansForNotification1.size() > 0)) 
			{
				objectMapperNotificaton = new ObjectMapper();
				
			//	objectMapperNotificaton1 = new ObjectMapper();
				
				sJsonNotificaton = objectMapperNotificaton.writeValueAsString(listOrderBeans);
				
		//	sJsonNotificaton1 = objectMapperNotificaton1.writeValueAsString(listOrderBeans1);
				
				session.setAttribute("notifications", sJsonNotificaton);
				//session.setAttribute("quotationType", sJsonNotificaton1);
				
			} else {
				objectMapperNotificaton = new ObjectMapper();
				
				//objectMapperNotificaton1 = new ObjectMapper();
				
				sJsonNotificaton = objectMapperNotificaton.writeValueAsString(listOrderBeans);
				
				//sJsonNotificaton1 = objectMapperNotificaton1.writeValueAsString(listOrderBeans1);
			}
			
			List<Map<String, Object>> statuslist=abheeTaskDao.getTaskStatusHistoryByTaskNo(taskId);
			
			abheeTaskDao.updateTaskStatus(taskstatus,taskno);
			ObjectMapper objectMapper = new ObjectMapper();
			String sJson;
			ObjectMapper objectMapper1 = new ObjectMapper();
			String sJson1;
			JSONObject jsonObj = new JSONObject();
			if (statuslist != null && statuslist.size() > 0) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(statuslist);
				sJson1=objectMapper1.writeValueAsString(viewtaskBean);
				request.setAttribute("statuslist1", sJson);
				request.setAttribute("test21", sJson1);
				jsonObj.put("statuslist1", statuslist);
				
				
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(statuslist);
				sJson1=objectMapper1.writeValueAsString(viewtaskBean);
				request.setAttribute("statuslist1", "''");
				request.setAttribute("test21", sJson1);
				jsonObj.put("statuslist1", statuslist);
				
				
				
			}
			
		
		return "ViewTicket";

	}
	
	@RequestMapping(value = "/viewQuotation")
	public String viewTicket(@RequestParam(value = "id", required = true) String quotationId,@RequestParam(value = "id", required = true) String customerId,@RequestParam(value = "id", required = true) String requestno,Model model,HttpSession session,HttpServletRequest request) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling  viewQuotation at controller");
		
		List<SalesRequest> listOrderBeansForNotification1 = null;
		List<SalesRequest> listOrderBeans1 = null;
		ObjectMapper objectMapperNotificaton1 = null;
		
		String sJsonNotificaton1 = null;
		
		/*User objuserBean = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id = String.valueOf(objuserBean.getId());*/
		
		listOrderBeansForNotification1= taskHistorydao.getQuotationNotificationforAdmin();
		if( listOrderBeansForNotification1 !=null && listOrderBeansForNotification1.size() > 0) {
			objectMapperNotificaton1 = new ObjectMapper();
			
			
			sJsonNotificaton1 = objectMapperNotificaton1.writeValueAsString(listOrderBeans1);
			session.setAttribute("quotationType", sJsonNotificaton1);
		}else {
			objectMapperNotificaton1 = new ObjectMapper();
			sJsonNotificaton1 = objectMapperNotificaton1.writeValueAsString(listOrderBeans1);
		}
		
		
		
			List<Map<String, Object>> viewquotationBean = srequestDao.getAbheeQuotationByQuotationId(quotationId);
			model.addAttribute("test3",viewquotationBean);
			
			List<Map<String, Object>> statuslist=srequestDao.getQuotationHistoryByRequestNo(requestno);
			ObjectMapper objectMapper = new ObjectMapper();
			String sJson;
			ObjectMapper objectMapper1 = new ObjectMapper();
			String sJson1;
			JSONObject jsonObj = new JSONObject();
			if (statuslist != null && statuslist.size() > 0) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(statuslist);
				sJson1=objectMapper1.writeValueAsString(viewquotationBean);
				request.setAttribute("quotationlist", sJson);
				request.setAttribute("test23", sJson1);
				jsonObj.put("quotationlist", statuslist);
				taskHistorydao.UpdateQuotationNotificationByRequestno(requestno);
				customerDao.UpdateQuotationNotificationByCustomerId(requestno);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(statuslist);
				sJson1=objectMapper1.writeValueAsString(viewquotationBean);
				request.setAttribute("quotationlist", "''");
				request.setAttribute("test23", sJson1);
				jsonObj.put("quotationlist", statuslist);
				taskHistorydao.UpdateQuotationNotificationByRequestno(requestno);
				customerDao.UpdateQuotationNotificationByCustomerId(requestno);
			}
		
		return "ViewQuotation";

	}
	
	@RequestMapping(value = "/viewResponse")
	public String viewQuotation(@RequestParam(value = "id", required = true) String qId,Model model,HttpSession session,HttpServletRequest request) throws JsonProcessingException, JSONException 
		{
			LOGGER.debug("Calling  viewResponse at controller");
			List<Map<String, Object>> responselist=srequestDao.getQuationHistory(qId);
			ObjectMapper objectMapper = new ObjectMapper();
			String sJson;
			if (responselist != null && responselist.size() > 0) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(responselist);
				request.setAttribute("test22", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(responselist);
				request.setAttribute("test22", sJson);
			}
		
		return "viewResponse";
	}

	@RequestMapping(value = "/viewServiceResponse")
	public String viewService(@RequestParam(value = "id", required = true) String taskId,@RequestParam(value = "id", required = true) String taskno,
			Model model,HttpSession session,HttpServletRequest request) throws JsonProcessingException, JSONException 
	{
			LOGGER.debug("Calling viewServiceResponse at controller");
			
			List<TaskHistoryLogs> listOrderBeansForNotification = null;
			
			ObjectMapper objectMapperNotificaton1 = null;
			String sJsonNotificaton = null;
			
			String sJsonNotificaton1 = null;
			List<SalesRequest> customerNotification = null;
			ObjectMapper objectMapperNotificaton = null;
			
			
			List<Map<String, Object>> responselist=abheeTaskDao.getAbheeTaskByTaskId(taskId);
			ObjectMapper objectMapper2 = new ObjectMapper();
			String sJson2;
			if (responselist != null && responselist.size() > 0) {
				
				objectMapper2 = new ObjectMapper();
				sJson2 = objectMapper2.writeValueAsString(responselist);
				request.setAttribute("test21", sJson2);
				// System.out.println(sJson);
			} else {
				objectMapper2 = new ObjectMapper();
				sJson2 = objectMapper2.writeValueAsString(responselist);
				request.setAttribute("test21", sJson2);
			}
		
			
			
			List<Map<String, Object>> statuslist=abheeTaskDao.getTaskHistoryByTaskNo(taskId);
			ObjectMapper objectMapper = new ObjectMapper();
			String sJson;
			ObjectMapper objectMapper1 = new ObjectMapper();
			String sJson1;
			JSONObject jsonObj = new JSONObject();
			if (statuslist != null && statuslist.size() > 0) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(statuslist);
				sJson1=objectMapper1.writeValueAsString(responselist);
				request.setAttribute("statuslist1", sJson);
				request.setAttribute("test21", sJson1);
				jsonObj.put("statuslist1", statuslist);
				customerDao.UpdateServiceNotificationByCustomerId(taskno);
				
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(statuslist);
				sJson1=objectMapper1.writeValueAsString(responselist);
				request.setAttribute("statuslist1", "''");
				request.setAttribute("test21", sJson1);
				jsonObj.put("statuslist1", statuslist);
				
				customerDao.UpdateServiceNotificationByCustomerId(taskno);
				
			}
			Customer custbean=(Customer) session.getAttribute("customer");
			String id = String.valueOf(custbean.getCustomerId());
			
			listOrderBeansForNotification=	customerDao.getServiceNotificationByCustomerIds(id);
			customerNotification = customerDao.getNotificationByCustomerIds(id);
			
			
			if (true)
			{	
				objectMapperNotificaton = new ObjectMapper();
				objectMapperNotificaton1 = new ObjectMapper();
				sJsonNotificaton1 = objectMapperNotificaton.writeValueAsString(customerNotification);
				sJsonNotificaton=objectMapperNotificaton1.writeValueAsString(listOrderBeansForNotification);
				
				session.setAttribute("services", sJsonNotificaton);
				session.setAttribute("quotations", sJsonNotificaton1);
			}
			/*else {
				objectMapperNotificaton1 = new ObjectMapper();
				sJsonNotificaton=objectMapperNotificaton1.writeValueAsString(listOrderBeansForNotification);
			}
		*/
		return "viewServiceResponse";
	}
	
	@RequestMapping(value = "/viewCustomerDetails")
	public String viewDetails(@RequestParam(value = "id", required = true) String customerId,
			@RequestParam(value = "pgn", required = true) String pgn,Model model,HttpSession session) 
	{
		LOGGER.debug("Calling  viewCustomerDetails at controller");
		/*if(pgn.equals("1"))
		{
			abheeTaskDao.openTask(taskId);
		}
		*/
			List<Map<String, Object>> viewcustomerBean = abheeTaskDao.getCustomerDetailsById(customerId);
			model.addAttribute("test2",viewcustomerBean);
			
			
		
		return "viewcustomerdetails";

	}
	
	@RequestMapping(value = "/viewQuotationDetails")
	public String viewQuotationDetails(@RequestParam(value = "id", required = true) String quotationId,@RequestParam(value = "id", required = true) String customerId,@RequestParam(value = "id", required = true) String requestno,Model model,HttpSession session,HttpServletRequest request) throws JsonProcessingException 
	{
		LOGGER.debug("Calling  viewQuotationDetails at controller");
		
		List<SalesRequest> customerNotification = null;
		List<TaskHistoryLogs> listOrderBeansForNotification = null;
		ObjectMapper objectMapperNotificaton = null;
		ObjectMapper objectMapperNotificaton12 = null;
		
		String sJsonNotificaton1 = null;
		String sJsonNotificaton = null;
		Customer custbean=(Customer) session.getAttribute("customer");
		String id = custbean.getCustomerId();
		
		
		
		
			List<Map<String, Object>> viewquotationBean = srequestDao.getAbheeQuotationByQuotationId(quotationId);
			model.addAttribute("test3",viewquotationBean);
			
			List<Map<String, Object>> statuslist=srequestDao.getQuotationHistoryByRequestNo(requestno);
			ObjectMapper objectMapper = new ObjectMapper();
			String sJson;
			ObjectMapper objectMapper1 = new ObjectMapper();
			String sJson1;
			JSONObject jsonObj = new JSONObject();
			if (statuslist != null && statuslist.size() > 0) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(statuslist);
				sJson1=objectMapper1.writeValueAsString(viewquotationBean);
				request.setAttribute("quotationlist", sJson);
				request.setAttribute("test23", sJson1);
				jsonObj.put("quotationlist", statuslist);
				//taskHistorydao.UpdateQuotationNotificationByRequestno(requestno);
				customerDao.UpdateQuotationNotificationByCustomerId(requestno);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(statuslist);
				sJson1=objectMapper1.writeValueAsString(viewquotationBean);
				request.setAttribute("quotationlist", "''");
				request.setAttribute("test23", sJson1);
				jsonObj.put("quotationlist", statuslist);
				//taskHistorydao.UpdateQuotationNotificationByRequestno(requestno);
				customerDao.UpdateQuotationNotificationByCustomerId(requestno);
			}
			customerNotification = customerDao.getNotificationByCustomerIds(id);
			listOrderBeansForNotification=	customerDao.getServiceNotificationByCustomerIds(id);
			
			objectMapperNotificaton = new ObjectMapper();
			objectMapperNotificaton12 = new ObjectMapper();
			if (true) {
				sJsonNotificaton1 = objectMapperNotificaton.writeValueAsString(customerNotification);
				sJsonNotificaton=objectMapperNotificaton12.writeValueAsString(listOrderBeansForNotification);
				session.setAttribute("quotations", sJsonNotificaton1);
				session.setAttribute("services", sJsonNotificaton);
			}
			/*else {
				sJsonNotificaton1 = objectMapperNotificaton.writeValueAsString(customerNotification);
				
			}*/
		
		return "viewQuotationResponse";

	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/severityByReportTo")
	public String  tasksFilterByseverityOnReportTo(@RequestParam(value="id", required=true) String sev,Model model,HttpServletRequest request,HttpSession session,@ModelAttribute("taskf")  AbheeTask taskf){
		LOGGER.debug("Calling  severityByReportTo at controller");
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
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getCount")
	public @ResponseBody String getCount(AbheeTask  objorg,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult)
	{
		LOGGER.debug("Calling  getCount at controller");
		JSONObject jsonObj = new JSONObject();
		Integer unseentasks =0;
		try{
			
			// session.setAttribute("acknotification", kpHistoryService.getHeaderNotificationsforack());
			 
			// session.setAttribute("notifications", kpHistoryService.getHeaderNotifications());
					
			//unseentasks = taskService.getUnseenTaskCount();
			//jsonObj.put("unseentasks",unseentasks);
			
			
			
			System.out.println("enter from header");
			
			
			jsonObj.put("paymentPending",dashBoardDao.getTasksCountBystatus().get("Payment Pending"));
			jsonObj.put("AllServiceRequests",dashBoardDao.getAllCountBystatus().get("allServiceCounts"));
			
			System.out.println("hi"+dashBoardDao.getAllCountBystatus());
		    System.out.println("hihi"+dashBoardDao.getTasksCountBystatus());
			
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			return String.valueOf(jsonObj);
			
		}
		return String.valueOf(jsonObj);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getAssignedNotifications")
	public @ResponseBody String getAssignedNotifications(AbheeTask  objorg,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) throws JsonProcessingException
	{
		LOGGER.debug("Calling  getAssignedNotifications at controller");
		
		
		JSONObject jsonObj = new JSONObject();
		List<Map<String,Object>> retlist=taskHistoryLogsDao.showAssignedTasksNotification();
		ObjectMapper objectMapper = new ObjectMapper();
		String sJson = objectMapper.writeValueAsString(retlist);
		try{
			
			jsonObj.put("AssignedNotifications",retlist);
			
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			return String.valueOf(jsonObj);
			
		}
		return String.valueOf(jsonObj);
	}
	
	

}
