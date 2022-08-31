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
	
	#updatebutton{
	   padding:5px 10px;
	   font-size:15px;
	   outline:none;
	   border:none;
	   color:#fff;
	   background-color:#333;
	   position: relative;
	   left:230px;
	}
</style>
</head>
<body>
	<form action="companyStateChange.do" method="post" onsubmit="companyStateUpdate()">
		<input type="hidden" name="com_id" value="${companyState.com_id}"/>
		<table>
			<tr>
	            <th>아이디</th>
	            <td>${companyState.com_id}</td>
	        </tr>
	        <tr>
	            <th>이름</th>
	            <td>${companyState.com_name}</td>
	        </tr>
	        <tr>
	            <th>회원상태</th>
	            <td>
	            	<select name="com_state" id="com_state">
	            		<option ${companyState.com_state == '기업회원' ? 'selected="selected"' : ''}>기업회원</option>
	            		<option ${companyState.com_state == '탈퇴회원' ? 'selected="selected"' : ''}>탈퇴회원</option>
	            	</select>
	            </td>
	        </tr>
	        <tr>
	            <th>수정사유</th>
	            <td>
	            	<input type="text"  name="com_admin_re" id="com_admin_re" value="${companyState.com_admin_re}" style="width:300px;"/>
	            </td>
	        </tr>
		</table>
		<br>
		<input type="submit" id="updatebutton" value="수정"/>
	</form>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>
	function companyStateUpdate(){
		var msg = "${msg}";
		if(msg){
			opener.document.location.href="redirect:/companyManagementList.do";
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