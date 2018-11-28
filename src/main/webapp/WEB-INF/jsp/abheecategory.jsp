<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@include file="abheeheader.jsp" %>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<title>Abhee Smart Homes</title>
<style>
.form-horizontal .control-label {
    padding-top: 2px;
    margin-bottom: 0;
    text-align: left;
}
.modal-header .close {
    margin-top: -2px;
    color: #fff;
    opacity: 1;
}
</style>
<script type="text/javascript">
	var isClick = 'No';
		window.setTimeout(function() {
		    $(".msgcss").fadeTo(500, 0).slideUp(500, function(){
		        $(this).remove(); 
		    });
		}, 5000);
		</script>
	<c:if test="${not empty msg}">
		<div class="msgcss row">
			<div class="col-sm-4 col-sm-offset-4">
				<div class="form-group">
					<div class="alert alert-success fadeIn animated">${msg}</div>
				</div>
			</div>
		</div>
	</c:if>
	

      <div class="container">
        <!-- Breadcrumb Starts here -->
        <nav aria-label="breadcrumb">
  			<ol class="breadcrumb">
    			<li class="breadcrumb-item"><a href="${baseurl }/">Home</a></li>
    			<li class="breadcrumb-item active"><a href="#">Products</a></li>
    			<a href="#"><li class="breadcrumb-item active" aria-current="page" id="breadcrumbname"></li></a>
    			<li class="breadcrumb-item active" aria-current="page" id="breadcrumbcompanyname"></li>
    			<li class="breadcrumb-item active" aria-current="page" id="breadcrumbmodelname"></li>
  			</ol>
		</nav>
        <!-- Breadcrumb Ends here -->
        
        <!-- Content Starts here -->
        <div class="cont">
        	<div class="col-md-3">
            	<div class="panel panel-primary">
    				<div style="font-size:18px;" class="panel-heading" id="panelheading"></div>
    				<div class="panel-body">
                    	<a id="catcom" class="catcom"></a><br>
                    </div>
  				</div>
        	</div>
        	<div class="col-md-9">
            	<div class="listdata">
            		<strong><span id="cathead"></span></strong>
            		<!-- <h2>YAMAHA Home Theaters</h2> -->
            		<div align="center" class="col-md-12 col-sm-4" id="productModels">
                    	
                	</div>
                	
                	</div>
            		
                	
                <div id="productDetails">	
                
            	<!-- <div class="dimg" >
            		<div class="col-sm-6" >
                    	<img width="100%" src="reportDocuments/1.jpg" class="img-responsive" alt="196" title="YHT-196"/>
                    	<div class="col-xs-6">
                        	<button style="width:95%;" class="btn btn-primary" type="button">Get Quotation</button>
                    	</div>
                    	<div class="col-xs-6">
                        	<button style="width:95%; margin-left:10px;" class="btn btn-warning" type="button">Get Service</button>
                        </div>
                    </div>
                </div>
                <div class="dtxt">
           		  <div class="col-sm-6">
                    	<p>
                        	5.1-channel powerful surround sound (100W × 5 channel + 100W subwoofer)<br>
							CINEMA DSP with 17 DSP programmes<br>
							HD Audio compatibility and good sound from stylish speakers<br>
							1080p-compatible HDMI (3 in/1 out)<br>
							HDMI with 3D and Audio Return Channel<br>
							SCENE buttons with direct power on<br>
							HDMI CEC for easy operation<br>
							Stylish, curvy speaker design looks fresh in any interior<br>
							Auto power down function with variable time setting.
                        </p>
                    </div>
                </div><div class="clearfix"></div>
                <div class="vid">
                	<div class="col-sm-4">
                    	<iframe width="270" height="200" src="https://www.youtube.com/embed/z2lM0G2opEM" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
                    </div>
                	<div class="col-sm-4">
                    	<iframe width="270" height="200" src="https://www.youtube.com/embed/SFGPK2w4SXw" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
                    </div>
                	<div class="col-sm-4">
                    	<iframe width="270" height="200" src="https://www.youtube.com/embed/N8of5Ees_dU" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
                    </div><div class="clearfix"></div>
                </div> -->
                </div>
            <div class="clearfix"></div>
                	
                </div><div class="clearfix"></div>
        	</div><div class="clearfix"></div>
        </div>
        <!-- Content Ends here --> 
      </div>  
        <div class="modal fade" id="formModal" data-backdrop="static" data-keyboard="false" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="background: #166eaf;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="color: white;">Service Request Form </h4>
        	</div>
        	<div style="max-width:90%; margin:0 auto;" class="modal-body">
        		<div align="center" class="text"><span class="impColor0"></span></div>
					<form  class="form-horizontal" method="Post" enctype="multipart/form-data">
						<div class="col-sm-4">
							<label>Request Type</label> <span class="impColor">*</span>
						</div>
						<div class="col-sm-8">
											<select  id="servicetypeid"  class="form-control" onfocus="removeBorder2(this.id)">
											<option value="">-- Request Type -- </option>
											<c:forEach var="list" items="${servicetypes}">
											<option value=${list.key}>${list.value} </option>
											</c:forEach>
										</select>
						</div>
						<div class="col-sm-4">
							<label>Description</label> <span class="impColor">*</span>
						</div>
						<div class="col-sm-8">
							<textarea class="form-control" id="message" name="message" placeholder="Description"  onfocus="removeBorder2(this.id)"></textarea>
						</div>
						
						<div class="col-sm-4">
							<label>Address</label> <span class="impColor">*</span>
						</div>
						<div class="col-sm-8">
							<textarea class="form-control" id="custaddress" name="custaddress" placeholder="Address"  onfocus="removeBorder2(this.id)"></textarea>
						</div>
						<div class="col-sm-4">
							<label>Warranty</label> <span class="impColor">*</span>
						</div>
						<div class="col-sm-8" style="padding-top:8px;">
							<input type="radio" id="warranty" name="warranty" value="1"> Yes
  							<input type="radio" id="warranty" name="warranty" value="0"> No
						</div><div class="clearfix"></div>
						<div class="col-sm-4">
							<label>Request Time</label> <span class="impColor">*</span>
						</div>
						<div class="col-sm-8">
											<select  id="requesttimeid"  class="form-control" onfocus="removeBorder2(this.id)">
											<option value="">-- Request Time -- </option>
											<c:forEach var="list" items="${requesttimes}">
											<option value=${list.key}>${list.value} </option>
											</c:forEach>
										</select>
						</div>
						<div class="col-sm-4" style="padding-top:8px;">
							<label>Attach File(s)</label> <span class="impColor">*</span>
						</div>
						
						<div class="col-sm-8">
							<input class="validate" type="file" name="fileimg"  accept="image/*"  onchange="validateImage(this.id)" onfocus="removeBorder(this.id)" style= "margin-top:15px;" id="fileimg" multiple />
						</div>
						
							<!--<div class="col-sm-4">
							<label>Attach File(s)</label>
						</div>
					 <div class="col-sm-8">
							<input type="file" name="file1" id="file1" class="form-control" multiple="multiple"/>
						</div>
						 -->
						
				
            </div>
                    		
                    		<div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div style="float:right; margin-right:20px;" class="btn-toolbar text-center">
                    		<br>
					      			<input type="button" id="modelSubmit" value="Submit"  onclick="serviceSubmit()" class="btn-primary btn"/>
					      			<input type="reset" value="Reset" class="btn-danger btn cancel1"/>
				      			</div>
				      		</div>
				      	</div>
			      	</div>
                    		
                    		</form>
                    		</div>
                    		
				</div> 
					
				</div> 
				
				<!-- Request Quotaion Model Start  -->
				
				 <div class="modal fade" id="quotationModal" data-backdrop="static" data-keyboard="false" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="background: #166eaf;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="color: white;">Request Quotation </h4>
        	</div>
        	<div style="max-width:90%; margin:0 auto; background:#FFFFFF;" class="modal-body">
        		<div align="center" class="text"><span class="impColor0"></span></div>
        			<div class="col-md-6">
						<form   class="form-horizontal" method="Post" enctype="multipart/form-data">
	                  <input type="hidden" id="id"/>
				
									<input type="hidden" id="modelnumber"  class="form-control validate" placeholder="Product Model"/>
							
								<div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-4 control-label no-padding-right">Location<span class="impColor"> *</span></label>
									<div class="col-md-6">
										<input type="text" name="locationData" id="locationData" class="form-control validate"  placeholder="Location in Map"/> 
									</div>
								</div><div class="clearfix"></div>
								
								<div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-4 control-label no-padding-right">Address <span class="impColor">*</span></label> 
									<div class="col-md-6">
										<input type="textarea" id="address" class="form-control validate " placeholder="Address"/>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-4 control-label no-padding-right">Description<span class="impColor">*</span></label> 
									<div class="col-md-6">
										<input type="textarea" id="reqdesc" class="form-control validate" placeholder=" Description"/>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
								<label class="col-md-4 control-label no-padding-right ">Attach <span class="impColor">*</span></label> 
									<div class="col-md-6">
										<input class="validate " type="file" name="imgfile"  accept="image/*"  onchange="validateImage(this.id)" onfocus="removeBorder(this.id)" style= "margin-top:15px;" id="imgfile" multiple />
									</div>
								</div>
					</form>
        			</div>
        			<div class="col-md-6">
						<div id="us2" style="width: 250px; height: 250px;"></div>
        			</div><div class="clearfix"></div>
								
								
									<div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div style="float:right; margin-right:20px;" class="btn-toolbar text-center">
                    		<br>
                    					<input type="submit" id="submit1" value="Submit" onclick="quotationSubmit()" class="btn-primary btn"/>
					      				<input type="reset" value="Reset" class="btn-danger btn cancel"/>
					      			
				      			</div>
				      		</div>
				      	</div>
			      	</div>

                    		
                    	
                    		
                    		</div>
                    		
				</div> 
					
				</div> 
				</div>
				<!-- Request Qutotaion Model End -->

