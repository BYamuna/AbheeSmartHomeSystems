package com.charvikent.abheeSmartHomeSystems.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Transient;
@Entity
@Table(name = "abheeCompany")
public class Company 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String name;
	private String description;
	private String companyimg;
	private String category;
	@Transient
	private String categoryid;
	@Column
	private String status;
	
	@CreationTimestamp
	private Date createdTime;

	@UpdateTimestamp
	private Date updatedTime;
	public Company() 
	{
		super();
		
	}
	public Company(Integer id, String name, String description, String companyimg, String category, String categoryid,
			String status, Date createdTime, Date updatedTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.companyimg = companyimg;
		this.category = category;
		this.categoryid = categoryid;
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
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getCompanyimg() 
	{
		return companyimg;
	}
	public void setCompanyimg(String companyimg) 
	{
		this.companyimg = companyimg;
	}
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", description=" + description + ", companyimg=" + companyimg
				+ ", category=" + category + ", categoryid=" + categoryid + ", status=" + status + ", createdTime="
				+ createdTime + ", updatedTime=" + updatedTime + "]";
	}
	
	
	
}
