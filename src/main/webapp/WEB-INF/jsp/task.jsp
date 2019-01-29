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
.countclass{
display: none;
}
.nopad {
padding:0px;}
.butt {
	border:none;
    width: 121px;
    margin-top: 10px;
}
.butt a hover{
	text-decoration: none;
    
}
.table1 {
    font-family: arial, sans-serif;
    /* border-collapse: collapse; */
    width: 100%;
    border:none;
}
.commonclass {
width:96%;}

#mod-foot {
border:none;}
.form-control {
margin:4px;}
.tablerow {
    /* border: 1px solid #FF335E; */
    text-align: left;
    padding: 8px;
}
.preview td:nth-child(4) {
display:none;}
.bt1 {
    padding: 7px 34px;
    cursor: pointer;
    list-style: none;
    text-decoration: none;
}
.fg {
display:inline-flex;
text-align:center;
}
.fg .butt {
margin:2px;
}
</style>

<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://html2canvas.hertzen.com/build/html2canvas.js"></script>
<script src="https://github.com/tsayen/dom-to-image"></script>
<script src="https://github.com/eligrey/FileSaver.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css">
<script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>	
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
			<form:form class="form-horizontal" id="serviceRequestForm"  modelAttribute="taskf" action="savetask1" method="post" enctype="multipart/form-data">
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
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label ">Service Request DeadLine <span class="impColor">*</span>
									</label>
										<form:input type="text" path="taskdeadline" class="col-xs-10 col-sm-5 validate"  onfocus="removeBorder(this.id)"/>
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
										<form:textarea path="description" class="col-xs-10 col-sm-5 validate" placeholder="Description" />
										<span class="hasError" id="stationnameError"></span>
								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label">Service Request Number

										<!-- <span class="impColor">*</span> -->
									</label>
									<form:input path="taskno" placeholder="Service Request Number" class="col-xs-10 col-sm-5 " />
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
						</div>
						<%-- <div class="row" id="invoiceDiv">
						<div class="col-md-6">
						<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label">Invoice Number
										<!-- <span class="impColor">*</span> -->
									</label>
									<form:input path="invoiceId" placeholder="Invoice Number" class="col-xs-10 col-sm-5 " />
								</div>
						
							
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label
									class="ace-file-input ace-file-multiple col-sm-3 col-md-push-3 control-label no-padding-right">Attach Invoice</label>
								<div class="col-md-8">
									<input type="file" name="file" id="file" class="col-sm-9 col-md-push-5 " multiple="multiple" style="margin: 7px 0px 0px 0px;">
								</div>
							</div>
						</div>
						</div> --%>
						<div class="row">
						<div class="col-md-6">
						<div class="form-group">
								<label
									class="ace-file-input ace-file-multiple col-sm-3 col-md-push-3 control-label no-padding-right">Attach
									File(s)</label>
								<div class="col-md-8">
									<input type="file" name="file1" id="file1" class="col-sm-9 col-md-push-5 " multiple="multiple" style="margin: 7px 0px 0px 0px;">
								</div>
							</div>
								</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Warranty</label>
								<div class="col-md-3 ">
										<form:checkbox path="warranty" id ="warranty" value ="0" onClick="openWarrantyModal()"/>
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
									<input type="submit" id="submit1" value="Submit"class="btn-primary btn"  /> 
									<input type="reset" id="reset" value="Reset" onClick="window.location.reload()"
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
						<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
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

<security:authorize access="hasRole('ROLE_BRANCHHEAD')">
	<input id="isRole" type="text" class="hide" value="true" />
</security:authorize>

