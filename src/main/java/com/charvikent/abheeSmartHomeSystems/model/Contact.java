package com.charvikent.abheeSmartHomeSystems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abheeContact")
public class Contact 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String fullname;
	private String emailid;
	private String mobilenumber;
	private String subject;
	public Contact() 
	{
		
	}
	public Contact(Integer id, String fullname, String emailid, String mobilenumber, String subject) 
	{
		super();
		this.id = id;
		this.fullname = fullname;
		this.emailid = emailid;
		this.mobilenumber = mobilenumber;
		this.subject = subject;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", fullname=" + fullname + ", emailid=" + emailid + ", mobilenumber="
				+ mobilenumber + ", subject=" + subject + "]";
	}
	
	
	
	
	
}
