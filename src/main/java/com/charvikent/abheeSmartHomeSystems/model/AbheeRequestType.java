package com.charvikent.abheeSmartHomeSystems.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "abheerequesttypes")
public class AbheeRequestType 
{
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
		private String RequestType;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getCustomerType() {
			return RequestType;
		}
		public void setCustomerType(String RequestType) {
			this.RequestType = RequestType;
		}
		@Override
		public String toString() {
			return "AbheeRequestType [id=" + id + ",RequestType=" + RequestType + "]";
		}
	}

