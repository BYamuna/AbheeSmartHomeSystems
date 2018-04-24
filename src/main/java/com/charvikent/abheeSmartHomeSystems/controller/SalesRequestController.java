package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@RequestMapping(value = "/salesRequest", method = RequestMethod.POST)
	public String saveRequestDetails(@ModelAttribute("salesRequest")SalesRequest salesrequest,
									@RequestParam("imgfile") MultipartFile[] uploadedFiles,
									@RequestParam(value = "locationData") String latlong,HttpServletRequest request,RedirectAttributes redir) throws IllegalStateException, IOException, MessagingException
	{
		
		Customer customer=(Customer) request.getAttribute("customer");
		
		String referalUrl=request.getHeader("referer");
		Integer id = salesrequest.getId();
		String email = srequestDao.getSalesRequestEmailById(id.toString()); // for get the email address 
		SalesRequest loginDetails = srequestDao.getSalesRequestById(id.toString());
		int filecount =0;
		
		String str[] = latlong.split("&");
//		Customer objLoginCustomer= (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int randomNum = ThreadLocalRandom.current().nextInt(10, 20 + 1);
		
		loginDetails.setSalesrequestnumber(salesrequest.getModelnumber()+randomNum);
		loginDetails.setLat(str[0]);
		loginDetails.setLongitude(str[1]);
		loginDetails.setEnable("1");
		//salesrequest.setMobileno(objLoginCustomer.getMobilenumber());
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
	   	Boolean result =srequestDao.checkSalesrequestExistsorNotByEmailAndModelNo(loginDetails);
	   	if(result==false)
	   	{
			srequestDao.saveRequest(loginDetails);
	   		//sendingMail.SendingSalesRequestByEmail(salesrequest.getEmail());
	   		sendingMail.sendSalesRequestEmailWithattachment(email, uploadedFiles);
	   	}
	   	else {
	   		redir.addFlashAttribute("msg","Record Already Exists");
	   		System.out.println("record Already Exists");
		return "redirect:abheecategory";
		}
	   	redir.addFlashAttribute("msg","We Received The Request and will send you the Quotation soon. Thanking you.");
	   	System.out.println("&&&&&&&&&&&&&&&&&&&&& Mail Sent");
	   	return "redirect:abheecategory";
		
	}
	@RequestMapping("/allsalesrequest")
	public String  totalSalesList( @ModelAttribute("salesrequestf")  SalesRequest salesrequest,Model model ,HttpServletRequest request) 
	{
		List<SalesRequest> listOrderBeans = null;
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
   		 fileTemplate.clearFiles();
   		 
   	 }
			srequestDao.saveRequest(salesrequest);
	   		sendingMail.sendSalesRequestEmailWithMultipleAttachment(email.toString(), uploadedFiles,description);
	   		String str ="true";
	   		
		return str;
		
	}
	
	
}
