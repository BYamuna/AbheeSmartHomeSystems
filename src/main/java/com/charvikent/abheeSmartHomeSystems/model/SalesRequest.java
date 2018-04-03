package com.charvikent.abheeSmartHomeSystems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abheeSalesRequest")
public class SalesRequest 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String modelnumber;
	private String email;
	private String mobileno;
	private String location;
	private String address;
	private String reqdesc;
	private String imgfiles;
	
	private String salesrequestnumber;
	
	public SalesRequest() 
	{
		super();
		
	}
	public SalesRequest(Integer id, String modelnumber, String email, String mobileno, String location, String address,
			String reqdesc, String imgfiles) 
	{
		super();
		this.id = id;
		this.modelnumber = modelnumber;
		this.email = email;
		this.mobileno = mobileno;
		this.location = location;
		this.address = address;
		this.reqdesc = reqdesc;
		this.imgfiles = imgfiles;
	}
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id) 
	{
		this.id = id;
	}
	public String getModelnumber() 
	{
		return modelnumber;
	}
	public void setModelnumber(String modelnumber) 
	{
		this.modelnumber = modelnumber;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getMobileno() 
	{
		return mobileno;
	}
	public void setMobileno(String mobileno) 
	{
		this.mobileno = mobileno;
	}
	public String getLocation() 
	{
		return location;
	}
	public void setLocation(String location) 
	{
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public String getReqdesc() 
	{
		return reqdesc;
	}
	public void setReqdesc(String reqdesc) 
	{
		this.reqdesc = reqdesc;
	}
	public String getImgfiles() 
	{
		return imgfiles;
	}
	public void setImgfiles(String imgfiles) 
	{
		this.imgfiles = imgfiles;
	}
	
	
	
	public String getSalesrequestnumber() {
		return salesrequestnumber;
	}
	public void setSalesrequestnumber(String salesrequestnumber) {
		this.salesrequestnumber = salesrequestnumber;
	}
	@Override
	public String toString() 
	{
		return "SalesRequest [id=" + id + ", modelnumber=" + modelnumber + ", email=" + email + ", mobileno=" + mobileno
				+ ", location=" + location + ", address=" + address + ", reqdesc=" + reqdesc + ", imgfiles=" + imgfiles
				+ "]";
	}
}
