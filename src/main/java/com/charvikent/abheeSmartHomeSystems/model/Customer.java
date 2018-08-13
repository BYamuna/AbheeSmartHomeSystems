package com.charvikent.abheeSmartHomeSystems.model;

import java.util.Date;
/*import java.util.Set;*/

/*import javax.persistence.CascadeType;*/
import javax.persistence.Entity;
/*import javax.persistence.FetchType;*/
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;*/
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "abhee_customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreationTimestamp
	private Date createdTime;

	@UpdateTimestamp
	private Date updatedTime;

	private String mobilenumber;

	private String email;

	private String BranchId;

	private String enabled;
	

	private String password;
	
	private String firstname;
	private String lastname;
	private String status;
	private String branchName;
	
	private String customerId;
	
	private String address;
	private String communicationaddress;
	
	private String registedredFromAndroid;
	
	private String customerType;
	
	@Transient
	private String customerTypeName;
	
	private String gst;
	
	private boolean purchaseCustomer;
	
	/*@OneToMany(fetch = FetchType.LAZY, targetEntity=CustomerOrders.class, cascade=CascadeType.ALL)
	@JoinColumn(name="customerId")
	private Set<CustomerOrders> CustomerOrders;
	
	

	public Set<CustomerOrders> getCustomerOrders() {
		return CustomerOrders;
	}

	public void setCustomerOrders(Set<CustomerOrders> customerOrders) {
		CustomerOrders = customerOrders;
	}*/

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	public boolean isPurchaseCustomer() {
		return purchaseCustomer;
	}

	public void setPurchaseCustomer(boolean purchaseCustomer) {
		this.purchaseCustomer = purchaseCustomer;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	

	

	public Customer() {
	}
	
	
	



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Customer(Customer customer) {
		this.id = customer.id;
		this.createdTime = customer.createdTime;
		this.updatedTime = customer.updatedTime;
		this.mobilenumber = customer.mobilenumber;
		this.email = customer.email;
		this.enabled = customer.enabled;
		
		this.password = customer.password;
		this.status = customer.status;
		this.firstname = customer.firstname;
		this.lastname = customer.lastname;
		this.BranchId=customer.BranchId;
		this.address=customer.address;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
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


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}



	

	public String getBranchId() {
		return BranchId;
	}

	public void setBranchId(String branchId) {
		BranchId = branchId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	

	public String getRegistedredFromAndroid() {
		return registedredFromAndroid;
	}

	public void setRegistedredFromAndroid(String registedredFromAndroid) {
		this.registedredFromAndroid = registedredFromAndroid;
	}
	

	public String getCommunicationaddress() {
		return communicationaddress;
	}

	public void setCommunicationaddress(String communicationaddress) {
		this.communicationaddress = communicationaddress;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime
				+ ", mobilenumber=" + mobilenumber + ", email=" + email + ", BranchId=" + BranchId + ", enabled="
				+ enabled + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", status=" + status + ", branchName=" + branchName + ", customerId=" + customerId + ", address="
				+ address + ", communicationaddress=" + communicationaddress + ", registedredFromAndroid="
				+ registedredFromAndroid + ", customerType=" + customerType + ", customerTypeName=" + customerTypeName
				+ ", gst=" + gst + ", purchaseCustomer=" + purchaseCustomer + "]";
	}

	

	
	
	
	

}
