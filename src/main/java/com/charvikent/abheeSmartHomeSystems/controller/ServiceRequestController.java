package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.dao.ServiceRequestDao;
import com.charvikent.abheeSmartHomeSystems.model.AllServiceRequests;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ServiceRequestController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequestController .class);
	@Autowired
	FilesStuff fileTemplate;
	@Autowired
	ServiceRequestDao servicerequestDao;
	

	/*@RequestMapping(value = "/serviceRequest" ,method = RequestMethod.GET)
	public String serviceRequestsView(Model model)
	{
		model.addAttribute("servicerequestf", new AllServiceRequests());
		return "allservicerequests";
		
	}*/
/*
	@RequestMapping(value = "/ServiceRequest", method = RequestMethod.POST)
	public String saveRequestDetails(@Valid @ModelAttribute AllServiceRequests servicerequest,@RequestParam("files") MultipartFile[] uploadedFiles) throws IllegalStateException, IOException
	{
			int filecount=0;
			 for(MultipartFile multipartFile : uploadedFiles) {
					String fileName = multipartFile.getOriginalFilename();
					if(!multipartFile.isEmpty())
					{
						filecount++;
					 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
					}
				}
 
			 if(filecount>0)
			 {
				 servicerequest.setFiles(fileTemplate.concurrentFileNames());
				 fileTemplate.clearFiles();
				 
			 }
				servicerequestDao.saveRequest(servicerequest);
			return "redirect:serviceRequest";
	}*/
	
	@RequestMapping("/serviceRequest")
	public String  totalServicesList( @ModelAttribute("servicerequestf") AllServiceRequests servicerequest ,Model model ,HttpServletRequest request) 
	{
		LOGGER.debug("Calling serviceRequest at controller");
		List<AllServiceRequests> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		model.addAttribute("servicerequestf", new AllServiceRequests());
		
		try {
			listOrderBeans = servicerequestDao.getServiceRequestList();
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
		return "allservicerequests";
	}
	
	@RequestMapping(value = "/editrequest", method = RequestMethod.POST)
	public String saveOReditRequest(@Valid @ModelAttribute  AllServiceRequests servicerequest, BindingResult bindingresults,
			RedirectAttributes redir, @RequestParam("files") MultipartFile[] uploadedFiles) throws IOException {
		LOGGER.debug("Calling editrequest at controller");
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		
		int id = 0;
		try
		{
			AllServiceRequests orgBean= servicerequestDao.getServiceCategoryNameById(servicerequest);
			
			int dummyId =0;
			
			if(orgBean != null){
				dummyId = orgBean.getId();
			}
			
			if(servicerequest.getId()==null)
			{
				if(dummyId ==0)
				{
					
					int filecount =0;
			    	 
			    	 for(MultipartFile multipartFile :  uploadedFiles) {
							String fileName = multipartFile.getOriginalFilename();
							if(!multipartFile.isEmpty())
							{
								filecount++;
							 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
							}
						}
			    	 
			    	 if(filecount>0)
			    	 {
			    		servicerequest.setFiles(fileTemplate.concurrentFileNames());
			    		 fileTemplate.clearFiles();
			    		 
			    	 }
					servicerequest.setStatus("1");
					servicerequestDao.saveRequest(servicerequest);

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
				id=servicerequest.getId();
				if(id == dummyId || orgBean == null)
				{
					int filecount =0;
		        	 
		        	 for(MultipartFile multipartFile : uploadedFiles) {
		    				String fileName = multipartFile.getOriginalFilename();
		    				if(!multipartFile.isEmpty())
		    				{
		    					filecount++;
		    				 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
		    				}
		    			}
		        	 
		        	 if(filecount>0)
		        	 {
		        		servicerequest.setFiles(fileTemplate.concurrentFileNames());
		        		 fileTemplate.clearFiles();
		        		 
		        	 }
		        	 servicerequestDao.UpdateRequest(servicerequest);
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
		
		
		return "redirect:allservicerequests";
	}
	
	
	@RequestMapping(value = "/deleteRequest")
	public @ResponseBody String deleteRequest(AllServiceRequests  objorg,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		LOGGER.debug("Calling deleteRequest at controller");
		System.out.println("deleteEducation page...");
		List<AllServiceRequests> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objorg.getId() != 0){
 				delete = servicerequestDao.deleteRequest(objorg.getId(),objorg.getStatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
 				
			listOrderBeans =  servicerequestDao.getServiceCategoryNames();
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
	
	@RequestMapping(value = "/inActiveRequests")
	public @ResponseBody String getAllActiveOrInactiveCategories(AllServiceRequests  objdept,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		LOGGER.debug("Calling inActiveRequests at controller");
		List<AllServiceRequests> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(objdept.getStatus().equals("0"))
				listOrderBeans =  servicerequestDao.getAllInActiveList();
				else
					listOrderBeans =  servicerequestDao.getServiceCategoryNames();
					
					
 				
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
