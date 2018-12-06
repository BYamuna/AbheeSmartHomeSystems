<%@include file="abheeheader.jsp" %>
<style>
.tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}
.tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
    font-size: 17px;
}
.tab button:hover {
    background-color: #ddd;
}

.tab button.active {
    background-color: #ccc;
}
.tabcontent {
   /*  display: none; */
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}
.topright {
    float: right;
    cursor: pointer;
    font-size: 28px;
}
</style>
	<div class="container">
        <div class="ticketstatus">
        	<div class="tab">
        	<button class="tablinks" onclick="Quotationrequest()">test</button>
        	<script type="text/javascript">
        	function Quotationrequest()
        	{
        		$.ajax({
        	
        		type : "POST",
        		url : "quotationrequests",
        		data :'customerid='+catid,
        		dataType : "text",
        		beforeSend : function() {
                     $.blockUI({ message: 'Please wait' });
                  }, 
        		success : function(data) {
        			console.log(data);
        		},
        		complete: function () {
                    
                    $.unblockUI();
               }
        		//error :  function(e){$.unblockUI();console.log(e);});
        		});
        		
        		//displayTable2(salesRequestList);		
        	}
        	</script>
        	<button class="tablinks" onclick="Quotationrequest()">Quotation Requests</button>
  			<!-- <button class="tablinks" onclick="Servicerequest()">Service Requests</button> -->
        	</div>
          </div> 
          <div id="quotation" class="tabcontent">
  			<span onclick="this.parentElement.style.display='none'" class="topright">&times</span>
  			<div class="table-responsive" id="customerQuotationTable">
							<table class="table table-bordered priority prioritybg"	style="border: 1px solid #0460a4;" >
								<thead>
									<tr style="background-color: #0460a4; color: #fff; text-align: center; font-family:'Roboto'; font-size: 12px;">
										<th>Quotation No</th>
										<th>Product Model</th>
										<th>Address</th>
										<th>Warranty Expired Date</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
		</div>
		<!-- <div id="service" class="tabcontent">
  		<span onclick="this.parentElement.style.display='none'" class="topright">&times</span>
  			<div class="table-responsive" id="customerTaskTable">
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
		</div> -->
         </div>  
	<script>
	
	 /*var serviceRequestList = ${RequestsList};
	if (serviceRequestList != "") {
		displayTable2(serviceRequestList);
	}
	function displayTable3(listOrders) {
		$('#customerTaskTable').html('');
		var tableHead = '<table id="customerTaskTable" class="table tablestriped table-bordered datatables">'
				+ '<thead><tr style=" font-size:12px;"><th>RequestNo</th><th>ServiceType</th><th>Product Model</th><th>Address</th><th>Description</th><th>Product Images</th></tr></thead><tbody></tbody></table>';
		$('#customerTaskTable').html(tableHead);
		serviceUnitArray = {};
		serviceUnitArray[orderObj.id] = orderObj;
		$.each(listOrders,function(i, orderObj) {
				if(orderObj.uploadfile==undefined) orderObj.uploadfile='';
				else
					{
						var list=orderObj.uploadfile.split('*');
						var imgname='';
						for(var i=0;i<list.length;i++)
						{
							uploadfile=uploadfile+'<a href="../abheeimg/'+list[i]+'" target="_blank" title="'+list[i]+'"><img src="../abheeimg/'+list[i]+'" style="height:42px; width:42px"></a>';
						}
						orderObj.uploadfile=uploadfile;
					}
			var tblRow = "<tr>"
				+ "<td title='"+orderObj.taskno+"'>"+ orderObj.taskno + "</td>"
				+ "<td title='"+orderObj.servicetypename+"'>"+ orderObj.servicetypename
				+ "<td title='"+orderObj.communicationaddress+"'>"+ orderObj.communicationaddress+ "</td>"
				+ "<td title='"+orderObj.subject+"'>"+ orderObj.subject+ "</td>"
				+ "<td title='"+orderObj.uploadfile+"'>"+ orderObj.uploadfile + "</td>" 
				+ "</tr>";
			$(tblRow).appendTo("#customerTaskTable table tbody");
		});
		
	}*/
	var salesRequestList = "";
	window.onload(function(e){
		$.ajax({
			type : "POST",
			url : "quotationrequests",
			//data :'categoryid='+catid,
			dataType : "text",
			beforeSend : function() {
	             $.blockUI({ message: 'Please wait' });
	          }, 
			success : function(data) {
				displayTable2(data);
			},
			complete: function () {
	            
	            $.unblockUI();
	       },
			error :  function(e){$.unblockUI();console.log(e);}
			
	});
	
	//var salesRequestList = ${QuotationsList};
	if ( salesRequestList != "") {
		displayTable2(salesRequestList);
	} 
	function displayTable2(listOrders) {
		$('#customerQuotationTable').html('');
		var tableHead = '<table id="customerQuotationTable" class="table tablestriped table-bordered datatables">'
				+ '<thead><tr style=" font-size:12px;"><th>Quotation No</th><th>Product Model</th><th>Address</th><th>Description</th><th>Quotation Images</th><th>Mobileno</th></tr></thead><tbody></tbody></table>';
		$('#customerQuotationTable').html(tableHead);
		serviceUnitArray = {};
		serviceUnitArray[orderObj.id] = orderObj;
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
			var tblRow = "<tr>"
				+ "<td title='"+orderObj.salesrequestnumber+"'>"+ orderObj.salesrequestnumber + "</td>"
				+ "<td title='"+orderObj.modelname+"'>"+ orderObj.modelname + "</td>"
				+ "<td title='"+orderObj.address+"'>"+ orderObj.address + "</td>" 
				+ "<td title='"+orderObj.reqdesc+"'>"+ orderObj.reqdesc + "</td>" 
				+ "<td title='"+orderObj.imgfiles+"'>"+ orderObj.imgfiles + "</td>" 
				+ "</tr>";
			$(tblRow).appendTo("#customerQuotationTable table tbody");
		});
		
	}
	
}	
	
	
	

	/* function Quotationrequest()
	{$.ajax({
		type : "POST",
		url : "quotationrequests",
		//data :'categoryid='+catid,
		dataType : "text",
		beforeSend : function() {
             $.blockUI({ message: 'Please wait' });
          }, 
		success : function(data) {
			displayTable2(data);
		},
		complete: function () {
            
            $.unblockUI();
       },
		error :  function(e){$.unblockUI();console.log(e);}
		
		//displayTable2(salesRequestList);		
	} */
	/* function Servicerequest()
	{
		displayTable3(serviceRequestList);
	} */
	
		//$(".ticketstatus").addClass("active");
	</script>

<%@include file="abheefooter.jsp" %>