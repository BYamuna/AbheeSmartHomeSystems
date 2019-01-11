<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="com.charvikent.abheeSmartHomeSystems.model.Customer" %>
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
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>
     <script src="${baseurl }/abhee/js/bootstrap-dropdownhover.min.js"></script> 
    
      
     <script src='//static.codepen.io/assets/editor/live/console_runner-ce3034e6bde3912cc25f83cccb7caa2b0f976196f2f2d52303a462c826d54a73.js'>
</script><script src='//static.codepen.io/assets/editor/live/css_live_reload_init-890dc39bb89183d4642d58b1ae5376a0193342f9aed88ea04330dc14c8d52f55.js'></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA4TkibnxHHIJtDj1Dp59VAOSHp_sdA1KQ&libraries=places"></script>  
<!--      <script src='https://maps.google.com/maps/api/js?sensor=false&libraries=places'></script> -->

<style type="text/css">

.navbar {
}

.navbar a {
    float: left;
    color:#FFFFFF;
    text-align: center;
    text-decoration: none;
}

.dropdown {
    float: left;
}

.dropdown .dropbtn {
    font-size: 16px;    
    border: none;
	border-radius:10px;
    outline: none;
    color: white;
    background-color:#3366CC;
    font-family: inherit;
    margin: 0;
}

.navbar a:hover, .dropdown:hover .dropbtn {
    background-color:#3366FF;
    
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
   	width: 300px;
    z-index: 1;
	margin-left:-35px;
	right:0;
	height:200px;
	overflow-y:auto;
}

.dropdown-content a {
    float: none;
    color: black;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {
    background-color: #f9f9f9;
}

.dropdown:hover .dropdown-content {
    display: block;
}


  	.table1{
		border:solid 1px;
		border-color:#CCCCCC;
		width:260px;
	}
	.tr1, .td1, .th1{
		border:solid 1px;
		border-color:#CCCCCC;
	}
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
.myacd {
min-width:113px;
}
.ttr li a {
	text-transform: capitalize;
}
 .nav .badge {
    top: -2px;
    /* left: 22px; */
    position: absolute;
    background-color: #e73c3c;
}
 .nav .badge, #sidebar .badge {
    text-align: center;
    text-shadow: none !important;
}
 .nav .badge {
    top: -4px;
    /* left: 22px; */
    position: absolute;
    background-color: #e73c3c;
}
 .nav .badge, #sidebar .badge {
    text-align: center;
    text-shadow: none !important;
}
.badge {
    padding: 4px 6px;
    font-size: 11px;
    font-family: 'Source Sans Pro', 'Segoe UI', 'Droid Sans', Tahoma, Arial, sans-serif;
}
</style>

<script>
function getQuotationNotifications1(){
	 var formData = new FormData();
		$.fn.makeMultipartRequest('POST', 'customerlogin', false,
				formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
		//	alert(jsonobj);
			var cquotationType =data;
			var cserviceType =data;
			displayQuotationNotifications1(jsonobj.cquotationType)
			displayNotifications1(cserviceType)
		});  
}


