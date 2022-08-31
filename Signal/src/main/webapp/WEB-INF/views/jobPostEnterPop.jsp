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
	<div id="section">		
	<table>
		<thead>
			<tr>
				<th>
					<input type="hidden" id="com_id" value="${com_id }"/>
					<input type="hidden" id="jpo_no" value="${jpo_no }"/>
				</th>
				<th>이력서 제목</th>
				<th>직무 대분류</th>
				<th>직무 소분류</th>
				<th>작성일</th>				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="resume">
				<tr>
					<td><input type="radio" name="re_no" value="${resume.re_no}"/></td>
					<td><a href="#" onclick="moveRe('${resume.re_no}')">${resume.re_title}</a></td>
					<td>${resume.jp_name}</td>
					<td>${resume.jc_name}</td>
					<td>${resume.re_regDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<br/>
		<br/>
		<div style="text-align:center;">			
			<button onclick="jobPostEnter()">선택</button>			
		</div>
	</div>
</body>
<script>
function jobPostEnter(){
	var re_no = $('input[type=radio]:checked').val();
	var com_id = '${com_id}';
	var jpo_no = ${jpo_no};
	if(re_no>0){
		location.href="applyTwo.do?jpo_no="+jpo_no+"&re_no="+re_no+"&com_id="+com_id;		
	}else{
		alert("지원할 이력서를 선택하세요!");   
	}	
}

function moveRe(re_no){
	window.opener.location.href="resumeDetail.do?re_no="+re_no;
	window.close();
}


var msg = "${success}";
if(msg){
	window.opener.location.reload();
	alert("지원이 완료되었습니다!");
	window.close();
}

</script>
</html>