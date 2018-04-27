package com.charvikent.abheeSmartHomeSystems.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "abheecustomerorderorders")
public class CustomerOrders {
	
	
	private String orderId;
	private String customerId;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "CustomerOrders [orderId=" + orderId + ", customerId=" + customerId + ", getOrderId()=" + getOrderId()
				+ ", getCustomerId()=" + getCustomerId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public CustomerOrders() {
		super();
	}

}
