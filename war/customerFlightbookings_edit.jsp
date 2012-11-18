<%@ page import="java.util.*, ufly.entities.FlightBooking" %>

<html>
<head>	
	<jsp:include page="/_header" />
	
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<h3>Customer Flightbookings - edit Flight 
			</h3>
			<div class="row-fluid">
    			<div class="span12">
        			<div class="row-fluid">
	        			<%
							if(request.getAttribute("editFlightbooking") != null){
								FlightBooking editFlightbooking = (FlightBooking) request.getAttribute("editFlightbooking");
								out.print("<div class=\"dl-horizontal\">");
	        						out.print("<div class=\"control-group\">");
		                    			out.print("<dt>flightNumber</dt>");
										out.print("<dd>"+editFlightbooking.getConfirmationNumber().getId()+"</dd>");
									out.print("</div>");
									out.print("<div class=\"control-group\">");
		                    			out.print("<dt>Passenger Name</dt>");
										out.print("<dd>"+editFlightbooking.getConfirmationNumber().getId()+"</dd>");
									out.print("</div>");
		                		out.print("</div>");
							}
						%>
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