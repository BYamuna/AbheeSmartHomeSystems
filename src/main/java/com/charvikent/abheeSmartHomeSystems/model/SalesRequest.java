package com.charvikent.abheeSmartHomeSystems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "abheeSalesRequest")
public class SalesRequest 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	private String modelnumber;
	private String email;
	private String mobileno;
	private String location;
	private String address;
	private String reqdesc;
	private String imgfiles;
	private String salesrequestnumber;
	private String quotationDocuments;
	private String enable;
	private String lat;
	private String longitude;
	private String customerid;
	private String customername;
	@Transient
	private String locationData;

	public SalesRequest() 
	{
		super();

	}
	public SalesRequest(Integer id, String modelnumber, String email, String mobileno, String location, String address,
			String reqdesc, String imgfiles,String customername) 
	{
		super();
		this.id = id;
		this.modelnumber = modelnumber;
		this.email = email;
		this.mobileno = mobileno;
		this.location = location;
		this.address = address;
		this.reqdesc = reqdesc;
		this.imgfiles = imgfiles;
		this.customername=customername;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getLocationData() {
		return locationData;
	}
	public void setLocationData(String locationData) {
		this.locationData = locationData;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getQuotationDocuments() {
		return quotationDocuments;
	}
	public void setQuotationDocuments(String quotationDocuments) {
		this.quotationDocuments = quotationDocuments;
	}
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id) 
	{
		this.id = id;
	}
	public String getModelnumber() 
	{
		return modelnumber;
	}
	public void setModelnumber(String modelnumber) 
	{
		this.modelnumber = modelnumber;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getMobileno() 
	{
		return mobileno;
	}
	public void setMobileno(String mobileno) 
	{
		this.mobileno = mobileno;
	}
	public String getLocation() 
	{
		return location;
	}
	public void setLocation(String location) 
	{
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public String getReqdesc() 
	{
		return reqdesc;
	}
	public void setReqdesc(String reqdesc) 
	{
		this.reqdesc = reqdesc;
	}
	public String getImgfiles() 
	{
		return imgfiles;
	}
	public void setImgfiles(String imgfiles) 
	{
		this.imgfiles = imgfiles;
	}
	public String getSalesrequestnumber() {
		return salesrequestnumber;
	}
	public void setSalesrequestnumber(String salesrequestnumber) {
		this.salesrequestnumber = salesrequestnumber;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	@Override
	public String toString() {
		return "SalesRequest [id=" + id + ", modelnumber=" + modelnumber + ", email=" + email + ", mobileno=" + mobileno
				+ ", location=" + location + ", address=" + address + ", reqdesc=" + reqdesc + ", imgfiles=" + imgfiles
				+ ", salesrequestnumber=" + salesrequestnumber + ", quotationDocuments=" + quotationDocuments
				+ ", enable=" + enable + ", lat=" + lat + ", longitude=" + longitude + ", customerid=" + customerid
				+ ", customername=" + customername + ", locationData=" + locationData + "]";
	}
}
