<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="dashBoard">Home</a></li>
		<li>All Service Requests</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="background-color: white !important; padding-top: 15PX;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>All Service Requests List</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
					<input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
						<div class="table-responsive" id="tableId">
							<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
								<thead><tr><th>ServiceNumber</th><th>Service Category</th><th>Customer ID</th><th>Customer Description</th><th>files</th><th></th></tr></thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	<form:form class="form-horizontal" commandName="servicerequestf" role="form"  action="serviceRequest" method="post" enctype="multipart/form-data">
		</form:form>
	</div>
	
	
	
	

<%-- <form:form modelAttribute="servicerequestf" action="serviceRequest" class="form-horizontal " method="Post" enctype="multipart/form-data">
	                  <form:hidden path="id"/>
					<div class="col-md-6"><br>
					<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Service Number<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="servicenumber" class="form-control validate" placeholder="Enter Servicenumber"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Service Category<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="servicecategory" class="form-control validate" placeholder="Enter Servicecategory"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">customer ID<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="custID" class="form-control validate" placeholder="Enter customerID"/>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Customer Description</label>
									<div class="col-md-6">
										<form:textarea path="custDesc" class="form-control validate emailOnly" placeholder="Enter Customerdescription"/>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
								<label class="col-md-3 control-label no-padding-right"><span class="impColor">Select files*</span></label>
									<div class="col-md-6">
										<input type="file" name="files" id="files" multiple/>
									</div>
								</div>
								
								<div class="clearfix"></div>
									<div align="center" class="but">							
					      				<input type="submit" id="submit1" value="Submit" class="btn-primary btn"/>
					      				<input type="reset" value="Reset" class="btn-danger btn cancel"/>
					      			</div>
								</div>
					</form:form> --%>
<script type="text/javascript">	
var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');

	var tableHead = '<table id="product" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Service Number</th><th>Service Category</th><th>CustomerID</th><th>Customer Description</th><th>Files</th><th style="text-align: center;">Options</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	 $.each(listOrders,function(i, orderObj) {
		if(orderObj.productmodelpics==undefined) orderObj.productmodelpics='';
		else
			{
				var list=orderObj.productmodelpics.split('*');
				var productmodelpics='';
				for(var i=0;i<list.length;i++)
				{
					productmodelpics=productmodelpics+'<a href="reportDocuments/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="reportDocuments/'+list[i]+'" style="height:42px; width:42px"></a>';
				}
				orderObj.productmodelpics=productmodelpics;
			} 
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick='deleteTotalServices("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>"
		}else{  
			var deleterow = "<a class='activate' onclick='deleteTotalServices("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>"
		}
		var edit = "<a class='edit editIt' onclick='editTotalServices("	+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"		
 		/* var comment = "<a class='comment commentIt' onclick='addComment("	+ orderObj.id+ ")'>   <i class='fa fa-comments'></i></a>" */
		serviceUnitArray[orderObj.id] = orderObj;
		/* var checkbox="<input type='checkbox' class='form-check-input' id='salesrequest'>" */
		var tblRow = "<tr>"
			+ "<td title='"+orderObj. servicenumber+"'>"+ orderObj. servicenumber + "</td>"
			+ "<td title='"+orderObj. servicecategory+"'>"+ orderObj. servicecategory + "</td>"
			+ "<td title='"+orderObj.custID+"'>"+ orderObj.custID + "</td>"
			+ "<td title='"+orderObj.custDesc+"'>"+ orderObj.custDesc + "</td>"
			+ "<td title='"+orderObj.files+"'>"+ orderObj.files + "</td>"	
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
			/* + "<td style='text-align: center;white-space: nowrap;' title='Send Quotation'>" +comment+"</td>"  */
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	if(isClick=='Yes') $('.datatables').dataTable();
	
}
function editTotalServices(id) 
{
	$("#id").val(serviceUnitArray[id].id);
	$("#servicenumber").val(serviceUnitArray[id].servicenumber);
	$("#servicecategory").val(serviceUnitArray[id].servicecategory);
	$("#custID").val(serviceUnitArray[id].custID);
	$("#custDesc").val(serviceUnitArray[id].custDesc);
	$("#files").val(serviceUnitArray[id].files);
	
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
}

function deleteTotalServices(id,status)
{
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
		$.fn.makeMultipartRequest('POST', 'deleteRequest', false, formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			toolTips();
			$('#inActive').prop('checked', false);
		});
	}
}
function inactiveData() 
{
	var status="0";
	if($('#inActive').is(":checked") == true){
		status="0";
	}else{
		status="1";
	}
		var formData = new FormData();
		formData.append('status', status);
		
		$.fn.makeMultipartRequest('POST', 'inActiveRequests', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			console.log(jsonobj.allOrders1);
				});
		
}

$("#pageName").text("All Service Requests");
$(".allservicerequests").addClass("active");
</script>