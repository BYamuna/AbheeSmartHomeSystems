<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url var="loginAction" value="/loginAction"></spring:url>
    
    
    <%
	String baseurl =  request.getScheme() + "://" + request.getServerName() +      ":" +   request.getServerPort() +  request.getContextPath();
	session.setAttribute("baseurl", baseurl);
	
	/*  session = request.getSession(false);
	User userDesignation = (User)session.getAttribute("userDesignationSession"); 
	   if (userDesignation == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);
	}   */
 
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${baseurl }/abhee/images/icon.png">
<title>Abhee Smart Homes Systems</title>
    <link href="${baseurl }/abhee/css/bootstrap.min.css" rel="stylesheet">
    <link href="${baseurl }/abhee/css/main.css" rel="stylesheet">
    <link href="${baseurl }/assets/css/animate.min.css" rel="stylesheet">
     <link href="${baseurl }/abhee/css/bootstrap-dropdownhover.min.css" rel="stylesheet"> 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"/>
    <!-- Google Fonts -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA4TkibnxHHIJtDj1Dp59VAOSHp_sdA1KQ&sensor=false&libraries=places"></script>  
<!--      <script src='https://maps.google.com/maps/api/js?sensor=false&libraries=places'></script> -->
<style type="text/css">

.msgcss
{
/* 	width: 50% !important; */
/* 	font-weight: bold; */
	margin: auto;
	text-align: center;
	top: 3px !important;
	left:0;
	right:0;
	position: fixed;
	font-size: 14px;
	z-index:99999;
}

</style>
</head>
<body>
<%-- <c:if test="${not empty msg}">
		<div class="msgcss row">
			<div class="col-sm-4 col-sm-offset-4">
				<div class="form-group">
					<div class="alert alert-${cssMsg} fadeIn animated">${msg}</div>
				</div>
			</div>
		</div>
	</c:if> --%>
  	<div class="container">
    	<div class="top">
        	<img width="300px" src="${baseurl }/abhee/images/logo.png" class="img-resposive" alt="logo" title="Logo"/>
<!--         	<a style="float:right; margin-top:30px; margin-left:20px;" type="admin" class="button btn btn-primary" href="admin">Admin Login</a> -->
        </div><div class="clearfix"></div>
        <!-- Menu Starts here -->
        <div class="menu">
        	<nav class="navbar navbar-default">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div align="center" class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${baseurl }/">Home</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Categories</a>
          <ul class="dropdown-menu">
          <li id="cmlist"></li>
          </ul>
        </li>
        <li><a href="#">About Us</a></li>
        <li><a href="#">Our Mission & Vision</a></li>
        <li><a href="#">Gallery</a></li>
        <li><a href="#">Portfolia</a></li>
        <li><a href="#">Our other Locations</a></li>
        <li><a href="#">Contact Us</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">My Account</a>
          <ul class="dropdown-menu">
          <li id="cmlist"></li>
         
          
            <c:choose>
    <c:when test="${not empty loggedstatus}">
     <li><a href="${baseurl}/customerprofile">My Profile</a></li>
            <li><a href="${baseurl}/signout">Sign out</a></li>
    </c:when>
    <c:otherwise>
        <li><a href="customerlogin">Sign in</a></li>
    </c:otherwise>
</c:choose>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
</nav>
        </div>
  	</div>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
<%--     <script src="${baseurl }/abhee/js/bootstrap.min.js"></script> --%>
    <script src="${baseurl }/abhee/js/bootstrap-dropdownhover.min.js"></script> 
    
<%-- 
<script type='text/javascript' src="${baseurl }js/jquery.blockUI.min.js" ></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 --%>    
  	<script type="text/javascript">
  	 window.setTimeout(function() {
	    $(".msgcss1").fadeTo(500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 5000);
  	
  	</script>