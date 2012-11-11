<%@ page import="java.util.*" %>

<html>

<body>
	<div class="container">
		<div id="content">
			<div class="row-fluid">
		        <div class="span12">
			  		<div class="row-fluid">
		                <div class="span11">
	                        <h4>Staff Flight Management</h4>
							<div class="row-fluid">
								<div class="span12">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Flight No.</th>
												<th>Origin</th>
												<th>Destination</th>
												<th>Departure Date</th>
												<th>Departure Time</th>
												<th>Arrival Date</th>
												<th>Arrival Time</th>
												<th>Seats Available</th>
												<th>View</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>Vancouver</td>
												<td>Toronto</td>
												<td>Date</td>
												<td>Time</td>											
												<td>Date</td>
												<td>Time</td>
												<td>150/200</td>
												<td><a href="/flightmanifest.jsp">Manifest</a></td>
											</tr>
											<tr>
												<td>1</td>
												<td>Vancouver</td>
												<td>Toronto</td>
												<td>Date</td>
												<td>Time</td>
												<td>Date</td>
												<td>Time</td>
												<td>150/200</td>
												<td><a href="/flightmanifest.jsp">Manifest</a></td>
											</tr>
										</tbody>
									</table>
								</div><!-- span10 -->
							</div><!--/span row-->
		      			</div><!--/span12-->
		      			<div class="span1">
		      			<a href="#">Logout</a>
		      			</div>
		      		</div><!--/row-->
				</div><!--/span9-->
    	</div>
	</div>
</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>