<%-- <security:authorize access="hasRole('ROLE_USER')"> --%>
<div class="modal fade" id="warrantyModal" data-backdrop="static" data-keyboard="false"  role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background: #2973cf;    padding: 7px;">
				<button type="button" class="close" id="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="color: white;"> Add Product Warranty Details </h4>
        	</div>
        	<div class="modal-body">
					<form class="form-horizontal"  method="post" >
					<div class="panel-body">
					<div class="row">
						<div class="col-md-9">
								<div class="form-group">
									<input type="hidden" id= "warrantyid" />
									<label for="focusedinput" class="col-md-6 control-label ">Product Model Name<span class="impColor">*</span>
									</label>
									<input type="text" name="modelid" id="modelid" class="col-md-6" />
									
								<%--<select id="productmodelid"	class="col-xs-10 col-sm-5 validate"	onfocus="removeBorder(this.id)">
										<option value="" label="--- Select ---" />
										 <c:forEach var="list" items="${productmodelid}">
												<option value=${list.key}>${list.value} </option>
										</c:forEach> 
									</select>--%>
								</div>
							</div>
							<div class="clearfix"></div>
							
							<div class="col-md-9">
								<div class="form-group">
								<input type="hidden" id= "orderId" />
									<label for="focusedinput" class="col-md-6 control-label ">Customer ID	<span class="impColor">*</span>
									</label>
									<input type="text" name="customerId" id="customerId" class="col-md-6" />	
								</div>
							</div><div class="clearfix"></div>
							<div class="col-md-9">
								<div class="form-group">
								<input type="hidden" id= "orderId" />
									<label for="focusedinput" class="col-md-6 control-label ">No.Of.Years<span class="impColor">*</span>
									</label>
									<select id="year" class="col-md-6 "	onfocus="removeBorder(this.id)">
										<option value="" label="--- Select ---" />
										<option value="1" label="1" />  
										<option value="2" label="2" />
										<option value="3" label="3" />
										<option value="4" label="4" />
										<option value="5" label="5" />
										<option value="6" label="6" />
										<option value="7" label="7" />
										<option value="8" label="8" />
										<option value="9" label="9" />
										<option value="10" label="10"/>
									</select>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-9">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Purchased Date	<span class="impColor">*</span>
									</label>
										<input type="text" id="purchaseddate"	class="col-md-6 " />

								</div>
							</div>
							<div class="col-md-9">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Expired Date	<span class="impColor">*</span>
									</label>
										<input type="text" id="expireddate" class="col-md-6 " />
								</div>
							</div>
							
						</div>
						<div id="getting-started"></div>
					</div>
							
                    <div class="panel-footer">
				      <div class="row">
							<div class="col-sm-12">
								<div class="btn-toolbar text-center">
									<input type="submit" id="submit3" value="Submit" onclick="saveWarranty()" class="btn-primary btn" /> 
										<input type="reset" value="Reset"   class="btn-danger btn cancel" />
								</div>
							</div>
						</div>	
			      	</div>
			      	</form>
					</div>
				</div>	
			</div>
		</div>
