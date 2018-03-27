<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
	
	<div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="dashBoard">Home</a></li>
		<li>Customer Registration form</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="background-color:  white !important; padding-top: 15PX;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Customer List</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
					<span id="PasswordSuccessmsg"></span>
					<input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
						<div class="table-responsive" id="tableId">
							<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
								<thead><tr><th>Customer ID</th><th>Name</th><th>surname</th><th>Email</th> <th>Mobileno</th><th></th><th></th></tr></thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
<!-- 		<a class="btn btn-info btn-lg"  onclick="PopupFillingStation();">Add Gas</a><br><br> -->
		<div class="row" id="moveTo">
			<div class="col-md-12 col-sm-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Add Customer</h4>
					</div>
					
					<form:form modelAttribute="custReg" action="custreg" class="form-horizontal " method="Post">
	                  <form:hidden path="id"/>
					<div class="col-md-6"><br>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">First Name<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="firstname" class="form-control validate" placeholder="Enter name"/>
									</div>
								</div>
								
								</div>
								<div class="col-md-6"><br>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Last name<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="lastname" class="form-control validate" placeholder="Enter Surname"/>
									</div>
								</div>
								
								</div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Email<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="email" class="form-control" placeholder="Enter Emailid"/>
									</div>
								</div></div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Mobile Number<span class="impColor">*</span></label>
								<div class="col-md-6">
										<form:input path="mobilenumber" class="form-control validate onlyNumbers" placeholder="Enter Mobileno"/>
									</div>
								</div>
								</div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">password<span class="impColor">*</span></label>
								<div class="col-md-6">
										<form:password path="password" class="form-control validate onlyNumbers" placeholder="Enter Mobileno"/>
									</div>
								</div>
								</div>
						<div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar text-center">
					      			<input type="submit" id="submit1" value="Submit" class="btn-primary btn"/>
					      			<input type="reset" value="Reset" class="btn-danger btn cancel"/>
				      			</div>
				      		</div>
				      	</div>
			      	</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
			<!-- container -->
			
			
			
			<!-- Modal -->
<!-- <div id="passwordModal" tabindex="-1" class="modal fade" role="dialog">
  <div class="modal-dialog">

    Modal content
    <div class="modal-content">
        <div class="panel panel-primary" style="margin-bottom:-20px;">
      <div class="panel-heading">
						<h4><i class="fa  fas fa-key "> Change Password</i>	</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true" style="color:#FFFFFF;">&times;</span>
        </button>
						<div class="options">
							
						</div>
					</div>
      <div class="modal-body">
        <input type="hidden" id="userid" />
				<div class="col-md-12">
				<p id="passwordErrormsg" style="color:red;"></p>
					<div class="form-group" id="passwordDiv">
						<label class="col-md-4 control-label no-padding-left">New Password<span class="impColor">*</span></label>
						<div class="col-md-6">
							<input type="password" id="npassword" class="form-control"	placeholder="Enter  New Password" />
						</div>
						<div class="col-md-2"></div>
					</div>

				</div><div class="clearfix"></div><br>
				<div class="col-md-12">
					<div class="form-group" id="passwordDiv">
						<label class="col-md-4 control-label no-padding-left">Confirm Password<span class="impColor">*</span></label>
						<div class="col-md-6">
							<input type="password" id="cpassword" class="form-control"	placeholder="Re-Enter New Password" />
						</div>
						<div class="col-md-2"></div>
					</div>
				</div><div class="clearfix"></div>
<br>
				<div class="modal-footer" style="border:none;">
				<input type="submit" id="passwordModelsubmit" onclick="changePasswordModal();" class="btn btn-success"	value="Register" />
				 <input class="btn-danger btn cancel"	data-dismiss="modal" type="reset" value="Close" />
			</div>
		</div>

  </div>
</div>
</div>
</div>
<p data-toggle='modal' id="password_modal" data-target='#passwordModal'></p>
 -->
 </body>
<script type="text/javascript">
var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th> Cust ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>MobileNo</th><th style="text-align: center;">Options</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		if(orderObj.enabled == "1"){
			var deleterow = "<a class='deactivate' onclick='deleteCustomer("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>";
			var cls="activecss";
		}else{  
			var deleterow = "<a class='activate' onclick='deleteCustomer("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>";
			var cls="inactivecss";
		}
		var edit = "<a class='edit editIt' onclick='editEmployee("	+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr class='"+ cls +"'>"
			 + "<td title='"+orderObj.userId+"'>"+ orderObj.userId + "</td>" 
			+ "<td title='"+orderObj.firstname+"'>"+ orderObj.firstname + "</td>"
			+ "<td title='"+orderObj.lastname+"'>"+ orderObj.lastname + "</td>"
			+ "<td title='"+orderObj.email+"'>"+ orderObj.email + "</td>"
			+ "<td title='"+orderObj.mobilenumber+"'>"+ orderObj.mobilenumber + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	if(isClick=='Yes') $('.datatables').dataTable();
	
} 


