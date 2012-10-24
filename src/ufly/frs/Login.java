package ufly.frs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.User;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
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
			req.setAttribute("date", new java.util.Date());
			req.getRequestDispatcher("login.jsp")
				.forward(req,resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException
	{
		resp.setContentType("text/plain");
		String UserName= req.getParameter("username");
		String Password= req.getParameter("password");
		User localUser=User.login(UserName, Password, req.getSession());
		resp.getWriter().println("Hello "+localUser.getFirstName()+" "+localUser.getLastName()+" your password is "+ Password);

	}
}
