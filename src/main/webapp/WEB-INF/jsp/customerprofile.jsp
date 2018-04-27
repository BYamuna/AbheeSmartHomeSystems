<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
    
<style>
</style>
    

    <%@include file="abheeheader.jsp" %>
    
    <div class="container">
    	<div class="col-sm-3">
    		<div class="con">
    			<h4 class="active"><a href="${baseurl}/customerprofile"><span class="glyphicon glyphicon-user"></span> My Profile</a></h4>
    			<h4><a href="#order"><span class="glyphicon glyphicon-briefcase"></span> My Orders</a></h4>
    		</div>
    	</div>
    	<div class="col-sm-9">
    		<div class="cdata">
    			<div style="margin-top:-13px;" class="ed">
    				<div class="col-xs-6">
    					<h3>Personal Information</h3>
    				</div>
    				<div class="col-xs-6">
    					<h4 style="float:right; margin-top:20px;" id="edit"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
    				</div>
    			</div><div class="clearfix"></div>
    			<div class="inp">
    			<form:form  modelAttribute="customerProfile"  class="form-horizontal"  method="POST">
    				<div class="col-xs-6">
    					<form:input path="firstname" style="width:95%; margin-top:20px;" class="form-control" type="text" placeholder="First Name"  disabled="true"/>
    				</div>
    				<div class="col-xs-6">
    					<form:input path="lastname" style="width:95%; margin-left:20px; margin-top:20px;" class="form-control" type="text" placeholder="Last Name" disabled="true" />
    				</div><div class="clearfix"></div>
    				<div class="col-xs-6">
    					<form:input path="email" style="width:95%; margin-top:20px;" class="form-control" type="text" placeholder="Emailid" disabled="true"/>
    				</div>
    				<div class="col-xs-6">
    					<form:input path="mobilenumber" style="width:95%; margin-left:20px; margin-top:20px;" class="form-control" type="text" placeholder="Mobile Number" disabled="true"/>
    				</div><div class="clearfix"></div>
    				<form:textarea path= "address" style="margin-top:20px;" class="form-control" type="text" placeholder="Address" disabled="true" />
    				</form:form>
    			</div>
    		</div>
    		<div class="order">
    			<img src="${baseurl }/reportDocuments/1.jpg">
    		</div>
    	</div><div class="clearfix"></div>
    </div>
<script type="text/javascript">
$('#customer').blur(function() {
	var customer=$(this).val();

	$.ajax({
				type : "POST",
				url : "editCustomerProfile",
				data : {"customer":customer},
				dataType : "text",
				beforeSend : function() {
		             $.blockUI({ message: 'Please wait' });
		          }, 
				success : function(data) {
					if(data ==='true')
						{
						//alert("username already exists")
	 					$('#customer').css('border-color', 'red');
						 $('#submit1').prop('disabled', true);
						}
					else
						{
						$('#customer').css('border-color', 'none');
						$('#edit').prop('disabled', false);
						}
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});

		}); 
</script>
<%@include file="abheefooter.jsp" %>