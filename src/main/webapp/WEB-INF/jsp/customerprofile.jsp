<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
    
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/datatables/dataTables.css' /> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.fancy-select {
  position: absolute;
  left: 5000px;
}

.newSelect {
  position: relative;
  display: block;
  max-width: 300px;
  margin: 100px auto;
}
.newSelect:hover {
  height: auto;
}
.newSelect .selectedOption {
  background: white;
  padding: 20px;
  font-size: 18px;
  line-height: 18px;
  height: 58px;
  border: 1px solid #a9cdcf;
  color: #a9cdcf;
  cursor: pointer;
  position: relative;
  border-radius: 3px 3px 0 0;
}
.newSelect .selectedOption:after {
  content: '\f0dc';
  position: absolute;
  right: 20px;
  color: #af0606;
}
.newSelect .selectedOption.selected {
  color: #134b4e;
}
.newSelect .newOptions {
  position: absolute;
  width: 100%;
}
.newSelect .newOptions .newOption {
  display: none;
  top: 0;
  left: 0;
  font-size: 18px;
  line-height: 18px;
  height: 58px;
  padding: 20px;
  background: #ffe100;
  color: white;
  cursor: pointer;
}
.newSelect .newOptions .newOption:hover {
  background: #f80046;
}
.newSelect.clicked .newOption {
  display: block;
}
.newSelect.closed .newOption {
  display: none;
}

.panel-danger>.panel-heading
{
    background-color: #2f4cb5 !important;
    color: white !important;
}

.arrow_box {
    position: relative;
    background: none;
}
.arrow_box:hover {
    background: #262672;
}
.arrow_box:after {
    left: 100%;
    top: 50%;
    border: none;
    content: " ";
    height: 0;
    width: 0;
    position: absolute;
    pointer-events: none;
    border-color: #fff;
    border-left-color: #fff;
    border-width: 26px;
    margin-top: -26px;
    transition: all 0.3s ease 0s;
}
.arrow_box:hover:after,
.arrow_box.active:after {
    left: 100%;
    top: 50%;
    border: none;
    content: " ";
    height: 0;
    width: 0;
    position: absolute;
    pointer-events: none;
    border-color: transparent;
    border-left-color: #C21010;
    border-width: 0;
    margin-top: -26px;
}
.arrow_box:hover span,
.arrow_box.active span {
    height: 8px;
    width: 8px;
    line-height: 8px;
    border-radius: 50%;
    background: #fff;
    display: block;
    position: absolute;
    right: 0;
    top: 25px;
}
.list-items {
    border-bottom: none !important; width: 86%;
}
.list-items li {
    list-style: none;
    text-align: right;
    display: block;
    border-bottom: 1px solid #e7e7e7;
    position: relative;
    width: 100%;
    border-left: none !important; transition: all 0.4s ease 0s;
    border-radius: 3px;
}
.list-items li a {
	font-size:12px;
    color: #000;
    margin: 0;
    display: block;
    padding: 13px 25px 14px 0px;
    transition: none;
    border: 1px solid #e7e7e7 !important; border-left: none !important; transition: all 0.4s ease 0s;
}
.list-items li a:hover {
	font-size:12px;
    color: #fff;
    text-decoration: none;
    border-right: none;
    border-left: none !important;
}
.list-items li.active a {
	font-size:12px;
    background: #013950 !important;
    color: #fff;
    border: none;
    border-right-color: #013950 !important; border: 1px solid #013950 !important;
}
.nav.list-items > li > a:focus,
.nav.list-items > li > a:hover {
    background: #166eaf;
    color: #FFF;
    border: none;
    padding: 13px 25px 14px 0px;
    border-right-color: #013950 !important; outline-color: #013950 !important;
}
.nav-tabs > li > a {
    border-radius: 0;
    border: none;
}
.ser-list {
    padding: 100px 0;
}
.tab-data-info h5 {
    font-size: 24px;
    font-weight: 700;
    color: #C21010;
    font-weight: 700;
    margin: 35px 0 15px;
}
.tab-data-info p {
    font-size: 14px;
    font-weight: 400;
    color: #000;
    width: 78%;
}
.tab-data-info p {
    font-size: 16px ;
    font-weight: 300 ;
    color: #000 ;
    width: 100% !important;
}
.ulimagecss{
list-style-image:url(images/Right-pointer.png);
}
.col-md-6 h3 {
color:#ea8080 !important;
}
.close {
color:#000 !important;
}
body {font-family: Arial;}

/* Style the tab */
.tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 9px 16px;
    transition: 0.3s;
    font-size: 15px;
}

