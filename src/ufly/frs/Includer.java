package ufly.frs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.Customer;
import ufly.entities.FlightManager;
import ufly.entities.FlightStaff;
import ufly.entities.User;

@SuppressWarnings("serial")
public class Includer extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException,IOException
	{
		String pageToInclude= getServletConfig().getInitParameter("page");
		if(pageToInclude.equals("footer") )
		{
			req.getRequestDispatcher("/_footer.jsp")
				.include(req,resp);
		}else if (pageToInclude.equals("header") )
		{
			req.getRequestDispatcher("/_head.jsp")
				.include(req,resp);
		}else if ( pageToInclude.equals("navbar") )
		{
			User u = null;
			try {
				u=getLoggedInUser(req.getSession());
			} catch (UserInactivityTimeout e) {
			}
			if ( u != null)
			{
				String name=u.getDisplayName();
				req.setAttribute("userName", name);
				if(u.getClass().equals(Customer.class)){
					req.setAttribute("userProfilePage", "/customerProfile" );
				}else if(u.getClass().equals(FlightStaff.class)){
					req.setAttribute("userProfilePage", "/flightStaffProfile" );
				}else if(u.getClass().equals(FlightManager.class)){
					req.setAttribute("userProfilePage", "/flightManagerProfile" );
				}

			}
			req.getRequestDispatcher("/_navBar.jsp")
				.include(req,resp);
		}
			
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException,IOException		
	{
		doGet(req,  resp);
	}
}