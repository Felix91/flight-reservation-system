
<html>
<head>	
	<jsp:include page="_head.jsp" />
</head>
<body>
	<jsp:include page="/_navBar.jsp" />
	<div class="container">
		<div id="content">
			<h2>Logged in as Flight Staff</h2>
			
			<jsp:include page="/_staffFlights.jsp" />
		</div>
		<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer.jsp" />
    	</div><!-- end footer -->
	</div>
</body>

</html>