<c:choose>
    <c:when test="${empty param.model}">
       <script type="text/javascript">
        $("#productDetails").hide();
       </script>
    </c:when>
    <c:otherwise>
    <script type="text/javascript">
	         $("#productModels").hide();
	         </script>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${empty param.id}">
       <script>var catid="";
       var bradcrmcategory=false;</script>
    </c:when>
    <c:otherwise>
        <script>var catid=${param.id};
        var bradcrmcategory=true;</script>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${empty param.company}">
       <script>var company="";
       var bradcrmcompany=false;</script>
    </c:when>
    <c:otherwise>
    <script> var company=${param.company};
             var bradcrmcompany=true;</script>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${empty param.model}">
       <script>
       var bradcrmmodel=false;</script>
    </c:when>
    <c:otherwise>
        <script>
         var modelid=${param.model};
        var bradcrmmodel=true;</script>
    </c:otherwise>
</c:choose>
<input type="hidden" id="custhiddenid" value=${customerId} >


<script src='https://static.codepen.io/assets/editor/live/console_runner-ce3034e6bde3912cc25f83cccb7caa2b0f976196f2f2d52303a462c826d54a73.js'></script>
<script src='https://static.codepen.io/assets/editor/live/css_live_reload_init-890dc39bb89183d4642d58b1ae5376a0193342f9aed88ea04330dc14c8d52f55.js'></script><meta charset='UTF-8'><meta name="robots" content="noindex"><link rel="shortcut icon" type="image/x-icon" href="//static.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" /><link rel="mask-icon" type="" href="//static.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111" /><link rel="canonical" href="https://codepen.io/jonvadillo/pen/NNZzwB" />
<script type="text/javascript" src="https://rawgit.com/Logicify/jquery-locationpicker-plugin/master/dist/locationpicker.jquery.js"></script>
<script type="text/javascript">