<%-- </security:authorize> --%>
<security:authorize access="hasRole('ROLE_ADMIN')">
<div class="modal fade" id="InvoiceModal" data-backdrop="static" data-keyboard="false"  role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background: #2973cf;padding:11px;">
				<button type="button" class="close" id="close" data-dismiss="modal" style="margin-top: -9px;">&times;</button>
				<h4 class="modal-title" style="color: white;"> Invoice Details </h4>
        	</div>
        	<div class="modal-body" style=" overflow-y: auto;	">
        	

  <!-- Modal content -->
  
    <div id="qqqq1"  >
      
     <div class="">
                            <img src="${baseurl }/abhee/images/logo.png" style="text-align: left;width:200px;">
                        </div>
                         <div class="" >
                         <span class="pull-left">Invoice Id:
						  <input type="text" name="firstname" id="inv1" class="form-control"></span>
						   <span class="pull-right">Date:
						  <input type="text" name="firstname" id="indate1" class="form-control"></span></div>
						  <div class="col-md-12 ser">
								<div class="col-md-10">
								<div class="form-group">
									<label class="col-md-4 control-label no-padding-right">Request Number<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input id="rno" name="rno" placeholder="Request Number" class="form-control" type="text" value=""/>

									</div>
								</div></div>
								</div><div class="clearfix"></div>
							
								<div class="col-md-12">
								<div class="col-md-10">
								<div class="form-group">
									<label class="col-md-4 control-label no-padding-right">Product Model<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input id="model" name="model" placeholder="Product model" class="form-control" type="text" value=""/>
										
									</div>
								</div></div>
								</div>
								<div class="col-md-12">
								<div class="col-md-10">
								<div class="form-group">
									<label class="col-md-4 control-label no-padding-right">Type of Service<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input id="rtype" name="rtype" placeholder="Type of service" class="form-control" type="text" value=""/>

									</div>
								</div></div>
								</div>
	<div></div>
  </div>
  
  <div id="qqqq2" class="" >
  <div class="" id="billheader" style=" display: none;">
                            <img src="${baseurl }/abhee/images/logo.png" style="text-align: left;width:200px;">
                        
                        
                        <div class="col-md-12 " >
                        <div class="col-md-6 nopad">
                        <div class="form-group ">
									<label class="col-md-6 control-label no-padding-right " id="indate">Invoice Id:<span class="impColor">*</span></label>
									<div class="col-md-6 nopad">
										<input type="text" name="firstname" id="inv2" class="form-control " placeholder="Invoice Id"  >
									</div>
								</div></div> <div class="col-md-6 nopad">
								 <div class="form-group">
									<label class="col-md-6 control-label no-padding-right" id="indate">Date:<span class="impColor">*</span></label>
									<div class="col-md-6 nopad">
										<input type="text" name="firstname" id="indate2" class="form-control"  placeholder="Date">
									</div>
								</div></div></div>
								<div class="clearfix"></div>
								<div class="col-md-12 "  style="display:inline-flex;margin-bottom: -13px;">
								<div class="form-group">
									<label class="col-md-6 control-label no-padding-right" id="indate3">Request Number<span class="impColor">*</span></label>
									<div class="col-md-6 nopad">
										<input id="requestno" name="requestno" placeholder="Request Number" class="form-control" type="text" value=""/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label no-padding-right" id="indate3">Type of Service<span class="impColor">*</span></label>
									<div class="col-md-6 nopad">
										<input id="requesttype" name="requesttype" placeholder="Type of service" class="form-control" type="text" value=""/>
									</div>
								</div>
								
								</div><div class="clearfix"></div>
							
								<div class="col-md-12">
								<div class="col-md-6 nopad">
								<div class="form-group">
									<label class="col-md-6 control-label no-padding-right" id="indate3">Product Model<span class="impColor">*</span></label>
									<div class="col-md-6 nopad">
										<input id="pmodel" name="pmodel" placeholder="Product model" class="form-control" type="text" value=""/>
									</div>
								</div>
								</div>
								<div class="col-md-12">
								<div class="col-md-10">
								</div>
								</div>
								</div>
                         
 </div>
  <table id="myTable" class="table1 " style="" cellspacing="0" cellpadding="0">
  <tr id="gg0" class="row1 tablerow">
    <th id="headersofbill1" class="header"></th>
    <th id="headersofbill2" class="header">&nbsp; Item Name</th>
    <th id="headersofbill3" class="header">&nbsp; Price</th>
  </tr>
  <tr id="gg1" class="tablerow">
    <td class="tdata"> <input  class="pro1 countclass commonclass form-control" type="text"  name="proCoun" id="proCoun1" ></td>
    <td class="tdata"> <input class="pro1 commonclass form-control" type="text"   name="proName" id="proName1" ></td>
    <td class="tdata"> <input class="commonclass form-control"  type="text" onkeypress="return isNumber(event)" name="priceAfterDiscount" id="priceAfterDiscount1"></td>
    <td>
    <img  id="addbtn1" name="imgbtn" alt="Add Button" src="${baseurl }/abhee/images/blue_add_buttonn.jpg" onclick="addFieldd();"> 
    <img  id="clsbtn1" name="cl1" alt="Close Button" src="${baseurl }/abhee/images/close_button.jpg" onclick="closeSelectedRow(this);">  </td>
  </tr>  
  </table>

     <div class="col-md-12">
								<div class="col-md-10">
								<div class="form-group">
									<label class="col-md-4 control-label no-padding-right">Discount %:<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input  id="givenDiscount"  onkeypress="return isNumber(event)" name="givenDiscount" placeholder="Discount" class="form-control " type="text" value=""/>
									</div>
								</div></div>
								</div>
	
     <div class="col-md-12">
								<div class="col-md-10">
								<div class="form-group">
									<label class="col-md-4 control-label no-padding-right">GST tax:<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input  id="gstTax"  onkeypress="return isNumber(event)" name="givenDiscount" placeholder="GST" class="form-control " type="text" value=""/>
									</div>
								</div></div>
								</div>
	
     <div class="col-md-12">
								<div class="col-md-10">
								<div class="form-group">
									<label class="col-md-4 control-label no-padding-right">Total :<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input  id="totalwithtax"  disabled="true" onkeypress="return isNumber(event)" name="givenDiscount" placeholder="Total" class="form-control " type="text" value=""/>
									</div>
								</div></div>
								</div>
								<b id="test" style="color:#FFF;">Invoice Generated</b>
    
  </div>
  
    <div id="mod-foot" class="modal-footer">    
    
    <div class="col-md-12">
    <div class="col-md-12 nopad">
                        <div class="form-group fg ">
                        	<input id="prevButton" class="butt butto btn-success"  type="button" value="Preview" onclick="prev();">    
   							<a id="atag" onclick="hhhh();" data-auto-download download="proposed_file_name" class="butt butto btn-primary bt1"  >Download</a>
    						<input id="enterbut" class="butt butto btn-warning" type="button" value="Enter" onclick="itemsPrice();">
    						<input id="resetbut" class="butt butto btn-danger" type="reset" value="Reset">
                        </div>
    </div>
    </div>    
    
  <img id="textScreenshot" src="">     
        
  </div>  
  </div>
			</div>
		</div>	
	</div>
