<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header_jobPosting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

	textarea {
	   	width:100%;
	   	resize: none;
	   	height: 500px;
   }
   	table,h2,h6{
   		width: 80%;
   		margin: 0 auto;
   }
   	table,th,td{
	   	border: 1px solid gray;
	   	border-collapse: collapse;
   }
   td{
   		padding: 3px;
   }
   	th{
   		font-size: 15px;
   		font-weight: bold;
   		height: 30px;
   		width: 20%;
   		text-align: center;
   	}
   	input[type="text"],[type="url"]{
   		width: 100%;
   		padding: 3px;
   	}
	h2{
		margin-bottom: 10px;
		text-align: center;
	}
	.find-btn{
		text-align: center;
		margin-top: 10px;
	}
	.find-btn1,.find-btn2{
		display :inline-block;
		color: #fff;
		border-radius: 5px;
		width: 100px;
		height: 30px;
		
	}
	.find-btn1{
		background-color: #1f3864ff;
	}
	.find-btn2{
		background-color: #7f7f7fff;
	}
	img.comPosting{
		width: 100%;
	}

</style>
	<style type="text/css">
	.tg  {border-collapse:collapse;border-spacing:0;}
	.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
	  overflow:hidden;padding:10px 5px;word-break:normal;}
	.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
	  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
	.tg .tg-xcht{border-color:#ffffff;font-size:13px;text-align:left;vertical-align:top; font-weight: bold; color: #434343;}
	.tg .tg-xcht2{border-color:#ffffff;font-size:13px;text-align:left;vertical-align:top;color: #434343;}
	.tg .tg-zv4m{border-color:#ffffff;text-align:left;vertical-align:top;font-weight:bold;font-size:16px;}
	.tg .tg-zv3m{border-color:#ffffff;text-align:left;vertical-align:top;font-weight:bold;font-size:16px; border-bottom:2px solid black; border-top:2px solid black;}
	.tg .tg-xwu2{font-size:24px;border-color:#ffffff;color:#303498;font-weight:bold;text-align:left;vertical-align:top;}
	input[type='url']{
		font-size: 12px;
		border: none;
		font-weight: normal;
		color: navy;
	}
	td p{
		font-size: 14px;
	}
	.comLogo{
		object-fit: scale-down;
	}
	</style>
</head>
<body>

	<table class="tg" id="PostingDetailMainPage">
	<input type="hidden" name="jpo_no" value="${dto.jpo_no}"/>
	<tbody>
	  <tr>
	    <td class="tg-xwu2" colspan="9">${dto.jpo_title}<br/>  <br/></td>
	  </tr>
	  <tr>
	    <td class="tg-xcht" rowspan="2" width="100">
	    <p>모집기간</p>
	    <p>근무형태</p>
	    <p>모집분야</p>
	    <p>모집기준</p>
	    <p>학력조건</p>
	    <p>근무지역</p>
	    <p>급여</p>
	    <p>복리후생</p>
	    </td>
	    <td class="tg-xcht2" colspan="3" rowspan="2">
	    <p>${dto.jpo_start} ~ ${dto.jpo_deadline}</p>
	    <p>${dto.jpo_type}</p>
	    <p>${dto.jp_name} > ${dto.jc_name}</p>
	    <p>${dto.jpo_field}</p>
	    <p>${dto.jpo_education} 이상</p>
	    <p>${dto.jpo_region}</p>
	    <p>${dto.jpo_salary}</p>
	    <p>${dto.jpo_welfare}</p>
	    </td>
	    <td class="tg-zv4m" rowspan="3">   </td>
	    <td class="tg-xcht" width="100">
	    <p>조회수</p>
	    <p>지원자수</p>
	    <p>코멘트 작성률</p>
	    <p>코멘트 수</p>
	    <td class="tg-xcht2" colspan="3">
	    <p>${dto.jpo_views}</p>
	    <p>${dto.apply_no}</p>
	    <p>${dto.comment_a} %</p>
	    <p>${dto.apply}개 중 ${dto.comment}개 작성</p>
	    </td>
	  </tr>
	  <tr>
	    <td class="tg-zv4m" colspan="4" rowspan="2">
	    <p><a href="${dto.ci_web}" target="_blank" id="link"> ${dto.com_name}</a></p>
	    <img src="/photo/${dto.ci_photo}" width="250" alt="로고" class="comLogo">
	    </td>
	  </tr>
	  <tr>
	    <td class="tg-xcht">
	    <p>담당자</p>
	    <p>담당자 번호</p>
	    </td>
	    <td class="tg-xcht2" colspan="3">
	    <p>${dto.jpo_name}</p>
	    <p>${dto.jpo_contact}</p>
	    </td>
	  </tr>
	  <tr>
	    <td class="tg-zv3m" colspan="9" > <h4>[ 모집내용 ]</h4>
	    <img src="/photo/${dto.jpo_photo}" alt="채용공고" class="comPosting">
	    </td>
	  </tr>
	</tbody>
	</table>
<input id="apply_button" type="button" value="지원하기"/>
<input type="button" value="목록으로" onclick="location.href='/clientOfferList.go'">
</body>
<script>
	$(function(){ 
		$("#checkDate").submit(function(){
			var jpo_start = $('#jpo_start').val();
			var jpo_deadline = $('#jpo_deadline').val();        
			var startArray = jpo_start.split('-');         //-을 구분자로 연,월,일로 잘라내어 배열로 반환 
			var endArray = jpo_deadline.split('-');     
			       //배열에 담겨있는 연,월,일을 사용해서 Date 객체 생성         
			var start_date = new Date(startArray[0], startArray[1], startArray[2]);         
			var end_date = new Date(endArray[0], endArray[1], endArray[2]);   //날짜를 숫자형태의 날짜 정보로 변환하여 비교한다.         
			
			if(start_date.getTime() > end_date.getTime()) {
				 alert("종료날짜보다 시작날짜가 작아야합니다.");            
				 return false;
				}     
			}); 
	});
	
	var id = "${sessionScope.loginId}";
	var company = "${sessionScope.isCompany}";
	var admin = "${sessionScope.isAdmin}";
	var client = "${sessionScope.isClient}";
	
    $("#apply_button").on("click",function(){
    	if(client != "" && ${dto.jpo_state.equals("진행중")}){
    		location.href="/applyOne.do?jpo_no=${dto.jpo_no}&re_no=${dto.re_no}&com_id=${dto.com_id}";
    	}else{
    		 location.href = "/jobPostingMain.go";
    	}
			
    	if(id == "") {
           alert("로그인이 필요한 서비스입니다.");
           location.href = "/jobPostingMain.go";
        } else if(${dto.jpo_state.equals("마감")}){
            	alert("마감된 공고로 지원이 불가합니다.");
                location.href = "/jobPostingMain.go";
            } else if(company != "" || admin != "") { 
                alert("개인회원만 지원 가능합니다.");
            }
    	
    });

</script>
</html>