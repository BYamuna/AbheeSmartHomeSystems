<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script type="text/javascript"
	src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css">

<div class="clearfix"></div>
<ol class="breadcrumb">
	<li><a href="dashBoard">Home</a></li>
	<li>Guarantee Master</li>
</ol>
<br>
<div class="clearfix"></div>
<div class="container">
	<!-- <div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Guarantee List</h4>
					<div class="options">
						<a href="javascript:;" class="panel-collapse"><i
							class="fa fa-chevron-down"></i></a>
					</div>
				</div>
				<div class="panel-body collapse in">
					<input type="checkbox" class="form-check-input"
						onclick="inactiveData();" id="inActive"> <label
						class="form-check-label">Show Inactive List</label>
					<div class="table-responsive" id="tableId">
						<table cellpadding="0" cellspacing="0" border="0"
							class="table table-striped table-bordered datatables"
							id="example">
							<thead>
								<tr>
									<th>ProductID</th>
									<th>products</th>
									<th>Duration</th>
									
									<th style="text-align: center;">Options</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<div class="row" id="moveTo">
		<div class="col-md-12 col-sm-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
				<h4>Product Guarantee Form</h4>
				</div>
				<form:form class="form-horizontal" modelAttribute="guaranteef"
					action="productGuarantee" method="post" >
					<div class="panel-body">
					  <security:authorize access="hasRole('ROLE_ADMIN')">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<form:hidden path="id" />
									<label for="focusedinput" class="col-md-6 control-label ">ProductId
										<span class="impColor">*</span>
									</label>
										
									<form:select path="productid"
										class="col-xs-10 col-sm-5 validate"
										onfocus="removeBorder(this.id)">
										<form:option value="" label="--- Select ---" />
									<form:options items="${customerId}" />
									</form:select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label ">Products
										<span class="impColor">*</span>
									</label>

									<form:select path="products"
										class="col-xs-10 col-sm-5 validate"
										onfocus="removeBorder(this.id)">
										<form:option value="" label="--- Select ---" />
										<form:options items="${products}" />
									</form:select>
									<span class="hasError" id="stationnameError"></span>

								</div>
							</div>


						</div>						  
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-6 control-label">Duration
										<span class="impColor">*</span>
									</label>
									<div class="col-md-6">
										<form:input type="text" path="duration"
											class="form-control validate" />

									</div>
								</div>
							</div>
							
						</div>
						 </security:authorize>
						<div id="getting-started"></div>
					</div>
					<div class="panel-footer">
						<div class="row">
							<div class="col-sm-12">
								<div class="btn-toolbar text-center">
									<input type="submit" id="submit1" value="Submit" class="btn-primary btn" /> 
										<input type="reset" value="Reset" class="btn-danger btn cancel" />
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

$("#duration").keypress(function() {
	return false;
})
$(document).ready(function() {
		$('#duration').datetimepicker({

			useCurrent : false,
			format : 'DD-MMM-YYYY hh:mm A',
			showTodayButton : true,
			sideBySide : true,
			
			toolbarPlacement : 'top',
			focusOnShow : false,

		});
	});
var countDownDate;

var x = 0;

function showduration(id) {

	//$('#timeModal').html('');
	countDownDate = serviceUnitArray[id].duration

	var count = new Date(countDownDate).getTime();
	/*  x=setInterval(function() {clearInterval();}); */

	// Update the count down every 1 second
	if (x == 0) {
		x = setInterval(function() {
			// Get todays date and time
			var now = new Date().getTime();

			// Find the distance between now an the count down date
			var distance = count - now;

			document.getElementById("demo").innerHTML = '';

			// Time calculations for days, hours, minutes and seconds
			var days = Math.floor(distance / (1000 * 60 * 60 * 24));
			var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
					/ (1000 * 60 * 60));
			var minutes = Math.floor((distance % (1000 * 60 * 60))
					/ (1000 * 60));
			var seconds = Math.floor((distance % (1000 * 60)) / 1000);
			document.getElementById("demo").innerHTML = '';
			// Output the result in an element with id="demo"
			document.getElementById("demo").innerHTML = days + "d " + hours
					+ "h " + minutes + "m " + seconds + "s ";

			// If the count down is over, write some text 
			if (distance < 0) {
				document.getElementById("demo").innerHTML = "EXPIRED";
				showduration();
			}
		}, 1000);

		$("#timeModal").modal();
	} else {
		clearInterval(x);
		document.getElementById("demo").innerHTML = '';

		x = setInterval(function() {
			// Get todays date and time
			var now = new Date().getTime();

			// Find the distance between now an the count down date
			var distance = count - now;

			document.getElementById("demo").innerHTML = '';

			// Time calculations for days, hours, minutes and seconds
			var days = Math.floor(distance / (1000 * 60 * 60 * 24));
			var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
					/ (1000 * 60 * 60));
			var minutes = Math.floor((distance % (1000 * 60 * 60))
					/ (1000 * 60));
			var seconds = Math.floor((distance % (1000 * 60)) / 1000);
			document.getElementById("demo").innerHTML = '';
			// Output the result in an element with id="demo"
			document.getElementById("demo").innerHTML = days + "d " + hours
					+ "h " + minutes + "m " + seconds + "s ";

			// If the count down is over, write some text 
			if (distance < 0) {
				document.getElementById("demo").innerHTML = "EXPIRED";
				showduration();
			}
		}, 1000);
		$("#timeModal").modal();
 
	}

}
$("#pageName").text("Product Guarantee Master");
$(".productGuarantee").addClass("active");
</script>

	