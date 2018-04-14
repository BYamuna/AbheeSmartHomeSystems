<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@include file="abheeheader.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src='//static.codepen.io/assets/editor/live/console_runner-ce3034e6bde3912cc25f83cccb7caa2b0f976196f2f2d52303a462c826d54a73.js'>
</script><script src='//static.codepen.io/assets/editor/live/css_live_reload_init-890dc39bb89183d4642d58b1ae5376a0193342f9aed88ea04330dc14c8d52f55.js'></script>
<meta charset='UTF-8'><meta name="robots" content="noindex">
<link rel="shortcut icon" type="image/x-icon" href="//static.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" />
<link rel="mask-icon" type="" href="//static.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111" />
<link rel="canonical" href="https://codepen.io/jonvadillo/pen/NNZzwB" />	
<div class="container">	
<!-- Breadcrumb Starts here -->
        <nav aria-label="breadcrumb">
  			<ol class="breadcrumb">
    			<li class="breadcrumb-item"><a href="/">Home</a></li>
    			<li class="breadcrumb-item active" aria-current="page" id="modelName"></li>
    			<li class="breadcrumb-item active" aria-current="page" id="SendQuotation">Send Quotation</li>
    			</ol>
		</nav>
        <!-- Breadcrumb Ends here -->
        	
							<form:form modelAttribute="salesRequest" action="salesRequest" class="form-horizontal" method="Post" enctype="multipart/form-data">
	                  <form:hidden path="id"/>
					<div class="col-md-6"><br>
					<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Product Model<span class="impColor"> *</span></label>
									<div class="col-md-6">
										<form:input path="modelnumber" id="modelnumber"  class="form-control validate" placeholder="Enter Product Model"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Email<span class="impColor"> *</span></label>
									<div class="col-md-6">
										<form:input path="email" class="form-control validate" placeholder="Enter Email"/>
									</div>
								</div>
								<%-- <div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Mobile<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="modelnumber" class="form-control validate" placeholder="Enter Username"/>
									</div>
								</div> --%>
								<div class="clearfix"></div>
								<div class="form-group" id="passwordDiv">
									<label class="col-md-3 control-label no-padding-right">location<span class="impColor"> *</span></label>
									<div class="col-md-6">
<!-- 										<input type="text" id="us2-address" class="form-control validate"/> -->
										<input type="text" name="locationData" id="locationData" class="form-control"/> 
									</div>
								</div><div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Mobile<span class="impColor"> *</span></label>
									<div class="col-md-6">
										<form:input path="mobileno" class="form-control validate numericOnly" maxlength="10"  placeholder="Enter Mobile Number"/>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Address</label>
									<div class="col-md-6">
										<form:textarea path="address" class="form-control validate emailOnly" placeholder="Enter Address"/>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Requirements Description</label>
									<div class="col-md-6">
										<form:textarea path="reqdesc" class="form-control validate emailOnly" placeholder="Enter Description"/>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
								<label class="col-md-3 control-label no-padding-right">Choose File<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input class="form-control" type="file" name="imgfile" id="imgfile" multiple/>
									</div>
								</div>
								
								<div class="clearfix"></div>
									<div align="center" class="but">							
					      				<input type="submit" id="submit1" value="Submit" class="btn-primary btn" onclick="submitRequest()"/>
					      				<input type="reset" value="Reset" class="btn-danger btn cancel"/>
					      			</div>
								</div>
								
								<div class="col-md-6">
								<div id="us2" style="width: 500px; height: 400px;"></div>	
								</div>
					</form:form>
								
		</div>							
					

<%@include file="abheefooter.jsp" %>
<script src='//static.codepen.io/assets/editor/live/console_runner-ce3034e6bde3912cc25f83cccb7caa2b0f976196f2f2d52303a462c826d54a73.js'></script>
<script src='//static.codepen.io/assets/editor/live/css_live_reload_init-890dc39bb89183d4642d58b1ae5376a0193342f9aed88ea04330dc14c8d52f55.js'></script><meta charset='UTF-8'><meta name="robots" content="noindex"><link rel="shortcut icon" type="image/x-icon" href="//static.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" /><link rel="mask-icon" type="" href="//static.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111" /><link rel="canonical" href="https://codepen.io/jonvadillo/pen/NNZzwB" />


