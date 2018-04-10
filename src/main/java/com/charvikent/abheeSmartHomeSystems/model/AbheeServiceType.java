package com.charvikent.abheeSmartHomeSystems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "abheeservicetype")
public class AbheeServiceType 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String servicetypename;
	private String status;
	@Transient
	private String servicetypeId;
	
	public AbheeServiceType() 
	{
		super();
		
	}
	public AbheeServiceType(Integer id, String servicetypename, String status, String servicetypeId)
	{
		super();
		this.id = id;
		this.servicetypename = servicetypename;
		this.status = status;
		this.servicetypeId = servicetypeId;
	}
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id) 
	{
		this.id = id;
	}
	public String getServicetypename() 
	{
		return servicetypename;
	}
	public void setServicetypename(String servicetypename) 
	{
		this.servicetypename = servicetypename;
	}
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	public String getServicetypeId() 
	{
		return servicetypeId;
	}
	public void setServicetypeId(String servicetypeId) 
	{
		this.servicetypeId = servicetypeId;
	}
	@Override
	public String toString()
	{
		return "AbheeServiceType [id=" + id + ", servicetypename=" + servicetypename + ", status=" + status
				+ ", servicetypeId=" + servicetypeId + "]";
	}
	
	
	
	
}
