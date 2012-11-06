package ufly.frs_test;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ufly.entities.PMF;
import ufly.entities.Seat;

@SuppressWarnings("serial")
public class seatTest extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("SeatTest.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		int row = Integer.parseInt(req.getParameter("rowNumber"));
		int col= Integer.parseInt(req.getParameter("columnNumber"));
	
		Seat newSeat = new Seat(row, col); 
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
            pm.makePersistent(newSeat);
            
        } finally {
            pm.close();
        }
		
		//resp.setContentType("text/plain");
		//resp.getWriter().println("City: "+email+" your callsign is "+ password);
		resp.sendRedirect("/entityTest?test=Seat");
	}
}
