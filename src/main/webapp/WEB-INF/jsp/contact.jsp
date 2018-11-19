<%@include file="abheeheader.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
  	<div class="container">
        <c:if test="${not empty msg}">
		<div class="msgcss row">
			<div class="col-sm-4 col-sm-offset-4">
				<div class="form-group">
					<div class="alert alert-${cssMsg} fadeIn animated">${msg}</div>
				</div>
			</div>
		</div>
	</c:if>
        <!-- Contact us starts here -->
        <div class="contact">
        	<h2>Contact Us</h2>
            <form:form class="form-horizontal" commandName="contact"  action="contact" method="post">
            <form:hidden path="id"/>
            	<div class="col-md-6">
                	<div class="col-sm-3">
                    	<label>Full Name: </label>
                    </div>
                	<div class="col-sm-9">
                    	<form:input path="fullname" class="form-control validate onlyCharacters" placeholder="Full Name"/>
                    </div><div class="clearfix"></div>
                </div>
            	<div class="col-md-6">
                	<div class="col-sm-3">
                    	<label>Email Id: </label>
                    </div>
                	<div class="col-sm-9">
                    	<form:input path="emailid" class="form-control validate emailOnly" placeholder="Email Id" />
                    </div><div class="clearfix"></div>
                </div><div class="clearfix"></div>
            	<div class="col-md-6">
                	<div class="col-sm-3">
                    	<label>Mobile Number: </label>
                    </div>
                	<div class="col-sm-9">
                    	<form:input path="mobilenumber" class="form-control validate numericOnly2" placeholder="Mobile Number" maxlength="10"/>
                    </div><div class="clearfix"></div>
                </div>
            	<div class="col-md-6">
                	<div class="col-sm-3">
                    	<label>Subject: </label>
                    </div>
                	<div class="col-sm-9">
                    	<form:input path="subject" class="form-control validate onlyCharacters" placeholder="Subject" />
                    </div><div class="clearfix"></div>
                </div><div class="clearfix"></div>
                <a style="float:right;" id="submit1" class="btn btn-primary" type="button">Submit</a>
            </form:form>
        </div>
        <!-- Contact us starts here -->
        </div>
        
       
	<script>
	window.setTimeout(function() {
	    $(".msgcss").fadeTo(500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 5000);
	
		$("#contact").addClass("active");
	</script>
	
	

 <%@include file="abheefooter.jsp" %>