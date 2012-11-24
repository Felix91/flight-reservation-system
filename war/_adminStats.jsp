<%@ page import="java.util.*, ufly.entities.Airport" %>

<div class="row-fluid">
    <div class="span12">
        <h4>Airport Statistics</h4>
        <div class="row-fluid">
			<div class="span12">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Airport</th>
							<th>Passengers Departing</th>
							<th>Passengers Arriving</th>
						</tr>
					</thead>
					<tbody>
						<% 
						List<Airport> airports =  (List<Airport>) request.getAttribute("allAirports");
						
						Iterator<Airport> it = airports.iterator();
						while (it.hasNext())
						{
							Airport nextAirport = it.next();
								out.println("<tr>");
								out.println("<td>"+nextAirport.getCity()+"</td>");
								out.println("<td>"+nextAirport.getnumPassengersDeparting()+"</td>");
								out.println("<td>"+nextAirport.getnumPassengersArriving()+"</td>");
								out.println("</tr>");
						}
						%>
					</tbody>
				</table>
				<BR><a href="/adminAirportStats.jsp" target="_blank">View Graph</a>
        	</div><!-- span10 -->
		</div><!--/span row-->
	</div><!--/span12-->
</div><!--/row-->
