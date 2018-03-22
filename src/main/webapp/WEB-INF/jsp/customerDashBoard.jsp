


 <style>
.btn-toolbar {
	margin-left: 5px;
}

.priority th, td {
	text-align: center;
	border: 1px solid #efe9e9;
}

.prioritybg tr:nth-child(even) {
	background: #ccc;
	border: 1px solid red;
}

.assigned {
	width: 120px;
	height: 30px;
	line-height: 0.8;
	border-radius: 10px;
}
.btn-toolbar {
	margin-top:5px;
}

		
		.col-md-3 {
			float:left;
		}
		p {
			text-align:center;
		}
		.form-control {
			margin-top:10px;
		}
		.modal-header {
			background:#FFCC33;
		}
		h1 {
			font-size:18px;
			text-align:center;
		}
		.tab {
			margin-top:20px;
		}
</style>



<!-- Body starts here -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script> -->
<!-- Body starts here -->
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li class="">Dashboard</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>
		
		<div class="tab">
        <div class="col-md-3">
        	<div align="center" class="tab">
            	<a href="#modal" data-toggle="modal" data-target="#notifyModal">
                	<img width="200px" height="200px" src="${baseurl }/assets/images/1.png"/>
                </a>
            </div>
            <h1>Home Theaters</h1>
        </div>
        <div class="col-md-3">
        	<div align="center" class="tab">
            	<a href="#modal" data-toggle="modal" data-target="#notifyModal">
                	<img width="200px" height="200px" src="${baseurl }/assets/images/2.png"/>
                </a>
            </div>
            <h1>PA Audio</h1>
        </div>
        <div class="col-md-3">
        	<div align="center" class="tab">
            	<a href="#modal" data-toggle="modal" data-target="#notifyModal">
                	<img width="200px" height="200px" src="${baseurl }/assets/images/3.png"/>
                </a>
            </div>
            <h1>Projectors</h1>
        </div>
        <div class="col-md-3">
        	<div align="center" class="tab">
            	<a href="#modal" data-toggle="modal" data-target="#notifyModal">
                	<img width="200px" height="200px" src="${baseurl }/assets/images/4.png"/>
                </a>
            </div>
            <h1>Security Cameras</h1>
        </div>
        </div><div class="clearfix"></div>
		<div class="tab">
        <div class="col-md-3">
        	<div align="center" class="tab">
            	<a href="#modal" data-toggle="modal" data-target="#notifyModal">
                	<img width="200px" height="200px" src="${baseurl }/assets/images/5.png"/>
                </a>
            </div>
            <h1>Solar Fencing</h1>
        </div>
        <div class="col-md-3">
        	<div align="center" class="tab">
            	<a href="#modal" data-toggle="modal" data-target="#notifyModal">
                	<img width="200px" height="200px" src="${baseurl }/assets/images/6.png"/>
                </a>
            </div>
            <h1>Remote Gates</h1>
        </div>
        <div class="col-md-3">
        	<div align="center" class="tab">
            	<a href="#modal" data-toggle="modal" data-target="#notifyModal">
                	<img width="200px" height="200px" src="${baseurl }/assets/images/7.png"/>
                </a>
            </div>
            <h1>Wooden Flooring</h1>
        </div>
        <div class="col-md-3">
        	<div align="center" class="tab">
            	<a href="#modal" data-toggle="modal" data-target="#notifyModal">
                	<img width="200px" height="200px" src="${baseurl }/assets/images/8.png"/>
                </a>
            </div>
            <h1>Artificial Grass</h1>
        </div>
        </div><div class="clearfix"></div>
		
		<!-- /.page-content -->
	</div>
	<!-- /.main-content-inner -->
