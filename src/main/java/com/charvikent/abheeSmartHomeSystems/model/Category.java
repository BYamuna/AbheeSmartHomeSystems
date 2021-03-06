package com.charvikent.abheeSmartHomeSystems.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*import javax.persistence.Temporal;*/

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;
/**
 * @author Charvik101
 *
 */
@Entity
@Table(name = "abheecategory")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	
	@Column
	private String category;
	
	@Transient
	private Integer company;
	
	private String status;
	
	private String categoryimg;
	
	@CreationTimestamp
	private Date createdTime;

	@UpdateTimestamp
	private Date updatedTime;
	
	private String kpOrgId;
	
	

	public Integer getCompany() {
		return company;
	}

	public void setCompay(Integer company) {
		this.company = company;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
	
	

	public String getKpOrgId() {
		return kpOrgId;
	}

	public void setKpOrgId(String kpOrgId) {
		this.kpOrgId = kpOrgId;
	}
	
	

	public String getCategoryimg() {
		return categoryimg;
	}

	public void setCategoryimg(String categoryimg) {
		this.categoryimg = categoryimg;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + ", company=" + company + ", status=" + status
				+ ", categoryimg=" + categoryimg + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime
				+ ", kpOrgId=" + kpOrgId + "]";
	}

	
	
	
}
