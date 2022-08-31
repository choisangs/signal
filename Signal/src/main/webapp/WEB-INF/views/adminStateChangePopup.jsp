<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="resources/css/common.css" type="text/css"/>
<link rel="stylesheet" href="resources/css/popup.css" type="text/css"/>
<style>
	table{
		width:500px;
	}
</style>
</head>
<body>
	<form action="adminStateChange.do" method="post" onsubmit="adminStateUpdate()">
		<input type="hidden" name="ad_id" value="${adminState.ad_id}"/>
		<table>
			<tr>
	            <th>아이디</th>
	            <td>${adminState.ad_id}</td>
	        </tr>
	        <tr>
	            <th>이름</th>
	            <td>${adminState.ad_name}</td>
	        </tr>
	        <tr>
	            <th>회원상태</th>
	            <td>
	            	<select name="ad_state" id="ad_state">
	            		<option ${adminState.ad_state == '관리자' ? 'selected="selected"' : ''}>재직</option>
	            		<option ${adminState.ad_state == '탈퇴회원' ? 'selected="selected"' : ''}>퇴사</option>
	            	</select>
	            </td>
	        </tr>
	        <tr>
	            <th>수정사유</th>
	            <td>
	            	<input type="text"  name="ad_comment" id="ad_comment" value="${adminState.ad_comment}" style="width:300px;"/>
	            </td>
	        </tr>
		</table>
		<br>
		<input type="submit" value="수정"/>
	</form>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>
	function adminStateUpdate(){
		var msg = "${msg}";
	}

	// 팝업창 닫기
	var pclose = "${pclose}";
	if(pclose){
		opener.document.location.reload();
		window.close();
	}
</script>
</html>