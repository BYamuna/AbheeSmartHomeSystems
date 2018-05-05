package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//import org.apache.commons.codec.binary.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.config.SendingMail;
import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.CompanyDao;
import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.OTPDetailsDao;
import com.charvikent.abheeSmartHomeSystems.dao.ProductDao;
import com.charvikent.abheeSmartHomeSystems.dao.ReportIssueDao;
import com.charvikent.abheeSmartHomeSystems.dao.SalesRequestDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.Category;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.OTPDetails;
import com.charvikent.abheeSmartHomeSystems.model.Product;
import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;
import com.charvikent.abheeSmartHomeSystems.model.ServiceRequest;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AbheeCustomerRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbheeCustomerRestController.class);

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
	
	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	SalesRequestDao srequestDao;
	@Autowired
	FilesStuff fileTemplate;

	@Autowired
	SendingMail sendingMail;
	
	@Autowired
	ReportIssueDao reportIssueDao;
	@RequestMapping("/Customer")
	public String showCustomerRegistrationForm(Model model,HttpServletRequest request) throws JsonProcessingException
	{
		LOGGER.debug("Calling Customer at controller");
		return null;
		
	}
	
	
	
	//@RequestMapping(value = "/api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/saveRestCustomer", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public HashMap<String, String>  SaveAbheeCustomer( @RequestBody Customer customer) {
		LOGGER.debug("Calling saveRestCustomer at controller");
		String code =null;
		String regSuccessMsg =customer.getFirstname()+" "+customer.getLastname()+",  Successfully registered with ABhee Smart Homes. \n You can login using  \n UserId:  "+customer.getMobilenumber()+" or "+customer.getEmail()+"\n password: "+customer.getPassword();

		try {


			
			customerDao.saveAbheeCustomer(customer);
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
		
		LOGGER.debug("Calling requestsms at controller");
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
		LOGGER.debug("Calling requestsms2 at controller");
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
		LOGGER.debug("Calling logincrentails at controller");
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
	
	@RequestMapping(value="/getcategories", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getCategoriesList() throws JsonProcessingException, JSONException {
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
	public String  getProductslist() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getproducts at controller");
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
	
	
	@RequestMapping(value="/getcompanies", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getCompaniesBycategoryId(@RequestParam(value="id", required=false) String categoryid) throws JsonProcessingException, JSONException {
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
	public String  getProductsByCompantId(@RequestParam(value="id", required=false) String companyid) throws JsonProcessingException, JSONException {
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
	
	
	@PostMapping(value="/savequationrequest", consumes = "application/json", produces = "application/json")  
	public HashMap<String, String>  saveQuationRequest( @RequestBody SalesRequest salesrequest,@RequestParam("imgfile") MultipartFile[] uploadedFiles) {
		LOGGER.debug("Calling savequationrequest at controller");
		String code =null;
		int filecount =0;

		try {

			int randomNum = ThreadLocalRandom.current().nextInt(10, 20 + 1);
			salesrequest.setSalesrequestnumber(salesrequest.getModelnumber()+randomNum);
			
			
			 for(MultipartFile multipartFile : uploadedFiles) {
					String fileName = multipartFile.getOriginalFilename();
					if(!multipartFile.isEmpty())
					{
						filecount++;
					 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
					}
				}
	   	 
	   	 if(filecount>0)
	   	 {
	   		 salesrequest.setImgfiles(fileTemplate.concurrentFileNames());
	   		 fileTemplate.clearFiles();
	   		 
	   	 }
			
	   	srequestDao.saveRequest(salesrequest);
			code = "requestSubmittedSuccessfully";
		} catch (IOException e) {
			code="NOT_FOUND";
			e.printStackTrace();
		}
		
				HashMap<String,String> hm =new HashMap<String,String>();
		
		hm.put("status", code);
		return hm;
	}
	
	
	
	@PostMapping(value="/restSalesRequest", consumes = "application/json", produces = "application/json")
	public String saveRequestDetails(@ModelAttribute SalesRequest salesrequest,
									/*@RequestParam("imgfile") MultipartFile[] uploadedFiles*/
			                           
									@RequestParam(value = "imgfile") String[] file,HttpServletRequest request,RedirectAttributes redir) throws IllegalStateException, IOException, MessagingException
	{
		LOGGER.debug("Calling restSalesRequest at controller");
		String referalUrl=request.getHeader("referer");
		int filecount =0;
		
		//String str[] = salesrequest.getLocation().split("&");
		
		int randomNum = ThreadLocalRandom.current().nextInt(10, 20 + 1);
		
		salesrequest.setSalesrequestnumber(salesrequest.getModelnumber()+randomNum);
		/*salesrequest.setLat(str[0]);
		salesrequest.setLongitude(str[1]);*/
		salesrequest.setEnable("1");
		/* for(MultipartFile multipartFile : uploadedFiles) {
				String fileName = multipartFile.getOriginalFilename();
				if(!multipartFile.isEmpty())
				{
					filecount++;
				 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
				}
			}*/
		
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
		
	}
	
	

	@PostMapping(value="/restSaveServiceRequest", consumes = "application/json", produces = "application/json")
	public String  saveServiceRequest( @RequestBody ServiceRequest serviceRequest) throws JsonProcessingException, JSONException {
	
		LOGGER.debug("Calling saveServiceRequest at controller");
	
		System.out.println("enter to task controller Submit");
		JSONObject objJson = new JSONObject();
		String message=serviceRequest.getMessage();
		String servicetypeid=serviceRequest.getServicetypeid();
		String catid=serviceRequest.getCatid();
		String modelid=serviceRequest.getModelid();
		String customerId =serviceRequest.getCustomerId();
		String custaddress =serviceRequest.getCustaddress();
		
		AbheeTask task =new AbheeTask();
		task.setAdditionalinfo("0");
		task.setAssignto("1");
		task.setDescription(message);
		task.setKstatus("5");
		task.setPriority("1");
		task.setSeverity("1");
		task.setStatus("1");
		task.setSubject("Task created By Customer");
		task.setServiceType(servicetypeid);
		task.setCategory(catid);
		task.setModelid(modelid);

		task.setCustomerId(customerId);
		
		Customer customer= customerDao.findCustomerByCustId(customerId);
		
		
		
	
		
		Map<String, Object> abheeTask =reportIssueDao.checkServiceRequestExisrOrNot(task);
		if(null ==abheeTask )
		{
		reportIssueDao.saveReportIssue(task);
		//taskHistoryLogsDao.historyLogForcustomerEntry(task);
		//sendingMail.sendingMailWithTaskStatus(task);
		
		customer.setAddress(custaddress);
		try {
			customerDao.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(message+"  "+servicetypeid);
		objJson.put("msg", "true");
		}
		else
		{
			objJson.put("msg", "false");
			System.out.println("Service request alreadyExists");
			
			
		}
		return String.valueOf(objJson);
	}
	

	
}
