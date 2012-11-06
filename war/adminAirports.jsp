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
	                        <h2>Admin Airport Management</h2>
							<div class="row-fluid">
								<div class="span10">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Callsign</th>
												<th>City</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>YVR</td>
												<td>Vancouver</td>
												<td><a href="#">Delete</a></td>
											</tr>
											<tr>
	                                            <td>YVR</td>
	                                            <td>Vancouver</td>
												<td><a href="#">Delete</a></td>
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
