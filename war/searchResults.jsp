<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<html>
<head>	
	<jsp:include page="_head.jsp" />
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container-fluid">
		<div id="content">
			<div class="row-fluid">
        		<div class="span3 pull-left well">
					<jsp:include page="/_sideSearchBox.jsp" />
        		</div><!--/span-->
		        <div class="span9">
			  		<div class="row-fluid">
		                <div class="span12">
	                        <div class="alert alert-error" id="errmsg" <%if(request.getParameter("errorMsg")==null){%>style="display:none"<%} %>>
								<%=request.getParameter("errorMsg") %>
							</div>
	                        <h2>Departure Flight Option</h2>
	                        <form id="flightSelect" method="post" action="/select">
							<input type="hidden" name="numPassengers" value="<%=request.getAttribute("numPassengers") %>">
							<div class="row-fluid">
								<div class="span10">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>&nbsp;</th>
												<th>Price</th>
												<th>Duration</th>
												<th>Flight No.</th>
		                                        <th>Departing</th>
		                                        <th>Arriving</th>
											</tr>
										</thead>
										<tbody>
											<% 
												Vector trips=(Vector)request.getAttribute("thereTrips");
												SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
												for(Vector<HashMap<String,Object>> trip:(Vector<Vector>)trips)
												{
													long stops=trip.size();
											%>
											<!--  start of first row -->
											<tr>
												<td rowspan="<%out.print(stops);%>" >
                                            	<label class="radio">
													<input type="radio" name="departopt" id="optionsRadios1" value="<% 
														for(HashMap<String,Object> flight:trip){
															out.print(flight.get("flightNo")+"_");
															out.print(dateFormat.format(flight.get("departs"))+"|");
														}%>" >
												</label>
												</td >
												<td rowspan="<%out.print(stops);%>">$
													<%
														Integer price=0;
														for(HashMap<String,Object> flight:trip){
															price+=(Integer)flight.get("price");
														}
														out.print(price/100);//Dollars
														out.print(".");
														if (price%100 <10)
															out.print("0");
														out.print(price%100);
													%>
												</td>
												<td rowspan="<%out.print(stops);%>">
													<%	
														long duration=0;
														for(HashMap<String,Object> flight:trip){
															duration+=(Long)flight.get("durationInMins");
														}
														out.print(duration/60);
														out.print(":");
														if (duration%60 <10)
															out.print("0");
														out.print(duration%60);
													%>
												</td>
												<%
												for(int i = 0; i<trip.size();i++){
													HashMap<String,Object> flight=trip.get(i);
													if(i>0){out.print("<tr>");}
												%>
												<!-- this is the start of the row if it is not the first row -->
														<td>
															<%out.print((String)flight.get("flightNo"));%>
														</td>
														<td>
															<%out.print((String)flight.get("flightOrigin"));%>
															<br>
															<% out.print(dateFormat.format(flight.get("departs")));%>
														</td>
														<td>
															<%out.print((String)flight.get("flightDestination"));%>
															<br>
															<% out.print(dateFormat.format(flight.get("arrives")));%>
														</td>
													<%if(i<trip.size()-1){ out.print("</tr>");}%>
													<!-- this is the end of all the rows except the last row -->
												<%} %>
											</tr>
										<% } %>
										</tbody>
									</table>
								</div><!-- span10 -->
							</div><!--/span row-->
							<% if(request.getAttribute("oneWayOrReturn").equals("return")){%>
							<h2>Return Flight Option</h2>
							
	                    	<div class="row-fluid">
		                    	<div class="span10">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>&nbsp;</th>
												<th>Price</th>
												<th>Duration</th>
												<th>Flight No.</th>
		                                        <th>Departing</th>
		                                        <th>Arriving</th>
											</tr>
										</thead>
										<tbody>

											<% 
												trips=(Vector)request.getAttribute("returnTrips");
												for(Vector<HashMap<String,Object>> trip:(Vector<Vector>)trips)
												{
													long stops=trip.size();
											%>
											<!--  start of first row -->
											<tr>
												<td rowspan="<%out.print(stops);%>" >
                                            	<label class="radio">
													<input type="radio" name="returnopt" id="optionsRadios1" value="<% 
														for(HashMap<String,Object> flight:trip){
															out.print(flight.get("flightNo")+"_");
															out.print(dateFormat.format(flight.get("departs"))+"|");
														}%>" >
												</label>
												</td >
												<td rowspan="<%out.print(stops);%>">
													<%
														Integer price=0;
														for(HashMap<String,Object> flight:trip){
															price+=(Integer)flight.get("price");
														}
														out.print(price/100);//Dollars
														out.print(".");
														if (price%100 <10)
															out.print("0");
														out.print(price%100);
													%>
												</td>
												<td rowspan="<%out.print(stops);%>">
													<%	
														long duration=0;
														for(HashMap<String,Object> flight:trip){
															duration+=(Long)flight.get("durationInMins");
														}
														out.print(duration/60);
														out.print(":");
														if (duration%60 <10)
															out.print("0");
														out.print(duration%60);
													%>
												</td>
												<%
												for(int i = 0; i<trip.size();i++){
													HashMap<String,Object> flight=trip.get(i);
													if(i>0){out.print("<tr>");}
												%>
												<!-- this is the start of the row if it is not the first row -->
														<td>
															<%out.print((String)flight.get("flightNo"));%>
														</td>
														<td>
															<%out.print((String)flight.get("flightOrigin"));%>
															<br>
															<% out.print(dateFormat.format(flight.get("departs")));%>
														</td>
														<td>
															<%out.print((String)flight.get("flightDestination"));%>
															<br>
															<% out.print(dateFormat.format(flight.get("arrives")));%>
														</td>
													<%if(i<trip.size()-1){ out.print("</tr>");}%>
													<!-- this is the end of all the rows except the last row -->
												<%} %>
											</tr>
										<% } %>
										</tbody>
									</table>
	                  			</div><!-- span10 -->
	            			</div><!--/span row-->
	            			<%}//If returnFlight %>
	            			<div class="row-fluid">
								<div class="span10">
									<div id="flightNotSelected" class="text-required pull-right" style="display:none">Please select a flight</div>
									<button class="btn pull-right" type="submit">Select Flight(s)</button>
									<script type="text/javascript">
									$('#flightSelect').submit(function(){
										
										var departSelected = $('input[name="departopt"]:checked').length>0
										var returnSelected = true;
										//if return opt exists but not selected
										if( $('input[name="returnopt"]')!=[] && $('input[name="returnopt"]:checked')==[] )
											var returnSelected = false;
										if (!(departSelected && returnSelected)){
											$('#errmsg').html("Please select a flight").show()
											return false
										}return true
										  
									})
									</script>
								</div>
							</div>
	            			</form> <!-- Return flight option -->
		      			</div><!--/span12-->
		      		</div><!--/row-->
				</div><!--/span9-->
    	</div>
    	<div id="footer">
    		
    		<jsp:include page="/_footer.jsp" />
    		
    	</div>
	</div>
</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>
