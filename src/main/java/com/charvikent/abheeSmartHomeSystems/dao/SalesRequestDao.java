package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;

@Repository
@Transactional
public class SalesRequestDao 
{
	@PersistenceContext
    private EntityManager entityManager;
	
	public void saveRequest(SalesRequest salesrequest) 
	{
		entityManager.persist(salesrequest);

	}
	@SuppressWarnings("unchecked")
	public  Boolean checkSalesrequestExistsorNotByEmailAndModelNo(SalesRequest salesrequest)
	{
		String hql="from SalesRequest where modelnumber='"+salesrequest.getModelnumber()+"'"+ "and "+"email='"+salesrequest.getEmail()+"'";
		List<SalesRequest> list=entityManager.createQuery(hql).getResultList();
		if(list.size()>0)
			return true;
		else
			return false;	
	}
}
