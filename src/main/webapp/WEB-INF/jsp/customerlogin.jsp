<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url var="loginAction" value="/loginAction"></spring:url>
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!--     <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600' rel='stylesheet' type='text/css'> -->
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
    <!--[if lt IE 9]>
        <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.1.0/respond.min.js"></script>
        <script type="text/javascript" src="assets/plugins/charts-flot/excanvas.min.js"></script>
    <![endif]-->

    <!-- The following CSS are included as plugins and can be removed if unused-->



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
.modal-header {
	background-color:#ffb902;
}
.anchor{
	float:right;
}
.tag {
	color:#000000;
}
</style>

<script type="text/javascript">
	var isClick = 'No';
		 window.setTimeout(function() {
		    $(".msgcss").fadeTo(500, 0).slideUp(500, function(){
		        $(this).remove(); 
		    });
		}, 5000);
		
		 $(document).ready(function(){
			
			
		});  
		
</script>



    <%@include file="abheeheader.jsp" %>



	<div style="margin-bottom:45px;" class="main">
		<div class="main-row">
			<!-- login form -->
			<div class="login-form login-form-left"> 
				<div class="row">
					<div class="head">
							 <c:if test="${not empty msg}">
								<div class="msgcss1 row">
									<div align="center" class="form-group">
										<div style="width:80%" class="alert alert-${cssMsg} fadeIn animated">${msg}</div>
									</div>
								</div>
							</c:if>
						<h2>Login</h2>
					</div>					
									<div class="clearfix"></div>
						<form action="customerlogin" method="post"> 
					<div class="login-top"> 	
						  <input type="hidden" name="userType" id="userType" value="customerUser"/>
							<input type="text" class="form-control validate1 numericOnly" name="username" id="username" onfocus="this.placeholder=''" onblur="this.placeholder='Mobile Number'" maxlength="10" placeholder="Mobile Number" required/>
							<input type="password" maxlength="4" class="form-control validate1 numericOnly2" name="password" onfocus="this.placeholder=''" onblur="this.placeholder='Password'" placeholder="Password" required />
						
					</div> 
					<div class="login-bottom"> 
						<div class="col-sm-6">
							<h6><a href="#" data-toggle="modal" data-target="#passwordModel" style="color:#005696;" class="tag">Forgot Password</a></h6>
						</div>
						<div class="col-sm-6">
						
						   <!--  <input   class="btn btn-danger" type="reset" value="Reset"> 
							<input  class="btn btn-primary" type="submit" value="Login"> -->
							<button class="btn btn-primary" type="submit" value="Login">Login</button>
							<button class="btn btn-danger" type="reset" value="Reset">Reset</button>
						</div><div class="clearfix"></div>
					</div>
					</form> 	
					<div class="login-bottom"> 
						<h6 style="margin-top:40px;">Click Here To <a href="#" data-toggle="modal" onclick="openRegistrationModel()" style="color:#FF0000; text-decoration:underline;" class="tag">Register</a></h6>
					</div> 

				</div>  
			</div>  
		</div>
		<!-- //login form -->
		
		
	</div>	


 <div  class="modal fade" id="register-info" role="dialog">
    <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Customer Registration</h4>
        </div>
        <div class="modal-body">
          <form    id="registration"  method="post" class="login-form">
          
 

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
								<input	type="text" name="cmobile" id="cmobile" onkeydown="removeBorder(this.id)" maxlength="10" class="form-control validate1 mobilenumber" placeholder="Enter Mobile Number"/>
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
  
 
	<div class="modal fade" id="passwordModel" data-backdrop="static" data-keyboard="false" role="dialog">
    <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Password Assistance</h4>
        </div>
    
    
        
	<div class="modal-body">
          <form  action="#"  id="resendotp"  method="post" class="login-form">

						<div id="firstForm2">

							<div class="form-group">
								<label for="mobile number">Enter Registered MobileNumber :</label> 
								<input	type="text" name="resetmobile" id="resetmobile" onkeydown="removeBorder(this.id)" maxlength="10" class="form-control validate2 numericOnly" placeholder="Enter Mobile Number"/>
								<span class="hasError" id="cmobileError" style="font-size: 13px;"></span>
							</div>				
						</div>
					</form>	
        </div>
        <div class="modal-footer">
          <button type="button" id="resetpassword" onclick="resetpassword()" class="btn btn-primary" >Submit</button>
         
        </div>
      </div>
      
    </div>
  </div> 
  

<script type='text/javascript' src='js/customValidation.js'></script> 

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		

<%@include file="abheefooter.jsp" %>
<script type="text/javascript">


var validation = true;




var subValidation =false;
var subValidationemail =false;

