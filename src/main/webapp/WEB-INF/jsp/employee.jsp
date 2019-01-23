<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<style>
.panel-heading .close {
    margin-top: -2px;
    color: #fff;
    opacity: 1;
}
</style>
	<div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="dashBoard">Home</a></li>
		<li>Employee Master</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="background-color:  white !important; padding-top: 15PX;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Employee List</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
					<span id="PasswordSuccessmsg"></span>
					<input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
						<div class="table-responsive" id="tableId">
							<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
								<thead><tr><th>Employee ID</th><th>User Name</th><th>firstname</th><th>Last Name</th> <th>Department</th><th>Designation</th><th></th></tr></thead>
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
						<h4 id="emp">Add Employee</h4>
					</div>
					<br>
					<!-- <div class="clearfix"></div> -->
					<form:form modelAttribute="userForm" action="employee" class="form-horizontal " method="Post">
	                  <form:hidden path="id"/>
	                  <div class="sep">
					<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Username<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="username" class="form-control validate2" onfocus="removeBorder(this.id)" placeholder="Username"/>
									</div>
								</div>
								
								</div> <div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">First Name<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="firstname" class="form-control validate2 onlyCharacters" onfocus="removeBorder(this.id)" placeholder="First Name"/>
									</div>
								</div></div>
								<div class="clearfix"></div>
								</div>
								<div class="sep">
								
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Last Name<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="lastname" class="form-control validate2 onlyCharacters" onfocus="removeBorder(this.id)" placeholder="Last Name"/>
									</div>
								</div>
								</div><div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Mobile<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="mobilenumber" class="form-control validate2 numericOnly2" onfocus="removeBorder(this.id)" maxlength="10"  placeholder="Mobile Number"/>
									</div>
								</div>
								</div><div class="clearfix"></div>
								</div>
								<div class="sep">
								
								<div class="col-md-6">
								<div class="form-group" id="passwordDiv">
									<label class="col-md-3 control-label no-padding-right">Password<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:password path="password" class="form-control validate2" onfocus="removeBorder(this.id)"  placeholder="Password"/>
									</div>
								</div>
								
								</div>
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Email<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="email" class="form-control validate2 emailOnly" onfocus="removeBorder(this.id)" placeholder="Email"/>
									</div>
								</div></div></div><div class="clearfix"></div>
								<div class="sep">
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Aadhar Number</label>
									<div class="col-md-6">
										<form:input path="aadharno" class="form-control"  placeholder="Aadhar number" maxlength="12"/>
									</div>
								</div>
								</div>
								
								
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Designation<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:select path="designation" class="form-control validate2 " onfocus="removeBorder(this.id)" >
											<form:option value="">-- Select Designation --</form:option>
											<form:options items="${roles}"/>
										</form:select>
									</div>
								</div></div></div><div class="clearfix"></div>
								<div class="sep">
								 
								<security:authorize access="hasRole('ROLE_ADMIN')">
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Report To<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:select path ="reportto" class="form-control validate2" onfocus="removeBorder(this.id)">
											<form:option value="">-- Select Report to --</form:option>
								     		<form:options items="${reportto}"/>
										</form:select>
									</div>
								</div></div>
								 
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Branch<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:select path ="BranchId" class="form-control validate2" onfocus="removeBorder(this.id)">
											<form:option value="">-- Select Branch--</form:option>
								     		<form:options items="${orgNames}"/>
										</form:select>
									</div>
								</div></div><div class="clearfix"></div>
								</security:authorize>
								</div>
						<div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar text-center">
					      			<input type="submit" id="submit2" value="Submit" class="btn-primary btn"/>
					      			<input type="reset" id="reset" value="Reset" onClick="window.location.reload()" class="btn-danger btn cancel"/>
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
<div id="passwordModal" tabindex="-1" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
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
							<input type="password" id="npassword" class="form-control"	maxlength="6" placeholder="New Password" />
						</div>
						<div class="col-md-2"></div>
					</div>

				</div><div class="clearfix"></div><br>
				<div class="col-md-12">
					<div class="form-group" id="passwordDiv">
						<label class="col-md-4 control-label no-padding-left">Confirm Password<span class="impColor">*</span></label>
						<div class="col-md-6">
							<input type="password" id="cpassword" class="form-control"	maxlength="6" placeholder="Confirm New Password" />
						</div>
						<div class="col-md-2"></div>
					</div>
				</div><div class="clearfix"></div>
