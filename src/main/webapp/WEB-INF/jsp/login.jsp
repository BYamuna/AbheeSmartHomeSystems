<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url var="loginAction" value="/loginAction"></spring:url>
<!DOCTYPE html>
<html lang="en">

<header>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</header>
<head>
    <meta charset="utf-8">
    <title>Abhee Smart Homes</title>
    <link rel="shortcut icon" href="/assets/img/logoicon.jpg"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  
</body>
    <link rel="stylesheet" href="assets/css/styles.css">
<!--     <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600' rel='stylesheet' type='text/css'> -->
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
    <!--[if lt IE 9]>
        <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.1.0/respond.min.js"></script>
        <script type="text/javascript" src="assets/plugins/charts-flot/excanvas.min.js"></script>
    <![endif]-->

    <!-- The following CSS are included as plugins and can be removed if unused-->

<link rel='stylesheet' type='text/css' href='assets/plugins/charts-morrisjs/morris.css' /> 
<link rel='stylesheet' type='text/css' href='assets/plugins/codeprettifier/prettify.css' /> 
<link rel='stylesheet' type='text/css' href='assets/plugins/form-toggle/toggles.css' /> 
<link rel='stylesheet' type='text/css' href='assets/plugins/datatables/dataTables.css' />

<style type="text/css">
.alert-success, .alert-warning, .alert-danger{color: white !important;}
.alert-success{background-color: #4CAF50 !important;}
.alert-warning{background-color: #ff6600 !important;}
.alert-danger{background-color: #d43f3a !important;}

.your-class::-webkit-input-placeholder {color: #e73d4a !important;}
.your-class::-moz-placeholder {color: #e73d4a !important;}

.default-class::-webkit-input-placeholder {color: #e73d4a !important;}
.default-class::-moz-placeholder {color: #e73d4a !important;}
.panel-primary .panel-body {
  border-top: 2px solid #e43b25;
}
</style>

<script type='text/javascript' src='assets/js/jquery-1.10.2.min.js'></script>
<script type="text/javascript">
/* window.setTimeout(function() {
    $(".msgcss").fadeTo(500, 0).slideUp(500, function(){
        $(this).remove(); 
    });
}, 5000); */
</script>

</head>

<body class="focusedform">
<div class="verticalcenter">
<div><img src="assets/img/klogo.png" style="width:350px;" class="img-responsive"></div>
<!-- 	<h1 align="center">KHAIBAR GAS</h1> -->
	<div class="panel panel-primary">
		<form  action=login class="form-horizontal" method="POST"  style="margin-bottom: 0px !important;">
		<div class="panel-body">
			<h4 class="text-center" style="margin-bottom: 25px;">Login to get started</h4>
			<c:if test="${param.error ne null}">
				<div class="alert-danger">Invalid username and password.</div>
			
				<div class="col-sm-12" style="margin-bottom: -1.3em;">
					<div class="form-group">
						<div class="msgcss fadeIn animated alert alert-danger" style="text-align: center;">${msg}</div>
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<div class="col-sm-12">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<input type="text"  name= "username" class="form-control validate"  placeholder="Username"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						<input type="password" name="password" class="form-control validate"  placeholder="Password"/>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-footer">
			<div class="pull-right">
				<input type="reset" value="Reset" class="btn btn-default cancel"/>
				<input type="submit" value="Sign-in"  class="btn btn-primary">
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		</form>
	</div>
	
	<a  class="" data-toggle="modal" data-target="#register-info">Register</a>
</div>
 <div class="modal fade" id="register-info" role="dialog">
    <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Set up your Account</h4>
        </div>
        <div class="modal-body">
          <form  action="#"  id="registration"  method="post" class="login-form">

						<div id="firstForm">

							<div class="form-group">
								<label for="user_name">Enter Your Email-Id :</label> 
								<input	type="email" name="cemail" id="cemail" onkeydown="removeBorder(this.id)" class="form-control" placeholder="Enter Email"/>
								<span class="hasError" id="emailError" style="font-size: 13px;"></span>
							</div>
							<div class="form-group">
								<label for="user_name">Enter MobileNumber :</label> 
								<input	type="text" name="cmobile" id="cmobile" onkeydown="removeBorder(this.id)" class="form-control" placeholder="Enter Email"/>
								<span class="hasError" id="emailError" style="font-size: 13px;"></span>
							</div>
							<div class="form-group">
								<label for="user_name"> Surname :</label> 
								<input	type="text"name="csname" id="csname" onkeydown="removeBorder(this.id)" class="form-control" placeholder="Enter Email"/>
								<span class="hasError" id="emailError" style="font-size: 13px;"></span>
							</div>
							<div class="form-group">
								<label for="user_name">Name :</label> 
								<input	type="text" name="cname" id="cname" onkeydown="removeBorder(this.id)" class="form-control" placeholder="Enter Email"/>
								<span class="hasError" id="emailError" style="font-size: 13px;"></span>
							</div>
						</div>
					</form>	
        </div>
        <div class="modal-footer">
          <button type="button" id="submit1" onclick="modelsubmit()" class="btn btn-suscces" data-dismiss="modal">Submit</button>
        </div>
      </div>
      
    </div>
  </div> 
  
  <div class="modal fade" id="OTPModel" role="dialog">
    <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Set up your Account</h4>
        </div>
        <div class="modal-body">
          <form  action="#"  id="registration1"  method="post" class="login-form">

						<div id="firstForm1">

							
							<div class="form-group">
								<label for="user_name">Enter OTP :</label> 
								<input	type="text" name="cotp" id="cotp" onkeydown="removeBorder(this.id)" class="form-control" placeholder="Enter Email"/>
								<span class="hasError" id="emailError" style="font-size: 13px;"></span>
							</div>
							
						</div>
					</form>	
        </div>
        <div class="modal-footer">
          <button type="button" id="submit1" onclick="modelsubmit()" class="btn btn-suscces" data-dismiss="modal">Submit</button>
        </div>
      </div>
      
    </div>
  </div> 

<script type='text/javascript' src='js/customValidation.js'></script> 
<script type='text/javascript' src="/js/jquery.blockUI.min.js" ></script>
</body>
<script type="text/javascript">
$('#cmobile').blur(function() {
	var cmobile=$(this).val();
	
	/* alert("hi");
	alert(cmobile);
 */
	$.ajax({
				type : "POST",
				url : "checkCustExst",
				data :"cmobile="+cmobile,
				dataType : "text",
				beforeSend : function() {
		             $.blockUI({ message: 'Please wait' });
		          }, 
				success : function(data) {
					if(data ==='true')
						{
						//alert("username already exists")
	 					$('#cmobile').css('border-color', 'red');
						 $('#submit1').prop('disabled', true);
						}
					else
						{
						$('#cmobile').css('border-color', 'none');
						$('#submit1').prop('disabled', false);
						}
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});

		}); 
		
function modelsubmit()
{
	
	var cmobile =$('#cmobile').val();
	var cemail =$('#cemail').val();
	var csname =$('#csname').val();
	var cname =$('#cname').val();
	
	
alert(cmobile+"-->"+cemail+"-->"+csname+"-->"+cname);
	
	$.ajax({
		type : "POST",
		url : "modelSubmit",
		data :"cmobile="+cmobile+"&cemail="+cemail+"&csname="+csname+"&cname="+cname,
		dataType : "text",
		beforeSend : function() {
             $.blockUI({ message: 'Please wait' });
          }, 
		success : function(data) {
			if(data ==='true')
				{
				alert("OTP Send to Your Mobile Number ");
				$("#register-info").modal('toggle');
				$("#OTPModel").modal('show');
				}
			else
				{
				$('#cmobile').css('border-color', 'none');
				$('#submit1').prop('disabled', false);
				}
			
		},
		complete: function () {
            
            $.unblockUI();
       },
		error :  function(e){$.unblockUI();console.log(e);}
		
	});


	
	
}
		


</script>
</html>