<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
%>
<html>

<body>
	<div class="container">
		<div id="content">
			<div class="row-fluid">
				<div class="span12">
					<div class="row-fluid">
						<div class="span11">
							<h4>Staff Flight Management</h4>
							<div class="row-fluid">
								<div class="span12">
									<script type="text/javascript">
										function openManifest(date,flightNum){
											newwindow=window.open("/flightmanifest.jsp","Manifest");
										}
										
									</script>
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Flight No.</th>
												<th>Origin</th>
												<th>Destination</th>
												<th>Departure Date</th>
												<th>Departure Time</th>
												<th>Arrival Date</th>
												<th>Arrival Time</th>
												<th>Seats Booked</th>
												<th>View</th>
											</tr>
										</thead>
										<tbody>

											<%
												Vector<HashMap> flights = (Vector) request.getAttribute("flights");
												for (HashMap<String, Object> flight : flights) {
											%>
											<tr>
												<td>1</td>
												<td><%=(String) flight.get("flightOrigin")%></td>
												<td><%=(String) flight.get("flightDestination")%></td>
												<td><%=dateFormat.format(flight.get("departs"))%></td>
												<td><%=timeFormat.format(flight.get("departs"))%></td>
												<td><%=dateFormat.format(flight.get("arrives"))%></td>
												<td><%=timeFormat.format(flight.get("arrives"))%></td>

												<td><%=flight.get("numBooked").toString()%>/<%=flight.get("capacity").toString()%></td>
												<td>

													<button></button> <a href="/flightmanifest.jsp">Manifest</a>
												</td>
											</tr>
											<%
												}
											%>

										</tbody>
									</table>
								</div>
								<!-- span10 -->
							</div>
							<!--/span row-->
						</div>
						<!--/span12-->
						<div class="span1">
							<a href="#">Logout</a>
						</div>
					</div>
					<!--/row-->
				</div>
				<!--/span9-->
			</div>
		</div>
	</div>
	<!-- page generated at: <%//out.print(request.getAttribute("date"));%>-->
</body>
</html>
