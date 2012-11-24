package ufly.frs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.*;

@SuppressWarnings("serial")
public class FlightManagerProfile extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{	
			FlightManager loggedInUser = null;
			try {
				loggedInUser = (FlightManager) getLoggedInUser(req.getSession());
			}catch (ClassCastException e){
				resp.sendRedirect("/?errorMsg=Sorry, must be logged in as a flight manager to view that page");
			}
			catch (UserInactivityTimeout e) {
				resp.sendRedirect("/?errorMsg=Sorry, you have been logged out because you have been inactive too long");
			}		
			if (loggedInUser!=null)
			{
				req.getRequestDispatcher("/flightManagerProfile.jsp")
				.forward(req,resp);
			}
			else{
				resp.sendRedirect("/");
			}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		
		resp.setContentType("text/plain");
		
		
	}
}
