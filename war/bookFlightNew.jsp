<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<html>
<head>
<jsp:include page="/_header" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
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
		function isNumber(n) {
		  return !isNaN(parseFloat(n)) && isFinite(n);
		}
		function onFormSubmit()
		{
			var error = $("#requiredError")
			var passengerNameFilled=true;
			$("[name^=passengerName]").each(function(index){
				if(passengerNameFilled){
					passengerNameFilled=$(this).val()!=""
				}
			})
			
			var seatSelected=true;
			$('input[name^=Seat]').each(function(){
				if(seatSelected){
					seatSelected=$(this).val()!=""
				}
				
			})
			
			if(!passengerNameFilled){
				error.html("Make sure all passengers are named")
				return false
			}if(!seatSelected){
				error.html("Select a seat for all flights")
				return false
			}if(!$('#inputCard').val() || !isNumber($('#inputCard').val()) || $('#inputCard').val().length != 16){
				error.html("Please Provide a valid credit card number")
				return false;
			}if(	!$('#expDate1').val() ||
					!isNumber($('#expDate1').val())||
					$('#expDate1').val().length != 2 ||
					!$('#expDate2').val() ||
					!isNumber($('#expDate1').val())||
					$('#expDate2').val().length != 2){
				error.html("Please Provide a credit card expiry date")
				return false;
			}if(!$('#secNum').val() || !isNumber($('#secNum').val()) || $('#secNum').val().length != 3){
				error.html("Please Provide a 3 digit Security Number")
				return false;
			}if(!$('#cardHolder').val()){
				error.html("Please provide the cardholder's name");
				return false
			}if(!$('#addrLine1').val()){
				error.html("Please provide an address")
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
		<form action="/createBooking" method=Post class="form-horizontal">
		<div class="row-fluid">
		
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
				<h3>Passenger <%=numPax+1%></h3>
				<div class="row-fluid">
					<%
						
							for (int numFlight = 0; numFlight < Flights.size(); numFlight++) {
								HashMap<String, Object> flight = Flights.get(numFlight);
					%>
					<div class="span4 well" style="min-height: 500px;">
						<h4>Flight Options</h4>
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

					<div class="span4 well" style="min-height: 500px;">
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
		
		</div>
		<!-- row fluid -->
		<div class="row">
				
				<div class="span6 well">
					<strong> Total Cost= <%=request.getAttribute("TotalCostString") %> </strong> <br>
					<em>You have $<%=((Integer)request.getAttribute("loyaltyPoints"))/10 %> worth of loyalty points. How much would you like to use today?</em>
					<div class="control-group" >					
						<label class="control-label" for="inputPoints">Loyalty points:</label>
						<div class="controls">
							<input type="text" name="loyaltyPointsUsed" placeholder="Loyalty Points" id="inputPoints">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputCard">Credit Card:</label>
						<div class="controls">
							<input type="text" name="CreditCard" placeholder="Credit Card #" id="inputCard">
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="expDate1">Expiration Date:</label>

							<input type="text" name="ccExpDate" placeholder="MM" id="expDate1"> /


							<input type="text" name="ccExpDate" placeholder="YY" id="expDate2">

					</div>
					<div class="control-group">
						<label class="control-label" for="secNum">Expiration Date:</label>
						<div class="controls">
							<input type="text" name="ccSecNum" placeholder="3 digit security number" id="secNum">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="cardHolder">Cardholder Name:</label>
						<div class="controls">
							<input type="text" name="cardHolder" placeholder="Expiration Date" id="cardHolder">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="addrLine1">Address Line 1:</label>
						<div class="controls">
							<input type="text" name="addrLine1" placeholder="Address Line 1" id="addrLine1">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="addrLine2">Address Line 2:</label>
						<div class="controls">
							<input type="text" name="addrLine2" placeholder="Address Line 2" id="addrLine2">
						</div>
					</div>
				<div id=requiredError class="text-required"></div>
				<input type="submit"  class="btn btn-primary" value="Confirm and Pay">
				</div>			
			</div>
	</div>
		</form>
		</div>
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