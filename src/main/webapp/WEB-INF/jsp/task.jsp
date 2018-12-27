<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<style>
.modal-header .close {
    margin-top: -2px;
    color: #fff;
    opacity: 1;
}
</style>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script type="text/javascript"
	src="js/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css">
	
<div class="clearfix"></div>
<ol class="breadcrumb">
	<li><a href="dashBoard">Home</a></li>
	<li>Service Request Master</li>
</ol>

<br>
<div class="clearfix"></div>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Service Request List</h4>
					<div class="options">
						<a href="javascript:;" class="panel-collapse"><i
							class="fa fa-chevron-down"></i></a>
					</div>
				</div>
				<div class="panel-body collapse in">
					<input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label
						class="form-check-label">Show Inactive List</label>
					<div class="table-responsive" id="tableId">
						<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
							<thead>
								<tr>
									<th>Service Request No</th>
									<th>ServiceType</th>
									<th>Severity</th>
									<th>Priority</th>
									<th>Assigned To</th>
									<th>Subject</th>
									<th>Service Request Deadline</th>
									<th>Service Request Status</th>
									<th>CreateTime</th>
									<th style="text-align: center;">Options</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row" id="moveTo">
		<div class="col-md-12 col-sm-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4 id="service">Service Request</h4>
					<!-- <div class="options"><a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a></div> -->
				</div>
			<form:form class="form-horizontal" modelAttribute="taskf" action="savetask1" method="post" enctype="multipart/form-data">
				<form:hidden path="id"/>
					<div class="panel-body">
					  <security:authorize access="hasRole('ROLE_ADMIN')">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label ">Service
										Type <span class="impColor">*</span>
									</label>
									<form:select path="ServiceType" class="col-xs-10 col-sm-5 validate" onfocus="removeBorder(this.id)">
										<form:option value="" label="--- Select ---" />
										<form:options items="${category}" />
									</form:select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label ">Severity
										<span class="impColor">*</span>
									</label>
									<form:select path="severity" class="col-xs-10 col-sm-5 validate" onfocus="removeBorder(this.id)">
										<form:option value="" label="--- Select ---" />
										<form:options items="${severity}" />
									</form:select>
									<span class="hasError" id="stationnameError"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label ">Priority
										<span class="impColor">*</span>
									</label>
									<form:select path="priority" class="col-xs-10 col-sm-5 validate" onfocus="removeBorder(this.id)">
										<form:option value="" label="--- Select ---" />
										<form:options items="${priority}"></form:options>
									</form:select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label ">Assigned To <span class="impColor">*</span>
									</label>
									<form:select path="assignto" class="col-xs-10 col-sm-5 validate" onfocus="removeBorder(this.id)">
										<form:option value="" label="--- Select ---" />
										<form:options items="${userNames}" />
									</form:select>
									<span class="hasError" id="stationnameError"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label ">Summary
										<span class="impColor">*</span>
									</label>
									<form:input path="subject" placeholder="Summary" class="col-xs-10 col-sm-5 validate" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label">Service Request DeadLine <span class="impColor">*</span>
									</label>
										<form:input type="text" path="taskdeadline" class="col-xs-10 col-sm-5 validate1"  onfocus="removeBorder(this.id)"/>
										<span class="hasError" id="stationnameError"></span>
								</div>
							</div>
						</div>
						 </security:authorize>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label">
										Status <span class="impColor">*</span>
									</label>
									<form:select path="kstatus" class="col-xs-10 col-sm-5 validate" onfocus="removeBorder(this.id)">
										<form:option value="" label="--- Select ---" />
										<form:options items="${taskstatus}" />
									</form:select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Description
										<!-- <span class="impColor">*</span> -->
									</label>
										<form:textarea path="description" class="col-xs-10 col-sm-5" placeholder="Description" />
										<span class="hasError" id="stationnameError"></span>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label">Service Request Number
										<!-- <span class="impColor">*</span> -->
									</label>
									<form:input path="taskno" placeholder="Service Request Number" class="col-xs-10 col-sm-5" />
								</div>
							</div>
							
							
						</div>
						<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label
									class="ace-file-input ace-file-multiple col-sm-3 col-md-push-3 control-label no-padding-right">Attach
									File(s)</label>
								<div class="col-md-8">
									<input type="file" name="file1" id="file1" class="col-sm-9 col-md-push-5 validate1" multiple="multiple" style="margin: 7px 0px 0px 0px;">
								</div>
							</div>
						</div>
						<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Comment
										<span class="impColor">*</span>
									</label>
										<form:textarea path="addComment" class="col-xs-10 col-sm-5 validate " placeholder="Comment" /><span class="hasError" id="stationnameError"></span>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Warranty</label>
								<div class="col-md-3 ">
										<input type="checkbox" id="warranty"  value ="0"/>
									</div>
								</div>
							</div>
							</div>
							
						<%-- <div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label ">Tax
										<!-- <span class="impColor">*</span> -->
									</label>
									<form:input path="tax" placeholder="Tax" class="col-xs-10 col-sm-5" />
								</div>
							</div> --%>
					<%-- <security:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label ">Total
										<span class="impColor">*</span>
 									</label>
									<form:input path="total" placeholder="Total" class="col-xs-10 col-sm-5" />
								</div>
							</div>
						
							
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label ">Discount
										<span class="impColor">*</span>
									</label>
									<form:input path="discount" placeholder="Discount" class="col-xs-10 col-sm-5" />
								</div>
							</div>
						</div> --%>
							<%-- <div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label ">Amount Received
										<span class="impColor">*</span>
									</label>
									<form:input path="amountreceived" placeholder="Amountreceived" class="col-xs-10 col-sm-5" />
								</div>
							</div> 
						 </security:authorize> --%>
						<%-- <security:authorize access="hasRole('ROLE_USER')">	
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label">
										Payment Mode <span class="impColor">*</span>
									</label>
									<form:select path="paymentstatus" class="col-xs-10 col-sm-5 validate"
										onfocus="removeBorder(this.id)">
										<form:option value="" label="--- Select ---" />
										<form:options items="${paymentmode}" />
									</form:select>
								</div>
							</div>
							</security:authorize> --%>
						</div>	
						
						<div id="getting-started"></div>
					<div class="panel-footer">
						<div class="row">
							<div class="col-sm-12">
								<div class="btn-toolbar text-center">
									<input type="submit" id="submit1" value="Submit"
										class="btn-primary btn" /> <input type="reset" id="reset" value="Reset" onClick="window.location.reload()"
										class="btn-danger btn cancel" />
								</div>
							</div>
						</div>
					</div>
				</form:form>
				</div>
			</div>
		</div>
	</div>

