package ufly.frs_test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		
		new Flight(flightNumber, origin,destination,departure,arrival,allowableMealTypes,seatingArrangementLayout); // TODO: create Customer once User has proven to work
		// Flight's constructor will automaticallly make object persistent
		/*PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
            pm.makePersistent(newFlight);
            
        } finally {
            pm.close();
        }*/
		
		//resp.setContentType("text/plain");
		//resp.getWriter().println("City: "+city+" your callsign is "+ callsign);
		resp.sendRedirect("/entityTest?test=Flight");
	}
}