/* Change background color of buttons on hover */
.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    display: none;
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}
</style>

    <%@include file="abheeheader.jsp" %>
    
    <div class="container">
      <div>
        <div class="">
          <div class="row">
            <div class="col-sm-2">
              <ul class="nav nav-tabs list-items">
                <li class="arrow_box active"><a data-toggle="tab" href="#1_1">Personal Information</a> </li>
                <li class="arrow_box"><a data-toggle="tab" href="#1_2">Email</a> </li>
                <li class="arrow_box"><a data-toggle="tab" href="#1_3">Mobile Number</a> </li>
                <li class="arrow_box"><a data-toggle="tab" href="#1_4">Change Password</a> </li>
                <li class="arrow_box"><a data-toggle="tab" href="#1_5">My Orders</a> </li>
                <li class="arrow_box"><a data-toggle="tab" href="#1_6">Ticket Status</a> </li>
                <%-- <li class="ticketstatus"><a href="${baseurl }/ticketstatus">Ticket Status</a></li> --%>
                
              </ul>
            </div>
            <div class="col-sm-10">
              <!-- Tab panes -->
              <div class="tab-content">
                <div class="tab-pane fade active in" id="1_1">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="pinfo">
                  		<div class="col-md-6">
                  			<h3>Personal Information</h3>
                  		</div>
                  		<div class="col-md-6">
                  			<h4 style="float:right; margin-top:20px;" id="editpersnolinfo"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
                  			<h4 style="float:right; margin-top:20px;" id="savepersnolinfo"><a href="#"><i class="glyphicon glyphicon-save"></i> Save</a></h4>
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="edata">
                  		<div class="col-sm-6">
                  			<div class="col-md-3">
                  				<label style="margin-top:18px;">First Name: </label>
                  			</div>
                  			<div class="col-md-5">
                  				<input class="form-control onlyCharacters validate" id="firstname" type="text" placeholder="First Name">
                  			</div>
                  			<div class="4">
                  			</div><div class="clearfix"></div>
                  		</div>
                  		<div class="col-sm-6">
                  			<div class="col-md-3">
                  				<label style="margin-top:18px;  margin-left:5px;">Last Name: </label>
                  			</div>
                  			<div class="col-md-5">
                  				<input class="form-control onlyCharacters validate" type="text" id="lastname"  placeholder="Last Name" >
                  			</div>
                  			<div class="4">
                  			</div><div class="clearfix"></div>
                  		</div><div class="clearfix"></div>
                  		<div class="ainfo col-md-6">
                  			<div class="col-md-3">
                  				<label style="margin-top:25px;">Address: </label>
                  			</div>
                  			<div class="col-md-5">
                  				<textarea  class="form-control validate" id="address" type="text" placeholder="Address"></textarea>
                  			</div><div class="clearfix"></div>
                  		</div>
                  	</div>
                  </div>
                </div>
                <div class="tab-pane fade" id="1_2">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="cinfo">
                  		<div class="col-md-6">
                  			<h3>Email Information</h3>
                  		</div>
                  		<div class="col-md-6">
                  			<h4 style="float:right; margin-top:20px;" id="editemailinfo"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
                  			<h4 style="float:right; margin-top:20px;" id="saveemailinfo"><a href="#"><i class="glyphicon glyphicon-save"></i> Save</a></h4>
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="cdata">
                  		<div class="col-sm-6">
                  			<div class="col-md-2">
                  				<label style="margin-top:18px;">Email : </label>
                  			</div>
                  			<div class="col-md-6">
                  				<input style="float:left;" id="pemail" class="form-control emailOnly validate" type="text"  placeholder="Email" disabled="true">
                  			</div>
                  			<div class="col-md-4">
                  			</div><div class="clearfix"></div>
                  		</div>
                  		<div class="col-md-6">
                  		</div><div class="clearfix"></div>
                  	</div>
                  </div>
                </div>
                <div class="tab-pane fade" id="1_3">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="cinfo">
                  		<div class="col-md-6">
                  			<h3>Mobile Number</h3>
                  		</div>
                  		<div class="col-md-6">
                  			<h4 style="float:right; margin-top:20px;" id="editmobileno"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
                  			<h4 style="float:right; margin-top:20px;" id="savemobileno"><a href="#"><i class="glyphicon glyphicon-save"></i> Save</a></h4>
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="cdata">
                  		<div class="col-sm-6">
                  			<div class="col-md-3">
                  				<label style="margin-top:18px; margin-left:5px;">Mobile Number : </label>
                  			</div>
                  			<div class="col-md-9">
                  				<input class="form-control numericOnly2 validate " maxlength="10" type="text"  id="pmobilenumber" placeholder="Mobile Number" disabled="true">
                  				<input class="form-control" type="hidden"  id="customerid" placeholder="Mobile Number">
                  				<input class="form-control" type="hidden"  id="checkpass">
                  			</div>
                  			<div class="col-md-4">
                  			</div><div class="clearfix"></div>
                  		</div>
                  		<div class="col-md-6">
                  		</div><div class="clearfix"></div>
                  	</div>
                  </div>
                </div>
                <div class="tab-pane fade" id="1_4">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="ainfo">
                  		<div class="col-md-6">
                  			<h3>Change Password</h3>
                  		</div>
                  		<div class="col-md-6">
                  			<h4 style="float:right; margin-top:20px;" id="editProfilePassword"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
                  			<h4 style="float:right; margin-top:20px;" id="saveProfilePassword"><a href="#"><i class="glyphicon glyphicon-save"></i> Save</a></h4>
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="pdata">
                  		<div class="col-sm-6">
                  			<div class="col-md-4">
                  				<label style="margin-top:-5px;">Current Password: </label>
                  			</div>
                  			<div class="col-md-8">
                  				<input style="float:left;" id="pcurrentpassword" class="form-control" maxlength="4" type="password" placeholder="****" disabled="true">
                  			</div><div class="clearfix"></div>
                  		</div>
                  		<div class="col-sm-6">
                  		</div><div class="clearfix"></div>
                  		<div class="col-sm-6">
                  			<div class="col-md-4">
                  				<label style="margin-top:-5px;">New Password: </label>
                  			</div>
                  			<div class="col-md-8">
                  				<input style="float:left;" id="pnewpassword" class="form-control" maxlength="4" type="password" placeholder="****" disabled="true">
                  			</div><div class="clearfix"></div>
                  		</div>
                  		<div class="col-sm-6">
                  		</div><div class="clearfix"></div>
                  		<div class="col-sm-6">
                  			<div class="col-md-4">
                  				<label style="margin-top:-5px;">Confirm Password: </label>
                  			</div>
                  			<div class="col-md-8">
                  				<input style="float:left;" id="pconfirmpassword" class="form-control" maxlength="4" type="password" placeholder="****" disabled="true">
                  			</div><div class="clearfix"></div>
                  		</div>
                  		<div class="col-sm-6">
                  		</div><div class="clearfix"></div>
                  	</div>
                  </div>
                </div>
                <div class="tab-pane fade" id="1_5">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="oinfo">
                  		<div class="col-md-6">
                  			<h3>My Orders</h3>
                  		</div>
                  		<div class="col-md-6">
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="odata">
    					<div class="table-responsive" id="customerOrderTable">
							<table class="table table-bordered priority prioritybg"	style="border: 1px solid #0460a4;" >
								<thead>
									<tr style="background-color: #0460a4; color: #fff; text-align: center; font-family:'Roboto'; font-size: 12px;">
										<th>OrderId</th>
										<th>Product(s)</th>
										<th>Date of Purchased</th>
										<th>Warranty Expired Date</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
                  	</div>
                  </div>
                </div>
                
                <div class="tab-pane fade" id="1_6">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="qsrinfo">
                  		<div class="col-md-6">
                  			<h3>Ticket Status</h3>
                  		</div>
                  		<div class="col-md-6">
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="tab">
						  <button class="tablinks" onclick="Quotationrequests(),openCity(event, 'customerQuotationTable')" id="defaultOpen">Quotation Requests</button>
						  <button class="tablinks" onclick="Servicerequest(),openCity(event, 'customerTaskTable')">Service Requests</button>
					</div><br/>
                  	<div class="qsrdata"> 
	                  	<!--<div class="col-md-3 pull-right">
	                  		  <input type="text" id = "search" name="search"  placeholder="search ..">
	                  	</div>  -->
                  		<div class="clearfix"></div>
	                  	<div id="customerQuotationTable" class="tabcontent">
						  			<table class="table table-bordered priority prioritybg "	style="border: 1px solid #0460a4;" >
									<thead>
										<tr style="background-color: #0460a4; color: #fff; text-align: center; font-family:'Roboto'; font-size: 12px;">
											<th>Quotation No.</th>
											<th>Product Model</th>
											<th>Address</th>
											<th>Description</th>
											<th>Quotation Images</th>
											<th>Mobile No. </th>
											<th>Quotation Date</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						<div id="customerTaskTable" class="tabcontent">
	  						<table class="table table-bordered priority prioritybg "	style="border: 1px solid #0460a4;" >
								<thead>
									<tr style="background-color: #0460a4; color: #fff; text-align: center; font-family:'Roboto'; font-size: 12px;">
										<th>Request No.</th>
										<th>Service Type</th>
										<th>Product Model</th>
										<th>Address</th>
										<th>Description</th>
										<th>Product Images</th>
										<th>Request Date</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>	
						</div>
					</div>
					</div>
                  	</div>
                  </div>
                </div> 
              </div>
              <br />
            </div>
          </div>
        </div>
		</div>
		</div>

	<div class="modal fade" id="OTPModel" data-backdrop="static" data-keyboard="false" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">OTP Verification</h4>
        </div>
        <div class="modal-body" style="padding:20px;">
          <form  action="#"  id="registration1"  method="post" class="login-form">
						<div id="firstForm1">
							<div class="form-group">
								<div class="col-md-3">
									<label for="user_name">OTP :</label>
								</div>
								<div class="col-md-9">
									<input	type="password" name="cotp" id="cotp" onkeydown="removeBorder(this.id)" maxlength="4" class="form-control numericOnly" placeholder="OTP"/>
								</div><div class="clearfix"></div> 
								<span class="hasError" id="emailError" style="font-size: 13px;"></span>
							</div>
						</div>
					</form>	
        </div>
        <div class="col-md-1"></div>
        <div class="col-sm-5" style="padding-top:15px;">
			<a onclick="resendOTP()" class="btn btn-warning">Resend OTP</a>
		</div>
        <div class="modal-footer">
          <button type="button" id="submit2" onclick="modelsubmit()" class="btn btn-primary" >Submit</button>
        </div>
      </div>
    </div>
  </div> 
  
  
  <div class="modal fade" id="PaymentModal" data-backdrop="static" data-keyboard="false"  role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background: #2973cf;    padding: 7px;">
				<button type="button" class="close" id="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="color: white;"> Payment Details </h4>
        	</div>
        	<div class="modal-body">
					<form class="form-horizontal"  method="post" >
					<div class="panel-body">
						<div class="col-md-6">
								<div class="form-group">
									<label style="margin-top:-7px;" for="focusedinput" class="col-md-6 control-label">Payment Mode <span class="impColor">*</span></label>
									<select id="paymentstatus"	class="col-xs-10 col-sm-5 "	onfocus="removeBorder(this.id)">
										 <option value="" >--- Select --- </option>
										 <c:forEach var="list" items="${paymentmode}">
												<option value=${list.key}>${list.value} </option>
										</c:forEach>  
									</select>
								</div>
							</div>	
							<div id="getting-started"></div>
					</div>	
                    <div class="panel-footer">
				      <div class="row">
							<div class="col-sm-12">
								<div class="btn-toolbar text-center">
									<input type="submit" id="submit3" value="Submit" onclick="" class="btn-primary btn" /> 
										<input type="reset" value="Reset"  class="btn-danger btn cancel" />
								</div>
							</div>
						</div>	
			      	</div>	
			      	</form>
					</div>
				</div>	
			</div>
		</div>
  
