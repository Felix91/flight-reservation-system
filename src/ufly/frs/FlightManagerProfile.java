package ufly.frs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.Customer;

@SuppressWarnings("serial")
public class FlightManagerProfile extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{	

			if (getLoggedInUser(req.getSession())!=null)
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
