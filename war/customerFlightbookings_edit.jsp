<%@ page import="java.util.*, ufly.entities.FlightBooking, ufly.entities.Flight, ufly.entities.Meal" %>

<html>
<head>	
	<jsp:include page="/_header" />
	
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<h3>Customer Flightbookings - edit Flight 
			</h3>
			<div class="row-fluid">
    			<div class="span12">
        			<div class="row-fluid">
	        			<%
							if(request.getAttribute("editFlightbooking") != null){
								FlightBooking editFlightbooking = (FlightBooking) request.getAttribute("editFlightbooking");
								out.print("<form action = \"/customerProfile/editFlightbookings\" method=\"post\">");
								out.print("<div class=\"dl-horizontal\">");
	        						out.print("<div class=\"control-group\">");
		                    			out.print("<label class=\"control-label span2\" for=\"flightNumber\">flightNumber</label>");
										out.print("<input type=\"text\" name=\"flightNumber\">");
									out.print("</div>");
									out.print("<div class=\"control-group\">");
		                    			out.print("<label class=\"control-label span2\" for=\"passengerName\">passengerName</label>");
										out.print("<input type=\"text\" name=\"passengerName\">");
		                    		out.print("</div>");
									
		    						out.print("<div class=\"control-group\">");
		    							out.print("<label class=\"control-label span2\" for=\"mealPreference\">mealPreference</label>");
										
		    							out.print("<select name=\"meal\">");
						    				Vector<Meal> allowableMeals = editFlightbooking.getBookedFlight().getAllowableMeals();	
						    				for(int j=0;j<allowableMeals.size();j++)
						    				{
						    					out.print("<option value=\""+ allowableMeals.elementAt(j).toString() +"\">"+ allowableMeals.elementAt(j).toString() + "</option>");
						    					
						    				}
						    			out.print("</select>");
		    						out.print("</div>");
									
									out.print("<input type=\"submit\" value=\"Modify\">");
		                		out.print("</div>");
		                		out.print("</form>");
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