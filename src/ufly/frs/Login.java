package ufly.frs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.User;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException
	{	
		if(req.getParameter("logout")!= null)
		{
			HttpSession session = req.getSession();
			User loggedInUser = User.getLoggedInUser(session);
			loggedInUser.logout();
			resp.sendRedirect("/");
		}
		else if (User.getLoggedInUser(req.getSession())!=null)
		{
			resp.sendRedirect("/search");
		}
		try {
			req.setAttribute("date", new java.util.Date());
			req.getRequestDispatcher("login.jsp")
				.forward(req,resp);
		} catch (ServletException e) {
			resp.setContentType("text/plain");
			resp.getWriter().println("page does not exist:login.jsp");
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException
	{
		resp.setContentType("text/plain");
		String UserName= req.getParameter("username");
		String Password= req.getParameter("password");
		resp.getWriter().println("Hello "+UserName+" your password is "+ Password);

	}
}
