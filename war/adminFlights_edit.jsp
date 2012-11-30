<%@ page import="java.util.*, ufly.entities.Flight" %>

<html>
<head>	
	<jsp:include page="/_header" />
	
</head>
<%
Flight editFlight = (Flight) request.getAttribute("editFlight");
%>

<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<% 
        	if((String) request.getAttribute("errorMsg") != null){
        		out.print("<div class=\"alert alert-error\">");
        			out.print("<button data-dismiss=\"alert\" class=\"close\" type=\"button\">x</button>");
        			out.print((String) request.getAttribute("errorMsg"));
				out.print("</div>");
			}else if((String) request.getAttribute("successMsg") != null ){
				out.print("<div class=\"alert alert-success\">");
        			out.print("<button data-dismiss=\"alert\" class=\"close\" type=\"button\">x</button>");
        			out.print((String) request.getAttribute("successMsg"));
				out.print("</div>");
			}
        	%>
			<h3>Flight Management - Edit Flight <% out.print(editFlight.getFlightNumber()); %>
			</h3>
			<div class="row-fluid">
    			<div class="span12">
        			<div class="row-fluid">
	        			<form action = "/flightManagerProfile/editFlights" method="post">
	        				<div class="control-group">
		                    	<label class="control-label span2" for="origin">origin</label>
		                    	<input type="text" name="origin" value="<% out.print(editFlight.getOrigin().getCallSign()); %>">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span2" for="destination">destination</label>
		                    	<input type="text" name="destination" value="<% out.print(editFlight.getDestination().getCallSign()); %>">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span2" for="departure">departure</label>
		                    	<input type="text" name="departure" value="<% out.print(editFlight.getDeparture()); %>">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span2" for="arrival">arrival</label>
		                    	<input type="text" name="arrival" value="<% out.print(editFlight.getArrival()); %>">
		                    </div>
		                    <div class="control-group">
		                    	<label class="control-label span2" for="price">Price in cents</label>
		                    	<input type="text" name="price" value="<% out.print(editFlight.getPriceInCents()); %>">
		                    </div>
							<div class="control-group">
		                    	<label class="control-label span3" for="allowableMealTypes">allowableMealTypes</label>
		                    	<input type="text" name="allowableMealTypes" value="<% out.print(editFlight.getAllowableMeals()); %>">
		                    </div>
		                    <input type="hidden" id="flightKey"
								value="<%=editFlight.getKey().getName()%>" name="flightKey">
							<input type="submit" value="Submit">
						</form>
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