$(function(){
	getLocation();

	if (navigator.geolocation) {
	    	//var location_timeout = setTimeout("geolocFail()", 10000);

	        navigator.geolocation.getCurrentPosition( showPosition,showError);
	    } else {
	       // x.innerHTML = "Geolocation is not supported by this browser.";
	    }
	var langi= null;
	var longi = null;
	
		
		
		$('#us2').locationpicker({ 
			
			radius: 10,
			onchanged: function (currentLocation, radius, isMarkerDropped) {
		
	        var addressComponents = $(this).locationpicker('map').location.addressComponents;
		      //addressComponents.map.setZoom(200);
		      $("#locationData").val(currentLocation.latitude+'&'+currentLocation.longitude);
		      
		     // var mapContext = $(this).locationpicker('map');
		    //updateControls(addressComponents); //Data
		    // $("#locationData").readOnly() = true;
		    
		    	//var id = $(this).attr('id');
		    if( (currentLocation.latitude+'&'+currentLocation.longitude) !=  ""){
		    	
		    	removeBorder('locationData');
		    }
		   
		    }
		});
	
	
	 
});
function getLocation() {
    if (navigator.geolocation) {
    	//var location_timeout = setTimeout("geolocFail()", 10000);

        navigator.geolocation.getCurrentPosition( showPosition,showError,{timeout:5000});
    } else {
       // x.innerHTML = "Geolocation is not supported by this browser.";
    }
} 
 function showPosition(position) {
	var	langi = position.coords.latitude;
	var longi = position.coords.longitude;
		
		$('#us2').locationpicker({
			location: {
		        latitude: langi,
		        longitude: longi
		    },
		/* enableAutocomplete: true,
		    enableReverseGeocode: true,
		  radius: 0,
		  inputBinding: {
		    latitudeInput: $('#us2-lat'),
		    longitudeInput: $('#us2-lon'),
		    radiusInput: $('#us2-radius'),
		    locationNameInput: $('#us2-address')
		  }, */
		  
		  radius: 10,
		  onchanged: function (currentLocation, radius, isMarkerDropped) {
		        var addressComponents = $(this).locationpicker('map').location.addressComponents;
		      //addressComponents.map.setZoom(200);
		      $("#locationData").val(currentLocation.latitude+'&'+currentLocation.longitude);
		      
		     // var mapContext = $(this).locationpicker('map');
		    //updateControls(addressComponents); //Data
		    // $("#locationData").readOnly() = true;
		    
		    	//var id = $(this).attr('id');
		    if( (currentLocation.latitude+'&'+currentLocation.longitude) !=  ""){
		    	
		    	removeBorder('locationData');
		    }
		   
		    } ,
		    	
		});
	 	
	 }
 

