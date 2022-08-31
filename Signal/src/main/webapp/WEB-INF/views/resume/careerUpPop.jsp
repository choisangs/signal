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
body{
		width:800px;
	}

</style>
</head>
<body>
	<form action="careerUp.do" method="post">	
	<table>	
		<thead>
			<tr>
				<th class="hidden"></th>
				<th>기업명</th>				
				<th>근무기간</th>				
				<th>직무</th>				
			</tr>
		</thead>		
		<tbody>
			<c:forEach items="${careerDto}" var="career">
				<tr>					
					<td class="hidden">
					<input type="text" name="re_no" value="${re_no}">
					<input type="text" name="ca_no" value="${career.ca_no }">
					</td>	
					<td><input type="text" name="ca_com_name" value="${career.ca_com_name}"/></td>
					<td><input type="text" name="ca_period" value="${career.ca_period}"/></td>
					<td><input type="text" name="ca_job" value="${career.ca_job}"/></td>
					</tr>
					<tr>
					<th>근무내용</th>
					<td colspan="2">
					<input type="text" style="width:80%;" name="ca_content" value="${career.ca_content}"/>
					<input type="submit" value="수정"/>
					<input type="button" onclick="careerDelete(${career.ca_no},${re_no})" value="삭제">
					</td>
				</tr>
				<tr><td colspan="3"></td></tr>
			</c:forEach>			
		</tbody>	
		<tr>
			<th colspan="3"><button onclick="window.close()">닫기</button></th>
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

function careerDelete(no,re_no){	
	location.href="careerDelete.do?ca_no="+no+"&&re_no="+re_no;
}

var result = "${result}";
if(result){
	alert("삭제가 완료되었습니다");
	window.opener.location.reload();	
}
</script>
</html>