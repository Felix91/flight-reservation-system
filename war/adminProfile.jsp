<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="_head.jsp" />
	
</head>
<body>
	<jsp:include page="/_navBar.jsp" />
	<div class="container">
		<div id="content"><!-- start content -->
			<h2>Welcome Admin</h2>
			
			<div class="tabbable">
				<ul class="nav nav-pills">
					<li class="active"><a href="#flights" data-toggle="tab">Upcoming Flights</a></li>
					<li><a href="#flightstat" data-toggle="tab">Flight Statistics</a></li>
					<li><a href="#airports" data-toggle="tab">Airports</a></li>				
				</ul>
				<div class="tab-content">
					<div id="flights" class="tab-pane fade active in">
						<h3>Upcoming Flights</h3>									
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>To</th>
									<th>From</th>
									<th>Date</th>
									<th>Departure Time</th>
									<th>ETA</th>
								</tr>
							</thead>
							<tbody>
							<tr>
								<td>Test</td>
								<td>Test</td>
								<td>Test</td>
								<td>Test</td>
								<td>Test</td>
							</tr>
							<tr>
								<td>Test</td>
								<td>Test</td>
								<td>Test</td>
								<td>Test</td>
								<td>Test</td>
							</tr>
							</tbody>
						</table>
						<a href="#"><button class="btn btn-primary">Add Flights</button></a>
					</div>
					<div id="flightstat" class="tab-pane fade">
						<p>Stat goes here</p>
					</div>
					<div id="airports" class="tab-pane fade">
						<p>Do Airport stuff</p>
					</div>
				</div> <!-- tab content -->
			</div><!-- tabbable -->
			
			
    	</div><!-- end content -->
		    
    	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer.jsp" />
    	</div><!-- end footer -->
	</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>