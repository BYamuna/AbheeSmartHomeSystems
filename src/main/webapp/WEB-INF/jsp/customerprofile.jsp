<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
    
<style>
.fancy-select {
  position: absolute;
  left: 5000px;
}

.newSelect {
  position: relative;
  display: block;
  max-width: 300px;
  margin: 100px auto;
}
.newSelect:hover {
  height: auto;
}
.newSelect .selectedOption {
  background: white;
  padding: 20px;
  font-size: 18px;
  line-height: 18px;
  height: 58px;
  border: 1px solid #a9cdcf;
  color: #a9cdcf;
  cursor: pointer;
  position: relative;
  border-radius: 3px 3px 0 0;
}
.newSelect .selectedOption:after {
  content: '\f0dc';
  position: absolute;
  right: 20px;
  color: #af0606;
}
.newSelect .selectedOption.selected {
  color: #134b4e;
}
.newSelect .newOptions {
  position: absolute;
  width: 100%;
}
.newSelect .newOptions .newOption {
  display: none;
  top: 0;
  left: 0;
  font-size: 18px;
  line-height: 18px;
  height: 58px;
  padding: 20px;
  background: #ffe100;
  color: white;
  cursor: pointer;
}
.newSelect .newOptions .newOption:hover {
  background: #f80046;
}
.newSelect.clicked .newOption {
  display: block;
}
.newSelect.closed .newOption {
  display: none;
}

.panel-danger>.panel-heading
{
    background-color: #2f4cb5 !important;
    color: white !important;
}

