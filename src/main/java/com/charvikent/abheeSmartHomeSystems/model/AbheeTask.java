package com.charvikent.abheeSmartHomeSystems.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class AbheeTask  
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String taskno;
	private String customerId;
	@CreationTimestamp
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date createdTime;
	@UpdateTimestamp
	private Date updatedTime;
	private String category;
	private String company;
	private String severity;
	private String priority;
	private String ServiceType;
	private String  assignby;
	private String assignto;
	private String subject;
	private String description;
	private  String uploadfile;
	private String requesttime;
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
	@Transient
	private String assigntoid;
	@Transient
	private String categoryid;
	@Transient
	private String priorityid;
	@Transient
	private String  severityid;
	@Transient
	private String  servicetypeid;
	@Transient
	private String  requesttimeid;
	@Transient
	private String assignbyid;
	@Transient
	private String kstatusid;
	private  String modelid;
	private  String addComment;
	private  String communicationaddress;
	private String warranty;
	private String total;
	private String discount;
	private String tax;
	private String amountreceived;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCommunicationaddress() 
	{
		return communicationaddress;
	}
	public void setCommunicationaddress(String communicationaddress) 
	{
		this.communicationaddress = communicationaddress;
	}
	public String getCustomerId() 
	{
		return customerId;
	}
	public void setCustomerId(String customerId) 
	{
		this.customerId = customerId;
	}
	public String getAssignbyid() 
	{
		return assignbyid;
	}
	public void setAssignbyid(String assignbyid) 
	{
		this.assignbyid = assignbyid;
	}
	public String getTaskno() 
	{
		return taskno;
	}
	public void setTaskno(String taskno) 
	{
		this.taskno = taskno;
	}
	public String getAdditionalinfo() 
	{
		return additionalinfo;
	}
	public void setAdditionalinfo(String additionalinfo) 
	{
		this.additionalinfo = additionalinfo;
	}
	public String getKstatus() 
	{
		return kstatus;
	}
	public void setKstatus(String kstatus) 
	{
		this.kstatus = kstatus;
	}
	public Integer getGapdays() 
	{
		return gapdays;
	}
	public void setGapdays(Integer gapdays) 
	{
		this.gapdays = gapdays;
	}
	public Integer getGapcount() 
	{
		return gapcount;
	}
	public void setGapcount(Integer gapcount) 
	{
		this.gapcount = gapcount;
	}

	public String getAssignby() 
	{
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
	public String getServicetypeid() {
		return servicetypeid;
	}
	public void setServicetypeid(String servicetypeid) {
		this.servicetypeid = servicetypeid;
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
	public String getWarranty() {
		return warranty;
	}
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getAmountreceived() {
		return amountreceived;
	}
	public void setAmountreceived(String amountreceived) {
		this.amountreceived = amountreceived;
	}
	public String getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}
	public String getRequesttimeid() {
		return requesttimeid;
	}
	public void setRequesttimeid(String requesttimeid) {
		this.requesttimeid = requesttimeid;
	}
	@Override
	public String toString() {
		return "AbheeTask [id=" + id + ", taskno=" + taskno + ", customerId=" + customerId + ", createdTime="
				+ createdTime + ", updatedTime=" + updatedTime + ", category=" + category + ", company=" + company
				+ ", severity=" + severity + ", priority=" + priority + ", ServiceType=" + ServiceType + ", assignby="
				+ assignby + ", assignto=" + assignto + ", subject=" + subject + ", description=" + description
				+ ", uploadfile=" + uploadfile + ", requesttime=" + requesttime + ", gapdays=" + gapdays + ", gapcount="
				+ gapcount + ", kstatus=" + kstatus + ", status=" + status + ", additionalinfo=" + additionalinfo
				+ ", taskdeadline=" + taskdeadline + ", taskdeadlineid=" + taskdeadlineid + ", assigntoid=" + assigntoid
				+ ", categoryid=" + categoryid + ", priorityid=" + priorityid + ", severityid=" + severityid
				+ ", servicetypeid=" + servicetypeid + ", requesttimeid=" + requesttimeid + ", assignbyid=" + assignbyid
				+ ", kstatusid=" + kstatusid + ", modelid=" + modelid + ", addComment=" + addComment
				+ ", communicationaddress=" + communicationaddress + ", warranty=" + warranty + ", total=" + total
				+ ", discount=" + discount + ", tax=" + tax + ", amountreceived=" + amountreceived + "]";
	}
	
	
	
	
	
}
