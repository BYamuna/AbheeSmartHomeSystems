package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.charvikent.abheeSmartHomeSystems.dao.ProductGuaranteeDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeBranch;
import com.charvikent.abheeSmartHomeSystems.model.ProductGuarantee;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class ProductGuaranteeController 
{
	@Autowired ProductGuaranteeDao productGuaranteeDao;
	/*@RequestMapping(value = "/productGuarantee" ,method = RequestMethod.GET)
	public String productWarrantyView(Model model)
	{
		model.addAttribute("guaranteef", new ProductGuarantee());
		List<ProductGuarantee>pglist=productGuaranteeDao.getProductWarrantyList();
		model.addAttribute("pglist",pglist);
		model.addAttribute("customerid", productGuaranteeDao.getCustomersMap());
		model.addAttribute("productmodelid", productGuaranteeDao.getProductsMap());
		return "productguarantee";
		
	}*/
	@RequestMapping(value = "/productGuarantee" ,method = RequestMethod.POST)
	public String saveProductWarranty(@Valid @ModelAttribute("guaranteef") ProductGuarantee productGuarantee,BindingResult bindingresults, RedirectAttributes redir) throws IOException 
	{
		System.out.println("Enter to post............");
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		int id = 0;
		try
		{
			ProductGuarantee orgBean=null;
			if(productGuarantee.getId()!=null)
			{
			  orgBean=  (ProductGuarantee) productGuaranteeDao.getProductWarrantyDetailsByObject(productGuarantee);
			
			}
			int dummyId =0;
			
			if(orgBean != null){
				dummyId = orgBean.getId();
			}
			
			if(productGuarantee.getId()==null)
			{
				if(dummyId ==0)
				{	
					productGuaranteeDao.saveWarranty(productGuarantee);
					
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
				id=productGuarantee.getId();
				if(id == dummyId || orgBean == null)
				{
					
					productGuaranteeDao.updateWarranty(productGuarantee);
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
	

		return "redirect:productguarantee";
		
	}
	@RequestMapping(value = "/productGuarantee" ,method = RequestMethod.GET)
	public String  ProductWarrantyList( @ModelAttribute("guaranteef") ProductGuarantee productGuarantee,Model model ,HttpServletRequest request) 
	{
		List<Map<String, Object>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		model.addAttribute("guaranteef", new ProductGuarantee());
		model.addAttribute("customerid", productGuaranteeDao.getCustomersMap());
		model.addAttribute("productmodelid", productGuaranteeDao.getProductsMap());
		
		try {
			listOrderBeans = productGuaranteeDao. getProductWarrantyList();
			System.out.println(listOrderBeans);
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
		return "productguarantee";
	}
	@RequestMapping(value = "/deleteProductWarranty")
	public @ResponseBody String deactiveProductWarranty(ProductGuarantee  productGuarantee ,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<Map<String, Object>> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(productGuarantee.getId() != 0){
 				delete = productGuaranteeDao.deleteProductWarranty(productGuarantee.getId(),productGuarantee.getStatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
 				
			listOrderBeans =  productGuaranteeDao. getProductWarrantyList();
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
	
	
	@RequestMapping(value = "/inActiveProductWarranty")
	public @ResponseBody String getAllActiveOrInactiveWarrantyList( ProductGuarantee  productGuarantee ,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<Map<String, Object>> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(productGuarantee.getStatus().equals("0"))
				listOrderBeans = productGuaranteeDao.getAllInActiveList();
				else
					listOrderBeans = productGuaranteeDao. getProductWarrantyList();
					
					
 				
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
