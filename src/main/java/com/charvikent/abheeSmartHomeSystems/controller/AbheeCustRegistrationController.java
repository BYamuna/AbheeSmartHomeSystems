package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.abheeSmartHomeSystems.config.KptsUtil;
import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.config.SendingMail;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeCustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.OTPDetailsDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeCustRegistration;
import com.charvikent.abheeSmartHomeSystems.model.OTPDetails;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class AbheeCustRegistrationController 
{
	@Autowired
	AbheeCustomerDao adao;
	
	@Autowired
	SendSMS sendSMS;
	
	@Autowired
	OTPDetailsDao oTPDetailsDao;
	@Autowired
	UserService userService;
	@Autowired
	KptsUtil kptsUtil;
	@Autowired
	SendingMail sendingMail;
	
	String otpnumber ="";
	
	@RequestMapping("/custRegistration")	
	public String AbheeCustRegistrationPage(Model model,HttpServletRequest request)
	{
	  model.addAttribute("custReg",new User());
	  List<User> listOrderBeans = null;
	  ObjectMapper objectMapper = null;
	  String sJson = null;
	  try 
	  {
			listOrderBeans = adao.getAbheeCustomerNames();
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

		} 
	  catch (Exception e) 
	  	{
			e.printStackTrace();
			System.out.println(e);
		}
	  return "custRegistration";
	  
	}
	/*@RequestMapping(value = "/custreg", method = RequestMethod.POST)
	public String saveAbheeCustRegistration(@Validated @ModelAttribute  User abheecustregistration,Model model) throws IOException 
	{
		
		User custbean =adao.checkCustomerExistOrNot(abheecustregistration);
		
		if(custbean == null)
		{
		adao.saveabheecustregistration(abheecustregistration);
		}
		else
		{
			// send only otp screen
		}
		return "redirect:custRegistration";
		
	}*/
	
	@RequestMapping(value = "/checkCustExst", method = RequestMethod.POST)
	public @ResponseBody  Boolean checkCustomerExistence(@Validated @ModelAttribute  AbheeCustRegistration abheecustregistration,Model model,HttpServletRequest request) throws IOException 
	{
		System.out.println("enter to checkCustExst");
		
		String custMobile=request.getParameter("cmobile");
		
		User custbean =userService.checkCustomerExistOrNotbyMobile(custMobile);
		
		if(custbean != null)
		{
			return true;
		}
		else
		
		return false;
		
	}
	
	/*@RequestMapping(value = "/task", method = RequestMethod.GET)
	public String showtaskPage(@Validated @ModelAttribute Model model,HttpServletRequest request) throws IOException 
	{
		return "task";
		
	}*/
	
	
	@RequestMapping(value = "/customerDashBoard", method = RequestMethod.GET)
	public String showCustomerdashBoard(Model model) throws IOException 
	{
		return "customerDashBoard";
		
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/modelSubmit", method = RequestMethod.POST)
	public @ResponseBody  boolean modelSubmit(Model model,HttpServletRequest request) throws IOException 
	{
		System.out.println("enter to model Submit");
		
		String custMobile=request.getParameter("cmobile");
		String cemail=request.getParameter("cemail");
		String csname=request.getParameter("csname");
		String cname=request.getParameter("cname");
		String cotp=request.getParameter("cotp");
		String cpassword=request.getParameter("cpassword");
		
		/*AbheeCustRegistration abcust =new AbheeCustRegistration();
		
		abcust.setMobileno(custMobile);
		abcust.setName(cname);
		abcust.setSurname(csname);
		abcust.setEmail(cemail);
		abcust.setOTP(cotp);
		
		adao.saveabheecustregistration(abcust);
		*/
		
		User customer =new User();
		
		
		String usernumber =kptsUtil.randNum();
		  
		String regSuccessMsg =csname+" "+cname+",  Successfully registered with ABhee Smart Homes. \n You can login using  \n UserId:  "+custMobile+" or "+cemail+"\n password: "+cpassword;

		
		
		
		customer.setMobilenumber(custMobile);
		customer.setFirstname(csname);
		customer.setLastname(cname);
		customer.setEmail(cemail);
		customer.setPassword(cpassword);
		customer.setEnabled("1");
		customer.setDesignation("9");
		//customer.setUsername(str);
		String returnmsg ="";
		if(otpnumber.equals(cotp))
		{
		userService.saveUser(customer);
		sendSMS.sendSMS(regSuccessMsg,custMobile);
		return true;
		
		}
		else
		return false;
		
	}
	
	
	
	@RequestMapping(value = "/getOtp", method = RequestMethod.POST)
	public @ResponseBody  Boolean getOTP(Model model,HttpServletRequest request) throws IOException 
	{
		System.out.println("enter to getOtp");
		
		String custMobile=request.getParameter("cmobile");
		Random random = new Random();
		 otpnumber = String.format("%04d", random.nextInt(10000));
		
		sendSMS.sendSMS(otpnumber,custMobile);
		OTPDetails oTPDetails =new OTPDetails();
		
		oTPDetails.setMobileno(custMobile);
		oTPDetails.setOTPnumber(otpnumber);
		
		oTPDetailsDao.saveOTPdetails(oTPDetails);
		
		
		
		
		return true;
		
	}
	
	@RequestMapping(value = "/checkEmailExst", method = RequestMethod.POST)
	public @ResponseBody  Boolean checkemailExistence(@Validated @ModelAttribute  AbheeCustRegistration abheecustregistration,Model model,HttpServletRequest request) throws IOException 
	{
		System.out.println("enter to checkCustExst");
		
		String custMobile=request.getParameter("cmobile");
		
		User custbean =userService.checkCustomerExistOrNotbyEmail(custMobile);
		
		if(custbean != null)
		{
			return true;
		}
		else
		
		return false;
		
	}
	
	
	@RequestMapping(value = "/inActiveCust")
	public @ResponseBody String getAllActiveOrInactiveOrgnizations(User  objdept,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<User> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(objdept.getStatus().equals("0"))
				listOrderBeans = userService.getCustomerInActiveList();
				else
					listOrderBeans =  adao.getAbheeCustomerNames();



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
	
	@RequestMapping(value = "/deleteCustomer")
	public @ResponseBody String deleteEmployee(User  objUser,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<User> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objUser.getId() != 0){
 				delete = userService.deleteUser(objUser.getId(),objUser.getStatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}

			listOrderBeans =  adao.getAbheeCustomerNames();
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
	
	@RequestMapping(value = "/custreg" ,method = RequestMethod.POST)
	public String saveAdmin(@Valid @ModelAttribute  User user, BindingResult bindingresults,
			RedirectAttributes redir) throws IOException {
		
		
		if(user.getBranchId()==null)
		{
			User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			user.setBranchId(objuserBean.getBranchId());
			
		}

		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}

		int id = 0;
		try
		{
			User userBean=null;
			if(user.getId()!=null)
			{
			  userBean= userService.getUserByObject(user);

			}
			int dummyId =0;

			if(userBean != null){
				dummyId = userBean.getId();
			}

			if(user.getId()==null)
			{
				if(dummyId ==0)
				{


					user.setEnabled("1");
					user.setDesignation("9");

					userService.saveUser(user);
					
					sendingMail.sendConfirmationEmail(user);

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
				id=user.getId();
				if(id == dummyId || userBean == null)
				{
					user.setDesignation("9");
					//userService.updateUser(user);
					sendingMail.sendConfirmationEmail(user);
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
		return "redirect:custRegistration";
	}



}
