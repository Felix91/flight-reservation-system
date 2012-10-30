<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="_head.jsp" />
</head>
<body>
	<jsp:include page="/navBar.jsp" />
	<div class="container-fluid">
		<div id="content"><!-- start content -->
			    	<div class="row-fluid">
        	<h2>Customer Profile</h2>
            <div class="row-fluid">
              <div class="span8">
                  <dl class="dl-horizontal">
                  	<dt>Full Name</dt>
                    <dd>Edward Budiman</dd>
                    <dt>Date of Birth</dt>
                    <dd>05/05/90</dd>
                    <dt>Address</dt>
                    <dd>3534 Wesbrook Mall, Vancouver BC</dd>
                    <dt>Phone</dt>
                    <dd>7789381704</dd>
                    <dt>Loyalty Points</dt>
                    <dd>50,000</dd>
                  </dl>
              </div> <!--span6-->
              <div class="span4">
                  <ul class="unstyled">
                  	<li><h4><a href="#">Edit Profile</a></h4></li>
                    <li><h4><a href='#'>Check Loyalty Points</a></h4></li>
                    <li><h4><a href='#'>Sign Out</a></h4></li>
                  </ul>
              </div>
            </div><!--rowfluid spans-->
            <h2>Upcoming Flight</h2>
            <div class="row-fluid">
            	<div class="span8">
                  <dl class="dl-horizontal">
                  	<dt>Date</dt>
                    <dd>05/05/2013</dd>
                    <dt>To:</dt>
                    <dd>Vancouver, BC (YVR)</dd>
                    <dt>From:</dt>
                    <dd>Toronto, ON (YYZ)</dd>
                    <dt>Meal:</dt>
                    <dd>Chicken Rice</dd>
                  </dl>
              </div> <!--span6-->
              <div class="span4">
                  <ul class="unstyled">
                  	<li><h4><a href="#">Modify Flight</a></h4></li>
                    <li><h4><a href="#">View More Flights</a></h4></li>
                    <li><h4><a href="#">Print</a></h4></li>
                  </ul>
              </div>
            </div><!--row fluid upcoming flight-->
    	</div><!-- end content -->
    
    	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer.jsp" />
    	</div><!-- end footer -->
	</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>