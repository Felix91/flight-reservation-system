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
							<th>Departing Flights</th>
							<th>Arriving Flights</th>
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
							out.println("<td>"+nextAirport.getnumDepartures()+"</td>");
							out.println("<td>"+nextAirport.getnumArrivals()+"</td>");
							out.println("</tr>");
						}
						%>
					</tbody>
				</table>
			</div><!-- span10 -->
		</div><!--/span row-->
	</div><!--/span12-->
</div><!--/row-->
