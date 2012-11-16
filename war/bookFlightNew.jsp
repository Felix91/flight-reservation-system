<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="/_header" />
	<script>
		function popSubmit(formid){
			var target = window.open('seatselect.jsp','tgt','width=1080,height=600,scrollbars=yes,status=yes');
			target.focus();
			//document.getElementById(formid).submit();
			return false;
		}
		function seat(){
			//var seatno = document.getElementById("seat").value;
			document.getElementById('seatout').innerHTML="hai";
		}
	</script>	
	

</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h2>Booking Confirmation</h2>
					<legend>Passenger1</legend>
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
						</div>
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
						</div>
						<div class="span4 well" style="min-height:400px;">
						<h4>In-Flight Options</h4>
							 <!-- <form id="flightopt" action="seatselect.jsp" target="tgt"> --> 
								<dl>																							
									<dt>Seat Choice</dt>
									<!--  <input type="text" class="disabled" value="haha">-->
									<button type="button" class="btn btn-primary" onclick="popSubmit('flightopt')">Display Seats</button>		
								</dl>		
								
								<input type="hidden" name="seat" value="">	
								<!--  <button type="button" class="btn" onclick="seat()">SHOW THE VALUE</button>	
								<p id="seatout">haha</p> -->											
							<!--   </form> 						-->
							
							<dl>
								<dt>Meal Preference</dt>
									<select name="meal">
										<option value="nopref">No Preference</option>
										<option value="chicken">Chicken Rice</option>
										<option value="beef">Beef Noodle</option>
									</select>
							</dl>
							
							
						</div><!-- span 4 flight opt -->
					</div><!-- rowfluid -->
					
					<div class="row-fluid">				
					<legend>Passenger2</legend>
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
						</div>
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
						</div>
						<div class="span4 well" style="min-height:400px;">
						<h4>In-Flight Options</h4>
							<form id="flightopt2" action="seatselect.jsp" target="tgt">
								<dl>																							
									<dt>Seat Choice</dt>
									<button type="button" class="btn btn-primary" onclick="popSubmit('flightopt2')">Display Seats</button>		
								</dl>
								
							</form>							
							
							<dl>
								<dt>Meal Preference</dt>
										<select name="meal">
											<option value="nopref">No Preference</option>
											<option value="chicken">Chicken Rice</option>
											<option value="beef">Beef Noodle</option>
										</select>
							</dl>
												
						</div><!-- span 4 flight opt -->
					</div>
			</div> <!-- span12 -->
		</div> <!-- row fluid -->
	</div> <!-- container -->
	
	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer" />
    </div><!-- end footer -->
</body>
</html>