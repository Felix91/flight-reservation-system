<%@ page import="java.util.*, ufly.entities.FlightBooking, com.google.appengine.api.datastore.Key" %>

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
			        <h2>Customer FlightBookings</h2>
					<div class="row-fluid">
						<div class="span10">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Confirmation Number</th>
										<th>Booked By</th>
										<th>Flight Number</th>
										<th>Flight Class</th>
										<th>Seat</th>
										<th>World Map</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<% 
									Vector<Key> allFlightbookings = (Vector<Key>) request.getAttribute("allFlightbookings");
									
									Iterator<Key> iterator = allFlightbookings.iterator();
									while (iterator.hasNext()) {
										FlightBooking nextFlightbooking = FlightBooking.getFlightBooking(iterator.next());
										if(nextFlightbooking != null){
										out.println("<tr>");
											out.println("<td>"+nextFlightbooking.getConfirmationNumber().getId()+"</td>");
											out.println("<td>"+nextFlightbooking.getBookedBy().getFirstName() + nextFlightbooking.getBookedBy().getLastName() + "</td>");
											out.println("<td>"+nextFlightbooking.getBookedFlight().getFlightNumber()+"</td>");
											out.println("<td>"+nextFlightbooking.getBookedFlightClass().toString()+"</td>");
											out.println("<td>"+nextFlightbooking.getBookedSeat().toString()+"</td>");
								            String originCoor = nextFlightbooking.getBookedFlight().getOrigin().getCoordinates();
											String destinationCoor = nextFlightbooking.getBookedFlight().getDestination().getCoordinates();
											out.println("<td><a href=\"http://maps.google.com/maps?saddr="+originCoor+"&daddr="+destinationCoor+"\" target=\"_blank\" >View on Map</a><td>");
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