<%@ page import="java.util.*, ufly.entities.Flight" %>




<% 
                    	List<Flight> allFlights = (List<Flight>) request.getAttribute("allFlights");
						
						Iterator<Flight> iterator = allFlights.iterator();
						while (iterator.hasNext()) {
							out.println(iterator.next().getFlightNumber());
						}
%>

<div class="row-fluid">
    <div class="span12">
        <h4>Flight Management</h4>
		<div class="row-fluid">
			<div class="span12">
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
						<tr>
							<td>1</td>
							<td>Vancouver</td>
							<td>Toronto</td>
							<td>Date</td>
							<td>Date</td>
							<td><a href="#">View</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td>Vancouver</td>
							<td>Toronto</td>
							<td>Date</td>
							<td>Date</td>
							<td><a href="#">View</a></td>
						</tr>
					</tbody>
				</table>
			</div><!-- span10 -->
		</div><!--/span row-->
	</div><!--/span12-->
</div><!--/row-->