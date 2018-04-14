<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.btn1 {
background-color:#337ab7 !important;
color: #fff !imoportant;
}
label, .form-control {
	margin-top:10px;
}
</style>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<div class="container">
        <div class="footer">
        	<div style="padding-left:15px;padding-right:15px;" class="col-md-3">
            	<h4 style="color:#ffffff">About</h4>
                <p>Abhee Smart Home Systems located in Guntur is a expert in Home Theaters , Professional Audio & Home Automation installation & services.</p>
                <iframe src="https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2FAbheesystems%2F&tabs=timelines&width=250&height=130&small_header=true&adapt_container_width=true&hide_cover=false&show_facepile=true&appId" width="295" height="130" style="border:none;overflow:hidden" scrolling="no" frameborder="0" allowtransparency="true"></iframe>
            </div>
        	<div style="padding-left:15px;padding-right:15px;" class="col-md-3">
            	<h4 style="color:#ffffff">Catalog</h4>
                <ul>
                	<li><a class="sme" href="#"><i class="fa fa-angle-right"></i> Home Theaters</a></li><br>
                	<li><a class="sme" href="#"><i class="fa fa-angle-right"></i> PA Audio</a></li><br>
                	<li><a class="sme" href="#"><i class="fa fa-angle-right"></i> Projectors</a></li><br>
                	<li><a class="sme" href="#"><i class="fa fa-angle-right"></i> Security Cameras</a></li><br>
                	<li><a class="sme" href="#"><i class="fa fa-angle-right"></i> Site Map</a></li><br>
                </ul>
            </div>
        	<div style="padding-left:15px;padding-right:15px;" class="col-md-6">
            	<h4 style="color:#ffffff">Contact</h4>
            	<div class="col-sm-6">
                	<p><i style="color:#0066FF;" class="fa fa-map-marker f_left"></i> Nightingale Hospital <br><span style="margin-left:10px;">Complex, Opp SBI ,</span><br><span style="margin-left:10px;">Lakshmipuram Main Road.</span><br><span style="margin-left:10px;">Guntur,522006</span></p>
                	<p><i style="color:#009933;" class="fa fa-phone f_left"></i> +91 92464 83744</p>
                	<p><i style="color:#FF0000;" class="fa fa-envelope-o f_left"></i> rajv238@gmail.com</p>
            	</div>
            	<div class="col-sm-6">
                	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3829.362418849931!2d80.45853331442157!3d16.30441598873604!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3a4a0ab186f4da17%3A0xbdc025ad688e3a3c!2sABHEE+SMART+HOME+SYSTEMS!5e0!3m2!1sen!2sin!4v1520499615976" 
width="250" height="200" frameborder="0" ></iframe>
            	</div><div class="clearfix"></div>
            </div><div class="clearfix"></div>
        </div>
        <div class="copy">
        	<p>&copy; 2018. All Rights Reserved.</p>
        </div>
	</div>
	
	<!--service Model start here to   -->
	 
<div class="modal fade" id="formModal" data-backdrop="static" data-keyboard="false" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="background: #166eaf;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="color: white;">Service Request Form </h4>
        	</div>
        	<div class="modal-body">
				<div class="panel-body">
					<form>
						<div class="col-sm-4">
							<label>Service Type</label>
						</div>
						<div class="col-sm-8">
						<select  id="servicetypeid"  class="form-control" >
											<c:forEach var="list" items="${servicetypes}">
											<option value=${list.key}>${list.value} </option>
											</c:forEach>
										</select>
						</div>
						<div class="col-sm-4">
							<label>Message</label>
						</div>
						<div class="col-sm-8">
							<textarea class="form-control" id="message" name="message" placeholder="Message"></textarea>
						</div>
							<!--<div class="col-sm-4">
							<label>Attach File(s)</label>
						</div>
					 <div class="col-sm-8">
							<input type="file" name="file1" id="file1" class="form-control" multiple="multiple"/>
						</div>
						 -->
						
					</form>
				</div>
            </div>
                    		
                    		
                    		<div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div style="float:right; margin-right:20px;" class="btn-toolbar text-center">
					      			<input type="button" id="modelSubmit" value="Submit"  onclick="serviceSubmit()" class="btn-primary btn"/>
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
	
	
</body>
<c:choose>
    <c:when test="${empty param.model}">
       <script>
        $("#productDetails").hide();
       </script>
    </c:when>
    <c:otherwise>
     <script>
	         $("#productModels").hide();
	         </script>
    </c:otherwise>
</c:choose>
 <c:choose>
    <c:when test="${not empty loggedstatus}">
     <script> var login=true;</script>
    </c:when>
    <c:otherwise>
        <script> var login=false;</script>
    </c:otherwise>
</c:choose>
<script type='text/javascript' src="${baseurl }/js/jquery.blockUI.min.js" ></script>
<script type='text/javascript' src='${baseurl }js/customValidation.js'></script> 
<script type="text/javascript">


// var categorieslist =${allOrders1};
$( document ).ready(function() {
	getlist();
});
	
	function getlist(){
			var rowdata =null;
	$.ajax({
		type : "POST",
		url : "getCategoryList",
		data :"message="+message+"&servicetypeid="+servicetypeid,
		dataType : "text",
		
		success : function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.list;
			$.each(alldata,function(i, orderObj) {
			rowdata ="<li><a href='abheecategory?id="+orderObj.id+" ' >"+orderObj.category+"</a> </li>";
			$("#cmlist").append(rowdata);
			});
		}
	});
	
	}
	
	
	
	
	
	
	
	
	
 
</script>
</html>
