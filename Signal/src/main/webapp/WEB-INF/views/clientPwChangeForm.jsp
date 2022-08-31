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
			<form action="clientPwChange.do" method="post" onsubmit="return clientPwChange()">
				<table>
			        <tr>
						<th>아이디</th>
						<td>${cl_id}</td>
					</tr>
			        <tr>
						<th>새 비밀번호</th>
						<td><input type="password" name="cl_pw" id="cl_pw"/></td>
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
	function clientPwChange(){
		var clPw = $("#cl_pw").val();
		var expPw = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
		var clPw2 = $("#pw2").val();
		
		if(clPw==""){
			alert("비밀번호를 입력해주세요.");
			$("#cl_pw").focus();
			return false;
		}
		
		if(!expPw.test(clPw)){
        	alert("비밀번호는 8 ~ 20자 영문,숫자 조합으로 만들어주세요.");
        	$("#cl_pw").val("");
        	$("#pw2").val("");
        	$("#cl_pw").focus();
        	return false;
	    }
		
		if(clPw2==""){
           alert("비밀번호 확인을 입력해주세요.");
           $("#pw2").val("");
           $("#pw2").focus();
           return false;	
		}
		
		
		if(clPw!==clPw2){
        	alert("비밀번호가 일치하지 않습니다.");
       	 	$("#pw2").val("");
            $("#pw2").focus();
        	return false;
        }

	}
</script>