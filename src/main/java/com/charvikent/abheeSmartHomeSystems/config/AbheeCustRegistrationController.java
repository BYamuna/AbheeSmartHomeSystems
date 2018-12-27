package com.charvikent.abheeSmartHomeSystems.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.OTPDetailsDao;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.OTPDetails;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AbheeCustRegistrationController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger( AbheeCustRegistrationController .class);
	@Autowired SendSMS sendSMS;
	@Autowired OTPDetailsDao oTPDetailsDao;
	@Autowired UserService userService;
	@Autowired KptsUtil kptsUtil;
	@Autowired SendingMail sendingMail;
	@Autowired CustomerDao customerDao;
	@Autowired private Environment environment;
	String otpnumber ="";
	
	@RequestMapping("/custRegistration")	
	public String AbheeCustRegistrationPage(Model model,HttpServletRequest request)
	{
	  LOGGER.debug("Calling  custRegistration at controller");
	  model.addAttribute("custReg",new Customer());
	  List<Customer> listOrderBeans = null;
	  ObjectMapper objectMapper = null;
	  String sJson = null;
	  model.addAttribute("allCustomerTypes", customerDao.getCustomerTypes());
	  try 
	  {
			listOrderBeans = customerDao.getAbheeCustomerNames();
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
	public @ResponseBody  Boolean checkCustomerExistence(@Validated @ModelAttribute  Customer abheecustregistration,Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  checkCustExst at controller");
		System.out.println("enter to checkCustExst");
		
		String custMobile=request.getParameter("cmobile");
		
		String editFieldsId=request.getParameter("editFields");
		Customer custbean1 =null;
		if(editFieldsId.equals("0"))
		{
		
			custbean1 =customerDao.checkCustomerExistOrNotbyMobile(custMobile);
		}
		else
		{
			custbean1 =customerDao.checkCustomerExistOrNotbyMobileOnEdit(custMobile,editFieldsId);
			
		}
		
		if(custbean1 != null)
		{
			return true;
		}
		else
		
		return false;
		
	}
	@RequestMapping(value = "/checkCustExstOrnotByPassword", method = RequestMethod.POST)
	public @ResponseBody  Boolean checkCustExstOrnotByPassword(@Validated @ModelAttribute  Customer abheecustregistration,Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  checkCustExst at controller");
		System.out.println("enter to checkCustExst");
		
		String editFieldsId=request.getParameter("editFields");
		String custPassword=request.getParameter("cpassword");
		
		Customer custbean1 =null;
		if(!editFieldsId.equals("0"))
		{
		
			custbean1 =customerDao.checkCustomerExistOrNotbyPassword(custPassword);
		}
		
		if(custbean1 != null)
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
		LOGGER.debug("Calling  customerDashBoard at controller");
		return "customerDashBoard";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/modelSubmit", method = RequestMethod.POST)
	public @ResponseBody  boolean modelSubmit(Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  modelSubmit at controller");
		System.out.println("enter to model Submit");
		String custMobile=request.getParameter("cmobile");
		String cemail=request.getParameter("cemail");
		String csname=request.getParameter("csname");
		String cname=request.getParameter("cname");
		String caddress=request.getParameter("caddress");
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
		Customer customer =new Customer();
		String usernumber =kptsUtil.randNum();
		String regSuccessMsg =csname+" "+cname+",  Successfully registered with ABhee Smart Homes as Customer. \n You can login using \n Mobilenumber:  "+custMobile+" or Email:"+cemail+" and \n password: "+cpassword;
		customer.setMobilenumber(custMobile);
		customer.setFirstname(csname);
		customer.setLastname(cname);
		customer.setEmail(cemail);
		customer.setAddress(caddress);
		customer.setPassword(cpassword);
		customer.setEnabled("1");
		customer.setCustomerType("1");
		//customer.setUsername(str);
		String returnmsg ="";
		if(otpnumber.equals(cotp))
		{
			customer.setRegistedredFromAndroid("0");
			customerDao.saveAbheeCustomer(customer);
			sendSMS.sendSMS(regSuccessMsg,custMobile);
			return true;
		}
		else
			return false;	
	}
	
	@RequestMapping(value = "/getOtp", method = RequestMethod.POST)
	public @ResponseBody  Boolean getOTP(Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  getOtp at controller");
		System.out.println("enter to getOtp");
		String custMobile=request.getParameter("cmobile");
		Random random = new Random();
		otpnumber = String.format("%04d", random.nextInt(10000));
		String tmsg =environment.getProperty("app.otpmsg");
		 System.out.println(tmsg);
		tmsg= tmsg.replaceAll("_otpnumber_", otpnumber);
		sendSMS.sendSMS(tmsg,custMobile);
		OTPDetails oTPDetails =new OTPDetails();
		oTPDetails.setMobileno(custMobile);
		oTPDetails.setOTPnumber(otpnumber);
		oTPDetailsDao.saveOTPdetails(oTPDetails);		
		return true;		
	}
	@RequestMapping(value = "/checkEmailExst", method = RequestMethod.POST)
	public @ResponseBody  Boolean checkemailExistence(@Validated @ModelAttribute  Customer abheecustregistration,Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  checkEmailExst at controller");
		System.out.println("enter to checkCustExst");
		String custEmail=request.getParameter("cemail");
		String editFieldsId=request.getParameter("editFields");
		Customer customer =null;
		 if(editFieldsId.equals("0"))
			{
			 customer =customerDao.checkCustomerExistOrNotByEmail(custEmail);
			}
			else
			{
				  customer =customerDao.checkCustomerExistOrNotByEmailOnEdit(custEmail,editFieldsId);
			}
		if(customer != null)
		{
			return true;
		}
		else
		return false;
	}

	@RequestMapping(value = "/inActiveCust")
	public @ResponseBody String getAllActiveOrInactiveOrgnizations(Customer  objdept,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		LOGGER.debug("Calling  inActiveCust at controller");
		List<Customer> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(objdept.getStatus().equals("0"))
				listOrderBeans = customerDao.getCustomerInActiveList();
				else
				listOrderBeans =  customerDao.getAbheeCustomerNames();
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
	public @ResponseBody String deleteEmployee(Customer  objUser,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		LOGGER.debug("Calling  deleteCustomer at controller");
		List<Customer> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objUser.getId() != 0){
 				delete = customerDao.deleteCustomer(objUser.getId(),objUser.getEnabled());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
			listOrderBeans =  customerDao.getAbheeCustomerNames();
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
	public String saveCustomer(@Valid @ModelAttribute("custreg")  Customer user, BindingResult bindingresults,RedirectAttributes redir) throws IOException {
		LOGGER.debug("Calling  custreg at controller");
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		int id = 0;
		try
		{
			Customer userBean=null;
			if(user.getId()!=null)
			{
			  userBean= customerDao.getCustomerByObject(user);
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
					customerDao.saveAbheeCustomer(user);	
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
					customerDao.updateCustomer(user);
					sendingMail.sendConfirmationEmail(user);
					redir.addFlashAttribute("msg", "Record Updated Successfully");
					redir.addFlashAttribute("cssMsg", "warning");
				}else
				{
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");
				}
		
		}
		}
			catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return "redirect:custRegistration";
	}

	@RequestMapping(value = "/getresetcustomerpassword", method = RequestMethod.POST)
	public @ResponseBody  Boolean getResetCustomerPassword(Model model,HttpServletRequest request) throws IOException, MessagingException 
	{
		LOGGER.debug("Calling  getresetcustomerpassword at controller");
		System.out.println("enter to getresetcustomerpassword");
		String custMobile=request.getParameter("resetmobile");
		String custEmail=request.getParameter("resetmobile");
		Customer custbean2 =customerDao.checkCustomerExistOrNotbyMobileOrEmail(custMobile,custEmail);
		if(custbean2 != null)
		{
			sendSMS.sendSMS("Dear"+" "+custbean2.getFirstname()+" "+custbean2.getLastname()+", your password for Abhee smart home systems account"+" '"+custbean2.getMobilenumber()+"' "+"is:"+custbean2.getPassword(),custMobile);
			sendingMail.resetPassword(custbean2);
			return true;
		}
		else
			return false;	
	}
	
	@RequestMapping(value = "/checkEmpExst", method = RequestMethod.POST)
	public @ResponseBody  Boolean checkCustomerExistence1(@Validated @ModelAttribute Customer abheecustregistration,Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  checkEmpExst at controller");
		System.out.println("enter to checkCustExst");
		String custMobile=request.getParameter("cmobile");
		String editFieldsId=request.getParameter("editFields");
		User custbean =null;
		 if(editFieldsId.equals("0"))
			{
			 custbean	=userService.checkEmployeeExistOrNotbyMobile(custMobile);
			}
			else
			{
				custbean =userService.checkEmployeeExistOrNotbyMobileOnEdit(custMobile,editFieldsId);	
			}
		if(custbean != null)
		{
			return true;
		}
		else
		
		return false;
	}
	
	@RequestMapping(value = "/checkEmpExstbyemail", method = RequestMethod.POST)
	public @ResponseBody  Boolean checkEmployeeExistence(@Validated @ModelAttribute  Customer abheecustregistration,Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  checkEmpExstbyemail at controller");
		System.out.println("enter to checkCustExst");
		String empcemail=request.getParameter("cemail");
		String editFieldsId=request.getParameter("editFields");
		User custbean =null;
		 if(editFieldsId.equals("0"))
			{
			 custbean	=userService.checkEmployeeExistOrNotbyEmail(empcemail);
			}
			else
			{
				custbean =userService.checkEmployeeExistOrNotbyEmail(empcemail,editFieldsId);	
			}
		if(custbean != null)
		{
			return true;
		}
		else
		    return false;	
	}
	
	@RequestMapping(value = "/resendOtp", method = RequestMethod.POST)
	public @ResponseBody  Boolean resendOTP(Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  resendOtp at controller");
		System.out.println("enter to resendOtp");
		String custMobile=request.getParameter("cmobile");
		Random random = new Random();
		otpnumber = String.format("%04d", random.nextInt(10000));
		String tmsg =environment.getProperty("app.otpmsg");
		 System.out.println(tmsg);
		tmsg= tmsg.replaceAll("_otpnumber_", otpnumber);
		//sendSMS.sendSMS(tmsg,custMobile);
		OTPDetails oTPDetails =new OTPDetails();
		oTPDetails.setMobileno(custMobile);
		oTPDetails.setOTPnumber(otpnumber);
		List<Map<String, Object>> curotplist=oTPDetailsDao.getCurrentDayList(custMobile);
		if(curotplist.size()<=4) 
		{
		    sendSMS.sendSMS(tmsg,custMobile);
			oTPDetailsDao.saveOTPdetails(oTPDetails);	
			return true;
			
		}
		else
		{
			System.out.println("OTP Limit Expired For Today");
			return false;
		}	
	}
	
	
}
