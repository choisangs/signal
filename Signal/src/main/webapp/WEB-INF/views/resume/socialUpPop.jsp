<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="resources/css/common.css" type="text/css"/>
<style>
body{
	width:800px;
	}

.hidden{display:none;}
</style>
</head>
<body>
	<form action="socialUp.do" method="post">	
	<table>	
		<thead>
			<tr>
				<th class="hidden"></th>	
				<th>분류</th>
				<th>이름</th>
				<th>기간</th>
				<th>내용</th>
				<th></th>
			</tr>
		</thead>		
		<tbody>
			<c:forEach items="${socialDto}" var="social">
				<tr>
					<td class="hidden">
					<input type="text" name="soc_no" value="${social.soc_no}">
					<input type="text" name="re_no" value="${re_no}">
					</td>	
					<td><input type="text" name="soc_field" value="${social.soc_field}"/></td>
					<td><input type="text" name="soc_name" value="${social.soc_name}"/></td>
					<td><input type="text" name="soc_period" value="${social.soc_period}"/></td>
					<td><input type="text" name="soc_content" value="${social.soc_content}"/></td>
					<td><input type="submit" value="수정"/><input type="button" onclick="socialDelete(${social.soc_no},${re_no})" value="삭제"></td>
				</tr>
			</c:forEach>			
		</tbody>	
		<tr>
			<th colspan="5"><button onclick="window.close()">닫기</button></th>
		</tr>	
	</table>
	</form>
</body>
<script>
var msg = "${success}";
if(msg){
	alert("수정이 완료되었습니다");
	window.opener.location.reload();	
}

function socialDelete(no,re_no){	
	location.href="socialDelete.do?soc_no="+no+"&&re_no="+re_no;
}

var result = "${result}";
if(result){
	alert("삭제가 완료되었습니다");
	window.opener.location.reload();	
}
</script>
</html>