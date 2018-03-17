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
public class AbheeCustRegistration
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String name;
	String surname;
	String custID;
	String email;
	String mobileno;
	String OTP;
	String OTPstatus;
	String status;
	@CreationTimestamp
	 Date createdTime;

	@UpdateTimestamp
	 Date updatedTime;
	
	public AbheeCustRegistration() 
	{
		super();
	}

	public AbheeCustRegistration(int id, String name,String surname, String custID, String email, String mobileno, String OTP,
			String OTPstatus, String status, Date createdTime, Date updatedTime) 
	{
		super();
		this.id = id;
		this.name=name;
		this.surname = surname;
		this.custID = custID;
		this.email = email;
		this.mobileno = mobileno;
		this.OTP = OTP;
		this.OTPstatus = OTPstatus;
		this.status = status;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
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

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) 
	{
		OTP = oTP;
	}

	public String getOTPstatus() 
	{
		return OTPstatus;
	}

	public void setOTPstatus(String oTPstatus) 
	{
		OTPstatus = oTPstatus;
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

	@Override
	public String toString() 
	{
		return "AbheeCustRegistration [id=" + id + ", name=" + name + ", surname=" + surname + ", custID=" + custID
				+ ", email=" + email + ", mobileno=" + mobileno + ", OTP=" + OTP + ", OTPstatus=" + OTPstatus
				+ ", status=" + status + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}
	
	
}
