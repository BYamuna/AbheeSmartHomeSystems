<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<style>
	.col-xs-6 {
		margin-top:20px;
	}
</style>
    

    <%@include file="abheeheader.jsp" %>
    
    <div class="container">
    	<div class="col-sm-3">
    		<div class="con">
    			<h4 class="active"><a href="#"><span class="glyphicon glyphicon-user"></span> My Profile</a></h4>
    			<h4><a href="#"><span class="glyphicon glyphicon-briefcase"></span> My Orders</a></h4>
    		</div>
    	</div>
    	<div class="col-sm-9">
    		<div class="cdata">
    			<div class="ed">
    				<div class="col-xs-6">
    					<h3>Personal Information</h3>
    				</div>
    				<div class="col-xs-6">
    					<h4 style="float:right;"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
    				</div>
    			</div><div class="clearfix"></div>
    			<div class="inp">
    				<div class="col-xs-6">
    					<input style="width:95%;" class="form-control" type="text" placeholder="First Name" disabled>
    				</div>
    				<div class="col-xs-6">
    					<input style="width:95%; margin-left:20px;" class="form-control" type="text" placeholder="Last Name" disabled>
    				</div><div class="clearfix"></div>
    				<div class="col-xs-6">
    					<input style="width:95%;" class="form-control" type="text" placeholder="Emailid" disabled>
    				</div>
    				<div class="col-xs-6">
    					<input style="width:95%; margin-left:20px;" class="form-control" type="text" placeholder="Mobile Number" disabled>
    				</div><div class="clearfix"></div>
    				<textarea style="margin-top:30px;" class="form-control" type="text" placeholder="Address" disabled></textarea>
    			</div>
    		</div>
    	</div><div class="clearfix"></div>
    </div>

<%@include file="abheefooter.jsp" %>