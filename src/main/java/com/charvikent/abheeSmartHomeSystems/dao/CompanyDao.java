package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.Company;


@Repository
@Transactional
public class CompanyDao 
{
	@PersistenceContext
    private EntityManager entityManager;


	public void saveCompany(Company company) 
	{
		entityManager.persist(company);

	}
	@SuppressWarnings("unchecked")
	public List<Company> getCompanyNames()
	 {

		return entityManager.createQuery("  from Company where status='1'").getResultList();

	 }
	public Company getCompanyNameById(Company com) 
	{
			
			@SuppressWarnings("unchecked")
			List<Company> comList =(List<Company>) entityManager.createQuery("SELECT com FROM Company com where name =:custName ").setParameter("custName",com.getName()).getResultList();
			if(comList.size() > 0)
				return comList.get(0);
			return null;
			
	}
	public void UpdateCompany(Company com)
	{
		Company uc= (Company)entityManager.find(Company.class ,com.getId());
		uc.setName(com.getName());
		uc.setDescription(com.getDescription());
		entityManager.flush();
	}
	public boolean deleteCompany(Integer id, String status) 
	{
		Boolean delete=false;
		try{
			
			Company com= (Company)entityManager.find(Company.class ,id);
			   com.setStatus(status);
			   entityManager.merge(com);
			if(!status.equals(com.getStatus()))
			{
				delete=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}

	@SuppressWarnings("unchecked")
	public List<Company> getAllInActiveList() 
	{
		
		return entityManager.createQuery("from Company where status='0'").getResultList();
	}
	
	
	public Map<Integer, String> getCompanymap()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Company> rolesList= getCompanyNames();
		for(Company bean: rolesList){
			rolesMap.put(bean.getId(), bean.getName());
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;
				
		
	}
}
