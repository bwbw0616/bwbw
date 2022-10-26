package kr.or.ddit.commons;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsonView.do")
public class JsonMarshallingViewServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		// 응답데이터를 json으로 표현 방식 변경. - Marshalling(native언어로 표현된 데이터를 범용 표현 방식의 언어로 바꾸는 과정)
//		JSON(JavaScript Object Notation)
//		{"expression":123123}	{"expression": "6! = 720" , }
		StringBuffer json = new StringBuffer();
		String jsonPtrn = "\"%s\":\"%s\" , ";
		json.append("{");
		Enumeration<String> attrNames = req.getAttributeNames();
		while (attrNames.hasMoreElements()) {
			String key = (String) attrNames.nextElement();
			Object value = req.getAttribute(key);
			json.append(String.format(jsonPtrn, key, value));
		}
		int lastIndex = json.lastIndexOf(", ");
		if (lastIndex >= 0) {
			json.deleteCharAt(lastIndex);
		}
		json.append("}");
		PrintWriter out = resp.getWriter();
		out.println(json); // Serialize : 데이터를 전송 혹은 저장하기 위해 바이트 배열의 형태로 변환하는 작업.
	}
}
