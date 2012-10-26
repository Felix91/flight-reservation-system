<%@ page import="java.util.*" %>

<html>
<head>	
	<title>uFly Login Page DSKAFJASDLFDS</title>
	<link href="/stylesheets/logincss.css" type="text/css" rel="stylesheet" />
</head>
<body>
	
	<div id="container">
	<div id="header">
    	<jsp:include page="/_header" />
    </div>
    <div id="content">
    	<h2>CUSTOMER LOGIN</h2>
       	<div id="signin">
        	<h3>SIGN IN</h3>
            <form action="/login" method="post">
            	<label for="username">Username:</label> <br />   
            	<input type="textfield" name="username" /> <br />
                <label for="password">Password:</label> <br />
                <input type="password" name="password" /> <br />
                <input type="submit" value="Login" /> <br />
            </form>
        		
 		</div>
        <div id="signup">
        	<h3>SIGN UP</h3>
            <form action="/signedup" method="post">
            	<label for="firstname">First Name:</label>  <br />  
            	<input type="textfield" name="fname"  
            	<% String a = (String)request.getAttribute("defFName"); 
            	 if (a!= null) out.print("value="+(String)request.getAttribute("defFName")); %> /> <br />
                <label for="lastname">Last Name:</label> <br />   
            	<input type="textfield" name="lname" <%=request.getAttribute("defLName")!=null ? "value="+(String)request.getAttribute("defLName") :""%> /> <br />
                <label for="email">E-mail:</label> <br />
                <input type="textfield" name="email" <%=request.getAttribute("defEmail")!=null ? "value="+(String)request.getAttribute("defEmail") :"" %> /> <br />
                <label for="newpassword">Password</label> <br />
                <input type="password" name="newpassword" /> <br />
                <label for="confirmnewpass">Confirm Password</label> <br />
                <input type="password" name="confirmnewpass" /> <br />
                <input type="submit" value="Sign Up" /> <br />
            </form>
            <div id="signupError"><%=(String)request.getAttribute("errorMsg") =="passwordMismatch" ? "<font color=\"red\">Passwords Do Not Match</font>" :"" %></div>
        </div>       
    </div>
    <div id="footer">
    	<jsp:include page="/_footer" />
    </div>
	</div>
		<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
	</body>
</html>