</div>
</security:authorize>
<script type="text/javascript">

$('#indate1').datetimepicker({

	useCurrent : false,
	format : 'DD-MMM-YYYY',
	showTodayButton : true,
	sideBySide : true,
	
	toolbarPlacement : 'top',
	focusOnShow : false,

});
/* document.getElementById("atag").style.display="none"; */
/* document.getElementById("billheader").style.display="none"; */
var currentRow;
var totalItemsPrice = 0;
var str = "javascript";

function addFieldd() {	
	
	var myTable = document.getElementById("myTable");
    var currentIndex = myTable.rows.length;
    currentRow = myTable.insertRow(myTable.rows.length); 
    currentRow.setAttribute("class", "tablerow"); 
    currentRow.setAttribute("id", "gg" +myTable.rows.length); 
    
    var procount = document.createElement("input");
    procount.setAttribute("id", "proCoun" +(myTable.rows.length-1));
    procount.setAttribute("readonly", true);
    procount.setAttribute("class", "countclass commonclass form-control");    
    
    var proNameBox = document.createElement("input");
    proNameBox.setAttribute("name", "proName" + myTable.rows.length);
    proNameBox.setAttribute("class", "rowwidth commonclass form-control");
    proNameBox.setAttribute("id", "proName" +(myTable.rows.length-1));

    /* var proPriceBox = document.createElement("input");
    proPriceBox.setAttribute("name", "proPrice" + myTable.rows.length);     
    proPriceBox.setAttribute("onkeypress", "return isNumber(event)");
    proPriceBox.setAttribute("class", "rowwidth commonclass");
    proPriceBox.setAttribute("id", "proPrice" +(myTable.rows.length-1));    

     var proDiscountBox = document.createElement("input");
    proDiscountBox.setAttribute("name", "proDiscount" + myTable.rows.length);    
    proDiscountBox.setAttribute("onkeypress", "return isNumber(event)");
    proDiscountBox.setAttribute("class", "rowwidth commonclass");
    proDiscountBox.setAttribute("id", "proDiscount" +(myTable.rows.length-1));*/
    
    var priceAfterDiscountBox = document.createElement("input");
    priceAfterDiscountBox.setAttribute("name", "priceAfterDiscount" + myTable.rows.length);    
    priceAfterDiscountBox.setAttribute("onkeypress", "return isNumber(event)");
    priceAfterDiscountBox.setAttribute("class", "commonclass form-control");
    
    //var pattern ="^\d*(\.\d{0,2})?$"		
    //priceAfterDiscountBox.setAttribute("pattern", "^\d*(\.\d{0,2})?$");	
    priceAfterDiscountBox.setAttribute("id", "priceAfterDiscount" +(myTable.rows.length-1)); 
    
    var addRowBox = document.createElement("img");
    addRowBox.setAttribute("src", "Images/blue_add_buttonn.jpg");
    addRowBox.setAttribute("onclick", "addFieldd();");
    addRowBox.setAttribute("id", "addbtn" +(myTable.rows.length-1));
        
    var deleteRowBox = document.createElement("img");
    deleteRowBox.setAttribute("src", "Images/close_button.jpg");
    deleteRowBox.setAttribute("onclick", "closeSelectedRow(this);");
    deleteRowBox.setAttribute("id", "clsbtn" +(myTable.rows.length-1));
    deleteRowBox.setAttribute("name", "cl" +(myTable.rows.length-1));    

    var currentCell = currentRow.insertCell(0);
    currentCell.appendChild(procount);
    
    currentCell = currentRow.insertCell(1);
    currentCell.appendChild(proNameBox);

    /* currentCell = currentRow.insertCell(2);
    currentCell.appendChild(proPriceBox);

    currentCell = currentRow.insertCell(2);
    currentCell.appendChild(proDiscountBox);  */
    
    currentCell = currentRow.insertCell(2);
    currentCell.appendChild(priceAfterDiscountBox);
    currentCell = currentRow.insertCell(3);
    currentCell.appendChild(addRowBox);
    currentCell.appendChild(deleteRowBox);   
    //hhhh();
    
}



function hhhh(){
	$('#atag[data-auto-download]').each(function(){
  	  var $this = $(this);
  	  setTimeout(function() {
  	  window.location = $this.attr('href');
  	  }, 2000);
  	  });
	/* html2canvas(document.getElementById("qqqq2"), {
	    onrendered: function(canvas) {
	      var screenshot = canvas.toDataURL("image/png");
	      document.getElementById("textScreenshot").setAttribute("src", screenshot);
	      document.getElementById("textScreenshot").style.display = "none";
	      document.getElementById("atag").setAttribute("href", screenshot);	
	      //document.getElementById("atag").setAttribute("download","INV001" );	
	      $('#atag[data-auto-download]').each(function(){
	    	  var $this = $(this);
	    	  setTimeout(function() {
	    	  window.location = $this.attr('href');
	    	  }, 2000);
	    	  });
	    }
	  }); */
}

