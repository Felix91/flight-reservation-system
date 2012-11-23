<%@ page import="java.util.*,ufly.entities.Airport" %>

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
										<th>Booked By</th>
										<th>Flight Number</th>
										<th>Passenger Name</th>
										<th>Flight Class</th>
										<th>Seat</th>
										<th>Checked In</th>
										<th>World Map</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<%
										Vector<HashMap> bookings = (Vector) request
												.getAttribute("bookings");
										for (HashMap<String, Object> booking : bookings) {
									%>
									<tr>
										<td><%=booking.get("confirmNo")%></td>
										<td><%=(String) booking.get("bookedBy")%></td>
										<td>flightNo</td>
										<td><%=(String) booking.get("passengerName")%></td>
										
										<td><%=booking.get("flightClass")%></td>
										
										<td><%=booking.get("seat")%></td>
										<td><%=booking.get("creditCardNumber")%></td>
										<td><%=booking.get("seat")%></td>
										<td><%=((Boolean) booking.get("checkedIn")) ? "Yes" : "No"%></td>
										<td>
										<%
											//HACK, this should not access Entity directly
												Airport origin = (Airport) booking.get("origin");
												Airport dest = (Airport) booking.get("dest");
										%>
										<a href="http://maps.google.com/maps?saddr=<%=origin.getCoordinates()%>&daddr=<%=dest.getCoordinates()%>" target="_blank" >View on Map</a>
										</td>
										<td><a
											href="/customerProfile/showFlightbookings?confirmationNumber=<%=booking.get("confirmNo")%>">Show</a>
											<BR> <a
											href="/customerProfile/editFlightbookings?confirmationNumber=<%=booking.get("confirmNo")%>">Modify</a>
											<BR> <a
											href="/customerProfile/deleteFlightbookings?confirmationNumber=<%=booking.get("confirmNo")%>">Delete</a>

										</td>
									</tr>
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