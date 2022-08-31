<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/popup.css" type="text/css"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#popup {
    position: relative;
    left: 200px;
    top: 70px;
    width: 600px;
    height: 400px;
}

input[type="submit"] {
	position: absolute;
	top: 200px;
	left: 80px;
}


#idFind{
	position: absolute;
	top: 250px;
	left: 40px;
}

#pwFind{
	position: absolute;
	top: 250px;
	left: 120px;
}

h1 {
	font-size: 40px;
}
</style>
</head>
<body>
	<div id="popup">
		<form action="login.do" method="post" onsubmit="return login()">
			<h1>Login</h1>
				<input type='radio' name='memberSelect' value='개인회원'  style="width:20px;height:20px;"/>개인회원
	   			<input type='radio' name='memberSelect' value='기업회원' style="width:20px;height:20px;"/>기업회원
			<table>
				<tr>
					<th>ID</th>
					<td><input type="text" name="id" id="id"/></td>
				</tr>
				<tr>
					<th>PW</th>
					<td><input type="password" name="pw" id="pw"/></td>
				</tr>
				<tr>
					<th colspan="2">
						<input id="login" type="submit" value="로그인"/>
					</th>
				</tr>
			</table>
			<br>
			<br>
			<input type="button" id="idFind" value="ID 찾기" onclick="findId()"/>
			<input type="button" id="pwFind" value="PW 찾기" onclick="findPw()"/>
		</form>
	</div>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>

	// 팝업창 닫기
	var pclose = "${pclose}";
	if(pclose){
		opener.document.location.href="main.do";
		window.close();
	}
	
	function findId(){
		var memberSelect = $('[name=memberSelect]:checked').val();
		if(memberSelect=="개인회원"){
			window.opener.location.href="findClientId.go";
			window.close();
		}else if(memberSelect=="기업회원"){
			window.opener.location.href="findCompanyId.go";
			window.close();
		}else{
			alert("회원상태를 선택해주세요.");
			return false;
		}
	}
	
	function findPw(){
		var memberSelect = $('[name=memberSelect]:checked').val();
		if(memberSelect=="개인회원"){
			window.opener.location.href="findClientPw.go";
			window.close();
		}else if(memberSelect=="기업회원"){
			window.opener.location.href="findCompanyPw.go";
			window.close();
		}else{
			alert("회원상태를 선택해주세요.");
			return false;
		}
	}
	
	function login(){
        //console.log('test');
        var id = $("#id");
		var pw = $("#pw");
        if(id.val()==""){
            alert("아이디를 입력해주세요.");
            id.focus();
            return false;
		}else if(pw.val()==""){
           alert("비밀번호를 입력해주세요.");
           pw.focus(); 
           return false;
        }
    }
	
</script>
</html>