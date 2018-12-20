package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;*/
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AllServiceRequests;
/*import com.charvikent.abheeSmartHomeSystems.model.Category;*/



@Repository
@Transactional
public class ServiceRequestDao 
{
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public void saveRequest(AllServiceRequests servicerequest) 
	{
		entityManager.persist(servicerequest);
		

	}
	@SuppressWarnings("unchecked")
	public List<AllServiceRequests> getServiceRequestList()
	 {
		 String hql ="from AllServiceRequests";
		 
		
		 List<AllServiceRequests> sreqlist= entityManager.createQuery(hql).getResultList();
		 for(AllServiceRequests sr:sreqlist)
		 {
		 System.out.println(sr.getServicenumber()+" "+sr.getServicecategory());
		 }
		return sreqlist;		 
	 }
	/*@SuppressWarnings("unchecked")
	public Boolean checkServiceRequestExistsorNotByServiceNumber(AllServiceRequests servicerequest)
	{
			
		String hql="from SalesRequest where servicenumber='"+servicerequest.getServicenumber()+"'";
		List<AllServiceRequests> list=entityManager.createQuery(hql).getResultList();
		if(list.size()>0)
			return true;
		else
			return false;
			
	}*/
	public void UpdateRequest(AllServiceRequests servicerequest)
	{
		AllServiceRequests uc= (AllServiceRequests)entityManager.find(AllServiceRequests.class ,servicerequest.getId());
		uc.setServicenumber(servicerequest.getServicenumber());
		uc.setServicecategory(servicerequest.getServicecategory());
		uc.setCustID(servicerequest.getCustID());
		uc.setCustDesc(servicerequest.getCustDesc());
		uc.setFiles(servicerequest.getFiles());
		entityManager.flush();
	}

	
	
	public boolean deleteRequest(Integer id, String status) {
		Boolean delete=false;
		try{
			
			AllServiceRequests servicerequest= (AllServiceRequests)entityManager.find(AllServiceRequests.class ,id);
			 servicerequest.setStatus(status);
			   entityManager.merge( servicerequest);
			if(!status.equals( servicerequest.getStatus()))
			{
				delete=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}

	@SuppressWarnings("unchecked")
	public List<AllServiceRequests> getAllInActiveList() 
	{
		return entityManager.createQuery("  from AllServiceRequests where status='0'").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<AllServiceRequests> getServiceCategoryNames() 
	{
		return entityManager.createQuery("  from AllServiceRequests where status='1'").getResultList();
		
	}
	
	public AllServiceRequests getServiceCategoryNameById(AllServiceRequests servicerequest) 
	{
		@SuppressWarnings("unchecked")
		List< AllServiceRequests> reqList =(List< AllServiceRequests>) entityManager.createQuery("SELECT servicerequest FROM AllServiceRequests servicerequest where servicecategory =:custName ").setParameter("custName",servicerequest.getServicecategory()).getResultList();
		if(reqList.size() > 0)
			return reqList.get(0);
		return null;
	}
	
	
}
