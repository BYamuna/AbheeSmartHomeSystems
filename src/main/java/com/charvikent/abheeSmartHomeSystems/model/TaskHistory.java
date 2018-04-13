package com.charvikent.abheeSmartHomeSystems.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "abheeTaskHistory")
public class TaskHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	
	private String taskno;
	private String taskid;
	
	private String taskstatus;
	private String message;
	@CreationTimestamp
	protected Date createdTime ;
	
	private String taskdeadline;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTaskno() {
		return taskno;
	}
	public void setTaskno(String taskno) {
		this.taskno = taskno;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getTaskstatus() {
		return taskstatus;
	}
	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getTaskdeadline() {
		return taskdeadline;
	}
	public void setTaskdeadline(String taskdeadline) {
		this.taskdeadline = taskdeadline;
	}
	
	
	
	
	

}
