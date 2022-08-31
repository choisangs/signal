<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="resources/css/common.css" type="text/css"/>
<style>
	input[type="submit"] {
		margin : 5px;
	}
	table {
		width: 70%;
	}
	
	body{width:500px;}
	
</style>
</head>
<body>
	<form action="adminManagementList.do" method="post">
	<table>		
		<tr>
			<th>관리자 비밀번호</th>
			<td><input type="text" name="superPw"/></td>
		</tr>
		<tr>
			<th colspan="2">
			<input type="submit" value="완료">
			<input type="button" onclick="window.close()" value="닫기">
			</th>
		</tr>		
	</table>
	</form>
</body>
<script>
	var success = "${success}";
	console.log(success);
	if(success){		
		window.opener.location.href="/adminManagementListReal.Go"
		window.close(); 
	} 

</script>
</html>