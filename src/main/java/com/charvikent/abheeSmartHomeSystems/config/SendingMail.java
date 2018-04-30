package com.charvikent.abheeSmartHomeSystems.config;

import java.io.File;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskDao;

import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.UserDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.Customer;

/*import com.charvikent.abheeSmartHomeSystems.dao.UserDao;*/
import com.charvikent.abheeSmartHomeSystems.model.User;

@Component
public class SendingMail {
	
	//private static final String SUBJECT_MAIL_TICKET_ISSUED = "Ticket Issued";
	
	@Autowired  
	private VelocityEngine velocityEngine; 

	@Autowired  
	private JavaMailSender javaMailSender; 
	/*@Autowired  
	private UserDao userDao;*/
	@Autowired
	HttpServletRequest request;
	@Autowired	FilesStuff filePath;
	@Autowired
	 CustomerDao  customerDao;

	@Autowired
	AbheeTaskDao abheeTaskDao;
	@Autowired
	UserDao userDao;
	
	public void sendConfirmationEmail(Customer user) throws MessagingException {  
		try {
			
			

			
			String email = user.getEmail();
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name",user.getFirstname());
			velocityContext.put("mobilenumber",user.getMobilenumber());
			velocityContext.put("email",user.getEmail());
			velocityContext.put("password",user.getPassword());
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("emailtemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
			helper.setSubject("Registration Successfully");  
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	
	//sending with attachment
	
	public void sendMailWithattachment(User user,File serverFile ) throws MessagingException {  
		try {
			
			String email = user.getEmail();
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name",user.getUsername());
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("emailtemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
		    helper.setSubject("Hi");
			//helper.addAttachment("file",serverFile);
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	
	public void SendingSalesRequestByEmail(String emailId) throws MessagingException, MalformedURLException {  
		try {
			
			VelocityContext velocityContext = new VelocityContext();
			
			Object objBean = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(objBean instanceof User)
			{
				
				User objuserBean = (User)objBean;
				velocityContext.put("name",objuserBean.getFirstname());
			}
			else
			
				if(objBean instanceof Customer)
				{
					
					Customer objuserBean = (Customer)objBean;
					velocityContext.put("name",objuserBean.getFirstname());
					
				}
			
			String email =emailId;
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			
			/*URL url = new URL("https://www.google.co.in/search?q=http://www.apache.org%22&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjFtOnIgZbaAhVBRo8KHXnKC0kQ_AUIDSgE&biw=1093&bih=530#imgrc=bzTPKVRxHpnr9M:");*/
			//VelocityContext velocityContext = new VelocityContext();
			//velocityContext.put("name",objuserBean.getFirstname());
			/*velocityContext.put("img",url);*/
			/*velocityContext.put("mobilenumber",user.getMobilenumber());
			velocityContext.put("email",user.getEmail());
			velocityContext.put("password",user.getPassword());*/
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("RequestemailTemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
			helper.setSubject("Request submitted Successfully"); 
			 String path = request.getServletContext().getRealPath("/");
			File  moveFile = new File(path +"reportDocuments","hand.jpg");
			helper.addInline("id101",moveFile );
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	
	public void sendSalesRequestEmailWithattachment(String emailId,MultipartFile[] files ) throws MessagingException {  
		try {
			
			VelocityContext velocityContext = new VelocityContext();
			
						
			String email =  emailId;
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			
			
			//VelocityContext velocityContext = new VelocityContext();
			//velocityContext.put("name",objuserBean.getFirstname());
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("RequestemailTemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
		    helper.setSubject("Request submitted successfully");
		    
		    for(MultipartFile multipartFile : files) {
				String fileName = multipartFile.getOriginalFilename();
				if(!multipartFile.isEmpty())
				{
					FileSystemResource file = new FileSystemResource(filePath.makeDirectory() + File.separator + fileName);
					helper.addAttachment(file.getFilename(), file);
				}
				
			}
			
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	
	public void sendSalesRequestEmailWithMultipleAttachment(String emailId,MultipartFile[] files,String description ) throws MessagingException {  
		try {
			
			
			String email =  emailId;
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name",description);
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("RequestemailTemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
		    helper.setSubject("Quotation");
		  		   
		   for(MultipartFile multipartFile : files) {
				String fileName = multipartFile.getOriginalFilename();
				if(!multipartFile.isEmpty())
				{
					FileSystemResource file = new FileSystemResource(filePath.makeDirectory() + File.separator + fileName);
					helper.addAttachment(file.getFilename(), file);
				}
				
			}
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	
	public  void  resetPassword(Customer custbean2) throws MessagingException
	{
		try {
			
			
			String email =  custbean2.getEmail();
			String password=custbean2.getPassword();
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name",custbean2.getFirstname());
			velocityContext.put("password", password);
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("PasswordResetEmail.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
		    helper.setSubject("Your password sent successfully");
		  		   
		   
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}

	
	public void sendUserConfirmationEmail(User user) throws MessagingException {  
		try {
			
			

			
			String email = user.getEmail();
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name",user.getFirstname());
			velocityContext.put("mobilenumber",user.getMobilenumber());
			velocityContext.put("email",user.getEmail());
			velocityContext.put("password",user.getPassword());
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("emailtemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
			helper.setSubject("Registration Successfully");  
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	
	public  void  sendingMailWithTaskStatus(AbheeTask abheetask) throws MessagingException
	{
		try {
			
			
			String customerid =  abheetask.getCustomerId();
			//String password=custbean2.getPassword();
			Customer customer= customerDao.findCustomerByCustId(customerid);
			String emailid=customer.getEmail();
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name",customer.getFirstname());

			velocityContext.put("taskno", abheetask.getTaskno());
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("ServiceRequestEmailTemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( emailid);
		    helper.setSubject("Task service request sent successfully");
		  		   
		   
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	/*public  void  sendingMailWithTaskStatusUpdate(AbheeTask abheetask) throws MessagingException
	{
		try {
			
			
			String customerid =  abheetask.getCustomerId();
			//String password=custbean2.getPassword();
			Customer customer= customerDao.findCustomerByCustId(customerid);
			List<Map<String, Object>> task=abheeTaskDao.getAbheeTaskById(customerid);
			String emailid=customer.getEmail();
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name",customer.getFirstname());
			velocityContext.put("taskno", abheetask.getTaskno());
			velocityContext.put("service_type",task.get(0).get("service_type") );
			velocityContext.put("severity", task.get(0).get("severity"));
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("ServiceRequestTemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( emailid);
		    helper.setSubject("Task service request sent successfully");

		  		   
		   
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}*/

		public void sendMailTocustomer(AbheeTask editissue) throws MessagingException 
		{
			try {
				
				
				String customerid =  editissue.getCustomerId();
				String assigntechnician=editissue.getAssignto();
				User emp=userDao.getUserById(Integer.parseInt(assigntechnician));
				Customer customer= customerDao.findCustomerByCustId(customerid);
				String emailid=customer.getEmail();
				
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				
				
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("name",customer.getFirstname());
				velocityContext.put("Firstname",emp.getFirstname());
				velocityContext.put("Lastname",emp.getLastname());
				velocityContext.put("taskno", editissue.getTaskno());
				velocityContext.put("description", editissue.getDescription());
				velocityContext.put("mobileno", emp.getMobilenumber());
				StringWriter stringWriter = new StringWriter();
				velocityEngine.mergeTemplate("CustomerEmailTemplate.vm", "UTF-8", velocityContext, stringWriter);
				helper.setText(stringWriter.toString(), true);
				helper.setTo( emailid);
			    helper.setSubject("Technician is assigned to you successfully");
			  		   
			   
				javaMailSender.send(message);
			
		}catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  


	}

		public void sendMailToUser(AbheeTask editissue) throws MessagingException
		{
			try {
				
				
				String customerid =  editissue.getCustomerId();
				String assigntechnician=editissue.getAssignto();
				User emp=userDao.getUserById(Integer.parseInt(assigntechnician));
				Customer customer= customerDao.findCustomerByCustId(customerid);
				String emailid=customer.getEmail();
				
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				
				
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("name",customer.getFirstname());
				velocityContext.put("Firstname",emp.getFirstname());
				velocityContext.put("Lastname",emp.getLastname());
				velocityContext.put("taskno", editissue.getTaskno());
				velocityContext.put("description", editissue.getDescription());
				velocityContext.put("mobileno", customer.getMobilenumber());
				
				StringWriter stringWriter = new StringWriter();
				velocityEngine.mergeTemplate("UserEmailTemplate.vm", "UTF-8", velocityContext, stringWriter);
				helper.setText(stringWriter.toString(), true);
				helper.setTo( emailid);
			    helper.setSubject("Service Request Assigned To You Successfully");
			  		   
			   
				javaMailSender.send(message);
			
		}catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  


			
		}		
}
