package ufly.frs;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")
public class UflySessionCounter extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException 
    {
		HttpSession session = request.getSession();
		Integer sessionCount = (Integer) session.getAttribute("sessionCount");
		if (sessionCount == null)
		{
			sessionCount = 0;
		}
		response.setContentType("text/plain");
		response.getWriter().println("site visit #:"+(sessionCount++).toString());
		session.setAttribute("sessionCount", sessionCount);	
    }
}