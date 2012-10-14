<%@ page import="java.util.*" %>

<html>
<head>	
	<title>uFly Login Page</title>
	<link href="logincss.css" type="text/css" rel="stylesheet" />
</head>
<body>
	
	<div id="container">
	<div id="header">
    	<div id="logo">
        	<p>CANADA AIRLINES</p>
        </div>
    	<div id="subheader">
        	<ul>
            	<a href="loginpage.html"><li>Home</li></a>
                <a href="loginpage.html"><li>Search</li></a>
                <a href="loginpage.html"><li>About</li></a>
                <a href="loginpage.html"><li>Contact Us</li></a>
            </ul>
        </div>
    </div>
    <div id="content">
    	<h2>CUSTOMER LOGIN</h2>
       	<div id="signin">
        	<h3>SIGN IN</h3>
            <form action="">
            	<label for="username">Username:</label> <br />   
            	<input type="textfield" name="username" /> <br />
                <label for="password">Password:</label> <br />
                <input type="password" name="password" /> <br />
                <input type="submit" value="Login" /> <br />
            </form>
        		
 		</div>
        <div id="signup">
        	<h3>SIGN UP</h3>
            <form action="">
            	<label for="firstname">First Name:</label>  <br />  
            	<input type="textfield" name="fname" /> <br />
                <label for="lastname">Last Name:</label> <br />   
            	<input type="textfield" name="lname" /> <br />
                <label for="email">E-mail:</label> <br />
                <input type="textfield" name="email" /> <br />
                <label for="newpassword">Password</label> <br />
                <input type="password" name="newpassword" /> <br />
                <label for="confirmnewpass">Confirm Password</label> <br />
                <input type="password" name="confirmnewpass" /> <br />
                <input type="submit" value="Sign Up" /> <br />
            </form>
        </div>       
    </div>
    <div id="footer">
    	<div id="uflylogo">
        	<p class="small">Powered by uFly Airline Reservations Software </p>
        </div>
    	<div id="contactus">
        	<p>Check out our FAQ <br />
               Need help? Call us at 1-888-999-1234 (toll free)<br />
               For calls outside of North America call us at 800-111-2233</p>
        </div> 
    </div>
	</div>
		page generated at: <% out.print(request.getAttribute("date")); %>
	</body>
</html>