<%@ page import="java.util.*, ufly.entities.FlightBooking, ufly.entities.Flight, ufly.entities.Meal, ufly.entities.SeatingArrangement, ufly.entities.Seat" %>

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
		<div id="content"><!-- start content -->
			<h3>Flight Management Flightbookings - edit Flightbookings 
			</h3>
			<div class="row-fluid">
    			<div class="span12">
        			<div class="row-fluid">
	        			<%
							if(request.getAttribute("editFlightbooking") != null){
								FlightBooking editFlightbooking = (FlightBooking) request.getAttribute("editFlightbooking");
								out.print("<div class=\"dl-horizontal\">");
								out.print("<form action = \"/customerProfile/editFlightbookings\" method=\"post\">");
									out.print("<div class=\"control-group\">");
		                    			out.print("<label class=\"control-label span2\" for=\"flightNumber\">flightNumber</label>");
										out.print("<input type=\"text\" name=\"flightNumber\">");
									out.print("</div>");
									
		    						out.print("<div class=\"control-group\">");
		    							out.print("<label class=\"control-label span2\" for=\"mealPreference\">mealPreference</label>");
										
		    							out.print("<select name=\"meal\">");
						    				Vector<Meal> allowableMeals = editFlightbooking.getBookedFlight().getAllowableMeals();	
						    				for(int j=0;j<allowableMeals.size();j++)
						    				{
						    					if(allowableMeals.elementAt(j).toString().equals(editFlightbooking.getMealChoice().toString())){
						    						out.print("<option value=\""+ allowableMeals.elementAt(j).toString() +"\" selected=\"selected\">"+ allowableMeals.elementAt(j).toString() + "</option>");
						    					}else{
						    						out.print("<option value=\""+ allowableMeals.elementAt(j).toString() +"\">"+ allowableMeals.elementAt(j).toString() + "</option>");
						    					}
						    				}
						    			out.print("</select>");
		    						out.print("</div>");
		    						out.print("<div class=\"control-group\">");
										SeatingArrangement currentSeatingArrangement = editFlightbooking.getBookedFlight().getSeatingArrangement();
										Vector<Seat> availableSeats = currentSeatingArrangement.getAvailableSeats();
									out.print("</div>");
									out.print("<input type=\"hidden\" name=\"confirmationNumber\" value=\"" + editFlightbooking.getConfirmationNumber().getId() +  "\">");
									out.print("<input type=\"submit\" value=\"Modify\">");
		                		out.print("</form>");
		                		out.print("</div>");
							}
						%>
					</div><!--/span row-->
				</div><!--/span12-->
			</div><!--/row-->
    	</div><!-- end content -->
		    
    	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer" />
    	</div><!-- end footer -->
	</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>