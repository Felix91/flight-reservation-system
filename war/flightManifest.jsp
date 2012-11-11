
<html>
<head>	
	<jsp:include page="_head.jsp" />
</head>
<body>
	<jsp:include page="/_navBar.jsp" />
	<div class="container">
		<div id="content">
			<h2>Flight Manifest</h2>
			<div class="row-fluid">
				<div class="span12">
					<div class="row-fluid">
						<div class="span6">
						<h3>Flight Details</h3>
							<dl>
								<dt>Flight Number</dt>
								<dd>CA2012</dd>
								<dt>Origin</dt>
								<dd>Vancouver (YVR)</dd>
								<dt>Destination</dt>
								<dd>Toronto (YYZ)</dd>
								<dt>Departure Date</dt>
								<dd>19 Dec 2012</dd>
								<dt>Departure Time</dt>
								<dd>09:15</dd>
								<dt>Arrival Time (destination time)</dt>
								<dd>12:15</dd>												
							</dl>
						</div> <!-- span6 left -->
						<div class="span6">
							<jsp:include page="/flightLayout.jsp"/>							
						</div><!-- span6 right-->
					</div> <!-- row -->
					
				</div> <!-- span12 -->
			</div> <!-- row -->
		</div>
		<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer.jsp" />
    	</div><!-- end footer -->
	</div>
</body>

</html>