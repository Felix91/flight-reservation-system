package ufly.frs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ufly.entities.User;

@SuppressWarnings("serial")
public class Search extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException ,ServletException
	{
		HttpSession s=req.getSession();
		if (User.getLoggedInUser(req.getSession()) != null)
		{
			req.getRequestDispatcher("Search.jsp").forward(req,resp);
		}else
		{
			resp.sendRedirect("/");
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
