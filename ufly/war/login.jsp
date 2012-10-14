<%@ page import="java.util.*" %>

<html>
	<head>	
	</head>
	<body>
	<h2>Sign In</h2>
		<form action="/login" method="post">
			Username:<br>
			<input type="text" name="username"><br>
			Password:<br>
			<input type="password" name="password"><br>
			<input type="submit" value="Submit">
			
			
			fdsfadsfdffdsfdsf
		</form>
		page generated at: <% out.print(request.getAttribute("date")); %>
	</body>
</html>