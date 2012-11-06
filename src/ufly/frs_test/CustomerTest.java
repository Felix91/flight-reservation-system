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
import ufly.entities.FlightManager;
import ufly.entities.FlightStaff;
import ufly.entities.Customer;


@SuppressWarnings("serial")
public class CustomerTest extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("CustomerTest.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		String emailAddr = req.getParameter("emailAddr");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		
		Customer newCustomer = new Customer(emailAddr, password, firstName, lastName); 
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
            pm.makePersistent(newCustomer);
            
        } finally {
            pm.close();
        }
		
		//resp.setContentType("text/plain");
		//resp.getWriter().println("City: "+emailAddr+" your callsign is "+ password);
		resp.sendRedirect("/entityTest?test=Customer");
	}
}