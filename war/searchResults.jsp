<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="_head.jsp" />
</head>
<body>
	<jsp:include page="/navBar.jsp" />
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
	                        <form action="" method="post">
							<div class="row-fluid">
								<div class="span10">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Flight</th>
												<th>Departs</th>
												<th>Arrive</th>
												<th>Stops</th>
												<th>Duration</th>
												<th>Price</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<label class="radio">
														<input type="radio" name="depart" id="optionsRadios1" value="option1" checked>
														test1
													</label>
												</td>
												<td>test2</td>
												<td>test3</td>
												<td>test4</td>
												<td>test5</td>
												<td>test6</td>
											</tr>
											<tr>
	                                            <td>
	                                            	<label class="radio">
														<input type="radio" name="depart" id="optionsRadios1" value="option2">
														test1
													</label>
												</td>
	                                            <td>test2</td>
	                                            <td>test3</td>
												<td>test4</td>
												<td>test5</td>
												<td>test6</td>
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
		                                    	<th>Flight</th>
		                                        <th>Departs</th>
		                                        <th>Arrive</th>
		                                        <th>Stops</th>
		                                        <th>Duration</th>
		                                        <th>Price</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                	<tr>
		                                		<td>
													<label class="radio">
														<input type="radio" name="return" id="optionsRadios2" value="option1" checked>
														test1
													</label>
												</td>
		                                        <td>test2</td>
		                                        <td>test3</td>
		                                        <td>test4</td>
		                                        <td>test5</td>
		                                        <td>test6</td>
		                                   </tr>
		                                   <tr>
		                                   		<td>
													<label class="radio">
														<input type="radio" name="return" id="optionsRadios2" value="option2" >
														test1
													</label>
												</td>
		                                        <td>test2</td>
		                                        <td>test3</td>
		                                        <td>test4</td>
		                                        <td>test5</td>
		                                        <td>test6</td>
		                                   </tr>
		                               </tbody>
									</table>
	                  			</div><!-- span10 -->
	            			</div><!--/span row-->
	            			</form> <!-- Return flight option -->
							<div class="row-fluid">
								<div class="span10">
									<button class="btn pull-right" type="submit">Book Flight</button>
								</div>
							</div>
		      			</div><!--/span12-->
		      		</div><!--/row-->
				</div><!--/span9-->
    	</div>
    	<div id="footer">
    		
    		<jsp:include page="/_footer.jsp" />
    		
    	</div>
	</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>