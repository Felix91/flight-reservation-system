package ufly.frs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.FlightBooking;

@SuppressWarnings("serial")
public class AdminFlightbookings extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{	
		
		//if (getLoggedInUser(req.getSession())!=null)
		//{
		//	Customer loggedInCustomer = Customer.getCustomer(getLoggedInUser(req.getSession()).getEmailAddr());
		//	req.setAttribute("customerFirstName", loggedInCustomer.getFirstName());
		//	req.setAttribute("customerLastName", loggedInCustomer.getLastName());
		
		String pageToInclude= getServletConfig().getInitParameter("action");
		if(pageToInclude.equals("create") )
		{
			req.getRequestDispatcher("/adminFlightbookings_create.jsp")
			.forward(req,resp);
		}else if (pageToInclude.equals("edit") )
		{
			String confirmationNumber = (String) req.getParameter("confirmationNumber");
			Long confirmNumber = Long.valueOf(confirmationNumber);
			if(confirmNumber != null){
				FlightBooking editFlightbooking = FlightBooking.getFlightBooking(confirmNumber);
				if(editFlightbooking != null){
					req.setAttribute("editFlightbooking", editFlightbooking);
					req.getRequestDispatcher("/adminFlightbookings_edit.jsp")
					.forward(req,resp);
				}
			}
		}else{
		
		List<FlightBooking> allFlightbookings = FlightBooking.getAllFlightBookings();
		req.setAttribute("allFlightbookings", allFlightbookings);
			req.getRequestDispatcher("/_adminFlightbookings.jsp")
			.include(req,resp);
		}
		//}
		//else{
		//	resp.sendRedirect("/");
		//}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		resp.setContentType("text/plain");
		
		
	}
}
