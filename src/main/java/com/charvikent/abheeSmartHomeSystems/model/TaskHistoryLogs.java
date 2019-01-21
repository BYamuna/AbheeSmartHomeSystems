package com.charvikent.abheeSmartHomeSystems.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class TaskHistoryLogs  {


	
	public int getCstatus() {
		return cstatus;
	}
	public void setCstatus(int cstatus) {
		this.cstatus = cstatus;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	
	private String taskno;
	private Integer taskid;
	@CreationTimestamp
	private Date createdTime;

	@UpdateTimestamp
	private Date updatedTime;

	private String category;
	private String severity;
	private String priority;
	private String assignto;
	private String subject;
	private String description;
	private  String uploadfile;
	private String imgfile;
	private String invimg;
	@Transient
	private Integer gapdays;
	@Transient
	private Integer gapcount;

	private String kstatus;
	private String status;
	private String additionalinfo;
	
	private String taskdeadline;
	@Transient
	private String taskdeadlineid;
	
	private String ServiceType;
	private String requestType;
	private String ModifiedBy;
	
	
	
	@Transient
	private String assigntoid;
	@Transient
	private String categoryid;
	@Transient
	private String priorityid;
	@Transient
	private String  severityid;
	@Transient
	private String assignbyid;
	@Transient
	private String kstatusid;
	
	
	private  String modelid;
	
	private  String addComment;
	
	private  String communicationaddress;
	
	private String requesttime;
	
	private int notificationstatus;
	private int tstatus;
	private int webstatus;
	
	private int cstatus;
	
	
	public String getInvimg() {
		return invimg;
	}
	public void setInvimg(String invimg) {
		this.invimg = invimg;
	}
	public int getTstatus() {
		return tstatus;
	}
	public void setTstatus(int tstatus) {
		this.tstatus = tstatus;
	}
	public String getImgfile() {
		return imgfile;
	}
	public void setImgfile(String imgfile) {
		this.imgfile = imgfile;
	}
	public String getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}
	public Integer getTaskid() {
		return taskid;
	}
	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}
	public String getAssignbyid() {
		return assignbyid;
	}
	public void setAssignbyid(String assignbyid) {
		this.assignbyid = assignbyid;
	}
	public String getTaskno() {
		return taskno;
	}
	public void setTaskno(String taskno) {
		this.taskno = taskno;
	}
	public String getAdditionalinfo() {
		return additionalinfo;
	}
	public void setAdditionalinfo(String additionalinfo) {
		this.additionalinfo = additionalinfo;
	}
	public String getKstatus() {
		return kstatus;
	}
	public void setKstatus(String kstatus) {
		this.kstatus = kstatus;
	}
	public Integer getGapdays() {
		return gapdays;
	}
	public void setGapdays(Integer gapdays) {
		this.gapdays = gapdays;
	}
	public Integer getGapcount() {
		return gapcount;
	}
	public void setGapcount(Integer gapcount) {
		this.gapcount = gapcount;
	}

	private String  assignby;

	public String getAssignby() {
		return assignby;
	}
	public void setAssignby(String assignby) {
		this.assignby = assignby;
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
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getAssignto() {
		return assignto;
	}
	public void setAssignto(String assignto) {
		this.assignto = assignto;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public String getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(String uploadfile) {
		this.uploadfile = uploadfile;
	}
	
	
	
	
	
	public String getAssigntoid() {
		return assigntoid;
	}
	public void setAssigntoid(String assigntoid) {
		this.assigntoid = assigntoid;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getPriorityid() {
		return priorityid;
	}
	public void setPriorityid(String priorityid) {
		this.priorityid = priorityid;
	}
	public String getSeverityid() {
		return severityid;
	}
	public void setSeverityid(String severityid) {
		this.severityid = severityid;
	}
	
	
	
	
	
	
	public String getTaskdeadline() {
		return taskdeadline;
	}
	public void setTaskdeadline(String taskdeadline) {
		this.taskdeadline = taskdeadline;
	}
	
	
	
	public String getTaskdeadlineid() {
		return taskdeadlineid;
	}
	public void setTaskdeadlineid(String taskdeadlineid) {
		this.taskdeadlineid = taskdeadlineid;
	}
	
	
	
	
	
	
	
	public String getKstatusid() {
		return kstatusid;
	}
	public void setKstatusid(String kstatusid) {
		this.kstatusid = kstatusid;
	}
	
	
	public String getServiceType() {
		return ServiceType;
	}
	public void setServiceType(String serviceType) {
		ServiceType = serviceType;
	}
	
	
	
	
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	
	
	
	public String getAddComment() {
		return addComment;
	}
	public void setAddComment(String addComment) {
		this.addComment = addComment;
	}
	
	
	
	
	public String getModifiedBy() {
		return ModifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}
	
	
	public String getCommunicationaddress() {
		return communicationaddress;
	}
	public void setCommunicationaddress(String communicationaddress) {
		this.communicationaddress = communicationaddress;
	}
	
	public int getNotificationstatus() {
		return notificationstatus;
	}
	public void setNotificationstatus(int notificationstatus) {
		this.notificationstatus = notificationstatus;
	}
	
	public int getWebstatus() {
		return webstatus;
	}
	public void setWebstatus(int webstatus) {
		this.webstatus = webstatus;
	}

	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	@Override
	public String toString() {
		return "TaskHistoryLogs [id=" + id + ", taskno=" + taskno + ", taskid=" + taskid + ", createdTime="
				+ createdTime + ", updatedTime=" + updatedTime + ", category=" + category + ", severity=" + severity
				+ ", priority=" + priority + ", assignto=" + assignto + ", subject=" + subject + ", description="
				+ description + ", uploadfile=" + uploadfile + ", imgfile=" + imgfile + ", invimg=" + invimg
				+ ", gapdays=" + gapdays + ", gapcount=" + gapcount + ", kstatus=" + kstatus + ", status=" + status
				+ ", additionalinfo=" + additionalinfo + ", taskdeadline=" + taskdeadline + ", taskdeadlineid="
				+ taskdeadlineid + ", ServiceType=" + ServiceType + ", requestType=" + requestType + ", ModifiedBy="
				+ ModifiedBy + ", assigntoid=" + assigntoid + ", categoryid=" + categoryid + ", priorityid="
				+ priorityid + ", severityid=" + severityid + ", assignbyid=" + assignbyid + ", kstatusid=" + kstatusid
				+ ", modelid=" + modelid + ", addComment=" + addComment + ", communicationaddress="
				+ communicationaddress + ", requesttime=" + requesttime + ", notificationstatus=" + notificationstatus
				+ ", tstatus=" + tstatus + ", webstatus=" + webstatus + ", cstatus=" + cstatus + ", assignby="
				+ assignby + "]";
	}

}
