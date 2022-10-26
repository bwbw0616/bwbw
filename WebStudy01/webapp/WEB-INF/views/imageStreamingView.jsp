<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>			<!-- directive -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--    View의 역할? -->
<!--    1) UI 생성 : (model 확보, Controller가 사용한 scope에 저장된 model) -->
<!--    2) 응답 전송 -->
<!-- scriptlet -->
	<%
	StringBuffer options = (StringBuffer) request.getAttribute("options");
	%>
<!-- scriptlet -->
<!DOCTYPE html>
<html>
	<head>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	</head>
	<body>
	<h4>이미지 선택</h4>
		<form action="<%=request.getContextPath() %>/02/streaming.do" id="imageForm">
			<select name="image">
				<%=options %>      <!-- expression -->
			</select>
			<input type="submit" value="전송"/>
		</form>
		<div id="imageArea">
			
		</div>
		<script type="text/javascript">
			let selectTag = $("select[name='image']").on("change", function(){
				$(this.form).trigger("submit");
				
			});
			let imageArea = $('#imageArea');
			const SRCPTRN = "<%=request.getContextPath() %>/02/streaming.do?image=%v";
			let imageForm = $('#imageForm').on("submit",function(event){
				event.preventDefault();
				//<img src="./streaming.do?image=%v" />
				let imageName = $(this.image).val();
				let imgTag = $("<img>").attr("src", SRCPTRN.replace("%v",imageName));
				imageArea.html(imgTag);
				return false;
			});
		</script>
	</body>
</html>