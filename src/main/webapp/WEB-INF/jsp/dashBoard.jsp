


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
.panel-heading {
	background:#cccccc;
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
		<div class="page-content container" style="background-color: #fff;">
			<div class="col-md-12"
				style="background-color: white !important; padding-top: 15PX;">
				<div class="panel">
					<div class="panel-heading">
						<h4>Dashboard</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i
								class="fa fa-chevron-down"></i></a>
						</div>
					</div>

					<div class="panel-body collapse in">
						<div class="col-md-8">

							<div class="table-responsive">
								<table class="table table-bordered priority prioritybg"
									style="border: 1px solid #0460a4; width:;" id="severityTable">
									<thead>
									<tr
										style="background-color: #0460a4; color: #fff; text-align: center;">

										<th>Unclosed Tasks /Severity</th>
										<th>Critical</th>
										<th>Major</th>
										<th>Minor</th>

									</tr>
									</thead>

																	</table>
							</div>
							<!-- *********************************** By Severity End *****************-->

							<!-- *********************************** By Status Start *****************-->

							<br>
							<div class="table-responsive">
								<table class="table table-bordered priority prioritybg"
									style="border: 1px solid #0460a4;" id="statusTable">
									<thead>
										<tr
											style="background-color: #0460a4; color: #fff; text-align: center;">
											<th>By Status</th>
											<th>Open</th>
											<th>Resolved</th>
											<th>Closed</th>
											<th>Total</th>
										</tr>
										
									
									</thead>
									<tbody>


									</tbody>

								</table>
							</div>

							<!--  **************************  By Status End  ******************************-->

							<!-- *********************************** By Category Start *****************-->
							<br>
							<div class="table-responsive">
								<table class="table table-bordered priority prioritybg"
									style="border: 1px solid #0460a4;" id="categoryTable">
									<thead>
										<tr
											style="background-color: #0460a4; color: #fff; text-align: center;">

											<th>By Category</th>
											<th>Open</th>
											<th>Resolved</th>
											<th>Closed</th>
											

										</tr>
										
										<tr>
										<td>Home Theaters</td>
										<td>5</td>
										<td>2</td>
										<td>3</td>
										</tr>
										
										
										<tr>
										<td>PA Audios</td>
										<td>9</td>
										<td>4</td>
										<td>5</td>
										
										</tr>
										
										
										<tr>
										<td>Projectors</td>
										<td>8</td>
										<td>7</td>
										<td>1</td>
									
										</tr>
									</thead>

									<tbody>
									

									</tbody>
								</table>
							</div>

							<!--  ************************** By Category End  ******************************-->
							
							
							<br>
							
							
						

							
							<!-- *********************************** By Department Start *****************-->
							 <security:authorize access="hasRole('ROLE_ADMIN')">
							<div class="table-responsive">
								<table class="table table-bordered priority prioritybg"
									style="border: 1px solid #0460a4;" id="deptTable">
									<thead>
										<tr
											style="background-color: #0460a4; color: #fff; text-align: center;">

											<th>Branch Name</th>
											<th>Open</th>
											<th>Closed</th>
											<th>Balanced</th>

										</tr>
										
										<tr>
										<td>Guntur</td>
										<td>3</td>
										<td>1</td>
										<td>2</td>
										</tr>
										
										<tr>
										<td>Vijayawada</td>
										<td>23</td>
										<td>19</td>
										<td>4</td>
										</tr>
										
										<tr>
										<td>Vizag</td>
										<td>55</td>
										<td>29</td>
										<td>26</td>
										</tr>
										
									</thead>

									<tbody>
									

									</tbody>
								</table>
							</div>
								</security:authorize>
								
								<!--  ************************** By Department  End  ******************************-->

						</div>



						<div class="col-md-4" style="border: 1px solid #ccc;">
							<div id="assigned" class="widget-box widget-color-blue2">
								<div class="widget-header widget-header-small">
									<h4 class="widget-title lighter">
										<i class="ace-icon fa fa-clock-o"></i> <a class="white"
											href="#">Timeline</a>
									</h4>
									<div class="widget-toolbar">
										<a data-action="collapse" href="#"> <!-- <i class="1 ace-icon fa bigger-125 fa-chevron-up"></i> -->
										</a>
									</div>
									<div class="widget-toolbar no-border hidden-xs"></div>
								</div>

								<div style="display: block;" class="widget-body">
									<div class="widget-main no-padding">
										<div class="table-responsive" style="overflow-x: inherit;">
											<table
												class="table table-bordered table-condensed table-striped table-hover ">
												<tbody>

													<tr class="my-buglist-bug ">
													<tr>
														<th>By Date(days)</th>
														<th>Open</th>
														<th>Closed</th>
														<th>Balanced</th>

													</tr>
													<tr>
														<td>0-1</td>
														<td>1</td>
														<td>0</td>
														<td>1</td>

													</tr>
													
													<tr>
														<td>1-2</td>
														<td>1</td>
														<td>0</td>
														<td>1</td>

													</tr>
													
													<tr>
														<td>2-3</td>
														<td>1</td>
														<td>0</td>
														<td>1</td>

													</tr>
													
													<tr>
														<td>3-7</td>
														<td>1</td>
														<td>0</td>
														<td>1</td>

													</tr>
													
													<tr>
														<td>7-30</td>
														<td>1</td>
														<td>0</td>
														<td>1</td>

													</tr>
													
													<tr>
														<td>30-60</td>
														<td>1</td>
														<td>0</td>
														<td>1</td>

													</tr>
													
													<tr>
														<td>60-90</td>
														<td>1</td>
														<td>0</td>
														<td>1</td>

													</tr>
													
													<tr>
														<td>90-180</td>
														<td>1</td>
														<td>0</td>
														<td>1</td>

													</tr>
													
													<tr>
														<td>180-365</td>
														<td>1</td>
														<td>0</td>
														<td>1</td>

													</tr>
													
													<c:forEach var="issue" items="${gapAndCount}">

														<tr class="my-buglist-bug ">
															<td class="nowrap width-13">${issue.key}</a></td>
															<c:forEach items="${issue.value}" var="entry"
																varStatus="loop">
																<td><a
																	href="timeline${loop.index}?range=${issue.key}">${entry}</a></td>
															</c:forEach>

														</tr>

													</c:forEach>
											</table>


										</div>
									</div>
								</div>
								<!-- /.row -->
							</div>
							<!-- /.page-content -->
						</div>







					</div>
				</div>
				<!-- <div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Dashboard</h4>
						<div class="options">
							<a href="" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
						
						<div class="col-md-2"></div>
					</div>
				</div>
			</div> -->

				<div class="row" style="margin-bottom: 10px"; >

				<c:forEach var="issue" items="${severityCount}">
					<c:set var="String" value="${issue.key}" />
					<c:if test="${fn:contains(String, 'Critical')}">
						<div class="btn-toolbar pull-left" style="margin-left: 5px"!important">
							<span
								style="font-size: 18px; lettee-spacinf: 1px; color: #006699;">Assigned
								to Me</span> <a href="severity?id=${issue.key}" class="btn btn-danger "
								style="border-radius: 15px;"><span id="unseentasks">
									${issue.value} </span><br>${issue.key} </a>
						</div>
					</c:if>
					<c:if test="${fn:contains(String, 'Major')}">
						<div class="btn-toolbar pull-left">
							<a href="severity?id=${issue.key}" class="btn btn-warning "
								style="border-radius: 15px;"><span id="unseentasks">
									${issue.value} </span><br>${issue.key} </a>
						</div>
					</c:if>
					<c:if test="${fn:contains(String, 'Minor')}">
						<div class="btn-toolbar pull-left">
							<a href="severity?id=${issue.key}" class="btn btn-primary "
								style="border-radius: 15px;"><span id="unseentasks">
									${issue.value} </span><br>${issue.key} </a>
						</div>
					</c:if>
				</c:forEach>

			</div>
			<div class="row" style="margin-bottom: 10px"; >

				<c:forEach var="issue" items="${severityCountsBY}">
					<c:set var="String" value="${issue.key}" />
					<c:if test="${fn:contains(String, 'Critical')}">
						<div class="btn-toolbar pull-left" style="margin-left: 5px"!important">
							<span style="font-size: 18px; lettee-spacinf: 1px; color: #006699;">Assigned	BY Me</span> 
							
							<a href="severityby?id=${issue.key}"
								class="btn btn-danger " style="border-radius: 15px;"><span
								id="unseentasks"> ${issue.value} </span><br>${issue.key} </a>
						</div>
					</c:if>
					<c:if test="${fn:contains(String, 'Major')}">
						<div class="btn-toolbar pull-left">
							<a href="severityby?id=${issue.key}" class="btn btn-warning "
								style="border-radius: 15px;"><span id="unseentasks">
									${issue.value} </span><br>${issue.key} </a>
						</div>
					</c:if>
					<c:if test="${fn:contains(String, 'Minor')}">
						<div class="btn-toolbar pull-left">
							<a href="severityby?id=${issue.key}" class="btn btn-primary "
								style="border-radius: 15px;"><span id="unseentasks">
									${issue.value} </span><br>${issue.key} </a>
						</div>
					</c:if>
				</c:forEach>
			</div>
			<br>

			<div class="row">

				<c:forEach var="issue" items="${SevMonitoredCounts}">
					<c:set var="String" value="${issue.key}" />
					<c:if test="${fn:contains(String, 'Critical')}">
						<div class="btn-toolbar pull-left" style="margin-left: 5px"!important">
							<span
								style="font-size: 18px; lettee-spacinf: 1px; color: #006699;">Monitored
								BY Me</span> 
								<a href="severityReportTo?id=${issue.key}"
								class="btn btn-danger " style="border-radius: 15px;"><span
								id="unseentasks"> ${issue.value} </span><br>${issue.key} </a>
						</div>
					</c:if>
					<c:if test="${fn:contains(String, 'Major')}">
						<div class="btn-toolbar pull-left">
							<a href="severityReportTo?id=${issue.key}"
								class="btn btn-warning " style="border-radius: 15px;"><span
								id="unseentasks"> ${issue.value} </span><br>${issue.key} </a>
						</div>
					</c:if>
					<c:if test="${fn:contains(String, 'Minor')}">
						<div class="btn-toolbar pull-left">
							<a href="severityReportTo?id=${issue.key}"
								class="btn btn-primary " style="border-radius: 15px;"><span
								id="unseentasks"> ${issue.value} </span><br>${issue.key} </a>
						</div>
					</c:if>
				</c:forEach>

			</div>



				<!-- History table starts Here -->

				<div class="row" style="background: white;">


					<div class="col-sm-4">
						<div class="space-10"></div>

					</div>
					<!-- /.row -->
					<!-- /.page-content -->
				</div>
			</div>
		</div>
		<!-- /.page-content -->
	</div>
	<!-- /.main-content-inner -->
</div>

<div id="notifyModal" class="modal fade" data-backdrop="static"
	data-keyboard="false" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="col-md-12" style="width: 800px; margin-left: -55px;">
			<div class="panel panel-primary">

				<div class="panel-heading">
					<h4>Unread Tasks List</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="panel-body collapse in">
					<!-- <input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label> -->
					<div style="overflow:auto;" class="table" id="tableId">
						<table cellpadding="0" cellspacing="0" border="0"	class="table table-striped table-bordered datatables"	id="notification">
							<thead>
								<tr>
									<th>Dept ID</th>
									<th>Name</th>
									<th>Description</th>
									<th>Status</th>
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
<script type="text/javascript">
	
	$("#pageName").text("Dashboard");
	$(".dashBoard").addClass("active");
	$(document).ready(function() {
		$('.dashBoard').on('click', function() {
		});
	});
</script>


<script>
	/* $(window).load(function(){        
	 $('#notifyModal').modal('show');
	 });  */

	var loginUserId = "1";
	var 	severityCounts  = ${severityCounts};
	
	
	var rowdata;
		rowdata='<tr>'
				+'<td> Assigned To Me</td>'
				+'<td><a href="severityBy?id=3">'+ severityCounts.CRITICAL +'</a></td>'
				+'<td><a href="severityBy?id=2">'+ severityCounts.MAJOR +'</a></td>'
				+'<td><a href="severityBy?id=1">'+ severityCounts.MINOR +'</a></td>'
					+'</tr>';
	$("#severityTable").append(rowdata);
		
		
	
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
	
	var loginUserDId ="1";
	
	function displayTable(listOrders) {
		$('#tableId').html('');
		var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
				+ '<thead><tr><th>Task No</th><th>Category</th><th>Model Name</th><th>ServiceType</th><th>Severity</th><th>Priority</th><th>Assigned To</th><th>Subject</th><th>Task Deadline</th><th>Task Status</th><th>CreateTime</th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead);
		serviceUnitArray = {};
		
		$.each(listOrders,function(i, orderObj) {
			if(loginUserDId == "1")
				{
			if(orderObj.status == "1"){
				var deleterow = "<a class='deactivate' onclick='deletetask("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>"
			}else{  
				var deleterow = "<a class='activate' onclick='deletetask("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>"
			}
			
			
				}
			else
			{
				deleterow =" ";
			}
			
			var edit = "<a class='edit editIt' onclick='editTask("	+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"
			
			
			var view = "<a class='view viewIt' onclick='viewTask("	+ orderObj.id+ ")'>"+ orderObj.taskno+ "</a>"
			var history2 = "<a class='history historyit' onclick='viewTask2("	+ orderObj.id+ ")'> <i class='fa fa-history'></i></a>"
			var view2 = "<a class='view viewIt' href='viewTicket?id="	+ orderObj.taskno+ "&pgn=1'>"+ orderObj.taskno+ "</a>"
			var comment = "<a class='comment commentIt' onclick='addComment("	+ orderObj.id+ ")'>   <i class='fa fa-comments'></i></a>"
			var time = "<a class='time timeIt' onclick='showdeadline("	+ orderObj.id+ ")'> <i class='fa fa-hourglass-half'></i> </a>"
			var history = "<a class='history historyit' onclick='viewTask("	+ orderObj.id+ ")'> <i class='fa fa-history'></i></a>"
			
			
			serviceUnitArray[orderObj.id] = orderObj;
			var tblRow = "<tr>"
				+ "<td title='"+orderObj.taskno+"'>"+ view2 + "</td>"
				+ "<td title='"+orderObj.category+"'>"+ orderObj.category + "</td>"
				+ "<td title='"+orderObj.modelname+"'>"+ orderObj.modelname + "</td>"
				+ "<td title='"+orderObj.servicetypename+"'>"+ orderObj.servicetypename + "</td>"
				+ "<td title='"+orderObj.severity+"'>"+ orderObj.severity + "</td>"
				+ "<td title='"+orderObj.priority+"'>"+ orderObj.priority + "</td>"
				+ "<td title='"+orderObj.username+"'>"+ orderObj.username + "</td>"
				+ "<td title='"+orderObj.subject+"'>"+ orderObj.subject + "</td>"
				+ "<td title='"+orderObj.taskdeadline+"'>"+ orderObj.taskdeadline + "</td>"
				+ "<td title='"+orderObj.statusname+"'>"+ orderObj.statusname + "</td>"
				+ "<td title='"+orderObj.created_time+"'>"+ orderObj.created_time + "</td>"
				+ "</tr>";
			$(tblRow).appendTo("#tableId table tbody");
		});
		if(isClick=='Yes') $('#example').dataTable();
		
	}
	
	
	

	

</script> 