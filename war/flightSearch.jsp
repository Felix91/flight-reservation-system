
<html>
<head>
</head>
<body>
<% if ((String)request.getAttribute("User")!=null){ %>
<h2>You are logged in</h2>
<a href="/login?logout=1">logout</a>
<% }%>

<form action="/search" method="post">
  <label for="from">departing airport:</label></br>
  <input type="text" name="from"></br>
  <label for="to">arriving airport:</label></br>
  <input type="text" name="to"></br>
  <label for="departureDate">departure Date(/dd/mm/yy)</label></br>
  <input type="text" name="departureDate"></br>
  <label for="arrivalDate">arrivalDate(/dd/mm/yy)</label></br>
  <input type="text" name="departureDate"></br>
  <input type="submit" value="search">
</form>
</body>

</html>
