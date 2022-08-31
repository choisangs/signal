<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
<style>
	table {
		width : 1000px;
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
	
	.button4 {
		position: absolute;
		top: 800px;
		left: 440px;
		font-size: 15px;
	}
	
	#membersection {
    width: 1400px;
    position: relative;
    top: -380px;
    left: 300px;
    height: 1000px;
	}
</style>
<body>
	<div id="membersection">
		<h3>개인정보수정</h3>
		<form action="clientInfoUpdate.do" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
	        <input type="hidden" name="cl_id" value="${clientInfo.cl_id}"/>
	        <input type="hidden" name="cl_state" id="cl_state" value="개인회원"/>   
		    <table>
		        <tr>
		            <th>아이디</th>
		            <td>${sessionScope.loginId}</td>
		        </tr>
		        <tr>
		            <th>현재 비밀번호</th>
		            <td>
		            	<input type="password" name="cl_pw" id="cl_pw" maxlength="20"/>
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
		            <th>이름</th>
		            <td><input type="text" name="cl_name" id="cl_name" value="${clientInfo.cl_name}"/></td>
		        </tr>
		        <tr>
		            <th>생년월일</th>
		            <td>${clientInfo.cl_birth}</td>
		        </tr>
		        <tr>
		            <th>나이</th>
		            <td>
		            <input type="text" name="cl_age" id="cl_age" value="${clientInfo.cl_age}" style="border:none; width:40px;"/>&nbsp;세
		            </td>
		        </tr>
		        <tr>
		        	<th>성 별</th>
						<td>
							<input type='radio' name='gender' value='남' ${clientInfo.cl_gender eq '남'? 'checked= "checked"' : ''}/>남성
		        			<input type='radio' name='gender' value='여' ${clientInfo.cl_gender eq '여'? 'checked= "checked"' : ''}/>여성
			        	</td>
			        </tr>
		        <tr>
		            <th>주 소</th>
		            <td>
			            <input type="text" name="cl_address" id="cl_address" size=50 style="font-size:10px" value="${clientInfo.cl_address }" readonly/>
			            <input type="button" onclick="sample4_execDaumPostcode()" value="주소찾기"/>
		            </td>
		        </tr>
		        <tr>
		            <th>핸드폰 번호</th>
		            <td>
		            	<input type="text" name="cl_call" id="cl_call" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" pattern="\d*" maxlength="11" value="${clientInfo.cl_call}"/>
		            </td>
		        </tr>
		        <tr>
		            <th>이메일</th>
		            <td>
		            	<input type="text" name="cl_email"  id="cl_email" value="${clientInfo.cl_email}" />
		            	<button type="button" onclick="overlayEmail()">중복확인</button>
		            </td>
		        </tr>
		        <tr>
		            <th>사진</th>
		            <td>
							<!-- 하지만 div 는 서버에 값을 전송 할 수 없다. -->
							<!-- 결국엔 div 의 내용을 input 에 담아 서버에 전송할 예정 -->
			            	<input type="file" multiple ="multiple" name="file" onchange="checkFile(this)" accept=".jpg , .png , .jpeg, .jfif, .exif, .gif, .bmp"/>
			            	<input type="text"  name="orifile" style="border:none;" value="${clientInfo.cl_photo}"/>
			            	<br>※ 파일은 PNG,JPEG/JFIF,Exif,GIF,BMP 형식만 가능합니다.
		            </td>
		        </tr>
		    </table>
            <input type="submit" class="button3" value="수정완료"/>
         	<input type="button" class="button4" value="취소" onclick="location.href='clientInfoManagement.do'"/>
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
               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

               // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
               // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
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
	               document.getElementById('cl_address').value = data.zonecode;
	               document.getElementById("cl_address").value = addr;               
	       	} 
    	}).open();
	}


	// 파일 업로드 제한 경고창
    function checkFile(f){

    	var file = f.files;

    	//for문으로 파일 갯수만큼 확인
    	for(var i = 0; i<file.length; i++){
    	//경고창 한번으로 수정
    	if(!/\.(jpg|png|jpeg|jfif|exif|gif|bmp)$/i.test(file[i].name)) alert('이미지(.jpg , .png , .jpeg, .jfif, .exif, .gif, .bmp) 파일만 선택해 주세요.\n\n현재 파일 : ' + file[i].name);
    	
    	else return;
    	
    	f.outerHTML = f.outerHTML;
    	}
    }	

	
	var overChk2=false;
	
	function overlayEmail(){
		var email=$("#cl_email").val();

		//이메일 정규화 표현
		var expEmail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
		//console.log("이메일중복체크 : "+email);
		if(email==""){
			alert("이메일을 입력해 주세요.");
			$("#cl_email").focus();
		}else if(!expEmail.test(email)){
			alert("이메일 형식이 아닙니다.");
			$("#cl_email").val("");
			$("#cl_email").focus();
			}else{
				$.ajax({
					type:'get',
					url:'overlayEmail.ajax',
					data:{
						chkEmail:email
					},
					datatype:"JSON",
					success:function(data){
						// true / false 리턴 console.log(data);
						if(data.overlayEmail){
							alert("사용중인 이메일 입니다.");
							$("#cl_email").val("");
							$("#cl_email").focus();
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
	
	
	// 개인회원 정보 수정하기 요청전 비밀번호 유효성 검사
	function checkPw(){
		var clPw = $("#cl_pw").val();
		
		//암호화 된 비밀번호 확인 요청 ajax
		if(clPw==""){
			alert("현재 비밀번호를 입력해주세요.");
			$("#cl_pw").focus();
			return false;
		}else if(clPw!=""){
			$.ajax({
				type:'post',
				url:'passwordConfirm.ajax',
				data:{
					cl_pw:clPw
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
	
	
	// 개인회원 정보 수정하기 요청전 유효성 검사
	function checkForm(){
		var clPw = $("#cl_pw").val();
		var newPw = $("#pw").val();
		var newPw2 = $("#pw2").val();
		var name = $("#cl_name").val();
		var clCall= $("#cl_call").val();
		var clAddress = $("#cl_address").val();
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
		
		
		
		if(clPw==""){
			alert("현재 비밀번호를 입력해주세요.");
			$("#cl_pw").focus();
			return false;
		}
		
		if(name==""){
			 alert("이름을 입력해주세요.");
			 $("#cl_name").focus();
			 return false;
		}
		
		if(clAddress==""){
			alert("주소를 입력해주세요.");
			return false;
		}
		
		if(clCall==""){
			alert("핸드폰 번호를 입력해주세요.");
			$("#cl_call").focus();
			return false;
		}
		
		if(email==""){
			alert("이메일을 입력해주세요");
			$("#cl_email").focus();
			return false; 
		}
		
		if(!expEmail.test(Email)){
        	alert("이메일 형식이 아닙니다.");
        	$("#cl_email").val('');
        	$("#cl_email").focus();
        	return false;  	
        }
		
	}
	
	
	
	
	
</script>