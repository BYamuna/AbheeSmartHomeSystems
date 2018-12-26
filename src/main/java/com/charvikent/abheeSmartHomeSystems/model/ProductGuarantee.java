package com.charvikent.abheeSmartHomeSystems.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
/*import javax.persistence.GenerationType;*/
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name = "abheeproductguarantee")
public class ProductGuarantee implements Serializable
{
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO) private Integer id;
	 */
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "order_id") 
	@GenericGenerator(name = "order_id", strategy = "com.charvikent.abheeSmartHomeSystems.config.OrderIdGenerator")
	@Column( name= "orderId", unique=true, nullable=false)
	private String  orderId=null;
	private String productmodelid;
	private String customerid;
	private String purchaseddate;
	private String expireddate;
	private String status;
	
	/*@GenericGenerator(name = "sequence_order_id", strategy = "com.charvikent.abheeSmartHomeSystems.config.OrderIdGenerator")
	@GeneratedValue(generator = "sequence_order_id")  */
	
	@CreationTimestamp
	protected Date createdTime ;
	@CreationTimestamp
	protected Date UpdatedTime ;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public ProductGuarantee() 
	{
		super();
		
	}
	





	public ProductGuarantee(/* Integer id, */String orderId, String productmodelid, String customerid, String purchaseddate,
			String expireddate, String status, Date createdTime, Date updatedTime) {
		super();
		/* this.id=id; */
		this.orderId = orderId;
		this.productmodelid = productmodelid;
		this.customerid = customerid;
		this.purchaseddate = purchaseddate;
		this.expireddate = expireddate;
		this.status = status;
		this.createdTime = createdTime;
		UpdatedTime = updatedTime;
	}

	/*
	 * public Integer getId() { return id; }
	 * 
	 * public void setId(Integer id) { this.id = id; }
	 */

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
		return "ProductGuarantee [orderId=" + orderId + ", productmodelid=" + productmodelid
				+ ", customerid=" + customerid + ", purchaseddate=" + purchaseddate + ", expireddate=" + expireddate
				+ ", status=" + status + ", createdTime=" + createdTime + ", UpdatedTime=" + UpdatedTime + "]";
	}




	
	
}
