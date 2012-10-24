<%@ page import="java.util.*"%>

<html>
<head>
<title>uFly Login Page</title>
<link href="/stylesheets/logincss.css" type="text/css" rel="stylesheet" />
</head>
<body>

	<div id="container">
		<div id="header">
			<jsp:include page="/_header" />
		</div>
		<div id="content">
			<!-- Add content here -->

			<%
				if ((String) request.getAttribute("User") != null) {
			%>
			<h2>You are logged in</h2>
			<a href="/login?logout=1">logout</a>
			<%
				}
			%>

			<form action="/search" method="post">
				<label for="from">departing airport:</label></br> <input type="text"
					name="from"></br> <label for="to">arriving airport:</label></br> <input
					type="text" name="to"></br> <label for="departureDate">departure
					Date(/dd/mm/yy)</label></br> <input type="text" name="departureDate"></br> <label
					for="arrivalDate">arrivalDate(/dd/mm/yy)</label></br> <input type="text"
					name="departureDate"></br> <input type="submit" value="search">
			</form>

		</div>
		<div id="footer">
			<jsp:include page="/_footer" />
		</div>
	</div>
	<!-- page generated at: <%//out.print(request.getAttribute("date"));%>-->
</body>
</html>
