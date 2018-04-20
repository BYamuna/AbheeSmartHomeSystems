package com.charvikent.abheeSmartHomeSystems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abheeproductguarantee")
public class ProductGuarantee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String productid;
	private String products;
	private String duration;
	public ProductGuarantee() 
	{
		super();
		
	}
	public ProductGuarantee(Integer id, String productid, String products, String duration) 
	{
		super();
		this.id = id;
		this.productid = productid;
		this.products = products;
		this.duration = duration;
	}
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id) 
	{
		this.id = id;
	}
	public String getProductid() 
	{
		return productid;
	}
	public void setProductid(String productid) 
	{
		this.productid = productid;
	}
	public String getProducts() 
	{
		return products;
	}
	public void setProducts(String products) 
	{
		this.products = products;
	}
	public String getDuration() 
	{
		return duration;
	}
	public void setDuration(String duration) 
	{
		this.duration = duration;
	}
	@Override
	public String toString() 
	{
		return "ProductGuarantee [id=" + id + ", productid=" + productid + ", products=" + products + ", duration="
				+ duration + "]";
	}
	
	
}
