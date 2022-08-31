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
		width:800px;
	}
</style>
</head>
<body>
	<form action="interQueReg.do" method="post">
	<table>
		<tr>	
			<th>키워드</th>
			<td>
				<select name="it_keyword">
					<option value="책임감">책임감</option>
					<option value="침착함">침착함</option>
					<option value="적극성">적극성</option>
					<option value="자신감">자신감</option>
					<option value="성실함">성실함</option>
				</select>		
		</tr>
		<tr>
			<th>질문</th>
			<td><input type="text" name="it_que"/></td>
		</tr>				
		<tr>
			<th colspan="4"><input type="submit" value="등록"/><input type="button" onclick="window.close()" value="닫기"/></th>
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