/*package com.charvikent.abheeSmartHomeSystems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abheepayment")
public class AbheePayment 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String payment;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPayment() {
		return payment;
	}
	public void setPaymentstatus(String payment) {

		this.payment = payment;
	}
	@Override
	public String toString() {
		return "AbheePayment [id=" + id + ", payment=" + payment + "]";
	}
	
	
}
*/