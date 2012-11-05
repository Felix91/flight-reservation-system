package ufly.frs_test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.Airport;
import ufly.entities.PMF;
import ufly.entities.User;
import ufly.entities.Flight;

@SuppressWarnings("serial")
public class FlightTest extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("FlightTest.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		String flightNumber = req.getParameter("flightNumber");
		String origin = req.getParameter("origin");
		String destination = req.getParameter("destination");
		String departure = req.getParameter("departure");
		String arrival = req.getParameter("arrival");
		String allowableMealTypes = req.getParameter("allowableMealTypes");
		String seatingArrangementLayout = req.getParameter("seatingArrangementLayout");		
		
		Flight newFlight = new Flight(flightNumber, origin,destination,departure,arrival,allowableMealTypes,seatingArrangementLayout); // TODO: create Customer once User has proven to work
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
            pm.makePersistent(newFlight);
            
        } finally {
            pm.close();
        }
		
		//resp.setContentType("text/plain");
		//resp.getWriter().println("City: "+city+" your callsign is "+ callsign);
		resp.sendRedirect("/entityTest?test=Flight");
	}
}
