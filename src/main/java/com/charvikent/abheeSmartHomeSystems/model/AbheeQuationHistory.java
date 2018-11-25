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
/**
 * @author Charvik101
 *
 */
@Entity
public class AbheeQuationHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	
	@Column
	private String filename;
	
	private String quationid;
	
	
	@CreationTimestamp
	private Date createdTime;

	
	

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getQuationid() {
		return quationid;
	}

	public void setQuationid(String quationid) {
		this.quationid = quationid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "abheeQuationHistory [id=" + id + ", filename=" + filename + ", quationid=" + quationid
				+ ", createdTime=" + createdTime + "]";
	}

	
	
	
	
	
}
