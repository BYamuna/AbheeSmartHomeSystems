package com.charvikent.abheeSmartHomeSystems.config;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.UserDao;
/*import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.User;*/
@Component
public class SendSMS {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	 CustomerDao  customerDao;
	@Autowired
	private Environment env;
	/*public  String  mobileNumber =null;
		static String username = "ssjewellers";
		static String password = "SSjewellers@123";
		static String from = "GTCLUB";
		static String from = "AbheeSmartHomes";
		static String from = "AARNA-MATRIMONY";*/
	    static String requestUrl = null;
	    static String toAddress = null;
	    //public String message=null;
	    
    	
	    public String sendSMS(String message, String mobileNumber) throws IOException
		{	
	    	String username =env.getProperty("app.smsusername");
	    	String password =env.getProperty("app.smspassword");
	    	String from =env.getProperty("app.smsfrom");
			System.out.println("hello sms class");
			requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileNumber, "UTF-8")+"&message="+URLEncoder.encode(message, "UTF-8")+"&sms_type=2";
	        URL url = new URL(requestUrl);
	        HttpURLConnection uc = (HttpURLConnection)url.openConnection();
	        String response = uc.getResponseMessage();
	        System.out.println("SMS response:"+response);
	        uc.disconnect();
	        return response;
		}

		/*@SuppressWarnings("unused")
		public String sendsmsToCustomer(AbheeTask editissue) throws IOException
		{
			String customerid=editissue.getCustomerId();
			Customer customer= customerDao.findCustomerByCustId(customerid);
			String assigntechnician=editissue.getAssignto();
			User emp=userDao.getUserById(Integer.parseInt(assigntechnician));
			String mobileno=emp.getMobilenumber();
			String Firstname=emp.getFirstname();
			String Lastname=emp.getLastname();
			String Description=editissue.getDescription();
			String ServiceRequestNo=editissue.getTaskno();
			String message=" The Following Technician is assigned to you related to the service request no ="+URLEncoder.encode(ServiceRequestNo, "UTF-8")+" "+"and His details is given as follows-Mr:"+ Firstname+" "+ Lastname+" "+"and his mobile:"+mobileno+". ";
			//String message="HI";
			
			requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileno, "UTF-8")+"&message="+URLEncoder.encode(message, "UTF-8")+"&sms_type=2";
			//requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?message="+URLEncoder.encode(message, "UTF-8")+"&firstname="+ URLEncoder.encode(Firstname, "UTF-8")+"&lastname="+URLEncoder.encode(Lastname, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileno, "UTF-8")+"&description="+URLEncoder.encode(Description, "UTF-8")+"&sms_type=2";
	        URL url = new URL(requestUrl);
	        HttpURLConnection uc = (HttpURLConnection)url.openConnection();
	        String response = uc.getResponseMessage();
	        System.out.println("SMS response:"+response);
	        uc.disconnect();
	        return response;
			
		}

		@SuppressWarnings("unused")
		public String sendsmsToUser(AbheeTask editissue) throws IOException 
		{
			
			String customerid=editissue.getCustomerId();
			Customer customer= customerDao.findCustomerByCustId(customerid);
			String assigntechnician=editissue.getAssignto();
			User emp=userDao.getUserById(Integer.parseInt(assigntechnician));
			String mobileno=customer.getMobilenumber();
			String Firstname=emp.getFirstname();
			String Lastname=emp.getLastname();
			String Description=editissue.getDescription();
			String ServiceRequestNo=editissue.getTaskno();
			String message=" The Following Service Request assigned to you related to the service request no ="+URLEncoder.encode(ServiceRequestNo, "UTF-8")+" "+"and Request details is given as follows-Mr:"+ Firstname+" "+ Lastname+" "+"and his mobile:"+mobileno+". ";
			//String message="HI";
			
			requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileno, "UTF-8")+"&message="+URLEncoder.encode(message, "UTF-8")+"&sms_type=2";
			//requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?message="+URLEncoder.encode(message, "UTF-8")+"&firstname="+ URLEncoder.encode(Firstname, "UTF-8")+"&lastname="+URLEncoder.encode(Lastname, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileno, "UTF-8")+"&description="+URLEncoder.encode(Description, "UTF-8")+"&sms_type=2";
	        URL url = new URL(requestUrl);
	        HttpURLConnection uc = (HttpURLConnection)url.openConnection();
	        String response = uc.getResponseMessage();
	        System.out.println("SMS response:"+response);
	        uc.disconnect();
	        return response;
		}
*/
}
