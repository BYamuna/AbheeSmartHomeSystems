package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheePayment;

@Repository
@Transactional
public class AbheepaymentDao {
	@PersistenceContext
    private EntityManager entityManager;
	
	public Map<Integer, String> getPaymentmap()
	{
		Map<Integer, String> paymentMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<AbheePayment> paymentlist= getPayments();
		System.out.println(paymentlist);
		for(AbheePayment bean: paymentlist){
			paymentMap.put(bean.getId(), bean.getPayment());
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return paymentMap;
				
		
	}

	@SuppressWarnings("unchecked")
	private List<AbheePayment> getPayments() 
	{
		
		return entityManager.createQuery("from AbheePayment").getResultList();
	}

}