function displayQuotationNotifications1(listOrders) {
// 	alert(listOrders);
	$('#notification1').html('');
	var tableHead = '<table id="notification1" class="table table-striped table-bordered datatables">'
			+ '<thead><tr style="background:#166eaf; color:#FFFFFF;"><th style="text-align:center;">Request.No</th><th style="text-align:center;">Status</th><th style="text-align:center;">Request Type</th></thead><tbody></tbody></table>';
	$('#notification1').html(tableHead);
	//serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		
	//serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr>"
			+ "<td title='"+orderObj.salesrequestnumber+"'>"+ orderObj.salesrequestnumber + "</td>"
			+ "<td title='"+orderObj.kstatus+"'>New</td>"
			+ "<td title='"+orderObj.requestType+"'>"+ orderObj.requestType + "</td>"
			+ "</tr>";
		$(tblRow).appendTo("#notification1 table tbody");
	});
}
function displayNotifications1(listOrders) {
// 	alert(listOrders);
	$('#notification1').html('');
	var tableHead = '<table id="notification1" class="table table-striped table-bordered datatables">'
			+ '<thead><tr style="background:#166eaf; color:#FFFFFF;"><th style="text-align:center;">Request.No</th><th style="text-align:center;">Status</th><th style="text-align:center;">Request Type</th></thead><tbody></tbody></table>';
	$('#notification1').html(tableHead);
	//serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		var comment =null;
		if(orderObj.addComment == "" ||orderObj.addComment =="null"||typeof orderObj.addComment === "undefined")
			{
			comment="----";
			}else{
				comment =orderObj.addComment;
			
		}
	//serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr>"
			/* + "<td title='"+orderObj.taskno+"'><a href=viewTicket?id='"+ orderObj.taskno+"'&pgn=0'>"+ orderObj.taskno+"</a></td>" */
			 + "<td title='"+orderObj.taskno+"'>"+ orderObj.taskno + "</a></td>" 
			//+ "<td title='"+orderObj.serviceType+"'>"+ orderObj.serviceType + "</td>"
			+ "<td title='"+orderObj.kstatus+"'>"+ orderObj.kstatus + "</td>"
			+ "<td title='"+orderObj.requestType+"'>"+ orderObj.requestType + "</td>"
			+ "</tr>";
		$(tblRow).appendTo("#notification1 table tbody");
	});
}
</script>

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
        	<img width="300px" src="${baseurl }/abhee/images/logo.png" class="img-resposive" alt="logo"/>
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
        <li class="home"><a href="${baseurl }/">Home</a></li>
        <li class="dropdown category">
        
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Products</a>
          <ul class="dropdown-menu">
          <li class="ttr" id="cmlist"></li>
          </ul>
        </li>
        <li class="vision"><a href="${baseurl }/mission">Mission & Vision</a></li>
        <li class="gallery"><a href="${baseurl }/gallery">Gallery</a></li>
        <li class="career"><a href="${baseurl }/career">Career</a></li>
        <li class="location"><a href="${baseurl }/location">Our Other Locations</a></li>
        <li id="contact"><a href="${baseurl }/contact">Contact Us</a></li>
        <li class="about"><a href="${baseurl }/about">About Us</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <li style="float:left;">
            <div style="box-shadow:none; margin-right:10px; margin-bottom:0px;" class="navbar">
  <div style="border-left:none;" class="dropdown">
    <diV style="color:#fff; background:#166eaf; font-size:25px; margin-top:10px;" class="dropbtn"">










      <i class="fa fa-bell-o" ></i> <span class="badge" id="noOfMessages1"></span> 
    </div>
   <%--  <c:if test="${not empty notifications}"> --%>
<div id="notification1"  class="dropdown-content">
      <a style="padding: 10px 16px;" href="#">
      
      	<table class="table1 table table-striped table-bordered "  id="taskTableHeader1">
        	 <thead><tr class="tr1" style=" background: #006699; color: #FFF;"><th class="th1">Request.No</th><th class="th1">Status</th>
                	<th class="th1">Request Type</th></tr> 
            </thead>
            <tbody></tbody>
        </table>
        
        
      
      </a>
    </div>
     <%--  </c:if> --%>
  </div> 
</div>
                </li> 
        <li class="dropdown acc">
          <a href="#" class="dropdown-toggle"  data-toggle="dropdown" data-hover="dropdown">Hello! <span id="loggedCustomerName"></span></a>
          <ul style="width:100% !important;" class="dropdown-menu myacd">
          <li id="cmlist"></li>
         
          
            <c:choose>
    <c:when test="${not empty loggedstatus}">
      <script type="text/javascript"> $("#loggedCustomerName").text("${customerName}");</script>
     <li><a href="${baseurl}/customerprofile">My Profile</a></li>
            <li><a href="${baseurl}/signout">Sign out</a></li>
    </c:when>
    <c:otherwise>
     <script> $("#loggedCustomerName").text("Sign-in");</script>
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
   
    
<%-- 
<script type='text/javascript' src="${baseurl }js/jquery.blockUI.min.js" ></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 --%>    
  	