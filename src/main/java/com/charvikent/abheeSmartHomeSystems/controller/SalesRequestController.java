package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.config.SendingMail;
import com.charvikent.abheeSmartHomeSystems.dao.SalesRequestDao;
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
	public String saveRequest(Model model)
	{
		model.addAttribute("srequestf", new SalesRequest());
		return "salesrequest";
		
	}
	@RequestMapping(value = "/salesRequest", method = RequestMethod.POST)
	public @ResponseBody String saveRequestDetails(@Valid @ModelAttribute SalesRequest salesrequest,@RequestParam("imgfile") MultipartFile[] uploadedFiles,@RequestParam("locationData") String latlong,HttpServletRequest request) throws IllegalStateException, IOException, MessagingException
	{
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
		int filecount =0;
		
		String str[] = latlong.split("&");
		
		int randomNum = ThreadLocalRandom.current().nextInt(10, 20 + 1);
		
		salesrequest.setSalesrequestnumber(salesrequest.getModelnumber()+randomNum);
		salesrequest.setLat(str[0]);
		salesrequest.setLongitude(str[1]);
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		File dir = new File(rootPath + File.separator + "reportDocuments");
		
		if (!dir.exists()) {
			dir.mkdirs();
		}
   	 for(MultipartFile multipartFile : uploadedFiles) {
				String fileName = multipartFile.getOriginalFilename();
				if(!multipartFile.isEmpty())
				{
					filecount++;
				 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName,dir));
				}
			}
   	 
   	 if(filecount>0)
   	 {
   		 salesrequest.setImgfiles(fileTemplate.concurrentFileNames());
   		 fileTemplate.clearFiles();
   		 
   	 }
	   	Boolean result =srequestDao.checkSalesrequestExistsorNotByEmailAndModelNo(salesrequest);
	   	if(result==false)
	   	{
			srequestDao.saveRequest(salesrequest);
	   		//sendingMail.SendingSalesRequestByEmail(salesrequest.getEmail());
	   		sendingMail.sendSalesRequestEmailWithattachment(salesrequest.getEmail(), uploadedFiles);
	   	}
	   	else
	   		System.out.println("Record Already exists");
		return "redirect:salesRequest";
		
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
	public @ResponseBody String sendingQuotation(@RequestParam("id")  String id,@RequestParam("file") MultipartFile[] uploadedFiles,HttpServletRequest request) throws IllegalStateException, IOException, MessagingException
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
	   		sendingMail.sendSalesRequestEmailWithMultipleAttachment(email.toString(), uploadedFiles,salesrequest);
	   		String str ="true";
	   		
		return str;
		
	}
	
	
}
