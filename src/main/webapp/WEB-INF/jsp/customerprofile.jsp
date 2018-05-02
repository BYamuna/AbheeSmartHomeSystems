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
    					<h4 style="float:right; margin-top:20px;" id="edit"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
    				</div>
    			</div><div class="clearfix"></div>
    			<div class="inp">
    			<form:form  modelAttribute="customerProfile"  class="form-horizontal"  method="POST">
    				<div class="col-xs-6">
    					<div class="col-sm-3">
    						<label style="margin-top:28px;">First Name: </label>
    					</div>
    					<div class="col-sm-9">
    						<form:input path="firstname" style="width:93%; margin-top:20px;" class="form-control" type="text" placeholder="First Name"  disabled="true"/>
    					</div><div class="clearfix"></div>
    				</div>
    				<div class="col-xs-6">
    					<div class="col-sm-3">
    						<label style="margin-top:28px;">Last Name: </label>
    					</div>
    					<div class="col-sm-9">
    						<form:input path="lastname" style="width:100%; margin-top:20px;" class="form-control" type="text" placeholder="Last Name" disabled="true" />
    					</div><div class="clearfix"></div>
    				</div><div class="clearfix"></div>
    				<div class="col-xs-6">
    					<div class="col-sm-3">
    						<label style="margin-top:28px;">Email Id: </label>
    					</div>
    					<div class="col-sm-9">
    						<form:input path="email" style="width:93%; margin-top:20px;" class="form-control" type="text" placeholder="Emailid" disabled="true"/>
    					</div><div class="clearfix"></div>
    				</div>
    				<div class="col-xs-6">
    					<div class="col-sm-3">
    						<label style="margin-top:28px;">Mobile No: </label>
    					</div>
    					<div class="col-sm-9">
							<form:input path="mobilenumber" style="width:100%; margin-top:20px; float:left;" class="form-control" type="text" placeholder="Mobile Number" disabled="true"/>
						</div><div class="clearfix"></div>
    				</div><div class="clearfix"></div>
					<div class="col-sm-1">
						<label style="margin-top:35px;">Address: </label>
					</div>
					<div class="col-sm-11">
    					<form:textarea path= "address" style="margin-top:20px;" class="form-control" type="text" placeholder="Address" disabled="true" />
					</div>
    				</form:form>
    			</div>
    		</div>    		
    		<div id="order">
    			<div style="margin-top:-15px;" class="odata">
    				<h1>Pavan</h1>
    				
    				<div class="table-responsive">
								<table class="table table-bordered priority prioritybg"	style="border: 1px solid #0460a4;" id="customerOrderTable">
									<thead>
										<tr style="background-color: #0460a4; color: #fff; text-align: center;">
											<th>OrderId</th>
											<th>Product(s)</th>
											<th>Date of Purchased</th>
											<th>Warranty Expired Date</th>
											<th>Price</th>
										</tr>
										
									
									</thead>
									<tbody>


									</tbody>
									

								</table>
							</div>
    				
    			</div>
    		</div>
    	</div><div class="clearfix"></div>
    </div>

<script type="text/javascript">

$("#myorders").click(function(){
	var id=$(this).attr('href');
	$(id).css('display','block');
	$(".cdata").css('display','none');
});
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