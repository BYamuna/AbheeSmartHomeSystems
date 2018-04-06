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
		
         
  <div id="carousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#carousel" data-slide-to="0" class="active"></li>
      <li data-target="#carousel" data-slide-to="1"></li>
      <li data-target="#carousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
        <img src="${baseurl }/abhee/images/hmt.png" alt="HMT" style="width:100%;">
      </div>

      <div class="item">
        <img src="${baseurl }/abhee/images/paa.png" alt="PAA" style="width:100%;">
      </div>
    
      <div class="item">
        <img src="${baseurl }/abhee/images/flooring.png" alt="Floor" style="width:100%;">
      </div>
    </div>

  </div>
        <!-- Carousel Ends here -->
        
        <!-- Content starts here -->
        <div class="data">
        	<div class="col-md-4 imgl">
            	<a href="#"><img src="${baseurl }/abhee/images/hmt.png" class="img-responsive" alt="Home Theater" title="Home Theater"/></a>
            </div>
        	<div class="col-md-4 imgm">
            	<a href="#"><img src="${baseurl }/abhee/images/paa.png" class="img-responsive" alt="PA Audio" title="PA Audio"/></a>
            </div>
        	<div class="col-md-4 imgr">
            	<a href="#"><img src="${baseurl }/abhee/images/flooring.png" class="img-responsive" alt="Wooden" title="Wooden Flooring"/></a>
            </div><div class="clearfix"></div>
        	<div class="col-md-4 imgl">
            	<a href="#"><img src="${baseurl }/abhee/images/security.png" class="img-responsive" alt="Security" title="Security Cameras"/></a>
            </div>
        	<div class="col-md-4 imgm">
            	<a href="#"><img src="${baseurl }/abhee/images/remote.png" class="img-responsive" alt="Remote" title="Remote"/></a>
            </div>
        	<div class="col-md-4 imgr">
            	<a href="#"><img src="${baseurl }/abhee/images/proj.jpg" class="img-responsive" alt="Projector" title="Projectors"/></a>
            </div><div class="clearfix"></div>
        	<div class="col-md-4 imgl">
            	<a href="#"><img src="${baseurl }/abhee/images/solar.png" class="img-responsive" alt="Solar" title="Solar Fencing"/></a>
            </div>
        	<div class="col-md-4 imgm">
            	<a href="#"><img src="${baseurl }/abhee/images/car.png" class="img-responsive" alt="Car" title="Car Accessories"/></a>
            </div>
        	<div class="col-md-4 imgr">
            	<a href="#"><img src="${baseurl }/abhee/images/grass.png" class="img-responsive" alt="Grass" title="Artificial Grass"/></a>
            </div><div class="clearfix"></div>
        </div>
	</div>
</body>

<%@include file="abheefooter.jsp" %>
<script type="text/javascript">
var status= ${loggedstatus};
</script>
</html>