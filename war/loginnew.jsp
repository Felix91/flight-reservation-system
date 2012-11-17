<%@ page import="java.util.*" %>

<html>
<head>
	<title>uFly Login Page</title>
	<jsp:include page="/_header" />
</head>
<body>
		<jsp:include page="/_navbar" />
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<h2>CUSTOMER LOGIN</h2>
				  <div class="row-fluid">
				  	<div class="span6">

				        	<h3>SIGN IN</h3>
				            <form id="login"class="form-horizontal" action="/login" method="post">
				            	<div class="control-group">
		                            <label class="control-label" for="in_from">Email</label>
		                            <div class="controls">
		                                <input class="required2" type="text" id="in_from" name="username" placeholder="Enter your Email">
		                            </div>
                        		</div>
                        		<div class="control-group">
		                            <label class="control-label" for="in_from">Password</label>
		                            <div class="controls">
		                                <input class="required2"type="password" id="in_from" name="password" placeholder="Enter your Password">
		                            </div>
                        		</div>
                        		<div class="control-group">
								    <div class="controls">
								      <label class="checkbox">
								        <input type="checkbox"> Remember me
								      </label>
								      <button type="submit" class="btn">Sign in</button>
								    </div>
  								</div>
				            </form>
				 	</div><!-- span6 -->
				    <div class="span6">
				        	<h3>SIGN UP</h3>
				            <form id="signup"class="form-horizontal" action="/signedup" method="post">
					           <div class="control-group">
					            	<label class="control-label" for="fname">First Name *</label>
		                            <div class="controls">
		                                <input class="required1"type="text" id="fname" name="fname" placeholder="Enter your First Name">
		                            </div>
		                            <% String a = (String)request.getAttribute("defFName");
					            	 if (a!= null) out.print("value="+(String)request.getAttribute("defFName")); %>
                        	   </div>
					           <div class="control-group">
					            	<label class="control-label" for="lname">Last Name</label>
		                            <div class="controls">
		                                <input class="required1"type="text" id="lname" name="lname" placeholder="Enter your Last Name" <%=request.getAttribute("defLName")!=null ? "value="+(String)request.getAttribute("defLName") :""%> />
		                            </div>
					           </div>
					           <div class="control-group">
					            	<label class="control-label" for="email">Email</label>
		                            <div class="controls">
		                                <input class="required1" type="text" id="email" name="email" placeholder="Enter your Email" <%=request.getAttribute("defEmail")!=null ? "value="+(String)request.getAttribute("defEmail") :""%> />
		                            </div>
					           </div>
					           <div class="control-group">
					            	<label class="control-label" for="newpassword">Password</label>
		                            <div class="controls">
		                                <input class="required1" type="password" id="newpassword" name="newpassword" placeholder="Enter your Password" />
		                            </div>
					           </div>
					           <div class="control-group">
					            	<label class="control-label" for="confpassword">Confirm Password</label>
		                            <div class="controls">
		                                <input class="required1"type="password" id="confpassword" name="confirmnewpass" placeholder="Confirm your Password" />
		                            </div>
		                            <br>
		                            <button type="submit" class="btn">Sign Up</button>

					           </div>



				            </form>
				            <div id="signupError"><%=(String)request.getAttribute("errorMsg") =="passwordMismatch" ? "<font color=\"red\">Passwords Do Not Match</font>" :"" %></div>
				    </div>
				   </div><!-- row -->
				 </div><!-- span12 -->
		</div><!-- rowfluid  -->

	</div>
	<div id="footer">
    	<jsp:include page="/_footer" />
    </div>
		<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
	</body>
</html>