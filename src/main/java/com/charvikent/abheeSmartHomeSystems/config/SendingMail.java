package com.charvikent.abheeSmartHomeSystems.config;

import java.io.File;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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
	
	public void sendConfirmationEmail(User user) throws MessagingException {  
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
			
			
			User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String email =emailId;
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			
			URL url = new URL("https://www.google.co.in/search?q=http://www.apache.org%22&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjFtOnIgZbaAhVBRo8KHXnKC0kQ_AUIDSgE&biw=1093&bih=530#imgrc=bzTPKVRxHpnr9M:");
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name",objuserBean.getFirstname());
			velocityContext.put("img",url);
			/*velocityContext.put("mobilenumber",user.getMobilenumber());
			velocityContext.put("email",user.getEmail());
			velocityContext.put("password",user.getPassword());*/
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("RequestEmailTemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
			helper.setSubject("Request submitted Successfully");  
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}
	
	public void sendSalesRequestEmailWithattachment(String emailId,File serverFile ) throws MessagingException {  
		try {
			
			
			User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String email =  emailId;
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name",objuserBean.getUsername());
			
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("RequestEmailTemplate.vm", "UTF-8", velocityContext, stringWriter);
			helper.setText(stringWriter.toString(), true);
			helper.setTo( email);
		    helper.setSubject("Hi");
			helper.addAttachment("file",serverFile);
			javaMailSender.send(message);
				
			
		} catch (MailException e) {
			e.printStackTrace();
			System.out.println(e);
		}  
	}

}
