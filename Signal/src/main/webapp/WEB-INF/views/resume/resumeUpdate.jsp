<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../../resources/inc/header.jsp" %>
<style>
	#section {
		width : 800px;
		position: relative;
		top : -380px;
		left : 350px;
		table-layout:fixed;
		word-break:break-all;
	}
	
	.hidden {
		display : none;
	}
	
	input[type="text"]{width:100%;}
</style>
<body>	
	<table id="section">
		 <tr>
		    <th colspan="4">제목</th>
		    <td colspan="24">
		    	<input type="text" id="re_title" value="${dto.re_title}"/>
		    	<input type="hidden" id="old_re_no" value="${re_no}"/>
		    	<input type="hidden" id="reco_no" value="${dto.reco_no}"/>
		    </td>
		  </tr>
		  <tr>
		    <th colspan="28" style="text-align:start;">인적사항 <button style="float:right;" onclick="location.href='/clientInfoUpdateForm.go'">개인정보 수정</button></th>
		  </tr>
		  <tr class="hidden">
		    <th colspan="28"><input type="text" id="cl_id" value="${cl_id}"></th>
		  </tr>
		  <tr>
		    <td colspan="4" rowspan="3"></td>
		    <th colspan="4">이름</th>
		    <td colspan="4">${dto.cl_name}</td>
		    <th colspan="4">생년월일</th>
		    <td colspan="4">${dto.cl_birth}</td>
		    <th colspan="4">성별</th>
		    <td colspan="4">${dto.cl_gender}</td>
		  </tr>
		  <tr>
		    <th colspan="4">주소</th>
		    <td colspan="20">${dto.cl_address}</td>
		  </tr>
		  <tr>
		    <th colspan="4">연락처</th>
		    <td colspan="8">${dto.cl_call}</td>
		    <th colspan="4">이메일</th>
		    <td colspan="8">${dto.cl_email}</td>
		  </tr>
		  <tr>
		    <th colspan="28" style="text-align:start;">희망직무 <input type="button" style="float:right;" onclick="jobClassPopGo()" value="등록"></th>
		  </tr>
		  <tr>
		    <th colspan="4">대분류</th>
			    <td colspan="10">
				    <input type="hidden" id="jp_no" name="jp_no" value="${dto.jp_no}">
				    <input type="text" id="jp_name" name="jp_name" value="${dto.jp_name}" readonly/>
			    </td>
		    <th colspan="4">중분류</th>
			    <td colspan="10">
				    <input type="hidden" id="jc_no" name="jc_no" value="${dto.jc_no}">
				    <input type="text" id="jc_name" name="jc_name" value="${dto.jc_name}" readonly/>
			    </td>
		  </tr>
		  <tr>
		    <th colspan="18">최종학력</th>
		    <td colspan="10">
		    	<select style="width:100%; text-align:center;" id="re_fn_status">
		    		<option value="없음" ${dto.re_fn_status=="없음"?'selected="selected"':''}>---</option>
		    		<option value="대졸" ${dto.re_fn_status=="대졸"?'selected="selected"':''}>대졸</option>
		    		<option value="고졸" ${dto.re_fn_status=="고졸"?'selected="selected"':''}>고졸</option>
		    		<option value="중졸" ${dto.re_fn_status=="중졸"?'selected="selected"':''}>중졸</option>
		    	</select>
	    	</td>
		  </tr>
		  <tr>
		    <th colspan="4">학교명</th>
		    <td colspan="6"><input type="text" id="re_sch_name" value="${dto.re_sch_name}"/></td>
		    <th colspan="4">재학기간</th>
		    <td colspan="14">
		    	<input type="hidden" id="re_sch_period" value="${dto.re_sch_period }"/>
				<select id="enterYear" style="text-align:center;">
					<option value="0000">---</option>
				</select> 년
				<select id="enterMonth" style="text-align:center;">
					<option value="00">---</option>
				</select> 월 ~ 
				<select id="outYear" style="text-align:center;">
					<option value="0000">---</option>
				</select> 년
				<select id="outMonth" style="text-align:center;">
					<option value="00">---</option>
				</select> 월										
			</td>		    
		  </tr>
		  <tr>
		    <th colspan="4">전공</th>
		    <td colspan="6"><input type="text" id="re_major" value="${dto.re_major}"/></td>
		    <th colspan="4">학점</th>
		    <td colspan="3"><input type="text" id="re_average" value="${dto.re_average}" placeholder="평점"/></td>
		    <td colspan="3"><input type="text" id="re_total" value="${dto.re_total}" placeholder="총점"/></td>
		    <th colspan="4">학적상태</th>
		    <td colspan="4">
		    	<select id="re_register" style="width:100%; text-align:center;">
		    		<option value="없음" ${dto.re_register=="없음"?'selected="selected"':''}>---</option>
		    		<option value="졸업" ${dto.re_register=="졸업"?'selected="selected"':''}>졸업</option>
		    		<option value="수료" ${dto.re_register=="수료"?'selected="selected"':''}>수료</option>
		    		<option value="재학" ${dto.re_register=="재학"?'selected="selected"':''}>재학</option>
		    		<option value="휴학" ${dto.re_register=="휴학"?'selected="selected"':''}>휴학</option>
		    		<option value="중퇴" ${dto.re_register=="중퇴"?'selected="selected"':''}>중퇴</option>
		    	</select>		    	
		    </td>
		  </tr>
		  <tr>
		    <th colspan="28">자기소개서</th>
		  </tr>
		  <tr>
		    <td colspan="28"><textarea id="re_intro">${dto.re_intro}</textarea></td>
		  </tr>
		  <tr>
		    <th colspan="4">포트폴리오</th>
		    <td colspan="24">
		    <p id="portfolioName">${dto.re_portfolio_ori}</p>
		    <input type="file" id="re_portfolio" multiple="multiple"/></td>
		  </tr>
		  <tr>
		  	<td colspan="28">
		  		<input type="button" onclick="resumeUp()" value="기본정보수정">
				<input type="button" value="취소" onclick="location.href='/resumeList.go'">				
		  	</td>
		  </tr>		 	
		</table>
	