</div>
<!--  Modal -->
<div tabindex="-1" id="notifyModal" class="modal fade" data-keyboard="true" data-backdrop="dynamic" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="col-md-12" style="width: 800px; margin-left: -55px;">
			<div class="panel panel-primary">

				<div class="panel-heading">
					<h4>Enquiry</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="panel-body collapse in">
                    	<form>
                        	<select class="form-control">
                            	<option>Type of Enquiry</option>
                            	<option>Quotation</option>
                            	<option>Product Availability</option>
                            	<option>Ticket</option>
                            </select>
                        	<select class="form-control">
                            	<option>Type of Product</option>
                            	<option>Name of the product</option>
                            	<option>Model Number</option>
                            </select>
                        	<select class="form-control">
                            	<option>Type of Product</option>
                            	<option>Name of the product</option>
                            	<option>Model Number</option>
                            </select>
                        	<select class="form-control">
                            	<option>Type of Ticket</option>
                            	<option>Raised on</option>
                            </select>
                        </form>
				</div>
      				<div class="panel-footer">
        				<button style="float:right;" type="button" class="btn btn-success" data-dismiss="modal">Submit</button>
      				</div>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript">
	$(window).load(
			function() {
				var formData = new FormData();
				formData.append('ttypeid', "1");
				$.fn.makeMultipartRequest('POST', 'setNotifyData', false,
						formData, false, 'text', function(data) {
							var jsonobj = $.parseJSON(data);
							var alldata = jsonobj.allOrders1;
							//console.log(alldata)
							if (alldata != "") {
								displayTable(alldata)
								$('#notifyModal').modal('show');
							}

						});
			});
	
	$(document).ready(function(){
	    if(!window.location.hash) {
	        window.location = window.location + '#loaded';
	        window.location.reload();
	    }
	});
	$("#pageName").text("Dashboard");
	$(".custDash").addClass("active");
	$(document).ready(function() {
		$('.custDash').on('click', function() {
		});
	});
</script>


