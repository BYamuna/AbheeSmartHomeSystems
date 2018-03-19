package com.charvikent.abheeSmartHomeSystems.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "otpDetails")
public class OTPDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	
	@CreationTimestamp
	private Date createdTime;
	
	private String mobileno;
	
	private String OTPstatus;
	
	private String OTPnumber;

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

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getOTPstatus() {
		return OTPstatus;
	}

	public void setOTPstatus(String oTPstatus) {
		OTPstatus = oTPstatus;
	}
	
	
	

	public String getOTPnumber() {
		return OTPnumber;
	}

	public void setOTPnumber(String oTPnumber) {
		OTPnumber = oTPnumber;
	}

	@Override
	public String toString() {
		return "OTPDetails [id=" + id + ", createdTime=" + createdTime + ", mobileno=" + mobileno + ", OTPstatus="
				+ OTPstatus + ", OTPnumber=" + OTPnumber + "]";
	}

	
	
	
	

}
