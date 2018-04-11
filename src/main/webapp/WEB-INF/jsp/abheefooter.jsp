<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
label, .form-control {
	margin-top:10px;
}
.tabactive
{
background-color: green;
color: white;
}
</style>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
							<select class="form-control" type="text" placeholder="Service type">
								<option>---select one---</option>
								<option>Repair</option>
								<option>Installation</option>
							</select>
						</div>
						<div class="col-sm-4">
							<label>Comment</label>
						</div>
						<div class="col-sm-8">
							<textarea class="form-control" type="comment" placeholder="Comment"></textarea>
						</div>
						<div class="col-sm-4">
							<label>Attach File(s)</label>
						</div>
						<div class="col-sm-8">
							<input type="file" name="file1" id="file1" class="form-control" multiple="multiple"/>
						</div>
						
						
					</form>
				</div>
            </div>
                    		
                    		
                    		<div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div style="float:right; margin-right:20px;" class="btn-toolbar text-center">
					      			<input type="button" id="modelSubmit" value="Submit"  onclick="submitCommet()" class="btn-primary btn"/>
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
<script type="text/javascript">


  /* var categorieslist =${allOrders2};
	var rowdata;
	
	 $.each(categorieslist, function(k,v){
		 $("#cathead").text(v.category); 
		
		rowdata ="<li><a href='abheecategory?id="+v.id+" ' >"+v.category+"</a> </li>";
		$("#cmlist").append(rowdata);
	});   */
	
	var productdetailslist =${productdetails};

	var rowdata;
	
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
	               +"<img width='100%' src='${baseurl }/reportDocuments/"+v.productmodelpics+"' class='img-responsive' alt='196' title='YHT-196'/>"
	               +"<div class='col-xs-6'>"
    	           +"<a href='#' onclick='checkLogin()' style='width:95%;margin-top:9px !important;' class='btn btn-primary' >Get Quotation</a>"
    	           +"</div>"
	               +"<div class='col-xs-6'>"
	               +"<a href='#' onclick='checkService()' style='width:95%;margin-top:9px !important; float:right;' class='btn btn-warning' >Get service</a>"
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
			
		alert("true");
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
		alert("Enter to service");
		$("#formModal").modal();
		
			//window.location.href='${baseurl }/customerlogin';
		
	}
 
 
</script>
</html>
