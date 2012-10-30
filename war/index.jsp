<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="_head.jsp" />
</head>
<body>
	<jsp:include page="/navBar.jsp" />
	<div class="container-fluid">
		<div id="content">
			<div class="row-fluid">
   				<div class="span12">
              		<div class="hero-unit">
                		<h1>Hello, world!</h1>
                		<p>This is a template for a simple marketing or informational website.</p>
              		</div>
          			<div class="row-fluid">
                    	<div class="span7">
                            <h2>Searh for Flights</h2>
                      		<form class="form-horizontal" action="/searchResults.jsp" method="post">
	                        	<div class="control-group">
	                            	<label class="control-label" for="in_from">Leaving From</label>
	                            	<div class="controls">
	                                	<input type="text" id="in_from" placeholder="Enter your Departure City">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label" for="in_to">Leaving To</label>
	                            	<div class="controls">
	                                	<input type="text" id="in_to" placeholder="Enter your Destination City">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label" for="in_depart">Date of Departure</label>
	                            	<div class="controls">
	                               		<input type="text" id="in_depart" placeholder="Enter Date of Departure">
	                           		</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label" for="in_return">Date of Return</label>
	                            	<div class="controls">
	                                	<input type="text" id="in_return" placeholder="Enter Date of Return">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<label class="control-label" for="in_psgr">Number of Passangers</label>
	                            	<div class="controls">
	                                	<input type="text" id="in_psgr" placeholder="Number of Passangers">
	                            	</div>
	                        	</div>
	                        	<div class="control-group">
	                            	<div class="controls">
	                                	<label class="radio">
	                                    	<input type="radio" name="flightOpt" value="opt1"> Direct Flight
	                                	</label>
	                                	<label class="radio">
	                                    	<input type="radio" name="flightOpt" value="opt2"> Connecting Flight
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
    		<jsp:include page="/_footer.jsp" />
    	</div>
	</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>
