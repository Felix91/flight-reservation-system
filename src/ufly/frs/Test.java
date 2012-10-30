package ufly.frs;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ufly.entities.Airport;
@SuppressWarnings("serial")
public class Test extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException 
    {
		
		String test=request.getParameter("test");
		switch(test){
		case "Airport":
			testAirport(request,response);
			return;
		}
		response.setContentType("text/plain");
		response.getWriter().println("<h2>welcome to the test servlet</h2>");
	
    }
	private void testAirport(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if(request.getParameter("callSign")==null)
		{
			
			List<Airport> la = Airport.getAllAirports();
			Iterator<Airport> it = la.iterator();
			response.getWriter().println("<ul>");
			while (it.hasNext())
			{
				response.getWriter().println("<li>"+it.next().toString()+"</li>");
			
			}
			response.getWriter().println("</ul>");
		}
		else
		{
			String city = request.getParameter("city");
			String csign = request.getParameter("callSign");
			if(city ==null || csign == null)
			{
				response.getWriter().println("Need both city or callsign");
				return;
			}
			Airport a = new Airport(csign,city);
			response.sendRedirect("/entityTest");
		}
	}
}