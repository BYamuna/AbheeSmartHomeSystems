package com.charvikent.abheeSmartHomeSystems.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.ProductGuarantee;



@Repository
@Transactional
public class ProductGuaranteeDao 
{
	@PersistenceContext
    private EntityManager em;
	
	public void saveCategory(ProductGuarantee productGuarantee ) 
	{
		em.persist(productGuarantee);

	}
	
}
