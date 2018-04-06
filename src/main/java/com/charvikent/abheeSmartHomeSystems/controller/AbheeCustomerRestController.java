package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeCustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.OTPDetailsDao;
import com.charvikent.abheeSmartHomeSystems.dao.ProductDao;
import com.charvikent.abheeSmartHomeSystems.model.Category;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.OTPDetails;
import com.charvikent.abheeSmartHomeSystems.model.Product;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AbheeCustomerRestController {
	
	@Autowired
	AbheeCustomerDao abheeCustomerDao;
	@Autowired CustomerDao customerDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SendSMS sendSMS;
	
	
	@Autowired
	OTPDetailsDao oTPDetailsDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductDao productDao;

	
	@RequestMapping("/Customer")
	public String showCustomerRegistrationForm(Model model,HttpServletRequest request) throws JsonProcessingException
	{
		return null;
		
	}
	
	
	
	//@RequestMapping(value = "/api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/saveRestCustomer", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public HashMap<String, String>  SaveAbheeCustomer( @RequestBody Customer customer) {
		
		String code =null;
		String regSuccessMsg =customer.getFirstname()+" "+customer.getLastname()+",  Successfully registered with ABhee Smart Homes. \n You can login using  \n UserId:  "+customer.getMobilenumber()+" or "+customer.getEmail()+"\n password: "+customer.getPassword();

		try {
			customer.setDesignation("9");
			customerDao.savecustomer(customer);
			sendSMS.sendSMS(regSuccessMsg,customer.getMobilenumber());
			code = customer.getFirstname()+" "+customer.getLastname();
		} catch (IOException e) {
			code="NOT_FOUND";
			e.printStackTrace();
		}
		
HashMap<String,String> hm =new HashMap<String,String>();
		
		hm.put("status", code);
		return hm;
	}
	
	@RequestMapping(value="/requestsms", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public ResponseEntity<String>  getOTP( @RequestBody Customer user) {
		
		user.setDesignation("9");
		String custMobile=user.getMobilenumber();
		Random random = new Random();
		String  otpnumber = String.format("%04d", random.nextInt(10000));
		
		HttpStatus code =null;
		try {
			String status = sendSMS.sendSMS(otpnumber,custMobile);
			if(status.equals("OK")){
				code = HttpStatus.OK;
			}else{
				code=HttpStatus.NOT_FOUND;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OTPDetails oTPDetails =new OTPDetails();
		
		oTPDetails.setMobileno(custMobile);
		oTPDetails.setOTPnumber(otpnumber);
		
		oTPDetailsDao.saveOTPdetails(oTPDetails);
		ResponseEntity<String> response = new ResponseEntity<String>(otpnumber,code);
		return response;
		

		
	}
	
	
	@RequestMapping(value="/requestsms2", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public HashMap<String, String> VerifyingAndSendOTP( @RequestBody Customer custBean) {
		
		String custMobile=custBean.getMobilenumber();
		
		String custemail=custBean.getEmail();
		Random random = new Random();
		String  otpnumber = String.format("%04d", random.nextInt(10000));
		
		
		String code =null;
		
         Customer custbean1 =customerDao.checkCustomerExistOrNotbyMobile(custMobile);
         
         Customer customer =customerDao.checkCustomerExistOrNotByEmail(custemail);
		
		if(custbean1 != null)
		{
			code ="already exists Mobile";
		}
		else if(customer !=null)
		{
			
				code  ="already exists Email";
		}
		else
		{
		try {
			String status = sendSMS.sendSMS(otpnumber,custMobile);
			if(status.equals("OK")){
				code = "OK";
				OTPDetails oTPDetails =new OTPDetails();  
				
				oTPDetails.setMobileno(custMobile);
				oTPDetails.setOTPnumber(otpnumber);
				
				oTPDetailsDao.saveOTPdetails(oTPDetails);
				
				
			}else{
				code="NOT_FOUND";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		HashMap<String,String> hm =new HashMap<String,String>();
		
		hm.put("otpnumber", otpnumber);
		hm.put("statuscode", code);
		
	
		return hm;
		
		

		
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/logincredentials", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  checkingLogincredentials( @RequestBody Customer customer) throws JsonProcessingException, JSONException {
		
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		
		JSONObject json =new JSONObject();
		
		System.out.println("rest call user called at end");
		
		
		
			Customer userBean =customerDao.checkuserExistOrNot(customer);
			
			System.out.println("rest call user called at staring"+userBean);
			
			
			ObjectMapper objectMapper = new ObjectMapper();
			String userjson = objectMapper.writeValueAsString(userBean);
			
			if(null != userBean)
			{
				code =userBean.getFirstname()+" "+userBean.getLastname();
				json.put("status", userBean);
				
			}
			else
			{
				json.put("status", "NOT_FOUND");
		
			System.out.println("rest call user called at end");
			}

		
		return userjson;
	}
	
	
	/*@RequestMapping(value="/getcategories", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getCategoriesList() throws JsonProcessingException, JSONException {
		
		List<Category> listOrderBeans = categoryDao.getCategoryNames();
		
		JSONObject json =new JSONObject();
		
		
		
		
			
			//ObjectMapper objectMapper = new ObjectMapper();
			//String userjson = objectMapper.writeValueAsString(userBean);
			//String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
			
			if(null != listOrderBeans)
			{
				json.put("categorieslist", listOrderBeans);
=======
			//users.add(userBean);
			
			ObjectMapper objectMapper = new ObjectMapper();
			String userjson = objectMapper.writeValueAsString(userBean);
			//String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
			
			if(null != userBean)
			{
				//json.put("categorieslist", listOrderBeans);
				code =userBean.getFirstname()+" "+userBean.getLastname();
				json.put("customerBean", userBean);
>>>>>>> c121eda3b50a1306a9dfe9b02688b9062934b16f
				
			}
			else
				//code="NOT_FOUND";
				
				json.put("categorieslist", "NOT_FOUND");
		
		

		
		return userjson;
	}
	*/
	
	@RequestMapping(value="/getcategories", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getCategoriesList() throws JsonProcessingException, JSONException {
		
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<Category> listOrderBeans = categoryDao.getCategoryNames();
		
		JSONObject json =new JSONObject();
		
		
		
		
			
			//ObjectMapper objectMapper = new ObjectMapper();
			//String userjson = objectMapper.writeValueAsString(userBean);
			//String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
			
			if(null != listOrderBeans)
			{
				json.put("categorieslist", listOrderBeans);
				
			}
			else
				//code="NOT_FOUND";
				
				json.put("categorieslist", "NOT_FOUND");
		
			System.out.println("rest call user status:  "+code);
		

		
		return String.valueOf(json);
	}
	
	

	@RequestMapping(value="/getproducts", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getProductslist() throws JsonProcessingException, JSONException {
		
		List<Product> listOrderBeans =  productDao.getProductDetails();
		
		JSONObject json =new JSONObject();
		
		
		
		
			
			//ObjectMapper objectMapper = new ObjectMapper();
			//String userjson = objectMapper.writeValueAsString(userBean);
			//String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
			
			if(null != listOrderBeans)
			{
				json.put("productDetails", listOrderBeans);
				
			}
			else
				//code="NOT_FOUND";
				
				json.put("productDetails", "NOT_FOUND");
		
		

		
		return String.valueOf(json);
	}
	

}
