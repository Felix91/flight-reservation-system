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
        	<h2>Customer Profile</h2>
            <div class="row-fluid">
              <div class="span8">
                  <dl class="dl-horizontal">
                  	<dt>Full Name</dt>
                    <dd>
                    <% 
                    	String firstName;
						String lastName; 
                    	firstName = (String) request.getAttribute("customerFirstName");
                    	lastName = (String) request.getAttribute("customerLastName");
                    	out.print(firstName + " " + lastName); 
                    %>
                    </dd>
                    <dt>Date of Birth</dt>
                    <dd>05/05/90</dd>
                    <dt>Address</dt>
                    <dd>3534 Wesbrook Mall, Vancouver BC</dd>
                    <dt>Phone</dt>
                    <dd>7789381704</dd>
                    <dt>Loyalty Points</dt>
                    <dd><% out.print((Integer) request.getAttribute("loyaltyPoints")); %></dd>
                  </dl>
              </div> <!--span8-->
              <div class="span4">
                  <ul class="unstyled">
                  	<li><h4><a href="/customerProfile/edit">Edit Profile</a></h4></li>
                  	<li><h4><a href="/customerFlightbookings">View My Flights</a></h4></li>
                  </ul>
              </div><!--span4-->
            </div><!--rowfluid-->
              
    	</div><!-- end content -->
    
    	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer" />
    	</div><!-- end footer -->
	</div>
</body>
</html>