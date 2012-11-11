<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="/_header" />
	
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<h3>Flight Management - Add Flightbookings</h3>
			<div class="row-fluid">
    			<div class="span12">
        			<div class="row-fluid">
	        			<form action = "/flightbookingTest" method="post">
	        				<div class="control-group">
		                    	<label class="control-label span2" for="bookedBy">bookedBy</label>
								<input type="text" name="bookedBy">
							</div>
							<div class="control-group">
		                    	<label class="control-label span2" for="bookedFlight">bookedFlight</label>
		                    	<input type="text" name="bookedFlight">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span2" for="bookedFlightClass">bookedFlightClass</label>
		                    	<input type="text" name="bookedFlightClass">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span2" for="bookedSeat">bookedSeat</label>
		                    	<input type="text" name="bookedSeat">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span2" for="mealChoice">mealChoice</label>
		                    	<input type="text" name="mealChoice">
		                    </div>
							<input type="submit" value="Submit">
						</form>
					</div><!--/span row-->
				</div><!--/span12-->
			</div><!--/row-->
    	</div><!-- end content -->
		    
    	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer" />
    	</div><!-- end footer -->
	</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>