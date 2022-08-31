<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
<style>
	table {
		width : 60%;
	}
	
	#button {
    text-align: center;
	}
	
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
</style>
<body>
	<div id="membersection">
		<br>
		<br>
		<br>
		<h3><strong>기업회원 정보관리</strong></h3>
		<br>
	        <input type="hidden" name="com_state" id="com_state" value="기업회원"/>
	        <input type="hidden" name="com_id" id="com_id" value="${companyInfo.com_id}"/>   
		    <table>
		        <tr>
		            <th>아이디</th>
		            <td>${sessionScope.loginId}</td>
		        </tr>
		        <tr>
		            <th>사업자 번호</th>
		            <td>${companyInfo.com_business_no}</td>
		        </tr>
		        <tr>
		            <th>기업명</th>
		            <td>${companyInfo.com_name}</td>
		        </tr>
		        <tr>
		            <th>기업주소</th>
		            <td>${companyInfo.com_address}</td>
		        </tr>
		        <tr>
		        	<th>연락처</th>
					<td>${companyInfo.com_call}</td>
		        </tr>
		        <tr>
		            <th>이메일</th>
		            <td>${companyInfo.com_email}</td>
		        </tr>
		        <tr>
		            <th>사업자 등록증 사본</th>
		            <td>
		            	<c:if test="${!empty companyInfo.com_photo}">
		            	<img src="/photo/${companyInfo.com_photo}" width="100" height="100"/>
		            	</c:if>
		            </td>
		        </tr>
		    </table>
            <input type="button" class="button1" value="수정하기" onclick="location.href='companyInfoUpdateForm.go'"/>
         	<input type="button" class="button2" value="회원탈퇴" onclick="companyBreakForm()"/>
	</div>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>

function companyBreakForm(){
	var com_id = $("#com_id").val();
	window.open("companyBreakFormPopup.go?com_id="+com_id,"", "width=600, height=400, left=100, top=50");	
}

</script>