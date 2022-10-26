<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/factorial.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<h4>Factorial 연산 처리</h4>
<!-- 1. Model1 -> Model2 -> ajax -> XML/JSON(Marshalling) -->
2! -> 2*1 = 2, 3! -> 3*2*1 = 6
10!
스크립틀릿 기호만으로 반복 곱하기연산 수행.
<!-- <form action="명령어_요청URL" method="파라미터 전송 방법 & 요청의 목적" entype="요청 데이터의 표현 방식"> -->
<form action="<%=request.getContextPath() %>/04/factorial.do" name="facForm" method="">
<input type="number" name="number" min="0" value="" />
<input type="text" name="dummy" value="asdasdff" />
<input type="submit" value="=" />
</form>

<div id="resultArea">

</div>
<script type="text/javascript">
		let resultArea = $("#resultArea");
		let facForm = $("form[name]").on("submit", function(event){
			event.preventDefault();
			// form의 submit 이벤트의 기본 특성은 동기 요청.
			console.log(event.target);
			console.log(this);
//			$(this) -> jQuery 객체화
//			XMLHttpRequest 객체 활용한 비동기 요청
			let action = this.action;	// $(this).attr("action");
			let method = this.method;
			let data = $(this).serialize();	//	Query String 생성 ex) param1=value1&param2=value2
			console.log(data);
			$.ajax({
				url : action,
				method : method,
				data : data,
				dataType : "html" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
				,success : function(resp, status, jqXHR) {
					//  {"expression":"6! = 720"}
					//  resultArea.html(JSON.stringify(resp));
					
					resultArea.html(resp);
//					alert(resp.test);
				},
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
			return false;			
		});
		console.log(facForm);
</script>
</body>
</html>