<%@ page import="java.util.*, ufly.entities.Flight, ufly.entities.Airport" %>

<div class="row-fluid">
    <div class="span12">
        <h4>Flight Management</h4>
		<div class="row-fluid">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Flight Number</th>
							<th>Origin</th>
							<th>Destination</th>
							<th>Departure Date</th>
							<th>Arrival Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<% 
                    	List<Flight> allFlights = (List<Flight>) request.getAttribute("allFlights");
						
						Iterator<Flight> iterator = allFlights.iterator();
						while (iterator.hasNext()) {
							Flight nextFlight = iterator.next();
							out.println("<tr>");
							out.println("<td>"+nextFlight.getFlightNumber()+"</td>");
							out.println("<td>"+nextFlight.getOrigin().getCity()+"</td>");
							out.println("<td>"+nextFlight.getDestination().getCity()+"</td>");
							out.println("<td>"+nextFlight.getDeparture().toString()+"</td>");
							out.println("<td>"+nextFlight.getArrival().toString()+"</td>");
							out.println("<td><a href=\"/adminProfile/editFlights?flightKey="+ nextFlight.getKey().getName() + "\">Edit</a></td>");
							out.println("</tr>");
						}
						%>
					</tbody>
				</table>
		</div><!--/span row-->
	</div><!--/span12-->
</div><!--/row-->