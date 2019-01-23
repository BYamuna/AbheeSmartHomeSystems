package com.charvikent.abheeSmartHomeSystems.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*import java.util.Map;*/

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import org.hibernate.Query


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.config.KptsUtil;
import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.model.AbheeCustomerTypes;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;
/*import com.charvikent.abheeSmartHomeSystems.model.User;*/
import com.charvikent.abheeSmartHomeSystems.model.TaskHistoryLogs;


@Repository
@Transactional
public class CustomerDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	KptsUtil utilities;
	@Autowired
	SendSMS sendSMS;
	@Autowired private JdbcTemplate jdbcTemplate;
	
	public Customer findByUserName(String username) {
		
		String hql ="from Customer where mobilenumber='"+username+"'";
		
		
		
		Customer customer= (Customer) entityManager.createQuery(hql).getSingleResult();
		return customer;
	}
	
	@SuppressWarnings("unchecked")
	public Customer checkCustomerExistOrNotByEmail(String email) {

		String hql ="from Customer where  email ='"+email+"'";
		Query query =entityManager.createQuery(hql);

		List<Customer>usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
               else
		return usersList.get(0);
	}


@SuppressWarnings("unchecked")
public Customer checkuserExistOrNot(Customer customer) {
	String hql =" from Customer  where (mobilenumber='" +customer.getUsername()+"'or email='"+customer.getUsername()+"') and password='"+customer.getPassword()+"'";
	Query query =entityManager.createQuery(hql);
	
	List<Customer>usersList =query.getResultList();
	if(usersList.isEmpty())
           return null;
           else
	return usersList.get(0);
	
}
public List<Customer> checkMobileOrEmailExistOrNot(String username, String password) 
{
	String hql ="select c.mobilenumber,c.email from abhee_customer c where (mobilenumber='"+username+"'or email='"+username+"' or c.password='"+password+"')";
	RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class);
    return  this.jdbcTemplate.query(hql, rowMapper);
}
public List<Customer> checkcustomerExistOrNot(Customer customer) 
{
	String hql =" select * from  abhee_customer where (mobilenumber='" +customer.getUsername()+"'or email='"+customer.getUsername()+"') and password='"+customer.getPassword()+"'";
	RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class);
    return  this.jdbcTemplate.query(hql, rowMapper);
}

@SuppressWarnings("unchecked")
public Customer validateCustomer(String loginid, String password) {
	String hql =" from Customer  where enabled='1'and (mobilenumber ='"+loginid+"' or email = '"+loginid+"') and password='"+password +"'";
	Query query =entityManager.createQuery(hql);
	List<Customer>usersList =query.getResultList();
	if(usersList.isEmpty())
           return null;
           else
	return usersList.get(0);

	
}

@SuppressWarnings("unchecked")
public Customer getCustomerByObject(Customer customer) {
	String hql =" from Customer  where (mobilenumber ='"+customer.getMobilenumber()+"') or email='"+customer.getEmail()+"'";
	Query query =entityManager.createQuery(hql);
	
	List<Customer>usersList =query.getResultList();
	if(usersList.isEmpty())
           return null;
           else
	return usersList.get(0);
}

public void updateCustomer(Customer user) 
{
	Customer uc= (Customer)entityManager.find(Customer.class ,user.getId());
	
	uc.setMobilenumber(user.getMobilenumber());
	uc.setEmail(user.getEmail());
	uc.setAddress(user.getAddress());
	uc.setCommunicationaddress(user.getCommunicationaddress());
	//uc.setBranchId(user.getBranchId());
	//uc.setBranchName(user.getBranchName());
	//uc.setCustomerId(user.getCustomerId());
	//uc.setDesignation(user.getDesignation());
	//uc.setEnabled(user.getEnabled());
	uc.setFirstname(user.getFirstname());
	uc.setLastname(user.getLastname());
	//uc.setStatus(user.getStatus());
	//uc.setPassword(user.getPassword());
    uc.setPurchaseCustomer(user.isPurchaseCustomer());
	
	entityManager.flush();
	
}
public void saveAbheeCustomer(Customer cabheeCustomer ) {
	logger.info("Registering Customer");
	String randomNum = utilities.randNum();
	cabheeCustomer.setCustomerId(randomNum);
	
	entityManager.persist(cabheeCustomer);
	
	

}




