<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.btn1 {
background-color:#337ab7 !important;
color: #fff !imoportant;
}
.impColor{
	
	color:red;
}
.tabactive
{
background-color: green;
color: white;
}
</style>
</head>
<body>
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
	<div tabindex="-1" class="modal fade" id="salesrequest-info" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Sales Request</h4>
        </div>
        <div class="modal-body">
        
							<form:form modelAttribute="salesRequest" class="form-horizontal " method="Post" enctype="multipart/form-data">
	                  <form:hidden path="id"/>
					<div class="col-md-6"><br>
					<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Product Model<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="modelnumber" class="form-control validate" placeholder="Enter Product Model"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Email<span class="impColor">*</span></label>
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
									<label class="col-md-3 control-label no-padding-right">location<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input type="text" id="us2-address" class="form-control validate"/>
										<input type="text" name="locationData" id="locationData" class="form-control"/> 
									</div>
								</div><div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Mobile<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="modelnumber" class="form-control validate numericOnly" maxlength="10"  placeholder="Enter Mobile Number"/>
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
								<label class="col-md-3 control-label no-padding-right">Choose images<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input type="file" name="imgfile" id="imgfile" multiple/>
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
					<%-- 		<form  class="form-horizontal " method="Post" enctype="multipart/form-data">
<!-- 	                  <hidden id="id"/> -->
					<div class=""><br>
					<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Product Model<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input id="modelnumber" class="form-control validate" placeholder="Enter Product Model"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Email<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input id="email" class="form-control validate" placeholder="Enter Email"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Mobile<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="modelnumber" class="form-control validate" placeholder="Enter Username"/>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group" id="passwordDiv">
									<label class="col-md-3 control-label no-padding-right">location<span class="impColor">*</span></label>
									<div class="col-md-6">
<!-- 										<input type="text" id="us2-address" class="form-control validate"/> -->
										<input type="text" name="locationData" id="locationData" class="form-control"/> 
								<div id="us2" style="width: 100%; height: 250px; margin-top:15px;"></div>	
									</div>
								</div><div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Mobile<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input id="modelnumber" class="form-control validate numericOnly" maxlength="10"  placeholder="Enter Mobile Number"/>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Address</label>
									<div class="col-md-6">
										<textarea id="address" class="form-control validate emailOnly" placeholder="Enter Address"></textarea>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Requirements Description</label>
									<div class="col-md-6">
										<textarea path="reqdesc" class="form-control validate emailOnly" placeholder="Enter Description"></textarea>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
								<label class="col-md-3 control-label no-padding-right">Choose images<span class="impColor">*</span></label>
									<div class="col-md-6">
										<input type="file" name="imgfile" id="imgfile" multiple/>
									</div>
								</div>
								
								<div class="clearfix"></div>
									<div align="center" class="but">							
					      				<input type="submit" id="submit1" value="Submit" class="btn-primary btn" onclick="submitRequest()"/>
					      				<input type="reset" value="Reset" class="btn-danger btn cancel"/>
					      			</div>
								<div class="clearfix"></div>
								</div>
								
								<div class="">
								</div>
					</form> --%>
								<div class="clearfix"></div>
		</div>	
						</div>
					</div>
				</div>			
								
								<div class="col-md-6">
								<div id="us2" style="width: 200px; height: 200px;"></div>	
								</div>
			
<style class="cp-pen-styles"></style>
			
Lat.: <input type="text" id="us2-lat"/>
Long.: <input type="text" id="us2-lon"/>
			
</body>				
	<script src='//static.codepen.io/assets/editor/live/console_runner-ce3034e6bde3912cc25f83cccb7caa2b0f976196f2f2d52303a462c826d54a73.js'>
	</script><script src='//static.codepen.io/assets/editor/live/css_live_reload_init-890dc39bb89183d4642d58b1ae5376a0193342f9aed88ea04330dc14c8d52f55.js'></script>
	<meta charset='UTF-8'><meta name="robots" content="noindex">
	<link rel="shortcut icon" type="image/x-icon" href="//static.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" />
	<link rel="mask-icon" type="" href="//static.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111" />
	<link rel="canonical" href="https://codepen.io/jonvadillo/pen/NNZzwB" />				
