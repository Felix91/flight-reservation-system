package ufly.frs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import ufly.entities.FlightBooking;

@SuppressWarnings("serial")
public class CustomerFlightbookings extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{	
		
		//if (getLoggedInUser(req.getSession())!=null)
		//{
		//	Customer loggedInCustomer = Customer.getCustomer(getLoggedInUser(req.getSession()).getEmailAddr());
		//	req.setAttribute("customerFirstName", loggedInCustomer.getFirstName());
		//	req.setAttribute("customerLastName", loggedInCustomer.getLastName());
		
		String pageToInclude= getServletConfig().getInitParameter("action");
		if(pageToInclude.equals("index") )
		{
			List<FlightBooking> allFlightbookings = FlightBooking.getAllFlightBookings();
			req.setAttribute("allFlightbookings", allFlightbookings);
			req.getRequestDispatcher("/customerFlightbookings.jsp")
			.forward(req,resp);
		}else if (pageToInclude.equals("edit") )
		{
			//String confirmationNumber = req.getParameter("confirmationNumber");
			
			//FlightBooking editFlight = FlightBooking.getFlightBooking(confirmationNumber);
			//if(editFlight != null){
				//req.setAttribute("editFlight", editFlight);
			//	req.getRequestDispatcher("/adminFlightbookings_edit.jsp")
			//		.include(req,resp);
			//}
		}else if (pageToInclude.equals("show") )
		{
			String confirmationNumber = (String) req.getParameter("confirmationNumber");
			Long confirmNumber = Long.valueOf(confirmationNumber);
			if(confirmNumber != null){
				FlightBooking showFlightbooking = FlightBooking.getFlightBooking(confirmNumber);
				if(showFlightbooking != null){
					req.setAttribute("showFlightbooking", showFlightbooking);
					req.getRequestDispatcher("/customerFlightbookings_show.jsp")
					.forward(req,resp);
				}
			}
			
		}else{
		
		List<FlightBooking> allFlightbookings = FlightBooking.getAllFlightBookings();
		req.setAttribute("allFlightbookings", allFlightbookings);
			req.getRequestDispatcher("/customerFlightbookings.jsp")
			.forward(req,resp);
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
