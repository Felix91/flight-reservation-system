<%@ page import="java.util.*"%>

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
						<form id="login" class="form-horizontal" action="/login"
							method="post">
							<div class="control-group">
								<label class="control-label" for="li_email">Email</label>
								<div class="controls">
									<input class="required2" type="text" id="li_email"
										name="username" placeholder="Enter your Email">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="li_password">Password</label>
								<div class="controls">
									<input class="required2" type="password" id="li_password"
										name="password" placeholder="Enter your Password">
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<label class="checkbox"> <input type="checkbox">
										Remember me
									</label>
									<div id="passordError" class="text-required">
									<%
										if (request.getAttribute("errorMsg") == "passwordIncorrect") {
									%>
									User email or password incorrect
									<%} %>
									</div>
									<button type="submit" class="btn">Sign in</button>
								</div>
							</div>
							
						</form>
					</div>
					<!-- span6 -->
					<div class="span6">
						<h3>SIGN UP</h3>
						<form id="signup" class="form-horizontal" action="/signedup"
							method="post">
							<div class="control-group">
								<label class="control-label" for="fname">First Name </label>
								<div class="controls">
									<input class="required1" type="text" id="su_fname" name="fname"
										placeholder="Enter your First Name"
										<%=request.getAttribute("defFName") != null ? "value="
					+ (String) request.getAttribute("defFName") : ""%>>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="lname">Last Name</label>
								<div class="controls">
									<input class="required1" type="text" id="su_lname" name="lname"
										placeholder="Enter your Last Name"
										<%=request.getAttribute("defLName") != null ? "value="
					+ (String) request.getAttribute("defLName") : ""%> />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="email">Email</label>
								<div class="controls">
									<input class="required1" type="text" id="su_email" name="email"
										placeholder="Enter your Email"
										<%=request.getAttribute("defEmail") != null ? "value="
					+ (String) request.getAttribute("defEmail") : ""%> />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="newpassword">Password</label>
								<div class="controls">
									<input class="required1" type="password" id="su_newpassword"
										name="newpassword" placeholder="Enter your Password"
										<%=request.getAttribute("newpassword") != null ? "value="
					+ (String) request.getAttribute("newpassword") : ""%> />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="su_confpassword">Confirm
									Password</label>
								<div class="controls">
									<input class="required1" type="password" id="su_confpassword"
										name="confirmnewpass" placeholder="Confirm your Password"
										<%=request.getAttribute("confirmnewpass") != null ? "value="
					+ (String) request.getAttribute("confirmnewpass") : ""%> />
								</div>
								<br>
								<div id="signupError" class="text-required">
									<%
										if (request.getAttribute("errorMsg") == "passwordMismatch") {
									%>
									Passwords Do Not Match
									<%
										} else if (request.getAttribute("errorMsg") == "EmailExists") {
									%>
									Email already associated with an account
									<%
										}
									%>
								</div>
								<button type="submit" class="btn">Sign Up</button>
							</div>
						</form>
						<script type="text/javascript">
							$(function(){
								$('#signup').submit(function(){
									if ( !$('#su_email').val() ){
										$("#signupError").html("Please provide an email");
										return false;
									}else if(!$('#su_newpassword').val()){
										$("#signupError").html("Please provide a password");
										return false;
									}else if($('#su_confpassword').val()=="" || $('#su_newpassword').val() != $('#su_confpassword').val()){
										$("#signupError").html("Make sure passwords match");
										return false;
									}else if (!$('#su_fname').val()){
										$("#signupError").html("Please provide a first Name");
										return false;
									}else if (!$('#su_lname').val()){
										$("#signupError").html("Please provide a last Name");
										return false;
									}else{
										return true;
									}
								})
							})
						</script>
					</div>
				</div>
				<!-- row -->
			</div>
			<!-- span12 -->
		</div>
		<!-- rowfluid  -->

	</div>
	<div id="footer">
		<jsp:include page="/_footer" />
	</div>
	<!-- page generated at: <%//out.print(request.getAttribute("date"));%>-->
</body>
</html>