function closeSelectedRow(e){	
	
	var rr = e.getAttribute("name");
	var lastChar = rr[rr.length -1];
	var x = document.getElementsByClassName("tablerow");
	
    var tt = e.parentNode.parentNode.rowIndex;
    document.getElementById("myTable").deleteRow(tt);           
}

// USED url: https://ctrlq.org/code/20056-convert-text-to-images-with-javascript
function prev() {
	
	document.getElementById("atag").style.display="block";
	document.getElementById("atag").setAttribute("download",invnum );
	document.getElementById("prevButton").style.display="none";
	  document.getElementById("myTable").className ='table-bordered preview';
	  var x = document.getElementById("myTable").rows.length;		
	  for (i = 1; i <= x;i++) { 
		
		document.getElementById('addbtn' +i).style.visibility = 'hidden';
	  	document.getElementById('clsbtn' +i).style.visibility = 'hidden';	
	  	document.getElementById('enterbut').style.visibility = 'hidden';
	  	document.getElementById('resetbut').style.visibility = 'hidden';
	  	document.getElementById('mod-foot').style.backgroundColor = "#ffffff";
	  	document.getElementById('qqqq1').style.display = "none";
	  	document.getElementById('billheader').style.display = "block";

	  	document.getElementById('inv1').style.display = 'none';
	  	document.getElementById('indate1' ).style.display = 'none'; 
	  	document.getElementById("inv2").value = document.getElementById('inv1' ).value;
	  	document.getElementById("indate2").value = document.getElementById('indate1' ).value;
	  	document.getElementById('indate').style.display = 'block';
	  	document.getElementById('inv2').style.border = 'none';
	  	document.getElementById('indate2' ).style.border = 'none';  
	  	
	  	
	  	
	  	document.getElementById('rno').style.display = 'none';
	  	document.getElementById('model' ).style.display = 'none'; 
		document.getElementById('rtype' ).style.display = 'none'; 
	  	document.getElementById("requestno").value = document.getElementById('rno' ).value;
	  	document.getElementById("pmodel").value = document.getElementById('model' ).value;
		document.getElementById("requesttype").value = document.getElementById('rtype' ).value;
	  	document.getElementById('indate3').style.display = 'block';
	  	document.getElementById('requestno').style.border = 'none';
	  	document.getElementById('pmodel' ).style.border = 'none';
		document.getElementById('requesttype' ).style.border = 'none';
		
		document.getElementById('inv2').readOnly = true;
		document.getElementById('indate2').readOnly = true;
		document.getElementById('requestno').readOnly = true;
		document.getElementById('pmodel').readOnly = true;
		document.getElementById('requesttype').readOnly = true;
		
		document.getElementById('inv2').style.background = "#fff";
		document.getElementById('indate2').style.background = "#fff";
		document.getElementById('requestno').style.background = "#fff";
		document.getElementById('pmodel').style.background = "#fff";
		document.getElementById('requesttype').style.background = "#fff";
	  	
	  	document.getElementById('proCoun' +i).style.border = 'none';
	  	document.getElementById('proCoun' +i).readOnly = true;
	  	document.getElementById('proName' +i).style.border = 'none';
	  	document.getElementById('proName' +i).readOnly = true;
	  	document.getElementById('priceAfterDiscount' +i).style.border = 'none';
	  	document.getElementById('priceAfterDiscount' +i).readOnly = true;
	  	
	  	document.getElementById('givenDiscount').style.border = 'none';
	  	document.getElementById('givenDiscount').readOnly = true;
	  	document.getElementById('givenDiscount').style.background = "#fff";
	  	
	  	document.getElementById('gstTax').style.border = 'none';
	  	document.getElementById('gstTax').readOnly = true;
	  	document.getElementById('gstTax').style.background = "#fff";
	  	
	  	document.getElementById('totalwithtax').style.border = 'none';
	  	document.getElementById('totalwithtax').readOnly = true;
	  	document.getElementById('totalwithtax').style.background = "#fff";
	  	
	  	document.getElementById('headersofbill1').style.display = "block";
	  	document.getElementById("myTable").rows[0].cells[0].innerHTML = "&nbsp; S.No";
	  	document.getElementById('proCoun' +i).style.display = "block";
	  	document.getElementById('proCoun' +i).style.background = "#fff";
	  	document.getElementById('proCoun' +i).style.width = "80px";
	  	document.getElementById('proName' +i).style.background = "#fff";
	  	document.getElementById('priceAfterDiscount' +i).style.background = "#fff";
	  	document.getElementById('priceAfterDiscount' +i).style.width = "220px";
	  	
	  	document.getElementById('headersofbill1').style.border = '';
	  	document.getElementById('headersofbill2').style.border = '';
	  	document.getElementById('headersofbill3').style.border = '';
	  	
	  	document.getElementById('proCoun' +i).value = i;	  	
	  	document.getElementById('proName' +i).style.borderLeft = 'none';
	  	document.getElementById('priceAfterDiscount' +i).style.borderLeft = 'none';
	  	
	  	
	  	
	  	html2canvas(document.getElementById("qqqq2"), {
		    onrendered: function(canvas) {
		      var screenshot = canvas.toDataURL("image/png");
		      document.getElementById("textScreenshot").setAttribute("src", screenshot);
		      document.getElementById("textScreenshot").style.display = "none";
		      document.getElementById("atag").setAttribute("href", screenshot);	
		      //document.getElementById("atag").setAttribute("download","INV001" );	
		       document.getElementById("test").style.display = "none";
		      $('#atag[data-auto-download]').each(function(){
		    	  var $this = $(this);
		    	  setTimeout(function() {
		    	  window.location = $this.attr('href');
		    	  }, 2000);
		    	  });
		    }
		  });
	  		  	
	}	
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
   if ((charCode>31) && (charCode<46||charCode>46) &&(charCode<48||charCode>57)) {
        return false;
    }
    return true;
}

