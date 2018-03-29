package com.charvikent.abheeSmartHomeSystems.dao;



import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.Category;

@Repository
@Transactional
public class CategoryDao {

	@PersistenceContext
    private EntityManager entityManager;


	public void saveCategory(Category category ) {
		entityManager.persist(category);

	}

	@SuppressWarnings("unchecked")
	public List<Category> getCategoryNames()
	 {

		return entityManager.createQuery("  from Category where status='1'").getResultList();

	 }
	public Category getCategoryNameById(Category cate) {
		
		@SuppressWarnings("unchecked")
		List<Category> cateList =(List<Category>) entityManager.createQuery("SELECT cate FROM Category cate where category =:custName ").setParameter("custName",cate.getCategory()).getResultList();
		if(cateList.size() > 0)
			return cateList.get(0);
		return null;
		
	}
	
	
	public void UpdateCategory(Category cate)
	{
		Category uc= (Category)entityManager.find(Category.class ,cate.getId());
		uc.setCategory(cate.getCategory());
		entityManager.flush();
	}

	
	
	public boolean deleteCategory(Integer id, String status) {
		Boolean delete=false;
		try{
			
			Category cate= (Category)entityManager.find(Category.class ,id);
			   cate.setStatus(status);
			   entityManager.merge(cate);
			if(!status.equals(cate.getStatus()))
			{
				delete=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllInActiveList() {
		return entityManager.createQuery("  from Category where status='0'").getResultList();
	}
	
	
	public Map<Integer, String> getCategorymap()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Category> rolesList= getCategoryNames();
		for(Category bean: rolesList){
			rolesMap.put(bean.getId(), bean.getCategory());
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;
				
		
	}
}
