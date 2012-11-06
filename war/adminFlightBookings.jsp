<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="_header" />
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container-fluid">
		<div id="content">
			<div class="row-fluid">
        		<div class="span3 pull-left well">
					<jsp:include page="/_sideAdminNav.jsp" />
        		</div><!--/span-->
		        <div class="span9">
			  		<div class="row-fluid">
		                <div class="span12">
	                        <h2>Admin FlightBookings Management</h2>
							<div class="row-fluid">
								<div class="span10">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Confirmation Number</th>
												<th>Booked By</th>
												<th>Flight Number</th>
												<th>Flight Class</th>
												<th>Seat</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>John</td>
												<td>123</td>
												<td>Economy</td>
												<td>2C</td>
												<td><a href="#">View</a></td>
											</tr>
											<tr>
												<td>1</td>
												<td>John</td>
												<td>123</td>
												<td>Economy</td>
												<td>2C</td>
												<td><a href="#">View</a></td>
											</tr>
										</tbody>
									</table>
								</div><!-- span10 -->
							</div><!--/span row-->
		      			</div><!--/span12-->
		      		</div><!--/row-->
				</div><!--/span9-->
    	</div>
    	<div id="footer">
    		
    		<jsp:include page="/_footer" />
    		
    	</div>
	</div>
</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>
