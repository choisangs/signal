<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
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
   		width: 70%;
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
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
	
	#jp-list{
		position: relative;
		top:-330px;
		left:80px;
	}

</style>
</head>
<body>
 	<section>
		<div id="jp-list">
        	<br/>
	        		<h2>채용공고 수정 및 상세보기</h2><br/>
	        	<form action="/jobPostingUpdate.do?jpo_no=${dto1.jpo_no}" method="post" enctype="multipart/form-data" id="checkDate">
		        <div>
					<input type="hidden" name="jpo_no" value="${dto1.jpo_no}"/>
			        	<table id="jobPostingDetail">
    						<tr>
								<th>공고제목</th>
								<td><input type="text" name="jpo_title" value="${dto1.jpo_title}" required/></td>
							</tr>
							<tr>
								<th>마감여부</th>
								<td>
									<c:if test="${dto1.jpo_state.equals('진행중')}">
        								<lable><input type="radio" name="jpo_state" value="진행중" checked="checked" required="required"/> 진행중</lable>&nbsp;&nbsp;
										<lable><input type="radio" name="jpo_state" value="마감" required="required"/> 마감</lable>&nbsp;&nbsp;
									</c:if>
									<c:if test="${dto1.jpo_state.equals('마감')}">
        								<lable><input type="radio" name="jpo_state" value="진행중" required="required"/> 진행중</lable>&nbsp;&nbsp;
										<lable><input type="radio" name="jpo_state" value="마감" checked="checked" required="required"/> 마감</lable>&nbsp;&nbsp;
									</c:if>
								</td>
							</tr>
    						<tr>
								<th>조회수</th>
								<td>${dto1.jpo_views}</td>
							</tr>
				    		<tr>
								<th>기업명</th>
								<td>${dto1.com_name}</td>
							</tr>
				    		<tr>
								<th>모집분야</th>
								<td>
									<c:if test="${dto1.jpo_field.equals('신입')}">
        								<lable><input type="radio" name="jpo_field" value="신입" checked="checked" required="required"/> 신입</lable>&nbsp;&nbsp;
										<lable><input type="radio" name="jpo_field" value="경력" required="required"/> 경력</lable>&nbsp;&nbsp;
										<lable><input type="radio" name="jpo_field" value="무관" required="required"/> 무관</lable>
									</c:if>
									<c:if test="${dto1.jpo_field.equals('경력')}">
        								<lable><input type="radio" name="jpo_field" value="신입" required="required"/> 신입</lable>&nbsp;&nbsp;
										<lable><input type="radio" name="jpo_field" value="경력" checked="checked" required="required"/> 경력</lable>&nbsp;&nbsp;
										<lable><input type="radio" name="jpo_field" value="무관" required="required"/> 무관</lable>
									</c:if>
									<c:if test="${dto1.jpo_field.equals('무관')}">
        								<lable><input type="radio" name="jpo_field" value="신입" required="required"/> 신입</lable>&nbsp;&nbsp;
										<lable><input type="radio" name="jpo_field" value="경력" required="required"/> 경력</lable>&nbsp;&nbsp;
										<lable><input type="radio" name="jpo_field" value="무관" checked="checked" required="required"/> 무관</lable>
									</c:if>
								</td>
							</tr>
				   			<tr>
								<th>근무형태</th>
								<td>
									<c:if test="${dto1.jpo_type.equals('정규직')}">
										<lable><input type="radio" name="jpo_type" value="정규직" checked="checked" required="required"/> 정규직</lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<lable><input type="radio" name="jpo_type" value="비정규직" required="required"/> 비정규직</lable>
									</c:if>
									<c:if test="${dto1.jpo_type.equals('비정규직')}">
										<lable><input type="radio" name="jpo_type" value="정규직"  required="required"/> 정규직</lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<lable><input type="radio" name="jpo_type" value="비정규직" checked="checked" required="required"/> 비정규직</lable>
									</c:if>
								</td>
								<!--  <td><input type="text" name="jpo_type" value="${dto1.jpo_type}" required /></td>  -->
							</tr>
				    		<tr>
								<th>직무분류</th>
								<td>	
									<select name="jp_no" id="jp_no" required="required" value="${dto1.jp_no}" >
										<option value="0" >직군</option>
										<option value="1" <c:if test="${(dto1.jp_no).equals('1')}">selected</c:if>>IT</option>
										<option value="2" <c:if test="${(dto1.jp_no).equals('2')}">selected</c:if>>건축</option>
										<option value="3" <c:if test="${(dto1.jp_no).equals('3')}">selected</c:if>>항공</option>																				
										<option value="4" <c:if test="${(dto1.jp_no).equals('4')}">selected</c:if>>전기</option>
										<option value="5" <c:if test="${(dto1.jp_no).equals('5')}">selected</c:if>>축산</option>
									</select> 
									 > 
									<select name="jc_no" id="jc_no" required="required" value="${dto1.jc_no}" >
										<option value="0">직업</option>				
										<option value="1" class="mall1"<c:if test="${(dto1.jc_no).equals('1')}">selected</c:if>>웹개발</option>					
										<option value="6" class="mall1"<c:if test="${(dto1.jc_no).equals('6')}">selected</c:if>>퍼블리셔</option>
										<option value="2" class="mall1"<c:if test="${(dto1.jc_no).equals('2')}">selected</c:if>>SI개발</option>
										<option value="3" class="mall1"<c:if test="${(dto1.jc_no).equals('3')}">selected</c:if>>데이터분석가</option>
										<option value="4" class="mall2"<c:if test="${(dto1.jc_no).equals('4')}">selected</c:if>>건설관리</option>
										<option value="5" class="mall2"<c:if test="${(dto1.jc_no).equals('5')}">selected</c:if>>토목설계</option>
										<option value="7" class="mall2"<c:if test="${(dto1.jc_no).equals('7')}">selected</c:if>>내진설계</option>
										<option value="10" class="mall3"<c:if test="${(dto1.jc_no).equals('10')}">selected</c:if>>승무원</option>
										<option value="8" class="mall3"<c:if test="${(dto1.jc_no).equals('8')}">selected</c:if>>항공운송</option>
										<option value="9" class="mall3"<c:if test="${(dto1.jc_no).equals('9')}">selected</c:if>>항공정비</option>
										<option value="11" class="mall4"<c:if test="${(dto1.jc_no).equals('11')}">selected</c:if>>전기설계</option>
										<option value="12" class="mall4"<c:if test="${(dto1.jc_no).equals('12')}">selected</c:if>>전기기사</option>
										<option value="13" class="mall4"<c:if test="${(dto1.jc_no).equals('13')}">selected</c:if>>조경설계</option>
										<option value="14" class="mall5"<c:if test="${(dto1.jc_no).equals('14')}">selected</c:if>>축산식품가공</option>
										<option value="15" class="mall5"<c:if test="${(dto1.jc_no).equals('15')}">selected</c:if>>축산식품유통</option>
										<option value="16" class="mall5"<c:if test="${(dto1.jc_no).equals('16')}">selected</c:if>>도축</option>																																						
									</select>
								</td>
							</tr>
				   			<tr>
								<th>근무지역</th>
								<td>
									<select name="jpo_region" required="required" value="${dto1.jpo_region}">
										<option value="전체" <c:if test="${(dto1.jpo_region).equals('전체')}">selected</c:if>>전체</option>
										<option value="서울" <c:if test="${(dto1.jpo_region).equals('서울')}">selected</c:if>>서울</option>
										<option value="경기" <c:if test="${(dto1.jpo_region).equals('경기')}">selected</c:if>>경기</option>
										<option value="인천" <c:if test="${(dto1.jpo_region).equals('인천')}">selected</c:if>>인천</option>
										<option value="대전" <c:if test="${(dto1.jpo_region).equals('대전')}">selected</c:if>>대전</option>
										<option value="강원" <c:if test="${(dto1.jpo_region).equals('강원')}">selected</c:if>>강원</option>
										<option value="충남" <c:if test="${(dto1.jpo_region).equals('충남')}">selected</c:if>>충남</option>
										<option value="충북" <c:if test="${(dto1.jpo_region).equals('충북')}">selected</c:if>>충북</option>
										<option value="경남" <c:if test="${(dto1.jpo_region).equals('경남')}">selected</c:if>>경남</option>
										<option value="경북" <c:if test="${(dto1.jpo_region).equals('경북')}">selected</c:if>>경북</option>
										<option value="세종" <c:if test="${(dto1.jpo_region).equals('세종')}">selected</c:if>>세종</option>
										<option value="전남" <c:if test="${(dto1.jpo_region).equals('전남')}">selected</c:if>>전남</option>
										<option value="전북" <c:if test="${(dto1.jpo_region).equals('전북')}">selected</c:if>>전북</option>
										<option value="대구" <c:if test="${(dto1.jpo_region).equals('대구')}">selected</c:if>>대구</option>
										<option value="부산" <c:if test="${(dto1.jpo_region).equals('부산')}">selected</c:if>>부산</option>
										<option value="울산" <c:if test="${(dto1.jpo_region).equals('울산')}">selected</c:if>>울산</option>
										<option value="광주" <c:if test="${(dto1.jpo_region).equals('광주')}">selected</c:if>>광주</option>
										<option value="제주" <c:if test="${(dto1.jpo_region).equals('제주')}">selected</c:if>>제주</option>
									</select>
								</td>
							</tr>
				    		<tr>
								<th>모집기간</th>
								<td>
									<input type="date" name="jpo_start" value="${dto1.jpo_start}" id="jpo_start" required/> ~
									<input type="date" name="jpo_deadline" value="${dto1.jpo_deadline}" id="jpo_deadline" required/>
								</td>
							</tr>
							<tr>
								<th>최종학력</th>
								<td>		
									<select name="jpo_education" value="${dto1.jpo_education}" required="required">
										<option value="고등학교 졸업" <c:if test="${(dto1.jpo_education).equals('고등학교 졸업')}">selected</c:if>>고등학교 졸업</option>
										<option value="전문대학교 졸업" <c:if test="${(dto1.jpo_education).equals('전문대학교 졸업')}">selected</c:if>>전문대학교 졸업</option>
										<option value="대학교 졸업" <c:if test="${(dto1.jpo_education).equals('대학교 졸업')}">selected</c:if>>대학교 졸업</option>
										<option value="대학원 졸업" <c:if test="${(dto1.jpo_education).equals('대학원 졸업')}">selected</c:if>>대학원 졸업</option>
									</select> 이상
								</td>
							</tr>
							<tr>
								<th>급여</th>
								<td><input type="text" name="jpo_salary" value="${dto1.jpo_salary}" required/></td>
							</tr>
							<tr>
								<th>복리후생</th>
								<td><input type="text" name="jpo_welfare" value="${dto1.jpo_welfare}" required/></td>
							</tr>
							<tr>
								<th>담당자명</th>
								<td><input type="text" name="jpo_name" value="${dto1.jpo_name}" required/></td>
							</tr>
							<tr>
								<th>담당자 연락처</th>
								<td><input type="text" name="jpo_contact" value="${dto1.jpo_contact}" required/></td>
							</tr>
							<tr>
								<th rowspan="2">채용 내용</th>
								<td>
									<input type="file" name="jpo_photo" multiple="multiple" src="/photo/${dto1.jpo_photo}"/>				
								</td>
					        </tr>
					        <tr>
					            <td>	
					            	<p><img src="/photo/${dto1.jpo_photo}" alt="채용공고" class="comPosting"></p>
					            </td>
							</tr>
							
			        	</table>
			        	<div class="find-btn">
		        			<input class="find-btn1" type="submit" value="수정하기"/>
		        			<input class="find-btn2" type="button" value="돌아가기" onclick="location.href='/jobPostingList.go'"/>
						</div>
	        		</div>
	        	</form>
        </div>
   </section>
  
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
	
	var malls = false;

	function update_selected() {
	  $("#jc_no").val(0);
	  $("#jc_no").find("option[value!=0]").detach();

	  $("#jc_no").append(malls.filter(".mall" + $(this).val()));
	}

	$(function() {
		malls= $("#jc_no").find("option[value!=0]");
		malls.detach();

	  $("#jp_no").change(update_selected);
	  $("#jp_no").trigger("change");
	});

	
	
	
</script>
</html>