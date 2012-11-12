<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="/_header" />
	
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h2>Booking Confirmation</h2>
				<h3>Flight Details</h3>
					<div class="row-fluid">
						<div class="span4 well" style="min-height:400px;">
							<h4>Departure Flight Options</h4>
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
						</div> <!-- span4 left-->
						<div class="span4 well" style="min-height:400px;">
							<h4>Return Flight Options</h4>
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
							<label class="checkbox">
								<input type="checkbox"> One-way only
							</label>
						</div> <!-- span4 mid-->
						<div class="span4 well" style="min-height:400px;">
							<h4>In-Flight Options</h4>
							<dl>																	
								<dt>Meal Preference</dt>
									<select>
										<option>No Preference</option>
										<option>Chicken Rice</option>
										<option>Beef Noodle</option>
									</select>
								<dt>Class</dt>
									<select>
										<option>First</option>
										<option>Business</option>
										<option>Economy</option>
									</select>
							</dl>
						</div> <!-- span4 right-->
					</div> <!-- row fluid -->
					<div class="row-fluid">
						<div class="span12 well">
						<h3>Seat Preference</h3>
						<jsp:include page="/flightLayoutCust.jsp"/>
						</div>
					</div>
					
					<button class="btn btn-success" type="button">Confirm and Book Flight</button>
					<a href="/searchresults.jsp"><button class="btn btn-small" type="button">Redo Search</button></a>
			</div> <!-- span12 -->
		</div> <!-- row fluid -->
	</div><!-- container -->
 	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer" />
    </div><!-- end footer -->
</body>
</html>