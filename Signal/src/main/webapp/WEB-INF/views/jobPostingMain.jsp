<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header_jobPosting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<style>

		#searchBox{
			margin-left:150px;
		}
	   	table,h2,h6{
			width: 80%;
	   		margin: 0 auto;
	   }
	   h3{
			width: 80%;
	   		margin: 0 auto;
	   		text-align: left;
	   		margin-bottom: 10px;
	   		font-weight: bolder;
	   }
	   	table,th,td{
		   	border: 1px solid gray;
		   	border-collapse: collapse;
	  		padding: 3px;
	   }
	   	th{
	   		font-size: 15px;
	   		font-weight: bold;
	   		text-align: center;
	   	}
		h2{
			margin-bottom: 10px;
			text-align: center;
		}
		.find-btn{
			text-align: right;
			margin-top: 10px;
		}
		.find-btn1{
			display :inline-block;
			color: #fff;
			border-radius: 5px;
			width: 100px;
			height: 30px;
			background-color: #1f3864ff;
		}

		table#comList < td{
			text-align: left;
			border-collapse : collapse;
		}
		table#comList {
			width: 80%;
	   		margin: 0 auto;
		}
		.cen{
			text-align: center;
		}

	select{
		margin-bottom: 5px;
	}
	#searchBox{
		display :inline-block;
	}

	#form {
		position: relative;
		left: 140px;
	}
	
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}

	</style>
</head>
<body>
       	<h3>채용공고</h3>
       	<br/>
	       	<form action="/jobPostingMain.do" method="get" id="form">  
			 	&nbsp; <select name=jpo_region id="searchOption" onchange="jpoRegionShow();">
				<option value="">근무지역</option>
				<option value="서울" ${jpo_region == '서울'? 'selected="selected"' : ''}>서울</option>
				<option value="경기" ${jpo_region == '경기'? 'selected="selected"' : ''}>경기</option>
				<option value="인천" ${jpo_region == '인천'? 'selected="selected"' : ''}>인천</option>
				<option value="대전" ${jpo_region == '대전'? 'selected="selected"' : ''}>대전</option>
				<option value="강원" ${jpo_region == '강원'? 'selected="selected"' : ''}>강원</option>
				<option value="충남" ${jpo_region == '충남'? 'selected="selected"' : ''}>충남</option>
				<option value="충북" ${jpo_region == '충북'? 'selected="selected"' : ''}>충북</option>
				<option value="경남" ${jpo_region == '경남'? 'selected="selected"' : ''}>경남</option>
				<option value="경북" ${jpo_region == '경북'? 'selected="selected"' : ''}>경북</option>
				<option value="세종" ${jpo_region == '세종'? 'selected="selected"' : ''}>세종</option>
				<option value="전남" ${jpo_region == '전남'? 'selected="selected"' : ''}>전남</option>
				<option value="전북" ${jpo_region == '전북'? 'selected="selected"' : ''}>전북</option>
				<option value="대구" ${jpo_region == '대구'? 'selected="selected"' : ''}>대구</option>
				<option value="부산" ${jpo_region == '부산'? 'selected="selected"' : ''}>부산</option>
				<option value="울산" ${jpo_region == '울산'? 'selected="selected"' : ''}>울산</option>
				<option value="광주" ${jpo_region == '광주'? 'selected="selected"' : ''}>광주</option>
				<option value="제주" ${jpo_region == '제주'? 'selected="selected"' : ''}>제주</option>

				</select>
			       	&nbsp;
	       		<select name="jp_no" id="searchOption1" onchange="jobBigListShow();">
	       		<option value="">직군</option>
	       			<c:forEach items="${jobBigList}" var="jobBigList">
						<option value="${jobBigList.jp_no}" ${jp_no == jobBigList.jp_no ? 'selected="selected"' : ''}>${jobBigList.jp_name}</option>
					</c:forEach>
				</select>
				&nbsp;
				&nbsp;
			     기업명&nbsp;
				<input  type="text"  name="search" id="search" value="${search}"/>
	       		<input type="submit" value="조건검색">
	       		<!-- 페이징  -->
				<input type="hidden" name="pageNum" value="1"/>
			</form>
	
				<table id="mainPostingList">
					<thead>
				   		<tr>
				   			<th width="5px">번호</th>
							<th width="60px">기업명</th>
						    <th width="160px">공고제목</th>
   							<th width="90px">직무분야</th>
   							<th width="30px">지역</th>
							<th width="100px">모집기간</th>
							<th width="5px">조회수</th>
							<th width="25px">마감여부</th>
				         </tr>
			    	</thead>
			    	
			     	<tbody>
	   				<c:forEach items="${mainJpoList}" var="mainJpoList">
	   					<tr>
							<td class="cen">${mainJpoList.jpo_no}</td>
							<td class="cen">${mainJpoList.com_name}</td>
							<td><a href="PostingDetailMain.go?jpo_no=${mainJpoList.jpo_no}&&com_id=${mainJpoList.com_id}&&curState=1" >${mainJpoList.jpo_title}</a></td>
							<td class="cen">${mainJpoList.jp_name} > ${mainJpoList.jc_name}</td>
							<td class="cen">${mainJpoList.jpo_region}</td>
							<td class="cen">${mainJpoList.jpo_start} ~ ${mainJpoList.jpo_deadline}</td>
							<td class="cen">${mainJpoList.jpo_views}</td>
							<td class="cen">${mainJpoList.jpo_state}</td>
						</tr>
				</c:forEach>
		     		</tbody>
	     		</table>
	       <!--페이징 -->
	     		<div class="pageInfo_wrap" >
        	<div class="pageInfo_area">
        		<ul id="pageInfo" class="pageInfo">
        		<!-- 이전페이지 버튼 -->
	                <c:if test="${pageMaker.prev}">
	                    <li class="pageInfo_btn previous"><a href="${pageMaker.startPage-1}">Previous</a></li>
	                </c:if>
	        		
	 				<!-- 각 번호 페이지 버튼 -->
	                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
	                    <li class='pageInfo_btn ${pageMaker.cri.pageNum == num ? "active": "" }'><a href="${num}">${num}</a></li>
	                </c:forEach>
	                
	                <!-- 다음페이지 버튼 -->
	                <c:if test="${pageMaker.next}">
	                    <li class="pageInfo_btn next"><a href="${pageMaker.endPage + 1 }">Next</a></li>
	                </c:if> 
        		</ul>
        	</div>
    		</div>	
	        <form id="moveForm" method="get">
	  	 <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
	  </form>	
	<br/>   
</body>
<script>
function jpoRegionShow(){
	$("#form").submit();
}

function jobBigListShow(){
	$("#form").submit();
}

/*
$(".pageInfo a").on("click", function(e){
	e.preventDefault();
    $("#moveForm").find("input[name='pageNum']").val($(this).attr("href"));
    $("#moveForm").attr("action", "/jobPostingMain.do");
    $("#moveForm").submit();
});
*/

$(".pageInfo a").on("click", function(e){
	e.preventDefault();
	if($("#searchOption").val() =="" && $("#searchOption1").val()=="" && $("#search").val()=="" && $("#searchOption2").val()==""){
    	$("#moveForm").find("input[name='pageNum']").val($(this).attr("href"));
       	$("#moveForm").attr("action", "/jobPostingMain.go");
    	$("#moveForm").submit();
    } else {
       	$("#form").find('input[name="pageNum"]').val($(this).attr("href"));
       	$("#form").submit();
    }     
});

</script>
</html>