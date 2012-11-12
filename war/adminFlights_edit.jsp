<%@ page import="java.util.*, ufly.entities.Flight" %>

<html>
<head>	
	<jsp:include page="/_header" />
	
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<h3>Flight Management - Edit Flight ###</h3>
			<div class="row-fluid">
    			<div class="span12">
        			<div class="row-fluid">
	        			<form action = "/flightTest" method="post">
	        				<div class="control-group">
		                    	<label class="control-label span2" for="flightNumber">flightNumber</label>
								<input type="text" name="flightNumber">
							</div>
							<div class="control-group">
		                    	<label class="control-label span2" for="origin">origin</label>
		                    	<input type="text" name="origin">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span2" for="destination">destination</label>
		                    	<input type="text" name="destination">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span2" for="departure">departure</label>
		                    	<input type="text" name="departure">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span2" for="arrival">arrival</label>
		                    	<input type="text" name="arrival">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span3" for="allowableMealTypes">allowableMealTypes</label>
		                    	<input type="text" name="allowableMealTypes">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span3" for="seatingArrangementLayout">seatingArrangementLayout</label>
		                    	<input type="text" name="seatingArrangementLayout">
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