<script type='text/javascript' src='${baseurl }/assets/plugins/datatables/jquery.dataTables.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/datatables/dataTables.bootstrap.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/demo/demo-datatables.js'></script> 
<script type="text/javascript">
var isCheck=false;
$("#search").on("keyup", function() {
    var value = $(this).val().toUpperCase();

    $("table tr").each(function(index) {
        if (index !== 0) {

            $row = $(this);

            var id = $row.find("td").text();

            if (id.indexOf(value) !== 0) {
                $row.hide();
            }
            else {
                $row.show();
            }
        }
    });
});
 
         function Quotationrequests(){
        		$.ajax({
        			type : "POST",
        			url : "quotationrequests",
        			data : '',
        			dataType : "json",
        			beforeSend : function() {
        	            //$.blockUI({ message: 'Please wait' });
        	          }, 
        			success : function(data) {
        				console.log(data);
        				displayTable2(data);
        				isCheck=true;	
        			},
        			error:  function(e){
        				$.unblockUI();console.log(e);
        			}
        	});
 		} 
   
         function displayTable2(listOrders) {
     		$('#customerQuotationTable').html('');
     		var tableHead = '<table id="customerQuotationTable" class="table tablestriped table-bordered datatables">'
     				+ '<thead><tr style=" font-size:12px;background-color: #0460a4; color: #fff;"><th>Request No.</th><th>Product Model</th><th>Address</th><th>Description</th><th>Quotation Images</th><th>Mobile No.</th><th>Quotation Date</th></tr></thead><tbody></tbody></table>';
     		$('#customerQuotationTable').html(tableHead);
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
     				serviceUnitArray[orderObj.id] = orderObj;
     			var tblRow = "<tr>"
     				+ "<td title='"+orderObj.salesrequestnumber+"'><a href=viewQuotationDetails?id='"+ orderObj.id+"'>"+ orderObj.salesrequestnumber+"</a></td>"
     				+ "<td title='"+orderObj.modelname+"'>"+ orderObj.modelname + "</td>"
     				+ "<td title='"+orderObj.address+"'>"+ orderObj.address + "</td>" 
     				+ "<td title='"+orderObj.reqdesc+"'>"+ orderObj.reqdesc + "</td>" 
     				+ "<td title='"+orderObj.imgfiles+"'>"+ orderObj.imgfiles + "</td>" 
     				+ "<td title='"+orderObj.mobileno+"'>"+ orderObj.mobileno + "</td>"
     				+"<td title='"+orderObj.created_time+"'>"+ orderObj.created_time + "</td>"
     				+ "</tr>";
     			$(tblRow).appendTo("#customerQuotationTable table tbody");
     		});	
     		if(isCheck) $('#customerQuotationTable table').DataTable({});
     		$(".dataTables_filter input").attr('placeholder','Search...');
     	} 
       
     </script>


  			 
  	<script type="text/javascript">
         function Servicerequest(){
        		$.ajax({
        			type : "POST",
        			url : "servicerequests",
        			data : '',
        			dataType : "json",
        			beforeSend : function() {
        	            // $.blockUI({ message: 'Please wait' });
        	          }, 
        			success : function(data) {
        				console.log(data);
        				displayTable3(data);
        			},
        			error:  function(e){$.unblockUI();console.log(e);
        			}
        			
        	});
 		} 
         function displayTable3(listOrders) {
     		$('#customerTaskTable').html('');
     		var tableHead = '<table id="customerTaskTable" class="table tablestriped table-bordered datatables">'
     				+ '<thead><tr style=" font-size:12px;background-color: #0460a4; color: #fff;"><th>Request No.</th><th>Service Type</th><th>Product Model</th><th>Address</th><th>Description</th><th>Product Images</th><th>Invoice</th><th>Request Date</th></tr></thead><tbody></tbody></table>';
     		$('#customerTaskTable').html(tableHead);
     		serviceUnitArray = {};
     		
     		$.each(listOrders,function(i, orderObj) {
     				if(orderObj.uploadfile==undefined) orderObj.uploadfile='';
     				else
     					{
     						var list=orderObj.uploadfile.split('*');
     						var uploadfile='';
     						for(var i=0;i<list.length;i++)
     						{
     							uploadfile=uploadfile+'<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
     						}
     						orderObj.uploadfile=uploadfile;
     					}
         				if(orderObj.invimg==undefined) orderObj.invimg='';
         				else
         					{
         						var list=orderObj.invimg.split('*');
         						var invimg='';
         						for(var i=0;i<list.length;i++)
         						{
         							invimg=invimg+'<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
         						}
         						orderObj.invimg=invimg;
         					}	
     				/* var payment = "<a class='comment commentIt' onclick='paymentRequest("+ orderObj.id+ ")'>   <i class='fa fa-paypal'></i></a>" */
     				serviceUnitArray[orderObj.id] = orderObj;
     			var tblRow = "<tr>"
     				+ "<td title='"+orderObj.taskno+"'><a href=viewServiceResponse?id='"+ orderObj.taskno+"'>"+ orderObj.taskno+"</a></td>"
     				+ "<td title='"+orderObj.servicetypename+"'>"+ orderObj.servicetypename
     				+ "<td title='"+orderObj.modelname+"'>"+ orderObj.modelname
     				+ "<td title='"+orderObj.communicationaddress+"'>"+ orderObj.communicationaddress+ "</td>"
     				+ "<td title='"+orderObj.description+"'>"+ orderObj.description+ "</td>"
     				+ "<td title='"+orderObj.uploadfile+"'>"+ orderObj.uploadfile + "</td>" 
     				+ "<td title='"+orderObj.invimg+"'>"+ orderObj.invimg + "</td>" 
     				+"<td title='"+orderObj.created_time+"'>"+ orderObj.created_time + "</td>"
     				/* + "<td style='text-align: center;white-space: nowrap;'>"+ payment+ "</td>" */
     				+ "</tr>";
     			$(tblRow).appendTo("#customerTaskTable table tbody");
     		});
     		if(isCheck) $('#customerTaskTable table').DataTable({});
     		$(".dataTables_filter input").attr('placeholder','Search...');
     		
         }		
      </script> 
     <script>
		function openCity(evt, cityName)  {
		    var i, tabcontent, tablinks;
		    tabcontent = document.getElementsByClassName("tabcontent");
		    for (i = 0; i < tabcontent.length; i++) {
		        tabcontent[i].style.display = "none";
		    }
		    tablinks = document.getElementsByClassName("tablinks");
		    for (i = 0; i < tablinks.length; i++) {
		        tablinks[i].className = tablinks[i].className.replace(" active", "");
		    }
		    document.getElementById(cityName).style.display = "block";
		    evt.currentTarget.className += " active";
		}
		document.getElementById("defaultOpen").click();
	</script> 
