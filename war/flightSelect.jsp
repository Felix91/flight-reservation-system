<%@ page import="java.util.*"%>

<html>
<head>
<title>uFly Login Page</title>
<link href="/stylesheets/logincss.css" type="text/css" rel="stylesheet" />
</head>
<body>

	<div id="container">

		<h3>Select your fight from the list below</h3>
		<form method="POST">
			<ul>
				<li>8:30-9:45<input type="radio" name="flightNumber"
					value="jk123"></li>
				<li>9:00-9:45 connecting 9:50-10:30 <input type="radio"
					name="flightNumber" value="jk345_jk654"> <!--connecting flight numbers seperated by underscores-->
				</li>
				<!--
	  routes will be passed in in the following way, under the key "flights"
	  Vector<Vector<HashMap<String,Object>>> = { 0 ->
	                                                 { 0 ->
		                        					      { "startTime" : Calendar(),
							                       			"endTime"   : Calendar(),
															"flightNum" : Calendar()
															/* probably more stuff
															   like price and such */
							      						  }
													      /*connecting flight*/
													   1 ->
													      {
													        "startTime" : Calendar(),
													        "endTime"   : Calendar(),
														"flightNum" : Calendar()
												              }
							                         }
							                      /*more flights*/
									            }
	-->
			</ul>
			<input type="submit" value="Choose">
		</form>

	</div>
	<div id="footer">
		<jsp:include page="/_footer" />
	</div>
	</div>
	<!-- page generated at: <%//out.print(request.getAttribute("date"));%>-->
</body>
</html>