package ufly.frs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.User;

@SuppressWarnings("serial")
public class Includer extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException,IOException
	{
		String pageToInclude= getServletConfig().getInitParameter("page");
		if(pageToInclude.equals("footer") )
		{
			req.getRequestDispatcher("/_footer.jsp")
				.include(req,resp);
		}else if (pageToInclude.equals("header") )
		{
			req.getRequestDispatcher("/_head.jsp")
				.include(req,resp);
		}else if ( pageToInclude.equals("navbar") )
		{
			User u = getLoggedInUser(req.getSession());
			if ( u != null)
			{
				req.setAttribute("userEmailAddress", u.getEmailAddr());
			}
			req.getRequestDispatcher("/_navBar.jsp")
				.include(req,resp);
		}
			
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException,IOException		
	{
		doGet(req,  resp);
	}
}