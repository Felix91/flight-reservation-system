<%@ page import="java.util.*, ufly.entities.FlightBooking, ufly.entities.FlightClass, ufly.entities.Seat" %>


<div class="row-fluid">
    <div class="span12">
        <h2>Admin FlightBookings Management</h2>
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
						</tr>
					</thead>
					<tbody>
						<% 
                    	List<FlightBooking> allFlightbookings = (List<FlightBooking>) request.getAttribute("allFlightbookings");
						
						Iterator<FlightBooking> iterator = allFlightbookings.iterator();
						while (iterator.hasNext()) {
							FlightBooking nextFlightbooking = iterator.next();
							out.println("<tr>");
							out.println("<td>"+nextFlightbooking.getConfirmationNumber().getId()+"</td>");
							out.println("<td>"+nextFlightbooking.getBookedBy().getFirstName() + " " + nextFlightbooking.getBookedBy().getLastName()+"</td>");
							out.println("<td>"+nextFlightbooking.getBookedFlight().getFlightNumber()+"</td>");
							out.println("<td>"+nextFlightbooking.getBookedFlightClass().toString()+"</td>");
							out.println("<td>"+nextFlightbooking.getBookedSeat().getRowNumber()+nextFlightbooking.getBookedSeat().getColumn() +"</td>");
							out.println("</tr>");
						}
						%>
					</tbody>
				</table>
			</div><!-- span10 -->
		</div><!--/span row-->
	</div><!--/span12-->
</div><!--/row-->