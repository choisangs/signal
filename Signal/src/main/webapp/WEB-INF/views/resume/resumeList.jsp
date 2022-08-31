<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../../resources/inc/header.jsp" %>
<style>
	#section {
		width : 800px;
		position: relative;
		top : -380px;
		left : 350px;
	}
	
	button {
	  	padding:5px 10px;
	  	font-size:12px;
	  	outline:none;
	  	border:none;
	  	color:#fff;
	  	background-color:#333;
	}
	
	.hidden {
		display : none;
	}
	
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
</style>
<body>	
	<div id="section">
	<h2>이력서 리스트</h2>
		<div>
			<button onclick="location.href='resumeReg.go'">신규등록</button>
			<button onclick="resumeUpdate()">수정보완</button>
			<button onclick="resumeDelete()">삭제</button>
		</div>
		<br>
	<table>
		<thead>
			<tr>
				<th></th>
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
					<td><a href="resumeDetail.do?re_no=${resume.re_no}">${resume.re_title}</a></td>
					<td>${resume.jp_name}</td>
					<td>${resume.jc_name}</td>
					<td>${resume.re_regDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
<script>
function resumeUpdate(){
	var re_no = $('input[type=radio]:checked').val();
	if(re_no>0){
		location.href='resumeUpdate.go?re_no='+re_no;	
	}else{
		alert("수정,보완할 이력서를 선택하세요!");   
	}	
}

function resumeDelete(){
	var re_no = $('input[type=radio]:checked').val();
	if(re_no>0){
		location.href='resumeDelete.go?re_no='+re_no;	
	}else{
		alert("삭제할 이력서를 선택하세요!");   
	}	
}

var msg = "${success}";
if(msg){
	alert("삭제가 완료되었습니다");
	location.reload();
}

</script>
</html>