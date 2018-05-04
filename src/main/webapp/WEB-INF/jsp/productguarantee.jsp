<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script type="text/javascript"
	src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css">

<div class="clearfix"></div>
<ol class="breadcrumb">
	<li><a href="dashBoard">Home</a></li>
	<li>Warranty Master</li>
</ol>
<br>
<div class="clearfix"></div>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Warranty List</h4>
					<div class="options">
						<a href="javascript:;" class="panel-collapse"><i
							class="fa fa-chevron-down"></i></a>
					</div>
				</div>
				<div class="panel-body collapse in">
					<input type="checkbox" class="form-check-input"
						onclick="inactiveData();" id="inActive"> <label
						class="form-check-label">Show Inactive List</label>
					<div class="table-responsive" id="tableId">
						<table cellpadding="0" cellspacing="0" border="0"	class="table table-striped table-bordered datatables"	id="example">
							<thead>
								<tr>
									<th>CustomerID</th>
									<th>Order ID</th>
									<th>productmodelID</th>
									<th>Purchased Date</th>
									<th>Expired Date</th>
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
				<h4 id="productw">Add Product Warranty Details</h4>
				</div>
				<form:form class="form-horizontal" modelAttribute="guaranteef"	action="productGuarantee" method="post" >
					<div class="panel-body">
					  <security:authorize access="hasRole('ROLE_ADMIN')">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<%-- <form:hidden path= "id" /> --%>
									<label for="focusedinput" class="col-md-6 control-label ">ProductmodelId<span class="impColor">*</span>
									</label>
										
									<form:select path="productmodelid"	class="col-xs-10 col-sm-5 validate"	onfocus="removeBorder(this.id)">
										<form:option value="" label="--- Select ---" />
									<form:options items="${productmodelid}" />
									</form:select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<form:hidden path="orderId" /> 
									<label for="focusedinput" class="col-md-6 control-label ">Customer ID	<span class="impColor">*</span>
									</label>
										
									<form:select path="customerid"	class="col-xs-10 col-sm-5 validate"	onfocus="removeBorder(this.id)">
										<form:option value="" label="--- Select ---" />
									<form:options items="${customerid}" />
									</form:select>
								</div>
							</div>


						</div>						  
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Purchased Date	<span class="impColor">*</span>
									</label>
									<div class="col-md-6">
										<form:input type="text" path="purchaseddate"	class="form-control validate" />

									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Expired Date	<span class="impColor">*</span>
									</label>
									<div class="col-md-6">
										<form:input type="text" path="expireddate"
											class="form-control validate" />

									</div>
								</div>
							</div>
							
						</div>
						 </security:authorize>
						<div id="getting-started"></div>
					</div>
					<div class="panel-footer">
						<div class="row">
							<div class="col-sm-12">
								<div class="btn-toolbar text-center">
									<input type="submit" id="submit1" value="Submit" class="btn-primary btn" /> 
										<input type="reset" value="Reset" class="btn-danger btn cancel" />
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

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

		});

var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}

function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>CustomerID</th><th>Order ID</th><th>ProductModelName</th><th>Purchaseddate</th><th>Expired Date</th><th style="text-align: center;">Options</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick=deleteProductWarranty('"+ orderObj.orderId+ "',0)><i class='fa fa-eye'></i></a>"
		}else{  
			var deleterow = "<a class='activate' onclick=deleteProductWarranty('"+ orderObj.orderId+ "',1)><i class='fa fa-eye-slash'></i></a>"
		}
		var edit = "<a class='edit editIt' onclick=editProductWarranty('"+orderObj.orderId+"')><i class='fa fa-edit'></i></a>"
		serviceUnitArray[orderObj.orderId] = orderObj;
		var tblRow = "<tr>"
			+ "<td title='"+orderObj.customerid+"'>"+ orderObj.customerid + "</td>"
			+ "<td title='"+orderObj.orderId+"'>"+ orderObj.orderId + "</td>"
			+ "<td title='"+orderObj.productmodelname+"'>"+ orderObj.productmodelname + "</td>"
			+ "<td title='"+orderObj.purchaseddate+"'>"+ orderObj.purchaseddate + "</td>"
			+ "<td title='"+orderObj.expirededdate+"'>"+ orderObj.expireddate + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	if(isClick=='Yes') $('.datatables').dataTable();
	
}


function editProductWarranty(id) {
	$("#productw").text("Edit Warranty Details");
	$("#orderId").val(serviceUnitArray[id].orderId);
	$("#customerid").val(serviceUnitArray[id].customerid);
	$("#productmodelid").val(serviceUnitArray[id].productmodelid);
	$("#productmodelname").val(serviceUnitArray[id].productmodelname);
	$("#purchaseddate").val(serviceUnitArray[id].purchaseddate);
	$("#expireddate").val(serviceUnitArray[id].expireddate);
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
}

function deleteProductWarranty(id,status){
	
	var checkstr=null;
	if(status == 0){
		 checkstr = confirm('Are you sure you want to Deactivate?');
	}else{
		 checkstr = confirm('Are you sure you want to Activate?');
	}
	if(checkstr == true){
		var formData = new FormData();
	    formData.append('orderId', id);
	    formData.append('status', status);
		$.fn.makeMultipartRequest('POST', 'deleteProductWarranty', false, formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			toolTips();
			$('#inActive').prop('checked', false);
		});
	}
}

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

function inactiveData() {
	var status="0";
	if($('#inActive').is(":checked") == true){
		status="0";
	}else{
		status="1";
	}
		var formData = new FormData();
		formData.append('status', status);
		
		$.fn.makeMultipartRequest('POST', 'inActiveProductWarranty', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			console.log(jsonobj.allOrders1);
				});
		
}

$("#pageName").text("Product Warranty Details");
$(".productGuarantee").addClass("active");

</script>

	