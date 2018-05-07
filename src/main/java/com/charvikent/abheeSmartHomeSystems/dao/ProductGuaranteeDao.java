package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.Product;
import com.charvikent.abheeSmartHomeSystems.model.ProductGuarantee;



@Repository
@Transactional
public class ProductGuaranteeDao 
{
	@PersistenceContext
    private EntityManager em;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired CustomerDao customerDao;
	@Autowired  ProductDao  productDao;
	
	public void saveWarranty(ProductGuarantee productGuarantee ) 
	{
		//em.persist(productGuarantee);
		em.merge(productGuarantee);
	

	}
	public Map<String, String> getCustomersMap()
	{
		Map<String, String> rolesMap = new LinkedHashMap<String, String>();
		try
		{
		List<Customer> customersList=  customerDao.getAbheeCustomerNames();
		for(Customer bean: customersList){
			
			if(bean.isPurchaseCustomer()) {
			rolesMap.put(bean.getCustomerId(),bean.getCustomerId());
			}
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;
				
		
	}
	public Map<Integer, String> getProductsMap()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Product> productsList=  productDao.getProductNames(); 
		for(Product bean: productsList){
			rolesMap.put(bean.getId(), bean.getName());
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;
				
		
	}
	public void updateWarranty(ProductGuarantee productGuarantee) 
	{
		ProductGuarantee pg=getProductWarrantyById(productGuarantee.getOrderId());
		pg.setCustomerid(productGuarantee.getCustomerid());
		pg.setProductmodelid(productGuarantee.getProductmodelid());
		pg.setPurchaseddate(productGuarantee.getPurchaseddate());
		pg.setExpireddate(productGuarantee.getExpireddate());
		em.flush();
		
	}
	public ProductGuarantee getProductWarrantyById(String id) 
	{
		return em.find(ProductGuarantee.class, id);	
	}
	
	/*@SuppressWarnings("unchecked")
	public  List<ProductGuarantee> getProductWarrantyDetails() 
	{
		List<ProductGuarantee> listProducts =new ArrayList<ProductGuarantee>();
		String hql="from ProductGuarantee";
		List<Object[]> rows = em.createQuery(hql).getResultList();
		for (Object[] row : rows) 
		{
			ProductGuarantee pw=new ProductGuarantee();
					
			pw.setId(Integer.parseInt(String.valueOf(row[0])));
			pw.setCustomerid((String) row[1]);
			pw.setProductmodelid((String) row[2]);
			pw.setPurchaseddate((String) row[3]);
			pw.setExpireddate((String) row[4]);
					
			listProducts.add(pw);
		
		
			}

		return listProducts;
		
	}*/
	@SuppressWarnings("unchecked")
	public ProductGuarantee getProductWarrantyDetailsByObject(ProductGuarantee productGuarantee) 
	{
		String hql ="from ProductGuarantee where productmodelid ='"+ productGuarantee.getProductmodelid()+"' and customerid ='"+productGuarantee.getCustomerid()+"'and purchaseddate='"+productGuarantee.getPurchaseddate()+"'";
		List<ProductGuarantee> pwd= em.createQuery(hql).getResultList();
		if(pwd.size() > 0)
			return pwd.get(0);
		return null;
	}
	
	
	public List<Map<String, Object>> getProductWarrantyList()
	 {
			
		//return em.createQuery("  abg.id,abg.customerid,abg.expireddate,abg.expireddate,p.name as productmodelname from ProductGuarantee abg,Product p where abg.productmodelid=p.id and status='1'").getResultList();
			String sql="select abg.order_id as orderId,abg.customerid,abg.productmodelid,abg.purchaseddate,abg.expireddate,p.name as productmodelname,abg.status from abheeproductguarantee abg,abhee_product p where abg.productmodelid=p.id and abg.status='1' order by abg.updated_time desc";
			System.out.println(sql);
			
			List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
			System.out.println(retlist);
			return retlist;
		
	 }
	
	public boolean deleteProductWarranty(String id, String status) 
	{
		Boolean delete=false;
		try{
			
			ProductGuarantee pg= (ProductGuarantee)em.find(ProductGuarantee.class ,id);
			   pg.setStatus(status);
			   em.merge(pg);
			if(!status.equals(pg.getStatus()))
			{
				delete=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}

	public List<Map<String, Object>> getAllInActiveList() 
	{
		String sql="select abg.order_id,abg.customerid,abg.productmodelid,abg.purchaseddate,abg.expireddate,p.name as productmodelname,abg.status from abheeproductguarantee abg,abhee_product p where abg.productmodelid=p.id and abg.status='0' order by abg.updated_time desc";
		System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	
	@SuppressWarnings("unchecked")
	public ProductGuarantee getProductWarrantyDetailsByCustomerId(ProductGuarantee productGuarantee) 
	{
		String hql ="from ProductGuarantee where customerid ='"+productGuarantee.getCustomerid()+"'";
		List<ProductGuarantee> ordersList= em.createQuery(hql).getResultList();
		if(ordersList.size() > 0)
			return ordersList.get(0);
		return null;
	}
	
	
}
