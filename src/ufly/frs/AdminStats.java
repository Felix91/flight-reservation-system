package ufly.frs;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.Airport;
import ufly.entities.Flight;

@SuppressWarnings("serial")
public class AdminStats extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{	
		
			List<Airport> la = Airport.getAllAirports();
			req.setAttribute("allAirports", la);
			req.getRequestDispatcher("/_adminStats.jsp")
				.include(req,resp);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		resp.setContentType("text/plain");
		
		
	}
}