function editEmployee(id) {
	
	$("#id").val(serviceUnitArray[id].id);
	$("#firstname").val(serviceUnitArray[id].firstname);
	$("#lastname").val(serviceUnitArray[id].lastname); 
	$("#mobilenumber").val(serviceUnitArray[id].mobilenumber);
	$("#email").val(serviceUnitArray[id].email);
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
	document.getElementById("username").readOnly  = true;
	//document.querySelector("password").required = false;
    $("#passwordDiv").hide();
    var idArray = $.makeArray($('.validate').map(function() {
    	return this.id;
    }));
}

/* function changePasswordModal(){

	
	var id=$("#userid").val();
	var npassword=$("#npassword").val();
	var cpassword=$("#cpassword").val();
	
	if(npassword==null || npassword == "" || npassword == undefined){
		
		$("#passwordErrormsg").text("Please Enter New Password");
		return false;
	}
	if(npassword == cpassword ){
		var formData = new FormData();
		formData.append('id', id);
		formData.append('npassword', npassword);
		
		$.fn.makeMultipartRequest('POST', 'adminChangePassword', false,
				formData, false, 'text', function(data) {
			
			$("#passwordModal").modal('toggle');
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			console.log(jsonobj.allOrders1);
			
			
			$.each(JSON.parse(data),function(key,value) {
				console.log(value);
				alert(value);
				$("#npassword").val('');
	           $("#cpassword").val('');
				$(".msgcss1").css('visibility', 'visible');
				$(".msgcss1").show();
				$("#msg1").text(value);
				$("#msg1").fadeOut(5000);
				
			});
			
				});
		
	}else{
		$("#passwordErrormsg").text("Password Doesn't match");
	}
		
}
var userData="";
function getPasswordModal(id)
{
	userData=$('#userid').val(id);
	$('#password_modal').trigger('click');
}







$('#username').blur(function() {
	var username=$(this).val();

	$.ajax({
				type : "GET",
				url : "getUserName",
				data : {"username":username},
				dataType : "text",
				beforeSend : function() {
		             $.blockUI({ message: 'Please wait' });
		          }, 
				success : function(data) {
					if(data ==='true')
						{
						//alert("username already exists")
	 					$('#username').css('border-color', 'red');
						 $('#submit1').prop('disabled', true);
						}
					else
						{
						$('#username').css('border-color', 'none');
						$('#submit1').prop('disabled', false);
						}
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});

		}); 
		







function validate(id, errorMessage)
{
	var styleBlock = '.placeholder-style.placeholder-style::-moz-placeholder {color: #cc0000;} .placeholder-style::-webkit-input-placeholder {color: #cc0000;}';
	if($('#'+id).val() ==  null || $('#'+id).val() == ""  || $('#'+id).val()=="undefined" ) {
		$('style').append(styleBlock);
		$('#'+id).css('border-color','#cc0000');
		$('#'+id).css('color','#cc0000');
		$('#'+id).attr('placeholder',errorMessage);
		$('#'+id).addClass('placeholder-style your-class');
//			$('#'+id).css('color','#cc0000');
//			$('#'+id+'Error').text(errorMessage);
	}else{
		$('#'+id).css('border-color','');
		$('#'+id).removeClass('placeholder-style your-class');
//			$('#'+id).css('color','');
//			$('#'+id+'Error').text("");
	}
	
}
*/
function deleteCustomer(id,status){
	var checkstr=null;
	if(status == 0){
		 checkstr = confirm('Are you sure you want to Deactivate?');
	}else{
		 checkstr = confirm('Are you sure you want to Activate?');
	}
	if(checkstr == true){
		var formData = new FormData();
	    formData.append('id', id);
	    formData.append('status', status);
		$.fn.makeMultipartRequest('POST', 'deleteCustomer', false, formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			toolTips(); //calling tool tips defined at header
			$('#inActive').prop('checked', false);
		});
	}
}

function inactiveData() {
	var status="0";
	if($('#inActive').is(":checked") == true){
		status="0";
	}else{
		status="1";
	}
		var formData = new FormData();
		formData.append('status', status);
		
		$.fn.makeMultipartRequest('POST', 'inActiveCust', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			console.log(jsonobj.allOrders1);
				});
		
}
   
 $("#pageName").text("Customer Registration");
$(".abheecust").addClass("active"); 
</script>