<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="/_header" />
	
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<h3>Flight Management - Add Airports</h3>
			<div class="row-fluid">
    			<div class="span12">
        			<div class="row-fluid">
	        			<form action = "/airportTest" method="post">
	        				<div class="control-group">
		                    	<label class="control-label span2" for="city">city</label>
								<input type="text" name="bookedBy">
							</div>
							<div class="control-group">
		                    	<label class="control-label span2" for="Callsign">Callsign</label>
		                    	<input type="text" name="Callsign">
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