<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<style>
.modal-header .close {
    margin-top: -2px;
    color: #fff;
    opacity: 1;
}
/* .modal {
	z-index: 1041 !important;
} */
</style>
	<div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="dashBoard">Home</a></li>
		<li>Quotation Requests</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="background-color: white !important; padding-top: 15PX;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Quotation List</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
					<input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
						<div class="table-responsive" id="tableId">
							<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered " id="example">
								<thead><tr><th>Model Number</th><th>Email Id</th><th>Mobile Number</th><th>Files</th><th>Location</th><th>Address</th><th>Requirements description</th><th></th></tr></thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	<form:form class="form-horizontal" commandName="salesrequestf" role="form"  action="totalallsalesrequest" method="post" enctype="multipart/form-data">
		</form:form>
	</div>
			<!-- container -->
			
	<!-- add comment Modal Starts here-->
	
<div class="modal fade" id="formModal" data-backdrop="static" data-keyboard="false"  role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="background: #2973cf;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="color: white;"> Send Quotation </h4>
        	</div>
        	<div class="modal-body">
					<form class="form-horizontal">
					<div class="panel-body">
					<input type="hidden" id="salesRequestid" />
						<div class="row">
							<div class="col-md-6">
                    		<div class="form-group" style=" width: 154%;">
									<label class="ace-file-input ace-file-multiple col-sm-3 control-label no-padding-right" >Attach File(s)</label>
									<div class="col-md-9">
										<input type="file" name="fileupload" id="fileupload"  class="validate "  multiple style="margin: 8px 0px 0px 0px;">
									</div>
							</div>
							
							<!-- <div class="form-group" style=" width: 154%;">
									<label class="ace-file-input ace-file-multiple col-sm-3 control-label no-padding-right" >Description</label>
									<div class="col-md-9">
										<input type="text" name="description" id="description"  class="validate "  multiple style="margin: 8px 0px 0px 0px;">
									</div>
							</div> -->
							</div>
							</div>
					<div class="row">
						<div class="col-md-6">
								<div class="form-group" style=" width: 154%;">
									<label class="col-sm-3 control-label no-padding-right">Notes</label>
										<div class="col-md-6">
											<textarea class="form-control validate" name="notes" id= "notes"  placeholder="Notes"></textarea>
										</div>
								</div>	
							</div>
					</div>	
							
                    <div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar text-center">
					      			<input type="button" id="modelSubmit" value="Submit"  onclick="submitCommet()"  class="btn-primary btn"/>
					      			<input type="reset" value="Reset" class="btn-danger btn cancel1"/>
				      			</div>
				      		</div>
				      	</div>
			      	</div>
						</div>
					</form>
				</div>	
			</div>
		</div>
	</div>	
	
	
	
	
	
	
	<!-- Task History 2 Modal Starts here-->
<div class="modal fade" id="myModal2" data-backdrop="static" data-keyboard="false" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header"  style="background: #4f8edc;">
				<button style="color:#fff;opacity:100 !important;" type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 style="color:#fff;" class="modal-title"> Quotation History  </h4>
        	</div>
        	<div class="modal-body">
				<div class="row">
				<div class="table-responsive" id="HtableId2">
							<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered " id="example2">
								<thead><tr class="info"><th>Date Modified</th><th>User Name</th><th>Attachment</th><th>Field</th><th>Change</th></tr></thead>
								<tbody></tbody>
							</table>
						</div>
				</div>
			</div>
      	</div>
    </div>
</div>
<!-- Modal Ends here-->				
</body>










<%-- <script type='text/javascript' src='${baseurl }/js/custemValidation.js'></script>  --%>

<script type="text/javascript">

/* $(document).ready(function() {
	 var table = $('#example').DataTable();
	  
	 $('#example tbody').on('click', 'tr', function () {
	     var data = table.row( this ).data();
	     alert( 'You clicked on '+data[0]+'\'s row' );
	 } );
}); */


