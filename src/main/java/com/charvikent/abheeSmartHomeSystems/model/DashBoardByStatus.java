package com.charvikent.abheeSmartHomeSystems.model;

public class DashBoardByStatus {
	
	public String statusId;
	public String statusName;
	public String statusConcatination;
	
	
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStatusConcatination() {
		return statusConcatination;
	}
	public void setStatusConcatination(String statusConcatination) {
		this.statusConcatination = statusConcatination;
	}
	@Override
	public String toString() {
		return "DashBoarddByStatus [statusId=" + statusId + ", statusName=" + statusName + ", statusConcatination="
				+ statusConcatination + "]";
	}
	
	
	

}
