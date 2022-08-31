<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../../resources/inc/header.jsp" %>
<style>
	#section {
		width : 800px;
		position: relative;
		top : -380px;
		left : 350px;
	}
	
	.hidden {
		display : none;
	}
	
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
	
	#listgo {
	    padding: 5px 10px;
	    font-size: 15px;
	    outline: none;
	    border: none;
	    color: #fff;
	    background-color: #333;
	    position: relative;
	    top: -350px;
	    left: 700px;
	}
</style>
<body>
	
	<table id="section">
		 <tr>
		    <th>제목</th>
		    <td colspan="6">${dto.re_title}</td>
		  </tr>
		  <tr>
		    
		  </tr>
		  <tr>
		    <td rowspan="3"><img src="/photo/${dto.cl_photo}" width="100px" height="100px"/></td>
		    <th>이름</th>
		    <td>${dto.cl_name}</td>
		    <th>생년월일</th>
		    <td>${dto.cl_birth}</td>
		    <th>성별</th>
		    <td>${dto.cl_gender}</td>
		  </tr>
		  <tr>
		    <th>주소</th>
		    <td colspan="5">${dto.cl_address}</td>
		  </tr>
		  <tr>
		    <th>연락처</th>
		    <td colspan="2">${dto.cl_call}</td>
		    <th>이메일</th>
		    <td colspan="2">${dto.cl_email}</td>
		  </tr>
		  <tr>
		    <th colspan="7" style="text-align:start;">희망직무</th>
		  </tr>
		  <tr>
		    <th>대분류</th>
		    <td colspan="3">${dto.jp_name}</td>
		    <th>중분류</th>
		    <td colspan="2">${dto.jc_name}</td>
		  </tr>
		  <tr>
		    <th colspan="5">최종학력</th>
		    <td colspan="2">${dto.re_fn_status}</td>
		  </tr>
		  <tr>
		    <th>학교명</th>
		    <td colspan="3">${dto.re_sch_name}</td>
		    <th>재학기간</th>
		    <td colspan="4">${dto.re_sch_period}</td>		    
		  </tr>
		  <tr>
		    <th>전공</th>
		    <td colspan="2">${dto.re_major}</td>
		    <th>학점</th>
		    <td>${dto.re_average} / ${dto.re_total}</td>
		    <th>학적상태</th>
		    <td>${dto.re_register}</td>
		  </tr>
		  <tr>
		    <th colspan="7">자기소개서</th>
		  </tr>
		  <tr>
		    <td colspan="7">${dto.re_intro}</td>
		  </tr>
		  <tr>
		    <th colspan="2">포트폴리오</th>
		    <td colspan="5"><a href="download/${dto.re_portfolio_ori}/${dto.re_portfolio}/">${dto.re_portfolio_ori}</a></td>
		  </tr>		
		</table>
	<br/>
	<br/>
	<table id="section">
		<thead>
			<tr>
				<th colspan="4">경력사항</th>
			</tr>
			<tr>
				<th>기업명</th>
				<th>근무기간</th>
				<th>직무</th>
				<th>근무내용</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${careerDto}" var="career">
				<tr>
					<td>${career.ca_com_name}</td>
					<td>${career.ca_period}</td>
					<td>${career.ca_job}</td>
					<td>${career.ca_content}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/>
	<br/>
	<table id="section">
		<thead>
			<tr>
				<th colspan="4">그 외 활동</th>
			</tr>
			<tr>
				<th>분류</th>
				<th>이름</th>
				<th>기간</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${socialDto}" var="social">
				<tr>
					<td>${social.soc_field}</td>
					<td>${social.soc_name}</td>
					<td>${social.soc_period}</td>
					<td>${social.soc_content}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/>
	<br/>
	<table id="section">
		<thead>
			<tr>
				<th colspan="4">자격증/수상내역</th>
			</tr>
			<tr>
				<th>분류</th>
				<th>이름</th>
				<th>취득일</th>
				<th>발행기관</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${licenseDto}" var="license">
				<tr>
					<td>${license.li_field}</td>
					<td>${license.li_name}</td>
					<td>${license.li_org}</td>
					<td>${license.li_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/>
	<br/>
	<table id="section">
		<thead>
			<tr>
				<th colspan="3">내가받은 추천</th>
			</tr>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>추천일</th>				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${recommendDto}" var="reco">
				<tr>
					<td id="reco_no" class="hidden">${reco.reco_no }</td>
					<td>${reco.reco_cl_id}</td>
					<td>${reco.reco_date}</td>
					<td>${reco.reco_req_memo}</td>					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/>
	<br/>
	<div id="section">
		<h3>셀프평가</h3>
		<h4>평점 : ${selfTest6 }점</h4>
	</div>
	<table id="section">		
		<tr>
			<th>셀프코멘트</th>
			<td colspan="4">${selfComment }</td>
		</tr>
		<tr>
			<th>책임감</th>
			<th>침착함</th>
			<th>적극성</th>
			<th>자신감</th>
			<th>성실함</th>
		</tr>
		<tr>
			<td>${selfTest1 }</td>
			<td>${selfTest2 }</td>
			<td>${selfTest3 }</td>
			<td>${selfTest4 }</td>
			<td>${selfTest5 }</td>
		</tr>
	</table>
	<br/>
	<br/>	
	<div id="section">
		<h3>면접평가</h3>
		<h4>평점 : ${interviewTestDto6 }점    개수 : ${interviewTestDto7 }개</h4>
	</div>
	<table id="section">		
		<tr>
			<th>책임감</th>
			<th>침착함</th>
			<th>적극성</th>
			<th>자신감</th>
			<th>성실함</th>
			<th>면접 코멘트</th>
		</tr>		
			<c:forEach items="${interviewTestDto1}" var="inter1" varStatus="status">
				<tr>
				<td>${inter1.inter_score}</td>
				<td>${interviewTestDto2[status.index].inter_score}</td>
				<td>${interviewTestDto3[status.index].inter_score}</td>
				<td>${interviewTestDto4[status.index].inter_score}</td>
				<td>${interviewTestDto5[status.index].inter_score}</td>
				<td>${interviewComment[status.index].inter_comment}</td>
				</tr>
			</c:forEach>			
	</table>
	

	<br/>
	<br/>
		<button id="listgo" onclick="location.href='companyApplyList.go'">목록</button>
	

</body>
<script></script>
</html>