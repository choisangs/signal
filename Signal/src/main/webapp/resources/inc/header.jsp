<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="../resources/favicon.png" rel="icon">
<link rel="stylesheet" href="resources/css/common2.css" type="text/css"/>
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
		<a href="/"><img src="resources/images/logo2.gif" alt="Signal" style="margin:20px 10px 0 10px;"width="200" height="130"/></a>
		    <span class="titleA">그</span><span class="titleB">날의</span><span class="titleA">분</span><span class="titleB">위기</span>
		    <span class="titleC">(ft.See그날)</span>
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
    <div id="wrap">
    	<div id="aside">
        	<nav>
				<c:if test="${sessionScope.isClient.equals('true')}">
				<ul class="sidemenu">
					<li><a href="/clientInfoManagement.do">개인정보관리</a></li>
	                <li><a href="/resumeList.go">이력서</a></li>
	                <li><a href="/recommendMe.go">회원추천</a></li>
	                <li><a href="/clientApplyList.go">입사지원현황</a></li>
	                <li><a href="/clientOfferList.go">입사제안현황</a></li>
	                <li><a href="/interviewList.go">면접현황</a></li>
	                <li><a href="/clientObjectionList.go">이의제기현황</a></li>
	                <li><a href="/selfInsert.go">셀프평가</a></li>
				</ul>
				</c:if>
		        <c:if test="${sessionScope.isCompany.equals('true')}">
		            <ul style="height:366px;" class="sidemenu">
		                <li><a href="/companyInfoManagement.do">기업회원정보관리</a></li>
		                <li><a href="/companyInfo.go">기업정보관리</a></li>
		                <li><a href="/jobPostingList.go">채용공고관리</a></li>
		                <li><a href="/companyOfferList.do">입사제안관리</a></li>
		                <li><a href="/companyApplyList.go">지원자면접관리</a></li>
		                <li><a href="/comObjectionList.go">이의제기현황</a></li>
		            </ul>
		        </c:if>
		        <c:if test="${sessionScope.isAdmin.equals('true')}">
		            <ul class="sidemenu">		            
		                <li><a href="/adminManagementList.go" onclick="window.open(this.href, '_blank', 'width=800, height=600'); return false;">관리자계정관리</a></li>
		                <li><a href="/jobClassReg.go">직무분류관리</a></li>
		                <li><a href="/selfTestQue.go">셀프평가관리</a></li>
		                <li><a href="/interviewTestQue.go">면접평가관리</a></li>
		                <li><a href="/adminObjectionList.go">이의제기관리</a></li>
		                <li><a href="/adminBlindList.go">블라인드관리</a></li>
		                <li><a href="/clientManagementList.do">개인회원관리</a></li>
		                <li><a href="/companyManagementList.do">기업회원관리</a></li>
		            </ul>
		        </c:if>
			</nav>
    	</div>
     </div>
</body>
<script>
	//로그인 팝업창 by 상인
	function showPopup() {
		window.open("loginPopup.go","", "width=600, height=400, left=100, top=50");
	
	}

</script>
</html>
