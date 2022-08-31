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
<h2>블라인드관리</h2>
<form action="adminBlindList.do" method="get" id="form">
     	<select name="searchOption" id="searchOption">
			<option value="">전체</option>
			<option value="개인회원" ${searchOption == '개인회원'? 'selected="selected"' : ''}>개인회원</option>
			<option value="기업회원" ${searchOption == '기업회원'? 'selected="selected"' : ''}>기업회원</option>
			<option value="기업명" ${searchOption == '기업명'? 'selected="selected"' : ''}>기업명</option>
	 	</select>
			<input  type="text"  name="search" id="search" value="${search}" required/>
			<button type="submit" >검색</button>
			<!-- 페이징  -->
		 	<input type="hidden" name="pageNum" value="1"/>
 </form>	
<table>
    <colgroup>
        <col width="90"></col>
        <col width="70"></col>
        <col width="150"></col>
        <col width="80"></col>
        <col width="90"></col>
        <col width="100"></col>
    </colgroup>
        <thead>
            <tr>
                <th>개인회원</th>
                <th>기업회원</th>
                <th>기업명</th>
                <th>면접번호</th>
                <th>처리여부</th>
                <th>취소여부</th>          
        </thead>
        <tbody>
                <c:forEach items="${adminBlindList}" var="adminBlindList" >
                    <tr>
                        <td align="center">${adminBlindList.cl_id}</td>
                        <td align="center">${adminBlindList.com_id}</td>
                        <td align="center">${adminBlindList.com_name}</td>
                         <td align="center"><a href="adminInterviewDetail.go?inter_no=${adminBlindList.inter_no}">${adminBlindList.inter_no}</a></td>
                        <td align="center">
                        	<c:choose>
                        		<c:when test="${adminBlindList.obj_state eq 0}"><div style="color:#da0000;">미처리</div></c:when>
                        		<c:when test="${adminBlindList.obj_state eq 1}"><div style="color:#4f81bd;">처리완료</div></c:when>
                        	</c:choose>
                        </td>
                        <td align="center">
                        	<c:choose>
									<c:when test="${adminBlindList.inter_blind eq 0}">
										<button type="button" onclick="cancel(${adminBlindList.inter_no})">취소</button>
									</c:when>					
									<c:when test="${adminBlindList.inter_blind eq 1}"></c:when>					
							</c:choose>
                          	</td>
                    </tr>
                </c:forEach> 
        </tbody>
    </table>
       <!--페이징 -->
    <div class="pageInfo_wrap" >
        <div class="pageInfo_area">
        		<ul id="pageInfo" class="pageInfo">
        		<!-- 이전페이지 버튼 -->
	                <c:if test="${pageMaker.prev}">
	                    <li class="pageInfo_btn previous"><a href="${pageMaker.startPage-1}">Previous</a></li>
	                </c:if>
	        		
	        		
	 				<!-- 각 번호 페이지 버튼 -->
	                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
	                    <li class='pageInfo_btn ${pageMaker.cri.pageNum == num ? "active": "" }'><a href="${num}">${num}</a></li>
	                </c:forEach>
	                
	                <!-- 다음페이지 버튼 -->
	                <c:if test="${pageMaker.next}">
	                    <li class="pageInfo_btn next"><a href="${pageMaker.endPage + 1 }">Next</a></li>
	                </c:if> 
        		</ul>
        </div>
    </div>	
      <form id="moveForm" method="get">
	  	 <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
	  </form>
	  </div>	
</body>

<script>
function cancel(inter_no) {
	var chk = confirm("블라인드 처리를 취소하시겠습니까?");
	if (chk ==true) {
		location.href='adminBlindCancel?inter_no='+inter_no;
	}else{
		
	}
}	


$(".pageInfo a").on("click", function(e){
    e.preventDefault();
        if($("#searchOption").val()=="" && $("#search").val()==""){
    		$("#moveForm").find("input[name='pageNum']").val($(this).attr("href"));
        	$("#moveForm").attr("action", "/adminBlindList.go");
    		$("#moveForm").submit();
        } else {
        	$("#form").find('input[name="pageNum"]').val($(this).attr("href"));
        	$("#form").submit();
        }
        
});

</script>
</html>