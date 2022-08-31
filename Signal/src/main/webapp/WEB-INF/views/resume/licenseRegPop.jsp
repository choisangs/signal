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
	<form action="licenseReg.do" method="post">
	<table>
		<tr>
			<th>분류</th>
			<td><input type="text" name="li_field"/></td>
			<th>이름</th>
			<td><input type="text" name="li_name"/></td>
			<th>취득일</th>
			<td><input type="text" name="li_org"/></td>
		</tr>
		<tr>
			<th>발행기관</th>
			<td colspan="5">
				<input type="text" name="li_date"/>
				<input type="hidden" name="re_no" value="${re_no }">
			</td>
		</tr>
		<tr>
			<th colspan="6"><input type="submit" value="등록"/><input type="button"  onclick="window.close()" value="닫기"/></th>
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