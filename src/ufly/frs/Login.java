package ufly.frs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.Customer;
import ufly.entities.FlightManager;
import ufly.entities.FlightStaff;
import ufly.entities.User;

@SuppressWarnings("serial")
public class Login extends UflyServlet {
	//Refresh the page
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{	
		User user=null;
		try {
			user=getLoggedInUser(req.getSession());
		} catch (UserInactivityTimeout e) {
		}
		if(req.getParameter("logout")!= null)
		{
			req.getSession().setAttribute("loggedInUser", null);
			resp.sendRedirect("/");
		}

		else if (user!=null)
		{
			resp.sendRedirect("/");
		}
		
		req.setAttribute("date", new java.util.Date());
		req.getRequestDispatcher("loginnew.jsp")
			.forward(req,resp);
	}
	//Click submit button
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		resp.setContentType("text/plain");
		String email= req.getParameter("username");
		String Password= req.getParameter("password");
		
		FlightManager localFlightManager = FlightManager.getFlightManager(email);
		FlightStaff localFlightStaff = FlightStaff.getFlightStaff(email);
		Customer localUser = Customer.getCustomer(email);
		
		
		//Check if it is Flight Manager
		if(localFlightManager!=null && localFlightManager.checkPassword(Password))
		{
			login(email,req.getSession());
				resp.sendRedirect("/flightManagerProfile");	
		}

		//Check if it is Flight Staff
		if(localFlightStaff!=null && localFlightStaff.checkPassword(Password))
		{
			login(email,req.getSession());
				resp.sendRedirect("/flightStaffProfile");	
		}
	
		//Check if it is Customer
		if (localUser != null && localUser.checkPassword(Password))
		{
			login(email,req.getSession());
			//check to see if in the middle of a flight booking
			if(req.getSession().getAttribute("departopt")!=null )
			{
				resp.sendRedirect("/select");
			}else{
				resp.sendRedirect("/");
			}
		}
		else
		{
			req.setAttribute("failedEmail", email);
			req.setAttribute("errorMsg", "passwordIncorrect");
			req.getRequestDispatcher("loginnew.jsp").forward(req, resp);
		}
		
	}
}