<!-- Task History Modal Starts here-->
<div class="modal fade" id="myModal" data-backdrop="static"
	data-keyboard="false" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="background: #4f8edc;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Service Request History</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="table-responsive" id="HtableId">
						<table cellpadding="0" cellspacing="0" border="0"
							class="table table-striped table-bordered datatables"
							id="example">
							<thead>
								<tr class="info">
									<th>Date Modified</th>
									<th>User Name</th>
									<th>Attachment</th>
									<th>Change</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Modal Ends here-->



<!-- Task History 2 Modal Starts here-->
<div class="modal fade" id="myModal2" data-backdrop="static"
	data-keyboard="false" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="background: #4f8edc;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Service Request History</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="table-responsive" id="HtableId2">
						<table cellpadding="0" cellspacing="0" border="0"
							class="table table-striped table-bordered datatables"
							id="example2">
							<thead>
								<tr class="info">
									<th>Date Modified</th>
									<th>User Name</th>
									<th>Attachment</th>
									<th>Field</th>
									<th>Change</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Modal Ends here-->





<!-- add comment Modal Starts here-->
<div class="modal fade" id="formModal" data-backdrop="static"
	data-keyboard="false" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="background: #2973cf;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="color: white;">Add Comment</h4>
			</div>
			<div class="modal-body">
				<%-- <form class="form-horizontal" method="post">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-5">
								<div class="form-group">
									<!-- <input type=hidden path="id"/> -->

									<input type=hidden name="issueid" id="issueid" value="">
									<input type=hidden name="id" id="id" value=""> <label
										for="focusedinput" class="col-md-4 control-label">Status
										<span class="impColor">*</span>
									</label> <select name="kpstatus" id="kpstatus"
										class="col-xs-10 col-sm-7  validate2 " style="margin-top: 6px">
										<option value="">--select--</option>
										<c:forEach var="list" items="${kpstatuses}">
											<option value=${list.key}>${list.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group" style="width: 154%;">
									<label
										class="ace-file-input ace-file-multiple col-sm-3 control-label no-padding-right">Attach
										File(s)</label>
									<div class="col-md-9">
										<input type="file" name="fileupload[]" id="productpics"
											multiple style="margin: 8px 0px 0px 0px;">
									</div>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-7">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Comment
										<span class="impColor">*</span>
									</label>
									<div class="col-md-6">
										<input type="text" name="commet" id="commet"
											onkeyup="removeBorder(this.id)"
											class="form-control validate2" placeholder="Comment"
											style="width: 320px;"> <span class="hasError"
											id="stationnameError"></span>
									</div>
								</div>
							</div>

						</div>


						<div class="panel-footer">
							<div class="row">
								<div class="col-sm-12">
									<div class="btn-toolbar text-center">
										<input type="button" id="modelSubmit" value="Submit"
											onclick="submitCommet()" class="btn-primary btn" /> <input
											type="reset" value="Reset" class="btn-danger btn cancel1" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</form> --%>

			</div>

		</div>

	</div>
</div>
<!--  add comment model close here-->

<!--  Count Down timer model-->

<div class="modal fade" id="timeModal" data-backdrop="static"
	data-keyboard="false" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="background: #2973cf;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="color: white;">Service Request Count Down</h4>
			</div>
			<div></div>
			<div class="modal-body">
				<p id="demo"></p>

			</div>
		</div>
	</div>
</div>
<!-- deadline Model ends here -->

<!--  set security authorization true or false to isRoleExtero-->
<security:authorize access="hasRole('ROLE_USER')">
	<input id="isRole" type="text" class="hide" value="false" />
</security:authorize>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<input id="isRole" type="text" class="hide" value="true" />
</security:authorize>


<%-- <div class="modal fade" id="warrantyModal" data-backdrop="static" data-keyboard="false"  role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background: #2973cf;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="color: white;"> Add Product Warranty Details </h4>
        	</div>
        	<div class="modal-body">
					<form class="form-horizontal" action="productWarranty" method="post" >
					<div class="panel-body">
					<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<input type="hidden" id= "warrantyid" />
									<label for="focusedinput" class="col-md-6 control-label ">Product Model Name<span class="impColor">*</span>
									</label>
										
									<select id="productmodelid"	class="col-xs-10 col-sm-5 validate"	onfocus="removeBorder(this.id)">
										<option value="" label="--- Select ---" />
										<c:forEach var="list" items="${productmodelid}">
												<option value=${list.key}>${list.value} </option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
								<input type="hidden" id= "orderId" />
									<label for="focusedinput" class="col-md-6 control-label ">Customer ID	<span class="impColor">*</span>
									</label>	
									<select id="customerid"	class="col-xs-10 col-sm-5 validate"	onfocus="removeBorder(this.id)">
										<option value="" label="--- Select ---" />
										<c:forEach var="list" items="${customerid}">
												<option value=${list.key}>${list.value} </option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Purchased Date	<span class="impColor">*</span>
									</label>
										<input type="text" id="purchaseddate"	class="col-xs-10 col-sm-5 validate" />

								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Expired Date	<span class="impColor">*</span>
									</label>
										<input type="text" id="expireddate"
											class="col-xs-10 col-sm-5 validate" />

								</div>
							</div>
							
						</div>
						<div id="getting-started"></div>
					</div>
							
                    <div class="panel-footer">
				      <div class="row">
							<div class="col-sm-12">
								<div class="btn-toolbar text-center">
									<input type="submit" id="submit1" value="Submit" class="btn-primary btn" /> 
										<input type="reset" value="Reset" onClick="window.location.reload()" class="btn-danger btn cancel" />
								</div>
							</div>
						</div>	
			      	</div>
			      	</form>
					</div>
				</div>	
			</div>
		</div> --%>

<script type="text/javascript">


	/* $('#purchaseddate').datetimepicker({
	
		useCurrent : false,
		format : 'DD-MMM-YYYY',
		showTodayButton : true,
		sideBySide : true,
		
		toolbarPlacement : 'top',
		focusOnShow : false,
	
	});
	
	$('#expireddate').datetimepicker({
	
		useCurrent : false,
		format : 'DD-MMM-YYYY',
		showTodayButton : true,
		sideBySide : true,
		
		toolbarPlacement : 'top',
		focusOnShow : false,
	
	});
	
	function openWarrantyModal(){
			if($("#warranty").attr('checked',true))
			{
				$("#warrantyModal").modal('show');
			}
			else
			{
				$("#warrantyModal").modal('hide');
			}	
	} */


	$("#taskdeadline").keypress(function() {
		return false;
	})

	/* active or deactive task button will appear only to isRoleExterno true  */
	var isRole = $('#isRole').val();

	console.log(isRole);

	function makeEmpty() {

		$('#taskdeadline').val(" ");
		$('#subject').val(" ");
		$('#category').val("");
		$('#priority').val("");
		$("#severity").val("");
		$("#assignto").val("");
		$("#description").val("");

	}


//	var today = new Date();
var date = new Date();
var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
	$(document).ready(function() {
		
		
		//$("#taskdeadline").attr("disabled", "disabled"); 
		// 	$("#taskdeadline").attr('readonly', 'readonly');
		$('#taskdeadline').datetimepicker({
			useCurrent : false,
			format : 'DD-MMM-YYYY',
			minDate : today,
			showTodayButton : true,
			sideBySide : true,
			toolbarPlacement : 'top',
			focusOnShow : false,
		});
	});


	var loginUserDId = "1";
	var cuserid = "1";
	var listOrders1 = ${allOrders1};
	

	

	if (listOrders1 != "") {
		displayTable(listOrders1)
	}

	function displayTable(listOrders) {
		$('#tableId').html('');
		var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
				+ '<thead><tr><th>Service Request Number</th><th>Category</th><th>Model Name</th><th>CustomerID</th><th>ServiceType</th><th>Customer Sent Image</th><th>Priority</th><th>Task Created By</th><th>Service Request Status</th><th>Address</th><th>Requested Time</th><th style="text-align: center;">Options	</th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead);
		serviceUnitArray = {};

		$
				.each(
						listOrders,
						function(i, orderObj) {
							
								if (orderObj.uploadfile == undefined)
									orderObj.uploadfile = '';
								else {
									var list = orderObj.uploadfile
											.split('*');
									var uploadfile = '';
									for (var i = 0; i < list.length; i++) {
										uploadfile = uploadfile
												+ '<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
									}
									orderObj.uploadfile = uploadfile;
								} 	
							if (isRole == 'true') {
								if (orderObj.status == "1") {
									var deleterow = "<a class='deactivate' onclick='deletetask("
											+ orderObj.id
											+ ",0)'><i class='fa fa-eye'></i></a>"
								} else {
									var deleterow = "<a class='activate' onclick='deletetask("
											+ orderObj.id
											+ ",1)'><i class='fa fa-eye-slash'></i></a>"
								}

							} else if (isRole == 'false') {
								deleterow = " ";
							}

							var edit = "<a class='edit editIt' onclick='editTask("
									+ orderObj.id
									+ ")'><i class='fa fa-edit'></i></a>"

							var view = "<a class='view viewIt' onclick='viewTask("
									+ orderObj.id
									+ ")'>"
									+ orderObj.taskno
									+ "</a>"
							var history2 = "<a class='history historyit' onclick='viewTask2("
									+ orderObj.id
									+ ")'> <i class='fa fa-history'></i></a>"
							var view2 = "<a class='view viewIt' href='viewTicket?id="
									+ orderObj.taskno
									+ "&pgn=0'>"
									+ orderObj.taskno + "</a>"
									
						 var view3 = "<a class='view viewIt' href='viewCustomerDetails?id="
										+ orderObj.customer_id
										+ "&pgn=0'>"
										+ orderObj.customer_id + "</a>"
										
									
									
									
							var comment = "<a class='comment commentIt' onclick='addComment("
									+ orderObj.id
									+ ")'>   <i class='fa fa-comments'></i></a>"
							var time = "<a class='time timeIt' onclick='showdeadline("
									+ orderObj.id
									+ ")'> <i class='fa fa-hourglass-half'></i> </a>"
							var history = "<a class='history historyit' onclick='viewTask("
									+ orderObj.id
									+ ")'> <i class='fa fa-history'></i></a>"

							serviceUnitArray[orderObj.id] = orderObj;
							var tblRow = "<tr>"
									+ "<td title='"+orderObj.taskno+"'>"
									+ view2
									+ "</td>"
									+ "<td title='"+orderObj.category+"'>"
									+ orderObj.category
									+ "</td>"
									+ "<td title='"+orderObj.modelname+"'>"
									+ orderObj.modelname
									+ "</td>"
									+ "<td title='"+orderObj.customer_id+"'>"
									+ view3
									+ "</td>"
									+ "<td title='"+orderObj.servicetypename+"'>"
									+ orderObj.servicetypename
									+ "</td>"
									+ "<td title='"+orderObj.uploadfile+"'>"
									+ orderObj.uploadfile
									+ "</td>"
									+ "<td title='"+orderObj.priority+"'>"
									+ orderObj.priority
									+ "</td>"
									+ "<td title='"+orderObj.subject+"'>"
									+ orderObj.subject
									+ "</td>"
									+ "</td>"
									+ "<td title='"+orderObj.statusname+"'>"
									+ orderObj.statusname
									+ "</td>"
									+ "<td title='"+orderObj.communicationaddress+"'>"
									+ orderObj.communicationaddress
									+ "</td>"
									/* + "<td title='"+orderObj.amountreceived+"'>"
									+ orderObj.amountreceived
									+ "</td>"
									+ "<td title='"+orderObj.discount+"'>"
									+ orderObj.discount
									+ "</td>"
									+ "<td title='"+orderObj.tax+"'>"
									+ orderObj.tax
									+ "</td>"
									+ "<td title='"+orderObj.total+"'>"
									+ orderObj.total */
									+ "<td title='"+orderObj.requesttime+"'>"
									+ orderObj.requesttime
									+ "</td>"
									/* + "<td title='"+orderObj.paymentstatus+"'>"
									+ orderObj.paymentstatus
									+ "</td>" */
									+ "<td style='text-align: center;white-space: nowrap;'>"
									+ edit
									+ "&nbsp;&nbsp;"
									+ deleterow
									+ "&nbsp;&nbsp;"
									+ time
									+ "&nbsp;&nbsp;"
									+ "</td>" + "</tr>";
							$(tblRow).appendTo("#tableId table tbody");
						});
		if (isClick == 'Yes')
			$('#example').dataTable();

	}

	function editTask(id) {
		//alert("hello");
		$("#service").text("Edit Service Request");
		$("#id").val(serviceUnitArray[id].id);
		$("#subject").val(serviceUnitArray[id].subject);
		$("#taskno").val(serviceUnitArray[id].taskno);
		$("#category").val(serviceUnitArray[id].categoryid);
		$("#severity").val(serviceUnitArray[id].severityid);
		$("#priority").val(serviceUnitArray[id].priorityid);
		$("#ServiceType").val(serviceUnitArray[id].servicetypeid);
		$("#assignby").val(serviceUnitArray[id].assignbyid);
		$("#assignto").val(serviceUnitArray[id].assignto);
		$("#uploadfile").val(serviceUnitArray[id].uploadfile);
		$("#description").val(serviceUnitArray[id].description);
		$("#taskdeadline").val(serviceUnitArray[id].taskdeadline);
		$("#kstatus").val(serviceUnitArray[id].kstatus);
		$("#submit1").val("Update");
		$(window).scrollTop($('#moveTo').offset().top);
		$("#reset").hide();
		document.getElementById("description").readOnly  = true;
		document.getElementById("taskno").readOnly  = true;
		//document.getElementById("ServiceType").attr  = true;
		/*var idArray = $.makeArray($('.validate').map(function() {
	    	return this.id;
	    })); */
	   $("#ServiceType").attr('disabled', true);
	    /*$("#description").attr('disabled', true); */ 
	}

	/* view task history */

	function viewTask(id) {
		var formData = new FormData();
		formData.append('id', id);
		$.fn
				.makeMultipartRequest(
						'POST',
						'viewTask',
						false,
						formData,
						false,
						'text',
						function(data) {
							var jsonobj = $.parseJSON(data);
							var alldata = jsonobj.list;
							$('#HtableId').html('');
							var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
									+ '<thead><tr><th>Date Modified</th><th>User Name</th><th>Status</th><th>Attachment</th><th>Comment</th></tr></thead><tbody></tbody></table>';
							$('#HtableId').html(tableHead);
							$
									.each(
											alldata,
											function(i, orderObj) {
												if (orderObj.uploadfiles == undefined)
													orderObj.uploadfiles = '';
												else {
													var list = orderObj.uploadfiles
															.split('*');
													var uploadfiles = '';
													for (var i = 0; i < list.length; i++) {
														uploadfiles = uploadfiles
																+ '<a href="abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><i class="fa fa-paperclip fa-lg grey"></i></a>';
													}
													orderObj.uploadfiles = uploadfiles;
												}
												var tblRow = "<tr>"
														+ "<td title='"+orderObj.statustime+"'>"
														+ orderObj.statustime
														+ "</td>"
														+ "<td title='"+orderObj.issueid+"'>"
														+ orderObj.issueid
														+ "</td>"
														+ "<td title='"+orderObj.kpstatus+"'>"
														+ orderObj.kpstatus
														+ "</td>"
														+ "<td title='"+orderObj.uploadfiles+"'>"
														+ orderObj.uploadfiles
														+ "</td>"
														+ "<td title='"+orderObj.comment+"'>"
														+ orderObj.comment
														+ "</td>" + "</tr>";
												$(tblRow)
														.appendTo(
																"#HtableId table tbody");

											});
							$("#myModal").modal();
						});

	}

	function viewTask2(id) {
		var formData = new FormData();
		formData.append('id', id);
		$.fn
				.makeMultipartRequest(
						'POST',
						'viewTask2',
						false,
						formData,
						false,
						'text',
						function(data) {
							var jsonobj = $.parseJSON(data);
							var alldata = jsonobj.list;
							$('#HtableId2').html('');
							var tableHead = '<table id="example2" class="table table-striped table-bordered datatables">'
									+ '<thead><tr><th>Date Modified</th><th>User Name</th><th>Attachment</th><th>field</th><th>Change</th></tr></thead><tbody></tbody></table>';
							$('#HtableId2').html(tableHead);
							$
									.each(
											alldata,
											function(i, orderObj) {
												if (orderObj.uploadfiles == undefined)
													orderObj.uploadfiles = '';
												else {
													var list = orderObj.uploadfiles
															.split('*');
													var uploadfiles = '';
													for (var i = 0; i < list.length; i++) {
														uploadfiles = uploadfiles
																+ '<a href="reportDocuments/'+list[i]+'" target="_blank" title="'+list[i]+'"><i class="fa fa-paperclip fa-lg grey"></i></a>';
													}
													orderObj.uploadfiles = uploadfiles;
												}
												var tblRow = "<tr>"
														+ "<td title='"+orderObj.createdTime+"'>"
														+ orderObj.createdTime
														+ "</td>"
														+ "<td title='"+orderObj.changedby+"'>"
														+ orderObj.changedby
														+ "</td>"
														+ "<td title='"+orderObj.uploadfiles+"'>"
														+ orderObj.uploadfiles
														+ "</td>"
														+ "<td title='"+orderObj.kpfield+"'>"
														+ orderObj.kpfield
														+ "</td>"
														+ "<td title='"+orderObj.kpchange+"'>"
														+ orderObj.kpchange
														+ "</td>" + "</tr>";
												$(tblRow)
														.appendTo(
																"#HtableId2 table tbody");

											});
							$("#myModal2").modal();
						});

	}

	var cissueid = 0;
	function addComment(id) {

		cissueid = id;
		$("#issueid").val(id);
		$("#formModal").modal();

	}

	var countDownDate;

	var x = 0;

	function showdeadline(id) {

		//$('#timeModal').html('');
		countDownDate = serviceUnitArray[id].taskdeadline

		var count = new Date(countDownDate).getTime();
		/*  x=setInterval(function() {clearInterval();}); */

		// Update the count down every 1 second
		if (x == 0) {
			x = setInterval(function() {
				// Get todays date and time
				var now = new Date().getTime();

				// Find the distance between now an the count down date
				var distance = count - now;

				document.getElementById("demo").innerHTML = '';

				// Time calculations for days, hours, minutes and seconds
				var days = Math.floor(distance / (1000 * 60 * 60 * 24));
				var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
						/ (1000 * 60 * 60));
				var minutes = Math.floor((distance % (1000 * 60 * 60))
						/ (1000 * 60));
				var seconds = Math.floor((distance % (1000 * 60)) / 1000);
				document.getElementById("demo").innerHTML = '';
				// Output the result in an element with id="demo"
				document.getElementById("demo").innerHTML = days + "d " + hours
						+ "h " + minutes + "m " + seconds + "s ";

				// If the count down is over, write some text 
				if (distance < 0) {
					document.getElementById("demo").innerHTML = "EXPIRED";
					showdeadline();
				}
			}, 1000);

			$("#timeModal").modal();
		} else {
			clearInterval(x);
			document.getElementById("demo").innerHTML = '';

			x = setInterval(function() {
				// Get todays date and time
				var now = new Date().getTime();

				// Find the distance between now an the count down date
				var distance = count - now;

				document.getElementById("demo").innerHTML = '';

				// Time calculations for days, hours, minutes and seconds
				var days = Math.floor(distance / (1000 * 60 * 60 * 24));
				var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
						/ (1000 * 60 * 60));
				var minutes = Math.floor((distance % (1000 * 60 * 60))
						/ (1000 * 60));
				var seconds = Math.floor((distance % (1000 * 60)) / 1000);
				document.getElementById("demo").innerHTML = '';
				// Output the result in an element with id="demo"
				document.getElementById("demo").innerHTML = days + "d " + hours
						+ "h " + minutes + "m " + seconds + "s ";

				// If the count down is over, write some text 
				if (distance < 0) {
					document.getElementById("demo").innerHTML = "EXPIRED";
					showdeadline();
				}
			}, 1000);
			$("#timeModal").modal();

		}

	}

	$('#ttype').on(
			'change',
			function() {
				var ttype = $('#ttype').val();
				var formData = new FormData();
				formData.append('ttypeid', ttype);
				$.fn.makeMultipartRequest('POST', 'setdata', false, formData,
						false, 'text', function(data) {
							var jsonobj = $.parseJSON(data);
							var alldata = jsonobj.list;
							displayTable(alldata);
							toolTips()
							makeEmpty()

						});
			})

	$("#deptid").on(
			'change',
			function() {
				var dept = $('#deptid').val();
				var formData = new FormData();
				formData.append('deptid', dept);
				$.fn.makeMultipartRequest('POST', 'setdataDeptWise', false,
						formData, false, 'text', function(data) {
							var jsonobj = $.parseJSON(data);
							var alldata = jsonobj.allOrders1;
							var myJSON = JSON.stringify(alldata);
							displayTable(alldata);
							toolTips()
							makeEmpty()
						});
			})

	var idArrayCmt11 = null;

	function submitCommet() {
		idArrayCmt11 = $.makeArray($('.validate2').map(function() {
			return this.id;
		}));
		validation = true;
		$.each(idArrayCmt11, function(i, val) {
			var value = $("#" + idArrayCmt11[i]).val();
			var placeholder = $("#" + idArrayCmt11[i]).attr('placeholder');
			if (value == null || value == "" || value == "undefined") {
				$('style').append(styleBlock);
				$("#" + idArrayCmt11[i]).attr("placeholder", placeholder);
				$("#" + idArrayCmt11[i]).css('border-color', '#e73d4a');
				$("#" + idArrayCmt11[i]).css('color', '#e73d4a');
				$("#" + idArrayCmt11[i]).addClass(
						'placeholder-style your-class');
				var id11 = $("#" + idArrayCmt11[i] + "_chosen").length;
				if ($("#" + idArrayCmt11[i] + "_chosen").length) {
					$("#" + idArrayCmt11[i] + "_chosen").children('a').css(
							'border-color', '#e73d4a');
				}
				//			$("#" + idArray[i] + "Error").text("Please " + placeholder);
				validation = false;
			}
		});
		if (validation) {

		} else {
			return false;
		}
		var kpstatus = $('#kpstatus').val();
		var commet = $('#commet').val();
		var issueid = $('#issueid').val();
		var id = $('#id').val();

		var formData = new FormData();

		formData.append('commet', commet);
		formData.append('kpstatus', kpstatus);
		formData.append('issueid', issueid);
		formData.append('id', id);

		var ins = document.getElementById('fileupload').files.length;

		for (var i = 0; i < ins; i++) {
			var portfolio_values = document.getElementById('fileupload').files[i];
			formData.append('file[]', portfolio_values);
		}

		$.ajax({
			type : "post",
			enctype : 'multipart/form-data',
			url : "subTask",
			data : formData,
			processData : false, // tell jQuery not to process the data
			contentType : false, // tell jQuery not to set contentType

			success : function(result) {
				if (result != "" && result != null) {

					alert(result)
				}
				location.reload();
				$('#kpstatus').val("");
				$('#commet').val("");
				$('#fileupload').val("");
				$('#formModal').modal('toggle');

			},
			error : function(e) {
				console.log(e.responseText);
			}

		});

	}

	function deletetask(id, status) {
		var checkstr = null;
		if (status == 0) {
			checkstr = confirm('Are you sure you want to Deactivate?');
		} else {
			checkstr = confirm('Are you sure you want to Activate?');
		}
		if (checkstr == true) {
			var formData = new FormData();
			formData.append('id', id);
			formData.append('status', status);
			$.fn.makeMultipartRequest('POST', 'deleteTask', false, formData,
					false, 'text', function(data) {
						var jsonobj = $.parseJSON(data);
						var alldata = jsonobj.allOrders1;
						var result = $.parseJSON(alldata);
						displayTable(result);
						 toolTips();
					});
		}
	}

	/*
	 $('#kpstatus').on('change',function() {
	 var issueCreatedBY =(serviceUnitArray[cissueid].assignbyid);
	 var loginid=${objuserBean.id}
	
	 if($('#kpstatus').val()=='1')
	 {
	
	 if( issueCreatedBY!= loginid)
	 {
	
	 alert("you are not authorized to close ticket");
	 $('#kpstatus').css('border-color', 'red');
	 $('#kpstatus').val("");
	 $('#modelSubmit').prop('disabled',true)
	 }
	 else
	 {
	
	 $('#modelSubmit').prop('disabled',false);
	 }
	 }
	 else {
	 $('#modelSubmit').prop('disabled',false);
	 $('#kpstatus').css('border-color', 'black');
	 }
	
	
	 });

	 */

	function inactiveData() {
		var status = "0";
		if ($('#inActive').is(":checked") == true) {
			status = "0";
		} else {
			status = "1";
		}
		var formData = new FormData();
		formData.append('status', status);

		$.fn.makeMultipartRequest('POST', 'inActiveTasks', false, formData,
				false, 'text', function(data) {
					var jsonobj = $.parseJSON(data);
					var alldata = jsonobj.allOrders1;
					displayTable(alldata);
					console.log(jsonobj.allOrders1);
					 toolTips();
				});

	}

	// main form validation
	/* 
	 var idArrayCmt1 = $.makeArray($('.validate1').map(function() {
	 return this.id;
	 }));
	 $('#submitMainForm').click(function(event) {
	 validation = true;
	 $.each(idArrayCmt1, function(i, val) {
	 var value = $("#" + idArrayCmt1[i]).val();
	 var placeholder = $("#" + idArrayCmt1[i]).attr('placeholder');
	 if (value == null || value == "" || value == "undefined") {
	 $('style').append(styleBlock);
	 $("#" + idArrayCmt1[i] ).attr("placeholder", placeholder);
	 $("#" + idArrayCmt1[i] ).css('border-color','#e73d4a');
	 $("#" + idArrayCmt1[i] ).css('color','#e73d4a');
	 $("#" + idArrayCmt1[i] ).addClass('placeholder-style your-class');
	 var id11 = $("#" + idArrayCmt1[i]+"_chosen").length;
	 if ($("#" + idArrayCmt1[i]+"_chosen").length)
	 {
	 $("#" + idArrayCmt1[i]+"_chosen").children('a').css('border-color','#e73d4a');
	 }
	 //			$("#" + idArray[i] + "Error").text("Please " + placeholder);
	 validation = false;
	 } 
	 });
	 if(validation) {
	 $("#submitMainForm").attr("disabled",true);
	 $("#submitMainForm").val("Please wait...");
	 $("#taskf").submit();											
	 event.preventDefault();
	 }else {
	 return false;
	 event.preventDefault();
	 }
	 });


	 $(".cancel1").click(function()
	 {
	 $("#id").val(0);
	 $.each( idArrayCmt11, function(i, val)
	 {
	 var value = $("#" +  idArrayCmt11[i]).val();
	 if ($("#" + idArrayCmt11[i]+"_chosen").length)
	 {
	 $("#" + idArrayCmt11[i]).val("");
	 $("#" + idArrayCmt11[i]).trigger("chosen:updated");
	 }
	 //				$("form")[0].reset();
	 $("#"+ idArrayCmt11[i]).val('');
	 $("#"+ idArrayCmt11[i]).prop('readonly',false);
	 $("#"+ idArrayCmt11[i]).css('border-color','');
	 $("#"+ idArrayCmt11[i]).css('color','black');
	 $("#"+ idArrayCmt11[i]).removeClass('placeholder-style your-class default-class');
	 if ($("#" +  idArrayCmt11[i]+"_chosen").length)
	 {
	 $("#" +  idArrayCmt11[i]+"_chosen").children('a').css('border-color','black');
	 }
	 });
	 });

	 $(".cancel2").click(function()
	 {
	 $("#id").val(0);
	 $.each( idArrayCmt1, function(i, val)
	 {
	 var value = $("#" +  idArrayCmt1[i]).val();
	 if ($("#" + idArrayCmt1[i]+"_chosen").length)
	 {
	 $("#" + idArrayCmt1[i]).val("");
	 $("#" + idArrayCmt1[i]).trigger("chosen:updated");
	 }
	 //				$("form")[0].reset();
	 $("#"+ idArrayCmt1[i]).val('');
	 $("#"+ idArrayCmt1[i]).prop('readonly',false);
	 $("#"+ idArrayCmt1[i]).css('border-color','');
	 $("#"+ idArrayCmt1[i]).css('color','black');
	 $("#"+ idArrayCmt1[i]).removeClass('placeholder-style your-class default-class');
	 if ($("#" +  idArrayCmt1[i]+"_chosen").length)
	 {
	 $("#" +  idArrayCmt1[i]+"_chosen").children('a').css('border-color','black');
	 }
	 });
	 });
	 */
	 $(document).load(function()
		{
		 
		 
		 $('#moveTo').hide();
		 
		 
		 });
	 
	 
	$("#pageName").text("Service Request Master");
	$(".task").addClass("active");
</script>