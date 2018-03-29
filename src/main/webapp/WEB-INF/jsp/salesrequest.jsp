<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div  class="modal fade" id="salesrequest-info" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Sales Request</h4>
        </div>
      <div class="modal-body">
		<form:form modelAttribute="srequestf" action="salesRequest" class="form-horizontal" enctype="multipart/form-data" method="Post">
	          <form:hidden path="id"/>
	             <div class="row">     
					<div class="col-md-6"><br>
						<div class="form-group">
							<label class="col-md-3 control-label no-padding-right">Model Number<span class="impColor">*</span></label>
								<div class="col-md-6">
										<form:input path="modelnumber" class="form-control validate" placeholder="Enter Modelnumber"/>
								</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-3 control-label no-padding-right">EmailId</label>
								<div class="col-md-6">
									<form:input path="email" class="form-control" placeholder="Enter Email"/>
								</div>
						</div>
					</div>		
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-3 control-label no-padding-right">Mobile No<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="mobileno" class="form-control validate numericOnly" placeholder="Enter Mobile Number"/>
									</div>
								</div>
						</div>	
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-3 control-label no-padding-right">Select images<span class="impColor">*</span></label>
									<div class="col-md-6">
										<!-- <input type="file" class="form-control validate" multiple="multiple" /> -->
										<input type="file" name="imgfile" id="imgfile" multiple/>
									</div>
								</div>
						</div>					
				</div>	
				<div class="row">     
					<div class="col-md-6"><br>
						<div class="form-group">
							<label class="col-md-3 control-label no-padding-right">Location<span class="impColor">*</span></label>
								<div class="col-md-6">
										<form:input path="location" class="form-control validate" placeholder="Enter Location"/>
								</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-3 control-label no-padding-right">address</label>
								<div class="col-md-6">
									<form:textarea path="address" class="form-control" placeholder="Enter Address"/>
								</div>
						</div>
					</div>		
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-3 control-label no-padding-right">Requirements Description<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:textarea path="reqdesc" class="form-control validate" placeholder="Enter Requirementsdescription"/>
									</div>
								</div>
						</div>				
				</div>
		<div class="modal-footer">
        <input type="submit" id="create" class="btn btn-primary" value="Submit"/> 
		<input type="reset" id="reset" class="btn btn-default"  value="Cancel"/>
        </div>
		</form:form>
			</div>
<!-- <script type="text/javascript">
$('#imgfile').blur(function() {
	ifile =$('#imgfile').val();
	var fileSize=/^(\d*\.?\d+)(?(?=[KMGT])([KMGT])(?:i?B)?|B?)$/;
	alert(fileSize.test(ifile));
	if(fileSize.test(ifile))
	{
	alert("Maximum file size should be 2MB");
	$('#submitModel').prop('disabled', false);
	}
	else
	{
		alert("file size should not exceed more than 2MB");
		$('#imgfile').css('border-color', 'red');
		$('#submitModel').prop('disabled', true);
	}
	});
</script> -->		
      	</div> 
   	</div>
</div> 			