<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	#create{
	width: 250px;
	height: 100px;
	border: 1px solid lightgray; 
	border-radius: 10px;
	text-align: center;
	color: white;
	font-size:22px;
	font-weight: bold;
	background-color: gray;
	position: relative;
    top: -250px;
    left: 600px;
	}
	
	#comTitle {
    position: relative;
    top: -300px;
    left: 50;
	}
	
	textarea {
	   	width:100%;
	   	resize: none;
	   	height: 500px;
   }
   	table,h2,h6{
   		width: 60%;
   		margin: 0 auto;
   }
   	table,th,td{
	   	border: 1px solid gray;
	   	border-collapse: collapse;
  		padding: 3px;
   }
   	th{
   		font-size: 15px;
   		font-weight: bold;
   		height: 30px;
   		width: 20%;
   	}
		h2 {
	    margin-bottom: 10px;
	    text-align: center;
	    position: relative;
	    top: -350px;
	    left: -35px;
	}
	
	.find-btn{
		text-align: center;
		margin-top: 10px;
    	position: relative;
    	top: -280px;
	}
	
	#comTitle {
    position: relative;
    top: -330px;
    left: 350px;
	}
	
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
	
	#ComInfoDetail{
		position: relative;
		top:-300px;
	}
	
	
</style>
</head>
<body>
<section>
    <div id="jp-list">
	        	<div id="comInfoReg">
					<c:if test="${dto.ci_ceo == null}">
	        			<button id="create" onclick="location.href='/companyInfoWrite.go'">기업정보 등록하기</button>
					</c:if>
	        	</div>
				<c:if test="${dto.ci_ceo != null}">
	        		<h2>기업정보</h2>
		        	<table id="ComInfoDetail">
			    		<tr>
							<th>기업명</th>
							<td>${dto.com_name}</td>
						</tr>
			    		<tr>
							<th>대표자명</th>
							<td>${dto.ci_ceo}</td>
						</tr>
			   			<tr>
							<th>직원수</th>
							<td>${dto.ci_emp}명</td>
						</tr>
			    		<tr>
							<th>홈페이지 주소</th>
							<td><a href="${dto.ci_web}">${dto.ci_web}</a></td>
						</tr>
			   			<tr>
							<th>기업로고</th>
							<td>
								<c:forEach items="${list}" var="path">
                  					<p><img src="/photo/${dto.ci_photo}" width="200" alt="로고"></p>
                  				</c:forEach>
               				</td>
						</tr>
			    		<tr>
							<th>기업소개</th>
							<td>${dto.ci_intro}</td>
						</tr>
			    		<tr>
							<th colspan="2">합격자소서</th>
						</tr>
						<tr>
						<td colspan="2">
							<textarea maxlength="1000" readonly>${dto.ci_pass_intro}</textarea>
		        	</table>
	        		<div class="find-btn">
        				<input class="find-btn1" type="button" value="수정하기" onclick="location.href='/companyInfoUpdate.go?ci_no=${dto.ci_no}&ci_photo=${dto.ci_photo}'"/>
       				</div>
	        	</c:if>
        </div>
   </section>
</body>
<script>

</script>
</html>