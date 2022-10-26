<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js" integrity="sha512-YUkaLm+KJ5lQXDBdqBqk7EVhJAdxRnVdT2vtCzwPHSweCzyMgYV/tgGF4/dCyqtCC2eCphz0lRQgatGVdfR0ww==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.custom.js"></script>
</head>
<body>
<h4> 비동기 REST 구조를 기반으로 한 메모 서비스 </h4>
<table>
	<thead>
		<tr>
			<th>메모코드</th>
			<th>작성자</th>
			<th>프로필이미지(?)</th>
			<th>내용</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
</table>
<form id="insertForm" method="post" action="${pageContext.request.contextPath}/memo">
	<h4>메모 작성 폼</h4>
	<input type="text" name="writer" />
	<textarea name="content"></textarea>
	<input type="submit" value="메모 저장" />
<!-- 	<input type="file" name="profileImage" />FormData ---> 
</form>
<form id="updateForm" method="post">
	<h4>메모 수정 폼</h4>
	<input type="hidden" name="_method" value="put" />
<!-- 	<input type="hidden" name="code" /> -->
	<input type="text" name="writer" />
	<textarea name="content"></textarea>
	<input type="submit" value="메모 수정" />
<!-- 	<input type="file" name="profileImage" />FormData ---> 
</form>

<script type="text/javascript">
	const MEMOURL = "${pageContext.request.contextPath}/memo/%d";
	let updateForm = $("#updateForm").on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = $(this).find("[name='_method']").val();
		let jsonObj = $(this).formDataToJson();
		$.ajax({
			url : url,
			data : jsonObj,
			method : method,
			processData : false,
			contentType:"application/json;charset=utf-8",
			dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
			,
			success : function(resp, status, jqXHR) {
				let memo = resp;
				let trTag = listBody.find("#memo_" + memo.code);
				trTag.data("memo", memo)
				let innerHTML = makeMemoTr(memo).html();
				trTag.html(innerHTML);
				updateForm.get(0).reset();
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		return false;
	});
	let listBody = $("#listBody").on("click", "tr[id]", function(){
		let memo =  $(this).data("memo");
// 		updateForm.find("[name=writer]").val(memo.writer);
		updateForm.settingFormData(memo);
		updateForm.attr("action", "");
		updateForm.attr("action", MEMOURL.replace("%d", memo.code));		
	}).on("click", ".delBtn", function(event){
		let memoTr = $(this).parents("tr:first");
		let memo = memoTr.data("memo");
		let deleteURL = MEMOURL.replace("%d",memo.code);
		$.ajax({
			url : deleteURL,
			method : "delete",
			dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
			,
			success : function(resp, status, jqXHR) {
				let memo = resp;
				listBody.find("#memo_" + memo.code).remove();
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	});
	let makeMemoTr = function(memo){
		return $("<tr>").append(
					$("<td>").html(memo.code)		
					, $("<td>").html(memo.writer)		
					, $("<td>").html("")
					, $("<td>").html(memo.content)
					, $("<td>").html(
						$("<button>").text("삭제").addClass("delBtn")
						
					)					
				).prop("id", "memo_"+memo.code)
				 .data("memo", memo);							//data를 숨겨놓을거다!
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/memo",
		method : "get",
		dataType : "json" // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
		,
		success : function(resp, status, jqXHR) {
			let trTags = [];
			if(resp && resp.length > 0){
				$.each(resp, function(index,memo){
					let trTag = makeMemoTr(memo);
					trTags.push
				})
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").attr("colspan", "4")
								 .html("작성된 메모 없음.")
					)
				);
			}
			listBody.empty();
			listBody.append(trTags);
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
	
	$("#insertForm").on("submit", function(event){
		event.preventDefault();
		let insertForm = this;
		let url = this.action;
		let method = this.method;
		let jsonObj = $(this).formDataToJson();
		console.log(jsonObj);
		$.ajax({
			url : url
			, method : method
			, processData : false
			, contentType:"application/json;charset=UTF-8"
			, data:jsonObj
			, dataType:"json"
			, success:function(resp){
				let memo = resp;
				let trTag = makeMemoTr(memo)
				listBody.append(trTag);
				insertForm.reset();
			}
		});
		return false;
	});
		
// 	$(document).on("click", "tr", function(){
// 		event.preventDefault();
// 		let updateForm = this;
// 		let url = this.action;
// 		let method = this.method;
// 		let jsonObj = JSON.stringify( {
// 			writer : $("[name=writer]").val()
// 			, content :	$("[name=content]").val()
// 		});
// 		console.log(jsonObj);
// 		$.ajax({
// 			url : url
// 			, method : method
// 			, processData : false
// 			, contentType:"application/json;charset=UTF-8"
// 			, data:jsonObj
// 			, dataType:"json"
// 			, success:function(resp){
// 				let memo = resp;
// 				let trTag = makeMemoTr(memo)
// 				listBody.append(trTag);
// 				updateForm.reset();
// 			}
// 		});
// 		return false;
// 	});
</script>
<!-- REST(XML/JSON)Tful URI(자원의 식별자-명사형) : URI로는 자원을 식별하고, method로 자원에 대한 행위를 표현하는 방식. -->
<!-- /member/memberInsert.do					-> insert라는 행위를 하는 식별자(자원의 식별자가 아님) -->
<!-- /member/form GET -->
<!-- /member POST -->
<!-- /member GET -->
<!-- /member/a001 GET -->
<!-- /member/a001/form GET 		->a001 수정  -->
<!-- /member/a001 PUT -->
<!-- /member/a001 DELETE -->

<!-- /memo GET			->전체 메모가 필요 -->
<!-- /memo/1 GET			->1번 메모만 필요 -->
<!-- /memo/1/form GET			->1번을 수정할 양식 필요 -->
<!-- /memo/1 PUT			->1번을 수정해야겠음 -->
<!-- /memo/form GET			->1번을 수정할 폼이 필요 -->
<!-- /memo POST			->이제 새로 등록할거임 -->
<!-- /memo DELETE			->모두 삭제 -->
<!-- /memo/1 DELETE		->1번 메모만 삭제 -->
</body>
</html>