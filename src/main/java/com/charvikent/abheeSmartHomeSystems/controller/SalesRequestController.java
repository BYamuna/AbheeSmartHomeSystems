package com.charvikent.abheeSmartHomeSystems.controller;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.dao.SalesRequestDao;
import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;

@Controller
public class SalesRequestController 
{
	@Autowired
	SalesRequestDao srequestDao;
	@Autowired
	FilesStuff fileTemplate;
	@RequestMapping(value = "/salesRequest" ,method = RequestMethod.GET)
	public String saveRequest(Model model)
	{
		model.addAttribute("srequestf", new SalesRequest());
		return "salesrequest";
		
	}
	@RequestMapping(value = "/salesRequest", method = RequestMethod.POST)
	public String saveRequestDetails(@Valid @ModelAttribute SalesRequest salesrequest,@RequestParam("imgfile") MultipartFile[] uploadedFiles) throws IllegalStateException, IOException
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
   		 salesrequest.setImgfiles(fileTemplate.concurrentFileNames());
   		 fileTemplate.clearFiles();
   		 
   	 }
	   	Boolean result =srequestDao.checkSalesrequestExistsorNotByEmailAndModelNo(salesrequest);
	   	if(result==false)
			srequestDao.saveRequest(salesrequest);
	   	else
	   		System.out.println("Record Already exists");
		return "redirect:salesRequest";
		
	}
	
}
