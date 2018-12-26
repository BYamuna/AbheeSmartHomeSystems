/*package com.charvikent.abheeSmartHomeSystems.dao;

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
public class AbheePaymentDao 
{
	@PersistenceContext private EntityManager entityManager;
	//@Autowired private JdbcTemplate jdbcTemplate;
	public Map<Integer, String> getPaymentMap() 
	{
		Map<Integer, String> PaymentMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<AbheePayment> paymentlist = getPayments();
		for( AbheePayment bean: paymentlist){
			PaymentMap.put(bean.getId(),bean.getPayment());
		}		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return PaymentMap;
	}
	@SuppressWarnings("unchecked")
	public List<AbheePayment> getPayments() 
	{
		return entityManager.createQuery(" from AbheePayment").getResultList();
	}
}
*/