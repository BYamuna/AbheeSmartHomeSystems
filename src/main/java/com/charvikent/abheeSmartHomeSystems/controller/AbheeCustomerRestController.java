package com.charvikent.abheeSmartHomeSystems.controller;

import java.io.File;
/*import java.io.FileInputStream;
import java.io.FileNotFoundException;*/
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
import com.charvikent.abheeSmartHomeSystems.dao.RequestTimeDao;
import com.charvikent.abheeSmartHomeSystems.dao.SalesRequestDao;
import com.charvikent.abheeSmartHomeSystems.dao.TaskHistoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.UserDao;
import com.charvikent.abheeSmartHomeSystems.model.AbheeBranch;
import com.charvikent.abheeSmartHomeSystems.model.AbheeRequestTime;
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
import com.charvikent.abheeSmartHomeSystems.model.TaskHistoryLogs;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AbheeCustomerRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbheeCustomerRestController.class);
	@Autowired
	CustomerDao customerDao;
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
	@Autowired
	AbheeTaskDao abheeTaskDao;
	@Autowired
	DashBoardDao dashBoardDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ProductGuaranteeDao productGuaranteeDao;
	@Autowired
	AbheeBranchDao abheeBranchDao;
	@Autowired
	AbheeTaskStatusDao abheeTaskStatusDao;
	@Autowired
	RequestTimeDao  requestTimeDao;
	@Autowired
	TaskHistoryDao taskHistoryDao;

	/* @Autowired private Environment environment; */
	@RequestMapping("/Customer")
	public String showCustomerRegistrationForm(Model model, HttpServletRequest request) throws JsonProcessingException {
		LOGGER.debug("Calling Customer at controller");
		return null;
	}

	@RequestMapping(value = "/saveRestCustomer", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public HashMap<String, String> SaveAbheeCustomer(@RequestBody Customer customer)
			throws IOException, MessagingException {
		LOGGER.debug("Calling saveRestCustomer at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		String regSuccessMsg = customer.getFirstname() + " " + customer.getLastname()
				+ ",  Successfully registered with ABhee Smart Homes. \n You can login using  \n UserId:  "
				+ customer.getMobilenumber() + " or " + customer.getEmail() + "\n password: " + customer.getPassword();
		try {
			Customer custexist = customerDao.checkCustomerExistOrNot(customer);
			if (custexist == null) {
				customer.setRegistedredFromAndroid("1");
				customer.setEnabled("1");
				customerDao.saveAbheeCustomer(customer);
				sendSMS.sendSMS(regSuccessMsg, customer.getMobilenumber());
				sendingMail.sendConfirmationEmail(customer);
				code = customer.getFirstname() + " " + customer.getLastname();
			} else {
				code = "Already Registered";
			}
		} catch (IOException e) {
			code = "NOT_FOUND";
			e.printStackTrace();
		}
		hm.put("status", code);
		return hm;
	}

	@RequestMapping(value = "/custMobileDuplicate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody String mobileDuplicate(@RequestBody Customer customer, HttpServletRequest request)
			throws JSONException {
		JSONObject objJSON = new JSONObject();
		try {
			String mobileno = customer.getMobilenumber();
			Customer custBean = customerDao.checkCustomerExistOrNotbyMobile(mobileno);
			if (custBean != null) {
				objJSON.put("Mobileno", "already exists");
			} else {
				objJSON.put("Mobileno", "does not exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
			objJSON.put("msg", "fail");
		}
		return String.valueOf(objJSON);
	}

	@RequestMapping(value = "/custEmailDuplicate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody String emailDuplicate(@RequestBody Customer customer, HttpServletRequest request)
			throws JSONException {
		JSONObject objJSON = new JSONObject();
		try {
			String emailId = customer.getEmail();
			Customer custBean = customerDao.checkCustomerExistOrNotByEmail(emailId);
			if (custBean != null) {
				objJSON.put("Emailid", "already exists");
			} else {
				objJSON.put("Emailid", "does not exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
			objJSON.put("msg", "fail");
		}
		return String.valueOf(objJSON);
	}

	@RequestMapping(value = "/requestsms", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> getOTP(@RequestBody Customer user) {
		LOGGER.debug("Calling requestsms at controller");
		String custMobile = user.getMobilenumber();
		Random random = new Random();
		String otpnumber = String.format("%04d", random.nextInt(10000));
		// String msg="Dear,'"+user.getFirstname()+"' "+user.getLastname()+"', Thanks
		// for registering with Abhee Smart Home Systems. OTP for your registration
		// is:'"+otpnumber+"'";
		HttpStatus code = null;
		try {
			String status = sendSMS.sendSMS(otpnumber, custMobile);
			if (status.equals("OK")) {
				code = HttpStatus.OK;
			} else {
				code = HttpStatus.NOT_FOUND;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		OTPDetails oTPDetails = new OTPDetails();
		oTPDetails.setMobileno(custMobile);
		oTPDetails.setOTPnumber(otpnumber);
		oTPDetailsDao.saveOTPdetails(oTPDetails);
		ResponseEntity<String> response = new ResponseEntity<String>(otpnumber, code);
		return response;
	}

	@RequestMapping(value = "/otpVerification", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody String getOtp(@RequestBody Customer customer, HttpServletRequest request)
			throws JSONException {
		LOGGER.debug("Calling otpVerification at controller");
		String otp = customer.getOtpstatus();
		customer.setOtpstatus(otp);
		JSONObject json = new JSONObject();
		if (otp.equals("OTP_Verified")) {
			customerDao.getProfileInfo(customer);
			json.put("status", "Updated");
		}
		return String.valueOf(json);
	}

	@RequestMapping(value = "/requestsms2", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public HashMap<String, String> VerifyingAndSendOTP(@RequestBody Customer custBean) {
		LOGGER.debug("Calling requestsms2 at controller");
		String custMobile = custBean.getMobilenumber();
		String custemail = custBean.getEmail();
		Random random = new Random();
		String otpnumber = String.format("%04d", random.nextInt(10000));
		/*
		 * String tmsg =environment.getProperty("app.otpmsg"); System.out.println(tmsg);
		 * tmsg= tmsg.replaceAll("_fullname_",
		 * custBean.getFirstname()+" "+custBean.getLastname());
		 * tmsg=tmsg.replaceAll("_otp_", otpnumber);
		 */
		String code = null;
		Customer custbean1 = customerDao.checkCustomerExistOrNotbyMobile(custMobile);
		Customer customer = customerDao.checkCustomerExistOrNotByEmail(custemail);
		String msg = "Dear Customer,thanks for registering with Abhee Smart Home Systems. OTP for your registration is:"
				+ otpnumber;
		HashMap<String, String> hm = new HashMap<String, String>();
		if (null == custbean1 && null == customer) {
			try {
				String status = sendSMS.sendSMS(msg, custMobile);
				if (status.equals("OK")) {
					code = "OK";
					OTPDetails oTPDetails = new OTPDetails();
					oTPDetails.setMobileno(custMobile);
					oTPDetails.setOTPnumber(otpnumber);
					oTPDetailsDao.saveOTPdetails(oTPDetails);
					hm.put("otpnumber", otpnumber);
					hm.put("statuscode", code);
				} else {
					code = "NOT_FOUND";
					hm.put("statuscode", code);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			code = "Mobilenumber or email id already exists";
			hm.put("statuscode", code);
		}
		return hm;
	}

	@RequestMapping(value = "/requestsms3", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public HashMap<String, String> VerifyingAndSendOTP2(@RequestBody Customer custBean) {
		LOGGER.debug("Calling requestsms3 at controller");
		String custMobile = custBean.getMobilenumber();
		String custemail = custBean.getEmail();
		Random random = new Random();
		String otpnumber = String.format("%04d", random.nextInt(10000));
		String code = null;
		Customer custbean1 = customerDao.checkCustomerExistOrNotbyMobile(custMobile);
		Customer customer = customerDao.checkCustomerExistOrNotByEmail(custemail);
		String msg = "Dear Customer,thanks for registering with Abhee Smart Home Systems. OTP for your registration is:"
				+ otpnumber;
		HashMap<String, String> hm = new HashMap<String, String>();
		if (null == custbean1 || null == customer) {
			try {
				String status = sendSMS.sendSMS(msg, custMobile);
				if (status.equals("OK")) {
					code = "OK";
					OTPDetails oTPDetails = new OTPDetails();
					oTPDetails.setMobileno(custMobile);
					oTPDetails.setOTPnumber(otpnumber);
					oTPDetailsDao.saveOTPdetails(oTPDetails);
					hm.put("otpnumber", otpnumber);
					hm.put("statuscode", code);
					// hm.put("status", "Updated");
				} else {
					code = "NOT_FOUND";
					hm.put("statuscode", code);
				}
			} catch (IOException e) {
				e.printStackTrace();
				code = "Failed";
				hm.put("statuscode", code);
			}
		} else {
			code = "Not Updated";
			hm.put("statuscode", code);
		}
		return hm;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/logincredentials", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String checkingLogincredentials(@RequestBody Customer customer)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling logincredentails at controller");
		String code = null;
		String username = customer.getUsername();
		String password=customer.getPassword();
		String message="";
		String breakpoint="0";
		HashMap<String, String> hm = new HashMap<String, String>();
		JSONObject json = new JSONObject();
		System.out.println("rest call user called at end");
		List<Customer> custMobileOrEmail=customerDao.checkMobileOrEmailExistOrNot(username,password);
		
		if(username.length()==10) {
			for(int i=0;i<custMobileOrEmail.size();i++) {
				if(username.equals(custMobileOrEmail.get(i).getMobilenumber())&& breakpoint.equals("0")){
					message="OK";
					breakpoint="1";
				}else if(breakpoint.equals("0")){
					code="Invalid Mobile Number";
				}
			}
		}else {
			for(int i=0;i<custMobileOrEmail.size();i++) {
				if(username.equals(custMobileOrEmail.get(i).getEmail())&& breakpoint.equals("0"))
				{
					message="OK";
					breakpoint="1";
				}else if(breakpoint.equals("0")){
					code="Invalid Email Id";
				}
			}
		}
		System.out.println(message);
		 if (message.equals("OK")) 
		 { 
			 List<Customer> userBean =customerDao.checkcustomerExistOrNot(customer);
			 System.out.println("rest call user called at staring" + userBean);
			 ObjectMapper objectMapper = new ObjectMapper();
			 try 
			 { 
				 if (!userBean.isEmpty()) 
				 {
				 code = customer.getFirstname() + " " + customer.getLastname();
				 json.put("userbean", userBean); 
				 json.put("status","success"); 
				 }
				 else {
					 code ="Customer Details NOT_FOUND";
					 json.put("status",code);
				 }
			 } 
			 catch (Exception e) 
			 {
					  e.printStackTrace(); 
					  json.put("status", "fail"); 
					  return String.valueOf(json);
			 } 
		 } 
		 else 
		 	{ 
			 if(custMobileOrEmail.size()==0) {
				 code ="Customer Details NOT_FOUND";
			 }
			 else
			 { 
				 code +=" So,Customer Details NOT_FOUND";
			 }
			 System.out.println("rest call user called at end"); 
			 json.put("status", code); 
			 }
		return String.valueOf(json);
	}

	/*
	 * @RequestMapping(value="/getcategories", method=RequestMethod.POST, consumes =
	 * "application/json", produces = "application/json") public String
	 * getCategoriesList() throws JsonProcessingException, JSONException {
	 * List<Category> listOrderBeans = categoryDao.getCategoryNames(); JSONObject
	 * json =new JSONObject(); //ObjectMapper objectMapper = new ObjectMapper();
	 * //String userjson = objectMapper.writeValueAsString(userBean); //String
	 * categoryjson = objectMapper.writeValueAsString(listOrderBeans); if(null !=
	 * listOrderBeans) { json.put("categorieslist", listOrderBeans);
	 * //users.add(userBean); ObjectMapper objectMapper = new ObjectMapper(); String
	 * userjson = objectMapper.writeValueAsString(userBean); //String categoryjson =
	 * objectMapper.writeValueAsString(listOrderBeans); if(null != userBean) {
	 * //json.put("categorieslist", listOrderBeans); code
	 * =userBean.getFirstname()+" "+userBean.getLastname(); json.put("customerBean",
	 * userBean); } else //code="NOT_FOUND"; json.put("categorieslist",
	 * "NOT_FOUND"); return userjson; }
	 */

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getcategories", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getCategoriesList() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getCategories at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		List<Category> listOrderBeans = categoryDao.getCategoryNames();
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		// String userjson = objectMapper.writeValueAsString(userBean);
		// String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
		if (null != listOrderBeans) {
			/*jsonObj=om.writeValueAsString(listOrderBeans);*/
			json.put("categorieslist", listOrderBeans);
		} else
			// code="NOT_FOUND";
			json.put("categorieslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(json);
	}

	@RequestMapping(value = "/getproducts", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getProductslist(@RequestBody Product product) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getproducts at controller");
		List<Map<String, Object>> listOrderBeans = productDao.getProductsDescription(product);
		JSONObject json = new JSONObject();
		/*String jobj = null;
		ObjectMapper om = new ObjectMapper();*/
		if (null != listOrderBeans) {
			json.put("productDetails", listOrderBeans);
			//jobj=om.writeValueAsString(listOrderBeans);
		} else
			// code="NOT_FOUND";
			json.put("productDetails", "NOT_FOUND");
		return String.valueOf(json);
	}
	@RequestMapping(value = "/getproductdetails", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getProductslists() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getproducts at controller");
		List<Map<String, Object>> listOrderBeans = productDao.getProducts();
		JSONObject json = new JSONObject();
		/*String jobj = null;
		ObjectMapper om = new ObjectMapper();*/
		// ObjectMapper objectMapper = new ObjectMapper();
		// String userjson = objectMapper.writeValueAsString(userBean);
		// String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
		if (null != listOrderBeans) {
			json.put("productslist", listOrderBeans);
			/*jobj=om.writeValueAsString(listOrderBeans);*/
		} else
			// code="NOT_FOUND";
			json.put("productslist", "NOT_FOUND");
		return String.valueOf(json);
	}
	
	@RequestMapping(value = "/getcompanies", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getCompaniesBycategoryId(@RequestParam(value = "id", required = false) String categoryid)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getcompanies at controller");
		/*String jobj = null;
		ObjectMapper om = new ObjectMapper();*/
		List<Map<String, Object>> listOrderBeans = companyDao.getCompaniesByCategoryId(categoryid);
		JSONObject json = new JSONObject();
		if (null != listOrderBeans) {
			json.put("companyDetails", listOrderBeans);
			//jobj=om.writeValueAsString(listOrderBeans);
		} else
			json.put("companyDetails", "NOT_FOUND");
		return String.valueOf(json);
	}

	@RequestMapping(value = "/getproductsby", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getProductsByCompanyId(@RequestParam(value = "id", required = false) String companyid)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getproductsby at controller");
		/*String jobj = null;
		ObjectMapper om = new ObjectMapper();*/
		System.out.println(companyid);
		List<Map<String, Object>> listOrderBeans = productDao.getProductsByCompanyId(companyid);
		JSONObject json = new JSONObject();
		if (null != listOrderBeans) {
			json.put("productDetails", listOrderBeans);
			//jobj=om.writeValueAsString(listOrderBeans);
		} else
			json.put("productDetails", "NOT_FOUND");
		return String.valueOf(json);
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/savequotationrequest", consumes = "application/json", produces = "application/json")
	public HashMap<String, String> saveQuotationRequest(@RequestBody SalesRequest salesrequest,
			HttpServletRequest request) {
		LOGGER.debug("Calling savequotationrequest at controller");
		// JSONObject objJson = new JSONObject();
		String code = null;
		int randomNum = ThreadLocalRandom.current().nextInt(10, 20 + 1);
		salesrequest.setSalesrequestnumber(String.valueOf(randomNum));
		String modelnumber = salesrequest.getModelnumber();
		String address = salesrequest.getAddress();
		String customerid = salesrequest.getCustomerid();
		String location = salesrequest.getLocation();
		String mobile = salesrequest.getMobileno();
		String email = salesrequest.getEmail();
		String catid=salesrequest.getCatid();
		Boolean quotation =srequestDao.checkSalesrequestExistsorNotByEmailAndModelNo(salesrequest);
		System.out.println("@@@@@@@@@@"+salesrequest.getImgfiles() );
		String[] imgBytes =salesrequest.getImgfiles().split(",");
		if (quotation==false) 
		{
			if (imgBytes != null) 
			{
				String sfn ="";
				for(int i=0;i<imgBytes.length;i++) {
					if(i==0) {
				 sfn =sfn+imgdecoder(imgBytes[i], request);
				System.out.println(sfn);
					}
					else {
						
						sfn =sfn+"*"+imgdecoder(imgBytes[i], request);
						System.out.println(sfn);
					}
				}
				salesrequest.setImgfiles(sfn);
			}	
		salesrequest.setEnable("1");
		salesrequest.setWebstatus(1);
		salesrequest.setQstatus("1");
		salesrequest.setQuotationstatus(1);
		salesrequest.setNotes(" ");
		if(salesrequest.getLocation()!=null)
		{	
		salesrequest.setLocation(location);
		}
		else
		{
			salesrequest.setLocation(" ");	
		}
		salesrequest.setQuotationDocuments(" ");	
		salesrequest.setKstatus("New");
		srequestDao.saveRequest(salesrequest);
		code = "requestSubmittedSuccessfully";
		}
		else
		{
			code="Request Already Exists";
		}
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("status", code);
		return hm;
	}

	/*
	 * @PostMapping(value="/restSalesRequest", consumes = "application/json",
	 * produces = "application/json") public String
	 * saveRequestDetails(@ModelAttribute SalesRequest
	 * salesrequest,@RequestParam("imgfile") MultipartFile[]
	 * uploadedFiles, @RequestParam(value = "imgfile") String[]
	 * file,HttpServletRequest request,RedirectAttributes redir) throws
	 * IllegalStateException, IOException, MessagingException {
	 * LOGGER.debug("Calling restSalesRequest at controller"); String
	 * referalUrl=request.getHeader("referer"); int filecount =0; //String str[] =
	 * salesrequest.getLocation().split("&"); int randomNum =
	 * ThreadLocalRandom.current().nextInt(10, 20 + 1);
	 * salesrequest.setSalesrequestnumber(salesrequest.getModelnumber()+randomNum);
	 * salesrequest.setLat(str[0]); salesrequest.setLongitude(str[1]);
	 * salesrequest.setEnable("1"); for(MultipartFile multipartFile : uploadedFiles)
	 * { String fileName = multipartFile.getOriginalFilename();
	 * if(!multipartFile.isEmpty()) { filecount++;
	 * multipartFile.transferTo(fileTemplate.moveFileTodir(fileName)); } }
	 * for(String multipartFile : file) { //String imgData =
	 * request.getParameter("imgfile"); if (StringUtils.isNotBlank(multipartFile)) {
	 * // Base64Decoder decoder = new Base64Decoder(); File
	 * pathFile=fileTemplate.makeDirectory(); try (FileOutputStream imageOutFile =
	 * new FileOutputStream(pathFile)) { //byte[] imgBytes =
	 * Base64.getDecoder().decode(multipartFile.split(",")[1]);
	 * //imageOutFile.write(imgBytes); } } } if(filecount>0) {
	 * salesrequest.setImgfiles(fileTemplate.concurrentFileNames());
	 * fileTemplate.clearFiles(); } Boolean result
	 * =srequestDao.checkSalesrequestExistsorNotByEmailAndModelNo(salesrequest);
	 * if(result==false) { srequestDao.saveRequest(salesrequest);
	 * //sendingMail.SendingSalesRequestByEmail(salesrequest.getEmail());
	 * //sendingMail.sendSalesRequestEmailWithattachment(salesrequest.getEmail(),
	 * uploadedFiles); } else {
	 * redir.addFlashAttribute("msg","Record Already Exists");
	 * System.out.println("record Already Exists"); return "redirect:abheecategory";
	 * } redir.addFlashAttribute(
	 * "msg","We Received The Request and will send you the Quotation soon. Thanking you."
	 * ); System.out.println("&&&&&&&&&&&&&&&&&&&&& Mail Sent"); return
	 * "redirect:abheecategory";
	 * 
	 * }
	 */

	@PostMapping(value = "/restSaveServiceRequest", consumes = "application/json", produces = "application/json")
	public String saveServiceRequest(@RequestBody ServiceRequest serviceRequest, HttpServletRequest request)
			throws JSONException, IllegalStateException, IOException {
		LOGGER.debug("Calling saveServiceRequest at controller");
		System.out.println("enter to task controller Submit");
		JSONObject objJson = new JSONObject();
		String message = serviceRequest.getMessage();
		String servicetypeid = serviceRequest.getServicetypeid();
		String catid = serviceRequest.getCatid();
		String comid=serviceRequest.getCompanyid();
		String modelid = serviceRequest.getModelid();
		String customerId = serviceRequest.getCustomerId();
		String custaddress = serviceRequest.getCustaddress();
		String warranty = serviceRequest.getWarranty();
		String requesttime=serviceRequest.getRequesttime();
		AbheeTask task = new AbheeTask();
		task.setAdditionalinfo("0");
		task.setAssignto("2");
		task.setDescription(message);
		task.setKstatus("5");
		task.setPriority("3");
		task.setSeverity("3");
		task.setStatus("1");
		//task.setSubject("Task created By Customer");
		task.setServiceType(servicetypeid);
		task.setCategory(catid);
		task.setCompany(comid);
		task.setModelid(modelid);
		task.setCommunicationaddress(custaddress);
		task.setRequesttime(requesttime);
		Customer customer = customerDao.findCustomerByCustId(customerId);
		task.setSubject("Task created By "+customer.getFirstname()+" "+customer.getLastname());
		task.setCustomerId(customerId);
		task.setWarranty(warranty);
		task.setRequesttime(requesttime);
		//task.setAssignby("1");
		task.setTaskdeadline(" ");	
		if(task.getImgfile()==" ")
		{
		task.setImgfile("(NULL)");
		}
		/*task.setInvimg("icon.png ");*/
		task.setAddComment(" ");
		task.setRequestType("Service Request");
		Map<String, Object> abheeTask = reportIssueDao.checkServiceRequestExisrOrNot(task);
		
		System.out.println("@@@@@@@@@@"+serviceRequest.getImgname() );
		String[] imgBytes =serviceRequest.getImgname().split(",");
				/*byte[] imageByteArray = Base64.getDecoder().decode(imgBytes[1]);*/
				//byte[] imageByteArray = Base64Decoder.decode(imgBytes[1]);
		
				 if (null == abheeTask) {
				if (imgBytes  != null) 
				{		String sfn ="";
					for(int i=0;i<imgBytes.length;i++) {
						if(i==0) {
					 sfn =sfn+imgdecoder(imgBytes[i], request);
					System.out.println(sfn);
						}
						else {
							
							sfn =sfn+"*"+imgdecoder(imgBytes[i], request);
							System.out.println(sfn);
						}
					}
					task.setUploadfile(sfn);
				}
			/*}
			else
			{
				task.setUploadfile("icon.png ");
			}*/
			reportIssueDao.saveServiceRequest(task);
			// taskHistoryLogsDao.historyLogForcustomerEntry(task);
			// sendingMail.sendingMailWithTaskStatus(task);
			//task.setCommunicationaddress(custaddress);
			try {
				customerDao.updateCustomer(customer);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(message + "  " + servicetypeid);
			objJson.put("status", "Request Submitted Successfully");
		} else {
			objJson.put("status", "Service Request Already Exists");
			System.out.println("Service request already Exists");
		}
		return String.valueOf(objJson);
	}

	@RequestMapping(value = "/getproductcompanies", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllProductCompanies(@RequestBody Product product) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getproductcompanies at controller");
		List<Map<String, Object>> listOrderBeans = productDao.getProductCompaniesdesc(product);
		JSONObject json = new JSONObject();
		/*String jobj = null;
		ObjectMapper om = new ObjectMapper();*/
		if (null != listOrderBeans) {
			json.put("productcompanyDetails", listOrderBeans);
			//jobj=om.writeValueAsString(listOrderBeans);
		} else
			json.put("productcompanyDetails", "NOT_FOUND");
		return String.valueOf(json);
	}

	@RequestMapping(value = "/restEditProfileInfo", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getPersonalInfo(@RequestBody Customer customer)
			throws JsonProcessingException, JSONException, IOException {
		LOGGER.debug("Calling restEditProfileInfo at controller");
		JSONObject json = new JSONObject();
		HashMap<String, String> hm = new HashMap<String, String>();
		hm = VerifyingAndSendOTP2(customer);
		if (hm.get("otpnumber") != null) {
			json.put("status", hm.get("statuscode"));
			json.put("otp", hm.get("otpnumber"));
			// customerDao.getProfileInfo(customer);
		} else {
			json.put("status", hm.get("statuscode"));
			customerDao.getProfileInfo(customer);
		}
		return String.valueOf(json);
	}

	@RequestMapping(value = "/restChangePassword", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getPasswordInfo(@RequestBody Customer customer) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling restChangePassword at controller");
		int result = customerDao.getPassword(customer);
		JSONObject json = new JSONObject();
		if (result == 1) {
			json.put("password", "Updated");
		} else
			json.put("password", "Not Updated");
		return String.valueOf(json);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/restForgotPassword", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getForgotPassword(@RequestBody Customer user, HttpServletRequest request)
			throws IOException, JSONException {
		LOGGER.debug("Calling  restForgotPassword at controller");
		System.out.println("enter to restForgotPassword");
		HttpStatus code = null;
		JSONObject json = new JSONObject();
		String custMobile = user.getMobilenumber();
		Customer custbean2 = customerDao.checkCustomerExistOrNotbyMobile(custMobile);
		String password = "Dear" + " " + custbean2.getFirstname() + " " + custbean2.getLastname()
				+ ", your password for Abhee smart home systems customer account" + " '" + custbean2.getMobilenumber() + "' "
				+ "is:" + custbean2.getPassword();
		try {
			String status = sendSMS.sendSMS(password, custMobile);
			if (status.equals("OK")) {
				code = HttpStatus.OK;
			} else {
				code = HttpStatus.NOT_FOUND;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		json.put("password", password);
		return String.valueOf(json);
		/*
		 * ResponseEntity<String> response = new
		 * ResponseEntity<String>(custbean2.getPassword(),code); return response;
		 */
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/restAdminForgotPassword", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAdminForgotPassword(@RequestBody User user, HttpServletRequest request)
			throws IOException, JSONException {
		LOGGER.debug("Calling  restAdminForgotPassword at controller");
		System.out.println("enter to restForgotPassword");
		HttpStatus code = null;
		JSONObject json = new JSONObject();
		String empMobile = user.getMobilenumber();
		User custbean2 = userService.checkCustomerExistOrNotbyMobile(empMobile);
		String password=custbean2.getPassword();
		String msg = "Dear" + " " + custbean2.getFirstname() + " " + custbean2.getLastname()
				+ ", your password for Abhee smart home systems employee account" + " '" + custbean2.getMobilenumber() + "' "
				+ "is:" + custbean2.getPassword();
		try {
			String status = sendSMS.sendSMS(msg, empMobile);
			if (status.equals("OK")) {
				code = HttpStatus.OK;
			} else {
				code = HttpStatus.NOT_FOUND;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		json.put("password", password);
		return String.valueOf(json);
		/*
		 * ResponseEntity<String> response = new
		 * ResponseEntity<String>(custbean2.getPassword(),code); return response;
		 */
	}

	@RequestMapping(value = "/getServiceStatus", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getTaskStatusByCustomerId(@RequestBody Customer customer)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getTicketStatus at controller");
		String status=null;
		JSONObject json = new JSONObject();
		try {
			List<String> listOrderBeans = abheeTaskDao.getTaskNoByCustomerId(customer);
			List<HashMap<String, ArrayList<HashMap<String, List<Map<String, Object>>>>>> list1 = new ArrayList<HashMap<String, ArrayList<HashMap<String, List<Map<String, Object>>>>>>();
			System.out.println(listOrderBeans);
			if (null != listOrderBeans) {
				int j=0;
				for (int i = 0; i < listOrderBeans.size(); i++) {
					
					System.out.println(listOrderBeans.get(i));
					ArrayList<HashMap<String, List<Map<String, Object>>>> list = new ArrayList<HashMap<String, List<Map<String, Object>>>>();
					list.clear();
					HashMap<String, List<Map<String, Object>>> hm = new HashMap<String, List<Map<String, Object>>>();
					HashMap<String, ArrayList<HashMap<String, List<Map<String, Object>>>>> hm1 = new HashMap<String, ArrayList<HashMap<String, List<Map<String, Object>>>>>();
					List<Map<String, Object>> listOrderBeans2 = abheeTaskDao.getCustomerResponseByCustomerId(listOrderBeans.get(i));
					List<Map<String, Object>> listOrderBeans1 = abheeTaskDao.getAdminResponseByCustomerId(listOrderBeans.get(i));
					List<Map<String, Object>> listOrderBeans3 = abheeTaskDao.getAdminResponseByCustomerIdWhenStatusZero(listOrderBeans.get(i));
					System.out.println(listOrderBeans1);
					System.out.println(listOrderBeans2);
					System.out.println(listOrderBeans3);
					/*if (listOrderBeans3.get(i).get("status").equals("1")) {
						hm.put("CustomerBean", listOrderBeans2);
						hm.put("AdminBean", listOrderBeans1);
					} else {
						hm.put("CustomerBean", listOrderBeans2);
						hm.put("AdminBean", listOrderBeans3);
					}*/
					status = listOrderBeans3.get(0).get("status").toString();
					switch (status) {
					case "1":
						hm.put("CustomerBean", listOrderBeans2);
						hm.put("AdminBean", listOrderBeans1);
						list.add(hm);
						hm1.put("" + j, list);
						j++;
						list1.add(hm1);
						
						//continue;
						break;
					case "0":
						/*
						 * hm.put("CustomerBean", listOrderBeans2);
						 *  hm.put("AdminBean",listOrderBeans3);
						 */
						break;

					default:
						
						break;
					}
					/*
					 * list.add(hm); hm1.put("" + i, list);
					 * j++;
						list1.add(hm1);
					 */
					

				}

				json.put("ServiceRequest", list1);
			} else
				json.put("CustomerResponseList", "NOT_FOUND");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return String.valueOf(json);
	}

	@SuppressWarnings("static-access")
	private String imgdecoder(String imgData, HttpServletRequest request) {
		String filepath = null;
		FileOutputStream osf;
		KhaibarGasUtil utils = new KhaibarGasUtil();
		String id = utils.randNum(4);
		Base64Decoder decoder = new Base64Decoder();
		//byte[] imgBytes = decoder.decode(imgData.split(",")[1]);
		byte[] imgBytes = decoder.decode(imgData);
		/*
		 * name=name.substring(n + 1); name=name+".png";
		 */
		long millis = System.currentTimeMillis() % 1000;
		filepath = id + millis + ".png";
		// String rootPath = request.getSession().getServletContext().getRealPath("/");
		String rootPath = System.getProperty("catalina.base");
		// File dir = new File(rootPath + File.separator + "reportDocuments");
		File dir = new File(rootPath + File.separator + "webapps" + File.separator + "abheeimg");
		if (!dir.exists()) {
			dir.mkdirs();
		}

		System.out.println(dir);

		try {
			osf = new FileOutputStream(new File(dir.getAbsolutePath() + File.separator + filepath));
			osf.write(imgBytes);
			osf.flush();
		} catch (IOException e) {
			System.out.println("error : " + e);
		}
		// filepath= "abheeimg/"+filepath;
		return filepath;
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/saveEnquiryDetails", consumes = "application/json", produces = "application/json")
	public HashMap<String, String> saveEnquiryDetails(@RequestBody SalesRequest salesrequest,
			HttpServletRequest request) {
		LOGGER.debug("Calling saveEnquiryDetails at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		int randomNum = ThreadLocalRandom.current().nextInt(10, 20 + 1);
		salesrequest.setSalesrequestnumber(String.valueOf(randomNum));
		String modelnumber = salesrequest.getModelnumber();
		String mobile = salesrequest.getMobileno();
		String email = salesrequest.getEmail();
		String custname = salesrequest.getCustomername();
		String enquirydetails = salesrequest.getReqdesc();
		String enquiryimage = imgdecoder(salesrequest.getImgfiles(), request);
		if (!salesrequest.getImgfiles().isEmpty()) {
			salesrequest.setImgfiles(enquiryimage);
		}
		if (enquirydetails != "") {
			// salesrequest.setStatus(1);
			srequestDao.saveRequest(salesrequest);
			code = "Enquiry details sent successfully";
			hm.put("status", code);
		} else {
			code = "Not Found";
			hm.put("status", code);
		}
		// srequestDao.saveRequest(salesrequest);
		return hm;
	}

	@PostMapping(value = "/getrestseveritycounts", consumes = "application/json", produces = "application/json")
	public String getRestSeverityCounts(@RequestBody User user, HttpServletRequest request) throws JSONException {
		JSONObject objJSON = new JSONObject();
		User Validuser = userService.getUserById(user.getId());
		List<Map<String, Object>> severityCounts = dashBoardDao.getTasksCountBySeverityforRest(Validuser);
		objJSON.put("status", severityCounts);
		return String.valueOf(objJSON);
	}

	@PostMapping(value = "/userloggging", consumes = "application/json", produces = "application/json")
	public String checkUserLogin(@RequestBody User user, HttpServletRequest request) throws JSONException {
		JSONObject objJSON = new JSONObject();
		List<Map<String, Object>> validUser = userService.checkUserExistence(user);
		if (!validUser.isEmpty()) {
			objJSON.put("status", validUser);
			objJSON.put("login", "success");
		} else {
			objJSON.put("login", "user not exists");
		}
		return String.valueOf(objJSON);
	}

	@PostMapping(value = "/getreststatuscounts", consumes = "application/json", produces = "application/json")
	public String getRestStatusCounts(@RequestBody User user, HttpServletRequest request) throws JSONException {
		JSONObject objJSON = new JSONObject();
		User Validuser = userService.getUserById(user.getId());
		List<Map<String, Object>> statusCounts = dashBoardDao.getTasksCountByStatusforRest(Validuser);
		objJSON.put("status", statusCounts);
		return String.valueOf(objJSON);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getPriorities", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getPrioritiesList() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getPriorities at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		List<Map<String, Object>> listOrderBeans = abheeTaskDao.getPriority();
		JSONObject json = new JSONObject();
		/*ObjectMapper om = new ObjectMapper();
		String jsonObj = null;*/
		// String userjson = objectMapper.writeValueAsString(userBean);
		// String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
		if (null != listOrderBeans) {
			json.put("prioritieslist", listOrderBeans);
			//jsonObj=om.writeValueAsString(listOrderBeans);
		} else
			// code="NOT_FOUND";
			json.put("prioritieslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(json);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getSeverities", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getSeveritiesList() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getSeverities at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		List<Map<String, Object>> listOrderBeans = abheeTaskDao.getSeverity();
		JSONObject json = new JSONObject();
		/*ObjectMapper om = new ObjectMapper();
		String jsonObj = null;*/
		// ObjectMapper objectMapper = new ObjectMapper();
		// String userjson = objectMapper.writeValueAsString(userBean);
		// String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
		if (null != listOrderBeans) {
			//jsonObj=om.writeValueAsString(listOrderBeans);
			json.put("severitieslist", listOrderBeans);
		} else
			// code="NOT_FOUND";
			json.put("severitieslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(json);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getUsers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getUsersList() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getUsers at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		List<User> listOrderBeans = userDao.getAllUsers();
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		// String userjson = objectMapper.writeValueAsString(userBean);
		// String categoryjson = objectMapper.writeValueAsString(listOrderBeans);
		if (null != listOrderBeans) {
			//jsonObj=om.writeValueAsString(listOrderBeans);
			json.put("userslist", listOrderBeans);
		} else
			// code="NOT_FOUND";
			json.put("userslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(json);
	}

	@PostMapping(value = "/saveProduct", consumes = "application/json", produces = "application/json")
	public String saveProduct(@RequestBody Category cate, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling saveProduct at controller");
		JSONObject json = new JSONObject();
		String code = "";
		int id = 0;
		try {
			Category orgBean = categoryDao.getCategoryNameById(cate);
			int dummyId = 0;
			if (orgBean != null) {
				dummyId = orgBean.getId();
			}
			if (cate.getId() == null) {
				if (dummyId == 0) {
					String productimages = imgdecoder(cate.getCategoryimg(), request);
					if (!cate.getCategoryimg().isEmpty()) {
						cate.setCategoryimg(productimages);
					}
					cate.setStatus("1");
					categoryDao.saveCategory(cate);
					code = "success";
				} else {
					code = "exists";
				}
			} else {
				id = cate.getId();
				if (id == dummyId || orgBean == null) {
					String productimages = imgdecoder(cate.getCategoryimg(), request);
					if (!cate.getCategoryimg().isEmpty()) {
						cate.setCategoryimg(productimages);
					}
					categoryDao.UpdateCategory(cate);
					code = "updated";
				} else {
					code = "exists";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);

		return String.valueOf(json);
	}

	@RequestMapping(value = "/productslist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getProductsList() {
		LOGGER.debug("Calling productslist at controller");
		JSONObject json = new JSONObject();
		List<Category> listOrderBeans = null;
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		try {
			listOrderBeans = categoryDao.getCategoryNames();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("productslist", listOrderBeans);
				/*jsonObj=om.writeValueAsString(listOrderBeans);*/
				
			} else {
				json.put("productslist", "NOT_FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}

	@RequestMapping(value = "/getquotationlist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getQuotationList(@RequestBody SalesRequest salesRequest) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getquotationlist at controller");
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		List<Map<String, Object>> listOrderBeans = null;
		try {
			listOrderBeans = srequestDao.getSalesRequestList1(salesRequest);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("quotationslist", listOrderBeans);
				/*jsonObj=om.writeValueAsString(listOrderBeans);*/
			} else {
				json.put("quotationslist", "NOT_FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}
	@RequestMapping(value = "/getquotationlists", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getQuotationLists(@RequestBody SalesRequest salesRequest) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getquotationlist at controller");
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		List<Map<String, Object>> listOrderBeans = null;
		try {
			listOrderBeans = srequestDao.getSalesRequestList2(salesRequest);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("quotationslist", listOrderBeans);
				//jsonObj=om.writeValueAsString(listOrderBeans);
			} else {
				json.put("quotationslist", "NOT_FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}

	@RequestMapping(value = "/getquotationlistByRequestNo", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getquotationlistByRequestNo(@RequestBody SalesRequest request)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getquotationlist at controller");
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		List<Map<String, Object>> listOrderBeans = null;
		List<Map<String, Object>> listOrderBeans1 = null, listOrderBeans2 = null;

		try {
			listOrderBeans = srequestDao.getSalesRequestListByRequestNo(request);
			listOrderBeans1 = srequestDao.getAdminResponseListByRequestNo(request);
			listOrderBeans2 = srequestDao.getAdminResponseListByRequestNoWhenStatusZero(request);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {

				listOrderBeans2.get(0).get("status");
				System.out.println("Status Code:" + listOrderBeans2.get(0).get("status"));

				if (!listOrderBeans2.get(0).get("status").equals(0)) {
					/*jsonObj=om.writeValueAsString(listOrderBeans);
					jsonObj=om.writeValueAsString(listOrderBeans2.get(0).get("status"));
					jsonObj=om.writeValueAsString(listOrderBeans1);*/
					json.put("quotationslist", listOrderBeans);
					json.put("AdminResponseStatus", listOrderBeans2.get(0).get("status"));
					json.put("AdminResponseList", listOrderBeans1);
				}

				else {
					listOrderBeans2.get(0).get("status");
					System.out.println("Status Code:" + listOrderBeans2.get(0).get("status"));
					/*jsonObj=om.writeValueAsString(listOrderBeans);
					jsonObj=om.writeValueAsString(listOrderBeans2.get(0).get("status"));*/
					json.put("quotationslist", listOrderBeans);
					json.put("AdminResponseStatus", listOrderBeans2.get(0).get("status"));
					//json.put("AdminResponseList", listOrderBeans2);
				}
			} else {
				json.put("quotationslist", "NOT_FOUND");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}

	@PostMapping(value = "/saveCompany", consumes = "application/json", produces = "application/json")
	public String saveCompany(@RequestBody Company com, HttpServletRequest request, BindingResult bindingresults)
			throws JSONException {
		LOGGER.debug("Calling saveCompany at controller");
		JSONObject json = new JSONObject();
		String code = "";
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		int id = 0;
		try {
			Company orgBean = companyDao.getCompanyNameById(com);
			int dummyId = 0;
			if (orgBean != null) {
				dummyId = orgBean.getId();
			}
			if (com.getId() == null) {
				if (dummyId == 0) {
					String companyimages = imgdecoder(com.getCompanyimg(), request);
					if (!com.getCompanyimg().isEmpty()) {
						com.setCompanyimg(companyimages);
					}
					com.setStatus("1");
					companyDao.saveCompany(com);
					code = "success";
				} else {
					code = "exists";
				}
			} else {
				id = com.getId();
				if (id == dummyId || orgBean == null) {
					String companyimages = imgdecoder(com.getCompanyimg(), request);
					if (!com.getCompanyimg().isEmpty()) {
						com.setCompanyimg(companyimages);
					}
					companyDao.UpdateCompany(com);
					code = "updated";
				} else {
					code = "exists";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);

		return String.valueOf(json);
	}

	@RequestMapping(value = "/companieslist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getCompaniesList() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling companieslist at controller");
		List<Company> listOrderBeans = null;
		JSONObject json = new JSONObject();
		try {
			listOrderBeans = companyDao.getCompanyNames();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("companieslist", listOrderBeans);
			} else {
				json.put("companieslist", "NOT_FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}

	@PostMapping(value = "/saveProductModel", consumes = "application/json", produces = "application/json")
	public String savePrpductModel(@RequestBody Product pro, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling saveProductModel at controller");
		JSONObject json = new JSONObject();
		String code = "";

		String sfn = "";

		String vlinks[] = pro.getProductmodelvideoslinks().split(",");

		for (String files : vlinks) {
			sfn = sfn + files + "*";
		}

		String sfn2 = sfn.substring(0, sfn.length() - 1);

		pro.setProductmodelvideoslinks(sfn2);

		int id = 0;
		try {
			Product orgBean = productDao.getProductNameById(pro);
			int dummyId = 0;
			if (orgBean != null) {
				dummyId = orgBean.getId();
			}
			if (pro.getId() == null) {
				if (dummyId == 0) {
					String productmodelpics = imgdecoder(pro.getProductmodelpics(), request);
					if (!pro.getProductmodelpics().isEmpty()) {
						pro.setProductmodelpics(productmodelpics);
					}
					pro.setStatus("1");
					productDao.saveProduct(pro);
					code = "success";
				} else {
					code = "exists";
				}
			} else {
				id = pro.getId();
				if (id == dummyId || orgBean == null) {
					String productmodelpics = imgdecoder(pro.getProductmodelpics(), request);
					if (!pro.getProductmodelpics().isEmpty()) {
						pro.setProductmodelpics(productmodelpics);
					}
					productDao.UpdateProduct(pro);
					code = "updated";
				} else {
					code = "exists";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		return String.valueOf(json);
	}

	@RequestMapping(value = "/productmodelslist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getProductsModelsList() {
		LOGGER.debug("Calling productmodelslist at controller");
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		List<Product> listOrderBeans = null;
		try {
			listOrderBeans = productDao.getProductDetails();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				json.put("productmodelslist", listOrderBeans);
				//jsonObj=om.writeValueAsString(listOrderBeans);
			} else {
				json.put("productmodelslist", "NOT_FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}

	@PostMapping(value = "/saveCustomer", consumes = "application/json", produces = "application/json")
	public String saveCustomer(@RequestBody Customer user, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling saveCustomer at controller");
		JSONObject json = new JSONObject();
		String code = "";
		int id = 0;
		try {
			// Customer userBean= customerDao.getCustomerByObject(user);
			Customer userBean = null;
			if (user.getId() != null) {
				userBean = customerDao.getCustomerByObject(user);
			}
			int dummyId = 0;
			if (userBean != null) {
				dummyId = userBean.getId();
			}
			if (user.getId() == null) {
				if (dummyId == 0) {
					user.setEnabled("1");
					/* user.setRegistedredFromAndroid("1"); */
					customerDao.saveAbheeCustomer(user);
					sendingMail.sendConfirmationEmail(user);
					code = "success";
				} else {
					code = "exists";
				}
			} else {
				id = user.getId();
				if (id == dummyId || userBean == null) {
					customerDao.updateCustomer(user);
					sendingMail.sendConfirmationEmail(user);
					code = "updated";

				} else {
					code = "exists";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		return String.valueOf(json);
	}

	@RequestMapping(value = "/customerslist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getCustomersList() {
		LOGGER.debug("Calling customerslist at controller");
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		List<Customer> listOrderBeans = null;
		try {
			listOrderBeans = customerDao.getAbheeCustomerNames();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				/*jsonObj=om.writeValueAsString(listOrderBeans);*/
				json.put("customerslist", listOrderBeans);
			} else {
				json.put("customerslist", "NOT_FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}

	@PostMapping(value = "/saveUser", consumes = "application/json", produces = "application/json")
	public String saveUser(@RequestBody User user, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling saveUser at controller");
		JSONObject json = new JSONObject();
		String code = "";

		int id = 0;
		try {
			User userBean = null;

			userBean = userService.getUserByObject(user);

			int dummyId = 0;

			if (userBean != null) {
				dummyId = userBean.getId();
			}
			if (user.getId() == null) {
				if (dummyId == 0) {
					user.setEnabled("1");
					userService.saveUser(user);
					sendingMail.sendUserConfirmationEmail(user);
					code = "success";
				} else {
					code = "exists";
				}
			} else {
				id = user.getId();
				if (id == dummyId || userBean == null) {
					userService.updateUser(user);
					code = "updated";
				} else {
					code = "exists";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		return String.valueOf(json);
	}

	@RequestMapping(value = "/userslist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getEmployeesList() {
		LOGGER.debug("Calling userslist at controller");
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		List<User> listOrderBeans = null;
		try {
			listOrderBeans = userDao.getAllUsers();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				//jsonObj=om.writeValueAsString(listOrderBeans);
				json.put("userslist", listOrderBeans);
			} else {
				json.put("userslist", "NOT_FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}

	@PostMapping(value = "/saveServiceRequest", consumes = "application/json", produces = "application/json")
	public String saveServiceRequest(@RequestBody AbheeTask task, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling saveServiceRequest at controller");
		JSONObject json = new JSONObject();
		String code = "";
		String id=null;
		try 
		{
			AbheeTask orgBean = null;
			if (task.getTaskno() != "" && task.getTaskno() != null) 
			{
				orgBean = reportIssueDao.getRequestDetailsByObject(task);
			}
			String dummyId=null;
			if (orgBean != null) 
			{
				dummyId = orgBean.getTaskno();
			}	
			id = task.getTaskno();
			if (id.equals(dummyId) || orgBean == null) 
			{
				
					if (!task.getImgfile().isEmpty() && task.getImgfile().length()!=1 ) 
					{
						String images = imgdecoder(task.getImgfile(), request);
						task.setImgfile(images);
					}else {
						task.setImgfile(task.getAssignby());
					}
				if (task.getKstatus().equals(orgBean.getKstatus()))
				{
					code="already updated";
				}
				else
				{	
				
				reportIssueDao.updateRequest(task);
				code = "updated";
				}
			}
			else 
			{
				code = "exists";
			}	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		return String.valueOf(json);
	}

	@RequestMapping(value = "/taskslist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getServiceRequestList() {
		LOGGER.debug("Calling taskslist at controller");
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		List<Map<String, Object>> listOrderBeans = null;
		try {
			listOrderBeans = reportIssueDao.getTasksList1();

			System.out.println(listOrderBeans);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				//jsonObj=om.writeValueAsString(listOrderBeans);
				json.put("taskslist", listOrderBeans);
			} else {
				json.put("taskslist", "NOT_FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value = "/editProductWarranty", consumes = "application/json", produces = "application/json")
	public String updateWarranty(@RequestBody ProductGuarantee productGuarantee, HttpServletRequest request,
			BindingResult bindingresults) throws JSONException 
	{
		JSONObject json = new JSONObject();
		String code = "";
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		String id = null;
		try 
		{
			ProductGuarantee orgBean = (ProductGuarantee) productGuaranteeDao.getProductWarrantyDetailsByObject(productGuarantee);
			String dummyId = null;
			if (orgBean != null) 
			{
				dummyId = orgBean.getOrderId();
			}
			 else 
				{
					id = productGuarantee.getOrderId();
						if (id == dummyId || orgBean == null) 
						{
							productGuaranteeDao.updateWarranty(productGuarantee);
							code = "updated";
						} 
						else 
						{
							code = "exists";
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
	

	@PostMapping(value = "/saveProductWarranty", consumes = "application/json", produces = "application/json")
	public String saveWarranty(@RequestBody ProductGuarantee productGuarantee, HttpServletRequest request,
			BindingResult bindingresults) throws JSONException {
		LOGGER.debug("Calling saveProductWarranty at controller");
		ProductGuarantee productGuarantees = new ProductGuarantee();
		productGuarantees.setCustomerid(productGuarantee.getCustomerid());
		productGuarantees.setPurchaseddate(productGuarantee.getPurchaseddate());
		productGuarantees.setExpireddate(productGuarantee.getExpireddate());
		String productid= productGuaranteeDao.productid(productGuarantee.getProductmodelid());
		productGuarantees.setProductmodelid(productid);
		JSONObject json = new JSONObject();
		String code = "";
		if (bindingresults.hasErrors()) {
			System.out.println("has some errors");
			return "redirect:/";
		}
		String id = null;
		try {
			ProductGuarantee orgBean = (ProductGuarantee) productGuaranteeDao.getProductWarrantyDetailsByObject(productGuarantees);
			/*if (productGuarantees.getOrderId() != "" && productGuarantees.getOrderId() != null) {
				orgBean = (ProductGuarantee) productGuaranteeDao.getProductWarrantyDetailsByObject(productGuarantees);
			}*/
			String dummyId = null;
			if (orgBean != null) {
				dummyId = orgBean.getOrderId();
			}
			if (productGuarantees.getOrderId() == "" || productGuarantees.getOrderId() == null) {
				if (dummyId == null) {
					productGuarantees.setStatus("1");
					productGuaranteeDao.saveWarranty(productGuarantees);
					code = "success";
				} else {
					code = "exists";
				}
			} else {
				id = productGuarantees.getOrderId();
				if (id == dummyId || orgBean == null) {
					productGuaranteeDao.updateWarranty(productGuarantees);
					code = "updated";
				} else {
					code = "exists";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		json.put("status", code);
		return String.valueOf(json);
	}
	

	@RequestMapping(value = "/warrantylist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getProductWarrantyList() {
		LOGGER.debug("Calling warrantylist at controller");
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		List<Map<String, Object>> listOrderBeans = null;
		try {
			listOrderBeans = productGuaranteeDao.getWarrantyList();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				//jsonObj=om.writeValueAsString(listOrderBeans);
				json.put("warrantylist", listOrderBeans);
			} else {
				json.put("warrantylist", "NOT_FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}

	@RequestMapping(value = "/adminEditProfile", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getProfileInfo(@RequestBody User user) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling adminEditProfile at controller");
		int result = userDao.getProfileInfo(user);
		JSONObject json = new JSONObject();
		if (result == 1) {
			json.put("profileinfo", "Updated");
		} else
			json.put("profileinfo", "Not Updated");
		return String.valueOf(json);
	}

	@RequestMapping(value = "/adminChangePassword", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getPassword(@RequestBody User user) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling adminChangePassword at controller");
		int result = userDao.getPassword(user);
		JSONObject json = new JSONObject();
		if (result == 1) {
			json.put("password", "Updated");
		} else
			json.put("password", "Not Updated");
		return String.valueOf(json);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getDesignation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getDesignation() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getDesignation at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		List<Designation> listOrderBeans = userDao.getRoles();
		JSONObject json = new JSONObject();
		if (null != listOrderBeans) {
			json.put("designationlist", listOrderBeans);
		} else
			json.put("designationlist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(json);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getBranches", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAbheeBranches() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getBranches at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		List<AbheeBranch> listOrderBeans = abheeBranchDao.getAbheeBranchNames();
		JSONObject json = new JSONObject();
		if (null != listOrderBeans) {
			json.put("branchlist", listOrderBeans);
		} else
			json.put("branchlist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(json);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getReportToUsers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getReportToUsers() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getReportToUsers at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		List<User> listOrderBeans = userDao.getUserNames();
		JSONObject json = new JSONObject();
		if (null != listOrderBeans) {
			/*jsonObj=om.writeValueAsString(listOrderBeans);*/
			json.put("reporttouserslist", listOrderBeans);
		} else
			json.put("reporttouserslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(json);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/inActiveProducts", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getInActiveProducts() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling inActiveProducts at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		List<Category> listOrderBeans = categoryDao.getAllInActiveList();
		JSONObject json = new JSONObject();
		if (null != listOrderBeans)
			json.put("inactiveproductslist", listOrderBeans);
		else
			json.put("inactiveproductslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(json);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/inActiveCompanies", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getInActiveCompanies() throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling inActiveCompanies at controller");
		String code = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		List<Company> listOrderBeans = companyDao.getAllInActiveList();
		JSONObject json = new JSONObject();
		if (null != listOrderBeans)
			json.put("inactivecompaieslist", listOrderBeans);
		else
			json.put("inactivecompaieslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(json);
	}

	@RequestMapping(value = "/inActiveProductModels", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveProductModels(Product objdept, HttpServletRequest request)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling inActiveProductModels at controller");
		String code = null;
		List<Product> listOrderBeans = productDao.getAllInActiveList();
		JSONObject json = new JSONObject();
		if (null != listOrderBeans)
			json.put("inactiveproductmodelslist", listOrderBeans);
		else
			json.put("inactiveproductmodelslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(json);
	}

	@RequestMapping(value = "/inActiveCustomers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveCustomers(Customer objdept, HttpServletRequest request)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling inActiveCustomers at controller");
		String code = null;
		List<Customer> listOrderBeans = customerDao.getCustomerInActiveList();
		JSONObject jsonObj = new JSONObject();
		if (null != listOrderBeans)
			jsonObj.put("inactivecustomerslist", listOrderBeans);
		else
			jsonObj.put("inactivecustomerslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(jsonObj);
	}

	@RequestMapping(value = "/inActiveUsers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveTasks(User objdept, HttpServletRequest request)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling inActiveUsers at controller");
		String code = null;
		List<User> listOrderBeans = userService.getInActiveList();
		JSONObject jsonObj = new JSONObject();
		if (null != listOrderBeans)
			jsonObj.put("inactiveuserslist", listOrderBeans);
		else
			jsonObj.put("inactiveuserslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(jsonObj);
	}

	@RequestMapping(value = "/inActiveTasks", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveTasks(AbheeTask objorg, HttpServletRequest request)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling inActiveTasks at controller");
		String code = null;
		List<Map<String, Object>> listOrderBeans = abheeTaskDao.getInActiveTasksList();
		JSONObject jsonObj = new JSONObject();
		if (null != listOrderBeans)
			jsonObj.put("inactivetaskslist", listOrderBeans);
		else
			jsonObj.put("inactivetaskslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(jsonObj);
	}

	@RequestMapping(value = "/inActiveProductWarranty", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getAllActiveOrInactiveWarrantyList(ProductGuarantee productGuarantee, HttpServletRequest request)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling inActiveProductWarranty at controller");
		String code = null;
		List<Map<String, Object>> listOrderBeans = productGuaranteeDao.getAllInActiveList();
		JSONObject jsonObj = new JSONObject();
		if (null != listOrderBeans)
			jsonObj.put("inactiveproductwarrantylist", listOrderBeans);
		else
			jsonObj.put("inactiveproductwarrantylist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(jsonObj);
	}

	@PostMapping(value = "/deleteProduct", consumes = "application/json", produces = "application/json")
	public String deleteProduct(@RequestBody Category cate, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling deleteProduct at controller");
		// int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		categoryDao.deactiveCategory(cate.getStatus(), cate.getId());
		JSONObject jsonObj = new JSONObject();
		if (!cate.getStatus().equals("0")) {
			jsonObj.put("status", "Not Deactivated");
		} else
			jsonObj.put("status", "Deactivated");
		return String.valueOf(jsonObj);
	}

	@PostMapping(value = "/deleteCompany", consumes = "application/json", produces = "application/json")
	public String deleteCompany(@RequestBody Company com, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling deleteCompany at controller");
		// int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		companyDao.deactiveCompany(com.getStatus(), com.getId());
		JSONObject jsonObj = new JSONObject();
		if (!com.getStatus().equals("0")) {
			jsonObj.put("status", "Not Deactivated");
		} else
			jsonObj.put("status", "Deactivated");
		return String.valueOf(jsonObj);
	}

	@PostMapping(value = "/deleteProductModel", consumes = "application/json", produces = "application/json")
	public String deleteProductModel(@RequestBody Product pro, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling deleteProductModel at controller");
		// int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		productDao.deactiveProductModel(pro.getStatus(), pro.getId());
		JSONObject jsonObj = new JSONObject();
		if (!pro.getStatus().equals("0")) {
			jsonObj.put("status", "Not Deactivated");
		} else
			jsonObj.put("status", "Deactivated");
		return String.valueOf(jsonObj);
	}

	@PostMapping(value = "/deleteCustomer", consumes = "application/json", produces = "application/json")
	public String deleteCustomer(@RequestBody Customer cust, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling deleteCustomer at controller");
		// int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		customerDao.deactiveCustomer(cust.getStatus(), cust.getId());
		JSONObject jsonObj = new JSONObject();
		if (!cust.getStatus().equals("0")) {
			jsonObj.put("status", "Not Deactivated");
		} else
			jsonObj.put("status", "Deactivated");
		return String.valueOf(jsonObj);
	}

	@PostMapping(value = "/deleteUser", consumes = "application/json", produces = "application/json")
	public String deleteUser(@RequestBody User user, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling deleteUser at controller");
		// int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		userDao.deactiveUser(user.getStatus(), user.getId());
		JSONObject jsonObj = new JSONObject();
		if (!user.getStatus().equals("0")) {
			jsonObj.put("status", "Not Deactivated");
		} else
			jsonObj.put("status", "Deactivated");
		return String.valueOf(jsonObj);
	}

	@PostMapping(value = "/deleteServiceRequest", consumes = "application/json", produces = "application/json")
	public String deleteTask(@RequestBody AbheeTask task, HttpServletRequest request) throws JSONException {
		LOGGER.debug("Calling deleteServiceRequest at controller");
		// int result = categoryDao.deactiveCategory(cate.getStatus(),cate.getId());
		abheeTaskDao.deactiveTask(task.getStatus(), task.getId());
		JSONObject jsonObj = new JSONObject();
		if (!task.getStatus().equals("0")) {
			jsonObj.put("status", "Not Deactivated");
		} else
			jsonObj.put("status", "Deactivated");
		return String.valueOf(jsonObj);
	}

	@PostMapping(value = "/deleteProductWarranty", consumes = "application/json", produces = "application/json")
	public String deleteProductWarranty(@RequestBody ProductGuarantee pg, HttpServletRequest request)
			throws JSONException {
		LOGGER.debug("Calling deleteProductWarranty at controller");
		productGuaranteeDao.deactiveProductWarranty(pg.getStatus(), pg.getOrderId());
		JSONObject jsonObj = new JSONObject();
		if (!pg.getStatus().equals("0")) {
			jsonObj.put("status", "Not Deactivated");
		} else
			jsonObj.put("status", "Deactivated");
		return String.valueOf(jsonObj);
	}

	@RequestMapping(value = "/getStatusList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getStatusList(@RequestBody AbheeTaskStatus abheeTaskStatus, HttpServletRequest request)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getStatusList at controller");
		String code = null;
		List<AbheeTaskStatus> listOrderBeans = abheeTaskStatusDao.getTaskStatusList();
		JSONObject jsonObj = new JSONObject();
		if (null != listOrderBeans)
			jsonObj.put("statuslist", listOrderBeans);
		else
			jsonObj.put("statuslist", "NOT_FOUND");
		System.out.println("rest call user status:  " + code);
		return String.valueOf(jsonObj);
	}

	/*
	 * @RequestMapping(value = "/editEmailAndMobileno",method=RequestMethod.POST,
	 * consumes = "application/json", produces = "application/json") public String
	 * getMobileno(@RequestBody Customer customer ,HttpServletRequest request) {
	 * LOGGER.debug("Calling editEmailAndMobileno at controller"); JSONObject json
	 * =new JSONObject(); HashMap<String,String> hm =new HashMap<String,String>();
	 * hm=VerifyingAndSendOTP(customer); if(hm.get("otpnumber")!=null) {
	 * json.put("status", hm.get("statuscode")); json.put("otp",
	 * hm.get("otpnumber")); } else { json.put("status", hm.get("statuscode")); }
	 * return String.valueOf(json); }
	 */

	@RequestMapping(value = "/getEmailandMobileById", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getemailandmobileById(@RequestBody Customer customer) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getEmailandMobileById at controller");
		List<Customer> listOrderBeans = customerDao.getEmailandMobileByCustomerId(customer);
		JSONObject json = new JSONObject();
		if (null != listOrderBeans) {
			json.put("em", listOrderBeans);
		} else
			json.put("em", "NOT_FOUND");
		return String.valueOf(json);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value="/getRequestTime", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getRequestTime() throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getRequestTime at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<AbheeRequestTime> listOrderBeans = requestTimeDao.getRequestTimes();
		JSONObject json =new JSONObject();
		if(null != listOrderBeans)
		{
			json.put("getRequestTimelist", listOrderBeans);
		}
		else
			json.put("getRequestTimelist", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/getCustomerIds", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")  
	public String  getCustomerIds(Customer customer) throws JsonProcessingException, JSONException 
	{
		LOGGER.debug("Calling getCustomerIds at controller");
		String code =null;
		HashMap<String,String> hm =new HashMap<String,String>();
		List<Customer> listOrderBeans = customerDao.getCustomerId();
		JSONObject json =new JSONObject();
		
		if(null != listOrderBeans)
		{	
				json.put("CustomerIds", listOrderBeans);
		}
		else
			
			json.put("CustomerIds", "NOT_FOUND");
			System.out.println("rest call user status:  "+code);
			return String.valueOf(json);
	}
	
	@RequestMapping(value = "/getNotificationByTechnicianId", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getNotificationByCustomerId(@RequestBody TaskHistoryLogs history) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getEmailandMobileById at controller");
		JSONObject json = new JSONObject();
		
		 if(history.getAssignto() != null){
			List<TaskHistoryLogs> listOrderBeans1 = taskHistoryDao.getNotificationByTechnicianId(history);
			taskHistoryDao.UpdateNotificationByTechnicianId(history);
			if (null != listOrderBeans1) {
				json.put("History1", listOrderBeans1);
			} else
				json.put("History1", "NOT_FOUND");
		}
		return String.valueOf(json);
		}
	
	@RequestMapping(value = "/changeKstatusByTaskno", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String changeKstatusByTaskno(@RequestBody AbheeTask task) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling changeKstatusByTaskno at controller");
		//List<TaskHistoryLogs> listOrderBeans = taskHistoryDao.getNotificationByCustomerId(history);
		String msg="CUSTOMER NOT AVAILABLE";
		JSONObject json = new JSONObject();
		if (task.getKstatus().equals(msg)) {
			taskHistoryDao.UpdateKstatusbyTaskNo(task);
			json.put("Kstatus", "Updated");
		}else {
			json.put("Kstatus", "Alredy Exist or Not Acceptable Request");
		}
		return String.valueOf(json);
		}

	@RequestMapping(value = "/getNotificationsListByCustomerId", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getNotificationsListByCustomerId(@RequestBody TaskHistoryLogs history) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getNotificationsListByCustomerId at controller");
		List<String> listOrderBeans = abheeTaskDao.getTaskNoByCustomerIds(history);
		List<HashMap<String, List<Map<String, Object>>>> listOrderBeans1=new ArrayList<HashMap<String, List<Map<String, Object>>>>();
		List<Map<String, Object>> listOrderBeans2=new ArrayList<Map<String, Object>>();
		for(int i=0;i<listOrderBeans.size();i++) {
			HashMap<String, List<Map<String, Object>>> hm = new HashMap<String, List<Map<String, Object>>>();
			listOrderBeans2=taskHistoryDao.getNotificationsListByCustomerId(history.getAssignby(),listOrderBeans.get(i).toString());
		System.out.println(listOrderBeans2);
		hm.put(""+i, listOrderBeans2);
		listOrderBeans1.add(hm);
		}
		JSONObject json = new JSONObject();
		String message=" ";
		List<Map<String, Object>> listOrderBeans3 = srequestDao.getquotationList1(history);
		if ( listOrderBeans1.size()!=0) {
			message="Service_List";
			json.put("Service", listOrderBeans1);
		} 
		if( listOrderBeans3.size()!=0) {
			message =message+",Quotation_List";
			json.put("Quotation", listOrderBeans3);
		}
		if(message.length()==0) {
			json.put("status", "Not_Found");
		}
		else {
			json.put("status",message);
		}
		
		
		return String.valueOf(json);
		}
	@RequestMapping(value = "/getServiceDetailsByTaskno", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getServiceDetailsByTaskno(@RequestBody AbheeTask task) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getServiceDetailsByTaskno at controller");
		List<TaskHistoryLogs> listOrderBeans = taskHistoryDao.getNotificationsListByCustomerId1(task.getTaskno(),task.getCustomerId());
		System.out.println(listOrderBeans);
		List<AbheeTask> listOrderBeans1 = taskHistoryDao.getServiceDetailsByTaskno(task);
		System.out.println(listOrderBeans1);
		JSONObject json = new JSONObject();
		if (null != listOrderBeans) {
			json.put("NotificationList", listOrderBeans);
			json.put("ServiceDtailsList", listOrderBeans1);
			json.put("NotificationStatus","Found");
		} else
			json.put("NotificationStatus", "NOT_FOUND");
		return String.valueOf(json);
		}
	@RequestMapping(value = "/getQuotationNotificationByCustomerId", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getQuotationNotificationByCustomerId(@RequestBody SalesRequest history) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getQuotationNotificationByCustomerId at controller");
		JSONObject json = new JSONObject();
		String message="";
		if(history.getCustomerid() != null) {
			
			List<TaskHistoryLogs> listOrderBeans = taskHistoryDao.getNotificationByCustomerId(history);
			taskHistoryDao.UpdateNotificationByCustomerId(history);
			
			List<SalesRequest> listOrderBeans1 = customerDao.getQuotationNotificationByCustomerIds(history);
			customerDao.UpdateQuotationNotificationByCustomerIds(history);
			
		
		if ( listOrderBeans1.size()!=0) {
			message="Quotation_List";
			json.put("Quotation", listOrderBeans1);
		} 
		if( listOrderBeans.size()!=0) {
			message =message+",Service_List";
			json.put("Service", listOrderBeans);
		}
		if(message.length()==0) {
			json.put("status", "Not_Found");
		}
		else {
			json.put("status",message);
		}
		}
		return String.valueOf(json);
	}
	@RequestMapping(value = "/getNotificationByTaskno", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getNotificationByTaskno(@RequestBody AbheeTask taskno) throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getproducts at controller");
		List<Map<String, Object>> listOrderBeans = abheeTaskDao.getNotificationByTaskno(taskno);
		JSONObject json = new JSONObject();
		
		/*ObjectMapper om = new ObjectMapper();
		String jsonObj = null;*/
		if (null != listOrderBeans) {
			json.put("NotificationList", listOrderBeans);
			
			//jsonObj=om.writeValueAsString(listOrderBeans);
		} else
			json.put("NotificationList", "NOT_FOUND");
		return String.valueOf(json);
	}

	@RequestMapping(value = "/getTechnicianNotificationByUserId", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getTechnicianNotificationByUserId(@RequestBody User users,HttpServletRequest request)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getTechnicianNotificationByUserId at controller");
		List<Map<String, Object>> listOrderBeans = userDao.getTechnicianNotificationByUserId(users.getUserId());
		JSONObject json = new JSONObject();
		/*String jsonObj = null;
		ObjectMapper om = new ObjectMapper();*/
		if (null != listOrderBeans) 
		{
			//jsonObj=om.writeValueAsString(listOrderBeans);
			json.put("TechnicianNotificationList", listOrderBeans);
		}
		else
			json.put("TechnicianNotificationList", "NOT_FOUND");
			return String.valueOf(json);
	}
	
	@RequestMapping(value = "/getTechnicianNotificationList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getTechnicianNotificationList(@RequestBody AbheeTask task,HttpServletRequest request)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getTechnicianNotificationList at controller");
		List<Map<String, Object>> listOrderBeans1 = abheeTaskDao.getCustomerResponseByTaskno(task.getTaskno());
		List<Map<String, Object>> listOrderBeans2 = abheeTaskDao.getAdminResponseByTaskno(task.getTaskno());
		JSONObject json = new JSONObject();
		/*String jsonObj1 = null;
		String jsonObj2 = null;
		String jsonObj3=null;
		ObjectMapper om = new ObjectMapper();
		ObjectMapper objectMapper = new ObjectMapper();*/
		if (null != listOrderBeans1 && null != listOrderBeans2) 
		{
			//jsonObj1=om.writeValueAsString(listOrderBeans1);
			/*jsonObj1=om.writerWithDefaultPrettyPrinter().writeValueAsString(listOrderBeans1);
			jsonObj1=om.writerWithDefaultPrettyPrinter().writeValueAsString(listOrderBeans2);*/
			//jsonObj2=objectMapper.writeValueAsString(listOrderBeans2);
			//jsonObj3=jsonObj1.concat(jsonObj2);
			/*request.setAttribute("list1", listOrderBeans1);
			request.setAttribute("list2", listOrderBeans2);*/
			json.put("TechnicianNotificationList1", listOrderBeans1);
			json.put("TechnicianNotificationList2",listOrderBeans2);
			//JSON.parse(jsonObj1);
			//jsonObj.put("TechnicianNotificationList", listOrderBeans);
		}
		else
			json.put("TechnicianNotificationList", "NOT_FOUND");
	return String.valueOf(json);
			
	}
	
	@RequestMapping(value = "/getquotationByRequestNo", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getquotationByRequestNo(@RequestBody SalesRequest request)
			throws JsonProcessingException, JSONException {
		LOGGER.debug("Calling getquotationByRequestNo at controller");
		JSONObject json = new JSONObject();
		List<Map<String, Object>> listOrderBeans = null;
		List<Map<String, Object>> listOrderBeans1 = null, listOrderBeans2 = null;

		try {
			listOrderBeans = srequestDao.getSalesRequestListByRequestNo(request);
			listOrderBeans1 = srequestDao.getAdminResponseByRequestNo(request);
			listOrderBeans2 = srequestDao.getAdminResponseListByRequestNoWhenStatusZero(request);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {

				listOrderBeans1.get(0).get("status");
				System.out.println("Status Code:" + listOrderBeans1.get(0).get("status"));

				if (!listOrderBeans1.get(0).get("status").equals(0)) {
					json.put("quotationslist", listOrderBeans);
					json.put("AdminResponseList", listOrderBeans1);
				}

				else {
					listOrderBeans1.get(0).get("status");
					System.out.println("Status Code:" + listOrderBeans1.get(0).get("status"));

					json.put("quotationslist", listOrderBeans);

					json.put("AdminResponseList", listOrderBeans2);
				}
			} else {
				json.put("quotationslist", "NOT_FOUND");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(json);
	}
}