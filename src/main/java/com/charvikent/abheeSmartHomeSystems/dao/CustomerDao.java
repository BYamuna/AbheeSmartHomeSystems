package com.charvikent.abheeSmartHomeSystems.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import org.hibernate.Query


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.config.KptsUtil;
import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.model.AbheeCustomerTypes;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.User;


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
	
	/*@Autowired
    private JdbcTemplate jdbcTemplate;
*/
	
	
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
	
	String hql =" from Customer  where (mobilenumber ='"+customer.getMobilenumber()+"') and password='"+customer.getPassword()+"'";
	Query query =entityManager.createQuery(hql);
	
	List<Customer>usersList =query.getResultList();
	if(usersList.isEmpty())
           return null;
           else
	return usersList.get(0);
	
}

@SuppressWarnings("unchecked")
public Customer validateCustomer(String loginid, String password) {
	
	String hql =" from Customer  where enabled='1'and mobilenumber ='"+loginid+"' and password='"+password +"'";
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
	//uc.setBranchId(user.getBranchId());
	//uc.setBranchName(user.getBranchName());
	//uc.setCustomerId(user.getCustomerId());
	//uc.setDesignation(user.getDesignation());
	//uc.setEnabled(user.getEnabled());
	uc.setFirstname(user.getFirstname());
	uc.setLastname(user.getLastname());
	//uc.setStatus(user.getStatus());
	uc.setPassword(user.getPassword());
	
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
	
	String hql ="from Customer where mobileno='"+custreg.getMobilenumber()+"'";
	
	
List<Customer> custlist =	entityManager.createQuery(hql).getResultList();
    
if(custlist.size()>0)
return custlist.get(0);
else
return null;
	
}

@SuppressWarnings("unchecked")
public Customer checkCustomerExistOrNotbyMobile(String Mobile)
{
	
	String hql ="from Customer where mobilenumber='"+Mobile+"'";
	
	
List<Customer> custlist =	entityManager.createQuery(hql).getResultList();
    
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
	return (List<Customer>)entityManager.createQuery("from Customer where enabled='0' order by updatedTime desc").getResultList();
	
}


public void updateCustomerProfile(Customer user) 
{
	Customer uc= (Customer)entityManager.find(Customer.class ,user.getId());
	
	uc.setMobilenumber(user.getMobilenumber());
	uc.setEmail(user.getEmail());
	uc.setAddress(user.getAddress());
	uc.setFirstname(user.getFirstname());
	uc.setLastname(user.getLastname());
	
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



}
