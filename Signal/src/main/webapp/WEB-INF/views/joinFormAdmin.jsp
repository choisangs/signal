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
		<h1>관리자 계정생성</h1>
		        <input type="hidden" name="ad_state" id="ad_state" value="관리자"/>   
	    <table>
	        <tr>
	            <th>아이디</th>
	            <td>
	            	<input type="text" id="ad_id" name="ad_id" maxlength="20"/>
	            	<button type="button" id="overChkId" onclick="overlayId()">중복확인</button>
	            	<br>
	            	※아이디는 영문 또는 영문(소문자)시작+숫자 조합으로 8~20자로 작성해주세요.
	            </td>
	        </tr>
	        <tr>
	            <th>비밀번호</th>
	            <td>
	            	<input type="password" name="ad_pw" id="ad_pw"  maxlength="20"/>
	            	<br>
	            	※비밀번호는 영문 또는 영문(소문자)시작+숫자 조합으로 8~20자로 작성해주세요.
	            </td>
	        </tr>
	             <tr>
	            <th>비밀번호 확인</th>
	            <td>
	            	<input type="password"  id="pw2" maxlength="20"/>
	            </td>
	        </tr>
	        <tr>
	            <th>이름</th>
	            <td>
	            	<input type="text" name="ad_name" id="ad_name"/>
	            </td>
	        </tr>
	        <tr>
	            <th>연락처</th>
	            <td>
	            	<input type="text" name="ad_call" id="ad_call" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" pattern="\d*" maxlength="11" placeholder="예)01012345678"/>
	            </td>
	        </tr>
	        <tr>
	            <th>이메일</th>
	            <td>
	            	<input type="text" name="ad_email"  id="ad_email" placeholder="예)admin@naver.com" />
	            	<button type="button" onclick="overlayEmail()">중복확인</button>
	            </td>
	        </tr>
	        <tr>
	            <th id="button" colspan="2">
	                <input type="button" value="회원가입" onclick="joinFormAdmin()"/>
		         	<input type="button" value="취소" onclick="location.href='adminManagementList.do'"/>
	            </th>
	        </tr>
	    </table>
	</div>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<script>

	//아이디 중복 체크
	var overChk= false;
	function overlayId(){
		var adminId = $("#ad_id").val();
		//console.log("중복된 아이디 확인 : "+clId);
		
		// 아이디 제한 기능
		var expId=/^[a-z]+[a-z0-9]{7,20}$/g;
		
		if(expId.test($("#ad_id").val())){
		
			//아이디 중복 또는 사용가능 유효성 검사
			$.ajax({
				type:'get',
				url:'overlayAdminId.ajax',
				data:{chkadminId:adminId},
				datatype:"JSON",
				success:function(data){
					console.log(data);
					if(data.overlayClientId){
						alert("사용중인 아이디 입니다.");
						$("#ad_id").val("");
						$("#ad_id").focus();
					}else{
						alert("사용 가능한 아이디 입니다.");
						overChk = true;
					}
				},
				error:function(e){
					console.log(e);
				}
			});
			
		}else{
			alert("아이디는 영문(소문자) 또는 영문(소문자)시작+숫자 조합으로 8~20자로 작성해주세요.");
			$("#ad_id").focus();
			return false;	
		}
	}
	
	//이메일 중복체크
	var overChk2=false;
	
	function overlayEmail(){
		var email=$("#ad_email").val();
		
		//이메일 정규화 표현
		var expEmail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
		//console.log("이메일중복체크 : "+email);
		if(email==""){
			alert("이메일을 입력해 주세요.");
			$("#ad_email").focus();
		}else if(!expEmail.test(email)){
			alert("이메일 형식이 아닙니다.");
			$("#ad_email").val("");
			$("#ad_email").focus();
			}else{
				$.ajax({
					type:'get',
					url:'overlayAdminEmail.ajax',
					data:{
						chkAdminEmail:email
					},
					datatype:"JSON",
					success:function(data){
						// true / false 리턴 console.log(data);
						if(data.overlayAdminEmail){
							alert("사용중인 이메일 입니다.");
							$("#ad_email").val("");
							$("#ad_email").focus();
						}else{
							alert("사용가능한 이메일입니다.");
							overChk2=true;
						}
					},
					error:function(e){
						console.log(e);
					}
				});
			}
	}
	
	
	
	
	
	
	function joinFormAdmin(){
		var adminId = $("#ad_id").val();
		var adminPw = $("#ad_pw").val();
		var expPw = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
		var adminPw2 = $("#pw2").val();
		var name = $("#ad_name").val();
		var adminCall= $("#ad_call").val();
		var email = $("#ad_email").val();
		var expEmail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
		var state = $("#ad_state").val();
		

		//회원가입 요청 ajax
		if(adminId==""){
			alert("아이디를 입력해주세요.");
			$("#ad_id").focus();
			return false;
		}else if(adminPw==""){
			alert("비밀번호를 입력해주세요.");
			$("#ad_pw").focus();
			return false;
		}else if(!expPw.test(adminPw)){
        	alert("비밀번호는 8 ~ 20자 영문,숫자 조합으로 만들어주세요.");
        	$("#ad_pw").val("");
        	$("#pw2").val("");
        	$("#ad_pw").focus();
        	return false;
	    }else if(adminPw2==""){
           alert("비밀번호 확인을 입력해주세요.");
           $("#pw2").val("");
           $("#pw2").focus();
           return false;	
	    }else if(adminPw!==adminPw2){
        	alert("비밀번호가 일치하지 않습니다.");
       	 	$("#pw2").val("");
            $("#pw2").focus();
        	return false;
        }else if(name==""){
			 alert("이름을 입력해주세요.");
			 $("#ad_name").focus();
			 return false;
		 }else if(adminCall==""){
			alert("연락처를 입력해주세요.");
			$("#ad_call").focus();
			return false;
		}else if(email==""){
			alert("이메일을 입력해주세요");
			$("#ad_email").focus();
			return false; 
		}else if(!expEmail.test(email)){
        	alert("이메일 형식이 아닙니다.");
        	$("#ad_email").val('');
        	$("#ad_email").focus();
        	return false;  	
        }else if(overChk&&overChk2!=false){
			$.ajax({
				type:'post',
				url:'joinAdmin.ajax',
				data:{
					ad_id:adminId,
					ad_pw:adminPw,
					ad_name:name,
					ad_call:adminCall,
					ad_email:email,
					ad_state:state
				},
				datatype:"JSON",
				success:function(data){
					console.log(data);
					if(data.success){
						alert("관리자 계정등록에 성공하였습니다.");
						location.href='/adminManagementList.do';
					}else{
						alert("관리자 계정등록에 실패하였습니다.");
					}
				},
				error:function(e){
					console.log(e);
				}		
			});
		}else{
			alert("아이디 또는 이메일 중복체크를 진행해주세요.");
		}
		
		
		
		
	}
		
	

</script>