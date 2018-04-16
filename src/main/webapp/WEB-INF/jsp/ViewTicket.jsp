<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style type="text/css">
.fa-square:before {
    content: "\f0c8";
}
.fa-status-box {
    font-size: 1.8em;
    line-height: 0.73em;
    vertical-align: -25%;
}
.status-90-color {
    color: #c9ccc4 !important;
    background-color: #c9ccc4;
}
.status-10-color {
    color: #fcbdbd !important;
    background-color: #fcbdbd;
}
.status-80-color {
    color: #d2f5b0 !important;
    background-color: #d2f5b0;
}
.panel-body {

	border: 1px solid #4f8edc !important;
	padding: 0px !important;
}
.btn-white.btn-primary.active, .btn-white.btn-primary:active, .btn-white.btn-primary:focus, .btn-white.btn-primary:hover, .open>.btn-white.btn-primary.active.dropdown-toggle, .open>.btn-white.btn-primary.dropdown-toggle {
    background-color: #eaf2f8!important;
    border-color: #8aafce !important;
    color: #537c9f!important;
}
.widget-toolbox {
    background-color: #EEE;
}
.widget-toolbox.padding-8 {
    padding: 8px;
}

.widget-toolbox:first-child {
    padding: 2px;
    border-bottom: 1px solid #CCC;
}
.btn-sm {
	margin:8px;
	letter-spacing: 1px;
	line-height: 1.6;
}
.tr {
	background:#166eaf;
	color:#ffffff;
}
</style>

<!-- Body starts here -->

	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li class="">View Ticket</li>
				</ul><!-- /.breadcrumb -->
			</div>
			
			<div class="page-content">
				<div class="row">
				
				<!-- view-->
			<div class="container">
				<div class="col-md-12">
				<div class="panel panel-primary">
					<div style="margin:0 auto;" class="panel-heading">
						<h4><i class="ace-icon fa fa-bars"></i>View Issue Details</h4>
						<div class="options">
							<a href="" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel panel-body collapse in">
						<div class="widget-toolbox padding-8 clearfix noprint">
							<div class="btn-group pull-left">
							<a class="btn btn-primary  btn-round btn-sm" href="">Wiki</a><a class="btn btn-success  btn-round btn-sm" href="">Jump to Notes</a><a class="btn btn-warning  btn-round btn-sm" href="#history">Jump to History</a></div><div class="btn-group pull-right"></div></div><div class="clearfix"></div>
						<br>
						<div class="col-md-2"></div>
						<div class="col-md-8">
						
						<div class="table-responsive">
								<table class="table table-bordered priority prioritybg"
									style="border: 1px solid #0460a4; width:;" id="viewTaskTable">
									<thead>
									<tr class="tr">

										<th>Task No</th>
										<td></td>
									</tr>
									<tr>
										<th>Category</th>
										<td></td>
									</tr>
									<tr class="tr">
										<th>Model Name</th>
										<td></td>
									</tr>
									<tr>
										<th>Service Type</th>
										<td></td>
									</tr>
									<tr class="tr">
										<th>Serverity</th>
										<td></td>
									</tr>
									<tr>
										<th>Priority</th>
										<td></td>
									</tr>
									<tr class="tr">
										<th>Assigned to</th>
										<td></td>
									</tr>
									<tr>
										<th>Subject</th>
										<td></td>
									</tr>
									<tr class="tr">
										<th>Task Deadline</th>
										<td></td>
									</tr>
									<tr>
										<th>Task Status</th>
										<td></td>
									</tr>
									<tr class="tr">
										<th>Create Time</th>
										<td></td>
									</tr>
=======

<c:forEach var="listOuter" items="${test2}">
       
              <c:forEach var="listInner" items="${listOuter}">
                 <tr>
                  <td>
                      ${listInner.key}
                  </td>
                  <td>
                      ${listInner.value}
                  </td>
                   </tr>
              </c:forEach>
         
      </c:forEach>
									

									</thead>

																	</table>
							</div>
</div>
	<div class="col-md-2"></div>
</div>

				</div>
							</div>
						
				
               <%--  
                <div class="col-md-12" id="history">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4><i class="ace-icon fa fa-history"></i>
 Issue History</h4>
						<div class="options">
							<a href="" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel panel-body collapse in">
												<div class="table-responsive">
							<table class="table table-bordered table-condensed  table-striped table-hover" style=" ">
								<tr style="background:#006699;color: #fff;">
<th>Last Updated</th><th>User Name</th><th>Attachment</th><th>field</th><th>Change</th>
								</tr>
								
<c:forEach var="logs" items="${repeatLogs1}">								
								<tr>
<td> ${logs.createdTime}</td><td>${logs.changedby}</td>
<td>${logs.kpstatus}</td>
<td>


<c:forTokens items="${logs.uploadfiles}" delims="*" var="mySplit">
     	<a class="attachments" download href="reportDocuments/${mySplit}"><i class="fa fa-download" title="${mySplit}"></i></a>
     	</c:forTokens>
  

</td><td>${logs.kpfield}</td>
</td><td>${logs.kpchange}</td>
 --%>

</tr>

								</table>
			
</div>


</div>
		
					</div><!-- /.page-content -->
								
								
						</div>
					</div>
				</div>
			<!-- /page-content -->
		</div>
		<!-- /.main-content-inner -->
	
	<!-- /.main-content -->

<!-- Body ends here -->

	<link rel="stylesheet" type="text/css" href="http://charvikent.com/mantis/css/dropzone-4.3.0.min.css" />
<script type="text/javascript">


var 	severityCounts  = ${viewTask};


var rowdata;
	rowdata='<tr>'
			+'<td> Assigned To Me</td>'
			+'<td><a href="severityBy?id=3">'+ severityCounts.CRITICAL +'</a></td>'
			+'<td><a href="severityBy?id=2">'+ severityCounts.MAJOR +'</a></td>'
			+'<td><a href="severityBy?id=1">'+ severityCounts.MINOR +'</a></td>'
				+'</tr>';
$("#severityTable").append(rowdata);
	
	
$(".task").addClass("active");
$("#pageName").text("View Task");


</script> 