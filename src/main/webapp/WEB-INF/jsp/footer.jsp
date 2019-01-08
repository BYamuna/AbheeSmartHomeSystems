
<%
	String baseurl2 =  request.getScheme() + "://" + request.getServerName() +      ":" +   request.getServerPort() +  request.getContextPath();
	session.setAttribute("baseurl", baseurl2);
	String session_notification = (String)session.getAttribute("notifications");
	String quotation_notification = (String)session.getAttribute("quotationType");
%>

<!-- Footer Starts Here -->
		 </div>
	</div> 
    <footer role="contentinfo">
        <div class="clearfix">
            <ul class="list-unstyled list-inline pull-left">
                <li>CHARVIKENT.COM <span id="getting-started"></span></li>
            </ul>
            <button class="pull-right btn btn-inverse-alt btn-xs hidden-print" id="back-to-top"><i class="fa fa-arrow-up" style="
    color:  black;"></i></button>
        </div>
    </footer>
    
   
    
</div> 
</div>
</div>
</body>
 
<%-- <script type='text/javascript' src='${baseurl }/assets/js/jqueryui-1.10.3.min.js'></script>  --%>



<script type='text/javascript' src='${baseurl }/assets/js/bootstrap.min.js'></script>  
<script type='text/javascript' src='${baseurl }/assets/js/enquire.js'></script>  
<script type='text/javascript' src='${baseurl }/assets/js/jquery.cookie.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/js/jquery.nicescroll.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/codeprettifier/prettify.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/easypiechart/jquery.easypiechart.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/sparklines/jquery.sparklines.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/form-toggle/toggle.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/datatables/jquery.dataTables.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/datatables/dataTables.bootstrap.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/demo/demo-datatables.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/js/placeholdr.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/js/application.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/demo/demo.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/charts-morrisjs/raphael.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/charts-morrisjs/morris.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/demo/demo-morrisjs.js'></script> 

<script type='text/javascript' src='${baseurl }/js/customValidation.js'></script> 
<script type='text/javascript' src="${baseurl }/js/jquery.blockUI.min.js" ></script>
<script type='text/javascript' src="${baseurl }/js/select2.min.js" ></script>


	
<script type="text/javascript">
var isClick = 'Yes';
var test = <%= session_notification %>;
var quotation = <%= quotation_notification %>;

 //if(test != null){
	var msgIncrement = 0;
	$("#taskTableHeader tbody").empty();
	$.each(test,function(i, orderObj) { 
		 if(orderObj.webstatus == 1){
			msgIncrement++; 
		var task = "<tr>"
			 + "<td title='"+orderObj.taskno+"'><a href=viewTicket?id="+ orderObj.taskno+"&pgn=0>"+ orderObj.taskno+"</a></td>" 
			+ "<td title='"+orderObj.kstatus+"'>"+ orderObj.kstatus + "</td>"
			+ "<td title='"+orderObj.requestType+"'><b>"+ orderObj.requestType + "</b></td>"
			+ "</tr>";
		
		/* $(task).appendTo("#taskTableHeader table tbody"); */
		
		 $("#taskTableHeader tbody").append(task);
		 }
	});
	 
	//}
 
 if(quotation != null){
		var msgIncrement = 0;
		$.each(quotation,function(i, orderObj) { 
			 if(orderObj.webstatus == 1){
				msgIncrement++; 
			var task1 = "<tr>"
				+ "<td title='"+orderObj.salesrequestnumber+"'><a href=viewQuotation?id='"+ orderObj.id+"'>"+ orderObj.salesrequestnumber+"</a></td>"
				+ "<td title='"+orderObj.kstatus+"'>New</td>"
				+ "<td title='"+orderObj.requestType+"'><b>"+ orderObj.requestType + "</b></td>"
				+ "</tr>";
			
			
			 $("#taskTableHeader tbody").append(task1);
			 }
		});
		 
		}
		$("#noOfMessages").text(msgIncrement); 
	</script>

	<script type='text/javascript' src='${baseurl }/js/ajax.js'></script>

 
</script>
</body>
</html>