<script src='//static.codepen.io/assets/editor/live/console_runner-ce3034e6bde3912cc25f83cccb7caa2b0f976196f2f2d52303a462c826d54a73.js'></script>
<script src='//static.codepen.io/assets/editor/live/css_live_reload_init-890dc39bb89183d4642d58b1ae5376a0193342f9aed88ea04330dc14c8d52f55.js'></script><meta charset='UTF-8'><meta name="robots" content="noindex"><link rel="shortcut icon" type="image/x-icon" href="//static.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" /><link rel="mask-icon" type="" href="//static.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111" /><link rel="canonical" href="https://codepen.io/jonvadillo/pen/NNZzwB" />


<!-- AIzaSyBof-nUbLfnD7fyKZ2DvfLXwWX-RPgdU8c -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAi3pzKXd0y6FTkbdOOMFuhmp1E4DFicxo&libraries=places&sensor=false"></script>  
<script src='//static.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>
 <script src='https://maps.google.com/maps/api/js?sensor=false&libraries=places'></script>
<script src='https://cdn.rawgit.com/Logicify/jquery-locationpicker-plugin/master/dist/locationpicker.jquery.min.js'></script>

	
	
	
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
<script type="text/javascript">
var lat;
var lan;


  /* var categorieslist =${allOrders2};
	var rowdata;
	
	 $.each(categorieslist, function(k,v){
		 $("#cathead").text(v.category); 
		
		rowdata ="<li><a href='abheecategory?id="+v.id+" ' >"+v.category+"</a> </li>";
		$("#cmlist").append(rowdata);
	});   */
	
	var productdetailslist =${productdetails};

	var rowdata;
	
	
	$.each(productdetailslist, function(k,v){
		
		//rowdata ="<li><a href='abheecategory?id="+v.id+" ' >"+v.category+"</a> </li>";
		
		rowdata= "<a href='#' onclick='getCompanys("+v.companyid+") '>"+v.companyname+"</a><br>";
		$("#catcom").append(rowdata);
	});
	var productmodelslist =${productmodels};
	
$.each(productmodelslist, function(k,v){
	
		var divData= "<div class='img'>"
		+"<a href='#'onclick='getModels("+v.id+")' >"
		+"<img src='${baseurl }/reportDocuments/"+v.productmodelpics+"' class='img-responsive' alt='1810' title='YHT-1810'>"
		+"<h4>"+v.companyname+"</h4>"
		+"<p>"+v.name+"</p>"
        +"</a>"
        +"</div>";
		
		$("#productModels").append(divData);
		 $("#breadcrumbname").text(v.categoryname);
	 
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
	               +"<img width='100%' src='${baseurl }/reportDocuments/"+v.productmodelpics+"' class='img-responsive' alt='196' title='YHT-196'/>"
	               +"<div class='col-xs-6'>"
    	           +"<a href='#' onclick='checkLogin()' style='width:95%;margin-top:9px !important;' class='btn btn-primary' >Get Quotation</a>"
    	           +"</div>"
	               +"<div class='col-xs-6'>"
    	           +"<button style='width:95%; margin-left:10px;' class='btn btn-warning' type='button'>Get Service</button>"
                   +"</div>"
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
		    event.preventDefault();
		    jQuery.noConflict();
			$("#salesrequest-info").modal('show');
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
		  
	       // var addressComponents = $(this).locationpicker('map').location.addressComponents;
	      $("#locationData").val(currentLocation.latitude+'&'+currentLocation.longitude);
	    //updateControls(addressComponents); //Data
	    }
	});
	
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
//			$("#" + idArray[i] + "Error").text("Please " + placeholder);
			validation = false;
		} 
	});
	if(validation) {
		
	}else {
		return false;
	}
				var productmodel=$('#productmodel').val();
				var email=$('#email').val();
				var mobileno=$('#mobileno').val();
		    	var locationData=$('#locationData').val();
		    	var address=$('#address').val();
		    	var reqdesc=$('#reqdesc').val();
		    	
		    	 //Serializing all For Input Values (not files!) in an Array Collection so that we can iterate this collection later.
		    	//var params = form.serializeArray();
		    	var salesRequest = {"modelnumber":productmodel,	"email":email,"mobileno":mobileno,"address":address,"reqdesc":reqdesc ,"location":	locationData};
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

	
</script>
</html>
