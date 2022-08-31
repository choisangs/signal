<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
table {
	width:100%;
	border:1px solid #787878;
	border-collapse:collapse;
}

table tr th {
	padding:10px;
	border:1px solid #787878;
	background-color:#efefef;
}
table tr td {
	padding:10px;
	border:1px solid #787878;
}
/* 페이지 이동 CSS 작업 */
.pageInfo{
      list-style : none;
      display: inline-block;
    margin: 50px 0 0 100px;      
  }
  .pageInfo li{
      float: left;
    font-size: 20px;
    margin-left: 18px;
    padding: 7px;
    font-weight: 500;
  }
 a:link {color:black; text-decoration: none;}
 a:visited {color:black; text-decoration: none;}
 a:hover {color:black; text-decoration: underline;}
 .active{
      background-color: #cdd5ec;
  }
/* 메인 섹션 영역 */  
#section {
	width : 800px;
	position: relative;
	top : -380px;
	left : 350px;
}
</style>
</head>
<body>
<div id="section">
<p>기업페이지 > 면접관리 리스트 <p>
<form action="comInterviewList.do" method="get" id="form">
     	<select name="searchOption" id="searchOption">
			<option value="">전체</option>
			<option value="이름" ${searchOption == '이름'? 'selected="selected"' : ''}>이름</option>
			<option value="이력서제목" ${searchOption == '이력서제목'? 'selected="selected"' : ''}>이력서제목</option>
	 	</select>
			<input  type="text"  name="search" id="search" value="" required/>
			<button type="submit" >검색</button>
			<!-- 페이징  -->
		 	<input type="hidden" name="pageNum" value="1"/>
 </form>		
<table>
    <colgroup>
        <col width="70"></col>
        <col width="80"></col>
        <col width="90"></col>
        <col width="70"></col>
        <col width="70"></col>
        <col width="250"></col>
     	<col width="100"></col>
        <col width="80"></col>
    </colgroup>
        <thead>
            <tr>
                <th>면접번호</th>
                <th>사진</th>
                <th>이름</th>
                <th>나이</th>
                <th>성별</th>
                <th>이력서제목</th>
                <th>면접일</th>
                <th>면접결과</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${comInterviewList}" var="comInterviewList" >
                    <tr>
                    	 <td align="center">${comInterviewList.inter_no}</td>
                    	  <td align="center">${comInterviewList.cl_photo}</td>
                    	  <td align="center">${comInterviewList.cl_name}</td>
                    	  <td align="center">${comInterviewList.cl_age}</td>
                    	  <td align="center">${comInterviewList.cl_gender}</td>
                    	  <td align="center">${comInterviewList.re_title}</td>
                    	  <td align="center">${comInterviewList.inter_date}
                    	  <button type="button" onclick="pop(${comInterviewList.inter_no});">일정 변경</button>
                    	  </td>
                    	  <td align="center">${comInterviewList.inter_result}
                    	  <button type="button" onclick="pop2(${comInterviewList.inter_no});">결과 수정</button>
                    	  </td>
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
	  </div>
</body>
<script>

$(".pageInfo a").on("click", function(e){
    e.preventDefault();
        if($("#searchOption").val()=="" && $("#search").val()==""){
    		$("#moveForm").find("input[name='pageNum']").val($(this).attr("href"));
        	$("#moveForm").attr("action", "/comInterviewList.go");
    		$("#moveForm").submit();
        } else {
        	$("#form").find('input[name="pageNum"]').val($(this).attr("href"));
        	$("#form").submit();
        }
        
});


function pop(inter_no){
	
	 window.open("comInterviewDate.go?inter_no="+inter_no, '', 'width=550, height=300, left=100, top=50'); 
	 
	 return false;

}

function pop2(inter_no){
	
	 window.open("comInterviewUpdate.go?inter_no="+inter_no, '', 'width=800, height=450, left=100, top=50'); 
	 
	 return false;

}

</script>
</html>