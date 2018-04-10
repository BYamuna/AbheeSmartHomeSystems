package com.charvikent.abheeSmartHomeSystems.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.config.KptsUtil;
import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
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
	
	public Customer checkCustomerExistOrNotByEmail(String email) {

		String hql ="from Customer where  email ='"+email+"'";
		Query query =entityManager.createQuery(hql);

		List<Customer>usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
               else
		return usersList.get(0);
	}


public Customer checkuserExistOrNot(Customer customer) {
	
	String hql =" from Customer  where (mobilenumber ='"+customer.getMobilenumber()+"') and password='"+customer.getPassword()+"'";
	Query query =entityManager.createQuery(hql);
	
	List<Customer>usersList =query.getResultList();
	if(usersList.isEmpty())
           return null;
           else
	return usersList.get(0);
	
}

public Customer validateCustomer(String loginid, String password) {
	
	String hql =" from Customer  where mobilenumber ='"+loginid+"' and password='"+password+"'";
	Query query =entityManager.createQuery(hql);
	List<Customer>usersList =query.getResultList();
	if(usersList.isEmpty())
           return null;
           else
	return usersList.get(0);

	
}

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
	
	String hql ="from Customer where custID='"+custId+"'";
	
	
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
	  
	return (List<Customer>)entityManager.createQuery("from Customer where enabled='1'").getResultList();
	 
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
	return (List<Customer>)entityManager.createQuery("from Customer where enabled='0'").getResultList();
	
}

}
