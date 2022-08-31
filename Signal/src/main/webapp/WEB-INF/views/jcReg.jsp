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
	body{
		width: 800px;
	}
</style>
</head>
<body>
	<form action="jcReg.do" method="post">
	<table>		
		<tr>
			<th>직무 중분류</th>
			<td><input type="hidden" name="jp_no" value="${jp_no}"/><input type="text" name="jc_name"/></td>
		</tr>				
		<tr>
			<th colspan="2"><input type="submit" value="등록"/><input type="button" onclick="window.close()" value="닫기"/></th>
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