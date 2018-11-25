package com.charvikent.abheeSmartHomeSystems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "abheerequesttime")
public class AbheeRequestTime 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String requesttime;
	private String status;
	@Transient
	private String requesttimeid;
	
	public AbheeRequestTime() 
	{
		
	}
	
	public AbheeRequestTime(Integer id, String requesttime, String status, String requesttimeid) 
	{
		super();
		this.id = id;
		this.requesttime = requesttime;
		this.status = status;
		this.requesttimeid = requesttimeid;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequesttimeid() {
		return requesttimeid;
	}

	public void setRequesttimeid(String requesttimeid) {
		this.requesttimeid = requesttimeid;
	}

	@Override
	public String toString() {
		return "AbheeRequestTime [id=" + id + ", requesttime=" + requesttime + ", status=" + status + ", requesttimeid="
				+ requesttimeid + "]";
	}
	
	
}
