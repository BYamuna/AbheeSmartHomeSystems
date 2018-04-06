<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
    <link rel="shortcut icon" href="${baseurl }/abhee/images/icon.png">
<title>Abhee Snart Homes Systems</title>
    <link href="${baseurl }/abhee/css/bootstrap.min.css" rel="stylesheet">
    <link href="${baseurl }/abhee/css/main.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
  	<div class="container">
    	<div class="top">
        	<img width="300px" src="${baseurl }/abhee/images/logo.png" class="img-resposive" alt="logo" title="Logo"/>
        	<a style="float:right; margin-top:20px; margin-left:20px;" type="admin" class="btn btn-primary" href="admin">Admin Login</a>
            <h3 style="float:right; margin-top:25px;"><a style="text-decoration:none;" href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i style="color:#FFCC33;" class="fa fa-user-o"></i> My Account <span class="caret"></span></a></h3>
        </div>
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
        <li class="active"><a href="#">Home</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categories <span class="caret"></span></a>
          <ul class="dropdown-menu">
          <span id="cmlist"></span>
            <li><a href="#">Home Theatter </a></li>
            <li><a href="#">PA Audio</a></li>
            <li><a href="#">Projectors</a></li>
            <li><a href="#">Security Cameras</a></li>
            <li><a href="#">Solar Fencing</a></li>
            <li><a href="#">Remote Gates</a></li>
            <li><a href="#">Wooden Flooring</a></li>
            <li><a href="#">Artificial Grass</a></li>
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
      	<button class="btn btn-dark" type="login"><a href="customerlogin">Login/Register</a></button>
      </ul>
    </div><!-- /.navbar-collapse -->
</nav>
        </div>
  	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  	</body>
  	<script type="text/javascript">
  	var categorieslist =${allOrders1};
  	var rowdata;
  	
  	$.each(categorieslist, function(k,v){
  		
  		rowdata ='<li><a href="#">'+v.category+'</a> </li>';
  		$("#cmlist").append(rowdata);
  	});
  	
  	</script>
  	</html>