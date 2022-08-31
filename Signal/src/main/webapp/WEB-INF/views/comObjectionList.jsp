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
    width: 1000px;
    position: relative;
    top: -350px;
    left: 290px;
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
                <th>면접 및 지원자 정보</th>
                <th>이의내용</th>
                <th>처리상태</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${comObjectionList}" var="comObjectionList" >
                    <tr>
                        <td align="center">
                         <img src="/photo/${comObjectionList.cl_photo}" width="100"/>
                         ${comObjectionList.cl_name} ${comObjectionList.cl_age}${comObjectionList.cl_gender}<br>
                       		 ${comObjectionList.re_title}<br>
                        	${comObjectionList.inter_date} ${comObjectionList.inter_result}  평점 ${comObjectionList.inter_avg}
                        		
                        </td>
                        <td align="center">${comObjectionList.obj_cl_content}</td>
                        <td align="center">
                        	<c:choose>
									<c:when test="${comObjectionList.obj_state eq 0}">
										<button type="button" onclick="pop(${comObjectionList.obj_no});">미처리</button>
									</c:when>					
									<c:when test="${comObjectionList.obj_state eq 1}">${comObjectionList.obj_date}<br>처리완료</c:when>					
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
function pop(obj_no){
	
	 window.open("comObjectionUpdate.go?obj_no="+obj_no, '', 'width=750, height=400, left=100, top=50'); 
	 
	 return false;

}



</script>
</html>