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
		<form action="companyMemberInfoUpdate.do" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
		<h3><strong>기업회원 회원정보수정</strong></h3>
		<br>
	        <input type="hidden" name="com_state" id="com_state" value="기업회원"/>
	        <input type="hidden" name="com_id" value="${companyInfo.com_id}"/>   
		    <table>
		        <tr>
		            <th>아이디</th>
		            <td>${sessionScope.loginId}</td>
		        </tr>
		        <tr>
		            <th>현재 비밀번호</th>
		            <td>
		            	<input type="password" name="com_pw" id="com_pw" maxlength="20"/>
		            	<button type="button" onclick="checkPw()">비밀번호 확인</button>	
		            </td>
	            </tr>
		        <tr>
		            <th>새 비밀번호</th>
		            <td><input type="password" name="pw" id="pw" maxlength="20"/></td>
	            </tr>
	            <tr>
		            <th>새 비밀번호 확인</th>
		            <td><input type="password" name="pw2" id="pw2" maxlength="20"/></td>
	            </tr>
		        <tr>
		            <th>사업자 번호</th>
		            <td>${companyInfo.com_business_no}</td>
		        </tr>
		        <tr>
		            <th>기업명</th>
			            <td>
			            	<input type="text" name="com_name" id="com_name" value="${companyInfo.com_name}"/>
			            </td>
		        </tr>
		        <tr>
		            <th>기업주소</th>
		            <td>
		            	<input type="text" name="com_address" id="com_address" size=50 style="font-size:10px" value="${companyInfo.com_address}" readonly/>
		            	<input type="button" onclick="sample4_execDaumPostcode()" value="주소찾기"/>
		            </td>
		        </tr>
		        <tr>
		        	<th>연락처</th>
						<td>
		            		<input type="text" name="com_call" id="com_call" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" pattern="\d*" maxlength="11" value="${companyInfo.com_call}"/>
	            		</td>
		        </tr>
		        <tr>
		            <th>이메일</th>
			            <td>
			            	<input type="text" name="com_email"  id="com_email" value="${companyInfo.com_email}"/>
			            	<button type="button" onclick="overlayEmail()">중복확인</button>
			            </td>
			        </tr>
		        <tr>
		            <th>사업자 등록증 사본</th>
		            <td>
						<!-- 하지만 div 는 서버에 값을 전송 할 수 없다. -->
						<!-- 결국엔 div 의 내용을 input 에 담아 서버에 전송할 예정 -->
						<input type="text"  name="orifile" style="border:none;" value="${companyInfo.com_photo}"/>
						<br>
						<br>
		            	<input type="file" multiple ="multiple" name="file" onchange="checkFile(this)" accept=".jpg , .png , .jpeg, .jfif, .exif, .gif, .bmp"/>
		            	<br><div style="color:#da0000;">※ 파일은 PNG,JPEG/JFIF,Exif,GIF,BMP 형식만 가능합니다.</div>
		            </td>
		        </tr>
		    </table>
            <input type="submit" class="button3" value="수정완료"/>
        	<input type="button" class="button4" value="취소" onclick="location.href='companyInfoManagement.do'"/>
	    </form>
    </div>
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<!-- 통합 로딩 방식 카카오 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	//주소 찾기 API 이용
	//주소 찾기 버튼 클릭시 팝업창 띄우기
	function sample4_execDaumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	        	
	             var addr = ''; // 주소 변수
	             var extraAddr = ''; // 참고항목 변수
	
	             // 사용자가 선택한 주소가 도로명 타입일때 도로명 주소 가져오기
	             if(data.userSelectedType === 'R'){ //사용자가 도로명 주소를 클릭한 경우
	            	 addr = data.roadAddress;
	             }else{
	            	 addr = data.jibunAddress;
	             }
	                
	                
	          	// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	            if(data.userSelectedType === 'R'){
	            	// 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	            	if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	           		}
	           		
	            	// 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	             	
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraAddr !== ''){
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	            }
	          	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('com_address').value = data.zonecode;
	            document.getElementById("com_address").value = addr;               
       		} 
	    }).open();
	}
	
	
	//이메일 중복체크
	var overChk2=false;
	
	function overlayEmail(){
		var email=$("#com_email").val();
		
		//이메일 정규화 표현
		var expEmail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
		//console.log("이메일중복체크 : "+email);
			if(email==""){
				alert("이메일을 입력해 주세요.");
				$("#com_email").focus();
			}else if(!expEmail.test(email)){
			alert("이메일 형식이 아닙니다.");
			$("#com_email").val("");
			$("#com_email").focus();
			}else{
				$.ajax({
					type:'get',
					url:'overlayComEmail.ajax',
					data:{
						chkComEmail:email
					},
					datatype:"JSON",
					success:function(data){
						// true / false 리턴 console.log(data);
						if(data.overlayComEmail){
							alert("사용중인 이메일 입니다.");
							$("#com_email").val("");
							$("#com_email").focus();
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
	
	
	// 파일 업로드 제한 경고창
    function checkFile(f){

    	var file = f.files;

    	//for문으로 파일 갯수만큼 확인
    	for(var i = 0; i<file.length; i++){
    	//경고창 한번으로 수정
    	if(!/\.(jpg|png|jpeg|jfif|exif|gif|bmp)$/i.test(file[i].name)) alert('이미지(.png , .jpeg, .jfif, .exif, .gif, .bmp) 파일만 선택해 주세요.\n\n현재 파일 : ' + file[i].name);
    	
    	else return;
    	
    	f.outerHTML = f.outerHTML;
    	}
    }
	
	
	// 기업회원 정보 수정하기 요청전 비밀번호 유효성 검사
	function checkPw(){
		var comPw = $("#com_pw").val();
		
		//암호화 된 비밀번호 확인 요청 ajax
		if(comPw==""){
			alert("현재 비밀번호를 입력해주세요.");
			return false;
		}else if(comPw!=""){
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
						return false;
					}else{
						alert("비밀번호가 일치합니다.");
						return false;
					}
				}
			});
		}
	}
	
	
	// 기업회원 회원정보 수정하기 요청전 유효성 검사
	function checkForm(){
		var comPw = $("#com_pw").val();
		var newPw = $("#pw").val();
		var newPw2 = $("#pw2").val();
		var name = $("#com_name").val();
		var comCall= $("#com_call").val();
		var comAddress = $("#com_address").val();
		var expPw = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
		
		 if(newPw!=""|| newPw2!="") {
        	if(newPw=="") {
        		alert("새 비밀번호를 입력해주세요.");
        		$("#pw").focus();
        		return false;
        	}
        	
        	if(newPw2=="") {
        		alert("새 비밀번호 확인을 입력해주세요.");
        		$("#pw2").focus();
        		return false;
        	}
        	
	        if(!expPw.test(newPw)){
	        	alert("비밀번호는 8 ~ 20자 영문,숫자 조합으로 만들어주세요.");
	        	$("#pw").val('');
	        	$("#pw2").val('');
	        	$("#pw").focus();
	        	return false;  	
	        }
        	
        	if(newPw!=newPw2) {
        		alert("새 비밀번호가 일치하지 않습니다.");
        		$("#pw").val('');
	        	$("#pw2").val('');
        		return false;
        	}
        }
		
		
		if(comPw==""){
			alert("현재 비밀번호를 입력해주세요.");
			$("#com_pw").focus();
			return false;
		}
		
		if(name==""){
			 alert("기업명을 입력해주세요.");
			 $("#com_name").focus();
			 return false;
		}
		
		if(comAddress==""){
			alert("주소를 입력해주세요.");
			return false;
		}
		
		if(comCall==""){
			alert("연락처를 입력해주세요.");
			$("#com_call").focus();
			return false;
		}
		
		if(email==""){
			alert("이메일을 입력해주세요");
			$("#com_email").focus();
			return false; 
		}
		
		if(!expEmail.test(Email)){
        	alert("이메일 형식이 아닙니다.");
        	$("#com_email").val('');
        	$("#com_email").focus();
        	return false;  	
        }
		
	}
	
	
</script>