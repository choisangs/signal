<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="resources/css/common.css" type="text/css"/>
</head>
<body>
	<h2>회원탈퇴</h2>
	    <h3>탈퇴안내</h3>
	    <p>회원탈퇴를 신청하기 전에 아래 내용을 꼭 확인해 주세요.
	    <br>
	    <br>
	    사용하고 계신 아이디는 탈퇴할 경우 동일한 아이디로 재가입이 불가능합니다.
	    <br>
	    <br>
	    탈퇴하시려면 현재 로그인 중인 계정의 비밀번호를 입력하셔야 탈퇴 가능합니다.
	    </p>
	    <br>
	    <h4>탈퇴사유</h4>
	 	  <form id="frmMbDel" action="companyDelete.do" method="post" onsubmit="return memberDelete()">
	        <div>
             	<input name="mg_content" type="radio" value="사용빈도가 낮고 개인정보 유출이 우려돼서"/>
	            사용빈도가 낮고 개인정보 유출이 우려돼서
	            <br>
	            <br>
	            <input name="mg_content" type="radio" value="서비스 이용에 불만이 있어서"/>
	             서비스 이용에 불만이 있어서
	             <br>
	             <br>
	            <input name="mg_content" type="radio" value="기타"/>
	            기타
	            <input type="text" name="mg_content_other" id="contentOther" disabled/>
	            <br>
	            <br>
	            비밀번호 확인 <input type="password" name="com_pw" id="com_pw"/>
	        </div>
	        <br>
		    <input type="submit" value="탈퇴"/>
	    </form>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>

	//input 라디오 타입 "기타" 체크시 text 박스 활성화
	$('input[name="mg_content"]').on('click',function(){
		if($(this).val() == "기타") {
			$('#contentOther').prop('disabled', false).val('').focus();
		} else {
			$('#contentOther').prop('disabled', true).val('');
		}
	});
	
	function memberDelete(){
		var comPw = $("#com_pw").val();
		
		if($('input[name="mg_content"]').is(":checked") == false) {
			alert("탈퇴 사유를 체크해 주세요.");
			return false;
		}
		
		if($('input[name="mg_content"][value="기타"]').is(":checked") == true && $("#contentOther").val() == '') {
			alert("기타 사유를 입력해 주세요.");
			$('#contentOther').focus();
			return false;
		}
		
		if(comPw == ''){
			alert("비밀번호를 입력해주세요.");
			$("#com_pw").focus();
			return false;
		}
		
		$.ajax({
			type:'post',
			url:'passwordConfirm2.ajax',
			data:{
				com_pw:comPw
			},
			datatype:"JSON",
			success:function(data){
				console.log(data.pw);
				if(!data.pw){
					alert("비밀번호가 일치하지 않습니다.");
					location.href='companyBreakFormPopup.go';
				}
			}
		});
		
		if(!confirm("정말 회원탈퇴 하시겠습니까?")) {
			
			return false;
		}
		}

	//팝업창 닫기
	var pclose = "${pclose}";
	if(pclose){
		opener.document.location.href="main.do";
		window.close();
	}
</script>