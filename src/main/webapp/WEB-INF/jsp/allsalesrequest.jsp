<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

	<div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="dashBoard">Home</a></li>
		<li>All Sales Requests</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="background-color: white !important; padding-top: 15PX;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>All Sales Requests List</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
					<input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
						<div class="table-responsive" id="tableId">
							<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
								<thead><tr><th>ModelNumber</th><th>EmailId</th><th>Mobile No</th><th>files</th><th>Location</th><th>Address</th><th>Requirements description</th><th></th></tr></thead>
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
</body>
<%-- <script type='text/javascript' src='${baseurl }/js/custemValidation.js'></script>  --%>
<script>
</script>
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
	var tableHead = '<table id="product" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Request Number</th><th>Model Number</th><th>EmailId</th><th>Mobileno</th><th>Files</th><th>Location</th><th>Address</th><th>Reqirements Description</th><th style="text-align: center;">Options</th></tr></thead><tbody></tbody></table>';
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
			var deleterow = "<a class='deactivate' onclick='deleteTotalSales("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>"
		}else{  
			var deleterow = "<a class='activate' onclick='deleteTotalSales("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>"
		}
		var edit = "<a class='edit editIt' onclick='editTotalSales("	+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr>"
			+ "<td title='"+orderObj.salesrequestnumber+"'>"+ orderObj.salesrequestnumber + "</td>"
			+ "<td title='"+orderObj.modelnumber+"'>"+ orderObj.modelnumber + "</td>"
			+ "<td title='"+orderObj.email+"'>"+ orderObj.email + "</td>"
			+ "<td title='"+orderObj.mobileno+"'>"+ orderObj.mobileno + "</td>"
			+ "<td title='"+orderObj.imgfiles+"'>"+ orderObj.imgfiles + "</td>"
			+ "<td title='"+orderObj.location+"'>"+ orderObj.location + "</td>"
			+ "<td title='"+orderObj.address+"'>"+ orderObj.address + "</td>"
			+ "<td title='"+orderObj.reqdesc+"'>"+ orderObj.reqdesc + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	if(isClick=='Yes') $('.datatables').dataTable();
	
}


function editTotalSales(id) {
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
}

function deleteTotalSales(id,status)
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
	var status="0";
	if($('#inActive').is(":checked") == true){
		status="0";
	}else{
		status="1";
	}
		var formData = new FormData();
		formData.append('status', status);
		
		$.fn.makeMultipartRequest('POST', 'inTotalSales', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			console.log(jsonobj.allOrders1);
				});
		
}

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


$("#pageName").text("All Sales Requests");
$(".allsalesrequest").addClass("active"); 
</script>