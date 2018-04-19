package com.charvikent.abheeSmartHomeSystems.controller;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskDao;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskStatusDao;
import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.PriorityDao;
import com.charvikent.abheeSmartHomeSystems.dao.ReportIssueDao;
import com.charvikent.abheeSmartHomeSystems.dao.ServiceDao;
import com.charvikent.abheeSmartHomeSystems.dao.SeverityDao;
import com.charvikent.abheeSmartHomeSystems.dao.TaskHistoryLogsDao;
//import com.charvikent.abheeSmartHomeSystems.model.KpStatusLogs;
import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.TaskHistoryLogs;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TaskController {
	
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
	TaskHistoryLogsDao taskHistoryLogsDao;
	
	
	/*@Autowired
	DashBoardService dashBoardService;*/
	
	
	
	@GetMapping("/task")
	public String  department(Model model , HttpServletRequest request,HttpSession session) {
		List<Map<String, Object>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		model.addAttribute("taskf", new AbheeTask());
		
		model.addAttribute("severity", severityDao.getSeverityMap());
		model.addAttribute("priority", priorityDao.getPriorityMap());
		model.addAttribute("userNames", userService.getUserName());
		model.addAttribute("category", serviceDao.getServicemap());
		model.addAttribute("taskstatus", abheeTaskStatusDao.getTaskStatusMap());
		
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		model.addAttribute("objuserBean", objuserBean);
		
		try {
			listOrderBeans = abheeTaskDao.getTasksListAssignToMe();
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
	
	@PostMapping(value = "/savetask1" )
	public String saveAdmin(@Valid @ModelAttribute("taskf")  AbheeTask task, TaskHistoryLogs taskHistoryLogs,BindingResult bindingresults, @RequestParam("file1") MultipartFile[] uploadedFiles,
			RedirectAttributes redir) throws IOException {
		
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		
		int id = 0;
		try
		{
			AbheeTask orgBean=null;
			if(task.getId()!=null)
			{
			  orgBean= reportIssueDao.getReportIssueById(task.getId());
			
			}
			int dummyId =0;
			
			if(orgBean != null){
				dummyId = orgBean.getId();
			}
			
			if(task.getId()==null)
			{
				if(dummyId ==0)
				{
					
					try {
						for(MultipartFile multipartFile : uploadedFiles) {
							String fileName = multipartFile.getOriginalFilename();
							if(!multipartFile.isEmpty())
							{
							 task.setUploadfile("user browsed file(s)");            //add dummy value to check file upload status in dao layers
							 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
							}
						}
					} catch (IllegalStateException e) {
						e.printStackTrace();
					}
					
					task.setStatus("1");
					task.setAdditionalinfo("0");
					/*taskHistoryLogsDao.savetaskhistorylogs(taskHistoryLogs);*/
					reportIssueDao.saveReportIssue(task);
					
					redir.addFlashAttribute("msg", "Record Inserted Successfully");
					redir.addFlashAttribute("cssMsg", "success");
					
				} else
				{
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");
					
				}
				
			
			}
			
			else
			{
				id=task.getId();
				if(id == dummyId || orgBean == null)
				{
					
					try {
						for(MultipartFile multipartFile : uploadedFiles) {
							String fileName = multipartFile.getOriginalFilename();
							if(!multipartFile.isEmpty())
							{
							 task.setUploadfile("user browsed file(s)");            //add dummy value to check file upload status in dao layers
							 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
							}
						}
					} catch (IllegalStateException e) {
						e.printStackTrace();
					}
					task.setKstatus("2");
					/*taskHistoryLogsDao.savetaskhistorylogs(taskHistoryLogs);*/
					reportIssueDao.updateIssue(task);
					redir.addFlashAttribute("msg", "Record Updated Successfully");
					redir.addFlashAttribute("cssMsg", "warning");
					
				} else
				{
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		
		
		return "redirect:task";
	}
	
	
	@RequestMapping(value = "/deleteTask")
	public @ResponseBody String deleteDept(AbheeTask  objorg,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		System.out.println("deleteEducation page...");
		List<Map<String, Object>> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		try{
			if(objorg.getId() != 0){
 				delete = reportIssueDao.deleteTask(objorg.getId(),objorg.getStatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
 				
			listOrderBeans = abheeTaskDao.getTasksListAssignToMe();
			 objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", sJson);
			}
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			return String.valueOf(jsonObj);
			
		}
		return String.valueOf(jsonObj);
	}
	
	
	/*@RequestMapping(value = "/viewTask",method = RequestMethod.POST)
	public @ResponseBody Object viewIssue(@RequestParam(value = "id", required = true) String id, Model model,HttpServletRequest request, HttpSession session) throws JSONException {

		
		Set<KpStatusLogs> taskHistory =reportIssueDao.getrepeatLogsById(Integer.parseInt(id));
		
		JSONObject obj = new JSONObject();
		obj.put("list", taskHistory);
		return String.valueOf(obj);

	}
	*/
	
/*
	@RequestMapping(value = "/subTask", method = RequestMethod.POST)
	public @ResponseBody String saveSubtask(@RequestParam(value = "commet", required = true) String comment, @RequestParam(value = "kpstatus", required = true) String kpstatus, @RequestParam(value = "issueid", required = true) String issueid,  @RequestParam("file[]") MultipartFile[] uploadedFiles,
			RedirectAttributes redir) throws IOException {
		
		KpStatusLogs subtask =new KpStatusLogs();
		subtask.setComment(comment);
		subtask.setIssueid(issueid);
		subtask.setKpstatus(kpstatus);
		String str =null;
		try{
		
		
		try {
			for(MultipartFile multipartFile : uploadedFiles) {
				String fileName = multipartFile.getOriginalFilename();
				if(!multipartFile.isEmpty())
				{
					subtask.setUploadfiles("user browsed file(s)");            //add dummy value to check file upload status in dao layers
				 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		subtask.setIssueid(issueid);
		
		reportIssueDao.saveSubTask(subtask);
		
		 str="Comment inserted successfully";

		}catch(Exception e){
			e.printStackTrace();
			str = String.valueOf(e);
		}
		
		return str;
		
	}
	*/
	
	
	/*@RequestMapping(value = "/setdata",method = RequestMethod.POST)
	public @ResponseBody Object setTasks(@RequestParam(value = "ttypeid", required = true) String ttypeid,Model model,HttpServletRequest request, HttpSession session) throws JSONException {
		
		
		Set<ReportIssue> listOrderBeans = null;
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		if(ttypeid.equals("1"))
		
		{
		
		listOrderBeans = taskService.getissuesByselectionAssignTo(id);
		}
		
		else if(ttypeid.equals("2"))
			
		{
		
		listOrderBeans = taskService.getissuesByselectionAssignBy(id);
		}
		
		else  if(ttypeid.equals("3"))
			
		{
		
		listOrderBeans = taskService.getIssuesByAssignToUnderMonitor(id);
		}
        

		JSONObject obj = new JSONObject();
		obj.put("list", listOrderBeans);
		
		return String.valueOf(obj);
		
		
		

	}
	
	
	@RequestMapping(value = "/setdataDeptWise",method = RequestMethod.POST)
	public @ResponseBody Object setTasksDepartmentWise(@RequestParam(value = "deptid", required = true) String dept,Model model,HttpServletRequest request, HttpSession session) throws JSONException, JsonProcessingException {
		
		
		Set<ReportIssue> listOrderBeans = null;
		//User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String id=String.valueOf(objuserBean.getId());
		ObjectMapper objectMapper = null;
		String sJson = null;
		JSONObject jsonObj = new JSONObject();
		
		listOrderBeans = taskService.getIssuesByDepartmentWise(String.valueOf(dept));
		
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
	
	
	
	
	@RequestMapping(value = "/getCount")
	public @ResponseBody String getCount(ReportIssue  objorg,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		JSONObject jsonObj = new JSONObject();
		Integer unseentasks =0;
		try{
			
 				
			unseentasks = taskService.getUnseenTaskCount();
			jsonObj.put("unseentasks",unseentasks);
			
			jsonObj.put("reopentaskscount",taskService.getReopenTaskCount());
			
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			return String.valueOf(jsonObj);
			
		}
		return String.valueOf(jsonObj);
	}
	
	@RequestMapping(value = "/openTask")
	public @ResponseBody String opentask(ReportIssue  objorg,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		System.out.println("deleteEducation page...");
		Set<ReportIssue> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		
		try{
			if(objorg.getId() != 0){
 				delete = taskService.openTask(objorg.getId(),objorg.getAdditionalinfo());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
 				
			listOrderBeans = taskService.getOpenTasks(id);
			 objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", sJson);
			}
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			return String.valueOf(jsonObj);
			
		}
		return String.valueOf(jsonObj);
	}
	
	
	
	@RequestMapping(value = "/setNotifyData",method = RequestMethod.POST)
	public @ResponseBody Object setNotificationData(@RequestParam(value = "ttypeid", required = true) String ttypeid,Model model,HttpServletRequest request, HttpSession session) throws JSONException, JsonProcessingException {
		
		
		
		Set<ReportIssue> listOrderBeans = null;
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		ObjectMapper objectMapper = null;
		String sJson = null;
		JSONObject jsonObj = new JSONObject();
		
		listOrderBeans = taskService.getOpenTasks(id);
		
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

	}*/
	
	
	@RequestMapping(value = "/saveServiceRequest", method = RequestMethod.POST)
	public @ResponseBody  boolean modelSubmit(Model model,HttpServletRequest request) throws IOException 
	{
		System.out.println("enter to task controller Submit");
		
		String message=request.getParameter("message");
		String servicetypeid=request.getParameter("servicetypeid");
		String catid=request.getParameter("catid");
		String modelid=request.getParameter("modelid");
		
		String customerId =request.getParameter("customerId");
		AbheeTask task =new AbheeTask();
		task.setAdditionalinfo("0");
		task.setAssignby("1");
		task.setAssignto("1");
		task.setAssignto("1");
		task.setDescription(message);
		task.setKstatus("5");
		task.setPriority("1");
		task.setSeverity("1");
		task.setStatus("1");
		task.setSubject("Task creadted By Customer");
		task.setServiceType(servicetypeid);
		task.setCategory(catid);
		task.setModelid(modelid);
		task.setAssignby(customerId);
	//dfghfcgjfgjf
		
		
		
		reportIssueDao.saveReportIssue(task);
		taskHistoryLogsDao.historyLog(task);
		
		System.out.println(message+"  "+servicetypeid);
		return true;
		
	}
	@RequestMapping(value = "/inActiveTasks")
	public @ResponseBody String getAllActiveOrInactiveOrgnizations(AbheeTask  objorg,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<Map<String, Object>>listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(objorg.getStatus().equals("0"))
				listOrderBeans =abheeTaskDao.getInActiveList();
				else
					listOrderBeans = abheeTaskDao.getTasksListAssignToMe();



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
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			return String.valueOf(jsonObj);

		}
		return String.valueOf(jsonObj);
	}

}