//To use this code on your website, get a free API key from Google.
//Read more at: https://www.w3schools.com/graphics/google_maps_basic.asp

function showError(error) {
   /*  switch(error.code) {
        case error.PERMISSION_DENIED:
            x.innerHTML = "User denied the request for Geolocation."
            break;
        case error.POSITION_UNAVAILABLE:
            x.innerHTML = "Location information is unavailable."
            break;
        case error.TIMEOUT:
            x.innerHTML = "The request to get user location timed out."
            break;
        case error.UNKNOWN_ERROR:
            x.innerHTML = "An unknown error occurred."
            break;
    } */
}



function makeServiceRequestFieldsEmpty()
{
	$('#message').val("");
	$('#custaddress').val("");
}
 var customerId=$('#custhiddenid').val();
 console.log(customerId);
 /* var productdetailslist =${productdetails};
	var rowdata;
	$.each(productdetailslist, function(k,v){	
		//rowdata ="<li><a href='abheecategory?id="+v.id+" ' >"+v.category+"</a> </li>";
		rowdata= "<a href='#' onclick='getCompanys("+v.companyid+") '>"+v.companyname+"</a><br>";
		$("#catcom").append(rowdata);
	}); */
	var productmodelslist =${productmodels};
$.each(productmodelslist, function(k,v){
		var divData= "<div class='img'>"
		+"<a href='#'onclick='getModels("+v.id+")' >"
		+"<img src='../abheeimg/"+v.productmodelpics+"' class='img-responsive' alt='1810' title='"+v.name+"'>"
		+"<h4>"+v.companyname+"</h4>"
		+"<p id ='modelName'>"+v.name+"</p>"
        +"</a>"
        +"</div>";
		$("#productModels").append(divData);
		 /* toolTips(); */
	});
	
	function getCompanys(id){
		var url      = '${baseurl }/abheecategory?id='+catid; 
		url.replace("#", "");
		window.location.href=url+"&company="+id;
	}
	function getModels(id){
		var url      = '${baseurl }/abheecategory?id='+catid; 
		url.replace("#", "");
		window.location.href=url+"&company="+company+"&model="+id;
	} 
	var productdetailslist =${productdetails};
	var rowdata;
	var productmodelslist =${productmodels};
	
