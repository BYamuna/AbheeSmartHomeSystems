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

import com.charvikent.abheeSmartHomeSystems.dao.AbheeBranchDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeBranch;
import com.charvikent.abheeSmartHomeSystems.model.Department;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class AbheeBranchController {
	
	

	@Autowired
	AbheeBranchDao abheeBranchDao;
	
	
	
	
	@RequestMapping("/abBranch")
	public String  showBranches( @ModelAttribute("abheeBranchf")  AbheeBranch abheeBranchf,Model model ,HttpServletRequest request) {
		List<AbheeBranch> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try {
			listOrderBeans = abheeBranchDao.getAbheeBranchNames();
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
		
		
		return "abBranch";
	
	}
	
	
	@RequestMapping(value = "/abBranch", method = RequestMethod.POST)
	public String saveBranch(@Valid @ModelAttribute  AbheeBranch abheeBranch, BindingResult bindingresults,
			RedirectAttributes redir) throws IOException {
		
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		
		int id = 0;
		try
		{
			AbheeBranch  abheeBranchBean= abheeBranchDao.getAbheeBranchById(abheeBranch);
			int dummyId =0;
			
			if(abheeBranchBean != null){
				dummyId = abheeBranchBean.getId();
			}
			
			if(abheeBranch.getId()==null)
			{
				if(dummyId ==0)
				{
					abheeBranch.setStatus("1");
					abheeBranchDao.saveAbheeBranch(abheeBranch);

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
				id=abheeBranch.getId();
				if(id == dummyId || abheeBranchBean == null)
				{
					abheeBranchDao.updateAbheeBranch(abheeBranch);
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
		
		
		return "redirect:abBranch";
	}
	
	
	@RequestMapping(value = "/deleteAbBranch")
	public @ResponseBody String deactiveBranch(AbheeBranch  abheeBranch,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<AbheeBranch> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(abheeBranch.getId() != 0){
 				delete = abheeBranchDao.deleteAbheeBranch(abheeBranch.getId(),abheeBranch.getStatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
 				
			listOrderBeans = abheeBranchDao.getAbheeBranchNames();
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
	
	
	@RequestMapping(value = "/inActiveAbBranch")
	public @ResponseBody String getAllActiveOrInactiveBranches(AbheeBranch  abheeBranch,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<AbheeBranch> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(abheeBranch.getStatus().equals("0"))
				listOrderBeans = abheeBranchDao.getInActiveList();
				else
					listOrderBeans = abheeBranchDao.getAbheeBranchNames();
					
					
 				
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
	
	
	@RequestMapping("/dashBoard")
	public String showDashBoard(Model model,HttpServletRequest request) 
	{
		 return "dashBoard";
		
	}

	
	

}
