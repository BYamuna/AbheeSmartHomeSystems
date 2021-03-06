<%@include file="abheeheader.jsp" %>
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
.table>thead>tr>th {
    padding: 3px;
    }
    .bodypanel {
border: none !important;
    box-shadow: none !important;
    padding: 5px !important;
}
.panel-heading {
    padding: 1px 15px;
    }
    .table>tbody>tr>td {
    padding: 6px;
    }
</style>

<!-- Body starts here -->

	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
				<!-- 	<li class="">Service Request History</li> -->
				</ul><!-- /.breadcrumb -->
			</div>
			
			<div class="page-content">
				<div class="row">
				
				<!-- view-->
			<div class="container">
				<div class="col-md-12">
				<div class="panel panel-primary">
					<div style="margin:0 auto;" class="panel-heading">
						<h4>Service  Request  Details</h4>
						<!-- <div class="options">
							<a href="" class="panel-collapse"><i class="fa fa-chevron-up"></i></a>
						</div> -->
					</div>
					<div class="panel panel-body collapse in bodypanel">
						<br>    
						
						<div class="col-md-12">
						
						<div class="table-responsive">
								<table class="table table-bordered priority prioritybg" style="border: 1px solid #0460a4; width:;" id="viewTasksTable">
								 <thead><tr><th>Assigned To</th><th>Service Type</th><th>Created Time</th><th>Description</th><th>Request Status</th><th>Priority</th><th>Severity</th><th>Subject</th><th>Deadline</th><th>Task No</th><th>Category</th><th>Model Name</th><th>Customer Id</th>
				                 <th>Files</th></tr><tr></tr></thead><tbody></tbody></table>

			<%-- <c:forEach var="listOuter" items="${test2}">
       
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
					 --%>				
					 

									

							</div>
</div>
	<div class="col-md-2"></div>
</div>

				</div>
							</div>
						
				
              

</tr>

								</table>
			
</div>


<div class="container">
<div align="center">
<button onclick="goBack()" class="btn btn-primary"> <i class="fa fa-step-backward"></i> Back  </button></div>
<div class="col-md-12"><br>
				<div class="panel panel-primary">
					<div style="margin:0 auto;" class="panel-heading rounded-bottom">
						<h4>Service Request History</h4>
						<!-- <div class="options">
							<a href="" class="panel-collapse"><i class="fa fa-chevron-up"></i></a>
						</div> -->
					</div>
					<div class="panel panel-body collapse in bodypanel">
						<div class="clearfix"></div>
						<br>
						<div class="col-md-2"></div>
						<div class="col-md-8">
						
						<div class="table-responsive">
								<table class="table table-bordered priority prioritybg" style="border: 1px solid #0460a4; width:;" id="viewStatusTable">


       
              
                 <thead><tr>
                  <th>
                   Modified User Name   
                  </th>
                  <th>
                    Product model
                  </th>
                  <th>
                    Modified Status
                  </th>
                  <th>
                     created_time  
                  </th>
                  <th>
                     Note
                  </th>
                  <th>
                     Attached Files
                  </th>
                   </tr>
                 <tr>
                 
                 </tr>
                
              
         
      
									

									

																	</thead><tbody></tbody></table>
							</div>
</div>
	<div class="col-md-2"></div>
</div>

				</div>
							</div>
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
	<%@include file="abheefooter.jsp" %>
<script type="text/javascript">
var viewTasksTable = ${test21};
var allstatus = ${statuslist1};
$(function(){
$.each(allstatus, function(k, v){

	var Attachfile='';
if(v.Attachfile1 != null && v.Attachfile1 != ''){
	var list=v.Attachfile1.split('*');
	
	for(var i=0;i<list.length;i++)
	{
		Attachfile=Attachfile+'<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
	}
	//v.Attachfile1=Attachfile;
}
/* if(v.Attachedfile==undefined) v.Attachedfile='';
else
{
	var list=v.Attachedfile.split('*');
	var Attachedfile='';
	for(var i=0;i<list.length;i++)
	{
		Attachedfile=Attachedfile+'<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
	}
	v.Attachedfile=Attachedfile;
} */
var tr=	'<tr>'
+'<td>'+ v.username +'</td>'
+'<td>'+ v.productname +'</td>'
+'<td>'+ v.servicestatus +'</td>'
+'<td>'+ v.created_time +'</td>'
+'<td>'+ v.add_comment +'</td>'
+'<td id=img'+k+'>'+Attachfile+'</td>'
/* +'<td>'+v.Attachedfile+'</td>' */
+'</tr>';
$('#viewStatusTable tbody').append(tr);

if(v.Attachfile != null && v.Attachfile != ''){

$('#img'+k).text('');
$('#img'+k).append('<a href="../abheeimg/'+v.Attachfile+'" target="_blank" title="'+v.Attachfile+'"><img src="../abheeimg/'+v.Attachfile+'" style="height:42px; width:42px"></a>');
}
});
});
$(function(){
              
$.each(viewTasksTable, function(k, v){
	console.log(v);
	
	if(v.AttachedFiles==undefined) v.AttachedFiles='';
	else
		{
			var list=v.AttachedFiles.split('*');
			var Attachfile='';
			for(var i=0;i<list.length;i++)
			{
				Attachfile=Attachfile+'<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
			}
			v.AttachedFiles=Attachfile;
		}
	if(v.description==null){
		
		v.description='---';
	}
var tr=	'<tr>'
		+'<td>'+ v.username +'</td>'
		+'<td>'+ v.servicetypename +'</td>'
		+'<td>'+ v.created_time +'</td>'
		+'<td>'+ v.description +'</td>'
		+'<td>'+ v.Requeststatus  +'</td>'
		+'<td>'+ v.priority +'</td>'
		+'<td>'+ v.severity +'</td>'
		+'<td>'+ v.subject +'</td>'
		+'<td>'+ v.taskdeadline +'</td>'
		+'<td>'+ v.taskno +'</td>'
		+'<td>'+ v.category +'</td>'
		+'<td>'+ v.modelname +'</td>'
		+'<td>'+ v.customer_id +'</td>'
		+'<td>'+v.AttachedFiles+'</td>'
		+'</tr>';
$('#viewTasksTable tbody').append(tr);
});
});
function goBack() {
    window.history.go(-1);
    //window.location.reload();
}
	
$(".ticketstatus").addClass("active");
$("#pageName").text("Service Request History");
 

</script> 