<br>
				<div class="modal-footer" style="border:none;">
				<input type="submit" id="passwordModelsubmit" onclick="changePasswordModal();" class="btn btn-success"	value="Submit" />
				 <input class="btn-danger btn cancel"	data-dismiss="modal" type="reset" value="Close" />
			</div>
		</div>

  </div>
</div>
</div>
</div>
<p data-toggle='modal' id="password_modal" data-target='#passwordModal'></p>
</body>
<script type="text/javascript">

var editFields =0;


var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>User Id</th><th>Report To</th><th>First Name</th><th>Last Name</th><th>Designation</th><th>Mobile Number</th><th>Branch Name</th><th>Aadhar Number</th><th style="text-align: center;">Options</th><th></th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick='deleteEmployee("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>";
			var cls="activecss";
		}else{  
			var deleterow = "<a class='activate' onclick='deleteEmployee("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>";
			var cls="inactivecss";
		}
		var edit = "<a class='edit editIt' onclick='editEmployee("	+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr class='"+ cls +"'>"
			/* + "<td title='"+orderObj.id+"'>"+ orderObj.id + "</td>" */
			+ "<td title='"+orderObj.userId+"'>"+ orderObj.userId + "</td>"
			/* + "<td title='"+orderObj.username+"'>"+ orderObj.username + "</td>" */
			+ "<td title='"+orderObj.reportto+"'>"+ orderObj.reportName + "</td>"
			+ "<td title='"+orderObj.firstname+"'>"+ orderObj.firstname + "</td>"
			+ "<td title='"+orderObj.lastname+"'>"+ orderObj.lastname + "</td>"
			+ "<td title='"+orderObj.designationName+"'>"+ orderObj.designationName + "</td>"
			+ "<td title='"+orderObj.mobilenumber+"'>"+ orderObj.mobilenumber + "</td>"
			+ "<td title='"+orderObj.branchName+"'>"+ orderObj.branchName + "</td>"
			+ "<td title='"+orderObj.aadharno+"'>"+ orderObj.aadharno + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
			+ "<td ><a style='cursor:pointer' onclick='getPasswordModal("+ orderObj.id +")'>Change Password</a></td>" 
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	if(isClick=='Yes') $('.datatables').dataTable();
	
}
function changePasswordModal(){

	
	var id=$("#userid").val();
	var npassword=$("#npassword").val();
	var cpassword=$("#cpassword").val();
	
	if(npassword==null || npassword == "" || npassword == undefined){
		
		$("#passwordErrormsg").text("Please Confirm New Password");
		return false;
	}
	if(npassword == cpassword ){
		var formData = new FormData();
		formData.append('id', id);
		formData.append('npassword', npassword);
		
		$.fn.makeMultipartRequest('POST', 'adminChangePassword', false,
				formData, false, 'text', function(data) {
			alert("password changed successfully");
			$("#passwordModal").modal('show');
			$("#passwordModal").modal('toggle');
			/* $.each(JSON.parse(data),function(key,value) {
				console.log(value); */
				//alert(value);
				$("#npassword").val('');
	           $("#cpassword").val('');
				$(".msgcss1").css('visibility', 'visible');
				$(".msgcss1").show();
				//$("#msg1").text(value);
				$("#msg1").fadeOut(5000);
				
			});
			
				
		
	}else{
		$("#passwordErrormsg").text("Password Doesn't match");
		  setTimeout(function(){ $("#passwordErrormsg").text(""); }, 3000);
		
		$("#npassword").val('');
      $("#cpassword").val('');
	}
		
}
var userData="";
function getPasswordModal(id)
{
	userData=$('#userid').val(id);
	makeEmptyPasswordModal()
	$('#password_modal').trigger('click');
	
}


