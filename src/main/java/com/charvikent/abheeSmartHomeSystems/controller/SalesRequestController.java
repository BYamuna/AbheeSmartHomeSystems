package com.charvikent.abheeSmartHomeSystems.controller;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/*import java.util.concurrent.ThreadLocalRandom;*/
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
/*import org.springframework.web.bind.annotation.RequestBody;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.config.SendingMail;
import com.charvikent.abheeSmartHomeSystems.dao.SalesRequestDao;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SalesRequestController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesRequestController.class);

	@Autowired
	SalesRequestDao srequestDao;
	@Autowired
	FilesStuff fileTemplate;
	@Autowired
	SendingMail sendingMail;
	@RequestMapping(value = "/salesRequest" ,method = RequestMethod.GET)
	public String saveRequest(@ModelAttribute("salesRequest")SalesRequest salesrequest,Model model)
	{ 
		model.addAttribute("salesRequest", new SalesRequest());
		return "salesRequest";		
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/salesRequest", method = RequestMethod.POST)
	public @ResponseBody String saveRequestDetails(@RequestParam("modelnumber") String modelnumber,
			//@RequestParam("locationData") String locationData,
			@RequestParam("address") String address,
			@RequestParam("reqdesc") String reqdesc,
			@RequestParam("catid") String catid,
			@RequestParam("imgfile") MultipartFile[] uploadedFiles,
			//@RequestParam("requesttype") String requesttype,
			HttpServletRequest request,RedirectAttributes redir,HttpSession session) throws IllegalStateException, IOException, MessagingException
	{
		
		//Customer customer=(Customer) session.getAttribute("customer");
		//Customer customer=(Customer) request.getAttribute("customer");
		
		LOGGER.debug("Calling salesRequest at controller");
		Customer customer=(Customer) session.getAttribute("customer");
		String referalUrl=request.getHeader("referer");
		SalesRequest loginDetails = new SalesRequest();
		int filecount =0;
		
		//String str[] = locationData.split("&");
		//int randomNum = ThreadLocalRandom.current().nextInt(10, 20 + 1);
		loginDetails.setModelnumber(modelnumber);
		loginDetails.setAddress(address);
		loginDetails.setReqdesc(reqdesc);
		loginDetails.setCatid(catid);
		loginDetails.setSalesrequestnumber(loginDetails.getSalesrequestnumber());
		//loginDetails.setLat(str[0]);
		//loginDetails.setLongitude(str[1]);
		loginDetails.setEnable("1");
		loginDetails.setWebstatus(1);
		loginDetails.setQuotationstatus(1);
		loginDetails.setQstatus("1");
		loginDetails.setNotes(" ");
		loginDetails.setKstatus("New");
		loginDetails.setMobileno(customer.getMobilenumber());
		loginDetails.setEmail(customer.getEmail());
		loginDetails.setCustomerid(customer.getCustomerId());
		loginDetails.setImgfiles("icon.png");
		loginDetails.setRequestType("Quotation request");
		loginDetails.setLocation(" ");
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
   		loginDetails.setImgfiles(fileTemplate.concurrentFileNames());
   		 fileTemplate.clearFiles();
   		 
   	 }
	   	Boolean data =srequestDao.checkSalesrequestExistsorNotByEmailAndModelNo(loginDetails);
	   	if(data==false)
	   	{
			srequestDao.saveRequest(loginDetails);
	   		//sendingMail.SendingSalesRequestByEmail(salesrequest.getEmail());
	   		sendingMail.sendSalesRequestEmailWithattachment(customer.getEmail(),uploadedFiles);
	   	}
	   	else {
	   		redir.addFlashAttribute("msg","Record Already Exists");
	   		System.out.println("record Already Exists");
		return "false";
		}
	   	redir.addFlashAttribute("msg","We Received The Request and will send you the Quotation soon. Thanking you.");
	   	System.out.println("&&&&&&&&&&&&&&&&&&&&& Mail Sent");
	   	return "true";
		
	}
	@RequestMapping("/allsalesrequest")
	public String  totalSalesList( @ModelAttribute("salesrequestf")  SalesRequest salesrequest,Model model ,HttpServletRequest request) 
	{
		LOGGER.debug("Calling allsalesrequest at controller");
		List<Map<String, Object>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		model.addAttribute("salesrequestf", new SalesRequest());
		
		try {
			listOrderBeans = srequestDao.getSalesRequestList();
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
		return "allsalesrequest";
	}
	
	@RequestMapping(value = "/sendingQuotation", method = RequestMethod.POST)
	public @ResponseBody String sendingQuotation(@RequestParam("id")  String id,@RequestParam("description")  String description,
			@RequestParam("file") MultipartFile[] uploadedFiles,HttpServletRequest request,RedirectAttributes redir) throws IllegalStateException, IOException, MessagingException
	{
		LOGGER.debug("Calling sendingQuotation at controller");
		int filecount=0;
		String email = srequestDao.getSalesRequestEmailById(id); // for get the email address 
		SalesRequest salesrequest = srequestDao.getSalesRequestById(id);
		
		   	 for(MultipartFile multipartFile : uploadedFiles) 
		   	 {
		   		 
				String fileName = multipartFile.getOriginalFilename();
				
				if(!multipartFile.isEmpty())
				{
				filecount++;
				 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
				}
			}
 
   	 if(filecount>0)
   	 {
   		 salesrequest.setQuotationDocuments(fileTemplate.concurrentFileNames());
   		  salesrequest.setEnable("0");
   		  salesrequest.setStatus(1);
   		  //salesrequest.setQstatus("1");
   		 salesrequest.setCstatus(1);
   		 salesrequest.setKstatus("Quotation Sent");

   		salesrequest.setQuotationstatus(1);
   		  salesrequest.setNotes(description);
   		 fileTemplate.clearFiles();
   		 
   	 }
			srequestDao.saveQuotationRequest(salesrequest);
		/* srequestDao.saveQuationHistory(salesrequest); */
	   		sendingMail.sendSalesRequestEmailWithMultipleAttachment(email.toString(), uploadedFiles,description);
	   		String str ="true";
	   		
		return str;
		
	}
	
	
	
	@RequestMapping(value = "/inTotalSales")
	public @ResponseBody String getAllActiveOrInactiveQuotations(SalesRequest  objdept,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		LOGGER.debug("Calling inTotalSales at controller");
		List<Map<String, Object>> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(objdept.getQstatus().equals("0"))
				listOrderBeans = srequestDao.getInActiveList();
				else
					listOrderBeans = srequestDao.getSalesRequestList();



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
	
	@RequestMapping(value = "/deleteTotalSales")
	public @ResponseBody String deleteQuotation(SalesRequest  objorg,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		LOGGER.debug("Calling deleteTotalSales at controller");
		List<Map<String, Object>> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objorg.getId() != 0){
 				delete = srequestDao.deleteQuotation(objorg.getId(),objorg.getQstatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
 				
			listOrderBeans = srequestDao.getSalesRequestList();
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
	
	@RequestMapping(value = "/viewTask2")
	public @ResponseBody String getQuationsHistory(HttpServletRequest request,HttpSession session,@RequestParam("id")  String id) {
		LOGGER.debug("Calling inActiveEmp at controller");
		List<Map<String, Object>> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			
				listOrderBeans = srequestDao.getQuationHistory(id);
				


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
