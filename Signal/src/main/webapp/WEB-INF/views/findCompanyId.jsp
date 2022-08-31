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
</style>
<body>
	<div id="nomembersection">
		<h1>ID 찾기 - 기업</h1>
		<form action="findCompanyId.do" method="POST" onsubmit="return findId()">
			<table>
			   <tr>
					<th>이메일</th>
						<td>
							<input type="text" name="com_email" id="com_email" placeholder="예)admin@naver.com"/>
						</td>
				</tr>
			     <tr>
					<th>사업자번호</th>
					<td>
						<input type="text" name="com_business_no" id="com_business_no" size=50 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만 입력해 주세요."/>
					</td>
				</tr>
			     <tr>
					<th id="button" colspan="2">
						<input type="submit" value="아이디 찾기"/>
			         	<input type="button" value="취소" onclick="location.href='/'"/>
					</th>
				</tr>
			</table>
		</form>
	</div>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>
	function findId(){
		var email = $("#com_email").val();
		var expEmail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
		
		if(email==""){
   			alert("이메일을 입력해주세요");
   			$("#com_email").focus();
   			return false; 
       	}
		
		if(!expEmail.test(email)){
        	alert("이메일 형식이 아닙니다.");
        	$("#com_email").val('');
        	$("#com_email").focus();
        	return false;
		}
	}
</script>