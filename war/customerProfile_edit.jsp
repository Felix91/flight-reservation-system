<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="/_header" />
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<% 
        	if((String) request.getAttribute("errorMsg") != null){
        		out.print("<div class=\"alert alert-error\">");
        			out.print("<button data-dismiss=\"alert\" class=\"close\" type=\"button\">x</button>");
        			out.print((String) request.getAttribute("errorMsg"));
				out.print("</div>");
			}else if((String) request.getAttribute("successMsg") != null ){
				out.print("<div class=\"alert alert-success\">");
        			out.print("<button data-dismiss=\"alert\" class=\"close\" type=\"button\">x</button>");
        			out.print((String) request.getAttribute("successMsg"));
				out.print("</div>");
			}
        	%>
			<h2>Edit Customer Profile</h2> 
            <div class="row-fluid">
              <div class="span8">
                  <form class="form-horizontal" action="/customerProfile/edit" method="post">
			           <div class="control-group">
			            	<label class="control-label" for="email">Email</label>
                            <div class="controls">
                                <% out.print((String) request.getAttribute("customerEmail")); %>
                            </div>
                	   </div>
			           <div class="control-group">
			            	<label class="control-label" for="fname">First Name</label>
                            <div class="controls">
                                <input type="text" id="fname" name="fname" value="<% out.print((String) request.getAttribute("customerFirstName")); %>" placeholder="Enter your First Name">
                            </div>
                	   </div>
			           <div class="control-group">
			            	<label class="control-label" for="lname">Last Name</label>
                            <div class="controls">
                                <input type="text" id="lname" name="lname" value="<% out.print((String) request.getAttribute("customerLastName")); %>" placeholder="Enter your Last Name">
                            </div>
			           </div>
			           <div class="control-group">
			            	<label class="control-label" for="newpassword">Password</label>
                            <div class="controls">
                                <input type="password" id="newpassword" name="newpassword" placeholder="Enter your new Password" />
                            </div>
			           </div>
			           <div class="control-group">
			            	<label class="control-label" for="confpassword">Confirm Password</label>
                            <div class="controls">
                                <input type="password" id="confpassword" name="confirmnewpass" placeholder="Confirm your new Password" />
                            </div>
                            <br>
                            <button type="submit" class="btn">Update Profile</button>
						</div>
		            </form>
              </div> <!--span8-->
            </div><!--rowfluid spans-->
    	</div><!-- end content -->
    
    	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer" />
    	</div><!-- end footer -->
	</div>
</body>
</html>