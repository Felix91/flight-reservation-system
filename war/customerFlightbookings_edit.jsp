<%@ page import="java.util.*,ufly.entities.Airport"%>

<html>
<head>
<jsp:include page="/_header" />
<script type="text/javascript" src="/js/jquery.qrcode.min.js"></script>
<script>
	$(function() {
		$("#editMeal").click(function() {
			$('#mealEditDialog').dialog()
		})
		$("#editSeat").click(function() {
			$('#seatEditDialog').dialog({
				modal : true,
				minWidth : 1000,
				minHeight : 500
			})
		})
		$('#mealSelect').change(
				function() {
					$.ajax(
							{
								url : '/doAjax?ajaxCall=modifyMeal&meal='
										+ $('#mealSelect').val()
										+ "&bookingNumber="
										+ $('#confirmNo').val()
							}).done(function() {
						$("#mealName").html($('#mealSelect').val())
						$("#mealSelect").val("")
						$('#mealEditDialog').dialog('close')
					})

				})

	})
	function seatSelect(row, col) {
		$.ajax(
				{
					url : '/doAjax?ajaxCall=modifySeat&' + 'seat=' + row + col
							+ '&bookingNumber=' + $('#confirmNo').val()
				}).done(function() {
			$('#seatName').html(row + col);
			$('#seatEditDialog').dialog('close')
		})
	}
</script>
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content">
			<!-- start content -->
			<h3>Customer Flightbookings - modify Flightbooking</h3>
			<div class="row-fluid">
				<div class="span12">
					<div class="row-fluid">
						<%
							HashMap<String, Object> booking = (HashMap) request
									.getAttribute("editFlightbooking");
						%>
						<dl class="dl-horizontal control-group">
							<dt>Confirmation Number</dt>
							<dd><%=booking.get("confirmNo")%></dd>
							<input type="hidden" id="confirmNo"
								value="<%=booking.get("confirmNo")%>">
						</dl>
						<dl class="dl-horizontal control-group">
							<dt>Passenger Name</dt>
							<dd><%=booking.get("passengerName")%></dd>
						</dl>
						<dl class="dl-horizontal control-group">
							<dt>Flight Class</dt>
							<dd><%=booking.get("flightClass")%></dd>
						</dl>
						<dl class="dl-horizontal control-group">
							<dt>Departure</dt>
							<dd><%=((Airport) booking.get("origin")).getCity()%>
								(<%=((Airport) booking.get("origin")).getCallSign()%>)
							</dd>
						</dl>
						<dl class="dl-horizontal control-group">
							<dt>Arrival</dt>
							<dd><%=((Airport) booking.get("destination")).getCity()%>
								(<%=((Airport) booking.get("destination")).getCallSign()%>)
							</dd>
						</dl>
						<dl class="dl-horizontal control-group">
							<dt>Meal</dt>
							<dd>
								<span id="mealName"><%=booking.get("meal")%> </span><a href=#
									id="editMeal"> edit</a>
							</dd>
						</dl>
						<dl class="dl-horizontal control-group">
							<dt>Seat Number</dt>
							<dd>
								<span id="seatName"><%=booking.get("seat")%></span> <a href=#
									id="editSeat"> edit</a>
							</dd>
						</dl>
					</div>
				</div>
			</div>
		</div>
		<div id="mealEditDialog" style="display: none">
			<h3>Select Meal</h3>
			<select id="mealSelect">
				<option></option>
				<%
					for (Object m : (Vector) request.getAttribute("meals")) {
				%>
				<option><%=m%></option>
				<%
					}
				%>
			</select>
		</div>
		<div id="seatEditDialog" style="display: none">
			<div class="row-fluid">
				<div class="span12">
					<h2>Seat Selections</h2>
					<button class="btn btn-mini " type="button">&nbsp;</button>
					Current Seat
					<button class="btn btn-mini btn-primary" type="button">&nbsp;</button>
					First Class
					<button class="btn btn-mini btn-info" type="button">&nbsp;</button>
					Business Class
					<button class="btn btn-mini btn-success" type="button">&nbsp;</button>
					Economy Class
					<button class="btn btn-mini btn-success disabled" type="button">&nbsp;</button>
					Unavailable Seat
					<hr />
					<table class="table table-bordered table-condensed">
						<%
							HashMap<String, Object> flight = (HashMap) request
									.getAttribute("flight");
							int numRows = (Integer) ((HashMap) flight.get("seatingArrangement"))
									.get("numRows");
							int numCols = (Integer) ((HashMap) flight.get("seatingArrangement"))
									.get("numColumns");
							int numFirstClass = (Integer) ((HashMap) flight
									.get("seatingArrangement")).get("numRowsFirstClass");
							int numBusinessClass = (Integer) ((HashMap) flight
									.get("seatingArrangement")).get("numRowsBusinessClass");
							int numEconomyClass = (Integer) ((HashMap) flight
									.get("seatingArrangement")).get("numRowsEconomyClass");
							List availableSeats = Arrays.asList((String[]) flight
									.get("availableSeats"));
						%>
						<thead>
							<tr>
								<th>#</th>
								<%
									for (int row = 1; row <= numRows; row++) {
								%>
								<th><%=row%></th>
								<%
									}
								%>
							</tr>
						</thead>
						<tbody>
							<%
								char a = (char) numCols;
								a += 'A';
								for (Character Col = 'A'; Col < a; Col++) {
							%>
							<tr>
								<th><%=Col%></th>
								<%
									for (Integer row = 1; row <= numRows; row++) {
											String flightClass;
											if (row <= numFirstClass) {
												flightClass = "btn-primary";
											} else if (row <= (numFirstClass + numBusinessClass)) {
												flightClass = "btn-info";
											} else {
												flightClass = "btn-success";
											}
											if (booking.get("seat").equals(row.toString() + Col)) {
												flightClass = "";
											}
								%>
								<td>
									<button
										class="btn btn-mini <%=flightClass%>
												  		<%if (availableSeats.indexOf(row.toString() + " " + Col) < 0
							&& !booking.get("seat")
									.equals(row.toString() + Col)) {%>disabled"
										<%}else{ %>" onclick="seatSelect('<%=row%>','<%=Col%>')" <%} %>
										type="button" 
										value="<%=row%> <%=Col%>">&nbsp;</button>
								</td>
								<%
									}
								%>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>