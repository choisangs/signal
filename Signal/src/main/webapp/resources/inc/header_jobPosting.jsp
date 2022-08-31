<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="../resources/favicon.png" rel="icon">
<link rel="stylesheet" href="resources/css/common.css" type="text/css"/>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../../resources/js/jquery.twbsPagination.js"></script>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<style>
	.titleA{
	position:relative;
	font-size:80px;
	font-weight:bold;
	color:#B8860B;
	margin-bottom:20px;
}

.titleB{
	position:relative;
	font-size:50px;
	color:#191970;
	margin-bottom:20px;
}

.titleC{
	position:relative;
	font-size:30px;
	margin-bottom:20px;
}


</style>
</head>
<body>
	<div id="header">
		<p id="logo">
		    <a href="/"><img src="resources/images/logo2.gif" alt="Signal" style="margin:20px 10px 0 10px;"width="200" height="130"/></a>
		    <span class="titleA">그</span><span class="titleB">날의</span><span class="titleA">분</span><span class="titleB">위기</span>
		    <span class="titleC">(ft.See그날)</span>
		</p>
		<ul class="nav">
		    <li><a href="/jobPostingMain.go">채용공고</a></li>
		    <li><a href="/personList.go">인재채용</a></li>
		</ul>
		<hr style="border: solid 1px black;"/>
		<c:if test="${sessionScope.isClient.equals('true') || sessionScope.isCompany.equals('true') || sessionScope.isAdmin.equals('true')}">
			<div id="container" style="color:white">  
		        <aside>
		        	<strong>${sessionScope.loginId}</strong>님 반갑습니다 
		        	<br>
		        	<c:if test="${sessionScope.isClient.equals('true')}">
		        		<a href="/clientInfoManagement.do">마이페이지</a> 
		        	</c:if>
		        	<c:if test="${sessionScope.isCompany.equals('true')}">
		        		<a href="/companyInfoManagement.do">마이페이지</a> 
		        	</c:if>
		        	<c:if test="${sessionScope.isAdmin.equals('true')}">
		        		<a href="/clientManagementList.do">마이페이지</a> 
		        	</c:if>
		        	<span>|</span> <a href="logout.do">로그아웃</a>
		        </aside>
			</div>
		</c:if>
		<c:if test="${!sessionScope.isClient.equals('true') && !sessionScope.isCompany.equals('true') && !sessionScope.isAdmin.equals('true')}">
		    <input type="button" class="login" value="로그인" onclick="showPopup()"/>
		    <input type="button" class="join" value="회원가입" onclick="location.href='joinSelect.go'"/>
		</c:if>
	</div>
    
</body>
<script>
	//로그인 팝업창 by 상인
	function showPopup() {
		window.open("loginPopup.go","", "width=600, height=400, left=100, top=50");
	
	}

</script>
</html>
