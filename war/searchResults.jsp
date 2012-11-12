<%@ page import="java.util.*" %>

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
	                        <h2>Departure Flight Option</h2>
	                        <form method="post" action="">
							<div class="row-fluid">
								<div class="span10">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Flight No.</th>
		                                        <th>Departure Date</th>
		                                        <th>Departure Time</th>
		                                        <th>Arrival Date</th>
		                                        <th>Arrival Time</th>
		                                        <th>Stops/Transit</th>
		                                        <th>Duration</th>
		                                        <th>Price</th>
											</tr>
										</thead>
										<tbody>
											<% 
											
												Vector<Vector<HashMap<String,Object>>> trips = (Vector<Vector<HashMap<String,Object>>>) request.getAttribute("trips");
												if (trips != null)
												{
													Iterator tripsIt = trips.iterator();
													while(tripsIt.hasNext())
													{
														HashMap flightAttributes = (HashMap<String,Object>)((Vector)tripsIt.next()).get(0);

											%>
											<tr>
												<td>
													<label class="radio">
														<input type="radio" name="departopt" id="optionsRadios1" value="option1" checked>
														<%=(String)flightAttributes.get("flightNo") %>
													</label>
												</td>
												<td><%out.print((String)flightAttributes.get("flightOrigin") +"<BR>" +((Calendar)flightAttributes.get("departs")).getTime().toString()); %></td>
												<td><%out.print((String)flightAttributes.get("flightDesination") +"<BR>" +((Calendar)flightAttributes.get("arrives")).getTime().toString()); %></td>
												<td><%out.print(flightAttributes.get("stops").toString()); %> </td>
												<td><%
													Long duration = (Long)flightAttributes.get("durationInMins");
													out.print(new Long(duration/60).toString());
													out.print(":");
													if(duration%60 <10){
														out.print("0");
													}
													out.print(new Long(duration % 60).toString());
													%></td>
												<td>n/a</td>
											</tr>
											<%
													}
												}
											%>
											<tr>
	                                            <td>
	                                            	<label class="radio">
														<input type="radio" name="departopt" id="optionsRadios1" value="option2" checked>
														test1
													</label>
												</td>
	                                            <td>test2</td>
	                                            <td>test3</td>
												<td>test4</td>
												<td>test5</td>
												<td>test6</td>
												<td>test7</td>
												<td>test8</td>
	                                       </tr>
										</tbody>
									</table>
								</div><!-- span10 -->
							</div><!--/span row-->
							
							<h2>Return Flight Option</h2>
							
	                    	<div class="row-fluid">
		                    	<div class="span10">
									<table class="table table-striped">
										<thead>
		                                	<tr>
		                                    	<th>Flight No.</th>
		                                        <th>Departure Date</th>
		                                        <th>Departure Time</th>
		                                        <th>Arrival Date</th>
		                                        <th>Arrival Time</th>
		                                        <th>Stops/Transit</th>
		                                        <th>Duration</th>
		                                        <th>Price</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                	<tr>
		                                		<td>
													<label class="radio">
														<input type="radio" name="returnopt" id="optionsRadios2" value="option1" checked>
														test1
													</label>
												</td>
		                                        <td>test2</td>
		                                        <td>test3</td>
		                                        <td>test4</td>
		                                        <td>test5</td>
		                                        <td>test6</td>
		                                        <td>test7</td>
		                                        <td>test8</td>
		                                   </tr>
		                                   <tr>
		                                   		<td>
													<label class="radio">
														<input type="radio" name="returnopt" id="optionsRadios2" value="option2" >
														test1
													</label>
												</td>
		                                        <td>test2</td>
		                                        <td>test3</td>
		                                        <td>test4</td>
		                                        <td>test5</td>
		                                        <td>test6</td>
		                                        <td>test7</td>
		                                        <td>test8</td>
		                                   </tr>
		                               </tbody>
									</table>
	                  			</div><!-- span10 -->
	            			</div><!--/span row-->
	            			
	            			<div class="row-fluid">
								<div class="span10">
									<button class="btn pull-right" type="submit">Book Flight</button>
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
