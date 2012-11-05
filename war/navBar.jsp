 <div class="navbar navbar-inverse">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/index.jsp">Canada Airlines</a>
            <div class="navbar-text dropdown pull-right">
              <a href="#" data-toggle="dropdown" class="navbar-link ">
              Logged in as 
              <%if (request.getAttribute("userEmailAddress")!= null)
            	  { out.print((String)request.getAttribute("userEmailAddress"));
            	  }else{%>
              Anonymous
              <%} %>
              </a>
              <% %>
				<ul aria-labelledby="drop5" role="menu" class="dropdown-menu" id="menu2">
                  <li><a href="/customerProfile.jsp" tabindex="-1">Profile Page</a></li>
                  <li><a href="#" tabindex="-1">Modify a Booking</a></li>
                  <li><a href="#" tabindex="-1">Something else here</a></li>
                  <li class="divider"></li>
                  <li><a href="#" tabindex="-1">Log out</a></li>
                </ul>
			</div>
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
			  <li><a href="#">Search</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul>
        </div> <!--containerfluidnavbar-->
      </div> <!--navbar inner-->
    </div> <!--navbarinverse-->