 <%@include file="abheeheader.jsp" %>
  	<div class="container">
<style type="text/css">
.sl-cl2
{
display: none !important;
}
.col-lg-4 img {
	width:100%;
	height:280px;
}
</style>
        
      <!--Gallery-->
      	 <section class="gallery">
            <div class="grid gallery-info">
               <div class="col-lg-4 gallery-grids">
              	<a href="#" data-toggle="modal" data-target="#gallery" ><img src="${baseurl}/abhee/images/propr.jpg" alt="" class="img-responsive"></a>
               </div>
               <div class="col-lg-4 gallery-grids">
              	<a href="#" data-toggle="modal" data-target="#gallery" ><img src="${baseurl}/abhee/images/group.jpg" alt="" class="img-responsive"></a>
               </div>
               <div class="col-lg-4 gallery-grids">
              	<a href="#" data-toggle="modal" data-target="#gallery" ><img src="${baseurl}/abhee/images/sec.jpg" alt="" class="img-responsive"></a>
               </div><div class="clearfix"></div>
            </div>
            <div class="grid gallery-info">
               <div class="col-lg-4 gallery-grids">
              	<a href="#" data-toggle="modal" data-target="#gallery" ><img src="${baseurl}/abhee/images/home.jpg" alt="" class="img-responsive"></a>
               </div>
               <div class="col-lg-4 gallery-grids">
              	<a href="#" data-toggle="modal" data-target="#gallery" ><img src="${baseurl}/abhee/images/ravel.jpg" alt="" class="img-responsive"></a>
               </div>
               <div class="col-lg-4 gallery-grids">
              	<a href="#" data-toggle="modal" data-target="#gallery" ><img src="${baseurl}/abhee/images/elec.jpg" alt="" class="img-responsive"></a>
               </div><div class="clearfix"></div>
            </div>
           <div class="grid gallery-info">
               <div class="col-lg-4 gallery-grids">
              	<a href="#" data-toggle="modal" data-target="#gallery" ><img src="${baseurl}/abhee/images/art.jpg" alt="" class="img-responsive"></a>
               </div>
              <div class="col-lg-4 gallery-grids">
              	<a href="#" data-toggle="modal" data-target="#gallery" ><img src="${baseurl}/abhee/images/nam.jpg" alt="" class="img-responsive"></a>
              </div>
               <div class="col-lg-4 bot gallery-grids">
              	<a href="#" data-toggle="modal" data-target="#gallery" ><img src="${baseurl}/abhee/images/log.jpg" alt="" class="img-responsive"></a>
              </div><div class="clearfix"></div>
            </div>
      	</section><div class="clearfix"></div>
      <!--//Gallery-->
      
      <!-- Gallery Modal -->
      <div class="modal fade" id="gallery" tabindex="-1" role="dialog" aria-labelledby="galleryLabel" aria-hidden="true">
  		<div class="modal-dialog" role="document">
    		<div style="background-color:none;" class="modal-content1">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span style="color:#013950;" aria-hidden="true">&times;</span>
        		</button>
        		
    			<!-- Ninja slider -->
    			<div id="ninja-slider">
        			<div class="slider-inner">
           				<ul>
                			<li><a class="ns-img" href="abhee/images/propr.jpg"></a>
                    			<!--<div class="caption">Propritor</div>-->
                			</li>
                			<li><a class="ns-img" href="abhee/images/group.jpg"></a>
                    			<!--<div class="caption">Propritor</div>-->
                			</li>
                			<li><a class="ns-img" href="abhee/images/sec.jpg"></a>
                    			<!--<div class="caption">Propritor</div>-->
                			</li>
                			<li><a class="ns-img" href="abhee/images/home.jpg"></a>
                    			<!--<div class="caption">Propritor</div>-->
                			</li>
                			<li><a class="ns-img" href="abhee/images/ravel.jpg"></a>
                    			<!--<div class="caption">Propritor</div>-->
                			</li>
                			<li><a class="ns-img" href="abhee/images/elec.jpg"></a>
                    			<!--<div class="caption">Propritor</div>-->
                			</li>
                			<li><a class="ns-img" href="abhee/images/art.jpg"></a>
                    			<!--<div class="caption">Propritor</div>-->
                			</li>
                			<li><a class="ns-img" href="abhee/images/nam.jpg"></a>
                    			<!--<div class="caption">Propritor</div>-->
                			</li>
                			<li><a class="ns-img" href="abhee/images/log.jpg"></a>
                    			<!--<div class="caption">Propritor</div>-->
                			</li>
            			</ul>
       				 </div>
    			</div>
    			<!-- Ninja slider -->
    		</div>
  		</div>
      </div>
      <!-- Gallery Modal -->
           
    </div><!-- container -->
    <script src="${baseurl }/abhee/js/gallery.js"></script>
    <script>
    $(".gallery").addClass("active");
    </script>
     <%@include file="abheefooter.jsp" %>