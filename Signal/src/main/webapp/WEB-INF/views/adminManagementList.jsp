<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
<style>
	table {
		width : 75%;
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
		<h3><strong>관리자 리스트</strong></h3>
		<br>
		<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>상태</th>
					<th>비밀번호</th>
					<th>이름</th>
					<th>연락처</th>
					<th>이메일</th>
					<th>상태변경</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${adminList}" var="admin">
					<tr>
						<td><a href="adminStateDetailPopup.do?ad_id=${admin.ad_id}" onclick="window.open(this.href, '', 'width=700, height=300, left=100, top=50'); return false;">${admin.ad_id}</a></td>
						<td>${admin.ad_state eq '관리자'? '재직' : '퇴사'}</td>
						<td>${admin.ad_pw}</td>
						<td>${admin.ad_name}</td>
						<td>${admin.ad_call}</td>
						<td>${admin.ad_email}</td>
						<!-- <td><input type="button" value="변경하기" class="adminStatePopup" onclick=" adminStateChangePopup()"/></td> -->
						<td><a href="adminStateChangePopup.go?ad_id=${admin.ad_id}" onclick="window.open(this.href, '', 'width=500, height=300, left=100, top=50'); return false;">변경하기</a></td>
					</tr>
				 </c:forEach>
			</tbody>
		</table>
		<br>
	    <input type="button" value="관리자 계정 등록" onclick="location.href='joinFormAdmin.go'"/>
    </div>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>

	/*
	function adminStateChangePopup(){
		// 배열 선언
		var tdArr = new Array();
		
		// 현재 클릭된 Row(<tr>)
		var tdArr = new Array();
		var checkBtn = $(this);
		var tr = checkBtn.parent().parent();
		var td = tr.children();	
		var ad_id = td.eq(0).text();
		console.log(ad_id);
		
	window.open("adminStateChangePopup.go?ad_id=","", "width=400, height=300, left=100, top=50");
	}
	*/
	
	/*
	$(".adminStatePopup").click(function(){
		
		// 배열 선언
		var tdArr = new Array();
		
		// 현재 클릭된 Row(<tr>)
		var tdArr = new Array();
		var checkBtn = $(this);
		var tr = checkBtn.parent().parent();
		var td = tr.children();	
		var ad_id = td.eq(0).text();
		console.log(ad_id);
		
		window.open("adminStateChangePopup.go?ad_id="+ad_id,"", "width=400, height=300, left=100, top=50");
	});
	*/
		

</script>