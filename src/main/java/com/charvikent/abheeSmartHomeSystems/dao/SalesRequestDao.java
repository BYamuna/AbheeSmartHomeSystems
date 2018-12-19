package com.charvikent.abheeSmartHomeSystems.dao;

/*import java.util.Iterator;*/
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.config.KptsUtil;
import com.charvikent.abheeSmartHomeSystems.model.AbheeQuationHistory;
import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;

@Repository
@Transactional
public class SalesRequestDao 
{
	@PersistenceContext
    private EntityManager entityManager;
	@Autowired JdbcTemplate jdbcTemplate;
	@Autowired KptsUtil utilities;
	public void saveRequest(SalesRequest salesrequest) 
	{
		String randomNum = utilities.randNum();
		salesrequest.setSalesrequestnumber(randomNum);
		entityManager.persist(salesrequest);
		//saveQuationHistory(salesrequest);

	}
	@SuppressWarnings("unchecked")
	public  Boolean checkSalesrequestExistsorNotByEmailAndModelNo(SalesRequest salesrequest)
	{
		String hql="from SalesRequest where enable='1' and modelnumber='"+salesrequest.getModelnumber()+"'"+ "and "+"email='"+salesrequest.getEmail()+"'";
		List<SalesRequest> list=entityManager.createQuery(hql).getResultList();
		if(list.size()>0)
			return true;
		else
			return false;	
	}
	/*@SuppressWarnings("unchecked")
	public List<SalesRequest> getModelNumbers()
	 {

		return entityManager.createQuery("  from SalesRequest where  ").getResultList();

	 }
	public SalesRequest getModelNumberById() 
	{
			
			@SuppressWarnings("unchecked")
			List<SalesRequest> reqList =(List<SalesRequest>) entityManager.createQuery("  FROM SalesRequest where ").setParameter("",).getResultList();
			if(reqList.size() > 0)
				return reqList.get(0);
			return null;
			
	}*/
		public List<Map<String, Object>> getSalesRequestList()
		 {
		 
			 String hql ="select * from abhee_sales_request";
			 System.out.println(hql);
			
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
				System.out.println(retlist);
				return retlist;
		 }
	
		public List<Map<String, Object>> getSalesRequestList1(SalesRequest salesRequest)
		 {
		 
			String hql ="select sr.request_type,sr.salesrequestnumber,ap.name as modelname from abhee_sales_request sr,abhee_product ap where sr.customerid='"+salesRequest.getCustomerid()+"' and sr.modelnumber=ap.name";
			 /*String hql ="select sr.request_type,sr.salesrequestnumber,sr.modelnumber from abhee_sales_request sr where sr.customerid='"+salesRequest.getCustomerid()+"'"; */
			System.out.println(hql);
			
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
				System.out.println(retlist);
				return retlist;
		 }
		public List<Map<String, Object>> getSalesRequestList2(SalesRequest salesRequest)
		 {
		 
			 String hql ="select sr.request_type,sr.salesrequestnumber,ap.name as modelname from abhee_sales_request sr,abhee_product ap where sr.modelnumber=ap.id and sr.customerid='"+salesRequest.getCustomerid()+"'";
			 System.out.println(hql);
			
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
				System.out.println(retlist);
				return retlist;
		 }
		public List<Map<String, Object>> getSalesRequestListByCustomerId(String custId)
		 {
		 
			 String hql ="select sr.salesrequestnumber,sr.mobileno,ap.name as modelname,sr.address,sr.reqdesc,sr.imgfiles,DATE_FORMAT(sr.created_time,'%d-%b-%y %H:%i')as created_time from abhee_sales_request sr,abhee_product ap where sr.modelnumber=ap.name and sr.customerid='"+custId+"'";
			 System.out.println(hql);
			
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
				System.out.println(retlist);
				return retlist;
		 }
		public List<Map<String, Object>> getSalesRequestListByRequestNo(SalesRequest req)
		 {
		 
			String hql ="select sr.id,sr.address,sr.email,DATE_FORMAT(sr.created_time,'%d-%b-%y %H:%i')as created_time,sr.imgfiles,sr.location,ap.name as modelname,sr.comments,sr.customerid,sr.mobileno,sr.reqdesc,sr.salesrequestnumber,sr.quotation_documents,sr.enable,sr.customername,sr.request_type " + 

			 		"from abhee_sales_request sr, abhee_product ap where sr.modelnumber=ap.name and sr.salesrequestnumber='"+req.getSalesrequestnumber()+"'";
			/*String hql ="select sr.id,sr.address,sr.email,sr.created_time,sr.imgfiles,sr.location,sr.modelnumber,sr.comments,sr.customerid,sr.mobileno,sr.reqdesc,sr.salesrequestnumber,sr.quotation_documents,sr.enable,sr.customername,sr.request_type" + 
			 		"from abhee_sales_request sr where salesrequestnumber='"+req.getSalesrequestnumber()+"'";*/
			 System.out.println(hql);
			
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
				System.out.println(retlist);
				return retlist;
		 }
		
