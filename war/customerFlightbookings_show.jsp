<%@ page import="java.util.*, ufly.entities.FlightBooking" %>

<html>
<head>	
	<jsp:include page="/_header" />
	
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<h3>Customer Flightbookings - show Flight 
			</h3>
			<div class="row-fluid">
    			<div class="span12">
        			<div class="row-fluid">
	        			<%
							if(request.getAttribute("showFlightbooking") != null){
								FlightBooking showFlightbooking = (FlightBooking) request.getAttribute("showFlightbooking");
								out.print("<div class=\"dl-horizontal\">");
	        						out.print("<div class=\"control-group\">");
		                    			out.print("<dt>flightNumber</dt>");
										out.print("<dd>"+showFlightbooking.getConfirmationNumber().getId()+"</dd>");
									out.print("</div>");
									out.print("<div class=\"control-group\">");
		                    			out.print("<dt>Passenger Name</dt>");
										out.print("<dd>"+showFlightbooking.getPassengerName()+"</dd>");
									out.print("</div>");
									out.print("<div class=\"control-group\">");
		                    			out.print("<dt>Travel Date</dt>");
										out.print("<dd>"+showFlightbooking.getBookedFlight().getDeparture().toString()+"</dd>");
									out.print("</div>");
									out.print("<div class=\"control-group\">");
		                    			out.print("<dt>Departure</dt>");
										out.print("<dd>"+showFlightbooking.getBookedFlight().getOrigin().getCity()+"</dd>");
									out.print("</div>");
									out.print("<div class=\"control-group\">");
		                    			out.print("<dt>Arrival</dt>");
										out.print("<dd>"+showFlightbooking.getBookedFlight().getDestination().getCity()+"</dd>");
									out.print("</div>");
									out.print("<div class=\"control-group\">");
		                    			out.print("<dt>Seat Number</dt>");
										out.print("<dd>"+showFlightbooking.getBookedSeat().getRowNumber()+ showFlightbooking.getBookedSeat().getColumn()+"</dd>");
									out.print("</div>");
		                		out.print("</div>");
							}
						%>
						<%
						// if customer has not checked in yet
						if(request.getAttribute("showFlightbooking") != null){
							FlightBooking showFlightbooking = (FlightBooking) request.getAttribute("showFlightbooking");
							
							if(!showFlightbooking.getCheckedIn() ){
								out.print("<form action = \"/customerProfile/checkinFlightbookings\" method=\"post\">");
								out.print("<input type=\"submit\" value=\"Check In\">");
								out.print("<input type=\"hidden\" name=\"confirmationNumber\" value=\"" + showFlightbooking.getConfirmationNumber().getId() +  "\">");
								out.print("</form>");
							}else{
								// else show manifest details
								out.print("<H3>Checked In - Boarding Pass</H3>");
								out.print("<div class=\"row-fluid\">");
									out.print("<div class=\"span2\">");
										out.print("<img src=\"/img/qrcode.png\">");
									out.print("</div>");
									out.print("<div class=\"span6\" style=\"background-color:lightgrey\">");
										out.print("<div class=\"row-fluid\">");
											out.print("<div class=\"span6\">");
												out.print("<B>GATE</B>: 5D");
											out.print("</div>");
											out.print("<div class=\"span6\">");
												out.print("<B>SEAT</B>: " + showFlightbooking.getBookedSeat().getRowNumber()+ showFlightbooking.getBookedSeat().getColumn());
											out.print("</div>");
										out.print("</div>");
										out.print("<div class=\"row-fluid\">");
											out.print("<div class=\"span8\">");
												out.print("<B>PASSENGER NAME:</B> " +showFlightbooking.getPassengerName());
											out.print("</div>");
										out.print("</div>");
										out.print("<div class=\"row-fluid\">");
											out.print("<div class=\"span6\">");
												out.print("<B>TO: </B>" + showFlightbooking.getBookedFlight().getOrigin().getCity());
											out.print("</div>");
											out.print("<div class=\"span6\">");
												out.print("<B>FROM: </B>" + showFlightbooking.getBookedFlight().getDestination().getCity());
											out.print("</div>");
										out.print("</div>");
									out.print("</div>");
									out.print("<div class=\"span4\" style=\"background-color:lightblue\">");
										out.print("<div class=\"row-fluid\">");
											out.print("<B>NAME: </B>" + showFlightbooking.getPassengerName());
										out.print("</div>");
										out.print("<div class=\"row-fluid\">");
											out.print("<B>TO: </B>" + showFlightbooking.getBookedFlight().getOrigin().getCity());
										out.print("</div>");
										out.print("<div class=\"row-fluid\">");
											out.print("<B>FROM: </B> " +showFlightbooking.getBookedFlight().getDestination().getCity() );
										out.print("</div>");
										out.print("<div class=\"row-fluid\">");
											out.print("<div class=\"span6\">");
												out.print("<B>GATE</B> 5D");
											out.print("</div>");
											out.print("<div class=\"span6\">");
												out.print("<B>SEAT</B> " + showFlightbooking.getBookedSeat().getRowNumber()+ showFlightbooking.getBookedSeat().getColumn() );
											out.print("</div>");
										out.print("</div>");
									out.print("</div>");
								out.print("</div>");
							}
						
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