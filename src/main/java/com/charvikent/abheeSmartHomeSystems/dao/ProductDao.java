package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.Product;

/*import com.charvikent.abheeSmartHomeSystems.model.User;*/


@Repository
@Transactional
public class ProductDao 
{
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;


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
		uc.setCategoryid(pro.getCategoryid());
		uc.setCompanyid(pro.getCompanyid());
		uc.setName(pro.getName());
		uc.setDescription(pro.getDescription());
		uc.setProductModelSpecifications(pro.getProductModelSpecifications());
		uc.setProductPrice(pro.getProductPrice());
		uc.setMaxAllowedDiscount(pro.getMaxAllowedDiscount());
		uc.setProductmodelvideoslinks(pro.getProductmodelvideoslinks());
		uc.setProductmodelpics(pro.getProductmodelpics());
		
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
		
		List<Product> listProducts =new ArrayList<Product>();
		
		String hql ="select p.id,p.categoryid,cat.category,p.companyid,com.name,p.description,p.name,p.productmodelpics,p.productmodelvideoslinks,p.status,p.updatedTime from Product  p , Category cat , Company com where p.categoryid=cat.id and p.companyid=com.id and p.status='0' order by p.updatedTime desc";

		List<Object[]> rows = entityManager.createQuery(hql).getResultList();
		
		for (Object[] row : rows) {
			
			Product product =new Product();
			product.setId(Integer.parseInt(String.valueOf(row[0])));
			product.setCategoryid((String) row[1]);
			product.setCategoryname((String) row[2]);
			product.setCompanyid((String) row[3]);
			product.setCompanyname((String) row[4]);
			product.setDescription((String) row[5]);
			product.setName((String) row[6]);
			product.setProductmodelpics((String) row[7]);
			product.setProductmodelvideoslinks((String) row[8]);
			product.setStatus((String) row[9]);
			
			listProducts.add(product);

	}
		 return listProducts;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProductDetails()
	 {
		List<Product> listProducts =new ArrayList<Product>();
		
		String hql ="select p.id,p.categoryid,cat.category,p.companyid,com.name,p.description,p.name,p.productmodelpics,p.productmodelvideoslinks,p.status,p.ProductModelSpecifications,p.ProductPrice,p.maxAllowedDiscount,p.updatedTime from Product  p , Category cat , Company com where p.categoryid=cat.id and p.companyid=com.id and p.status='1' order by p.updatedTime desc";

		List<Object[]> rows = entityManager.createQuery(hql).getResultList();
		
		for (Object[] row : rows) {
			
			Product product =new Product();
			product.setId(Integer.parseInt(String.valueOf(row[0])));
			product.setCategoryid((String) row[1]);
			product.setCategoryname((String) row[2]);
			product.setCompanyid((String) row[3]);
			product.setCompanyname((String) row[4]);
			product.setDescription((String) row[5]);
			product.setName((String) row[6]);
			product.setProductmodelpics((String) row[7]);
			product.setProductmodelvideoslinks((String) row[8]);
			product.setStatus((String) row[9]);
			product.setProductModelSpecifications((String) row[10]);
			product.setProductPrice((String) row[11]);
			product.setMaxAllowedDiscount((String) row[12]);
			
			listProducts.add(product);


	 }
		return listProducts;
	 }
	public List<Product> getProductCompaniesByCategoryId(String categoryid) {
		
		
		List<Product> listProducts =new ArrayList<Product>();
		
		String hql ="select p.id,p.name,c.name as companyname,p.productmodelvideoslinks,p.productmodelpics,p.companyid from Product p,Company c where p.categoryid='"+categoryid+"' and p.status='1' and p.companyid=c.id group by c.name";

		@SuppressWarnings("unchecked")
		List<Object[]> rows = entityManager.createQuery(hql).getResultList();
		
		for (Object[] row : rows) {
			
			Product product =new Product();
			product.setId(Integer.parseInt(String.valueOf(row[0])));
			product.setName((String) row[1]);
			product.setCompanyname((String) row[2]);
			product.setProductmodelvideoslinks((String) row[3]);
			product.setProductmodelpics((String) row[4]);
			product.setCompanyid((String) row[5]);
			
			
			listProducts.add(product);

	}
		return listProducts;
}
	
	public List<Map<String,Object>> getProductModels(String categoryid,String companyId,String modelid){
		StringBuffer buffer = new StringBuffer("select abc.category as categoryname,p.id,p.name,c.name as companyname,p.productmodelvideoslinks,p.productmodelpics,p.companyid,categoryid,p.description,p.product_model_specifications,product_price,max_allowed_discount from abhee_product p,abhee_company c,abheecategory abc where p.companyid=c.id and p.categoryid =abc.id and p.status='1' ");
		
		if(categoryid  !=null && categoryid !="" )
		{
			
			buffer.append(" and p.categoryid= '"+categoryid+"'");
		}
		
		if(companyId  !=null && companyId !="" )
		{
			
			buffer.append(" and  p.companyid='"+companyId+"'");
		}
		if(modelid  !=null && modelid !="" )
		{
			
			buffer.append(" and  p.id='"+modelid+"'");
		}
		buffer.append(" order by p.companyid ");
		String sql = buffer.toString();
		
		System.out.println(sql);
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		
		return retlist ;
		
	}
	
	public List<Map<String, Object>> getProductsByCompanyId(String companyid) {
		String sql ="select p.id,p.categoryid,p.companyid,p.created_time,p.description,p.name,p.productmodelpics,productmodelvideoslinks,p.updated_time,p.product_model_specifications ,p.product_price,p.max_allowed_discount "
             +" from abhee_product p where p.companyid=? ";	
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{companyid});
		
		return retlist;
	}
	public List<Map<String, Object>> getProductsDescription() {
		String sql ="select p.id,p.categoryid,cat.category,p.companyid,com.name,p.description,p.name,p.productmodelpics,p.productmodelvideoslinks,p.status,p.product_model_specifications,p.product_price,p.max_allowed_discount,p.updated_time,com.companyimg from abhee_product  p , abheecategory cat , abhee_company com  where p.categoryid=cat.id and p.companyid=com.id and p.status='1' order by p.updated_time desc";	
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		
		return retlist;
	}
}
