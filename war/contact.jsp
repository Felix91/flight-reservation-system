<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="/_header" />
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<div class="row-fluid">
				<div class="span12">
					<h1>Contact Page</h1>
					<h1><small>Have a problem? Talk to us about it!</small></h1>
					<div class="row-fluid">
						<div class="span6 well" id="byPhone" style="min-height:360px;">
							<h3>Contact us by Phone</h3>
							<p>Call us at (888) 123-UFLY (8359)</p>
						</div>
						<div class="span6 well" id="byEmail">
						<h3>Contact us by Email</h3>
						<form class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="inName">Your Name:</label>
								<div class="controls">
									<input type="text" id="inName" placeholder="Full Name">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inEmail">Contact Email:</label>
								<div class="controls">
									<input type="text" id="inEmail" placeholder="Your Email">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inComment">Comments:</label>
								<div class="controls">
									<textarea rows="5" id="inComment" placeholder="Your Question/Comments"></textarea>
								</div>
							</div>
							
							<input type="submit" class="btn btn-primary pull-left" value="Submit">
						</form>
						</div>
					</div>
				</div><!-- span12 -->
			</div> <!-- row-fluid -->
		</div> <!-- content -->
		
    	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer" />
    	</div><!-- end footer -->
	</div> <!-- container -->
</body>
</html>