<%@include file="abheeheader.jsp" %>
<style>
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

	<div class="container">
        <div class="ticketstatus">
        	<div class="tab">
        	<!-- <button class="tablinks" onclick="Quotationrequests()">Quotation Requests</button>
        	<button class="tablinks" onclick="Servicerequest()">Service Requests</button>  -->
        	  </div>
         </div> 
         <div class="tab">
  <button class="tablinks" onclick="Quotationrequests(),openCity(event, 'customerQuotationTable')" id="defaultOpen">Quotation Requests</button>
  <button class="tablinks" onclick="Servicerequest(),openCity(event, 'customerTaskTable')">Service Requests</button>
</div>

<div id="customerQuotationTable" class="tabcontent">
  <!-- <h3>London</h3>
  <p>London is the capital city of England.</p>  -->
  	
  
</div>

<div id="customerTaskTable" class="tabcontent">
 <!--  <h3>Paris</h3>
  <p>Paris is the capital of France.</p> --> 
</div>


        	<script type="text/javascript">
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
        				
        			},
        			
        			error:  function(e){$.unblockUI();console.log(e);
        			}

        	});
 } 
   
         function displayTable2(listOrders) {
     		$('#customerQuotationTable').html('');
     		var tableHead = '<table id="customerQuotationTable" class="table tablestriped table-bordered datatables">'
     				+ '<thead><tr style=" font-size:12px;background-color: #0460a4; color: #fff;"><th>Quotation No</th><th>Product Model</th><th>Address</th><th>Description</th><th>Quotation Images</th><th>Mobileno</th><th>Quotation Date</th></tr></thead><tbody></tbody></table>';
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
     				+ "<td title='"+orderObj.salesrequestnumber+"'><a href=viewResponse?id='"+ orderObj.id+"'>"+ orderObj.salesrequestnumber+"</a></td>"
     				+ "<td title='"+orderObj.modelname+"'>"+ orderObj.modelname + "</td>"
     				+ "<td title='"+orderObj.address+"'>"+ orderObj.address + "</td>" 
     				+ "<td title='"+orderObj.reqdesc+"'>"+ orderObj.reqdesc + "</td>" 
     				+ "<td title='"+orderObj.imgfiles+"'>"+ orderObj.imgfiles + "</td>" 
     				+ "<td title='"+orderObj.mobileno+"'>"+ orderObj.mobileno + "</td>"
     				+"<td title='"+orderObj.created_time+"'>"+ orderObj.created_time + "</td>"
     				+ "</tr>";
     			$(tblRow).appendTo("#customerQuotationTable table tbody");
     		});
     		
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
     				+ '<thead><tr style=" font-size:12px;background-color: #0460a4; color: #fff;"><th>RequestNo</th><th>ServiceType</th><th>Product Model</th><th>Address</th><th>Description</th><th>Product Images</th><th>Request Date</th></tr></thead><tbody></tbody></table>';
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
     				serviceUnitArray[orderObj.id] = orderObj;
     			var tblRow = "<tr>"
     				+ "<td title='"+orderObj.taskno+"'><a href=viewServiceResponse?id='"+ orderObj.taskno+"'>"+ orderObj.taskno+"</a></td>"
     				+ "<td title='"+orderObj.servicetypename+"'>"+ orderObj.servicetypename
     				+ "<td title='"+orderObj.modelname+"'>"+ orderObj.modelname
     				+ "<td title='"+orderObj.communicationaddress+"'>"+ orderObj.communicationaddress+ "</td>"
     				+ "<td title='"+orderObj.description+"'>"+ orderObj.description+ "</td>"
     				+ "<td title='"+orderObj.uploadfile+"'>"+ orderObj.uploadfile + "</td>" 
     				+"<td title='"+orderObj.created_time+"'>"+ orderObj.created_time + "</td>"
     				+ "</tr>";
     			$(tblRow).appendTo("#customerTaskTable table tbody");
     		});
     	}
         
         </script>
        	
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
		<div id="service" class="tabcontent">
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
					</div>	
			</div>		
	<script type="text/javascript">
$(".ticketstatus").addClass("active");
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
<%@include file="abheefooter.jsp" %>