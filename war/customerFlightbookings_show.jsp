<%@ page import="java.util.*, ufly.entities.FlightBooking" %>
<%@ page import="java.text.SimpleDateFormat"%>

<html>
<head>	
	<jsp:include page="/_header" />
	<script type="text/javascript" src="/js/jquery.qrcode.min.js"></script>
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<h3>Customer Flightbookings - show Flight </h3>
			<%FlightBooking showFlightbooking = (FlightBooking) request.getAttribute("showFlightbooking");
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy HH:mm");%>
			<div class="row-fluid">
    			<div class="span12">
        			<div class="row-fluid">
        				<dl class="dl-horizontal">
        					<dt>Confirmation Number</dt>
        					<dd><%=showFlightbooking.getConfirmationNumber().getId() %></dd>
        					<dt>Flight Number</dt>
        					<dd><%=showFlightbooking.getBookedFlight().getFlightNumber() %></dd>
        					<dt>Departure Date</dt>
        					<dd><%=dateFormat.format(showFlightbooking.getBookedFlight().getDeparture()) %></dd>
        					<dt>Arrival Date</dt>
        					<dd><%=dateFormat.format(showFlightbooking.getBookedFlight().getArrival()) %></dd>
        					<dt>Origin</dt>
        					<dd><%=showFlightbooking.getBookedFlight().getOrigin().getCity()+" ("+showFlightbooking.getBookedFlight().getOrigin().getCallSign()+")" %></dd>
        					<dt>Destination</dt>
        					<dd><%=showFlightbooking.getBookedFlight().getDestination().getCity()+" ("+showFlightbooking.getBookedFlight().getDestination().getCallSign()+")" %></dd>
        					<dt>Passenger Name</dt>
        					<dd><%=showFlightbooking.getPassengerName() %></dd>
        					<dt>Flight Class</dt>
        					<dd><%=showFlightbooking.getBookedFlightClass() %></dd>
        					<dt>Seat</dt>
        					<dd><%=showFlightbooking.getBookedSeat() %></dd>
        					<dt>Meal</dt>
        					<dd><%=showFlightbooking.getMealChoice() %></dd>
        					<dt>Price In Dollars</dt>
        					<dd><%=showFlightbooking.getBookedFlight().getPriceString() %></dd>
        					<dt>Credit Card</dt>
        					<dd>**** **** **** <%=showFlightbooking.getCreditCardNumber() %></dd>
        				</dl>
						<%
						// if customer has not checked in yet
						if(request.getAttribute("showFlightbooking") != null){
							showFlightbooking = (FlightBooking) request.getAttribute("showFlightbooking");
							
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
										out.print("<div id=\"output\"></div>");
										out.print("<script>jQuery(function(){ jQuery('#output').qrcode( {width: 140,height: 140,text: \""+ request.getScheme()+ "://" + request.getServerName() + ":" + request.getServerPort() + request.getAttribute("javax.servlet.forward.request_uri") + "?" + request.getAttribute("javax.servlet.forward.query_string") + "\"}); })</script>");
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