.arrow_box {
    position: relative;
    background: none;
}
.arrow_box:hover {
    background: #262672;
}
.arrow_box:after {
    left: 100%;
    top: 50%;
    border: none;
    content: " ";
    height: 0;
    width: 0;
    position: absolute;
    pointer-events: none;
    border-color: #fff;
    border-left-color: #fff;
    border-width: 26px;
    margin-top: -26px;
    transition: all 0.3s ease 0s;
}
.arrow_box:hover:after,
.arrow_box.active:after {
    left: 100%;
    top: 50%;
    border: none;
    content: " ";
    height: 0;
    width: 0;
    position: absolute;
    pointer-events: none;
    border-color: transparent;
    border-left-color: #C21010;
    border-width: 0;
    margin-top: -26px;
}
.arrow_box:hover span,
.arrow_box.active span {
    height: 8px;
    width: 8px;
    line-height: 8px;
    border-radius: 50%;
    background: #fff;
    display: block;
    position: absolute;
    right: 0;
    top: 25px;
}
.list-items {
    border-bottom: none !important; width: 86%;
}
.list-items li {
    list-style: none;
    text-align: right;
    display: block;
    border-bottom: 1px solid #e7e7e7;
    position: relative;
    width: 100%;
    border-left: none !important; transition: all 0.4s ease 0s;
    border-radius: 3px;
}
.list-items li a {
    font-size: 16px;
    color: #000;
    margin: 0;
    display: block;
    padding: 13px 25px 14px 0px;
    transition: none;
    border: 1px solid #e7e7e7 !important; border-left: none !important; transition: all 0.4s ease 0s;
    font-weight: 400;
}
.list-items li a:hover {
    color: #fff;
    text-decoration: none;
    border-right: none;
    border-left: none !important;
}
.list-items li.active a {
    background: #013950 !important;
    color: #fff;
    border: none;
    padding: 13px 25px 14px 0px;
    border-right-color: #013950 !important; border: 1px solid #013950 !important; font-weight: 700;
}
.nav.list-items > li > a:focus,
.nav.list-items > li > a:hover {
    background: #166eaf;
    color: #FFF;
    border: none;
    padding: 13px 25px 14px 0px;
    border-right-color: #013950 !important; outline-color: #013950 !important;
}
.nav-tabs > li > a {
    border-radius: 0;
    border: none;
}
.ser-list {
    padding: 100px 0;
}
.tab-data-info h5 {
    font-size: 24px;
    font-weight: 700;
    color: #C21010;
    font-weight: 700;
    margin: 35px 0 15px;
}
.tab-data-info p {
    font-size: 14px;
    font-weight: 400;
    color: #000;
    width: 78%;
}
.tab-data-info p {
    font-size: 16px ;
    font-weight: 300 ;
    color: #000 ;
    width: 100% !important;
}
.ulimagecss{
list-style-image:url(images/Right-pointer.png);
}
</style>
    

    <%@include file="abheeheader.jsp" %>
    
    <div class="container">
      <div>
        <div class="">
          <div class="row">
            <div class="col-sm-4">
              <ul class="nav nav-tabs list-items">
                <li class="arrow_box active"><a data-toggle="tab" href="#1_1">Personal Information</a> </li>
                <li class="arrow_box"><a data-toggle="tab" href="#1_2">Change Email</a> </li>
                <li class="arrow_box"><a data-toggle="tab" href="#1_3">Change Mobile Number</a> </li>
                <li class="arrow_box"><a data-toggle="tab" href="#1_4">Change Password</a> </li>
                <li class="arrow_box"><a data-toggle="tab" href="#1_5">My Orders</a> </li>
              </ul>
            </div>
            <div class="col-sm-8">
              <!-- Tab panes -->
              <div class="tab-content">
                <div class="tab-pane fade active in" id="1_1">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="pinfo">
                  		<div class="col-md-6">
                  			<h3>Personal Inforation</h3>
                  		</div>
                  		<div class="col-md-6">
                  			<h4 style="float:right; margin-top:20px;" id="edit"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
                  			<h4 style="float:right; margin-top:20px;" id="edit"><a href="#"><i class="glyphicon glyphicon-save"></i> Save</a></h4>
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="edata">
                  		<div class="col-sm-6">
                  			<div class="col-md-3">
                  				<label style="margin-top:18px;">First Name: </label>
                  			</div>
                  			<div class="col-md-9">
                  				<input class="form-control" type="text" placeholder="First Name" disabled="true">
                  			</div><div class="clearfix"></div>
                  		</div>
                  		<div class="col-sm-6">
                  			<div class="col-md-3">
                  				<label style="margin-top:18px; margin-left:5px;">Last Name: </label>
                  			</div>
                  			<div class="col-md-9">
                  				<input class="form-control" type="text" placeholder="Last Name" disabled="true">
                  			</div><div class="clearfix"></div>
                  		</div><div class="clearfix"></div>
                  		<div class="ainfo">
                  			<div class="col-md-1">
                  				<label style="margin-top:25px;">Address: </label>
                  			</div>
                  			<div class="col-md-11">
                  				<textarea path= "address" class="form-control" type="text" placeholder="Address" disabled="true"></textarea>
                  			</div><div class="clearfix"></div>
                  		</div>
                  	</div>
                  </div>
                </div>
                <div class="tab-pane fade" id="1_2">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="cinfo">
                  		<div class="col-md-6">
                  			<h3>Email Inforation</h3>
                  		</div>
                  		<div class="col-md-6">
                  			<h4 style="float:right; margin-top:20px;" id="edit"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
                  			<h4 style="float:right; margin-top:20px;" id="edit"><a href="#"><i class="glyphicon glyphicon-save"></i> Save</a></h4>
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="cdata">
                  		<div class="col-sm-12">
                  			<div class="col-md-1">
                  				<label style="margin-top:18px;">Email Id: </label>
                  			</div>
                  			<div class="col-md-11">
                  				<input style="float:left;" class="form-control" type="text" placeholder="Email Id" disabled="true">
                  			</div><div class="clearfix"></div>
                  		</div><div class="clearfix"></div>
                  	</div>
                  </div>
                </div>
                <div class="tab-pane fade" id="1_3">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="cinfo">
                  		<div class="col-md-6">
                  			<h3>Mobile Number</h3>
                  		</div>
                  		<div class="col-md-6">
                  			<h4 style="float:right; margin-top:20px;" id="edit"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
                  			<h4 style="float:right; margin-top:20px;" id="edit"><a href="#"><i class="glyphicon glyphicon-save"></i> Save</a></h4>
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="cdata">
                  		<div class="col-sm-12">
                  			<div class="col-md-2">
                  				<label style="margin-top:18px; margin-left:5px;">Mobile Number: </label>
                  			</div>
                  			<div class="col-md-10">
                  				<input class="form-control" type="text" placeholder="Mobile Number" disabled="true">
                  			</div><div class="clearfix"></div>
                  		</div><div class="clearfix"></div>
                  	</div>
                  </div>
                </div>
                <div class="tab-pane fade" id="1_4">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="ainfo">
                  		<div class="col-md-6">
                  			<h3>Change Password</h3>
                  		</div>
                  		<div class="col-md-6">
                  			<h4 style="float:right; margin-top:20px;" id="edit"><a href="#"><i class="glyphicon glyphicon-edit"></i> Edit</a></h4>
                  			<h4 style="float:right; margin-top:20px;" id="edit"><a href="#"><i class="glyphicon glyphicon-save"></i> Save</a></h4>
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="pdata">
                  		<div class="col-sm-12">
                  			<div class="col-md-3">
                  				<label style="margin-top:18px;">Current Password: </label>
                  			</div>
                  			<div class="col-md-9">
                  				<input style="float:left;" class="form-control" type="text" placeholder="*****" disabled="true">
                  			</div><div class="clearfix"></div>
                  		</div>
                  		<div class="col-sm-12">
                  			<div class="col-md-3">
                  				<label style="margin-top:18px;">New Password: </label>
                  			</div>
                  			<div class="col-md-9">
                  				<input style="float:left;" class="form-control" type="text" placeholder="*****" disabled="true">
                  			</div><div class="clearfix"></div>
                  		</div>
                  		<div class="col-sm-12">
                  			<div class="col-md-3">
                  				<label style="margin-top:18px;">Confirm Password: </label>
                  			</div>
                  			<div class="col-md-9">
                  				<input style="float:left;" class="form-control" type="text" placeholder="*****" disabled="true">
                  			</div><div class="clearfix"></div>
                  		</div>
                  	</div>
                  </div>
                </div>
                <div class="tab-pane fade" id="1_5">
                  <div class="tab-data-info">
                  	<div style="margin-top:-20px;" class="oinfo">
                  		<div class="col-md-6">
                  			<h3>My Orders</h3>
                  		</div>
                  		<div class="col-md-6">
                  		</div><div class="clearfix"></div>
                  	</div>
                  	<div class="odata">
    					<div class="table-responsive">
							<table class="table table-bordered priority prioritybg"	style="border: 1px solid #0460a4;" id="customerOrderTable">
								<thead>
									<tr style="background-color: #0460a4; color: #fff; text-align: center;">
										<th>OrderId</th>
										<th>Product(s)</th>
										<th>Date of Purchased</th>
										<th>Warranty Expired Date</th>
										<th>Price</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
                  	</div>
                  </div>
                </div>
              </div>
			  
              <br />
            </div>
          </div>
        </div>
		</div>



<script type="text/javascript">
$('#customer').blur(function() {
	var customer=$(this).val();

	$.ajax({
				type : "POST",
				url : "editCustomerProfile",
				data : {"customer":customer},
				dataType : "text",
				beforeSend : function() {
		             $.blockUI({ message: 'Please wait' });
		          }, 
				success : function(data) {
					if(data ==='true')
						{
						//alert("username already exists")
	 					$('#customer').css('border-color', 'red');
						 $('#submit1').prop('disabled', true);
						}
					else
						{
						$('#customer').css('border-color', 'none');
						$('#edit').prop('disabled', false);
						}
					
				},
				complete: function () {
		            
		            $.unblockUI();
		       },
				error :  function(e){$.unblockUI();console.log(e);}
				
			});

		}); 
</script>
<%@include file="abheefooter.jsp" %>