<!-- AIzaSyBof-nUbLfnD7fyKZ2DvfLXwWX-RPgdU8c -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAi3pzKXd0y6FTkbdOOMFuhmp1E4DFicxo&libraries=places&sensor=false"></script>  
<script src='//static.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>
 <script src='https://maps.google.com/maps/api/js?sensor=false&libraries=places'></script>
<!-- <script src='https://cdn.rawgit.com/Logicify/jquery-locationpicker-plugin/master/dist/locationpicker.jquery.min.js'></script> -->
<script type="text/javascript" src="https://rawgit.com/Logicify/jquery-locationpicker-plugin/master/dist/locationpicker.jquery.js"></script>
<script >//Plugin used: https://github.com/Logicify/jquery-locationpicker-plugin

$('#modelName').text(localStorage.getItem("modelName"));
$('#modelnumber').val(localStorage.getItem("modelName"));

document.getElementById('modelnumber').readOnly=true;

$('#us2').locationpicker({
	location: {
        latitude: 13.576848329332353,
        longitude: 78.41736346531445
    },
enableAutocomplete: true,
    enableReverseGeocode: true,
  radius: 0,
  inputBinding: {
    latitudeInput: $('#us2-lat'),
    longitudeInput: $('#us2-lon'),
    radiusInput: $('#us2-radius'),
    locationNameInput: $('#us2-address')
  },
  onchanged: function (currentLocation, radius, isMarkerDropped) {
        var addressComponents = $(this).locationpicker('map').location.addressComponents;
      $("#locationData").val(currentLocation.latitude+'&'+currentLocation.longitude);
    updateControls(addressComponents); //Data
    }
});

function updateControls(addressComponents) {
  console.log(addressComponents);
}


function submitRequest()
{
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


/* var form = $('#completeData')[0];

var data = new FormData(form);
 */

			var modelnumber=$('#modelnumber').val();
			var email=$('#email').val();
			var mobileno=$('#mobileno').val();
	    	var locationData=$('#locationData').val();
	    	var address=$('#address').val();
	    	var reqdesc=$('#reqdesc').val();
	    	
	    	 //Serializing all For Input Values (not files!) in an Array Collection so that we can iterate this collection later.
	    	//var params = form.serializeArray();
	    	
	    	
	    	var salesRequest = {	"modelnumber":modelnumber,	"email":email,"mobileno":mobileno,"address":address,"reqdesc":reqdesc};
	    	var salesreq= JSON.stringify(salesRequest);
		   
		   var formData = new FormData();
		   
		  /*  formData.append('modelnumber',productmodel);
		   formData.append('email',email);
		   formData.append('mobileno',mobileno);
		 //formData.append('locationData',locationData);
		   formData.append('address',address);
		   formData.append('reqdesc',reqdesc);  */
		   
		   
		   
    	var ins = document.getElementById('imgfile').files.length;
    	
    	for(var i=0; i< ins; i++)
    	{	
    	var portfolio_values = document.getElementById('imgfile').files[i];
		formData.append('imgfile', portfolio_values);
		} 
    	
    	
    	/* //Now Looping the parameters for all form input fields and assigning them as Name Value pairs. 
        $(params).each(function (index, element) {
            formData.append(element.name, element.value);
        }); */
    	console.log(formData);
 		$.ajax({
			type:"POST",
			//enctype: 'multipart/form-data',
		  	url: "salesRequest", 
		  	data:{ "formData": formData, "salesRequest": salesreq },
			//contentType: false,  // tell jQuery not to set contentType
		  	
		  	success: function(result){
		  		if(result !="" && result != null){
		  		alert(result)
		  		}
		  		$('#salesrequest').val("");
		  		$('#imgfile').val("");
		  		 $('#salesrequest-info').modal('toggle');
		  	
		    },
		    error: function (e) {
	            console.log(e.responseText);
	        }
				    
		});
	
}
//$("#pageName").text("-------");
 </script>
 </html>
 