$.each(productmodelslist, function(k,v){
	$("#productModels").empty();
		var divData= "<div class='img'>"
		+"<a href='#'onclick='getModels("+v.id+")' >"
		+"<img src='../abheeimg/"+v.productmodelpics+"' class='img-responsive' alt='1810' title='"+v.name+"'>"
		+"<h4>"+v.companyname+"</h4>"
		+"<p  id ='modelName'>"+v.name+"</p>"
        +"</a>"
        +"</div>";
		
		$("#productModels").append(divData);
		 $("#breadcrumbname").text(v.categoryname);
		 $("#panelheading").text(v.categoryname);
	 
	});
	
$.each(productmodelslist, function(k,v){
	
	 var list=v.productmodelvideoslinks.split('*');
	var productmodelvideoslinks='';
	var vlinks="";
	for(var i=0;i<list.length;i++)
	{
		
		vlinks=vlinks 
		+"<div class='col-sm-4'>"
		
		+"<iframe width='270' height='200' src='https://www.youtube.com/embed/"+list[i]+" ' frameborder='0' allow='autoplay; encrypted-media' allowfullscreen></iframe>"
		+"</div>";
	}
	
	var pdivdata ="<div class='dimg' >"
	               +"<div class='col-sm-6' >"
	               +"<img width='100%' src='../abheeimg/"+v.productmodelpics+"' class='img-responsive' alt='196' title='"+v.name+"'/>"
	               +"<div class='col-xs-6'>"
    	           +"<a href='#' onclick='checkLogin()' style='width:95%;margin-top:9px !important;' class='btn btn-primary' >Request Quotation</a>"
    	           +"</div>"
	               +"<div class='col-xs-6'>"
	               +"<a href='#' onclick='checkService()' style='width:95%;margin-top:9px !important; float:right;' class='btn btn-warning' >Request Service</a>"
                   +"</div><div class='clearfix'></div>"
                   +"</div>"
                   +"</div>"
                   +"<div class='dtxt'>"
                   +"<div class='col-sm-6'>"
	                +"<p>"+v.description +"</p>"
	                +"<p>"+v.product_model_specifications +"</p>"
                   +"</div>"
                   +"</div><div class='clearfix'></div>"
                    +"<div class='vid'>"
                    + vlinks 
                     +"<div class='clearfix'></div>"
                      +"</div>";
	
	$("#productDetails").append(pdivdata);
	 $("#breadcrumbname").text(v.categoryname);
	$("#breadcrumbcompanyname").text(v.companyname);
	$("#breadcrumbmodelname").text(v.name);
	$("#panelheading").text(v.categoryname);
	if(bradcrmmodel) $('#cathead').text(v.companyname+' '+v.name); else $('#cathead').text(v.companyname+' '+v.categoryname);
});



