package com.charvikent.abheeSmartHomeSystems.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "abheeproductguarantee")
public class ProductGuarantee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String productmodelid;
	private String customerid;
	private String purchaseddate;
	private String expireddate;
	private String status;
	@CreationTimestamp
	protected Date createdTime ;
	@CreationTimestamp
	protected Date UpdatedTime ;
	public ProductGuarantee() 
	{
		super();
		
	}
	

	

	public ProductGuarantee(Integer id, String productmodelid, String customerid, String purchaseddate,
			String expireddate, String status, Date createdTime, Date updatedTime) {
		super();
		this.id = id;
		this.productmodelid = productmodelid;
		this.customerid = customerid;
		this.purchaseddate = purchaseddate;
		this.expireddate = expireddate;
		this.status = status;
		this.createdTime = createdTime;
		UpdatedTime = updatedTime;
	}




	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductmodelid() {
		return productmodelid;
	}
	public void setProductmodelid(String productmodelid) {
		this.productmodelid = productmodelid;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getPurchaseddate() {
		return purchaseddate;
	}
	public void setPurchaseddate(String purchaseddate) {
		this.purchaseddate = purchaseddate;
	}
	public String getExpireddate() {
		return expireddate;
	}
	public void setExpireddate(String expireddate) {
		this.expireddate = expireddate;
	}
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getCreatedTime() {
		return createdTime;
	}




	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}




	public Date getUpdatedTime() {
		return UpdatedTime;
	}




	public void setUpdatedTime(Date updatedTime) {
		UpdatedTime = updatedTime;
	}




	@Override
	public String toString() {
		return "ProductGuarantee [id=" + id + ", productmodelid=" + productmodelid + ", customerid=" + customerid
				+ ", purchaseddate=" + purchaseddate + ", expireddate=" + expireddate + ", status=" + status
				+ ", createdTime=" + createdTime + ", UpdatedTime=" + UpdatedTime + "]";
	}
	
}
