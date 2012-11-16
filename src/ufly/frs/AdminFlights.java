package ufly.frs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.Flight;

@SuppressWarnings("serial")
public class AdminFlights extends UflyServlet {
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
			req.getRequestDispatcher("/adminFlights_create.jsp")
			.forward(req,resp);
		}else if (pageToInclude.equals("edit") )
		{
			String flightKey = req.getParameter("flightKey");
			
			Flight editFlight = Flight.getFlight(flightKey);
			if(editFlight != null){
				req.setAttribute("editFlight", editFlight);
				req.getRequestDispatcher("/adminFlights_edit.jsp")
					.include(req,resp);
			}
		}else{
		
		List<Flight> allFlights = Flight.getAllFlights();
		req.setAttribute("allFlights", allFlights);
			req.getRequestDispatcher("/_adminFlights.jsp")
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
