package com.charvikent.abheeSmartHomeSystems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abheecustomerorderitems")
public class CustomerOrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private String productId;
	private String quantity;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CustomerOrderItems [orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
	public CustomerOrderItems() {
		super();
	}
	

}
