package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.Customer;

@Repository
@Transactional
public class CustomerDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public void savecustomer(Customer customer)
	{
		entityManager.persist(customer);
	}

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
public Customer checkCustomerExistOrNotbyMobile(String custMobile) {
		
		String hql ="from User where  mobilenumber ='"+custMobile+"'";
		Query query =entityManager.createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Customer>usersList =query.getResultList();
		if(usersList.isEmpty())
               return null;
               else
		return usersList.get(0);
		
		
	}

public Customer checkuserExistOrNot(Customer customer) {
	
	String hql =" from User  where (mobilenumber ='"+customer.getMobilenumber()+"') and password='"+customer.getPassword()+"'";
	Query query =entityManager.createQuery(hql);
	
	List<Customer>usersList =query.getResultList();
	if(usersList.isEmpty())
           return null;
           else
	return usersList.get(0);
	
}

}
