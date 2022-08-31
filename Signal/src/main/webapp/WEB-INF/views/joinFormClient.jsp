<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header_jobPosting.jsp" %>
<style>
	table {
		width : 80%;
	}
	
	#section {
		width : 1400px;
		position: relative;
		left : 15%;
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
	<!-- 폼방식 회원가입 <form action="clientJoin.do" method="post" enctype="multipart/form-data" onsubmit="return joinFormClient()"> -->
	<div id="section">
		<h1>회원가입 - 개인</h1>
		        <input type="hidden" name="cl_state" id="cl_state" value="개인회원"/>   
		    <table>
		        <tr>
		            <th>아이디</th>
		            <td>
		            	<input type="text" id="cl_id" name="cl_id" maxlength="20"/>
		            	<button type="button" id="overChkId" onclick="overlayId()">중복확인</button>
		            	※아이디는 영문 또는 영문(소문자)시작+숫자 조합으로 8~20자로 작성해주세요.
		            </td>
		        </tr>
		        <tr>
		            <th>비밀번호</th>
		            <td>
		            	<input type="password" name="cl_pw" id="cl_pw"  maxlength="20"/>
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
		            	<input type="text" name="cl_name" id="cl_name"/>
		            </td>
		        </tr>
		        <tr>
		            <th>생년월일</th>
		            <td>
		            	<input type="text" name="cl_birth"  id="cl_birth" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" pattern="\d*" maxlength="8" placeholder="예)YYYYMMDD,19900701" />
		            </td>
		        </tr>
		        <tr>
		            <th>나이</th>
		            <td>
		            	<input type="text" name="cl_age"  id="cl_age" readonly style="border:none; width:40px;">&nbsp; 세
		            </td>
		        </tr>
		        <tr>
		        	<th>성 별</th>
 						<td>
 							<input type='radio' name='gender' value='남' />남성
		        			<input type='radio' name='gender' value='여' />여성
		        	</td>
		        </tr>
		        <tr>
		            <th>주 소</th>
		            <td>
		            	<input type="text" name="cl_address" id="cl_address" size=50 style="font-size:10px" readonly/>
		            	<input type="button" onclick="sample4_execDaumPostcode()" value="주소찾기"/>
		            </td>
		        </tr>
		        <tr>
		            <th>핸드폰 번호</th>
		            <td>
		            	<input type="text" name="cl_call" id="cl_call" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" pattern="\d*" maxlength="11" placeholder="예)01012345678"/>
		            </td>
		        </tr>
		        <tr>
		            <th>이메일</th>
		            <td>
		            	<input type="text" name="cl_email"  id="cl_email" placeholder="예)admin@naver.com" />
		            	<button type="button" onclick="overlayEmail()">중복확인</button>
		            </td>
		        </tr>
		        <tr>
		            <th>사진등록</th>
		            <td>
						<!-- 하지만 div 는 서버에 값을 전송 할 수 없다. -->
						<!-- 결국엔 div 의 내용을 input 에 담아 서버에 전송할 예정 -->
		            	<input type="file" multiple ="multiple" id="cl_photo" onchange="checkFile(this)" accept=".png , .jpeg, .jfif, .exif, .gif, .bmp"/>
		            	<br>※ 파일은 JPG,PNG,JPEG/JFIF,Exif,GIF,BMP 형식만 가능합니다.
		            </td>
		        </tr>
		        <tr>
		            <th colspan="2">
		                <input type="button" value="회원가입" onclick="joinFormClient()"/>
			         	<input type="button" value="취소" onclick="location.href='main.do'"/>
		            </th>
		        </tr>
		    </table>
	</div>
	<!-- 폼방식 회원가입 </form> -->