<script type="text/javascript">
var validation = true;
var mobilevalidation=true;
var subValidation =false;

$( document ).ready(function() {
	
	$('#savepersnolinfo').hide();
	$('#saveProfilePassword').hide();
	$('#saveemailinfo').hide();
	$('#savemobileno').hide();
	
	$("#firstname").prop('disabled',true);
	$("#lastname").prop('disabled',true);
	$("#address").prop('disabled',true);
});

var customerProfile1 =${customerProfile1};
console.log(customerProfile1);
displayTable(customerProfile1);

function displayTable(listOrders) {
	$.each(listOrders,function(i, orderObj) {
		$("#firstname").val(orderObj.firstname);
		$("#lastname").val(orderObj.lastname);
		$("#pemail").val(orderObj.email);
		$("#address").val(orderObj.address);
		$("#pmobilenumber").val(orderObj.mobilenumber);
		$("#customerid").val(orderObj.id);
		$("#checkpass").val(orderObj.id);
	});
	}


var productWarrantyOrdersList = ${ordersList};
if (productWarrantyOrdersList != "") {
	displayTable4(productWarrantyOrdersList);
}
function displayTable4(listOrders) {
	$('#customerOrderTable').html('');
	var tableHead = '<table id="customerOrderTable" class="table table-striped table-bordered datatables">'
			+ '<thead><tr style=" font-size:12px;"><th>orderId</th><th>Products</th><th>Date of Purchased</th><th>Warranty Expired Date</th></tr></thead><tbody></tbody></table>';
	$('#customerOrderTable').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
	serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr>"
			+ "<td title='"+orderObj.order_id+"'>"+ orderObj.order_id + "</td>"
			+ "<td title='"+orderObj.product+"'>"+ orderObj.product + "</td>"
			+ "<td title='"+orderObj.purchaseddate+"'>"+ orderObj.purchaseddate + "</td>" 
			+ "<td title='"+orderObj.expireddate+"'>"+ orderObj.expireddate + "</td>" 		
			+ "</tr>";
		$(tblRow).appendTo("#customerOrderTable table tbody");
	});
}

