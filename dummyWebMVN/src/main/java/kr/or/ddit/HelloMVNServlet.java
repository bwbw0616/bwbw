package kr.or.ddit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
@WebServlet("/test.do")
public class HelloMVNServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> target = new HashMap<>();
		target.put("prop1", "TEXT_VALUE");
		target.put("prop2", 23);
		target.put("prop3", false);
		target.put("prop4", new String[] {"a", "b"});
		
//		1. JSON marshalling
		ObjectMapper jsonMapper = new ObjectMapper();
		String json = jsonMapper.writeValueAsString(target);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json); // serialization
//		req.getRequestDispatcher("/01/test.jsp").forward(req, resp);
	}
}

















