package com.charvikent.abheeSmartHomeSystems.model;

public class ServiceRequest {
	
	private Integer  id;
	
	private String message;
	private String servicetypeid;
	private String catid;
	private String modelid;
	private String customerId;
	private String custaddress;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getServicetypeid() {
		return servicetypeid;
	}
	public void setServicetypeid(String servicetypeid) {
		this.servicetypeid = servicetypeid;
	}
	public String getCatid() {
		return catid;
	}
	public void setCatid(String catid) {
		this.catid = catid;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustaddress() {
		return custaddress;
	}
	public void setCustaddress(String custaddress) {
		this.custaddress = custaddress;
	}
	@Override
	public String toString() {
		return "ServiceRequest [id=" + id + ", message=" + message + ", servicetypeid=" + servicetypeid + ", catid="
				+ catid + ", modelid=" + modelid + ", customerId=" + customerId + ", custaddress=" + custaddress + "]";
	}
	

	
	
	

}