</body>
<script>
schPeriod();
function schPeriod(){
	var re_sch_period=$('#re_sch_period').val();
	console.log(re_sch_period.substr(0,4) + '/' +re_sch_period.substr(5,2)+ '/' + re_sch_period.substr(8,4)+ '/' + re_sch_period.substr(13,2));
	var enterYear = re_sch_period.substr(0,4);
	var enterMonth = re_sch_period.substr(5,2);
	var outYear = re_sch_period.substr(8,4);
	var outMonth = re_sch_period.substr(13,2);
	
	schList();	
	function schList(){            
	    var now = new Date();
	    var year = now.getFullYear()+10;
	    var nowYear = now.getFullYear()
	    var mon = now.getMonth() + 1;               
	    //년도 selectbox만들기               
	    for(var i = 1950 ; i <= nowYear ; i++) {
	        $('#enterYear').append('<option value="' + i + '">' + i + '</option>');    
	    }

	    // 월별 selectbox 만들기            
	    for(var i=1; i <= 12; i++) {                    
	    	var mm = i > 9 ? i : "0"+i ;            
	        $('#enterMonth').append('<option value="' + mm + '">' + mm + '</option>');            
	    }    
	    
	    for(var i = 1950 ; i <= year ; i++) {
	        $('#outYear').append('<option value="' + i + '">' + i + '</option>');    
	    }

	    // 월별 selectbox 만들기            
	    for(var i=1; i <= 12; i++) {                    
	    	var mm = i > 9 ? i : "0"+i ;            
	        $('#outMonth').append('<option value="' + mm + '">' + mm + '</option>');            
	    }
	    
	    $("#enterYear  > option[value="+enterYear+"]").attr("selected", "true");        
	    $("#enterMonth  > option[value="+enterMonth+"]").attr("selected", "true");    
	    $("#outYear  > option[value="+outYear+"]").attr("selected", "true"); 
	    $("#outMonth  > option[value="+outMonth+"]").attr("selected", "true"); 
	}	
}

function jobClassPopGo(){
	 window.open("jobClassPop.go","new","width=850, height=400, resizable=no, scrollbars=no, status=no, location=no, directories=no;");	 
}

function resumeUp(){
	
	var $old_re_no=$('#old_re_no').val();
	var $reco_no=$('#reco_no').val();
	var $re_title=$('#re_title').val();
	var $cl_id=$('#cl_id').val();
	var $re_fn_status=$('#re_fn_status').val();
	var $jp_no=$('#jp_no').val();
	var $jc_no=$('#jc_no').val();
	var $re_sch_name=$('#re_sch_name').val();
	var $enterYear=$('#enterYear').val();
	var $enterMonth=$('#enterMonth').val();
	var $outYear=$('#outYear').val();
	var $outMonth=$('#outMonth').val();
	var $re_major=$('#re_major').val();
	var $re_average=$('#re_average').val();
	var $re_total=$('#re_total').val();
	var $re_register=$('#re_register').val();
	var $re_intro=$('#re_intro').val();
	var re_portfolio=$('#re_portfolio')[0].files[0];
	
	
	console.log($re_title+'/'+$jp_no+'/'+$cl_id+'/'+$re_fn_status+'/'+$re_sch_name+'/'+$enterYear+'/'+$enterMonth+'/'+$outYear+'/'+$outMonth)	
	
	var formData = new FormData();
	
	formData.append("re_portfolio",$('#re_portfolio')[0].files[0]);	
	formData.append("old_re_no", $old_re_no);
	formData.append("reco_no", $reco_no);
	formData.append("re_title", $re_title);
	formData.append("cl_id", $cl_id);
	formData.append("re_fn_status", $re_fn_status);
	formData.append("jp_no", $jp_no);
	formData.append("jc_no", $jc_no);
	formData.append("re_sch_name", $re_sch_name);
	formData.append("enterYear", $enterYear);
	formData.append("enterMonth", $enterMonth);
	formData.append("outYear", $outYear);
	formData.append("outMonth", $outMonth);
	formData.append("re_major", $re_major);
	formData.append("re_average", $re_average);
	formData.append("re_total", $re_total);
	formData.append("re_register", $re_register);
	formData.append("re_intro", $re_intro);	
	
	
	if(	confirm("수정보완한 이력서를 등록하시겠습니까?")==true){
		if($re_title==""){
			alert("제목입력은 필수사항입니다.");
			$('#re_title').focus();
		}else{
		if(confirm("이력서 추가정보를 변경하시겠습니까?")==true){			
			
			$.ajax({
				type:'post',
				url:'resumeUp.ajax',
				data: formData,
				contentType: false,
				processData: false,
				dataType:'JSON',
				success:function(data){
					console.log(data);					
					location.href="resumeAddReg.go?re_no="+data.re_no;
				},
				error:function(e){
					console.log(e);
				}
			});	
			
			} else{
				$.ajax({
					type:'post',
					url:'resumeUp.ajax',
					data: formData,
					contentType: false,
					processData: false,
					dataType:'JSON',
					success:function(data){
						console.log(data);
						alert("등록 완료");
						location.href="resumeList.go";
					},
					error:function(e){
						console.log(e);
					}
				});
			}
		}
	
	}
}


</script>
</html>