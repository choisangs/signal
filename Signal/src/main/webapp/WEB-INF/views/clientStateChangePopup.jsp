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
	<form action="clientStateChange.do" method="post" onsubmit="clientStateUpdate()">
		<input type="hidden" name="cl_id" value="${clientState.cl_id}"/>
		<table>
			<tr>
	            <th>아이디</th>
	            <td>${clientState.cl_id}</td>
	        </tr>
	        <tr>
	            <th>이름</th>
	            <td>${clientState.cl_name}</td>
	        </tr>
	        <tr>
	            <th>회원상태</th>
	            <td>
	            	<select name="cl_state" id="cl_state">
	            		<option ${clientState.cl_state == '개인회원' ? 'selected="selected"' : ''}>개인회원</option>
	            		<option ${clientState.cl_state == '탈퇴회원' ? 'selected="selected"' : ''}>탈퇴회원</option>
	            	</select>
	            </td>
	        </tr>
	        <tr>
	            <th>수정사유</th>
	            <td>
	            	<input type="text"  name="cl_admin_re" id="cl_admin_re" value="${clientState.cl_admin_re}" style="width:300px;"/>
	            </td>
	        </tr>
		</table>
		<br>
		<input type="submit" value="수정"/>
	</form>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>
	function clientStateUpdate(){
		var msg = "${msg}";
		if(msg){
			opener.document.location.href="redirect:/clientManagementList.do";
			self.close();
		}
	}
	
	//팝업창 닫기
	var pclose = "${pclose}";
	if(pclose){
		opener.document.location.reload();
		window.close();
	}
</script>
</html>