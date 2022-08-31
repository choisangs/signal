<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header_jobPosting.jsp" %>
<style>
	table {
		width : 80%;
	}
	
	#section {
		width : 1000px;
		position: relative;
		left : 15%;
	}
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
</style>
<body>
	<!-- 폼방식 회원가입 <form action="companyJoin.do" method="post" enctype="multipart/form-data" onsubmit="return joinFormCompany()"> -->
	<div id="section">
		<h1>회원가입 - 기업</h1>
		        <input type="hidden" name="com_state" id="com_state" value="기업회원"/>   
		    <table>
		        <tr>
		            <th>아이디</th>
		            <td>
		            	<input type="text" id="com_id" name="com_id" maxlength="20"/>
		            	<button type="button" id="overChkId" onclick="overlayId()">중복확인</button>
		            	※아이디는 영문 또는 영문(소문자)시작+숫자 조합으로 8~20자로 작성해주세요.
		            </td>
		        </tr>
		        <tr>
		            <th>비밀번호</th>
		            <td>
		            	<input type="password" name="com_pw" id="com_pw"  maxlength="20"/>
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
		            <th>사업자 번호</th>
		            <td>
		            	<input type="text" name="com_business_no" id="com_business_no" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="예)숫자만 입력해 주세요." maxlength="15"/>
		            	<button type="button" id="overChkNum" onclick="overlayNumber()">중복확인</button>
		            </td>
		        </tr>
		        <tr>
		            <th>기업명</th>
		            <td>
		            	<input type="text" name="com_name" id="com_name"/>
		            </td>
		        </tr>
		        <tr>
		            <th>사업자 등록증 사본</th>
		            <td>
						<!-- 하지만 div 는 서버에 값을 전송 할 수 없다. -->
						<!-- 결국엔 div 의 내용을 input 에 담아 서버에 전송할 예정 -->
		            	<input type="file" multiple ="multiple" id="com_photo" onchange="checkFile(this)" accept=".png , .jpeg, .jfif, .exif, .gif, .bmp"/>
		            	<br>※ 파일은 JPG,PNG,JPEG/JFIF,Exif,GIF,BMP 형식만 가능합니다.
		            </td>
		        </tr>
		        <tr>
		            <th>기업주소</th>
		            <td>
		            	<input type="text" name="com_address" id="com_address" size=50 style="font-size:10px" readonly/>
		            	<input type="button" onclick="sample4_execDaumPostcode()" value="주소찾기"/>
		            </td>
		        </tr>
		        <tr>
		        	<th>연락처</th>
 						<td>
		            	<input type="text" name="com_call" id="com_call" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" pattern="\d*" maxlength="11" placeholder="예)01012345678"/>
		            	</td>
		        </tr>
		        <tr>
		            <th>이메일</th>
		            <td>
		            	<input type="text" name="com_email"  id="com_email" placeholder="예)admin@naver.com" />
		            	<button type="button" onclick="overlayEmail()">중복확인</button>
		            </td>
		        </tr>
		        <tr>
		            <th colspan="2">
		                <input type="button" value="회원가입" onclick="joinFormCompany()"/>
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

	//아이디 중복 체크
	var overChk= false;
	function overlayId(){
		var comId = $("#com_id").val();
		//console.log("중복된 아이디 확인 : "+clId);
		
		// 아이디 제한 기능
		var expId=/^[a-z]+[a-z0-9]{7,20}$/g;
		
		if(expId.test($("#com_id").val())){
		
			//아이디 중복 또는 사용가능 유효성 검사
			$.ajax({
				type:'get',
				url:'overlayCompanyId.ajax',
				data:{chkComId:comId},
				datatype:"JSON",
				success:function(data){
					console.log(data);
					if(data.overlayCompanyId){
						alert("사용중인 아이디 입니다.");
						$("#com_id").val("");
						$("#com_id").focus();
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
			$("#com_id").focus();
			return false;	
		}
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
	
	
	//사업자번호 중복체크
	var overChk3=false;
	function overlayNumber(){
		var number = $("#com_business_no").val();
		if(number==""){
			alert("사업자번호를 입력해주세요.");
			$("#com_business_no").focus();
		}else{
			$.ajax({
				type:'get',
				url:'overlayNumber.ajax',
				data:{
					chkNumber:number
				},
				datatype:"JSON",
				success:function(data){
					// true / false 리턴 console.log(data);
					if(data.overlayNumber){
						alert("이미 등록된 사업자 번호 입니다.");
						$("#com_business_no").val("");
						$("#com_business_no").focus();
					}else{
						alert("등록 가능한 사업자 번호 입니다.");
						overChk3=true;
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
	                document.getElementById('com_address').value = data.zonecode;
	                document.getElementById("com_address").value = addr;               
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
	
	
	// 회원가입 요청하기 ajax
	function joinFormCompany(){
		var comId = $("#com_id").val();
		var comPw = $("#com_pw").val();
		var comPw2 = $("#pw2").val();
		var expPw = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
		var comNumber = $("#com_business_no").val();
		var name = $("#com_name").val();
		var comAddress = $("#com_address").val();
		var comCall= $("#com_call").val();
		var email = $("#com_email").val();
		var expEmail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
		var state = $("#com_state").val();
		//사업자등록증 사본 사진 변수 선언은 배열로 선언한다.
		var file = $("#com_photo")[0].files[0];
		
		var formData = new FormData();
		
		//data에 보내는 값 정하기
		formData.append("com_id",comId);
		formData.append("com_pw",comPw);
		formData.append("com_business_no",comNumber);
		formData.append("com_name",name);
		formData.append("com_address",comAddress);
		formData.append("com_call",comCall);
		formData.append("com_email",email);
		formData.append("com_state",state);
		formData.append("file",file);
		
		
		//기업 회원가입 요청 ajax
		if(comId==""){
			alert("아이디를 입력해주세요.");
			$("#com_id").focus();
			return false;
		}else if(comPw==""){
			alert("비밀번호를 입력해주세요.");
			$("#com_pw").focus();
			return false;
		}else if(!expPw.test(comPw)){
        	alert("비밀번호는 8 ~ 20자 영문,숫자 조합으로 만들어주세요.");
        	$("#com_pw").val("");
        	$("#pw2").val("");
        	$("#com_pw").focus();
        	return false;
	    }else if(comPw2==""){
           alert("비밀번호 확인을 입력해주세요.");
           $("#pw2").val("");
           $("#pw2").focus();
           return false;	
	    }else if(comPw!==comPw2){
        	alert("비밀번호가 일치하지 않습니다.");
       	 	$("#pw2").val("");
            $("#pw2").focus();
        	return false;
        }else if(comNumber==""){
			alert("사업자 번호를 입력해주세요.");
			$("#com_business_no").focus();
			return false;
		}else if(name==""){
			 alert("기업명을 입력해주세요.");
			 $("#com_name").focus();
			 return false;
		 }else if(comAddress==""){
			alert("주소를 입력해주세요.");
			return false;
		}else if(comCall==""){
			alert("연락처를 입력해주세요.");
			$("#com_call").focus();
			return false;
		}else if(email==""){
			alert("이메일을 입력해주세요");
			$("#com_email").focus();
			return false; 
		}else if(!expEmail.test(email)){
        	alert("이메일 형식이 아닙니다.");
        	$("#com_email").val('');
        	$("#com_email").focus();
        	return false;  	
        }else if(overChk&&overChk2&&overChk3!=false){
			$.ajax({
				type:'post',
				url:'joinCompany.ajax',
				data:formData,
				datatype:"JSON",
				processData : false ,
	            contentType : false ,
				success:function(data){
					console.log(data);
					if(data.joinCompany){
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
			alert("아이디 / 이메일 또는 사업자 번호 중복체크를 진행해주세요.");
		}
		
		
	
		
		
	}
		
	

</script>