<script>
	/* $(window).load(function(){        
	 $('#notifyModal').modal('show');
	 });  */

	var loginUserId = "1"

	function displayTable(listOrders) {
		$('#tableId').html('');
		var tableHead = '<table id="notification" class="table table-striped table-bordered datatables">'
				+ '<thead><tr><th>Task No</th><th>Summary</th><th>Category</th><th>priority</th><th>Severity</th><th>Assigned By</th><th>Created Time</th><th>Description</th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead);
		serviceUnitArray = {};

		$.each(listOrders, function(i, orderObj) {
			if (orderObj.additionalinfo == "0") {
				var deleterow = "<a class='deactivate' onclick='opentasks("
						+ orderObj.id
						+ ",0)'><i class='fa fa-folder-open-o'></i></a>"
			} else {
				var deleterow = "<a class='activate' onclick='opentasks("
						+ orderObj.id
						+ ",1)'><i class='fa fa-eye-slash'></i></a>"
			}

			var edit = "<a class='edit editIt' onclick='editTask("
					+ orderObj.id + ")'><i class='fa fa-edit'></i></a>"

			var view = "<a class='view viewIt' onclick='viewTask("
					+ orderObj.id + ")'>" + orderObj.taskno + "</a>"
			var view2 = "<a class='view viewIt' href='viewTicket?id="
					+ orderObj.id + "&pgn=1'>" + orderObj.taskno + "</a>"
			var comment = "<a class='comment commentIt' onclick='addComment("
					+ orderObj.id + ")'>   <i class='fa fa-comments'></i></a>"
			var time = "<a class='time timeIt' onclick='showdeadline("
					+ orderObj.id
					+ ")'> <i class='fa fa-hourglass-half'></i> </a>"
			serviceUnitArray[orderObj.id] = orderObj;
			var tblRow = "<tr>" + "<td title='"+orderObj.taskno+"'>" + view2
					+ "</td>" + "<td title='"+orderObj.subject+"'>"
					+ orderObj.subject + "</td>"
					+ "<td title='"+orderObj.category+"'>" + orderObj.category
					+ "</td>" + "<td title='"+orderObj.priority+"'>"
					+ orderObj.priority + "</td>" + "</td>"
					+ "<td title='"+orderObj.severity+"'>" + orderObj.severity
					+ "</td>" + "<td title='"+orderObj.assignby+"'>"
					+ orderObj.assignby + "</td>"
					+ "<td title='"+orderObj.createdTime+"'>"
					+ new Date(orderObj.createdTime).toDateString() + "</td>"
					+ "<td title='"+orderObj.description+"'>"
					+ orderObj.description + "</td>"
					/* + "<td style='text-align: center;white-space: nowrap;'>"
					 "</td>" */+ "</tr>";
			$(tblRow).appendTo("#tableId table tbody");
		});
		if (isClick == 'Yes')
			$('#notification').dataTable();

	}

	function opentasks(id, status) {
		var checkstr = null;
		if (status == 0) {
			status = 1;
			alert('Task Marked as Read');
		}
		var formData = new FormData();
		formData.append('id', id);
		formData.append('additionalinfo', status);
		$.fn.makeMultipartRequest('POST', 'openTask', false, formData, false,
				'text', function(data) {
					var jsonobj = $.parseJSON(data);
					var alldata = jsonobj.allOrders1;
					var result = $.parseJSON(alldata);
					if (result.length > 0) {
						displayTable(result)
						getHeadersCounts()

					} else
						getHeadersCounts()
						//location.reload()

				});

	}

	function goToTaskListBySelection(selection) {

		alert("hi");
		alert(selection);
		var ttype = selection;
		var formData = new FormData();
		formData.append('ttypeid', ttype);
		$.fn.makeMultipartRequest('POST', 'setdata', false, formData, false,
				'text', function(data) {
					var jsonobj = $.parseJSON(data);
					var alldata = jsonobj.list;
					displayTable(alldata);
					toolTips()
					makeEmpty()

				});
	}

	var listOrders1 = ${list};
	if (listOrders1 != "") {
		$('#categoryTable body').html('');
		/* var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Company Code</th><th>Company Name</th><th>Contact Person Name</th><th>Contact Person Mobile</th><th>Email Id</th><th>Type of Comapany</th><th>Company Address</th><th>Remarks</th><th>Status</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead); */
		serviceUnitArray = {};
		var categoryarray = null;
		var assigned = 0;
		var closed1 = 0;
		var resolved = 0;
		$
				.each(
						listOrders1,
						function(i, orderObj) {
							var totalcategory = 0;
							if (orderObj.kStatusNameWithId != "") {

								categoryarray = orderObj.kStatusNameWithId
										.split(",");
							}
							var occurrences = {};
							for (var i = 0, j = categoryarray.length; i < j; i++) {
								occurrences[categoryarray[i]] = (occurrences[categoryarray[i]] || 0) + 1;
							}
							if (occurrences['Assigned'] != undefined) {
								assigned = occurrences['Assigned'];
								totalcategory = totalcategory + assigned;
							} else {
								assigned = 0;
								totalcategory = totalcategory + assigned;
							}
							if (occurrences['Closed'] != undefined) {
								closed1 = occurrences['Closed'];
								totalcategory = totalcategory + closed1;
							} else {
								closed1 = 0;
								totalcategory = totalcategory + closed1;

							}
							if (occurrences['Resolved'] != undefined) {
								resolved = occurrences['Resolved'];
								totalcategory = totalcategory + resolved;
							} else {
								resolved = 0;
								totalcategory = totalcategory + resolved;
							}
							//<a href="severity?id=${issue.key}"
							console.log(occurrences['Assigned']);
							var tblRow = "<tr'>"
									+ "<td  title='"+orderObj.categoryName+"'>"
									+ orderObj.categoryName
									+ "</td>"
									+ "<td title='"+assigned+"' ><a href='categoryDashBord?status=2&categoryId="
									+ orderObj.categoryId
									+ "'  class='btn btn-danger assigned'>"
									+ assigned
									+ "</a></td>"
									+ "<td title='"+resolved+"'><a href='categoryDashBord?status=4&categoryId="
									+ orderObj.categoryId
									+ "'  class='btn btn-warning assigned'>"
									+ resolved
									+ "</td>"
									+ "<td title='"+closed1+"'><a href='categoryDashBord?status=1&categoryId="
									+ orderObj.categoryId
									+ "'  class='btn btn-primary assigned'>"
									+ closed1 + "</td>"
									+ "<td title='"+totalcategory+"'>"
									+ totalcategory + "</td>" + "</tr >";
							$(tblRow).appendTo("#categoryTable tbody");
						});
	}

	var byStatusList = ${byStatusList};
	if (byStatusList != "") {
		$('#statusTable body').html('');
		/* var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Company Code</th><th>Company Name</th><th>Contact Person Name</th><th>Contact Person Mobile</th><th>Email Id</th><th>Type of Comapany</th><th>Company Address</th><th>Remarks</th><th>Status</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead); */
		serviceUnitArray = {};
		var categoryarray = null;
		var assigned = 0;
		var closed2 = 0;
		var resolved = 0;

		$
				.each(
						byStatusList,
						function(i, orderObj) {
							var totalStatus = 0;
							if (orderObj.kStatusNameWithId != "") {

								categoryarray = orderObj.statusConcatination
										.split(",");
							}
							var occurrences = {};
							for (var i = 0, j = categoryarray.length; i < j; i++) {
								occurrences[categoryarray[i]] = (occurrences[categoryarray[i]] || 0) + 1;
							}
							if (occurrences['Assigned'] != undefined) {
								assigned = occurrences['Assigned'];
								totalStatus = totalStatus + assigned;
							} else {
								assigned = 0;
								totalStatus = totalStatus + assigned;
							}
							if (occurrences['Closed'] != undefined) {
								closed2 = occurrences['Closed'];
								totalStatus = totalStatus + closed2;
							} else {
								closed2 = 0;
								totalStatus = totalStatus + closed2;

							}
							if (occurrences['Resolved'] != undefined) {
								resolved = occurrences['Resolved'];
								totalStatus = totalStatus + resolved;
							} else {
								resolved = 0;
								totalStatus = totalStatus + resolved;
							}
							//<a href="severity?id=${issue.key}"
							console.log(occurrences['Assigned']);
							var tblRow = "<tr'>"
									+ "<td  title='"+orderObj.statusName+"'>"
									+ orderObj.statusName
									+ "</td>"
									+ "<td title='"+assigned+"' ><a href='statusDashBord?status="
									+ orderObj.statusId
									+ "'  class='btn btn-danger assigned'>"
									+ assigned
									+ "</a></td>"
									+ "<td title='"+resolved+"'><a href='statusDashBord?status="
									+ orderObj.statusId
									+ "'  class='btn btn-warning assigned'>"
									+ resolved
									+ "</td>"
									+ "<td title='"+closed1+"'><a href='statusDashBord?status="
									+ orderObj.statusId
									+ "'  class='btn btn-primary assigned'>"
									+ closed2 + "</td>"
									+ "<td title='"+totalStatus+"'>"
									+ totalStatus + "</td>" + "</tr >";
							$(tblRow).appendTo("#statusTable tbody");
						});
	}
	
	
	
/* 	$("#ack").mouseover(function(){
		
		alert("hello mousehour");
	}); */
	var deptcountjson = ${deptcountjson};
	var deptcountclosedjson = ${deptcountclosedjson};
	
	
	
	
	if (deptcountjson != "") {
		$('#deptTable body').html('');
		displayDeptTask(deptcountjson,deptcountclosedjson);
		
	}
	
	function displayDeptTask(deptcountjson,deptcountclosedjson){
		
		$.each(deptcountjson, function(i,item) {
			
			console.log(deptcountjson[i]+"------"+deptcountclosedjson[i]);
			
			var diff=parseInt(item)-parseInt(deptcountclosedjson[i])
			console.log(item);
			 var tblRow = "<tr'>"
					+ "<td> "
					+ i
					+ "</a></td>"
					+ "<td ><a href='deptAll?id="+i+"'>"
					+ item
					+ "</a></td>"
					+ "<td ><a href='deptClosed?id="+i+"'>"
					+ deptcountclosedjson[i]
					+ "</a></td>"
					+ "<td ><a href='deptBalanced?id="+i+"'>"
					+ diff
					+ "</a></td>"
					+ "</tr >";
			$(tblRow).appendTo("#deptTable tbody"); 
		});
		
	}

		
	

</script> 