		public List<Map<String, Object>> getAdminResponseListByRequestNo(SalesRequest req)
		 {
		 
			 String hql ="select sr.reqdesc,sr.status,sr.quotation_documents,sr.notes from abhee_sales_request sr where sr.salesrequestnumber='"+req.getSalesrequestnumber()+"'";
			 System.out.println(hql);
			
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
				System.out.println(retlist);
				return retlist;
		 }
		public List<Map<String, Object>> getAdminResponseListByRequestNoWhenStatusZero(SalesRequest req)
		 {
		 
			 String hql ="select sr.status from abhee_sales_request sr where salesrequestnumber='"+req.getSalesrequestnumber()+"'";
			 System.out.println(hql);
			
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
				System.out.println(retlist);
				return retlist;
		 }
		public List<Map<String, Object>> getQuotationDocsByRequestNo(String srequestno)
		 {
			 String hql ="select sr.quotation_documents from abhee_sales_request sr where salesrequestnumber='"+srequestno+"'";
			 System.out.println(hql);
			 List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
			 System.out.println(retlist);
			return retlist;
		 }
		public String getSalesRequestEmailById(String id) 
		{
			String hql ="select email from SalesRequest where id=:id";
			return (String) entityManager.createQuery(hql).setParameter("id", Integer.parseInt(id)).getSingleResult();
		}
	@SuppressWarnings("unused")
	public SalesRequest getSalesRequestById(String id) {
		
		SalesRequest salesRequest =new SalesRequest();
		String hql ="from SalesRequest where id=:id";
		/*Iterator itr = salesrequestlist.listIterator();
		if(itr.hasNext()) {
			salesRequest.setAddress(salesrequestlist.g);
			salesRequest.setEmail((String)email);
		}
		*/
		return (SalesRequest) entityManager.createQuery(hql).setParameter("id", Integer.parseInt(id)).getSingleResult();
	}
	public List<Map<String, Object>> getInActiveList() {
		 String hql ="select * from abhee_sales_request where enable =0";
		 System.out.println(hql);
		
			List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
			System.out.println(retlist);
			return retlist;
	}
	public List<Map<String, Object>> getQuationHistory(String id) {
		 String hql ="select * from abhee_quation_history where quationid ="+id;
		 System.out.println(hql);
		
			List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
			System.out.println(retlist);
			return retlist;
	}
	
	
	public void saveQuationHistory(SalesRequest salesrequest) 
	{
		AbheeQuationHistory abheeQuationHistory = new AbheeQuationHistory();
		abheeQuationHistory.setFilename(salesrequest.getQuotationDocuments());
		abheeQuationHistory.setQuationid(salesrequest.getSalesrequestnumber());
		abheeQuationHistory.setNotes(salesrequest.getNotes());
		abheeQuationHistory.setCreatedTime(salesrequest.getCreatedTime());
		entityManager.persist(abheeQuationHistory);
	}
}
