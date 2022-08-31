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
	width:1200px;
	}
	</style>
</head>
<body>	
	<table>
	<thead>
		<tr>
			<th style="width:15%">기업명</th>			
			<th style="width:20%">공고제목</th>			
			<th style="width:7.5%">직군</th>			
			<th style="width:7.5%">직업</th>			
			<th style="width:15%">시작일</th>			
			<th style="width:15%">마감일</th>			
			<th style="width:10%">코멘트 작성율</th>	
			<th style="width:10%">바로가기</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="post">
			<tr>
				<td>${post.com_name }</td>
				<td>${post.jpo_title }</td>
				<td>${post.jp_name }</td>
				<td>${post.jc_name }</td>
				<td>${post.jpo_start }</td>
				<td>${post.jpo_deadline }</td>
				<td>${post.rankCom }%</td>
				<td><button onclick="jobPostGo(${post.jpo_no},'${post.com_id }')">공고 바로가기</button></td>
			</tr>
		</c:forEach>
	</tbody>
		<tr>
			<th colspan="8"><input type="button" onclick="window.close()" value="닫기"/></th>
		</tr>
	</table>	
</body>
<script>
	function jobPostGo(jpo_no,com_id){
		window.opener.location.href="PostingDetailMain.go?jpo_no="+jpo_no+"&&com_id="+com_id+"&&curState=2";
		window.close(); 
	}

</script>
</html>