$('#customer').blur(function() {
	var customer=$(this).val();

	$.ajax({
				type : "POST",
				url : "editCustomerProfile",
				data : {"customer":customer},
				dataType : "text",
				beforeSend : function() {
		             $.blockUI({ message: 'Please wait' });
		          }, 
				success : function(data) {
					if(data ==='true')
						{
						//alert("username already exists")
	 					$('#customer').css('border-color', 'red');
						 $('#submit1').prop('disabled', true);
						}
					else
						{
						$('#customer').css('border-color', 'none');
						$('#edit').prop('disabled', false);
						}
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});

		}); 
		$(".acc").addClass("active");
		
$('#editpersnolinfo').click(function (){
	
	
	$('#savepersnolinfo').show();
	$('#editpersnolinfo').hide();
	
	$("#firstname").prop('disabled',false);
	$("#lastname").prop('disabled',false);
	$("#address").prop('disabled',false);
	
});


$('#savepersnolinfo').click(function (){
var firstname=	$("#firstname").val();
var lastname =	$("#lastname").val();
var address =$("#address").val();
var pemail =$("#pemail").val();
var pmobilenumber=$("#pmobilenumber").val();
var customerid=$("#customerid").val();
	
	//alert(customerid);
	
	
	
	$.ajax({
		type : "POST",
		url : "editprofilecustomer",
		data :"firstname="+firstname+"&lastname="+lastname+"&address="+address+"&pemail="+pemail+"&pmobilenumber="+pmobilenumber+"&customerid="+customerid,
		dataType : "text",
		beforeSend : function() {
             $.blockUI({ message: 'Please wait' });
          }, 
		success : function(data) {
			//alert(data);
			
			if(data ==='true')
			{
				$("#firstname").prop('disabled',true);
				$("#lastname").prop('disabled',true);
				$("#address").prop('disabled',true);
				$("#pemail").prop('disabled',true);
				$("#pmobilenumber").prop('disabled',true);
				$('#savepersnolinfo').hide();
				$('#editpersnolinfo').show();
				alert(" Profile Updated Successfully ");
				window.location.reload();
			}
			else
				{
				alert(data);
				}
		},
		complete: function () {
            
            $.unblockUI();
       },
		error :  function(e){$.unblockUI();console.log(e);}
	});
});

$('#editProfilePassword').click(function (){
	$('#saveProfilePassword').show();
	$('#editProfilePassword').hide();
	$("#pcurrentpassword").prop('disabled',false);
	$("#pnewpassword").prop('disabled',false);
	$("#pconfirmpassword").prop('disabled',false);	
});
$('#saveProfilePassword').click(function (){
	var pconfirmpassword=$("#pconfirmpassword").val();
	var pcurrentpassword=$("#pcurrentpassword").val();
	var customerid=$("#customerid").val();
	//var checkpass=$("#checkpass").val();
	/* if(checkpass!=pconfirmpassword)
		{
		alert("Enter Valid Password");
		return false;
		} */
	var pnewpassword=$("#pnewpassword").val();
	if( pnewpassword !=pconfirmpassword)
		{
		alert("New Password and Confirm Password Not Matched");
		}
	else
		{
		$.ajax({
			type : "POST",
			url : "saveProfilePassword",
			data :"pconfirmpassword="+pconfirmpassword+"&customerid="+customerid+"&pcurrentpassword="+pcurrentpassword,
			dataType : "text",
			beforeSend : function() {
	             $.blockUI({ message: 'Please wait' });
	          }, 
			success : function(data) {
				
				if(data ==='true')
				{
					$("#firstname").prop('disabled',true);
					$("#lastname").prop('disabled',true);
					$("#address").prop('disabled',true);
					$("#pemail").prop('disabled',true);
					$("#pmobilenumber").prop('disabled',true);
					$("#pcurrentpassword").prop('disabled',true);
					$("#pnewpassword").prop('disabled',true);
					$("#pconfirmpassword").prop('disabled',true);
					$("#pcurrentpassword").val();
					$("#pnewpassword").val();
					$("#pconfirmpassword").val("");
					$('#saveProfilePassword').hide();
					$('#editProfilePassword').show();
					alert(" Profile Password  Updated Successfully ");
				}
				else
					{
					alert("You Entered Current Password is Wrong!");
					}
			},
			complete: function () {
	            
	            $.unblockUI();
	       },
			error :  function(e){$.unblockUI();console.log(e);}	
		});
		}
	});
$('#editemailinfo').click(function (){
	$('#saveemailinfo').show();
	$('#editemailinfo').hide();
	$("#pemail").prop('disabled',false);
});	
$('#saveemailinfo').click(function (){
	var pemail =$("#pemail").val();
	var customerid=$("#customerid").val();
	//var cotp=$("#cotp").val();
	//var booleanValue=$("#pemail").hasClass('default-class errorCls');
	if(!$("#pemail").hasClass('default-class errorCls')){
		//alert('not a valid email');
		$.ajax({
			type : "POST",
			url : "editprofileemail",
			data :"pemail="+pemail+"&customerid="+customerid,
			dataType : "text",
			beforeSend : function() {
	             $.blockUI({ message: 'Please wait' });
	          }, 
	          success : function(data) {
	  			if(data =='true')
	  			{     
	  				
					getOtp();
					$("#submit2").attr("onclick","modelsubmit2()");
					
					
				}
				else if(data == "")
				{
					alert("Email Updation failed");	
				}
				else
					{
					alert("Email already exists");
					}	
			},
			complete: function () {
	          $.unblockUI();
	     },
			error :  function(e){$.unblockUI();console.log(e);}
			});
		
	}
	else
		{
		event.preventDefault();
		}
	//var pmobilenumber =$("#pmobilenumber").val();
	//alert(customerid);
		
	});	

$('#editmobileno').click(function (){
	$('#savemobileno').show();
	$('#editmobileno').hide();
	$("#pmobilenumber").prop('disabled',false);
});	

$('#savemobileno').click(function (){
	$('#savemobileno').show();
	$('#editmobileno').hide();
	var pmobilenumber =$("#pmobilenumber").val();
	//var otp=$("#ctop").val();
	var customerid=$("#customerid").val();
	//alert(pmobilenumber);
	//alert(customerid);
	$.ajax({
		type : "POST",
		url : "editprofilemobileno",
		data :"pmobilenumber="+pmobilenumber+"&customerid="+customerid,
		dataType : "text",
		beforeSend : function() {
             $.blockUI({ message: 'Please wait' });
          }, 
          success : function(data) {
  			
  			if(data == "true")
  			{   
  				getOtp();
  				$("#submit2").attr("onclick","modelsubmit()");

  					
			}
  			else
  				{
  				alert("MobileNumber already exists");
  				}
			if(data == "")
			{
				alert("MobileNumber Updation Failed");
			}
		},
		complete: function () {
          
          $.unblockUI();
     },
		error :  function(e){$.unblockUI();console.log(e);}
		
	});
});	

function getOtp()
{
	var pmobilenumber =$('#pmobilenumber').val();
	$.ajax({
		type : "POST",
		url : "getEditOtp",
		data :"pmobilenumber="+pmobilenumber,
		dataType: 'text',
		 async: false,
		success : function(data) {
			if(data == 'true')
				{
				alert("OTP Sent to Your Mobile Number ");
				$('#OTPModel').modal('toggle');
				$("#OTPModel").modal('show');
				}
			else
				{
				$('#pmobilenumber').css('border-color', 'none');
				$('#submit1').prop('disabled', false);
				} 
			
		},
		complete: function () {
            
            $.unblockUI();
       },
		error :  function(e){$.unblockUI();console.log(e);}
		
	});
}

function modelsubmit()
{
	 pmobilenumber =$('#pmobilenumber').val();
	 cotp=$('#cotp').val();
	 customerid=$('#customerid').val();
	$.ajax({
		type : "POST",
		url : "modelSubmit1",
		data :"&pmobilenumber="+pmobilenumber+"&cotp="+cotp+"&customerid="+customerid,
		dataType : "text",
		beforeSend : function() {
             $.blockUI({ message: 'Please wait' });
          }, 
		success : function(data) {
			//alert(data);
			
			if(data =="true")
			{
				
				alert(" Otp Verified Successfully ");
				$('#OTPModel').modal('toggle');
				$("#pmobilenumber").prop('disabled',true);
				$('#savemobileno').hide();
				$('#editmobileno').show();
  				alert("Mobilenumber Updated Successfully ");
			}
			else
				alert("Enter valid OTP");
			
		},
		complete: function () {
            
            $.unblockUI();
       },
		error :  function(e){$.unblockUI();console.log(e);}
		
	});

}

/* function getOtp2()
{
	var pmobilenumber =$('#pmobilenumber').val();
	var pemail=$('#pemail').val();
	$.ajax({
		type : "POST",
		url : "getEditOtp",
		data :"pmobilenumber="+pmobilenumber,
		dataType: 'text',
		 async: false,
		success : function(data) {
			if(data == 'true')
				{
				alert("OTP Send to Your Mobile Number ");
				$('#OTPModel').modal('toggle');
				$("#OTPModel").modal('show');
				}
			else
				{
				$('#pemail').css('border-color', 'none');
				$('#submit1').prop('disabled', false);
				} 
			
		},
		complete: function () {
            
            $.unblockUI();
       },
		error :  function(e){$.unblockUI();console.log(e);}
		
	});
} */


function modelsubmit2()
{
	 pemail=$('#pemail').val();
	 cotp=$('#cotp').val();
	 customerid=$('#customerid').val();
	$.ajax({
		type : "POST",
		url : "modelSubmit2",
		data :"&pemail="+pemail+"&cotp="+cotp+"&customerid="+customerid,
		dataType : "text",
		beforeSend : function() {
             $.blockUI({ message: 'Please wait' });
          }, 
		success : function(data) {
			
			if(data =="true")
			{
				
				alert(" Otp Verified Successfully ");
				$('#OTPModel').modal('toggle');
				$("#pemail").prop('disabled',true);
				$('#saveemailinfo').hide();
				$('#editemailinfo').show();
				alert(" Email Updated Successfully "); 
			}
			else
				alert("Enter valid OTP");
			
		},
		complete: function () {
            
            $.unblockUI();
       },
		error :  function(e){$.unblockUI();console.log(e);}
		
	});

}

 

function resendOTP()
{
	var pmobilenumber =$('#pmobilenumber').val();	
	$.ajax({
		type : "POST",
		url : "resendOtpOnMobileEdit",
		data :"pmobilenumber="+pmobilenumber,
		dataType : "text",
		beforeSend : function() {
             $.blockUI({ message: 'Please wait' });
          }, 
		success : function(data) {
			if(data ==='true')
				{
				alert("OTP sent to your mobile number");
				}
			else
				{
				alert("OTP Limit Expired For Today");
				} 
		},
		complete: function () {
            
            $.unblockUI();
       },
		error :  function(e){$.unblockUI();console.log(e);}
		
	});
}

//var paymentDropDown = ${paymentmode};
	function paymentRequest()
	{
	
		$("#PaymentModal").modal('show');
		 /* var optionsForPayment = "";
		 optionsForPayment = $("#paymentstatus").empty();
		 optionsForPayment.append(new Option("-- Select --",	""));
			$.each(paymentDropDown, function(i, tests) {
				var productId = tests;
				var productName = tests;
				optionsForPayment.append(new Option(productId,	productName));
			}); */
	}
	
	

</script>
<%@include file="abheefooter.jsp" %>