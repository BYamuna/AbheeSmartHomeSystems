package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.abheeSmartHomeSystems.dao.ProductDao;
import com.charvikent.abheeSmartHomeSystems.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ProductController 
{
	@Autowired
	ProductDao productDao;
	
	@RequestMapping("/product")
	public String  ProductList( @ModelAttribute("productf")  Product prof,Model model ,HttpServletRequest request) 
	{
		List<Product> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		model.addAttribute("product", new Product());
		
		try {
			listOrderBeans = productDao.getProductNames();
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
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute  Product pro, BindingResult bindingresults,
			RedirectAttributes redir) throws IOException 
	{
		
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
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
 				
			listOrderBeans = productDao.getProductNames();
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
		List<Product> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(objdept.getStatus().equals("0"))
				listOrderBeans = productDao.getAllInActiveList();
				else
					listOrderBeans = productDao.getProductNames();
					
					
 				
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