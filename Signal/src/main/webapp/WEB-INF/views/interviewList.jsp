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
<h2>면접현황</h2>
<br>
<p><strong>평균 평점 ${avgGrade}</strong><p>
<p><strong>총코멘트수 ${countComment}</strong><p>
<table>

    <colgroup>
        <col width="170"></col>
        <col width="120"></col>
        <col width="100"></col>
        <col width="70"></col>
        <col width="70"></col>
        <col width="80"></col>
     
    </colgroup>
        <thead>
            <tr>
                <th>채용공고제목</th>
                <th>기업명</th>
                <th>면접일</th>
                <th>면접현황</th>
                <th>평점</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${interviewList}" var="interviewList" >
                    <tr>
                        <td align="center"><a href="PostingDetailMain2.go?jpo_no=${interviewList.jpo_no}&&com_id=${interviewList.com_id}" >${interviewList.jpo_title}</a></td>
                        <td align="center">${interviewList.com_name}</td>
                        <td align="center">${interviewList.inter_date}</td>
                        <td>${interviewList.inter_result}</td>
                        <td align="center">${interviewList.inter_avg}</td>
                        <td align="center">
                        	<c:choose>
									<c:when test="${empty interviewList.obj_cl_content}">
										<button type="button" onclick="pop(${interviewList.inter_no});">이의제기</button>
									</c:when>
									<c:when test="${interviewList.obj_state == '1'}">
										처리완료
									</c:when>							
									<c:otherwise>처리중</c:otherwise>
							</c:choose>
							<br>
							<br>
	                            <button type="button" onclick="location.href='interviewDetail.go?inter_no=${interviewList.inter_no}'">상세보기</button>
                         </td>
                         
                    </tr>
                </c:forEach>
        </tbody>
    </table>		
    </div>
</body>
<script>


 
function pop(inter_no){
	
	 window.open("clientObjectionReg.go?inter_no="+inter_no, '', 'width=800, height=600, left=100, top=50'); 
	 
	 return false;

}
 
 
</script>
</html>