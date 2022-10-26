package kr.or.ddit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DummyServlet extends HttpServlet{
	private String dummy;
	
//	http://localhost/dummy01/dummy/content.do
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String data = "Dummy Servlet!!";
		StringBuffer html = new StringBuffer();
		html.append("<html>");
		html.append("<body>");
//		<h4>Dummy Servlet</h4>
		html.append(String.format("<h4>%s</h4>", data));
		html.append("</body>");
		html.append("</html>");
		resp.getWriter().println(html);
	}
}
