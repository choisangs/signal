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
		<h1><strong>새 비밀번호 설정</strong></h1>
		<br>
			<h4>비밀번호는 DB에 암호화 되어 저장되므로 새로운 비밀번호를 설정해야 합니다.</h4>
			<br>
			<form action="companyPwChange.do" method="post" onsubmit="return companyPwChange()">
				<table>
			        <tr>
						<th>아이디</th>
						<td>${com_id}</td>
					</tr>
			        <tr>
						<th>새 비밀번호</th>
						<td><input type="password" name="com_pw" id="com_pw"/></td>
					</tr>
			        <tr>
						<th>새 비밀번호 확인</th>
						<td><input type="password" name="pw2" id="pw2"/></td>
					</tr>
			        <tr>
						<th id="button" colspan="2">
							<input type="submit" value="비밀번호 설정"/>
				            <input type="button" value="취소" onclick="location.href='main.do'"/>
						</th>
					</tr>
				</table>
			</form>
	</div>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>
	function companyPwChange(){
		var comPw = $("#com_pw").val();
		var comPw2 = $("#pw2").val();
		var expPw = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
		
		if(comPw==""){
			alert("비밀번호를 입력해주세요.");
			$("#com_pw").focus();
			return false;
		}
		
		if(!expPw.test(comPw)){
        	alert("비밀번호는 8 ~ 20자 영문,숫자 조합으로 만들어주세요.");
        	$("#com_pw").val("");
        	$("#pw2").val("");
        	$("#com_pw").focus();
        	return false;
	    }
		
		if(comPw2==""){
           alert("비밀번호 확인을 입력해주세요.");
           $("#pw2").val("");
           $("#pw2").focus();
           return false;	
	    }
		
		
		if(comPw!==comPw2){
        	alert("비밀번호가 일치하지 않습니다.");
       	 	$("#pw2").val("");
            $("#pw2").focus();
        	return false;
        }

	}
</script>