<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    
    <%@include file="abheeheader.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Abhee Smart Homes</title>
</head>
<body>

      <div class="container">
        <!-- Breadcrumb Starts here -->
        <nav aria-label="breadcrumb">
  			<ol class="breadcrumb">
    			<li class="breadcrumb-item"><a href="index.html">Home</a></li>
    			<li class="breadcrumb-item"><a href="#">Catagories</a></li>
    			<li class="breadcrumb-item active" aria-current="page">Yamaha</li>
  			</ol>
		</nav>
        <!-- Breadcrumb Ends here -->
        
        <!-- Content Starts here -->
        <div class="cont">
        	<div class="col-md-3">
  				<div class="panel panel-default">
    				<div class="panel-body">Home Theater</div>
  				</div>
                <div class="lcat">
        			<div class="smen">
        				<span id="catcom"></span>
            			<!-- <a href="home.html">Yamaha</a><br>
            			<a href="#">Denon</a><br>
            			<a href="#">Polk Audio</a><br>
            			<a href="#">KEF</a><br>
            			<a href="#">ELAC</a><br>
            			<a href="#">JBL</a><br> -->
            		</div>
                </div>
        	</div>
        	<div class="col-md-9">
            	<div class="listdata">
            		<span id="cathead"></span>
            		<!-- <h2>YAMAHA Home Theaters</h2> -->
            		<div align="center" class="col-md-12 col-sm-4" id="productModels">
                    	
                	</div>
                	
                	</div>
            		
                	
                <div id="productDetails">	
            	<div class="dimg" >
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
                </div>
                </div>
            <div class="clearfix"></div>
                	
                </div><div class="clearfix"></div>
        	</div><div class="clearfix"></div>
        </div>
        <!-- Content Ends here --> 
      </div>  
        

</body>
<c:choose>
    <c:when test="${empty param.id}">
       <script>var catid="";</script>
    </c:when>
    <c:otherwise>
        <script>var catid=${param.id};</script>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${empty param.company}">
       <script>var company="";</script>
    </c:when>
    <c:otherwise>
        <script>var company=${param.company};</script>
    </c:otherwise>
</c:choose>
<%@include file="abheefooter.jsp" %>

<script type="text/javascript">
/* var productdetailslist =${productdetails};

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
	} */
</script>
</html>