</body>
<%@ include file="../../resources/inc/footer.jsp" %>
<!-- 통합 로딩 방식 카카오 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

		let date = new Date();
		var year = date.getFullYear();
	//나이 자동 입력하기
	$("#cl_birth").keydown(function(){
		var clbirth = $("#cl_birth").val();
		var birth = clbirth.substr(0, 4);
		$("#cl_age").val(year-birth+1);
	});
	

	//아이디 중복 체크
	var overChk= false;
	function overlayId(){
		var clId = $("#cl_id").val();
		//console.log("중복된 아이디 확인 : "+clId);
		
		// 아이디 제한 기능
		var expId=/^[a-z]+[a-z0-9]{7,20}$/g;
		
		if(expId.test($("#cl_id").val())){
		
			//아이디 중복 또는 사용가능 유효성 검사
			$.ajax({
				type:'get',
				url:'overlayClientId.ajax',
				data:{chkclId:clId},
				datatype:"JSON",
				success:function(data){
					console.log(data);
					if(data.overlayClientId){
						alert("사용중인 아이디 입니다.");
						$("#cl_id").val("");
						$("#cl_id").focus();
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
			$("#cl_id").focus();
			return false;	
		}
	}
	
	//이메일 중복체크
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
	
	
	
	/* 파일 삭제하기
	function del(elem){
		console.log(elem);
		// id 에서 삭제할 파일명을 추출
		var id = $(elem).attr("id");
		var fileName = id.substring(id.lastIndexOf("/")+1);
		console.log(fileName);
		
		// 해당 파일 삭제 요청
		$.ajax({
			url:'fileDelete',
			type:'get',
			data:{'fileName':fileName},
			dataType:"JSON",
			success:function(data){
				console.log(data);
				// a 태그를 포함한 img 태그를 삭제 (remove를 사용 empty는 태그는 남음)
				$(elem).remove(); 
			},
			error:function(e){
				console.log(e);
			}
		});
	}
	*/
	
	
	
	
	function joinFormClient(){
		var clId = $("#cl_id").val();
		var clPw = $("#cl_pw").val();
		var expPw = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
		var clPw2 = $("#pw2").val();
		var name = $("#cl_name").val();
		var birth = $("#cl_birth").val();
		var clAddress = $("#cl_address").val();
		var clCall= $("#cl_call").val();
		var email = $("#cl_email").val();
		var age = $("#cl_age").val();
		var expEmail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
		var gender = $('[name=gender]:checked').val();
		var state = $("#cl_state").val();
		//개인회원 프로필 사진 변수 선언은 배열로 선언한다.
		var file = $("#cl_photo")[0].files[0];
		
		var formData = new FormData();
		
		//data에 보내는 값 정하기
		formData.append("cl_id",clId);
		formData.append("cl_pw",clPw);
		formData.append("cl_name",name);
		formData.append("cl_birth",birth);
		formData.append("cl_age",age);
		formData.append("cl_gender",gender);
		formData.append("cl_address",clAddress);
		formData.append("cl_call",clCall);
		formData.append("cl_email",email);
		formData.append("cl_state",state);
		formData.append("file",file);
		
		
		//회원가입 요청 ajax
		//파일 업로드를 ajax로 하기위해서는 processData : false, contentType : false 넣어야한다.
		if(clId==""){
			alert("아이디를 입력해주세요.");
			$("#cl_id").focus();
			return false;
		}else if(clPw==""){
			alert("비밀번호를 입력해주세요.");
			$("#cl_pw").focus();
			return false;
		}else if(!expPw.test(clPw)){
        	alert("비밀번호는 8 ~ 20자 영문,숫자 조합으로 만들어주세요.");
        	$("#cl_pw").val("");
        	$("#pw2").val("");
        	$("#cl_pw").focus();
        	return false;
	    }else if(clPw2==""){
           alert("비밀번호 확인을 입력해주세요.");
           $("#pw2").val("");
           $("#pw2").focus();
           return false;	
	    }else if(clPw!=clPw2){
        	alert("비밀번호가 일치하지 않습니다.");
       	 	$("#pw2").val("");
            $("#pw2").focus();
        	return false;
        }else if(name==""){
			 alert("이름을 입력해주세요.");
			 $("#cl_name").focus();
			 return false;
		 }else if(birth.length !==8){
			 alert("생년월일을 올바르게 입력해주세요.");
			 $("#cl_birth").val("");
			 $("#cl_birth").focus();
			 return false;
		 }else if(age==""){
			 alert("나이를 입력해주세요.");
			 $("#cl_age").focus();
			 return false;
		 }else if($(':radio[name="gender"]:checked').length < 1){
		    alert("성별을 선택해주세요");
		    return false;
		}else if(clAddress==""){
			alert("주소를 입력해주세요.");
			return false;
		}else if(clCall==""){
			alert("핸드폰 번호를 입력해주세요.");
			$("#cl_call").focus();
			return false;
		}else if(email==""){
			alert("이메일을 입력해주세요");
			$("#cl_email").focus();
			return false; 
		}else if(!expEmail.test(email)){
        	alert("이메일 형식이 아닙니다.");
        	$("#cl_email").val('');
        	$("#cl_email").focus();
        	return false;  	
        }else if(overChk&&overChk2){
			$.ajax({
				type:'post',
				url:'joinClient.ajax',
				data:formData,
				datatype:"JSON",
				processData : false ,
	            contentType : false ,
				success:function(data){
					console.log(data);
					if(data.success){
						alert("회원가입에 성공하셨습니다.");
						location.href='/';
					}else{
						alert("회원가입에 실패하였습니다.");
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