public void Sendsms(String message,String mobileNumber)
{
	try {
		sendSMS.sendSMS(message, mobileNumber);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



@SuppressWarnings("unchecked")
public Customer findCustomerByCustId(String custId)
{
	
	String hql ="from Customer where customerId='"+custId+"'";
	
	
List<Customer> custlist =	entityManager.createQuery(hql).getResultList();
    
if(custlist.size()>0)
return custlist.get(0);
else
return null;
	
}



@SuppressWarnings("unchecked")
public Customer checkCustomerExistOrNot(Customer custreg)
{
	String hql ="from Customer where mobilenumber='"+custreg.getMobilenumber()+"'";	
	/*String hql ="from Customer where mobilenumber='"+custreg.getMobilenumber()+"' and email='"+custreg.getEmail()+"'";*/
	List<Customer> custlist =	entityManager.createQuery(hql).getResultList();   
	if(custlist.size()>0)
	return custlist.get(0);
	else
	return null;	
}

@SuppressWarnings("unchecked")
public Customer checkCustomerExistOrNotbyMobile(String Mobile)
{
	String hql ="from Customer where mobilenumber ='"+Mobile+"'";
	List<Customer> custlist =	entityManager.createQuery(hql).getResultList(); 
	if(custlist.size()>0)
	return custlist.get(0);
	else
	return null;	
}
@SuppressWarnings("unchecked")
public Customer checkCustomerExistOrNotbyMobileOrEmail(String Mobile,String email)
{
	String hql ="from Customer where (mobilenumber ='"+Mobile+"') or email='"+email+"'";
	List<Customer> custlist =	entityManager.createQuery(hql).getResultList(); 
	if(custlist.size()>0)
	return custlist.get(0);
	else
	return null;	
}
@SuppressWarnings("unchecked")
public Customer checkCustomerExistOrNotbyPassword(String Password)
{
	String hql ="from Customer where password='"+Password+"'";
	List<Customer> custlist =entityManager.createQuery(hql).getResultList(); 
	if(custlist.size()>0)
	return custlist.get(0);
	else
	return null;	
}

@SuppressWarnings("unchecked")
public Customer otpVeri(Customer cust)
{
	
	String hql ="from Customer where custID='"+cust.getMobilenumber()+"'";
	
	
List<Customer> custlist =	entityManager.createQuery(hql).getResultList();
    
if(custlist.size()>0)
return custlist.get(0);
else
return null;
	
}
@SuppressWarnings("unchecked")
public List<Customer> getAbheeCustomerNames()
 {
	List<Customer> listCustomer =new ArrayList<Customer>();
	//select c.id,c.createdTime,c.customerId,c.mobilenumber,c.updatedTime,c.BranchId,c.address,c.branchName,c.email,c.enabled,c.firstname,c.lastname,c.mobilenumber,c.status,c.registedredFromAndroid,ct.customerType,c.gst,c.purchaseCustomer
	
	String hql="select c.id,c.customerId,c.mobilenumber,c.BranchId,c.address,c.branchName,c.email,c.enabled,c.firstname,c.lastname,c.status,c.registedredFromAndroid,ct.customerType,c.gst,c.purchaseCustomer, c.customerType from Customer c, AbheeCustomerTypes ct where c.enabled='1' and c.customerType=ct.id";
	
	try{//String hql ="SELECT c FROM Customer c, AbheeCustomerTypes ct where c.enabled='1' and  c.customerType=ct.id";
	List<Object[]> rows = entityManager.createQuery(hql).getResultList();
	
	for (Object[] row : rows) {
		Customer customer =new Customer();
		
		customer.setId(Integer.parseInt(String.valueOf(row[0])));

		customer.setCustomerId((String) row[1]);
		customer.setMobilenumber((String) row[2]);
		customer.setBranchId((String) row[3]);
		customer.setAddress((String) row[4]);
		customer.setBranchName((String) row[5]);
		customer.setEmail((String) row[6]);
		customer.setEnabled((String) row[7]);
		customer.setFirstname((String) row[8]);
		customer.setLastname((String) row[9]);
		customer.setStatus((String) row[10]);
		customer.setRegistedredFromAndroid((String) row[11]);
		customer.setCustomerTypeName((String) row[12]);
		customer.setGst((String) row[13]);
		customer.setPurchaseCustomer((boolean) row[14]);
		customer.setCustomerType((String) row[15]);
	
		
		
		listCustomer.add(customer);

	}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//return (List<Customer>)entityManager.createQuery(hql).getResultList();
	
	 return listCustomer;
	  
	//return (List<Customer>)entityManager.createQuery("from Customer where enabled='1' order by updatedTime desc").getResultList();
	 
 }

@SuppressWarnings("unchecked")
public List<AbheeCustomerTypes> getCustomerTypes() {
	List<AbheeCustomerTypes> customerType = null;
	Criteria criteria=entityManager.unwrap(Session.class).createCriteria(AbheeCustomerTypes.class);
	//criteria.setProjection(Projections.property("customerType"));
	
	customerType =criteria.list();
	
	entityManager.close();
	return customerType;
	
}

public String getOPTByMobileno(String mobile)
{
	
	String hql ="from a.otp Customer  a where a.mobileno='"+mobile+"'";
	
	
String otpStr =	(String) entityManager.createQuery(hql).getResultList().get(0);
    

return otpStr;
	
}

public boolean deleteCustomer(Integer id, String status) {
	Boolean delete=false;
	try{

		Customer design= entityManager.find(Customer.class ,id);
		   design.setEnabled(status);
		   entityManager.merge(design);
		if(!status.equals(design.getEnabled()))
		{
			delete=true;
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return delete;
}

@SuppressWarnings("unchecked")
public List<Customer> getCustomerInActiveList() 
{
	List<Customer> listCustomer =new ArrayList<Customer>();
	//select c.id,c.createdTime,c.customerId,c.mobilenumber,c.updatedTime,c.BranchId,c.address,c.branchName,c.email,c.enabled,c.firstname,c.lastname,c.mobilenumber,c.status,c.registedredFromAndroid,ct.customerType,c.gst,c.purchaseCustomer
	
	String hql="select c.id,c.customerId,c.mobilenumber,c.BranchId,c.address,c.branchName,c.email,c.enabled,c.firstname,c.lastname,c.status,c.registedredFromAndroid,ct.customerType,c.gst,c.purchaseCustomer, c.customerType from Customer c, AbheeCustomerTypes ct where c.enabled='0' and c.customerType=ct.id";
	
	try{//String hql ="SELECT c FROM Customer c, AbheeCustomerTypes ct where c.enabled='1' and  c.customerType=ct.id";
	List<Object[]> rows = entityManager.createQuery(hql).getResultList();
	
	for (Object[] row : rows) {
		Customer customer =new Customer();
		
		customer.setId(Integer.parseInt(String.valueOf(row[0])));

		customer.setCustomerId((String) row[1]);
		customer.setMobilenumber((String) row[2]);
		customer.setBranchId((String) row[3]);
		customer.setAddress((String) row[4]);
		customer.setBranchName((String) row[5]);
		customer.setEmail((String) row[6]);
		customer.setEnabled((String) row[7]);
		customer.setFirstname((String) row[8]);
		customer.setLastname((String) row[9]);
		customer.setStatus((String) row[10]);
		customer.setRegistedredFromAndroid((String) row[11]);
		customer.setCustomerTypeName((String) row[12]);
		customer.setGst((String) row[13]);
		customer.setPurchaseCustomer((boolean) row[14]);
		customer.setCustomerType((String) row[15]);
	
		
		
		listCustomer.add(customer);

	}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//return (List<Customer>)entityManager.createQuery(hql).getResultList();
	
	 return listCustomer;

}


public void updateCustomerProfile(Customer user) 
{
	Customer uc= (Customer)entityManager.find(Customer.class ,user.getId());
	
	uc.setMobilenumber(user.getMobilenumber());
	uc.setEmail(user.getEmail());
	uc.setAddress(user.getAddress());
	uc.setFirstname(user.getFirstname());
	uc.setLastname(user.getLastname());
	/*uc.setCustomerId(user.getCustomerId());*/
	entityManager.flush();
	
}

public void updateCustomerProfilepassword(Customer customer) {
Customer uc= (Customer)entityManager.find(Customer.class ,customer.getId());
	
	uc.setPassword(customer.getPassword());
	
	entityManager.flush();
}

public void updateCustomerProfileEmail(Customer customer) {
	Customer uc= (Customer)entityManager.find(Customer.class ,customer.getId());
	uc.setEmail(customer.getEmail());
	System.out.println(uc);
	entityManager.flush();
}

public void updateCustomerProfileMobileNo(Customer customer) {
	Customer uc= (Customer)entityManager.find(Customer.class ,customer.getId());
	uc.setMobilenumber(customer.getMobilenumber());
	entityManager.flush();
	
}

@SuppressWarnings("unchecked")
public Customer checkProfileEmailExistsOrNot(Customer customer) {
	
	String hql ="from Customer where  email ='"+customer.getEmail()+"' and id <>'"+customer.getId()+"'";
	Query query =entityManager.createQuery(hql);

	List<Customer>ProfileCustomersList =query.getResultList();
	if(ProfileCustomersList.isEmpty())
           return null;
           else
	return ProfileCustomersList.get(0);
}

@SuppressWarnings("unchecked")
public Customer checkProfileMobileNoExistsOrNot(Customer customer) {
	String hql ="from Customer where  mobilenumber ='"+customer.getMobilenumber()+"' and id <>'"+customer.getId()+"'";
	Query query =entityManager.createQuery(hql);

	List<Customer>ProfileCustomersList =query.getResultList();
	if(ProfileCustomersList.isEmpty())
           return null;
           else
	return ProfileCustomersList.get(0);
}

@SuppressWarnings("unchecked")
public Customer checkCustomerExistOrNotbyMobileOnEdit(String custMobile, String editFieldsId) {
	
	
	String hql ="from Customer where id <>'"+editFieldsId+"' and  mobilenumber='"+custMobile+"'";
	
	
	List<Customer> custlist =	entityManager.createQuery(hql).getResultList();
	    
	if(custlist.size()>0)
	return custlist.get(0);
	else
	return null;
}


@SuppressWarnings("unchecked")
public Customer checkCustomerExistOrNotByEmailOnEdit(String custEmail, String editFieldsId) {
	String hql ="from Customer where id <>'"+editFieldsId+"'and   email ='"+custEmail+"'";
	Query query =entityManager.createQuery(hql);

	List<Customer>usersList =query.getResultList();
	if(usersList.isEmpty())
           return null;
           else
	return usersList.get(0);
}



	public int getProfileInfo(Customer customer) 
	{
		String sql =" update abhee_customer set firstname ='"+customer.getFirstname()+"',lastname='"+customer.getLastname()+"',address='"+customer.getAddress()+"',mobilenumber ='"+customer.getMobilenumber()+"', email ='"+customer.getEmail()+"'where id='"+customer.getId()+"' order by updated_time desc";	
		int  result = jdbcTemplate.update(sql);
		return result; 
		
	}
	
	public int getPassword(Customer customer) 
	{
		String sql =" update abhee_customer set password ='"+customer.getPassword()+"' where id='"+customer.getId()+"' order by updated_time desc";	
		int  result = jdbcTemplate.update(sql);
		return result; 
		
	}
	public void deactiveCustomer(String status, Integer id) 
	{
		String sql="update abhee_customer set status='"+status+"'where id='"+id+"'";
		jdbcTemplate.execute(sql);	
	}
	
	public int getMobileno(Customer customer) 
	{
		String sql =" update abhee_customer set mobilenumber ='"+customer.getMobilenumber()+"' where id='"+customer.getId()+"' order by updated_time desc";	
		int  result = jdbcTemplate.update(sql);
		return result; 	
	}
	public int getEmail(Customer customer) 
	{
		String sql =" update abhee_customer set email ='"+customer.getEmail()+"' where id='"+customer.getId()+"' order by updated_time desc";	
		int  result = jdbcTemplate.update(sql);
		return result; 
		
	}
	
	public List<Customer> getEmailandMobileByCustomerId(Customer customer){
		String hql ="select c.email,c.mobilenumber  from abhee_customer c where c.customer_id='"+customer.getCustomerId()+"'";
		RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class);
	    return  this.jdbcTemplate.query(hql, rowMapper);
	}
	public List<Customer> getCustomerId()
	{
		String hql="select * from abhee_customer where purchase_customer=true";
		RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class);
	    return  this.jdbcTemplate.query(hql, rowMapper);
	}
	
	
	/*Queries for Push Notifications in Customer Module*/
	
	public List<SalesRequest> getNotificationByCustomerIds(String history){
		System.out.println(history);
		String hql="select sr.id,sr.notes,sr.quotation_documents,sr.request_type,sr.cstatus ,sr.customerid,sr.salesrequestnumber from abhee_sales_request sr  where customerid='"+history+"' and cstatus='1'";
		RowMapper<SalesRequest> rowMapper = new BeanPropertyRowMapper<SalesRequest>(SalesRequest.class);
	    System.out.println(hql);
		return  this.jdbcTemplate.query(hql, rowMapper);
	}
	public List<TaskHistoryLogs> getServiceNotificationByCustomerIds(String history){
		System.out.println(history);
		String hql="select t.id, t.taskno,t.add_comment,t.cstatus,st.servicetypename as servicetype,ats.name as kstatus,t.assignto,t.request_type ,t.assignby from task_history_logs t,abheeservicetype st ,abheetaskstatus ats,abheeusers u  where  cstatus='1' and t.service_type=st.id and t.kstatus=ats.id  and t.assignto=u.id  and t.assignby='"+history+"'";
		RowMapper<TaskHistoryLogs> rowMapper = new BeanPropertyRowMapper<TaskHistoryLogs>(TaskHistoryLogs.class);
	    System.out.println(hql);
		return  this.jdbcTemplate.query(hql, rowMapper);
	}
	
	public void UpdateQuotationNotificationByCustomerId(String history){
		String hql ="update abhee_sales_request t set t.cstatus='0' where t.id="+history+" and t.cstatus='1'";
		System.out.println(hql);
		jdbcTemplate.execute(hql);
		
	}
	public void UpdateServiceNotificationByCustomerId(String history){
		String hql ="update task_history_logs t set t.cstatus='0' where t.taskno="+history+"and t.cstatus='1'";
		System.out.println(hql);
		jdbcTemplate.execute(hql);
		
	}
	/*Queries for Push Notifications in Customer Module*/
	
	
	
	
	/*Queries for  API's on push notification in customer*/	
	public List<SalesRequest> getQuotationNotificationByCustomerIds(SalesRequest history){
		String hql="select sr.id,sr.notes,sr.request_type,sr.customerid,sr.salesrequestnumber from abhee_sales_request sr  where sr.customerid='"+history.getCustomerid()+"' and sr.quotationstatus='1'";
		RowMapper<SalesRequest> rowMapper = new BeanPropertyRowMapper<SalesRequest>(SalesRequest.class);
	    System.out.println(hql);
		return  this.jdbcTemplate.query(hql, rowMapper);
	}
	public void UpdateQuotationNotificationByCustomerIds(SalesRequest history){
		String hql ="update abhee_sales_request t set t.quotationstatus='0' where t.customerid='"+history.getCustomerid()+"' and t.quotationstatus='1'";
		System.out.println(hql);
		jdbcTemplate.execute(hql);
		
	}
	/*Queries for  API's on push notification in customer*/	
}