var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');

	var tableHead = '<table id="example" class="table table-striped table-bordered ">'
			+ '<thead><tr><th>Request Number</th><th>Product Category</th><th>Product Model</th><th>Email Id</th><th>Mobile Number</th><th>Files</th><th>Location</th><th>Address</th><th>Comments</th><th style="text-align: center;">Options</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		if(orderObj.imgfiles==undefined) orderObj.imgfiles='';
		else
			{
				var list=orderObj.imgfiles.split('*');
				var imgfiles='';
				for(var i=0;i<list.length;i++)
				{
					imgfiles=imgfiles+'<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
				}
				orderObj.imgfiles=imgfiles;
			}
			/* if(orderObj.quotation_documents==undefined) orderObj.quotation_documents='';
			else
				{
					var list=orderObj.quotation_documents.split('*');
					var quotation_documents='';
					for(var i=0;i<list.length;i++)
					{
						quotation_documents=quotation_documents+'<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
					}
					orderObj.quotation_documents=quotation_documents; */

		 if(orderObj.qstatus == "1"){
			var deleterow = "<a class='deactivate' onclick='deleteTotalSales("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>"
		}else{  
			var deleterow = "<a class='activate' onclick='deleteTotalSales("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>"
		}
					/*var edit = "<a class='edit editIt' onclick='editTotalSales("	+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"*/		
 		var comment = "<a class='comment commentIt' onclick='addComment("	+ orderObj.id+ ")'>   <i class='fa fa-comments'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
 		
 		var history = "<a class='history historyit' onclick='viewTask("	+ orderObj.id+ ")'> <i class='fa fa-history'></i></a>"
 		
		/* var checkbox="<input type='checkbox' class='form-check-input' id='salesrequest'>" */
		var tblRow = "<tr>"
			+ "<td title='"+orderObj.salesrequestnumber+"'><a href=viewQuotation?id='"+ orderObj.id+"'>"+ orderObj.salesrequestnumber+"</a></td>"
			+ "<td title='"+orderObj.category+"'>"+ orderObj.category + "</td>"
			+ "<td title='"+orderObj.modelnumber+"'>"+ orderObj.modelnumber + "</td>"
			+ "<td title='"+orderObj.email+"'>"+ orderObj.email + "</td>"
			+ "<td title='"+orderObj.mobileno+"'>"+ orderObj.mobileno + "</td>"
			+ "<td title='"+orderObj.imgfiles+"'>"+ orderObj.imgfiles + "</td>"
			+ "<td title='"+orderObj.location+"'>"+ orderObj.location + "</td>"
			+ "<td title='"+orderObj.address+"'>"+ orderObj.address + "</td>"
			+ "<td title='"+orderObj.reqdesc+"'>"+ orderObj.reqdesc + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;' title='Quotation History'>" +comment+ "&nbsp;&nbsp;"+history+"&nbsp;&nbsp;" + deleterow +"</td>" 
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	if(isClick=='Yes') $('.datatables').dataTable();
	
}
/* var cissueid =0; */
function addComment(id)
{
	var salesRequestid=$('#salesRequestid').val(id);
	/* cissueid=id;
	$("#issueid").val(id); */
	$("#formModal").modal();	
}
var idArrayCmt11 = null;

function submitCommet()
{"src/main/resources"
 idArrayCmt11 = $.makeArray($('.validate2').map(function() {
	return this.id;
	}));
validation = true;
$.each(idArrayCmt11, function(i, val) {
	var value = $("#" + idArrayCmt11[i]).val();
	var placeholder = $("#" + idArrayCmt11[i]).attr('placeholder');
	if (value == null || value == "" || value == "undefined") {
		$('style').append(styleBlock);
		$("#" + idArrayCmt11[i] ).attr("placeholder", placeholder);
		$("#" + idArrayCmt11[i] ).css('border-color','#e73d4a');
		$("#" + idArrayCmt11[i] ).css('color','#e73d4a');
		$("#" + idArrayCmt11[i] ).addClass('placeholder-style your-class');
		 var id11 = $("#" + idArrayCmt11[i]+"_chosen").length;
		if ($("#" + idArrayCmt11[i]+"_chosen").length)
		{
			$("#" + idArrayCmt11[i]+"_chosen").children('a').css('border-color','#e73d4a');
		}
//		$("#" + idArray[i] + "Error").text("Please " + placeholder);
		validation = false;
	} 
});
if(validation) {
	
}else {
	return false;
}
		/* 
	     var commet=$('#commet').val();
	   
	    var id=$('#id').val();
		   
		   var formData = new FormData();
		   
		   formData.append('commet', commet); 
		   
		   formData.append('id',id);*/
		    
		  var id= $('#salesRequestid').val();
		   var notes= $('#notes').val();
		   
    	var ins = document.getElementById('fileupload').files.length;
      var data = new FormData();
     data.append('id',id); 
     data.append('description',notes); 

    	  for(var i=0; i< ins; i++)
    	{	
    	var quotation = document.getElementById('fileupload').files[i];
    	
		data.append('file', quotation);
		} 
    	 
		/* jQuery.each(jQuery('#fileupload')[0].files, function(i, file) {
			data.append('file-'+i, file);
		}); */
    	console.log(data);
		
 		$.ajax({
			type:"post",
			enctype: 'multipart/form-data',
		  	
		  	processData: false,  // tell jQuery not to process the data
			contentType: false,  // tell jQuery not to set contentType
			url: "sendingQuotation", 
		  	data:data,
			beforeSend : function() {
				$("#modelSubmit").click(function(){
					$("#modelSubmit").prop('disabled',true);
					$("#modelSubmit").val('Please wait...');
					$("form").submit();	
					
				}); 
				//$.blockUI({ message: 'Please wait' });
	          },
		  	success: function(result){
		  		
		  		if(result =="true"){
		  			alert("Quotation sent successfully");
		  			$('#formModal').modal('hide');
		  		
		  		/* $('#commet').val("");
		  		$('#fileupload').val("");
		  		
		  		 $('#formModal').modal('toggle'); */
		  		}
		  		else
		  			{
		  			console.log("Backend error");
		  			}
		  	
		    },
		    error: function (e) {
	            console.log(e.responseText);
	        }
				    
		});
	
}
	

