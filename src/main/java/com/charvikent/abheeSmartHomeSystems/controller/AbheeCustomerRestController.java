package com.charvikent.abheeSmartHomeSystems.controller;
import java.io.File;
/*import java.io.FileInputStream;
import java.io.FileNotFoundException;*/
import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.Deque;
//import org.apache.commons.codec.binary.Base64;
import java.util.HashMap;
//import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;
/*import javax.mail.MessagingException;*/
import javax.servlet.http.HttpServletRequest;
/*import org.apache.commons.lang.StringUtils;*/
import org.castor.core.util.Base64Decoder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.config.KhaibarGasUtil;
import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.config.SendingMail;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeBranchDao;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskDao;
import com.charvikent.abheeSmartHomeSystems.dao.AbheeTaskStatusDao;
import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.CompanyDao;
import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.DashBoardDao;
import com.charvikent.abheeSmartHomeSystems.dao.OTPDetailsDao;
import com.charvikent.abheeSmartHomeSystems.dao.ProductDao;
import com.charvikent.abheeSmartHomeSystems.dao.ProductGuaranteeDao;
import com.charvikent.abheeSmartHomeSystems.dao.ReportIssueDao;
import com.charvikent.abheeSmartHomeSystems.dao.SalesRequestDao;
import com.charvikent.abheeSmartHomeSystems.dao.UserDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeBranch;
import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.AbheeTaskStatus;
import com.charvikent.abheeSmartHomeSystems.model.Category;
import com.charvikent.abheeSmartHomeSystems.model.Company;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.Designation;
import com.charvikent.abheeSmartHomeSystems.model.OTPDetails;
import com.charvikent.abheeSmartHomeSystems.model.Product;
import com.charvikent.abheeSmartHomeSystems.model.ProductGuarantee;
import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;
import com.charvikent.abheeSmartHomeSystems.model.ServiceRequest;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
public class AbheeCustomerRestController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AbheeCustomerRestController.class);
	@Autowired CustomerDao customerDao;
	@Autowired UserService userService;
	@Autowired SendSMS sendSMS;
	@Autowired OTPDetailsDao oTPDetailsDao;
	@Autowired CategoryDao categoryDao;
	@Autowired ProductDao productDao;
	@Autowired CompanyDao companyDao;
	@Autowired SalesRequestDao srequestDao;
	@Autowired FilesStuff fileTemplate;
	@Autowired SendingMail sendingMail;
	@Autowired ReportIssueDao reportIssueDao;
	@Autowired AbheeTaskDao abheeTaskDao;
	@Autowired DashBoardDao dashBoardDao;
	@Autowired UserDao userDao;
	@Autowired ProductGuaranteeDao productGuaranteeDao;
	@Autowired AbheeBranchDao abheeBranchDao;
	@Autowired AbheeTaskStatusDao abheeTaskStatusDao;
	/*@Autowired private Environment environment;*/
	@RequestMapping("/Customer")
	public String showCustomerRegistrationForm(Model model,HttpServletRequest request) throws JsonProcessingException
	{
		LOGGER.debug("Calling Customer at controller");
		return null;	
	}
	
	@RequestMapping(value="/saveRestCustomer", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public HashMap<String, String>  SaveAbheeCustomer( @RequestBody Customer customer) throws IOException, MessagingException 
	{
		LOGGER.debug("Calling saveRestCustomer at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		String regSuccessMsg =customer.getFirstname()+" "+customer.getLastname()+",  Successfully registered with ABhee Smart Homes. \n You can login using  \n UserId:  "+customer.getMobilenumber()+" or "+customer.getEmail()+"\n password: "+customer.getPassword();
		try 
		{			
			Customer custexist=customerDao.checkCustomerExistOrNot(customer);
			if(custexist==null)
			{
			customer.setRegistedredFromAndroid("1");
			customer.setEnabled("1");
			customerDao.saveAbheeCustomer(customer);
			sendSMS.sendSMS(regSuccessMsg,customer.getMobilenumber());
			sendingMail.sendConfirmationEmail(customer);
			code=customer.getFirstname()+" "+customer.getLastname();
			}
			else
			{
				code="Already Registered";
			}
		}
		catch (IOException e) 
		{
			code="NOT_FOUND";
			e.printStackTrace();
		}
		hm.put("status", code);
		return hm;	
		}
  
	@RequestMapping(value = "/custMobileDuplicate", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody String mobileDuplicate(@RequestBody Customer customer,  HttpServletRequest request) throws JSONException  {
		JSONObject objJSON = new JSONObject();
		try{
			String mobileno=customer.getMobilenumber();
			Customer custBean = customerDao.checkCustomerExistOrNotbyMobile(mobileno);
			if(custBean!= null)
			{
				objJSON.put("Mobileno","already exists");
			}else
			{
				objJSON.put("Mobileno","does not exists");
			}
		}catch(Exception e){
			e.printStackTrace();
			objJSON.put("msg", "fail");
		}
		return String.valueOf(objJSON);
	}
	
	@RequestMapping(value = "/custEmailDuplicate", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody String emailDuplicate(@RequestBody Customer customer,  HttpServletRequest request) throws JSONException  {
		JSONObject objJSON = new JSONObject();
		try{
			String emailId=customer.getEmail();
			Customer custBean = customerDao.checkCustomerExistOrNotByEmail(emailId);
			if(custBean!= null)
			{
				objJSON.put("Emailid","already exists");
			}else
			{
				objJSON.put("Emailid","does not exists");
			}
		}catch(Exception e){
			e.printStackTrace();
			objJSON.put("msg", "fail");
		}
		return String.valueOf(objJSON);
	}
	
	@RequestMapping(value="/requestsms", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public ResponseEntity<String>  getOTP( @RequestBody Customer user) 
	{		
		LOGGER.debug("Calling requestsms at controller");
		String custMobile=user.getMobilenumber();
		Random random = new Random();
		String  otpnumber = String.format("%04d", random.nextInt(10000));
		//String msg="Dear,'"+user.getFirstname()+"' "+user.getLastname()+"', Thanks for registering with Abhee Smart Home Systems. OTP for your registration is:'"+otpnumber+"'";
		HttpStatus code =null;
		try {
			String status = sendSMS.sendSMS(otpnumber,custMobile);
			if(status.equals("OK"))
			{
				code = HttpStatus.OK;
			}else
			{
				code=HttpStatus.NOT_FOUND;
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		OTPDetails oTPDetails =new OTPDetails();
		oTPDetails.setMobileno(custMobile);
		oTPDetails.setOTPnumber(otpnumber);
		oTPDetailsDao.saveOTPdetails(oTPDetails);
		ResponseEntity<String> response = new ResponseEntity<String>(otpnumber,code);
		return response;		
	}
	
	@RequestMapping(value = "/otpVerification", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody String getOtp(@RequestBody Customer customer,  HttpServletRequest request) throws JSONException  
	{
		LOGGER.debug("Calling otpVerification at controller");
		String otp=customer.getOtpstatus();
		customer.setOtpstatus(otp);
		JSONObject json =new JSONObject();
		if(otp.equals("OTP_Verified"))
		{
			customerDao.getProfileInfo(customer);
			json.put("status","Updated");
		}
		return String.valueOf(json);	
	}
	
	@RequestMapping(value="/requestsms2", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public HashMap<String, String> VerifyingAndSendOTP( @RequestBody Customer custBean) 
	{
		LOGGER.debug("Calling requestsms2 at controller");
		String custMobile=custBean.getMobilenumber();
		String custemail=custBean.getEmail();
		Random random = new Random();
		String  otpnumber = String.format("%04d", random.nextInt(10000));
		/*String tmsg =environment.getProperty("app.otpmsg");
		System.out.println(tmsg);
		tmsg= tmsg.replaceAll("_fullname_", custBean.getFirstname()+" "+custBean.getLastname()); 
		tmsg=tmsg.replaceAll("_otp_", otpnumber);*/
		String code =null;
        Customer custbean1 =customerDao.checkCustomerExistOrNotbyMobile(custMobile);
        Customer customer =customerDao.checkCustomerExistOrNotByEmail(custemail);
        String msg="Dear Customer,thanks for registering with Abhee Smart Home Systems. OTP for your registration is:"+otpnumber;
        HashMap<String,String> hm =new HashMap<String,String>();
		if(null==custbean1 && null == customer)
		{
		try 
		{
			String status = sendSMS.sendSMS(msg,custMobile);
			if(status.equals("OK"))
			{
				code = "OK";
				OTPDetails oTPDetails =new OTPDetails();  
				oTPDetails.setMobileno(custMobile);
				oTPDetails.setOTPnumber(otpnumber);
				oTPDetailsDao.saveOTPdetails(oTPDetails);
				hm.put("otpnumber", otpnumber);
				hm.put("statuscode", code);
			}
			else
			{
				code="NOT_FOUND";
				hm.put("statuscode", code);
			}
		}
		catch (IOException e) 
		{
		e.printStackTrace();
		}
		}
		else
		{
			code="Mobilenumber or email id already exists";
			hm.put("statuscode", code);
		}		
		return hm;		
	}
	
	@RequestMapping(value="/requestsms3", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public HashMap<String, String> VerifyingAndSendOTP2( @RequestBody Customer custBean) 
	{
		LOGGER.debug("Calling requestsms3 at controller");
		String custMobile=custBean.getMobilenumber();
		String custemail=custBean.getEmail();
		Random random = new Random();
		String  otpnumber = String.format("%04d", random.nextInt(10000));
		String code =null;
        Customer custbean1 =customerDao.checkCustomerExistOrNotbyMobile(custMobile);
        Customer customer =customerDao.checkCustomerExistOrNotByEmail(custemail);
        String msg="Dear Customer,thanks for registering with Abhee Smart Home Systems. OTP for your registration is:"+otpnumber;
        HashMap<String,String> hm =new HashMap<String,String>();
		if(null==custbean1 || null == customer)
		{
		try 
		{
			String status = sendSMS.sendSMS(msg,custMobile);
			if(status.equals("OK"))
			{
				code = "OK";
				OTPDetails oTPDetails =new OTPDetails();  
				oTPDetails.setMobileno(custMobile);
				oTPDetails.setOTPnumber(otpnumber);
				oTPDetailsDao.saveOTPdetails(oTPDetails);
				hm.put("otpnumber", otpnumber);
				hm.put("statuscode", code);
				//hm.put("status", "Updated");
			}
			else
			{
				code="NOT_FOUND";
				hm.put("statuscode", code);
			}
		}
		catch (IOException e) 
		{
		e.printStackTrace();
		code="Failed";
		hm.put("statuscode", code);
		}
		}	
		else
		{
			code="Not Updated";
			hm.put("statuscode", code);
		}		
		return hm;		
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/logincredentials", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  checkingLogincredentials( @RequestBody Customer customer) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling logincredentails at controller");
		String code =null;
		String username=customer.getUsername();
		HashMap<String,String> hm =new HashMap<String,String>();
		JSONObject json =new JSONObject();
		System.out.println("rest call user called at end");
		List<Customer> userBean=customerDao.checkcustomerExistOrNot(customer);
		System.out.println("rest call user called at staring"+userBean);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			if(!userBean.isEmpty())
			{
					code =customer.getFirstname()+" "+customer.getLastname();
					json.put("userbean", userBean);
					json.put("status", "success");
			}	
			else 
			{
					System.out.println("rest call user called at end");	
					code="NOT_FOUND";
					json.put("status", code);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", "fail");
			return String.valueOf(json);
		}
		return String.valueOf(json);
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
			//users.add(userBean);
			ObjectMapper objectMapper = new ObjectMapper();
			String userjson = objectMapper.writeValueAsString(userBean);
			//String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
			if(null != userBean)
			{
				//json.put("categorieslist", listOrderBeans);
				code =userBean.getFirstname()+" "+userBean.getLastname();
				json.put("customerBean", userBean);
			}
			else
				//code="NOT_FOUND";
				json.put("categorieslist", "NOT_FOUND");
				return userjson;
	}
	*/
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/getcategories", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getCategoriesList() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getCategories at controller");
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
	public String  getProductslist() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getproducts at controller");
		List<Map<String, Object>> listOrderBeans =  productDao. getProductsDescription();
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
	
	@RequestMapping(value="/getcompanies", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getCompaniesBycategoryId(@RequestParam(value="id", required=false) String categoryid) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getcompanies at controller");
		List<Map<String, Object>>  listOrderBeans =  companyDao.getCompaniesByCategoryId(categoryid);
		JSONObject json =new JSONObject();
			if(null != listOrderBeans)
			{
				json.put("companyDetails", listOrderBeans);
			}
			else
				json.put("companyDetails", "NOT_FOUND");
		return String.valueOf(json);
	}
	
	@RequestMapping(value="/getproductsby", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getProductsByCompanyId(@RequestParam(value="id", required=false) String companyid) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getproductsby at controller");
		System.out.println(companyid);
		List<Map<String, Object>>  listOrderBeans =  productDao.getProductsByCompanyId(companyid);
		JSONObject json =new JSONObject();
			if(null != listOrderBeans)
			{
				json.put("productDetails", listOrderBeans);	
			}
			else
				json.put("productDetails", "NOT_FOUND");
		return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value="/savequotationrequest", consumes = "application/json", produces = "application/json")  
	public HashMap<String, String>  saveQuotationRequest( @RequestBody SalesRequest salesrequest,HttpServletRequest request) 
	{
		LOGGER.debug("Calling savequotationrequest at controller");
		//JSONObject objJson = new JSONObject();
		String code =null;
		int randomNum = ThreadLocalRandom.current().nextInt(10, 20 + 1);
		salesrequest.setSalesrequestnumber(salesrequest.getModelnumber()+randomNum);
		String modelnumber=salesrequest.getModelnumber();
		String address=salesrequest.getAddress();
		String customerid=salesrequest.getCustomerid();
		String location=salesrequest.getLocation();
		String mobile=salesrequest.getMobileno();
		String email=salesrequest.getEmail();
		String imgpath=imgdecoder(salesrequest.getImgfiles(),request);
		if(!salesrequest.getImgfiles().isEmpty())
		{
		salesrequest.setImgfiles(imgpath);
		}
		srequestDao.saveRequest(salesrequest);
		code = "requestSubmittedSuccessfully";
		HashMap<String,String> hm =new HashMap<String,String>();
		hm.put("status", code);
		return hm;
	}
	
	/*@PostMapping(value="/restSalesRequest", consumes = "application/json", produces = "application/json")
	public String saveRequestDetails(@ModelAttribute SalesRequest salesrequest,@RequestParam("imgfile") MultipartFile[] uploadedFiles,  @RequestParam(value = "imgfile") String[] file,HttpServletRequest request,RedirectAttributes redir) throws IllegalStateException, IOException, MessagingException
	{
		LOGGER.debug("Calling restSalesRequest at controller");
		String referalUrl=request.getHeader("referer");
		int filecount =0;
		//String str[] = salesrequest.getLocation().split("&");
		int randomNum = ThreadLocalRandom.current().nextInt(10, 20 + 1);
		salesrequest.setSalesrequestnumber(salesrequest.getModelnumber()+randomNum);
		salesrequest.setLat(str[0]);
		salesrequest.setLongitude(str[1]);
		salesrequest.setEnable("1");
		 for(MultipartFile multipartFile : uploadedFiles) {
				String fileName = multipartFile.getOriginalFilename();
				if(!multipartFile.isEmpty())
				{
					filecount++;
				 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
				}
			}
		for(String multipartFile : file) {
		//String imgData = request.getParameter("imgfile");
		if (StringUtils.isNotBlank(multipartFile)) {
		//	Base64Decoder decoder = new Base64Decoder(); 
			File pathFile=fileTemplate.makeDirectory();
			try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
			 //byte[] imgBytes = Base64.getDecoder().decode(multipartFile.split(",")[1]);
			 //imageOutFile.write(imgBytes);
		}
		}
		}
   	 if(filecount>0)
   	 {
   		 salesrequest.setImgfiles(fileTemplate.concurrentFileNames());
   		 fileTemplate.clearFiles(); 
   	 }
	   	Boolean result =srequestDao.checkSalesrequestExistsorNotByEmailAndModelNo(salesrequest);
	   	if(result==false)
	   	{
			srequestDao.saveRequest(salesrequest);
	   		//sendingMail.SendingSalesRequestByEmail(salesrequest.getEmail());
	   		//sendingMail.sendSalesRequestEmailWithattachment(salesrequest.getEmail(), uploadedFiles);
	   	}
	   	else {
	   		redir.addFlashAttribute("msg","Record Already Exists");
	   		System.out.println("record Already Exists");
		return "redirect:abheecategory";
		}
	   	redir.addFlashAttribute("msg","We Received The Request and will send you the Quotation soon. Thanking you.");
	   	System.out.println("&&&&&&&&&&&&&&&&&&&&& Mail Sent");
	   	return "redirect:abheecategory";
		
	}*/
	
	@PostMapping(value="/restSaveServiceRequest", consumes = "application/json", produces = "application/json")
	public String  saveServiceRequest( @RequestBody ServiceRequest serviceRequest,HttpServletRequest request) throws JSONException, IllegalStateException, IOException {
		LOGGER.debug("Calling saveServiceRequest at controller");
		System.out.println("enter to task controller Submit");
		JSONObject objJson = new JSONObject();
		String message=serviceRequest.getMessage();
		String servicetypeid=serviceRequest.getServicetypeid();
		String catid=serviceRequest.getCatid();
		String modelid=serviceRequest.getModelid();
		String customerId =serviceRequest.getCustomerId();
		String custaddress =serviceRequest.getCustaddress();
		String warranty=serviceRequest.getWarranty();
		AbheeTask task =new AbheeTask();
		task.setAdditionalinfo("0");
		task.setAssignto("5");
		task.setDescription(message);
		task.setKstatus("5");
		task.setPriority("3");
		task.setSeverity("3");
		task.setStatus("1");
		task.setSubject("Task created By Customer");
		task.setServiceType(servicetypeid);
		task.setCategory(catid);
		task.setModelid(modelid);
		task.setCommunicationaddress(custaddress);
		Customer customer= customerDao.findCustomerByCustId(customerId);
		//task.setSubject("Task created By "+customer.getFirstname()+" "+customer.getLastname());
		task.setCustomerId(customerId);
		task.setWarranty(warranty);
		task.setAssignby("1");
		Map<String, Object> abheeTask =reportIssueDao.checkServiceRequestExisrOrNot(task);
		if(null ==abheeTask )
		{
			
			if(serviceRequest.getImgname() != null)
			{
			String imgpath=imgdecoder(serviceRequest.getImgname(),request);
			task.setUploadfile(imgpath);
			}	
		reportIssueDao.saveServiceRequest(task);
		//taskHistoryLogsDao.historyLogForcustomerEntry(task);
		//sendingMail.sendingMailWithTaskStatus(task);
		task.setCommunicationaddress(custaddress);
		try 
		{
			customerDao.updateCustomer(customer);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println(message+"  "+servicetypeid);
		objJson.put("status", "Request Submitted Successfully");
		}
		else
		{
			objJson.put("status", "Service Request Already Exists");
			System.out.println("Service request already Exists");	
		}
		return String.valueOf(objJson);
	}

	@RequestMapping(value="/getproductcompanies", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getAllProductCompanies() throws JsonProcessingException, JSONException
	{
		LOGGER.debug("Calling getproductcompanies at controller");
		List<Map<String, Object>>  listOrderBeans = productDao.getProductCompaniesdesc(); 
		JSONObject json =new JSONObject();
			if(null != listOrderBeans)
			{
				json.put("productcompanyDetails", listOrderBeans);	
			}
			else
				json.put("productcompanyDetails", "NOT_FOUND");
		return String.valueOf(json);
	}
	
	@RequestMapping(value="/restEditProfileInfo", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getPersonalInfo( @RequestBody Customer customer) throws JsonProcessingException, JSONException,IOException
	{
		LOGGER.debug("Calling restEditProfileInfo at controller");
		JSONObject json =new JSONObject();
		HashMap<String,String> hm =new HashMap<String,String>();
		hm=VerifyingAndSendOTP2(customer);
				if(hm.get("otpnumber")!=null)
				{
				json.put("status", hm.get("statuscode"));
				json.put("otp", hm.get("otpnumber"));
				//customerDao.getProfileInfo(customer);
				}
				else
				{
					json.put("status", hm.get("statuscode"));
					customerDao.getProfileInfo(customer);
				}
		return String.valueOf(json);
	}
	
	@RequestMapping(value="/restChangePassword", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getPasswordInfo( @RequestBody Customer customer) throws JsonProcessingException, JSONException
	{
		LOGGER.debug("Calling restChangePassword at controller");
		int result = customerDao.getPassword(customer);
		JSONObject json =new JSONObject();
			if(result==1)
			{
				json.put("password", "Updated");
			}
			else
				json.put("password", "Not Updated");
		return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/restForgotPassword", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getForgotPassword(@RequestBody Customer user,HttpServletRequest request) throws IOException, JSONException 
	{
		LOGGER.debug("Calling  restForgotPassword at controller");
		System.out.println("enter to restForgotPassword");
		HttpStatus code =null;
		JSONObject json =new JSONObject();
		String custMobile=user.getMobilenumber();
		Customer custbean2 =customerDao.checkCustomerExistOrNotbyMobile(custMobile);
		String password="Dear"+" "+custbean2.getFirstname()+" "+custbean2.getLastname()+", your password for Abhee smart home systems account"+" '"+custbean2.getMobilenumber()+"' "+"is:"+custbean2.getPassword();
		try {
			String status = sendSMS.sendSMS(password,custMobile);
			if(status.equals("OK"))
			{
				code = HttpStatus.OK;
			}else
			{
				code=HttpStatus.NOT_FOUND;
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		json.put("password", password);
		return String.valueOf(json);
		/*ResponseEntity<String> response = new ResponseEntity<String>(custbean2.getPassword(),code);
		return response;*/
	}
	
	@RequestMapping(value="/getTicketStatus", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getTaskStatusByCustomerId( @RequestBody Customer customer) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getTicketStatus at controller");
		List<Map<String, Object>>  listOrderBeans = abheeTaskDao.getTasksByCustomerId(customer); 
		JSONObject json =new JSONObject();
			if(null != listOrderBeans)
			{
				json.put("ticketstatus", listOrderBeans);
			}
			else
				json.put("ticketstatus", "NOT_FOUND");
		return String.valueOf(json);
	}
	
	@SuppressWarnings("static-access")
	private String  imgdecoder(String imgData, HttpServletRequest request) 
	 {
	    	String filepath = null;
	    	FileOutputStream osf;
	    	KhaibarGasUtil utils=new KhaibarGasUtil();
	    	String id =utils.randNum(4);
			Base64Decoder decoder = new Base64Decoder(); 
			//byte[] imgBytes = decoder.decode(imgData.split(",")[1]);
			byte[] imgBytes = decoder.decode(imgData);
			/*name=name.substring(n + 1);
			name=name+".png";*/
			long millis = System.currentTimeMillis() % 1000;
			filepath= id+millis+".png";
            //String rootPath = request.getSession().getServletContext().getRealPath("/");
			String rootPath = System.getProperty("catalina.base");
			//File dir = new File(rootPath + File.separator + "reportDocuments");
			File dir = new File(rootPath + File.separator + "webapps"+ File.separator + "abheeimg");
			 if (!dir.exists()) 
			 {
			    dir.mkdirs();
			 }
			        
			 System.out.println(dir);
			        
			  try 
			  {
			   osf = new FileOutputStream(new File(dir.getAbsolutePath() + File.separator + filepath));
			   osf.write(imgBytes);
			   osf.flush();
			  }catch (IOException e) 
			  {
			    System.out.println("error : " + e);
			  }
	           //filepath= "abheeimg/"+filepath;
	    	return  filepath;	
		} 
	@SuppressWarnings("unused")
	@PostMapping(value="/saveEnquiryDetails", consumes = "application/json", produces = "application/json")  
	public HashMap<String, String> saveEnquiryDetails( @RequestBody SalesRequest salesrequest,HttpServletRequest request) 
	{
		LOGGER.debug("Calling saveEnquiryDetails at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		int randomNum = ThreadLocalRandom.current().nextInt(10, 20 + 1);
		salesrequest.setSalesrequestnumber(salesrequest.getModelnumber()+randomNum);
		String modelnumber=salesrequest.getModelnumber();
		String mobile=salesrequest.getMobileno();
		String email=salesrequest.getEmail();
		String custname=salesrequest.getCustomername();
		String enquirydetails=salesrequest.getReqdesc();
		String enquiryimage=imgdecoder(salesrequest.getImgfiles(),request);
		if(!salesrequest.getImgfiles().isEmpty())
		{
		salesrequest.setImgfiles(enquiryimage);
		}
		if(enquirydetails!="") {
			//salesrequest.setStatus(1);
			srequestDao.saveRequest(salesrequest);
			code = "Enquiry details sent successfully";
			hm.put("status", code);
			
		}
		else {
		
			code = "Not Found";
			hm.put("status", code);
			
		}
		//srequestDao.saveRequest(salesrequest);
		
		return hm;
	}
	
	@PostMapping(value="/getrestseveritycounts", consumes = "application/json", produces = "application/json")  
	public  String getRestSeverityCounts( @RequestBody User user,HttpServletRequest request) throws JSONException 
	{
		JSONObject objJSON = new JSONObject();
		User Validuser =userService.getUserById(user.getId());
		List<Map<String, Object>> severityCounts = dashBoardDao.getTasksCountBySeverityforRest(Validuser);
		objJSON.put("status", severityCounts);
		return String.valueOf(objJSON);
	}
	
	@PostMapping(value="/userloggging", consumes = "application/json", produces = "application/json")  
	public  String checkUserLogin( @RequestBody User user,HttpServletRequest request) throws JSONException 
	{
		JSONObject objJSON = new JSONObject();
		List<Map<String, Object>> validUser = userService.checkUserExistence(user);
		
		if(!validUser.isEmpty())
		{
		objJSON.put("status", validUser);
		}
		else
		{
		objJSON.put("status", "user not exists");
		}
		return String.valueOf(objJSON);
	}
	
	@PostMapping(value="/getreststatuscounts", consumes = "application/json", produces = "application/json")  
	public  String getRestStatusCounts( @RequestBody User user,HttpServletRequest request) throws JSONException 
	{
		JSONObject objJSON = new JSONObject();
		User Validuser =userService.getUserById(user.getId());
		List<Map<String, Object>> statusCounts = dashBoardDao.getTasksCountByStatusforRest(Validuser);
		objJSON.put("status", statusCounts);
		return String.valueOf(objJSON);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/getPriorities", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getPrioritiesList() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getPriorities at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<Map<String, Object>> listOrderBeans = abheeTaskDao.getPriority();
		JSONObject json =new JSONObject();
		//ObjectMapper objectMapper = new ObjectMapper();
		//String userjson = objectMapper.writeValueAsString(userBean);
		//String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
		if(null != listOrderBeans)
		{
			json.put("prioritieslist", listOrderBeans);
		}
		else
			//code="NOT_FOUND";
			json.put("prioritieslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/getSeverities", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getSeveritiesList() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getSeverities at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<Map<String, Object>> listOrderBeans = abheeTaskDao.getSeverity();
		JSONObject json =new JSONObject();
		//ObjectMapper objectMapper = new ObjectMapper();
		//String userjson = objectMapper.writeValueAsString(userBean);
		//String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
		if(null != listOrderBeans)
		{
			json.put("severitieslist", listOrderBeans);
		}
		else
			//code="NOT_FOUND";
			json.put("severitieslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/getUsers", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getUsersList() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getUsers at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<User>listOrderBeans =  userDao.getAllUsers();
		JSONObject json =new JSONObject();
		//ObjectMapper objectMapper = new ObjectMapper();
		//String userjson = objectMapper.writeValueAsString(userBean);
		//String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
		if(null != listOrderBeans)
		{
			json.put("userslist", listOrderBeans);
		}
		else
			//code="NOT_FOUND";
			json.put("userslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}

	@PostMapping(value="/saveProduct", consumes = "application/json", produces = "application/json")
		public String saveProduct( @RequestBody Category cate,HttpServletRequest request) throws JSONException 
		{
		LOGGER.debug("Calling saveProduct at controller");
		JSONObject json =new JSONObject();
		String code ="";
		int id = 0;
		try
		{
			Category orgBean= categoryDao.getCategoryNameById(cate);
			int dummyId =0;
			if(orgBean != null){
				dummyId = orgBean.getId();
			}
			if(cate.getId()==null)
			{
				if(dummyId ==0)
				{
					String productimages=imgdecoder(cate.getCategoryimg(),request);
					if(!cate.getCategoryimg().isEmpty())
					{
						cate.setCategoryimg(productimages);
					}  
					cate.setStatus("1");
					categoryDao.saveCategory(cate);
					code ="success";
				} else
				{
					code ="exists";	
				}
			}
			else
			{
				id=cate.getId();
				if(id == dummyId || orgBean == null)
				{
					String productimages=imgdecoder(cate.getCategoryimg(),request);
					if(!cate.getCategoryimg().isEmpty())
					{
						cate.setCategoryimg(productimages);
					}
					categoryDao.UpdateCategory(cate);
					code ="updated";	
				} else
				{
					code ="exists";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		
		return String.valueOf(json);
		}
	
	@RequestMapping(value="/productslist",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String  getProductsList() 
	{
		LOGGER.debug("Calling productslist at controller");
		JSONObject json =new JSONObject();
		List<Category> listOrderBeans = null;
		try 
		{
			listOrderBeans = categoryDao.getCategoryNames();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("productslist", listOrderBeans);
			} else 
			{
				json.put("productslist", "NOT_FOUND");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}

	@RequestMapping(value="/getquotationlist", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getQuotationList() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getquotationlist at controller");
		JSONObject json =new JSONObject();
		List<Map<String, Object>> listOrderBeans = null; 
		try 
		{
			listOrderBeans = srequestDao.getSalesRequestList1();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("quotationslist", listOrderBeans);
			} else 
			{
				json.put("quotationslist", "NOT_FOUND");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}
	@RequestMapping(value="/getquotationlistByRequestNo", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getquotationlistByRequestNo(@RequestBody SalesRequest request) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getquotationlist at controller");
		JSONObject json =new JSONObject();
		List<Map<String, Object>> listOrderBeans = null;
		List<Map<String, Object>> listOrderBeans1=null,listOrderBeans2=null;
		
		try 
		{
			listOrderBeans = srequestDao.getSalesRequestListByRequestNo(request);
			listOrderBeans1 = srequestDao.getAdminResponseListByRequestNo(request);
			listOrderBeans2=srequestDao.getAdminResponseListByRequestNoWhenStatusZero(request);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
			
			listOrderBeans1.get(0).get("status");
			System.out.println("Status Code:"+listOrderBeans1.get(0).get("status"));
			
			if(!listOrderBeans1.get(0).get("status").equals(0)) {
				json.put("quotationslist", listOrderBeans);
				json.put("AdminResponseList",listOrderBeans1);
			}
			
			else {
				listOrderBeans1.get(0).get("status");
				System.out.println("Status Code:"+listOrderBeans1.get(0).get("status"));
				
				json.put("quotationslist", listOrderBeans);
				
				json.put("AdminResponseList",listOrderBeans2);
			}
			}
			else 
			{
				json.put("quotationslist", "NOT_FOUND");
			}
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}
	
	@PostMapping(value="/saveCompany", consumes = "application/json", produces = "application/json")
	public String saveCompany( @RequestBody Company com,HttpServletRequest request,BindingResult bindingresults) throws JSONException 
	{
	LOGGER.debug("Calling saveCompany at controller");
	JSONObject json =new JSONObject();
	String code ="";
	if (bindingresults.hasErrors()) {
		System.out.println("has some errors");
		return "redirect:/";
	}
	int id = 0;
	try
	{
		Company orgBean= companyDao.getCompanyNameById(com);
		int dummyId =0;
		if(orgBean != null){
			dummyId = orgBean.getId();
		}
		if(com.getId()==null)
		{
			if(dummyId ==0)
			{
				String companyimages=imgdecoder(com.getCompanyimg(),request);
				if(!com.getCompanyimg().isEmpty())
				{
					com.setCompanyimg(companyimages);
				}
				com.setStatus("1");
				companyDao.saveCompany(com);
				code ="success";
			} else
			{
				code="exists";	
			}
		}
		else
		{
			id=com.getId();
			if(id == dummyId || orgBean == null)
			{
				String companyimages=imgdecoder(com.getCompanyimg(),request);
				if(!com.getCompanyimg().isEmpty())
				{
					com.setCompanyimg(companyimages);
				}
				companyDao.UpdateCompany(com);
				code="updated";
			} else
			{
				code="exists";
			}	
		}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		
		return String.valueOf(json);
		}
	
	@RequestMapping(value="/companieslist", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getCompaniesList() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling companieslist at controller");
		List<Company> listOrderBeans = null;
		JSONObject json =new JSONObject();
		try 
		{
		listOrderBeans = companyDao.getCompanyNames();
			if (listOrderBeans != null && listOrderBeans.size() > 0) 
			{
				json.put("companieslist", listOrderBeans);
			} else 
			{
				json.put("companieslist", "NOT_FOUND");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
	return String.valueOf(json);
	}	
	
@PostMapping(value="/saveProductModel", consumes = "application/json", produces = "application/json")	
public String savePrpductModel( @RequestBody Product pro,HttpServletRequest request) throws JSONException 
	{
	LOGGER.debug("Calling saveProductModel at controller");
	JSONObject json =new JSONObject();
	String code ="";
	
	String sfn ="";
	
	String vlinks[] =pro.getProductmodelvideoslinks().split(",");
	
	
	 for(String files: vlinks)
        {
        	sfn=sfn+files+"*"; 
        }
	 
        String sfn2=sfn.substring(0,sfn.length()-1);
	
        
        pro.setProductmodelvideoslinks(sfn2);
	
			int id = 0;
			try
			{
				 Product orgBean= productDao.getProductNameById(pro);
				int dummyId =0;
				if(orgBean != null)
				{
					dummyId = orgBean.getId();
				}
				if(pro.getId()==null)
				{
					if(dummyId ==0)
					{
						String productmodelpics=imgdecoder(pro.getProductmodelpics(),request);
						if(!pro.getProductmodelpics().isEmpty())
						{
							pro.setProductmodelpics(productmodelpics);
						}
						pro.setStatus("1");
						productDao.saveProduct(pro);
						code="success";
					} else
					{
						code="exists";	
					}	
				}
				else
				{
					id=pro.getId();
					if(id == dummyId || orgBean == null)
					{
						String productmodelpics=imgdecoder(pro.getProductmodelpics(),request);
						if(!pro.getProductmodelpics().isEmpty())
						{
							pro.setProductmodelpics(productmodelpics);
						}
						productDao.UpdateProduct(pro);
						code="updated";
					} 
					else
					{
						code="exists";
					}	
				}
			}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		return String.valueOf(json);
	}

@RequestMapping(value="/productmodelslist",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
public String  getProductsModelsList() 
{
	LOGGER.debug("Calling productmodelslist at controller");
	JSONObject json =new JSONObject();
	List<Product> listOrderBeans = null;
	try 
	{
		listOrderBeans = productDao. getProductDetails();
		if (listOrderBeans != null && listOrderBeans.size() > 0) 
		{
			json.put("productmodelslist", listOrderBeans);
		} else 
		{
			json.put("productmodelslist", "NOT_FOUND");
		}
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		System.out.println(e);
	}
	return String.valueOf(json);
}	

	@PostMapping(value="/saveCustomer", consumes = "application/json", produces = "application/json")
	public String saveCustomer( @RequestBody Customer user,HttpServletRequest request) throws JSONException 
	{
	LOGGER.debug("Calling saveCustomer at controller");
	JSONObject json =new JSONObject();
	String code ="";		
	int id = 0;
		try
		{
			//Customer userBean= customerDao.getCustomerByObject(user);
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
					/*user.setRegistedredFromAndroid("1");*/
					customerDao.saveAbheeCustomer(user);
					sendingMail.sendConfirmationEmail(user);
					code="success";
				} 
				else
				{
					code="exists";
				}
			}
			else
			{
				id=user.getId();
				if(id == dummyId || userBean == null)
				{
					customerDao.updateCustomer(user);
					sendingMail.sendConfirmationEmail(user);
					code="updated";

				} else
				{
					code="exists";
				}	
			}	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		return String.valueOf(json);
	}
	
	@RequestMapping(value="/customerslist",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String  getCustomersList() 
	{
		LOGGER.debug("Calling customerslist at controller");
		JSONObject json =new JSONObject();
		List<Customer> listOrderBeans = null;
		try 
		{
			listOrderBeans = customerDao.getAbheeCustomerNames();
			if (listOrderBeans != null && listOrderBeans.size() > 0) 
			{
				json.put("customerslist", listOrderBeans);
			} else 
			{
				json.put("customerslist", "NOT_FOUND");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}	
	
	@PostMapping(value="/saveUser", consumes = "application/json", produces = "application/json")
	public String saveUser( @RequestBody User user,HttpServletRequest request) throws JSONException 
	{
	LOGGER.debug("Calling saveUser at controller");
	JSONObject json =new JSONObject();
	String code ="";
			
	int id = 0;
		try
		{
			User userBean=null;
			
			  userBean= userService.getUserByObject(user);
			
			int dummyId =0;

			if(userBean != null){
				dummyId = userBean.getId();
			}
			if(user.getId()==null)
			{
				if(dummyId ==0)
				{
					user.setEnabled("1");
					userService.saveUser(user);
					sendingMail.sendUserConfirmationEmail(user);
					code="success";
				} else
				{
					code="exists";
				}
			}
			else
			{
				id=user.getId();
				if(id == dummyId || userBean == null)
				{
					userService.updateUser(user);
					code="updated";
				} else
				{
					code="exists";
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		return String.valueOf(json);
	}
	
	@RequestMapping(value="/userslist",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String  getEmployeesList() 
	{
		LOGGER.debug("Calling userslist at controller");
		JSONObject json =new JSONObject();
		List<User> listOrderBeans = null;
		try 
		{
			listOrderBeans = userDao.getAllUsers();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("userslist", listOrderBeans);
			} else 
			{
				json.put("userslist", "NOT_FOUND");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}	
	
	@PostMapping(value="/saveServiceRequest", consumes = "application/json", produces = "application/json")
	public String saveServiceRequest( @RequestBody AbheeTask task,HttpServletRequest request) throws JSONException 
	{
	LOGGER.debug("Calling saveServiceRequest at controller");
	JSONObject json =new JSONObject();
	String code ="";
			
	int id = 0;
		try
		{
			AbheeTask orgBean=null;
			if(task.getId()!=null)
			{
			  orgBean= reportIssueDao.getReportIssueById(task.getId());
			}
			int dummyId =0;
			if(orgBean != null){
				dummyId = orgBean.getId();
			}
			if(task.getId()==null)
			{
				if(dummyId ==0)
				{
					try 
					{	
						if(!task.getUploadfile().isEmpty())
						{
							String images=imgdecoder(task.getUploadfile(),request);
							task.setUploadfile(images);
						}
					} 
					catch (IllegalStateException e) 
					{
						e.printStackTrace();
					}
					task.setStatus("1");
					task.setAdditionalinfo("0");
					reportIssueDao.saveServiceRequest(task);
					code="success";
				} else
				{
					code="exists";
				}
			}
			else
			{
				id=task.getId();
				if(id == dummyId || orgBean == null)
				{	
				try 
				{	
					if(!task.getUploadfile().isEmpty())
					{
						String images=imgdecoder(task.getUploadfile(),request);
						task.setUploadfile(images);
					}		
				} 
				catch (IllegalStateException e) {
						e.printStackTrace();
					}
					reportIssueDao.updateIssue(task);
					code="updated";
				} 
				else
				{
					code="exists";
				}
			}
		}			
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		return String.valueOf(json);
	}
	
	@RequestMapping(value="/taskslist",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String  getServiceRequestList()
	{
		LOGGER.debug("Calling taskslist at controller");
		JSONObject json =new JSONObject();
		List<Map<String, Object>> listOrderBeans = null;
		try 
		{
			listOrderBeans = reportIssueDao.getAllTasksList();
			System.out.println(listOrderBeans);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("taskslist", listOrderBeans);
			} else 
			{
				json.put("taskslist", "NOT_FOUND");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}
	
	@PostMapping(value="/saveProductWarranty", consumes = "application/json", produces = "application/json")
	public String saveWarranty( @RequestBody ProductGuarantee productGuarantee,HttpServletRequest request,BindingResult bindingresults) throws JSONException 
	{
	LOGGER.debug("Calling saveProductWarranty at controller");
	JSONObject json =new JSONObject();
	String code ="";
	if (bindingresults.hasErrors()) 
	{
		System.out.println("has some errors");
		return "redirect:/";
	}		
	String id = null;
		try
		{
			ProductGuarantee orgBean=null;
			if(productGuarantee.getOrderId()!="" && productGuarantee.getOrderId() != null)
			{
			  orgBean=  (ProductGuarantee) productGuaranteeDao.getProductWarrantyDetailsByObject(productGuarantee);
			}
			String  dummyId =null;
			if(orgBean != null){
				dummyId = orgBean.getOrderId();
			}
			if(productGuarantee.getOrderId()== "" || productGuarantee.getOrderId() == null)
			{
				if(dummyId ==null)
				{	
					productGuarantee.setStatus("1");
					productGuaranteeDao.saveWarranty(productGuarantee);
					code="success";
				} else
				{
					code="exists";	
				}
			}
			else
			{
				id=productGuarantee.getOrderId();
				if(id == dummyId || orgBean == null)
				{
					productGuaranteeDao.updateWarranty(productGuarantee);
					code="updated";
				} else
				{
					code="exists";
				}
			}
		}			
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		return String.valueOf(json);
	}
	@RequestMapping(value="/warrantylist",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String  getProductWarrantyList()
	{
		LOGGER.debug("Calling taskslist at controller");
		JSONObject json =new JSONObject();
		List<Map<String, Object>> listOrderBeans  = null;
		try 
		{
			listOrderBeans = productGuaranteeDao.getProductWarrantyList();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("warrantylist", listOrderBeans);
			} else 
			{
				json.put("warrantylist", "NOT_FOUND");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}
	@RequestMapping(value="/adminEditProfile", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getProfileInfo( @RequestBody User user) throws JsonProcessingException, JSONException
	{
		LOGGER.debug("Calling adminEditProfile at controller");
		int result = userDao.getProfileInfo(user);
		JSONObject json =new JSONObject();
			if(result==1)
			{
				json.put("profileinfo", "Updated");
			}
			else
				json.put("profileinfo", "Not Updated");
		return String.valueOf(json);
	}
	
	@RequestMapping(value="/adminChangePassword", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getPassword( @RequestBody User user) throws JsonProcessingException, JSONException
	{
		LOGGER.debug("Calling adminChangePassword at controller");
		   int result = userDao.getPassword(user);
		   JSONObject json =new JSONObject();
			if(result==1)
			{
				json.put("password", "Updated");
			}
			else
				json.put("password", "Not Updated");
		return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/getDesignation", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getDesignation() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getDesignation at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<Designation> listOrderBeans = userDao.getRoles();
		JSONObject json =new JSONObject();
		if(null != listOrderBeans)
		{
			json.put("designationlist", listOrderBeans);
		}
		else
			json.put("designationlist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/getBranches", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getAbheeBranches() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getBranches at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<AbheeBranch> listOrderBeans = abheeBranchDao.getAbheeBranchNames();
		JSONObject json =new JSONObject();
		if(null != listOrderBeans)
		{
			json.put("branchlist", listOrderBeans);
		}
		else
			json.put("branchlist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/getReportToUsers", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getReportToUsers() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getReportToUsers at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<User> listOrderBeans = userDao.getUserNames();
		JSONObject json =new JSONObject();
		if(null != listOrderBeans)
		{
			json.put("reporttouserslist", listOrderBeans);
		}
		else
			json.put("reporttouserslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/inActiveProducts", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getInActiveProducts() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling inActiveProducts at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<Category> listOrderBeans = categoryDao.getAllInActiveList();
		JSONObject json =new JSONObject();
		if(null != listOrderBeans)
			json.put("inactiveproductslist", listOrderBeans);
		else
			json.put("inactiveproductslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/inActiveCompanies", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getInActiveCompanies() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling inActiveCompanies at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<Company> listOrderBeans = companyDao.getAllInActiveList();
		JSONObject json =new JSONObject();
		if(null != listOrderBeans)
			json.put("inactivecompaieslist", listOrderBeans);
		else
			json.put("inactivecompaieslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}
	
	@RequestMapping(value = "/inActiveProductModels",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveProductModels(Product  objdept,HttpServletRequest request) throws JsonProcessingException,JSONException 
	{
		LOGGER.debug("Calling inActiveProductModels at controller");
		String code =null;
		List<Product> listOrderBeans  = productDao.getAllInActiveList();
		JSONObject json = new JSONObject();
		if(null != listOrderBeans)
			json.put("inactiveproductmodelslist", listOrderBeans);
		else
			json.put("inactiveproductmodelslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
		return String.valueOf(json);
	}
	
	@RequestMapping(value = "/inActiveCustomers",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveCustomers(Customer  objdept,HttpServletRequest request) throws JsonProcessingException,JSONException 
	{
		LOGGER.debug("Calling inActiveCustomers at controller");
		String code =null;
		List<Customer> listOrderBeans  = customerDao.getCustomerInActiveList();
		JSONObject jsonObj = new JSONObject();
			if (null != listOrderBeans) 
				jsonObj.put("inactivecustomerslist", listOrderBeans); 
			else 
				jsonObj.put("inactivecustomerslist", "NOT_FOUND");
				System.out.println("rest call user status:  "+code);
		return String.valueOf(jsonObj);
	}
	
	@RequestMapping(value = "/inActiveUsers",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveTasks(User  objdept,HttpServletRequest request) throws JsonProcessingException,JSONException
	{
		LOGGER.debug("Calling inActiveUsers at controller");
		String code =null;
		List<User> listOrderBeans  = userService.getInActiveList();
		JSONObject jsonObj = new JSONObject();
			if (null != listOrderBeans) 
				jsonObj.put("inactiveuserslist", listOrderBeans);
			else 
				jsonObj.put("inactiveuserslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);	
		return String.valueOf(jsonObj);
	}

	@RequestMapping(value = "/inActiveTasks",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveTasks(AbheeTask  objorg,HttpServletRequest request) throws JsonProcessingException,JSONException
	{
		LOGGER.debug("Calling inActiveTasks at controller");
		String code =null;
		List<Map<String, Object>> listOrderBeans  = abheeTaskDao.getInActiveTasksList();
		JSONObject jsonObj = new JSONObject();
			if (null != listOrderBeans) 
				jsonObj.put("inactivetaskslist", listOrderBeans);
			else 
				jsonObj.put("inactivetaskslist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
		return String.valueOf(jsonObj);
	}

	@RequestMapping(value = "/inActiveProductWarranty",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveWarrantyList(ProductGuarantee  productGuarantee ,HttpServletRequest request) throws JsonProcessingException,JSONException
	{
		LOGGER.debug("Calling inActiveProductWarranty at controller");
		String code =null;
		List<Map<String, Object>> listOrderBeans  = productGuaranteeDao.getAllInActiveList();
		JSONObject jsonObj = new JSONObject();
			if (null != listOrderBeans) 
				jsonObj.put("inactiveproductwarrantylist", listOrderBeans);
		 else 
				jsonObj.put("inactiveproductwarrantylist", "NOT_FOUND");	
			System.out.println("rest call user status:  "+code);
		return String.valueOf(jsonObj);
	}
	
	@PostMapping(value="/deleteProduct", consumes = "application/json", produces = "application/json")
    public String deleteProduct( @RequestBody Category cate,HttpServletRequest request) throws JSONException       
	{
		LOGGER.debug("Calling deleteProduct at controller");
		//int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		JSONObject jsonObj = new JSONObject();
		if(!cate.getStatus().equals("0"))
		{
			jsonObj .put("status", "Not Deactivated");
		}
		else
			jsonObj .put("status", "Deactivated");	
            return String.valueOf(jsonObj);
	}
	@PostMapping(value="/deleteCompany", consumes = "application/json", produces = "application/json")
    public String deleteCompany( @RequestBody Company com,HttpServletRequest request) throws JSONException       
	{
		LOGGER.debug("Calling deleteCompany at controller");
		//int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		companyDao.deactiveCompany(com.getStatus(),com.getId());
		JSONObject jsonObj = new JSONObject();
		if(!com.getStatus().equals("0"))
		{
			jsonObj .put("status", "Not Deactivated");
		}
		else
			jsonObj .put("status", "Deactivated");	
            return String.valueOf(jsonObj);
	}
	
	@PostMapping(value="/deleteProductModel", consumes = "application/json", produces = "application/json")
    public String deleteProductModel( @RequestBody Product pro,HttpServletRequest request) throws JSONException       
	{
		LOGGER.debug("Calling deleteProductModel at controller");
		//int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		productDao.deactiveProductModel(pro.getStatus(), pro.getId());
		JSONObject jsonObj = new JSONObject();
		if(!pro.getStatus().equals("0"))
		{
			jsonObj .put("status", "Not Deactivated");
		}
		else
			jsonObj .put("status", "Deactivated");	
            return String.valueOf(jsonObj);
	}  
	
	@PostMapping(value="/deleteCustomer", consumes = "application/json", produces = "application/json")
    public String deleteCustomer( @RequestBody Customer cust,HttpServletRequest request) throws JSONException       
	{
		LOGGER.debug("Calling deleteCustomer at controller");
		//int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		customerDao.deactiveCustomer(cust.getStatus(), cust.getId());
		JSONObject jsonObj = new JSONObject();
		if(!cust.getStatus().equals("0"))
		{
			jsonObj .put("status", "Not Deactivated");
		}
		else
			jsonObj .put("status", "Deactivated");	
            return String.valueOf(jsonObj);
	}
	
	@PostMapping(value="/deleteUser", consumes = "application/json", produces = "application/json")
    public String deleteUser( @RequestBody User user,HttpServletRequest request) throws JSONException       
	{
		LOGGER.debug("Calling deleteUser at controller");
		//int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		userDao.deactiveUser(user.getStatus(), user.getId());
		JSONObject jsonObj = new JSONObject();
		if(!user.getStatus().equals("0"))
		{
			jsonObj .put("status", "Not Deactivated");
		}
		else
			jsonObj .put("status", "Deactivated");	
            return String.valueOf(jsonObj);
	}
	@PostMapping(value="/deleteServiceRequest", consumes = "application/json", produces = "application/json")
    public String deleteTask( @RequestBody AbheeTask task,HttpServletRequest request) throws JSONException       
	{
		LOGGER.debug("Calling deleteServiceRequest at controller");
		//int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		abheeTaskDao.deactiveTask(task.getStatus(), task.getId());
		JSONObject jsonObj = new JSONObject();
		if(!task.getStatus().equals("0"))
		{
			jsonObj .put("status", "Not Deactivated");
		}
		else
			jsonObj .put("status", "Deactivated");	
            return String.valueOf(jsonObj);
	}
	
	@PostMapping(value="/deleteProductWarranty", consumes = "application/json", produces = "application/json")
    public String deleteProductWarranty( @RequestBody ProductGuarantee pg,HttpServletRequest request) throws JSONException       
	{
		LOGGER.debug("Calling deleteProductWarranty at controller");
		productGuaranteeDao.deactiveProductWarranty(pg.getStatus(), pg.getOrderId());
		JSONObject jsonObj = new JSONObject();
		if(!pg.getStatus().equals("0"))
		{
			jsonObj .put("status", "Not Deactivated");
		}
		else
			jsonObj .put("status", "Deactivated");	
            return String.valueOf(jsonObj);
	}
	
	@RequestMapping(value = "/getStatusList",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getStatusList(@RequestBody AbheeTaskStatus abheeTaskStatus ,HttpServletRequest request) throws JsonProcessingException,JSONException
	{
		LOGGER.debug("Calling getStatusList at controller");
		String code =null;
		List<AbheeTaskStatus> listOrderBeans  = abheeTaskStatusDao.getTaskStatusList();
		JSONObject jsonObj = new JSONObject();
			if (null != listOrderBeans) 
				jsonObj.put("statuslist", listOrderBeans);
		 else 
				jsonObj.put("statuslist", "NOT_FOUND");	
			System.out.println("rest call user status:  "+code);
		return String.valueOf(jsonObj);
	}	
	
	/*@RequestMapping(value = "/editEmailAndMobileno",method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getMobileno(@RequestBody Customer customer ,HttpServletRequest request) 
	{
		LOGGER.debug("Calling editEmailAndMobileno at controller");
		JSONObject json =new JSONObject();
		HashMap<String,String> hm =new HashMap<String,String>();
		hm=VerifyingAndSendOTP(customer);
				if(hm.get("otpnumber")!=null)
				{
				json.put("status", hm.get("statuscode"));
				json.put("otp", hm.get("otpnumber"));
				}
				else
				{
					json.put("status", hm.get("statuscode"));
				}
			return String.valueOf(json);
	}*/
	
	

	@RequestMapping(value="/getEmailandMobileById", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String getemailandmobileById( @RequestBody Customer customer) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getEmailandMobileById at controller");
		List<Customer>  listOrderBeans = customerDao.getEmailandMobileByCustomerId(customer);
		JSONObject json =new JSONObject();
			if(null != listOrderBeans)
			{
				json.put("em", listOrderBeans);
			}
			else
				json.put("em", "NOT_FOUND");
		return String.valueOf(json);
	}
}