function editEmployee(id) {
	editFields =id;
	$("#emp").text("Edit Employee");
	$("#id").val(serviceUnitArray[id].id);
	//$("#userId").val(serviceUnitArray[id].userId);
	$("#username").val(serviceUnitArray[id].username);
	$("#password").val(serviceUnitArray[id].password);
	$("#firstname").val(serviceUnitArray[id].firstname);
	$("#lastname").val(serviceUnitArray[id].lastname);
	$("#mobilenumber").val(serviceUnitArray[id].mobilenumber);
	$("#designation").val(serviceUnitArray[id].designation);
	$("#reportto").val(serviceUnitArray[id].reportto);
	$("#email").val(serviceUnitArray[id].email);
	$("#aadharno").val(serviceUnitArray[id].aadharno);
	$("#BranchId").val(serviceUnitArray[id].branchId);
	$("#submit2").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
	//$("#reset").hide();
	document.getElementById("username").readOnly  = true;
	
	//document.querySelector("password").required = false;
	
    $("#passwordDiv").hide();
    
    var idArray = $.makeArray($('.validate').map(function() {
    	return this.id;
    }));
    console.log(idArray);
}

function deleteEmployee(id,status){
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
		$.fn.makeMultipartRequest('POST', 'deleteUser', false, formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			if(status==1)
			{
				alert("Employee Activated Successfully");
			}
					
			else
				{
				alert("Employee Deactivated Successfully");
				}
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			toolTips(); //calling tool tips defined at header
			$('#inActive').prop('checked', false);
		});
	}
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
		

$('#email').blur(function() {

	var cemail=$(this).val();
	
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  if( regex.test(cemail))
		  {
		  $('#submit1').prop('disabled', false);
	
	
	$.ajax({
				type : "POST",
				url : "checkEmpExstbyemail",
				data :"cemail="+cemail+"&editFields="+editFields,
				dataType : "text",
				beforeSend : function() {
		             $.blockUI({ message: 'Please wait' });
		          }, 
				success : function(data) {
					if(data ==='true')
						{
						//alert("username already exists")
	 					$('#email').css('border-color', 'red');
	 					 $('#submit1').prop('disabled', true);
						}
					else
						{
						$('#email').css('border-color', 'none');
						 $('#submit1').prop('disabled', false);
						}
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});
		  }
	  else
		  
	{
		  $('#email').css('border-color', 'red');
		  $('#submit1').prop('disabled', true);
		  
	}

		}); 




$('#mobilenumber').blur(function() {
	var cmobile=$(this).val();
	
	
	 
	 if(cmobile.length != 10 )
		 {
		 alert("Mobile Number Length Must Be 10 Digits")
		 $('#mobilenumber').css('border-color', 'red');
		 
			 $('#submit1').prop('disabled', true);
		 
		 }
	 
	
	 else
		 {
	
	
	$.ajax({
				type : "POST",
				url : "checkEmpExst",
				data :"cmobile="+cmobile+"&editFields="+editFields,
				dataType : "text",
				beforeSend : function() {
		             $.blockUI({ message: 'Please wait' });
		          }, 
				success : function(data) {
					if(data ==='true')
						{
						alert("Mobile Number already exists")
	 					$('#mobilenumber').css('border-color', 'red');
	 					 $('#submit1').prop('disabled', true);
	 					subValidation =false;
						}
					 else
						{
						$('#mobilenumber').css('border-color', 'none');
						 $('#submit1').prop('disabled', false);
						 subValidation =true;
						} 
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});
	
		 }

		}); 
		



/* function validate(id, errorMessage)
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
function inactiveData() {
	var status="0";
	if($('#inActive').is(":checked") == true){
		status="0";
	}else{
		status="1";
	}
		var formData = new FormData();
		formData.append('status', status);
		
		$.fn.makeMultipartRequest('POST', 'inActiveEmp', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			//console.log(jsonobj.allOrders1);
			 toolTips();
				});
		
}
 function makeEmptyPasswordModal()
 {
 	
 	$('#npassword').val("");
 	$('#npassword').css('border-color', 'none');
 	$('#cpassword').val("");
 	$('#cpassword').css('border-color', 'none');
 }

 $("#pageName").text("Employee Master");
$(".employee").addClass("active"); 
</script>