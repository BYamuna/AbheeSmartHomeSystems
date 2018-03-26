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

@Entity
@Table(name = "abheeProduct")
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String name;
	private String description;
	private String categoryid;
	private String companyid;
	private String productmodelpics;
	private String productmodelvideoslinks;
	
	
	
	
	
	@Column
	private String status;
	
	@CreationTimestamp
	private Date createdTime;

	@UpdateTimestamp
	private Date updatedTime;

	public Product() 
	{
		super();
	}

	public Product(Integer id, String name, String description, String status, Date createdTime, Date updatedTime) 
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	

	public String getProductmodelpics() {
		return productmodelpics;
	}

	public void setProductmodelpics(String productmodelpics) {
		this.productmodelpics = productmodelpics;
	}

	public String getProductmodelvideoslinks() {
		return productmodelvideoslinks;
	}

	public void setProductmodelvideoslinks(String productmodelvideoslinks) {
		this.productmodelvideoslinks = productmodelvideoslinks;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", categoryid=" + categoryid
				+ ", companyid=" + companyid + ", productmodelpics=" + productmodelpics + ", productmodelvideoslinks="
				+ productmodelvideoslinks + ", status=" + status + ", createdTime=" + createdTime + ", updatedTime="
				+ updatedTime + "]";
	}

	
	
}