/* function editTotalSales(id) 
{
	$("#id").val(serviceUnitArray[id].id);
	$("#modelnumber").val(serviceUnitArray[id].modelnumber);
	$("#email").val(serviceUnitArray[id].email);
	$("#mobileno").val(serviceUnitArray[id].mobileno);
	$("#imgfiles").val(serviceUnitArray[id].imgfiles);
	$("#location").val(serviceUnitArray[id].location);
	$("#address").val(serviceUnitArray[id].address);
	$("#reqdesc").val(serviceUnitArray[id].reqdesc);
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
}*/

function deleteTotalSales(id,qstatus)
{
	var checkstr=null;
	if(qstatus == 0){
		 checkstr = confirm('Are you sure you want to Deactivate?');
	}else{
		 checkstr = confirm('Are you sure you want to Activate?');
	}
	if(checkstr == true){
		var formData = new FormData();
	    formData.append('id', id);
	    formData.append('qstatus', qstatus);
		$.fn.makeMultipartRequest('POST', 'deleteTotalSales', false, formData, false, 'text', function(data){
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

function inactiveData() 
{
	var qstatus="0";
	if($('#inActive').is(":checked") == true){
		qstatus="0";
	}else{
		qstatus="1";
	}
		var formData = new FormData();
		formData.append('qstatus', qstatus);
		
		$.fn.makeMultipartRequest('POST', 'inTotalSales', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			console.log(jsonobj.allOrders1);
				});
		
}

/*

var i =1;
function addNewTextBox()
{
	 var  dvalue =  $("#name"+i).val().trim();
	 if((dvalue == undefined) || (dvalue==''))
	 {
		 return false;
		 
	 }
	 else
		 {
			i=i+1;
	var row ="<div><input type='text' name='vlink' id='name"+i+"' class='form-control validate' placeholder='Enter Videos links'/></div>";
	$("#dtext").append(row);
	
		 }
	
	
	}
	
 */	

/* document.getElementById("file1").onchange = function () {
    var reader = new FileReader();
    
    for(var i=0; i<=this.files.length; i++)
    {
    
    if(this.files[i].size>500){
        alert("Image Size should not be greater than 500Kb");
        $("#file1").attr("src","blank");
       // $("#file1").hide();  
        $('#file1').wrap('<form>').closest('form').get(0).reset();
        $('#file1').unwrap();     
        return false;
    }
    if(this.files[i].type.indexOf("image")==-1){
        alert("Invalid Type");
        $("#file1").attr("src","blank");
        //$("#file1").hide();  
       $('#file1').wrap('<form>').closest('form').get(0).reset();
      //  $('#file1').unwrap();         
        return false;
    }   
    reader.onload = function (e) {
        // get loaded data and render thumbnail.
        document.getElementById("menu_image").src = e.target.result;
        $("#file1").show(); 
    };

    // read the image file as a data URL.
    reader.readAsDataURL(this.files[0]);
    }
}; */


function viewTask(id){
	var formData = new FormData();
    formData.append('id', id);
	$.fn.makeMultipartRequest('POST', 'viewTask2', false, formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		var alldata = jsonobj.allOrders1;
		$('#HtableId2').html('');
		var tableHead = '<table id="example2" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Date Modified</th><th>Sent Quotation</th><th>Notes</th></tr></thead><tbody></tbody></table>';
	$('#HtableId2').html(tableHead);
	$.each(alldata,function(i, orderObj) {
		if(orderObj.filename==undefined) orderObj.filename='';
		else
			{
				var list=orderObj.filename.split('*');
				var filename='';
				for(var i=0;i<list.length;i++)
				{
					filename=filename+'<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
				}
				orderObj.filename=filename;
			}
		var tblRow = "<tr>"
			/*  + "<td title='"+orderObj.id+"'>"+ orderObj.id + "</td>" */ 
			/* + "<td title='"+orderObj.quationid+"'>"+ orderObj.quationid + "</td>" */
			+ "<td title='"+orderObj.created_time+"'>"+ orderObj.created_time + "</td>" 
			+ "<td title='"+orderObj.filename+"'>"+ orderObj.filename + "</td>"
			+ "<td title='"+orderObj.notes+"'>"+ orderObj.notes + "</td>"
			+ "</tr>";
		$(tblRow).appendTo("#HtableId2 table tbody");
		
	});
		$("#myModal2").modal();
	});
}


$("#pageName").text("Quotation Requests");
$(".allsalesrequest").addClass("active"); 
</script>