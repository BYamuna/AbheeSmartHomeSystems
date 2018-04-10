package com.charvikent.abheeSmartHomeSystems.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class AllServiceRequests 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String servicenumber;
	private String servicecategory;
	private String custID;
	private String custDesc;
	private String files;
	private String status;
	@CreationTimestamp
	private Date createdTime;

	@UpdateTimestamp
	private Date updatedTime;

	public AllServiceRequests() 
	{
		super();
		
	}

	

	public AllServiceRequests(Integer id, String servicenumber, String servicecategory, String custID, String custDesc,
			String files, String status, Date createdTime, Date updatedTime) 
	{
		super();
		this.id = id;
		this.servicenumber = servicenumber;
		this.servicecategory = servicecategory;
		this.custID = custID;
		this.custDesc = custDesc;
		this.files = files;
		this.status = status;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}



	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getServicenumber() 
	{
		return servicenumber;
	}

	public void setServicenumber(String servicenumber) 
	{
		this.servicenumber = servicenumber;
	}

	public String getServicecategory() 
	{
		return servicecategory;
	}

	public void setServicecategory(String servicecategory) 
	{
		this.servicecategory = servicecategory;
	}

	public String getCustID() 
	{
		return custID;
	}

	public void setCustID(String custID) 
	{
		this.custID = custID;
	}

	public String getCustDesc() 
	{
		return custDesc;
	}

	public void setCustDesc(String custDesc) 
	{
		this.custDesc = custDesc;
	}

	public String getFiles() 
	{
		return files;
	}

	public void setFiles(String files) 
	{
		this.files = files;
	}

	public Date getCreatedTime() 
	{
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) 
	{
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() 
	{
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) 
	{
		this.updatedTime = updatedTime;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}



	@Override
	public String toString() 
	{
		return "AllServiceRequests [id=" + id + ", servicenumber=" + servicenumber + ", servicecategory="
				+ servicecategory + ", custID=" + custID + ", custDesc=" + custDesc + ", files=" + files + ", status="
				+ status + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}

	
		
}
