<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url var="loginAction" value="/loginAction"></spring:url>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Abhee Smart Homes</title>
    <link rel="shortcut icon" href="${baseurl }/abhee/images/icon.png" type="icon"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
<link rel='stylesheet' type='text/css' href='assets/plugins/charts-morrisjs/morris.css' /> 
<link rel='stylesheet' type='text/css' href='assets/plugins/codeprettifier/prettify.css' /> 
<link rel='stylesheet' type='text/css' href='assets/plugins/form-toggle/toggles.css' /> 
<link rel='stylesheet' type='text/css' href='assets/plugins/datatables/dataTables.css' />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  </head>
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



<style type="text/css">
.alert {
    padding: 15px;
    margin-bottom: 0px;
    border: 1px solid transparent;
    border-radius: 1px;
}
.alert-success, .alert-warning, .alert-danger{color: white !important;}
.alert-success{background-color: #4CAF50 !important;}
.alert-warning{background-color: #ff6600 !important;}
.alert-danger{background-color: #d43f3a !important; padding: 5px;
    width: 208px;
    margin: 0 auto;}

.your-class::-webkit-input-placeholder {color: #e73d4a !important;}
.your-class::-moz-placeholder {color: #e73d4a !important;}

.default-class::-webkit-input-placeholder {color: #e73d4a !important;}
.default-class::-moz-placeholder {color: #e73d4a !important;}
.panel-primary .panel-body {}
.modal-header {
	background-color:#ffb902;
}
.anchor{
	float:right;
}
.tag {
	color:#FF0000;
}
</style>

<!-- <script type='text/javascript' src='assets/js/jquery-1.10.2.min.js'></script> -->
<script type="text/javascript">
 window.setTimeout(function() {
    $(".msgcss").fadeTo(500, 0).slideUp(500, function(){
        $(this).remove(); 
    });
}, 2000); 
</script>

</head>

<body class="focusedform">
<div class="verticalcenter">
<div><img src="assets/img/Klogo2.png" style="width:350px;" class="img-responsive"></div>
<!-- 	<h1 align="center">KHAIBAR GAS</h1> -->
	<div class="panel panel-primary">
		<form  action=login class="form-horizontal" method="POST"  style="margin-bottom: 0px !important;">
		<div class="panel-body">
			<h4 class="text-center" style="margin-bottom: 25px;">Login to get started</h4>
			<c:if test="${param.error ne null}">
			   
			   <div class="msgcss1 row">
									<div align="center" class="form-group">
										<div style="width:80%" class=" msgcss alert alert-danger fadeIn animated">Invalid username and password.</div>
									</div>
								</div>
				<%-- <div class="alert-danger">Invalid username and password.</div>
			
				<div class="col-sm-12" style="margin-bottom: -1.3em;">
					<div class="form-group">
						<div class="msgcss fadeIn animated alert" style="text-align: center;">${msg}</div>
					</div>
				</div> --%>
			</c:if>
			
			  <input type="hidden" name="userType" id="userType" value="adminUser"/>
			<div class="form-group">
				<div class="col-sm-12">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<input type="text"  name= "username" id="muser" autofocus="autofocus" class="form-control"  placeholder=" Mobile Number"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						<input type="password" name="password" id="mpass" class="form-control "  placeholder="Password"/>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-footer">
			<div class="pull-right">
				<input type="submit"  id="mlogin" value="Sign-in"  class="btn btn-primary">
				<input type="reset" value="Reset" class="btn btn-danger cancel"/>
				<!-- <input type="reset" value="Reset" class="btn btn-default cancel"/>
				<input type="submit" id="submit1" value="Sign-in"  class="btn btn-primary"> -->
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		</form>
	</div>
	
	<!-- <p class="anchor"> New Customer Registration <a href="#" data-toggle="modal" data-target="#register-info" class="tag">Click here</a></p> -->
</div>
 <div  class="modal fade" id="register-info" role="dialog">
    <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Customer Registration</h4>
        </div>
        <div class="modal-body">
          <form  action="#"  id="registration"  method="post" class="login-form">
          
 

						<div id="firstForm">
						
						<div class="form-group">
								<label for="user_name"> First Name :</label> 
								<input	type="text" name="csname" id="csname" onkeydown="removeBorder(this.id)" class="form-control validate1 onlyCharacters" placeholder="Enter First Name"/>
								<span class="hasError" id="csnamelError" style="font-size: 13px;"></span>
							</div>
							<div class="form-group">
								<label for="user_name">Last Name :</label> 
								<input	type="text" name="cname" id="cname" onkeydown="removeBorder(this.id)" class="form-control validate1 onlyCharacters" placeholder="Enter Last Name"/>
								<span class="hasError" id="cnameError" style="font-size: 13px;"></span>
							</div>
							<div class="form-group">
								<label for="user_name">Enter MobileNumber :</label> 
								<input	type="text" name="cmobile" id="cmobile" onkeydown="removeBorder(this.id)" maxlength="10" class="form-control validate1 numericOnly" placeholder="Enter Mobile Number"/>
								<span class="hasError" id="cmobileError" style="font-size: 13px;"></span>
							</div>

							<div class="form-group">
								<label for="user_name">Enter Your Email-Id :</label> 
								<input	type="email" name="cemail" id="cemail" onkeydown="removeBorder(this.id)" class="form-control validate1 emailOnly" placeholder="Enter Email"/>
								<span class="hasError" id="emailError" style="font-size: 13px;"></span>
							</div>
							<div class="form-group">
								<label for="user_name">Enter  password (Max 4 Digits) :</label> 
								<input	type="password" name="cpassword" id="cpassword" onkeydown="removeBorder(this.id)" maxlength="4" class="form-control validate1 numericOnly" placeholder="Enter password" />
								<span class="hasError" id="cpasswordError" style="font-size: 13px;"></span>
							</div>
							<div class="form-group">
								<label for="user_name">Retype password :</label> 
								<input	type="password" name="crtpassword"  id="crtpassword" onkeydown="removeBorder(this.id)"  maxlength="4" class="form-control validate1 numericOnly" placeholder="Enter Retype Password"/>
								<span class="hasError" id="crtpasswordError" style="font-size: 13px;"></span>
							</div>
							
							
							
						</div>
					</form>	
        </div>
        <div class="modal-footer">
          <button type="button" id="submitModel"  class="btn btn-primary" data-dismiss="modal">Submit</button>
           <input type="reset" value="Reset" class="btn-danger btn cancel1"/>
        </div>
      </div>
      
    </div>
  </div> 
  
  <div class="modal fade" id="OTPModel" data-backdrop="static" data-keyboard="false" role="dialog">
    <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h4 class="modal-title">OTP Verification</h4>
        </div>
        <div class="modal-body">
          <form  action="#"  id="registration1"  method="post" class="login-form">

						<div id="firstForm1">

							
							<div class="form-group">
								<label for="user_name">Enter OTP :</label> 
								<input	type="password" name="cotp" id="cotp" onkeydown="removeBorder(this.id)" maxlength="4" class="form-control numericOnly" placeholder="Enter OTP"/>
								<span class="hasError" id="emailError" style="font-size: 13px;"></span>
							</div>
							
						</div>
					</form>	
        </div>
        <div class="modal-footer">
          <button type="button" id="submit2" onclick="modelsubmit()" class="btn btn-primary" >Submit</button>
         
        </div>
      </div>
      
    </div>
  </div> 
  
  
  
<script type='text/javascript' src='js/customValidation.js'></script> 
<script type='text/javascript' src="js/jquery.blockUI.min.js" ></script>
</body>
<script type="text/javascript">


var validation = true;


var subValidation =true;

$('#cmobile').blur(function() {
	var cmobile=$(this).val();
	
	
	 
	 if(cmobile.length != 10 )
		 {
		 alert("Password Length Must Be 10 Digits")
		 $('#cmobile').css('border-color', 'red');
		// $('#submitModel').prop('disabled', true);
		 
		 subValidation =false;
		 
		 }
	 
	
	 else
		 {
	
	
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
						alert("Mobile Number already exists")
	 					$('#cmobile').css('border-color', 'red');
	 					 $('#submitModel').prop('disabled', true);
	 					subValidation =false;
						}
					 else
						{
						$('#cmobile').css('border-color', 'none');
						 $('#submitModel').prop('disabled', false);
						 subValidation =true;
						} 
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});
	
		 }

		}); 
		
		
/* $('#crtpassword').blur(function() {
	
	 cpassword =$('#cpassword').val();
	 
	 crtpassword=$('#crtpassword').val();
	 
	 if(cpassword != crtpassword)
		 {
		 alert("Password and Retype password should be Matched")
		 $('#submitModel').prop('disabled', true);
		 
		 }
	 else
		 {
		 
		 $('#submitModel').prop('disabled', false);
		 }
	 
	
	
	
	
}); 
		 */
		
var cmobile =0
var cemail =0
var csname =0
var cname =0

var cpassword =0
var idArrayCmt1 = null;



//var validation = true;

	idArrayCmt1 = $.makeArray($('.validate1').map(function() {
		return this.id ;
	}));
		
	
	$('#submitModel').click(function(event) {
	$.each(idArrayCmt1, function(i, val) {
		var value = $("#" + idArrayCmt1[i]).val();
		var placeholder = $("#" + idArrayCmt1[i]).attr('placeholder');
		if (value == null || value == "" || value == "undefined" ) {
			$('style').append(styleBlock);
			$("#" + idArrayCmt1[i] ).attr("placeholder", placeholder);
			$("#" + idArrayCmt1[i] ).css('border-color','#e73d4a');
			$("#" + idArrayCmt1[i] ).css('color','#e73d4a');
			$("#" + idArrayCmt1[i] ).addClass('placeholder-style your-class');
			 var id11 = $("#" + idArrayCmt1[i]+"_chosen").length;
			if ($("#" + idArrayCmt1[i]+"_chosen").length)
			{
				$("#" + idArrayCmt1[i]+"_chosen").children('a').css('border-color','#e73d4a');
			}
//			$("#" + idArray[i] + "Error").text("Please " + placeholder);
			validation = false;
		}else{
			validation = true;
		}
	});
	validation =subValidation;
	if(validation) {
	/* 	$("#submit1").attr("disabled",true);
		$("#submit1").val("Please wait...");
		$("#submit1").submit();											
		event.preventDefault(); */
		 getOTP();
	}else {
		return false;
		 event.preventDefault(); 
	}
	
});

function getOTP()
{
	
	 cmobile =$('#cmobile').val();
	 /*cemail =$('#cemail').val();
	 csname =$('#csname').val();
	 cname =$('#cname').val();
	 
	
	
alert(cmobile+"-->"+cemail+"-->"+csname+"-->"+cname);
	 */
	 
		
	$.ajax({
		type : "POST",
		url : "getOtp",
		data :"cmobile="+cmobile,
		dataType : "text",
		beforeSend : function() {
             $.blockUI({ message: 'Please wait' });
          }, 
		success : function(data) {
			if(data ==='true')
				{
				//location.reload();
				alert("OTP Send to Your Mobile Number ");
				$('#register-info').modal('hide');
				//$("#register-info").modal('toggle');
				$('#OTPModel').modal('toggle');
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
	function modelsubmit()
	{
		
		 cmobile =$('#cmobile').val();
		 cemail =$('#cemail').val();
		 csname =$('#csname').val();
		 cname =$('#cname').val();
		 cotp=$('#cotp').val();
		 cpassword =$('#cpassword').val();
		
	//alert(cmobile+"-->"+cemail+"-->"+csname+"-->"+cname);
	//alert(cotp+"-->"+cpassword);
	
		
		$.ajax({
			type : "POST",
			url : "modelSubmit",
			data :"cmobile="+cmobile+"&cemail="+cemail+"&csname="+csname+"&cname="+cname+"&cotp="+cotp+"&cpassword="+cpassword,
			dataType : "text",
			beforeSend : function() {
	             $.blockUI({ message: 'Please wait' });
	          }, 
			success : function(data) {
				//alert(data);
				
				if(data ==='true')
				{
					alert(" Registration Completed Successfully ");
					$('#OTPModel').modal('toggle');					
				}
				else
					alert("Enter valid OTP")
				
			},
			complete: function () {
	            
	            $.unblockUI();
	       },
			error :  function(e){$.unblockUI();console.log(e);}
			
		});

	}


	$(".cancel1").click(function()
			{
				$("#id").val(0);
				$.each( idArrayCmt1, function(i, val)
				{
					var value = $("#" +  idArrayCmt1[i]).val();
					if ($("#" + idArrayCmt1[i]+"_chosen").length)
					{
						$("#" + idArrayCmt1[i]).val("");
						$("#" + idArrayCmt1[i]).trigger("chosen:updated");
					}
//					$("form")[0].reset();
					$("#"+ idArrayCmt1[i]).val('');
					$("#"+ idArrayCmt1[i]).prop('readonly',false);
					$("#"+ idArrayCmt1[i]).css('border-color','');
					$("#"+ idArrayCmt1[i]).css('color','black');
					$("#"+ idArrayCmt1[i]).removeClass('placeholder-style your-class default-class');
					if ($("#" +  idArrayCmt1[i]+"_chosen").length)
					{
						$("#" +  idArrayCmt1[i]+"_chosen").children('a').css('border-color','black');
					}
				});
			});
	
	
	
	$('#cemail').blur(function() {

		var cemail=$(this).val();
		
		var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  if( regex.test(cemail))
			  {
			  subValidation =true;
		
		
		$.ajax({
					type : "POST",
					url : "checkEmailExst",
					data :"cmobile="+cemail,
					dataType : "text",
					beforeSend : function() {
			             $.blockUI({ message: 'Please wait' });
			          }, 
					success : function(data) {
						if(data ==='true')
							{
							alert("Email already exists")
		 					$('#cemail').css('border-color', 'red');
		 					 $('#submitModel').prop('disabled', true);
							}
						else
							{
							$('#cemail').css('border-color', 'none');
							 $('#submitModel').prop('disabled', false);
							}
						
					},
					complete: function () {
			            
			            $.unblockUI();
			       },
					error :  function(e){$.unblockUI();console.log(e);}
					
				});
			  }
		  else
			  
		{
			  $('#cemail').css('border-color', 'red');
			  subValidation =false;
			  
		}

			}); 
	
	/*  var pageURL = $(location).attr("href");
     alert(pageURL);
     
     if(pageURL.endsWith("error")){
    	    alert("String Found");
    	    changeUrl();
    	}
	function changeUrl()
	{
	
	var temp="/login/";
	$(location).attr('href','http://'+temp);
	
	} */

	/* $('#cpassword').blur(function() {
		ccpassword =$('#cpassword').val();
		//alert(ccpassword);
		var  passwordPolicy= /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@#$!%*?&.])[A-Za-z\d$@#$!%*?&.]{6,15}$/;
		alert(passwordPolicy.test(ccpassword));
		if (passwordPolicy.test(ccpassword)) 
		{
            alert('Valid password');
            $('#submitModel').prop('disabled', false);
        }
		else
			{
			alert("Password should contain minimum 6 and maximum 15 characters, at least one uppercase letter, one lowercase letter, one number and one special character");	
			$('#cpassword').css('border-color', 'red');
			$('#submitModel').prop('disabled', true);
			}  
			
		
	});  */
	
	$('#mlogin').click(function() {
	var musername =	$('#muser').val();
	var mpass =	$('#mpass').val();
	if (musername == null || musername == "" || musername == "undefined") {
		alert("Please Enter Mobile Number")
	return false;
	}
	if (mpass == null || mpass == "" || mpass == "undefined") {
		alert(" Please Enter password")
	return false;
	}
		
	});
</script>
</html>