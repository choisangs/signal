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
		width: 700px;
	}
</style>
</head>
<body>
		<input type="hidden" name="ad_id" value="${adminState.ad_id}"/>
		<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>상태</th>
					<th>상태변경날짜</th>
					<th>상태변경사유</th>
				</tr>
			</thead>
			<tbody>
			<tr>
	            <td>${adminState.ad_id}</td>
	            <td>${adminState.ad_state}</td>
	            <td>${adminState.ad_date}</td>
	            <td>${adminState.ad_comment}</td>
	        </tr>
			</tbody>
		</table>
</body>
<script>
</script>
</html>