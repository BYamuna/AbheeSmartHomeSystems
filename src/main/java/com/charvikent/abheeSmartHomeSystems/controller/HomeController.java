package com.charvikent.abheeSmartHomeSystems.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.abheeSmartHomeSystems.config.KptsUtil;
import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskDao;
import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.OTPDetailsDao;
import com.charvikent.abheeSmartHomeSystems.dao.ProductGuaranteeDao;
import com.charvikent.abheeSmartHomeSystems.dao.SalesRequestDao;
/*import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;*/
import com.charvikent.abheeSmartHomeSystems.model.Category;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.OTPDetails;
/*import com.charvikent.abheeSmartHomeSystems.model.ProductGuarantee;*/
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	@Autowired UserService userService;
	@Autowired CustomerDao customerDao;
	@Autowired CategoryDao categoryDao;
	@Autowired ProductGuaranteeDao productGuaranteeDao;
	@Autowired SalesRequestDao srequestDao;
	@Autowired SendSMS sendSMS;
	@Autowired OTPDetailsDao oTPDetailsDao;
	@Autowired KptsUtil kptsUtil;
	@Autowired AbheeTaskDao abheeTaskDao;
	@Autowired private Environment environment;
	static 	String loginurl=""; 
	static boolean falg =true;
	String otpnumber ="";
	
	@RequestMapping("/admin")
	public String customlogin(Model model) {	
		LOGGER.debug("Calling Admin Login page index::{} at controller");
		 /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if (null != auth){    
		        return "redirect:dashboard";
		    }
		 else*/
		return "login";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/userlogin")
	public String userLogin(Model model) {
		LOGGER.debug("Calling User Login page index::{} at controller");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return "userlogin";
	}
	
	@RequestMapping("/login")
	public String loginView(Model model) {
		LOGGER.debug("Calling Login page index::{} at controller");
		System.out.println("login called at /Rlogin page");
		//User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 //userService.setLoginRecord(objuserBean.getId(),"login");
		//userService.checkuserExistOrNot(objuserBean);
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		
		LOGGER.debug("Calling Logout page index::{} at controller");
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
	    userService.setLoginRecord(objuserBean.getId(),"logout");
	    
	   // userService.setLoginRecord(objuserBean.getId(),"logout");
	    if (null != auth){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    SecurityContextHolder.clearContext();
	    if(null != auth) {
	    	SecurityContextHolder.getContext().setAuthentication(null);
	    }
	    System.out.println("Called Logout");
	    return "redirect:/login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	/*@RequestMapping("/403")
	public String failureLogin(Model model) {
		return "403";
	}*/
	@RequestMapping("/customerlogin")
	public String ShowCustomerLoginPage(Model model,HttpServletRequest request) {
		LOGGER.debug("Calling Customer Login page index::{} at controller");
		if(falg)
		loginurl =request.getHeader("referer");
		
		return "customerlogin";
	}
	
	@PostMapping("/customerlogin")
	public String validateCustomerLogin(Model model,HttpServletRequest request,HttpSession session,RedirectAttributes redir) throws JsonProcessingException {
		
		LOGGER.debug("Validating Customer Login page index::{} at controller");
		String loginid=request.getParameter("username");
		String password=request.getParameter("password");
		
		Customer customer =customerDao.validateCustomer(loginid,password);
		String referalUrl=request.getHeader("referer");
		
		if(null ==customer)
		{
			System.out.println("Customer does not exists"+referalUrl);
			falg=false;
			redir.addFlashAttribute("msg", "Invalid Details");
			redir.addFlashAttribute("cssMsg", "danger");
			return "redirect:customerlogin";
		}
		else if(null==loginurl)
				{
			session.setAttribute("customer", customer);
			session.setAttribute("loggedstatus", "login");
			session.setAttribute("customerId", customer.getCustomerId());
			session.setAttribute("customerName", customer.getFirstname());
			return "redirect:/";
			
				}
		else
			
		{
		session.setAttribute("customer", customer);
		session.setAttribute("loggedstatus", "login");
		session.setAttribute("customerId", customer.getCustomerId());
		session.setAttribute("customerName", customer.getFirstname());
			System.out.println("()()()()()()()()()()("+loginurl);
			return "redirect:"+ loginurl;
		}
		
	}
	
	@PostMapping("/test")
	public String test(Model model,HttpServletRequest request) {
		LOGGER.debug("Calling Admin Login page index::{} at controller");
		//URI str= hrequest.getURI();
		
		System.out.println(request.getContextPath());
		
		System.out.println(request.getRequestURL());
		
		System.out.println(request.getHeader("referer"));
		
		//System.out.println(hrequest.getHeaders());
		
		return "admin";
	}
	/*@RequestMapping("/dummypage")
	public String dummy(Model model) {
		return "dummypage";
	}*/
	
	@RequestMapping("/")
	public String ShowAbhee(Model model,HttpServletRequest request,HttpSession session) throws JSONException, JsonProcessingException 
	{
		LOGGER.debug("Calling Abhee site Main page at controller");
		List<Category> listOrderBeans = categoryDao.getCategoryNames();
		//model.addAttribute("categories", listOrderBeans);
		ObjectMapper objectMapper = new ObjectMapper();
		String sJson = objectMapper.writeValueAsString(listOrderBeans);	
		request.setAttribute("allOrders1", sJson);
		String referalUrl=request.getHeader("referer");
		System.out.println(referalUrl);
		return "abheeindex";
	}
	
	
	@RequestMapping("/signout")
	public String SignOut(Model model,HttpServletRequest request,HttpSession session) throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Signout page at controller");
		String referalUrl=request.getHeader("referer");
		System.out.println(referalUrl);
		
		falg=true;
		
		session.invalidate();
		 
		return "redirect:/customerlogin";
	}
	
	@RequestMapping("/getCategoryList")
	public @ResponseBody String getCategoryList(Model model,HttpServletRequest request,HttpSession session) throws JSONException, JsonProcessingException 
	{
		LOGGER.debug("Retrieving Categories list at controller");
		List<Category> listOrderBeans = categoryDao.getCategories();
		//model.addAttribute("categories", listOrderBeans);
		ObjectMapper objectMapper = new ObjectMapper();
		String sJson = objectMapper.writeValueAsString(listOrderBeans);	
		request.setAttribute("allOrders1", sJson);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("list", listOrderBeans);
		String referalUrl=request.getHeader("referer");
		System.out.println(referalUrl);
		return String.valueOf(jsonObj);
	}
	@RequestMapping("*")
	public String erro404(Model model,HttpServletRequest request) {
		LOGGER.debug("Calling 404 error page at controller");
		String referalUrl=request.getHeader("referer");
		return "redirect:"+ referalUrl;
	}
	
	@RequestMapping("/customerprofile")
	public String customerProfile(@ModelAttribute("customerProfile") Customer customer, Model model,HttpServletRequest request,HttpSession session,RedirectAttributes redir) throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");	
		Customer customerProfile=(Customer) session.getAttribute("customer");
		//String id=String.valueOf(objuserBean.getId());
          if(null !=customerProfile)
          {
		List<Customer> customerList =new  ArrayList<Customer>(); 
		customerList.add(customerProfile);
		List<Map<String, Object>> ordersList=productGuaranteeDao.getProductWarrantyDetailsByCustomerId(customerProfile.getCustomerId());
		//model.addAttribute("customerProfile", customerProfile);
		ObjectMapper objectMapper = new ObjectMapper();
		String sJson = objectMapper.writeValueAsString(customerList);
		request.setAttribute("customerProfile1", sJson);
		String sJson2 = objectMapper.writeValueAsString(ordersList);
		request.setAttribute("ordersList", sJson2);
          }
          else
          {
        		request.setAttribute("customerProfile1", "''");
        		request.setAttribute("ordersList", "''");    	  
          }
		return "customerprofile";
	}
	
	@RequestMapping(value="/editCustomerProfile", method= RequestMethod.POST )
	public String editProfile(@ModelAttribute("customerProfile") Customer customer,RedirectAttributes redir,HttpServletRequest request){
		LOGGER.debug("Calling editCustomerProfile at controller");
		customerDao.saveAbheeCustomer(customer);
		redir.addFlashAttribute("msg", "Your Details Updated Successfully");
		redir.addFlashAttribute("cssMsg", "info");
		return "redirect:customerprofile";
	}
	
	@RequestMapping("/about")
	public String about() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		return "about";
	}
	@RequestMapping("/career")
	public String career() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		 
		return "career";
	}
	@RequestMapping("/contact")
	public String contact() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		 
		return "contact";
	}
	@RequestMapping("/gallery")
	public String gallery() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		 
		return "gallery";
	}
	@RequestMapping("/location")
	public String location() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		 
		return "location";
	}
	@RequestMapping("/mission")
	public String mission() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		return "mission";
	}
	
	@RequestMapping("/ticketstatus")
	public String ticketstatus(HttpServletRequest request,HttpSession session) throws JsonProcessingException {
			/*String sJson=null;
			Customer customerProfile=(Customer) session.getAttribute("customer");
			Customer customerId=(Customer) session.getAttribute("customerId");
			System.out.println(customerId);
			if(null !=customerProfile)
	        {
				List<Map<String, Object>> QuotationsList=srequestDao.getSalesRequestListByCustomerId(customerProfile.getCustomerId());
				System.out.println(QuotationsList);
				ObjectMapper objectMapper = new ObjectMapper(); 
				 sJson = objectMapper.writeValueAsString(QuotationsList);
		
			request.setAttribute("QuotationsList", sJson);
	          }
	          else
	          {
	        		request.setAttribute("QuotationsList", "''");    	  
	          }*/
		LOGGER.debug("Calling ticketstatus at controller");
		return "ticketstatus";
	}
	
	@RequestMapping(value="/quotationrequests",method = RequestMethod.POST )
	public @ResponseBody String  quotationRequests(Model model,HttpServletRequest request,HttpSession session) throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling quotationrequests at controller");
		
		
		Customer customerProfile=(Customer) session.getAttribute("customer");
		/*Customer customerId=(Customer) session.getAttribute("customerId");
		System.out.println(customerId);*/
		String sJson=null;
		if(null !=customerProfile)
        {
		List<Map<String, Object>> QuotationsList=srequestDao.getSalesRequestListByCustomerId(customerProfile.getCustomerId());
		System.out.println(QuotationsList);
		ObjectMapper objectMapper = new ObjectMapper(); 
		 sJson = objectMapper.writeValueAsString(QuotationsList);
		
		request.setAttribute("QuotationsList", sJson);
          }
          else
          {
        		request.setAttribute("QuotationsList", "''");    	  
          }
		return sJson;
	}
	
	@RequestMapping("/servicerequests")
	public @ResponseBody String serviceRequests(Model model,HttpServletRequest request,HttpSession session) throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling servicerequests at controller");
		Customer customerProfile=(Customer) session.getAttribute("customer");
		String sJson=null;
		if(null !=customerProfile)
        {
		List<Customer> customerList =new  ArrayList<Customer>(); 
		customerList.add(customerProfile);
		List<Map<String, Object>> RequestsList=abheeTaskDao.getTasksListByCustomerId(customerProfile.getCustomerId());
		ObjectMapper objectMapper = new ObjectMapper(); 
	 sJson = objectMapper.writeValueAsString(RequestsList);
		request.setAttribute("RequestsList", sJson);
          }
          else
          {
        		request.setAttribute("RequestsList", "''");    	  
          }
		return sJson;
	}
	
	
	@RequestMapping(value = "/editprofilecustomer", method = RequestMethod.POST)
	public @ResponseBody  String editProfileCustomer(Model model,HttpServletRequest request,HttpSession session) throws IOException, MessagingException 
	{
		LOGGER.debug("Calling editProfileCustomer at controller");
	
		
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String address=request.getParameter("address");
		String pemail=request.getParameter("pemail");
		String pmobilenumber =request.getParameter("pmobilenumber");
		String customerid =request.getParameter("customerid");
		
		 Customer customer = new Customer();
		 customer.setFirstname(firstname);
		 customer.setLastname(lastname);
		 customer.setAddress(address);
		 customer.setEmail(pemail);
		 customer.setMobilenumber(pmobilenumber);
		 customer.setId(Integer.parseInt((customerid)));
		 
		  
			List<Customer> customerList =new  ArrayList<Customer>(); 
			customerList.add(customer);
		 
		 try {
			customerDao.updateCustomerProfile(customer);
			 session.removeAttribute("customerName");
			 session.removeAttribute("customer");
			 
			 session.setAttribute("customerName", firstname);
			 session.setAttribute("customer", customer);
			 ObjectMapper objectMapper = new ObjectMapper();
				String sJson = objectMapper.writeValueAsString(customerList);
				request.setAttribute("customerProfile1", sJson);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@RequestMapping(value = "/saveProfilePassword", method = RequestMethod.POST)
	public @ResponseBody  String saveProfilePassword(Model model,Customer cust,HttpServletRequest request) throws IOException, MessagingException 
	{
		LOGGER.debug("Calling saveProfilePassword at controller");
		String pcurrentpassword=request.getParameter("pcurrentpassword");
		String pconfirmpassword =request.getParameter("pconfirmpassword");
		String customerid =request.getParameter("customerid");
		 Customer customer = new Customer();
		 customer.setId(Integer.parseInt((customerid)));
		 customer.setPassword(pconfirmpassword);
		
		 Customer custbean1 =null;
		 
		 custbean1 =customerDao.checkCustomerExistOrNotbyPassword(pcurrentpassword);
		// String password=null;
		 
		 if(custbean1 != null)
			{
			 customerDao.updateCustomerProfilepassword(customer);
			return "true";
			}
		
		return "false";
	}
	
	//edit email
	@RequestMapping(value = "/editprofileemail", method = RequestMethod.POST)
	public @ResponseBody  String EditProfileEmail(Model model,HttpServletRequest request) throws IOException, MessagingException 
	{
		LOGGER.debug("Calling EditProfileEmail at controller");
		String pemail=request.getParameter("pemail");
		//String pmobilenumber=request.getParameter("pmobilenumber");
		String customerid =request.getParameter("customerid");
		 Customer customer = new Customer();
		 customer.setId(Integer.parseInt((customerid)));
		 customer.setEmail(pemail);
		Customer profilecustomer =	customerDao.checkProfileEmailExistsOrNot(customer);
			if( profilecustomer==null)
			{ 
			try
			{		
				//customerDao.updateCustomerProfileEmail(customer);
				return "true";
				
			}
			catch (Exception e) 
				{
				e.printStackTrace();
				return "failed";
				}		
			}
			else
			{
				return "false";
			}
	}


//edit mobileno
	
	
	@RequestMapping(value = "/editprofilemobileno", method = RequestMethod.POST)
	public @ResponseBody  String EditProfileMobileNo(Model model,HttpServletRequest request) throws IOException, MessagingException 
	{
		LOGGER.debug("Calling EditProfileMobileNo at controller");
		String pmobilenumber =request.getParameter("pmobilenumber");
		String customerid =request.getParameter("customerid");
		 Customer customer = new Customer();
		 customer.setId(Integer.parseInt((customerid)));
		 customer.setMobilenumber(pmobilenumber);
		 Customer profilecustomer =	customerDao.checkProfileMobileNoExistsOrNot(customer);
		 if(profilecustomer==null)
		 {
			 try 
			 {
				//customerDao.updateCustomerProfileMobileNo(customer);
				return "true";	
			 } 
			 catch (Exception e) 
			 {
				e.printStackTrace();
				return "failed";
			 }
		 }	
		 else
		 {
			 return "false";
		 }
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/modelSubmit1", method = RequestMethod.POST)
	public @ResponseBody  boolean modelSubmit1(Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  modelSubmit1 at controller");
		System.out.println("enter to model Submit1");
		String custMobile=request.getParameter("pmobilenumber");
		String customerid =request.getParameter("customerid");
		String cotp=request.getParameter("cotp");
		Customer customer =new Customer();
		customer.setId(Integer.parseInt((customerid)));
		customer.setMobilenumber(custMobile);
		//String cemail=request.getParameter("pemail");
		//customer.setEmail(cemail);
		String returnmsg ="";
		if(otpnumber.equals(cotp))
		{
			customer.setRegistedredFromAndroid("0");
			customerDao.updateCustomerProfileMobileNo(customer);
			//customerDao.saveAbheeCustomer(customer);
			//customerDao.updateCustomerProfileMobileNo(customer);
			//customerDao.updateCustomerEmailOrMobile(customer);
			return true;
		}
		else
			return false;	
	}
	@SuppressWarnings("unused")
	@RequestMapping(value = "/modelSubmit2", method = RequestMethod.POST)
	public @ResponseBody  boolean modelSubmit2(Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  modelSubmit1 at controller");
		System.out.println("enter to model Submit1");
		String cemail=request.getParameter("pemail");
		String customerid =request.getParameter("customerid");
		String cotp=request.getParameter("cotp");
		Customer customer =new Customer();
		customer.setId(Integer.parseInt((customerid)));
		
		customer.setEmail(cemail);
		String returnmsg ="";
		if(otpnumber.equals(cotp))
		{
			customer.setRegistedredFromAndroid("0");
			customerDao.updateCustomerProfileEmail(customer);
			
			return true;
		}
		else
			return false;	
	}
	@RequestMapping(value = "/getEditOtp", method = RequestMethod.POST)
	public @ResponseBody  String getEditOTP(Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  getEditOtp at controller");
		System.out.println("enter to getEditOtp");
		String custMobile=request.getParameter("pmobilenumber");
		Random random = new Random();
		otpnumber = String.format("%04d", random.nextInt(10000));
		String tmsg =environment.getProperty("app.otpmsg");
		tmsg= tmsg.replaceAll("_otpnumber_", otpnumber);
		System.out.println(tmsg);
		sendSMS.sendSMS(tmsg,custMobile);
		OTPDetails oTPDetails =new OTPDetails();
		oTPDetails.setMobileno(custMobile);
		oTPDetails.setOTPnumber(otpnumber);
		oTPDetailsDao.saveOTPdetails(oTPDetails);		
		return "true";		
	}
	
	@RequestMapping(value = "/resendOtpOnMobileEdit", method = RequestMethod.POST)
	public @ResponseBody  Boolean resendOtpMobile(Model model,HttpServletRequest request) throws IOException 
	{
		LOGGER.debug("Calling  resendOtpOnMobileEdit at controller");
		System.out.println("enter to resendOtp");
		String custMobile=request.getParameter("pmobilenumber");
		Random random = new Random();
		otpnumber = "Dear Customer,thanks for registering with Abhee Smart Home Systems. OTP for your registration is:"+String.format("%04d", random.nextInt(10000));
		OTPDetails oTPDetails =new OTPDetails();
		oTPDetails.setMobileno(custMobile);
		oTPDetails.setOTPnumber(otpnumber);
		List<Map<String, Object>> curotplist=oTPDetailsDao.getCurrentDayList(custMobile);
		if(curotplist.size()>4) 
		{
			System.out.println("OTP Limit Expired For Today");
			return false;
		}
		else
		{
		sendSMS.sendSMS(otpnumber,custMobile);
		oTPDetailsDao.saveOTPdetails(oTPDetails);	
		return true;
		}	
	}	
	
	@RequestMapping(value = "/getresetpassword", method = RequestMethod.POST)
	public @ResponseBody Boolean getResetUserPassword(Model model, HttpServletRequest request)
			throws IOException, MessagingException {
		System.out.println("enter to getresetpassword");
		String username = request.getParameter("username");
		System.out.println(username);
		/*
		 * Random rnd = new Random(); int n = 100000 + rnd.nextInt(900000); String
		 * strRandomNumber =String.valueOf(n); PasswordDetails pd =new
		 * PasswordDetails(); pd.setPWDnumber(strRandomNumber);
		 */
		User custbean2 = userService.checkEmployeeExistOrNotbyMobile(username);
		
		if (custbean2 != null) {
			String password = "Dear" + " " + custbean2.getFirstname() + " " + custbean2.getLastname()
			+ ", your password for Abhee smart home systems employee account" + " '" + custbean2.getMobilenumber() + "' "
			+ "is:" + custbean2.getPassword();
			sendSMS.sendSMS(password,username);
			//mailTemplate.resetPassword(custbean2);
			return true;
		} else
			return false;
	}
}
