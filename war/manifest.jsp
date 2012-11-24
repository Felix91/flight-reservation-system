<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<jsp:include page="_head.jsp" />
<script type="text/javascript" src="/js/jquery.tablesorter.js"></script> 
</head>
<body>
	<div class="container">
		<div id="content">
			<div class="row-fluid">
				<div class="span12">
					<div class="row-fluid">
						<div class="span11">
							<h4>Bookings</h4>
							<div class="row-fluid">
								<div class="span12">
									<script type="text/javascript">
									</script>
									<table id="bookingsTable" class="table table-striped">
										<thead>
											<tr>
												<th>Confirmation Number</th>
												<th>PassengerName</th>
												<th>Flight Class</th>
												<th>Booked By</th>
												<th>Seat Number</th>
												<th>Meal choice</th>
												<th>CreditCard</th>
												<th>Checked in</th>
											</tr>
										</thead>
										<tbody>

											<%
												Vector<HashMap> bookings = (Vector) request.getAttribute("bookings");
												for (HashMap<String, Object> booking : bookings) {
											%>
											<tr>
												<td><%=booking.get("confirmNo")%></td>
												<td><%=(String) booking.get("passengerName")%></td>
												<td><%=booking.get("flightClass")%></td>
												<td><%=(String) booking.get("bookedBy")%></td>
												<td><%=booking.get("seat")%></td>
												<td><%=booking.get("meal")%></td>
												<td>**** **** **** <%=booking.get("creditCardNumber")%></td>
												<td><%=((Boolean)booking.get("checkedIn"))?"Yes":"No"%></td>

											</tr>
											<%
												}
											%>

										</tbody>
									</table>
									<script>
										$(function(){
											$('#bookingsTable').tablesorter()
										})
									</script>
								</div>
								<!-- span10 -->
							</div>
							<!--/span row-->
						</div>
						<!--/span12-->

					</div>
					<!--/row-->
				</div>
				<!--/span9-->
			</div>
		</div>
	</div>
</body>
</html>