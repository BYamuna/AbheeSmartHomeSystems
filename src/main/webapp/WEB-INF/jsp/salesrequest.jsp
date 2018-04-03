<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%-- <%@include file="header.jsp" %>
<%@include file="footer.jsp" %>
 --%>



<!-- <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAVOdAfKTzdJilketdMBkTBClJezNk07Ws&callback=initMap"
  type="text/javascript"></script>
 -->


<form:form modelAttribute="srequestf" action="salesRequest" class="form-horizontal " method="Post" enctype="multipart/form-data">
	                  <form:hidden path="id"/>
					<div class="col-md-6"><br>
					<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Product Model<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="modelnumber" class="form-control validate" placeholder="Enter product model"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Email<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="email" class="form-control validate" placeholder="Enter emailid"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Mobile<span class="impColor">*</span></label>
									<div class="col-md-6">

										<form:input path="mobileno" class="form-control validate" placeholder="Enter Mobile Number"/>
									</div>
								</div>
								
								
								</div><div class="clearfix"></div>
								<div class="col-md-6">
								<div class="form-group" id="passwordDiv">
									<label class="col-md-3 control-label no-padding-right">location<span class="impColor">*</span></label>
									<div class="col-md-6">

										<form:input path="location" class="form-control validate" placeholder="Enter Location" readonly="true"/>
									</div>
								</div>
								
								</div><div class="clearfix"></div>
								<div class="col-md-6">
								</div>
								<div class="clearfix"></div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Address</label>
									<div class="col-md-6">
										<form:textarea path="address" class="form-control validate emailOnly" placeholder="Enter Address"/>
									</div>
								</div></div>
								<div class="clearfix"></div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Comments</label>
									<div class="col-md-6">

										<form:textarea path="reqdesc" class="form-control validate emailOnly" placeholder="Comments"/>
									</div>
								</div></div>
								<div class="clearfix"></div>
								<div class="form-group">
								<label class="col-md-3 control-label no-padding-right"><span class="impColor">Choose images*</span></label>
									<div class="col-md-6">
										<input type="file" name="imgfile" id="imgfile" multiple/>
									</div>
								</div>
								
								<div class="clearfix"></div>
								<div class="col-md-6">
									<div align="center" class="but">							
					      				<input type="submit" id="submit1" value="Submit" class="btn-primary btn"/>
					      				<input type="reset" value="Reset" class="btn-danger btn cancel"/>
					      			</div>
								</div>
					</form:form>
					
					
					<div id="googleMap" style="width:100%;height:400px;"></div>
					
					<button onclick="getLocation()">Try It</button>
					<script>
					
					
					var lat;
					var lan;
						
						
					var x = document.getElementById("demo");
						function getLocation() {
						    if (navigator.geolocation) {
						        navigator.geolocation.getCurrentPosition(showPosition);
						    } else {
						        x.innerHTML = "Geolocation is not supported by this browser.";
						    }
						}
						function showPosition(position) {
							lat=position.coords.latitude;
							lan=position.coords.longitude;
							
							console.log(position.coords.latitude)
							console.log(lan);
						    x.innerHTML = "Latitude: " + position.coords.latitude + 
						    "<br>Longitude: " + position.coords.longitude; 
						}

					
function myMap() {
var mapProp= {
   /*  center:new google.maps.LatLng(13.576848329332353,78.41736346531445), */
    center:new google.maps.LatLng(lat,lan),
    zoom:8,
};
var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);

google.maps.event.addListener(map, 'click', function(event) {
	$("#location").val(event.latLng.lat()+"&"+event.latLng.lng());
alert(event.latLng.lat() + ", " + event.latLng.lng());
});

}








</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAVOdAfKTzdJilketdMBkTBClJezNk07Ws&callback=myMap"></script>
<<script type="text/javascript">
$("#pageName").text("Sales Request");
$(".salesrequest").addClass("active"); 
</script>					

 