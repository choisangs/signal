<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="resources/css/common.css" type="text/css"/>
<style>
.hidden{display:none;}
body {
	width:800px;
}
</style>
</head>
<body>
	<form action="recommendUp.do" method="post">	
	<table>	
		<thead>
			<tr>
				<th></th>
				<th class="hidden"><input type="hidden" name="re_no" value="${re_no}"/></th>
				<th class="hidden"><input type="hidden" name="reco_no_chk" value="${reco_no}"/></th>
				<th>추천인</th>				
				<th>추천날짜</th>				
				<th>추천내용</th>				
			</tr>
		</thead>		
		<tbody>
			<c:forEach items="${recommendDto}" var="reco">
				<tr>
					<td><input type="radio" name="reco_no" value="${reco.reco_no }"></td>	
					<td>${reco.reco_cl_id}</td>
					<td>${reco.reco_date}</td>
					<td>${reco.reco_req_memo}</td>
					</tr>				
			</c:forEach>			
		</tbody>	
		<tr>
			<th colspan="4"><input type="submit" value="선택"><button onclick="window.close()">닫기</button></th>
		</tr>	
	</table>
	</form>
</body>
<script>
var msg = "${success}";
if(msg){
	alert("선택이 완료되었습니다");
	window.opener.location.reload();
	window.close(); 
}
</script>
</html>