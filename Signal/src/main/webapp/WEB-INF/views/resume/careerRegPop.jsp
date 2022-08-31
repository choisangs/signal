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
</style>
</head>
<body>
	<form action="careerReg.do" method="post">
	<table>
		<tr>
			<th>기업명</th>
			<td><input type="text" name="ca_com_name"/></td>
			<th>근무기간</th>
			<td><input type="text" name="ca_period"/></td>
			<th>직무</th>
			<td><input type="text" name="ca_job"/></td>
		</tr>
		<tr>
			<th>근무내용</th>
			<td colspan="5">				
				<input type="text" name="ca_content"/>
				<input type="hidden" name="re_no" value="${re_no }">
			</td>
		</tr>
		<tr>
			<th colspan="6"><input type="submit" value="등록"/><input type="button" onclick="window.close()" value="닫기"/></th>
		</tr>
	</table>
	</form>
</body>
<script>
	var msg = "${success}";
	if(msg){
		alert("등록이 완료되었습니다");
		window.opener.location.reload();
		window.close(); 
	}

</script>
</html>