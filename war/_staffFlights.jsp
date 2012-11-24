<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	SimpleDateFormat fullDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd_HH:mm");
%>
<html>

<body>
	<div class="container">
		<div id="content">
			<div class="row-fluid">
				<div class="span12">
					<div clasfs="row-fluid">
						<div class="span11">
							<h4>Staff Flight Management</h4>
							<div class="row-fluid">
								<div class="span12">
									<div class="controls">
	                               		<input name="departureDate" class="required" type="text" id="flightDate" placeholder="Date To List Flights">
	                               		<script>
	                               			$(function(){
	                                        	$('#flightDate').datepicker({
	       		                                	format: 'mm-dd-yyyy'
	 	                                    	}).on('changeDate', function(ev){								
													window.location = "/flightStaffProfile?Date="+$('#flightDate').val()
												})
	                               			});     
                                        </script>
	                           		</div>
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
												<td><%=flight.get("flightNo")%></td>
												<td><%=(String) flight.get("flightOrigin")%></td>
												<td><%=(String) flight.get("flightDestination")%></td>
												<td><%=dateFormat.format(flight.get("departs"))%></td>
												<td><%=timeFormat.format(flight.get("departs"))%></td>
												<td><%=dateFormat.format(flight.get("arrives"))%></td>
												<td><%=timeFormat.format(flight.get("arrives"))%></td>

												<td><%=flight.get("numBooked").toString()%>/<%=flight.get("capacity").toString()%></td>
												<td>
													<a href="/flightManifest?flightNo=<%=flight.get("flightNo")%>&date=<%=fullDateFormat.format(flight.get("departs"))%>" target="_blank">Manifest</a>
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
