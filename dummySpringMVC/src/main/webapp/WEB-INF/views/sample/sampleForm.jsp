<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">
		<input type="text" name="param1" required />
		<input type="number" name="param2" />
		<input type="submit" value="전송" />
	</form>
	<pre>
		param1 : ${param1 }
		param2 : ${param2 }
	</pre>
</body>
</html>