$('#cmobile').blur(function() {
	var cmobile=$(this).val();
	$('span.error-keyup-4').remove();
    var inputVal = $(this).val();
    
    	
    var characterReg = /^[6789]\d{9}$/;
    if(!characterReg.test(inputVal)) {
        $(this).after('<span class="error error-keyup-4">Not a valid Mobile Number </span>');
        
        $('.mobilenumber' ).css('border-color','#e73d4a');
		$('.mobilenumber' ).css('color','#e73d4a');
		
		$('.mobilenumber' ).addClass("errorCls");
        //setTimeout(function() { $("#error-keyup-4").text(''); }, 3000);
        
        return false;
    }else{
    	
    	
	
	 /* if(cmobile.length != 10 )
		 {
		 alert("Mobile Number Length Must Be 10 Digits")
		 $('#cmobile').css('border-color', 'red');
		// $('#submitModel').prop('disabled', true);
		 
		 subValidation =false;
		 
		 }
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
						alert("Mobile Number already exists")
	 					$('#cmobile').css('border-color', 'red');
// 	 					 $('#submitModel').prop('disabled', true);
	 					 //alert("customer could not be registered")
	 					subValidation =false;
						}
					 else
						{
						$('#cmobile').css('border-color', 'none');
// 						 $('#submitModel').prop('disabled', false);
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
		var errorCls = $("#" + idArray[i]).hasClass('errorCls');
		var placeholder = $("#" + idArrayCmt1[i]).attr('placeholder');
		if (value == null || value == "" || value == "undefined" || errorCls ) {
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
			 event.preventDefault(); 
		}else{
			validation = true;
		}
	});
	//validation =subValidation;
	// retype password validation
	
	 var cpassword1 =$('#cpassword').val();
	 
	var crtpassword1=$('#crtpassword').val();
	
	if(cpassword1 != "" && crtpassword1 != "" && cpassword1==crtpassword1){
		validation = true;
	}else{
		if(cpassword1 != "" && crtpassword1 != ""){
		alert(" password and reenter password missmatch")
		}
		validation = false;
	} 
	
	
	if(validation && subValidation && subValidationemail) {
		$("#submit1").attr("disabled",true);
		$("#submit1").val("Please wait...");
		$("#submit1").submit();											
		 getOTP();
	}else {
		if(!subValidation){
			alert("Mobile number already Exists");
		}
		if(!subValidationemail){
			alert("Email already Exists");
		}
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
			  subValidationemail =true;
		
		
		$.ajax({
					type : "POST",
					url : "checkEmailExst",
					data :"cemail="+cemail,
					dataType : "text",
					beforeSend : function() {
			             $.blockUI({ message: 'Please wait' });
			          }, 
					success : function(data) {
						if(data ==='true')
							{
							subValidationemail =false;
							alert("Email already exists")
		 					$('#cemail').css('border-color', 'red');
// 		 					 $('#submitModel').prop('disabled', true);
							}
						else
							{
							$('#cemail').css('border-color', 'none');
// 							 $('#submitModel').prop('disabled', false);
							  subValidationemail =true;
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
			  subValidationemail =false;
			  
		}

			}); 
	
	
	
	function resetpassword()
	{
		$('#resetpassword').prop('disabled', true);
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
				var mobileno=$('#resetmobile').val();
		    	
			   
			   var formData = new FormData();
			   
			   
			   formData.append('resetmobile',mobileno);
	    	console.log(formData);
	 		$.ajax({
				type:"POST",
				
			  	url: "getresetcustomerpassword", 
			  	data:formData,
			  	processData: false,  // tell jQuery not to process the data
				contentType: false,  // tell jQuery not to set contentType
			  	
			  	success: function(result){
			  		//alert(result);
			  		if(result==true)
			  		{
			  			alert("Your Password sent to registered email and mobile number ");	
			  			$('#passwordModel').modal('toggle');
			  			 window.location.reload();
			  		}	
			  		else
			  			{
			  				alert("Enter registered mobile number");
			  				$('#resetpassword').prop('disabled', false);
			  				
			  			}
			  		
			  		
			  		 
			  	
			    },
			    error: function (e) {
		            console.log(e.responseText);
		        }
					    
			});
		
	}
	function makeEmptyRegistration()
	{
		$('#csname').val("");
		$('#cname').val("");
		$('#csmobile').val("");
		$('#cemail').val("");
		$('#csname').val("");
		$('#cpassword').val("");
		$('#crtpassword').val("");
	}
	
	function openRegistrationModel()
	{
		makeEmptyRegistration();
		$('#register-info').modal();
		
	}

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
</script>
</html>