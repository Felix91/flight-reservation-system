<%@ page import="java.util.*, ufly.entities.FlightBooking, com.google.appengine.api.datastore.Key" %>
<%@ page import="java.text.SimpleDateFormat"%>

<html>
<head>	
	<jsp:include page="/_header" />
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<div class="row-fluid">
			    <div class="span12">
			        <h2>Customer Flight Bookings</h2>
					<div class="row-fluid">
						<div class="span12">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Confirmation Number</th>
										<th>Flight Number</th>
										<th>Departure Date</th>
										<th>Origin</th>
										<th>Destination</th>
										<th>Passenger Name</th>
										<th>Map</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<% 
									Vector<Key> allFlightbookings = (Vector<Key>) request.getAttribute("allFlightbookings");
									SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy HH:mm");
									Iterator<Key> iterator = allFlightbookings.iterator();
									while (iterator.hasNext()) {
										FlightBooking nextFlightbooking = FlightBooking.getFlightBooking(iterator.next());
										if(nextFlightbooking != null){
										out.println("<tr>");
											out.println("<td>"+nextFlightbooking.getConfirmationNumber().getId()+"</td>");
											out.println("<td>"+nextFlightbooking.getBookedFlight().getFlightNumber()+"</td>");
											out.println("<td>"+dateFormat.format(nextFlightbooking.getBookedFlight().getDeparture())+"</td>");
											out.println("<td>"+nextFlightbooking.getBookedFlight().getOrigin().getCity()+" ("+nextFlightbooking.getBookedFlight().getOrigin().getCallSign()+")"+"</td>");
											out.println("<td>"+nextFlightbooking.getBookedFlight().getDestination().getCity()+" ("+nextFlightbooking.getBookedFlight().getDestination().getCallSign()+")"+"</td>");
											out.println("<td>"+nextFlightbooking.getPassengerName()+ "</td>");
											
											String originCoor = nextFlightbooking.getBookedFlight().getOrigin().getCoordinates();
											String destinationCoor = nextFlightbooking.getBookedFlight().getDestination().getCoordinates();
											out.println("<td><a href=\"http://maps.google.com/maps?saddr="+originCoor+"&daddr="+destinationCoor+"\" target=\"_blank\" >View on Map</a></td>");
											out.println("<td>");
												out.println("<a href=\"/customerProfile/showFlightbookings?confirmationNumber="+ nextFlightbooking.getConfirmationNumber().getId() + "\">Show</a>");
												if(!nextFlightbooking.getCheckedIn() ){
													out.println("<BR><a href=\"/customerProfile/editFlightbookings?confirmationNumber="+ nextFlightbooking.getConfirmationNumber().getId() + "\">Modify</a>");
													out.println("<BR><a href=\"/customerProfile/deleteFlightbookings?confirmationNumber="+ nextFlightbooking.getConfirmationNumber().getId() + "\">Delete</a>");
												
												}
											out.println("</td>");
										out.println("</tr>");
										}
									}
									%>
								</tbody>
							</table>
						</div><!-- span10 -->
					</div><!--/span row-->
				</div><!--/span12-->
			</div><!--/row-->
    	</div><!-- end content -->
    
    	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer" />
    	</div><!-- end footer -->
	</div>
</body>
</html>