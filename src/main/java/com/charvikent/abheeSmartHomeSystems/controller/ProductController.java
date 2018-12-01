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
import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.CompanyDao;
import com.charvikent.abheeSmartHomeSystems.dao.ProductDao;
import com.charvikent.abheeSmartHomeSystems.model.Company;
import com.charvikent.abheeSmartHomeSystems.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ProductController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger( ProductController   .class);
	@Autowired
	ProductDao productDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	FilesStuff fileTemplate;
	
	@RequestMapping("/product")
	public String  ProductList( @ModelAttribute("productf")  Product prof,Model model ,HttpServletRequest request,Company cate) 
	{
		LOGGER.debug("Calling product at controller");
		List<Product> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		model.addAttribute("product", new Product());
		
		model.addAttribute("CategoriesMap",categoryDao.getCategorymap());
		model.addAttribute("companiesMap",companyDao.getCompanymap(cate));
		
		
		try {
			listOrderBeans = productDao. getProductDetails();
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
		return "product";
	}

	@RequestMapping(value ="/products", method = RequestMethod.POST)
	public @ResponseBody String ProductLists( @ModelAttribute("productf")  Product prof,Model model ,HttpServletRequest request,Company cate) 
	{
		LOGGER.debug("Calling product at controller");
		List<Company> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;		
		try {
			listOrderBeans = (List<Company>) companyDao.getCompanies(cate);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				 System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return sJson.toString();
	}
	
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute  Product pro, BindingResult bindingresults, @RequestParam("file1") MultipartFile[] productpics,@RequestParam("vlink") String vlink[],
			RedirectAttributes redir) throws IOException 
	{
		LOGGER.debug("Calling product at controller");
		System.out.println(vlink.length);
		
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		
		String sfn ="";
		
		if(vlink.length != 0)
		{
		 for(String files: vlink)
	        {
	        	sfn=sfn+files+"*"; 
	        }
		 
	        String sfn2=sfn.substring(0,sfn.length()-1);
		
	        
	        pro.setProductmodelvideoslinks(sfn2);
		}
		
		int id = 0;
		try
		{
			 Product orgBean= productDao.getProductNameById(pro);
			int dummyId =0;
			
			if(orgBean != null){
				dummyId = orgBean.getId();
			}
			
			if(pro.getId()==null)
			{
				if(dummyId ==0)
				{
					
					int filecount =0;
			    	 
			    	 for(MultipartFile multipartFile : productpics) {
							String fileName = multipartFile.getOriginalFilename();
							if(!multipartFile.isEmpty())
							{
								filecount++;
							 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
							}
						}
			    	 
			    	 if(filecount>0)
			    	 {
			    		 pro.setProductmodelpics(fileTemplate.concurrentFileNames());
			    		 fileTemplate.clearFiles();
			    		 
			    	 }
					pro.setStatus("1");
					productDao.saveProduct(pro);

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
				id=pro.getId();
				if(id == dummyId || orgBean == null)
				{
					
					int filecount =0;
		        	 
		        	 for(MultipartFile multipartFile : productpics) {
		    				String fileName = multipartFile.getOriginalFilename();
		    				if(!multipartFile.isEmpty())
		    				{
		    					filecount++;
		    				 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
		    				}
		    			}
		        	 
		        	 if(filecount>0)
		        	 {
		        		 pro.setProductmodelpics(fileTemplate.concurrentFileNames());
		        		 fileTemplate.clearFiles();
		        		 
		        	 }
					productDao.UpdateProduct(pro);
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
		
		
		return "redirect:product";
	}
	
	@RequestMapping(value = "/deleteProduct")
	public @ResponseBody String deleteProduct(Product objorg,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) 
	{
		//System.out.println("deleteEducation page...");
		LOGGER.debug("Calling deleteProduct at controller");
		List<Product> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objorg.getId() != 0){
 				delete = productDao.deleteProduct(objorg.getId(),objorg.getStatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
 				
			listOrderBeans = productDao.getProductDetails();
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
	@RequestMapping(value = "/inActiveProducts")
	public @ResponseBody String getAllActiveOrInactiveProducts(Product objdept,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) 
	{
		LOGGER.debug("Calling inActiveProducts at controller");
		List<Product> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(objdept.getStatus().equals("0"))
				listOrderBeans = productDao.getAllInActiveList();
			else
				listOrderBeans = productDao.getProductDetails();
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
