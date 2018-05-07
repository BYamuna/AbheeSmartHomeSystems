<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@include file="abheeheader.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Abhee Smart Homes</title>

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
    			<li class="breadcrumb-item"><a href="#">Catagories</a></li>
    			<li class="breadcrumb-item active" aria-current="page" id="breadcrumbname"></li>
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
					<form>
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
        <script>var company=${param.company};
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

<%@include file="abheefooter.jsp" %>

<script type="text/javascript">


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
		+"<img src='${baseurl }/reportDocuments/"+v.productmodelpics+"' class='img-responsive' alt='1810' title='YHT-1810'>"
		+"<h4>"+v.companyname+"</h4>"
		+"<p id ='modelName'>"+v.name+"</p>"
        +"</a>"
        +"</div>";
		
		$("#productModels").append(divData);
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
		+"<img src='${baseurl }/reportDocuments/"+v.productmodelpics+"' class='img-responsive' alt='1810' title='YHT-1810'>"
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
	               +"<img width='100%' src='${baseurl }/reportDocuments/"+v.productmodelpics+"' class='img-responsive' alt='196' title='YHT-196'/>"
	               +"<div class='col-xs-6'>"
    	           +"<a href='#' onclick='checkLogin()' style='width:95%;margin-top:9px !important;' class='btn btn-primary' >Request Quotation</a>"
    	           +"</div>"
	               +"<div class='col-xs-6'>"
	               +"<a href='#' onclick='checkService()' style='width:95%;margin-top:9px !important; float:right;' class='btn btn-warning' >Request Service</a>"
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
			//console.log($("#modelName").text());
				localStorage.setItem("modelName",document.getElementById('modelName').innerHTML);
			window.location.href='${baseurl }/salesRequest'
			
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
	
	function serviceSubmit()
	{
		
		 message =$('#message').val();
		 servicetypeid =$('#servicetypeid').val();
		 
		 custaddress =$('#custaddress').val();
		 
		 servicetypeid =$("#servicetypeid").val();
		 
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
		 
		
		$.ajax({
			type : "POST",
			url : "saveServiceRequest",
			data :"message="+message+"&servicetypeid="+servicetypeid+"&catid="+catid+"&modelid="+modelid+"&customerId="+customerId+"&custaddress="+custaddress,
			dataType : "text",
			beforeSend : function() {
	             $.blockUI({ message: 'Please wait' });
	          }, 
			success : function(data) {
				//alert(data);
				
				if(data ==='true')
				{
					alert(" Thank you, your request had been submitted successfully. Our team will contact you soon");
					$('#formModal').modal('toggle');					
				}
				else
					{
					alert(data);
					$('#formModal').modal('toggle');
					}
				
			},
			complete: function () {
	            
	            $.unblockUI();
	       },
			error :  function(e){$.unblockUI();console.log(e);}
			
		});

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
    