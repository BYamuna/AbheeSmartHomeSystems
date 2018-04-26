<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<style>
</style>
    

    <%@include file="abheeheader.jsp" %>
    
    <div class="container">
    	<div class="col-sm-3">
    		<div class="con">
    			<h4 class="active"><a href="${baseurl}/customerprofile"><span class="glyphicon glyphicon-user"></span> My Profile</a></h4>
    			<h4><a href="#order" id="myorders"><span class="glyphicon glyphicon-briefcase"></span> My Orders</a></h4>
    		</div>
    	</div>
    	<div class="col-sm-9">
    		<div class="cdata">
    			<div style="margin-top:-13px;" class="ed">
    				<div class="col-xs-6">
    					<h3>Personal Information</h3>
    				</div>
    				<div class="col-xs-6">
    					<h4 id="edit" style="float:right; margin-top:20px;"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
<!--     					<h4 id="save" style="float:right; margin-top:20px;"><a href="#"><i class="glyphicon glyphicon-saved"></i> Save</a></h4> -->
    					<h4 id="save" style="float:right; margin-top:20px;"><a href="#"><i class="glyphicon glyphicon-saved"></i> Save</a></h4>
    				</div>
    			</div><div class="clearfix"></div>
    			<div class="inp">
    				<div class="col-xs-6">
    					<div class="col-sm-3">
    						<label>First Name: </label>
    					</div>
    					<div class="col-sm-9">
    						<input id="firstName" style="width:95%;" class="form-control" type="text" placeholder="First Name" disabled>
    					</div><div class="clearfix"></div>
    				</div>
    				<div class="col-xs-6">
    					<div class="col-sm-3">
    						<label>Last Name: </label>
    					</div>
    					<div class="col-sm-9">
    						<input id="lastName" style="width:95%; margin-left:15px;" class="form-control" type="text" placeholder="Last Name" disabled>
    					</div>
    				</div><div class="clearfix"></div>
    				<div class="col-xs-6">
    					<div class="col-sm-3">
    						<label>Email Id: </label>
    					</div>
    					<div class="col-sm-9">
    						<input id="emailid" style="width:95%;" class="form-control" type="text" placeholder="Email Id" disabled>
    					</div><div class="clearfix"></div>
    				</div>
    				<div class="col-xs-6">
    					<div class="col-sm-3">
    						<label>Mobile Number: </label>
    					</div>
    					<div class="col-sm-9">
    						<input id="mobile" style="width:95%; margin-left:15px;" class="form-control" type="text" placeholder="Mobile Number" disabled>
    					</div>
    				</div><div class="clearfix"></div>
    				<div class="col-sm-1">
    					<label>Address: </label>
    				</div>
    				<div class="col-sm-11">
    					<textarea style="width:95.5%; margin-left:35px;" class="form-control" type="text" placeholder="Address" disabled></textarea>
    				</div>
    			</div>
    		</div>
    		<div id="order">
    			<div style="margin-top:-15px;" class="odata">
    				<h1>Pavan</h1>
    			</div>
    		</div>
    	</div><div class="clearfix"></div>
    </div>
<script>
$("#myorders").click(function(){
	var id=$(this).attr('href');
	$(id).css('display','block');
	$(".cdata").css('display','none');
});
</script>
<%@include file="abheefooter.jsp" %>