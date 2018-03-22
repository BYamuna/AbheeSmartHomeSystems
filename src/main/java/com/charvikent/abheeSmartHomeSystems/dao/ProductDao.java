package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.Product;


@Repository
@Transactional
public class ProductDao 
{
	@PersistenceContext
    private EntityManager entityManager;


	public void saveProduct(Product product) 
	{
		entityManager.persist(product);

	}
	@SuppressWarnings("unchecked")
	public List<Product> getProductNames()
	 {

		return entityManager.createQuery("  from Product where status='1'").getResultList();

	 }
	public Product getProductNameById(Product pro) 
	{
			
			@SuppressWarnings("unchecked")
			List<Product> proList =(List<Product>) entityManager.createQuery("  FROM Product where name =:custName ").setParameter("custName",pro.getName()).getResultList();
			if(proList.size() > 0)
				return proList.get(0);
			return null;
			
	}
	public void UpdateProduct(Product pro)
	{
		Product uc= (Product)entityManager.find(Product.class ,pro.getId());
		uc.setName(pro.getName());
		uc.setDescription(pro.getDescription());
		entityManager.flush();
	}
	public boolean deleteProduct(Integer id, String status) 
	{
		Boolean delete=false;
		try{
			
			Product pro= (Product)entityManager.find(Product.class ,id);
			   pro.setStatus(status);
			   entityManager.merge(pro);
			if(!status.equals(pro.getStatus()))
			{
				delete=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllInActiveList() 
	{
		
		return entityManager.createQuery("from Product where status='0'").getResultList();
	}
}
