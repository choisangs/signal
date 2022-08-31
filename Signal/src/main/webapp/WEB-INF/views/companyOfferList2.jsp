<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>입사제안관리</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
/* 메인 section 영역 */
#section {
	width : 800px;
	position: relative;
	top : -350px;
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
	<h3>입사제안관리</h3>
	<br>
	<table>
		<thead>
			<tr>
				<th>채용공고제목</th>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>열람여부</th>
				<th>제안날짜</th>
			</tr>
		</thead>
		<tbody>		
			<c:choose>
				<c:when test="${offerList.size() > 0}">
					<c:forEach items="${offerList}" var="item">
						<tr>
							<td align="center">${item.jpo_title}</td>
							<td align="center">${item.cl_name}</td>
							<td align="center">${item.cl_gender}</td>
							<td align="center">${item.cl_age}</td>
							<td align="center">
							  <c:choose>
									<c:when test="${item.reading_state == '미열람'}">
										<div style="color:#da0000;">미열람</div>
									</c:when>					
									<c:when test="${item.reading_state =='열람'}">
										<div style="color:#4f81bd;">열람</div>
									</c:when>					
							</c:choose>
							</td>
							<td align="center">${item.offer_date}</td>
						</tr>
					</c:forEach>
				</c:when>
			<c:otherwise>
				<tr>
					<td>입사제안한 인재가 없습니다.</td>
				</tr>
			</c:otherwise>
			</c:choose>
		</tbody>		
	</table>
	
	<!-- '페이지 인터페이스'를 작업을 위한 <div> 태그를 작성 -->
	<div class="pageInfo_wrap" >
        <div class="pageInfo_area">
 			<ul id="pageInfo" class="pageInfo">
 				<!-- 이전페이지 버튼 -->
                <c:if test="${pageMaker.prev}">
                    <li class="pageInfo_btn previous"><a href="${pageMaker.startPage-1}">Previous</a></li>
                </c:if>
 				<!-- 각 번호 페이지 버튼 -->
                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                    <li class="pageInfo_btn" ${pageMaker.cri.pageNum == num ? "active":""}"><a href="${num}">${num}</a></li>
                </c:forEach>
                <!-- 다음페이지 버튼 -->
                <c:if test="${pageMaker.next}">
                    <li class="pageInfo_btn next"><a href="${pageMaker.endPage + 1 }">Next</a></li>
                </c:if>
 			</ul>
        </div>
    </div>
	<!-- value값은 현재 페이지의 정보가 저장되도록 하였습니다. 
	이는 현 페이지에서 '조회, 수정 페이지'로 이동하였다가 다시 현 페이지로 이동하기 위해 작성한 것인데 이에 대해 선 다음 포스팅에서 알아봅니다. -->
	<form id="moveForm" method="get">
		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
        <input type="hidden" name="amount" value="${pageMaker.cri.amount }">   
	</form>
	</div>
</body>
<script>
//by태섭, 페이징 작업_2022_08_13
//form 태그 값 가져와서 moveForm 변수에 담기
let moveForm = $("#moveForm");

//출력시킨 '페이지 이동 번호'가 동작시키기 위해 JS코드 작업
$(".pageInfo a").on("click", function(e){
	
	e.preventDefault();
  moveForm.find("input[name='pageNum']").val($(this).attr("href"));
  moveForm.attr("action", "/companyOfferList.do");
  moveForm.submit();    
  
});


</script>
</html>