$.each(productdetailslist, function(k,v){
	
	//rowdata ="<li><a href='abheecategory?id="+v.id+" ' >"+v.category+"</a> </li>";
	//if(v.companyid==)
	if($("#breadcrumbcompanyname").text()==v.companyname) var cls='tabactive'; else var cls='';
	rowdata= "<a href='#' class='"+cls+"' onclick='getCompanys("+v.companyid+") '>"+v.companyname+"</a><br>";
	$("#catcom").append(rowdata);
	
});

	
	function getCompanys(id){
		var url      = '${baseurl }/abheecategory?id='+catid; 
		url.replace("#", "");
		window.location.href=url+"&company="+id;
		
	}
	function getModels(id){
		var url      = '${baseurl }/abheecategory?id='+catid; 
		url.replace("#", "");
		window.location.href=url+"&company="+company+"&model="+id;
	}
	
	function checkLogin(){
		if(login){
			//console.log($("#modelName").text());
			
			//getLocation();
			$("#quotationModal").modal();
			 $("#locationData").prop("readonly", true);
				//localStorage.setItem("modelName",document.getElementById('modelName').innerHTML);
				//$('#modelName').text(localStorage.getItem("modelName"));
				//$('#modelnumber').val(localStorage.getItem("modelName"));
				$('#modelnumber').val(document.getElementById('modelName').innerHTML);
			//window.location.href='${baseurl }/salesRequest'
			
		}else{
			window.location.href='${baseurl }/customerlogin';
		}
	}
	
	if(bradcrmcategory){
		$("#breadcrumbname").show();
		$("#breadcrumbcompanyname").hide();
		$("#breadcrumbmodelname").hide();
	}
	
	if(bradcrmcompany)
		{
		$("#breadcrumbname").show();
		$("#breadcrumbcompanyname").show();
		}
	if(bradcrmmodel)
	{
		$("#breadcrumbname").show();
		$("#breadcrumbcompanyname").show();
		$("#breadcrumbmodelname").show();
	}
	
	function checkService(){
           if(login){
		$("#formModal").modal();
		
		makeServiceRequestFieldsEmpty();
           }
           else
           {
        	   window.location.href='${baseurl }/customerlogin';
           }
		
			//window.location.href='${baseurl }/customerlogin';
		
	}
	function quotationSubmit(){
		
		var modelnumber=$('#modelnumber').val();
    	var locationData=$('#locationData').val();
    	var address=$('#address').val();
    	var reqdesc=$('#reqdesc').val();
    	
    	 //Serializing all For Input Values (not files!) in an Array Collection so that we can iterate this collection later.
    	//var params = form.serializeArray();
    	
    	var objArr = [];
    	var jsonData = {"modelnumber":modelnumber,"address":address,"reqdesc":reqdesc, "locationData":locationData };
    	
	   var formData = new FormData();
    	
    	formData.append("salesRequest",JSON.stringify(jsonData));
	   
	   
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
	
	formData.append( "modelnumber",modelnumber);
	formData.append( "locationData",locationData);
	formData.append( "address",address);
	formData.append( "reqdesc",reqdesc);
	console.log(formData);
		$.ajax({
		type:"POST",
		//enctype: 'multipart/form-data',
		processData:false,
        contentType: false,
	  	url: "salesRequest", 
	  	data:formData,
		//contentType: false,  // tell jQuery not to set contentType
	  	
	  	success: function(result){
	  		if(result !="" && result != null){
	  		alert(result);
	  		}
	  		$('#salesrequest').val("");
	  		$('#imgfile').val("");
	  		 $('#quotationModal').modal('toggle');
	  	
	    },
	    error: function (e) {
            console.log(e.responseText);
        }
			    
	});
		
		
	}//quotationSubmit End
	
	
	function serviceSubmit()
	{
		
		 message =$('#message').val();
		 servicetypeid =$('#servicetypeid').val();
		 
		 custaddress =$('#custaddress').val();
		 
		 servicetypeid =$("#servicetypeid").val();
		 requesttimeid =$("#requesttimeid").val();
		 
		 var objArr = [];
	    	var jsonData = {"message":message,"catid":catid,"servicetypeid":servicetypeid,"requesttimeid":requesttimeid,"custaddress":custaddress,"modelid":modelid,"customerId":customerId};
	    	
		   var formData = new FormData();
	    	
	    	formData.append("saveServiceRequest",JSON.stringify(jsonData));
		 
		 $('span.error-keyup-4').remove();
		 
		 
		
		 
 if (servicetypeid == null || servicetypeid == "" || servicetypeid == "undefined") {
			 
			 $('.impColor0').after('<span class="error error-keyup-4">Fill Service Type Field </span>');
			 $('#servicetypeid' ).css('border-color','#e73d4a');
				$('#servicetypeid' ).css('color','#e73d4a');
			 return false;
			 
			 
		 }
 
		 
		 if (message == null || message == "" || message == "undefined") {
			 
			 $('.impColor0').after('<span class="error error-keyup-4">Fill Message Field </span>');
			 $('#message' ).css('border-color','#e73d4a');
				$('#message' ).css('color','#e73d4a');
			 return false;
			 
			 
		 }
		 if (custaddress == null || custaddress == "" || custaddress == "undefined") {
			 $('.impColor0').after('<span class="error error-keyup-4">Fill Address Field </span>');
			 $('#custaddress' ).css('border-color','#e73d4a');
				$('#custaddress' ).css('color','#e73d4a');
			 return false;
			 
			 
		 }
		 
		 var ins = document.getElementById('fileimg').files.length;
			
			for(var i=0; i< ins; i++)
			{	
			var images = document.getElementById('fileimg').files[i];
			formData.append('fileimg', images); 
			}
			alert(images);
			formData.append( "message",message);
			formData.append( "servicetypeid",servicetypeid);
			formData.append( "catid",catid);
			formData.append( "modelid",modelid);
			formData.append( "customerId",customerId);
			formData.append( "custaddress",custaddress);
			formData.append( "requesttimeid",requesttimeid);
		$.ajax({
			type : "POST",
			processData:false,
			contentType:false,
			url : "saveServiceRequest",
			data :formData,
			dataType : "text",
			beforeSend : function() {
	             $.blockUI({ message: 'Please wait' });
	          }, 
			success : function(result) {
				//alert(data);
				
				if(result !="" && result != null){
			  		alert("Thank you, your request had been submitted successfully.  Our team will contact you soon");
			  		}
			  		$('#saveServiceRequest').val("");
			  		$('#fileimg').val("");
			  		 $('#formModal').modal('toggle');
				
				/* if(data ==='true')
				{
					alert(" Thank you, your request had been submitted successfully. Our team will contact you soon");
					$('#formModal').modal('toggle');					
				}
				else
					{
					alert(data);
					$('#formModal').modal('toggle');
					} */
				
			},
			complete: function () {
	            
	            $.unblockUI();
	       },
			error :  function(e){$.unblockUI();console.log(e);}
			
		});

	}
	
	function validateImage(id) {
		$('span.error-keyup-4').remove();
		removeBorder(id);
		 var photos = document.getElementById(id);
         var howmany = photos.files.length;
         var err = 0;

         var img = new Array();
         
         
         for (var i = 0; i < howmany; i++) {               
	    
            var file1=photos.files[i];
            //document.getElementById('imgfile').files[i];
            if(file1){                        
                var file_size=file1.size;
                if(file_size<=1024000){
                    var ext = file1.name.split('.').pop().toLowerCase();                            
                    //if($.inArray(ext,['jpg','jpeg','gif', 'png','bmp'])===-1)
                     if(file1.type.indexOf("image")==-1)
                    {
                    	console.log(id);
                    	 $('#'+id).after('<span class="error error-keyup-4"> Upload images only. </span>');
                        return false;
                    }else{removeBorder(id);}

                }else{
                	$('#'+id).after('<span class="error error-keyup-4"> Image size should be less than 1MB. </span>');
                    return false;
                }                        
            }else{
                //alert("fill all fields..");         
                return false;
            }
        }
	 
	   /*  var t = file.type.split('/').pop().toLowerCase();
	    if (t != "jpeg" && t != "jpg" && t != "png" && t != "bmp" && t != "gif") {
	        alert('Please select a valid image file');
	        document.getElementById(id).value = '';
	        return false;
	    }
	    if (file.size > 1024000) {
	        alert('Max Upload size is 1MB only');
	        document.getElementById(id).value = '';
	        return false;
	    }
	    return true; */
	}
	
	function removeBorder2(el){	
		  $("#"+el).css("border", "");
		  $("#"+el).css('color','black');
		  $('#'+el).addClass('default-class');
		  if ($("#" + el+"_chosen").length)
			{
				$("#" +el+"_chosen").children('a').css('border-color','black');
			}
		  
		  $('span.error-keyup-4').remove();
	}
 
	$('.category').addClass("active");
</script>
 <%@include file="abheefooter.jsp" %>