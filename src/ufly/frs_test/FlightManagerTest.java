package ufly.frs_test;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ufly.entities.FlightManager;

@SuppressWarnings("serial")
public class FlightManagerTest extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("FlightManagerTest.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		String email = req.getParameter("emailAddr");
		String password = req.getParameter("password");
		
		FlightManager newFlightManager = new FlightManager(email, password); 
		
		
		//resp.setContentType("text/plain");
		//resp.getWriter().println("City: "+email+" your callsign is "+ password);
		resp.sendRedirect("/entityTest?test=FlightManager");
	}
}
