<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="/_header" />
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content">
			<div class="row-fluid">
   				<div class="span12">
              		<div class="hero-unit" style="background-color:whiteSmoke">
                		<h1>Canada Airlines</h1>
                		<p>Flying at its best</p>
              		</div>
          			<div class="row-fluid">
                    	<div class="span7">
                            <h2>Search for Flights</h2>
                      		<form class="form-horizontal" action="/search" method="post">
	                        	<div class="control-group">
	                            	<label class="control-label" for="in_from">From</label>
	                            	<div class="controls">
	                                	<input name="origin" type="text" id="in_from" placeholder="Enter your Departure City">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label" for="in_to">To</label>
	                            	<div class="controls">
	                                	<input name="destination" type="text" id="in_to" placeholder="Enter your Destination City">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label" for="in_depart">Date of Departure</label>
	                            	<div class="controls">
	                               		<input name="departureDate" type="text" id="in_depart" placeholder="Enter Date of Departure">
	                               		<script>
                                        	$('#in_depart').datepicker({
       		                                	format: 'mm-dd-yyyy'
 	                                    	});     
                                        </script>
	                           		</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label" for="in_return">Date of Return</label>
	                            	<div class="controls">
	                                	<input name="returnDate" type="text" id="in_return" placeholder="Enter Date of Return">
	                                	<script>
                                        	$('#in_return').datepicker({
       		                                	format: 'mm-dd-yyyy'
 	                                    	});     
                                        </script>
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label" for="in_psgr">Number of Passangers</label>
	                            	<div class="controls">
	                                	<input name="numPassengers" type="text" id="in_psgr" placeholder="Number of Passangers">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<div class="controls">
	                                	<label class="radio">
	                                    	<input name="oneWayOrReturn" type="radio" name="flightOpt" value="oneWay" checked> One Way
	                                	</label>
	                                	<label class="radio">
	                                    	<input name="oneWayOrReturn" type="radio" name="flightOpt" value="return"> Return
	                                	</label>
	                                	<button type="submit" class="btn">Search for Flights</button>
	                            	</div>
	                        	</div>
                    	</form> <!-- Close the form -->
                    
                 	</div><!--/span7-->
                    <div class="span5">
                    	<h2>Promotions</h2>
                        <ul class="thumbnails">
  							<li class="span12">
                            	<div class="thumbnails">
                                    <a href="#" class="thumbnail">
                                      <img src="http://placehold.it/300x200" alt="">
                                    </a>
                                    <div class="captions">
                                    	<h3>Promotions This Month</h3>
                                        <p>This month fly to Antartica one way trip for free</p>
                                    </div><!--captions-->
                                </div> <!--thumbnails-->
                          </li>
                        </ul>
                    </div> <!--span5-->
          		</div><!-- span12 -->     
          </div><!--row-fluid12-->
    	</div><!-- content -->
    
    	<div id="footer">
    		<jsp:include page="/_footer" />
    	</div>
	</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>
