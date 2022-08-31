<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
<style>
	table {
		font-size: 75%;
		width : 70%;
	}
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
</style>
<body>
	<div id="adminsection">
	<h2>개인회원 리스트</h2>
	<!-- form 방식으로 select의 선택 값과 검색의 내용을 컨트롤러에 보내준다. -->
	<form action="clientListSearch.do" method="get" id="form">
		<!-- select option 넣기 -->
		<select name="searchOption" id="searchOption">
			<option value="">회원전체</option>
			<option value="개인회원" ${searchOption == '개인회원'? 'selected="selected"' : ''}>개인회원</option>
			<option value="탈퇴회원" ${searchOption == '탈퇴회원'? 'selected="selected"' : ''}>탈퇴회원</option>
		</select>
		<input type="text" name="search" id="search" value="${search}"/>
		<button type="submit">검색</button>
		<br>
		<br>
		<p>※ 회원이름 또는 아이디로도 검색할 수 있습니다.</p>
		<!-- 페이징 관련 -->
		<input type="hidden" name="pageNum" value="1"/>
	</form>
	<table>
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>주소</th>
				<th>성별</th>
				<th>이메일</th>
				<th>연락처</th>
				<th>생년월일</th>
				<th>회원상태</th>
				<th>상태수정</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${clientList.size()>0}">
					<c:forEach items="${clientList}" var="client">
						<tr>
							<td><a href="clientStateDetailPopup.do?cl_id=${client.cl_id}" onclick="window.open(this.href, '', 'width=700, height=300, left=100, top=50'); return false;">${client.cl_id}</a></td>
							<td>${client.cl_name}</td>
							<td>${client.cl_address}</td>
							<td>${client.cl_gender}</td>
							<td>${client.cl_email}</td>
							<td>${client.cl_call}</td>
							<td>${client.cl_birth}</td>
							<td>${client.cl_state}</td>				
							<td><a href="clientStateChangePopup.go?cl_id=${client.cl_id}" onclick="window.open(this.href, '', 'width=500, height=300, left=100, top=50'); return false;">변경하기</a></td>
						</tr>
					</c:forEach>
				</c:when>
			<c:otherwise>
				<tr>
					<td colspan="9" align="center">일치하는 회원 정보가 없습니다.</td>
				</tr>
			</c:otherwise>
			</c:choose>
		</tbody>	
	</table>
	<!-- 하단에 페이지 버튼 만들기 -->
	<div class="pageInfo_wrap">
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
	
	<!-- 페이지 이동 관련 form 태그 -->
	<form id="moveForm" method="get">
	  	 <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
  	</form>
  	</div>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>
		
		// 하단 페이지 버튼관련 제이쿼리
		$(".pageInfo a").on("click", function(e){
    	e.preventDefault();
        if($("#searchOption").val()=="" && $("#search").val()==""){
    		$("#moveForm").find("input[name='pageNum']").val($(this).attr("href"));
        	$("#moveForm").attr("action", "/clientManagementList.do");
    		$("#moveForm").submit();
        } else {
        	$("#form").find('input[name="pageNum"]').val($(this).attr("href"));
        	$("#form").submit();
        }
        
});

</script>