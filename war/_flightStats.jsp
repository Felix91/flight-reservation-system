<%@ page import="java.util.*, ufly.entities.Flight" %>

<div class="row-fluid">
    <div class="span12">
        <h4>Flight Statistics</h4>
		<div class="row-fluid">
			<div class="span12">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Flight</th>
							<th>Average Booked Flights</th>
						</tr>
					</thead>
					<tbody>
						<% 
						Set<String> flightnumber =  (Set<String>) request.getAttribute("flightnumber");	
						Iterator<String> it = flightnumber.iterator();
						while (it.hasNext())
						{
							String nextflightnumber = it.next();
							int avg = Flight.getBookedFlightsByFlightNum(nextflightnumber)/Flight.getTotalFlightsByFlightNum(nextflightnumber);
							out.println("<tr>");
							out.println("<td>"+nextflightnumber+"</td>");
							out.println("<td>"+avg+"</td>");
							out.println("</tr>");
						}
						%>
					</tbody>
				</table>
			</div><!-- span10 -->
		</div><!--/span row-->
	</div><!--/span12-->
</div><!--/row-->
