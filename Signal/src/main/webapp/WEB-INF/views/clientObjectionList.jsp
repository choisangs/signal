<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
/* 메인 섹션 영역 */  
#section {
	width : 800px;
	position: relative;
	top : -380px;
	left : 350px;
}

body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
</style>
</head>
<body>
	<div id="section">
<h2>이의제기현황</h2>
<table>
    <colgroup>
       <col width="400"></col>
        <col width="250"></col>
        <col width="100"></col>
     
    </colgroup>
        <thead>
            <tr>
                <th>면접 및 기업 정보</th>
                <th>처리내용</th>
                <th>처리상태</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${clientObjectionList}" var="clientObjectionList" >
                    <tr>
                        <td align="center"><a href="interviewDetail2.go?inter_no=${clientObjectionList.inter_no}">${clientObjectionList.jpo_title}</a>
                        		${clientObjectionList.com_name}<br/> ${clientObjectionList.inter_date} ${clientObjectionList.inter_result}  평점  ${clientObjectionList.inter_avg}
                        </td>
                        <td align="center">${clientObjectionList.obj_content}</td>
                        <td align="center">
                        	<c:choose>
									<c:when test="${clientObjectionList.obj_state eq 0}"><div style="color:#da0000;">미처리</div></c:when>					
									<c:when test="${clientObjectionList.obj_state eq 1}">${clientObjectionList.obj_date}<div style="color:#4f81bd;">처리완료</div></c:when>					
									
							</c:choose>
                        </td>
                    	
                    </tr>
                </c:forEach>
        </tbody>
    </table>
 
    <!--페이징 -->
    <div class="pageInfo_wrap">
        <div class="pageInfo_area">
       		<ul id="pageInfo" class="pageInfo">
       		<!-- 이전페이지 버튼 -->
                <c:if test="${pageMaker.prev}">
                    <li class="pageInfo_btn previous"><a href="?obj_no=${obj_no}&pageNum=${pageMaker.startPage-1}">Previous</a></li>
                </c:if>
        		
 				<!-- 각 번호 페이지 버튼 -->
                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                    <li class="pageInfo_btn ${pageMaker.cri.pageNum == num ? 'active': ''}"><a href="?obj_no=${obj_no}&&pageNum=${num}">${num}</a></li>
                </c:forEach>
                
                <!-- 다음페이지 버튼 -->
                <c:if test="${pageMaker.next}">
                    <li class="pageInfo_btn next"><a href="?obj_no=${obj_no}&pageNum=${pageMaker.endPage + 1}">Next</a></li>
                </c:if>
       		</ul>
        </div>
    </div>		
</div>
</body>
<script>
</script>
</html>