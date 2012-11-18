<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<html>
<head>
<jsp:include page="/_header" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<script>

		function openDialog(paxNo,flightNo){
			$('#Flight'+paxNo+'_'+flightNo).dialog('open')
		}
		function seatSelect(seat,flight,pass){
			seatName="Seat"+seat+"Flight"+flight
			$('[name="'+seatName+'"]').addClass('disabled').attr("onclick","");
			
			seatInputId="Seat"+pass+"_"+flight
			$('#'+seatInputId).val(seat)
			$('label[for="'+seatInputId+'"]').html(seat);
			$('#Flight'+pass+'_'+flight).dialog('close')
			
			$('[name="'+"SeatButton"+pass+"_"+flight+'"]').addClass('disabled')
			.attr("onclick","");
		}
		function onFormSubmit()
		{
			var passengerNameFilled=true;
			$("[name^=passengerName]").each(function(index){
				if(passengerNameFilled){
					passengerNameFilled=$(this).val()!=""
				}
			})
			if(!passengerNameFilled){
				$("#requiredError").html("Make sure all passengers are named")
				return false
			}
			var seatSelected=true;
			$('input[name^=Seat]').each(function(){
				if(seatSelected){
					seatSelected=$(this).val()!=""
				}
				
			})
			if(!seatSelected){
				$("#requiredError").html("Select a seat for all flights")
				return false
			}
			return true;
		}
		$(function(){
			$('form').submit(onFormSubmit)
			$('.disabled').attr("onclick","")
			//for safety:
			$('input[name^=Seat]').val("")
	
		})
	</script>
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div class="row-fluid">
		<form action="/createBooking" method=Post>
			<div class="span12">
				<h2>Booking Confirmation</h2>
				<%
					SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");
					SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
					SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
					Vector<HashMap> Flights = (Vector) request
							.getAttribute("flightInfo");
				%>
				<input type="hidden" name="numberOfFlights" value="<%=Flights.size()%>">
				<input type="hidden" name="numberOfPassengers" value="<%=(Integer) request
							.getAttribute("numPassengers")%>">
				<%
					for (int numPax = 0; numPax < (Integer) request
							.getAttribute("numPassengers"); numPax++) {
				%>
				Passenger<%=numPax+1%>
				<div class="row-fluid">
					<%
						
							for (int numFlight = 0; numFlight < Flights.size(); numFlight++) {
								HashMap<String, Object> flight = Flights.get(numFlight);
					%>
					<div class="span4 well" style="min-height: 400px;">
						<h4>Departure Flight Options</h4>
						<input type="hidden" name="FlightNo<%=numPax%>_<%=numFlight%>" value="<%=(String) flight.get("flightNo") %>">
						<input type="hidden" name="Date<%=numPax%>_<%=numFlight%>" value="<%=fullDateFormat.format((Date)flight.get("departs")) %>">
						<input type="hidden" id="Seat<%=numPax%>_<%=numFlight%>" name="Seat<%=numPax%>_<%=numFlight%>" >
						<dl>
							<dt>Flight Number</dt>
							<dd>
								<%
									out.print((String) flight.get("flightNo"));
								%>
							</dd>
							<dt>Origin</dt>
							<dd>
								<%
									out.print((String) flight.get("flightOrigin"));
								%>
							</dd>
							<dt>Destination</dt>
							<dd>
								<%
									out.print((String) flight.get("flightDestination"));
								%>
							</dd>
							<dt>Departure Date</dt>
							<dd><%=dateFormat.format((Date) flight.get("departs"))%></dd>
							<dt>Departure Time</dt>
							<dd><%=timeFormat.format((Date) flight.get("departs"))%></dd>
							<dt>Arrival Time (destination time)</dt>
							<dd><%=timeFormat.format((Date) flight.get("arrives"))%></dd>
							<dt >Meal Preference</dt>
							<dd>
						    	<select name="meal<%=numPax%>_<%=numFlight%>">
						    	<%
						    		String[] allowableMeals=((String)flight.get("allowableMeals")).split("\\|");	
						    		for(int j=0;j<allowableMeals.length;j++)
						    		{
						    			if(!allowableMeals[j].equals(""))
						    			{
						    	%>
						    		<option value="<%=allowableMeals[j]%>"><%=allowableMeals[j]%></option>
						    	<%
						    			}
						    		}
						    	%>
						    	</select>
							</dd>
							<dt>Seat Choice</dt>
							<dd>
							<label for="Seat<%=numPax%>_<%=numFlight%>">Please Choose a Seat </label>
								<button type="button" name="SeatButton<%=numPax%>_<%=numFlight%>" class="btn btn-primary"
									onclick='openDialog(<%=numPax%>,<%=numFlight%>)'>Display
									Seats</button>
							</dd>

						</dl>
					</div>

					<%
						}//foreach flight
					%>

					<div class="span4 well" style="min-height: 400px;">
						<h4>In-Flight Options</h4>
						<input type="hidden" name="seat" value="">
						<dl>
							<dt>Passenger Name</dt>
							<dd><input name=passengerName<%=numPax %>> </dd>
							
						</dl>
					</div>
					<!-- span 4 flight opt -->
				</div>
				<!-- rowfluid -->
				<%
					}//for numPax
				%>

				</div>
				<div id=PaymentInfo>
					Total Cost= <%=request.getAttribute("TotalCostString") %><br>
					User has $<%=((Integer)request.getAttribute("loyaltyPoints"))/10 %> worth of loyalty points how much would you like to use
					<label>
						Loyalty points:
						<input type="text" name=loyaltyPointsUsed placeholder="Loyalty Points">
					</label>
					<label>
						Credit Card:
						<input type="text" name=CreditCard placeholder="Credit Card #">	
					</label>
					<label>
						Expiration Date:
						<input name="ccExpDate" placeholder="Expiration date">  
					</label>
					<label>
						Card holder:
						<input name="cardHolder" placeholder="Cardholder Name">
					</label>
					<label>
						Address Line1:
						<input name="addrLine1" placeholder="address line 1">
					</label>
					<label>
						Address Line2:
						<input name="addrLine2" placeholder="address line 2">
					</label>
				</div>
				<div id=requiredError class="text-required"></div>
				<input type=submit value=submit>
			</form>
			</div>
			<!-- row fluid -->
			<div id=SeatSelectHiddenDiv>
			<%
				for (int numPax = 0; numPax < (Integer) request
							.getAttribute("numPassengers"); numPax++) {
							for (int numFlight = 0; numFlight < Flights.size(); numFlight++) {
								HashMap<String, Object> flight = Flights.get(numFlight);
					%>
										<div id="Flight<%=numPax%>_<%=numFlight%>"
							class="container-fluid seatDialog">
							<div class="row-fluid">
								<div class="span12">
									<h2>Seat Selections</h2>
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
											int numRows = (Integer) ((HashMap) flight
															.get("seatingArrangement")).get("numRows");
													int numCols = (Integer) ((HashMap) flight
															.get("seatingArrangement")).get("numColumns");
													int numFirstClass = (Integer) ((HashMap) flight
															.get("seatingArrangement"))
															.get("numRowsFirstClass");
													int numBusinessClass = (Integer) ((HashMap) flight
															.get("seatingArrangement"))
															.get("numRowsBusinessClass");
													int numEconomyClass = (Integer) ((HashMap) flight
															.get("seatingArrangement"))
															.get("numRowsEconomyClass");

													List availableSeats = Arrays.asList((String[]) flight
															.get("availableSeats"));
										%>
										<thead>
											<tr>
												<th>#</th>
												<%for(int row=1; row<=numRows; row++){%>
												<th><%=row%></th>
												<%}%>
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
													if(row<=numFirstClass){
														flightClass="btn-primary";
													}else if (row<=(numFirstClass+numBusinessClass)){
														flightClass="btn-info";
													}else{
														flightClass="btn-success";
													}
												%>
												<td>
													<button
														class="btn btn-mini <%=flightClass %>
												  		<%if (availableSeats.indexOf(row.toString() + " "
																+ Col) < 0) {%>disabled<%}%>"
														type="button"
														name="Seat<%=row%> <%=Col%>Flight<%=numFlight%>"
														onclick="seatSelect('<%=row%> <%=Col%>',<%=numFlight%>,<%=numPax %>)"
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
						<script>
							$(function(){
								$(".seatDialog").dialog({autoOpen:false,modal:true,minWidth:1000,minHeight:500})
							})
						</script>
			<%		}
				}
			%>
			</div>
		</div>
		<!-- container -->

		<div id="footer">
			<!-- start footer -->
			<jsp:include page="/_footer" />
		</div>
		<!-- end footer -->
</body>
</html>