/*  USED URL: https://www.codexworld.com/export-html-to-word-doc-docx-using-javascript/ */
function Export2Doc(element, filename = ''){	  
	
    var preHtml = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns='http://www.w3.org/TR/REC-html40'><head><meta charset='utf-8'><title>Export HTML To Doc</title></head><body>";
    var postHtml = "</body></html>";
    var html = preHtml+document.getElementById(element).innerHTML+postHtml;

    var blob = new Blob(['\ufeff', html], {
        type: 'application/msword'
    });
    
    // Specify link url
    var url = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(html);
    
    // Specify file name
    filename = filename?filename+'.doc':'document.doc';
    
    // Create download link element
    var downloadLink = document.createElement("a");

    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob ){
        navigator.msSaveOrOpenBlob(blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = url;
        
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
    
    document.body.removeChild(downloadLink); 
    
}

function itemsPrice(){	
		
	var x = document.getElementById("myTable").rows.length;		
	
	for (i = 1; i <= x;i++) {    
    	
    	 /*  document.getElementById("priceAfterDiscount" +i).value = 
    		(document.getElementById("proPrice" +i).value) 
  		(document.getElementById("proDiscount" +i).value); */
    	  
    	totalItemsPrice =  totalItemsPrice + Number(document.getElementById("priceAfterDiscount" +i).value);
    	//document.getElementById("totalwithtax").value = totalItemsPrice *(1 -((Number(document.getElementById("gstTax").value) + Number(document.getElementById("givenDiscount").value))/100));
    	
    	document.getElementById("totalwithtax").value =((totalItemsPrice-(totalItemsPrice*(Number(document.getElementById("givenDiscount").value)/100)))+(totalItemsPrice*(Number(document.getElementById("gstTax").value)/100)));
    }    
	/* var dis=(totalItemsPrice*(Number(document.getElementById("givenDiscount").value)/100));
	console.log(dis);
	var gst =(totalItemsPrice*(Number(document.getElementById("gstTax").value)/100));
	console.log(gst);
	var total=((totalItemsPrice-dis)+gst);
	console.log(total); */
} 	
var modelid="";
function getCurrentDate() {
	   var purchaseddate = new Date();
	   var dd = purchaseddate.getDate(); //yields day
	   var MM = purchaseddate.getMonth(); //yields month
	   var yyyy = purchaseddate.getFullYear(); //yields year
	   var currentDate= dd + "-" +( MM+1) + "-" + yyyy;	
	   return currentDate;
	}

function getExpiryDate(year1) {
	   var expireddate = new Date();
	   var dd = expireddate.getDate(); //yields day
	   var MM = expireddate.getMonth(); //yields month
	   var yyyy = expireddate.getFullYear(); //yields year
	   var year = yyyy+parseInt(year1);
	   var year2 = expireddate.setFullYear(year);
	   var currentDate= dd + "-" +( MM+1) + "-" + year;

	   return currentDate;
	}
/* var customerIdDropDown = ${customerid};
var productmodelidDropDown =${productmodelid};


	 $('#purchaseddate').datetimepicker({
	
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
	
	}); */
	
	function openWarrantyModal(){
			if($("#warranty").attr('checked',true))
			{
				$("#warrantyModal").modal('show');
				/*  var optionsForProductModels = "";
				optionsForProductModels = $("#customerid").empty();
				optionsForProductModels.append(new Option("-- Select --",	""));
				$.each(customerIdDropDown, function(i, tests) {
					var productId = tests;
					var productName = tests;
					optionsForProductModels.append(new Option(productId,	productName));
				});
				var optionsForProductModels2 = "";
				optionsForProductModels2 = $("#productmodelid").empty();
				optionsForProductModels2.append(new Option("-- Select --",	""));
					$.each(productmodelidDropDown, function(i, tests) {
						var productId1 =tests;
						var productName1 = i;
						optionsForProductModels2.append(new Option(productId1,	productName1));
					}); */	
			}
			else
			{
				$("#warrantyModal").modal('hide');
			}	
	} 
	
	/* $("#reset").click(function(){
		makeWarrantyModalEmpty();
		});
	function makeWarrantyModalEmpty()
	{
		$('#year').val("");
		$('#expireddate').val("");
		
	} */
	function saveWarranty()
	{
		//var product=$('#model').val();
		
		var product=modelid;
		var customer=$('#customerId').val();
		var purchaseddate=$('#purchaseddate').val();
		var expireddate=$('#expireddate').val();
		var formData = new FormData();
		formData.append('productmodelid',product);
		formData.append('customerid',customer);
		formData.append('purchaseddate',purchaseddate);
		formData.append('expireddate',expireddate);  
		
		
		/* $.fn.makeMultipartRequest('POST', 'Warranty', false, formData,false, 'json', function(data) {
			var data = JSON.parse(result);
			console.log(data);
			//alert(data);
						if(data[0] =="true")
						{
							
						alert("Warranty Added Successfully");
						$('#warrantyModal').modal('toggle');
					//$("#warranty").prop('checked', true);
						}
						else
						{
							alert("Warranty Adding Failed!"); 
							
						}

				}); */
		 $.ajax({
			type:"POST",			
			url: "Warranty", 
			//data :"&productmodelid="+product+"&customerid="+customer+"&purchaseddate="+purchaseddate+"&expireddate="+expireddate,
			data:formData,
			processData: false,  // tell jQuery not to process the data
			contentType: false,  // tell jQuery not to set contentType
			dataType : "json",
			 async: false,
			success: function(result){
				//var data = JSON.parse(result);
				console.log(result);
				//alert(data);
			if(result.status =="true")
			{
				
			alert("Warranty Added Successfully");
			$('#warrantyModal').modal("hide");
			//$("#warranty").prop('checked', true);
			}
			else
			{
				alert("Warranty Adding Failed!"); 
				
			}
		   	}
		   
		 }); 
	} 
	
	

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
		
		$("#year").change(function(evt){
			$("#expireddate").val(getExpiryDate(($("#year").val())));
		});
		
		$("#close").click(function(){
			$("#warranty").attr('checked',false);
			});
		
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

	var invnum='${invoiceid}';
	var loginUserDId = "1";
	var cuserid = "1";
	var listOrders1 = ${allOrders1};
	

	

	if (listOrders1 != "") {
		displayTable(listOrders1)
	}

	function displayTable(listOrders) {
		$('#tableId').html('');
		var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
				+ '<thead><tr><th>Service Request Number</th><th>Category</th><th>Model Name</th><th>Customer ID</th><th>Service Type</th><th>Customer Sent Image</th><th>Invoice</th><th>Priority</th><th>Task Created By</th><th>Service Request Status</th><th>Address</th><th>Requested Time</th><th style="text-align: center;">Options	</th></tr></thead><tbody></tbody></table>';
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
										uploadfile = uploadfile + '<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
									}
									orderObj.uploadfile = uploadfile;
								}  	

								if (orderObj.invimg == undefined)
									orderObj.invimg = '';
								else {
									var list = orderObj.invimg
											.split('*');
									var invimg = '';
									for (var i = 0; i < list.length; i++) {
										invimg = invimg + '<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
									}
									orderObj.invimg = invimg;
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
							var invoice="<a class='invoice invoiceIt' onclick='addInvoice("+ orderObj.id+ ")'><i class='fa fa-money'></i></a>" 
							serviceUnitArray[orderObj.id] = orderObj;
							var tblRow = "<tr>"
									+ "<td title='"+orderObj.taskno+"'>"+ view2 + "</td>"
									+ "<td title='"+orderObj.category+"'>"+ orderObj.category + "</td>"
									+ "<td title='"+orderObj.modelname+"'>"+ orderObj.modelname + "</td>"
									+ "<td title='"+orderObj.customer_id+"'>"+ view3+ "</td>"
									+ "<td title='"+orderObj.servicetypename+"'>"+ orderObj.servicetypename+ "</td>"
									+ "<td title='"+orderObj.uploadfile+"'>"+ orderObj.uploadfile	+ "</td>"
									+ "<td title='"+orderObj.invimg+"'>"+ orderObj.invimg	+ "</td>"
									+ "<td title='"+orderObj.priority+"'>"+ orderObj.priority+ "</td>"
									+ "<td title='"+orderObj.subject+"'>"+ orderObj.subject+ "</td>"
									+ "<td title='"+orderObj.statusname+"'>"+ orderObj.statusname+ "</td>"
									+ "<td title='"+orderObj.communicationaddress+"'>"+ orderObj.communicationaddress+ "</td>"
									/* + "<td title='"+orderObj.amountreceived+"'>"+ orderObj.amountreceived + "</td>"
									+ "<td title='"+orderObj.discount+"'>"+ orderObj.discount + "</td>"
									+ "<td title='"+orderObj.tax+"'>"+ orderObj.tax + "</td>"
									+ "<td title='"+orderObj.total+"'>"+ orderObj.total */
									+ "<td title='"+orderObj.requesttime+"'>"+ orderObj.requesttime+ "</td>"
									/* + "<td title='"+orderObj.paymentstatus+"'>"+ orderObj.paymentstatus+ "</td>" */
									+ "<td style='text-align: center;white-space: nowrap;'>"+ edit+ "&nbsp;&nbsp;"+ deleterow+ "&nbsp;&nbsp;"+ time+ "&nbsp;&nbsp;"+invoice+"</td>"
									+ "</tr>";	 
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
		$("#addComment").val(serviceUnitArray[id].add_comment);
		//$("#model").val(serviceUnitArray[id].modelid);
		modelid=serviceUnitArray[id].modelid;
		var warrantydata=serviceUnitArray[id].warranty
		 //$("#warranty").val(serviceUnitArray[id].warranty);
		 if(warrantydata =='0')
		{
			 $("#warranty").prop('checked',true);	 
		}
		else
		{
			$("#warranty").prop('checked',false);
		} 
		$("#modelid").val(serviceUnitArray[id].modelname);
		$("#customerId").val(serviceUnitArray[id].customer_id);
		$("#purchaseddate").val(getCurrentDate());
		/* var taskstatus=serviceUnitArray[id].kstatus;
		if(taskstatus == "3")
		{
			$("#invoiceDiv").show();
			//document.getElementById("invoiceDiv").style.display='block';
		}
		else
		{
			//document.getElementById("invoiceDiv").style.display='none';
			$("#invoiceDiv").hide();
		}	 */
		$("#submit1").val("Update");
		$(window).scrollTop($('#moveTo').offset().top);
		//$("#reset").hide();
		document.getElementById("description").readOnly  = true;
		document.getElementById("taskno").disabled  = true;
		//document.getElementById("ServiceType").attr  = true;
		var idArray = $.makeArray($('.validate').map(function() {
	    	return this.id;
	    })); 
	   $("#ServiceType").attr('disabled', true);
	    /*$("#description").attr('disabled', true); */ 
	}
	
	
	 function addInvoice(id)
		{
			 	$("#rno").val(serviceUnitArray[id].taskno);
				$("#model").val(serviceUnitArray[id].modelname);
				$("#rtype").val(serviceUnitArray[id].servicetypename);
				$("#inv1").val(invnum.toString());
			$("#InvoiceModal").modal('show');
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
																+ '<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"><i class="fa fa-paperclip fa-lg grey"></i></a>';
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
																+ '<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"><i class="fa fa-paperclip fa-lg grey"></i></a>';
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
						var jsonobj = $.parseJSON(data);
						if(status==1)
						{
							alert("Service Request Activated Successfully");
						}
								
						else
							{
							alert("Service Request Deactivated Successfully");
							}
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
	 
	 /* var idArrayCmt1 = $.makeArray($('.validate1').map(function() {
	 return this.id;
	 }));
	 $('#submit1').click(function(event) {
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
	 $("#submit1").attr("disabled",true);
	 $("#submit1").val("Please wait...");
	 $("#taskf").submit();											
	 event.preventDefault();
	 }else {
	 return false;
	 event.preventDefault();
	 }
	 }); */


	/* $(".cancel1").click(function()
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