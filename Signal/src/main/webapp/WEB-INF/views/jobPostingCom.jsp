<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../resources/inc/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<style>
		.pageInfo li {
	    float: left;
	    font-size: 20px;
	    margin-left: 18px;
	    padding: 7px;
	    font-weight: 500;
	    position: relative;
	    top: -200px;
	    left: 300px;
	}

	   	table,h2,h6{
	   		width: 60%;
	   		margin: 0 auto;
	   }
	   h3{
   	   		width: 60%;
	   		margin: 0 auto;
	   		text-align: left;
	   		margin-bottom: 10px;
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
		.find-btn1 {
		    display: inline-block;
		    color: #fff;
		    border-radius: 5px;
		    width: 100px;
		    height: 30px;
		    background-color: #1f3864ff;
		    position: relative;
		    top: -150px;
		    left: -300px;
		}

		table#comList < td{
			text-align: left;
			border-collapse : collapse;
		}
		table#comList {
			width: 60%;
	   		margin: 0 auto;
		}
		.cen{
			text-align: center;
		}
		img.comLogo{
			width: 200px;
			height: 160px;
		}
		
		#form {
	    position: relative;
	    top: -200px;
	    left: 305px;
	    width: 350px;
		}
		
		#jp-list > #infoList{
		   	position: relative;
		    top: -250px;
		    width: 800px;
		}
		
		body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
		}
		
		#comTitle {
	    position: relative;
	    top: -330px;
	    left: 50px;
		}
		
		#postingList {
	    position: relative;
	    top: -200px;
	    left: 0px;
		}
		
		table tr comphoto {
	    padding: 10px;
	    border: 1px solid #787878;
	    background-color: white;
	    text-align: center;
	    color: white;
		}
				
	</style>
</head>
<body>
   <section>
    	<div id="jp-list">
    		<div id="comTitle">
       		<h3>채용관리</h3>
    		</div>
       			<table id="infoList">
       				<tr>
       					<th rowspan="4" id="comphoto">
       						<c:forEach items="${list}" var="path">
                  				<p><img src="/photo/${dto.ci_photo}" alt="로고" class="comLogo"></p>
                  			</c:forEach>
               			</th>
       				</tr>
       				<tr>
       					<td colspan="5">${dto.com_name}</td>
       				</tr>
   			    	<tr>
       					<td>기업주소 : </td>
       					<td colspan="4">${dto.com_address}</td>
       				</tr>
   			    	<tr>
       					<td colspan="2">면접 코멘트 작성률 : </td>
       					<td>${dto.comment_a}%</td>
      					<td>코멘트 수: </td>
       					<td>${dto.comment}/${dto.apply}개</td>
       				</tr>
       			</table>
     	  		<br/>	
       			<div class="find-btn">
       				<input class="find-btn1" type="button" value="신규 공고 등록" onclick="location.href='/jobPostingWrite.go'">
				</div>
   		</div>
	</section>
       	<br/>
       


	<form action="jobPostingList.do" method="get" id="form">
     	<select name="searchOption" id="searchOption">
			<option value="">전체</option>
			<option value="진행중" ${searchOption == '진행중'? 'selected="selected"' : ''}>진행중</option>
			<option value="마감" ${searchOption == '마감'? 'selected="selected"' : ''}>마감</option>
	 	</select>
			<button type="submit" >검색</button>
			<!-- 페이징  -->
		 	<input type="hidden" name="pageNum" value="1"/>
 	</form>
			<div>
			<table id="postingList">
				<thead>
			   		<tr>
						<th width="10px">번호</th>
					    <th width="150px">채용공고 제목</th>
						<th width="100px">모집기간</th>
						<th width="30px">조회수</th>
						<th width="50px">마감여부</th>
			         </tr>
		    	</thead>
		    	
		     	<tbody>
   				<c:forEach items="${jpoList}" var="jpoList">
   					<tr>
						<td class="cen">${jpoList.jpo_no}</td>
						<td><a href="jobPostingDetail.do?jpo_no=${jpoList.jpo_no}" >${jpoList.jpo_title}</a></td>
						<td class="cen">${jpoList.jpo_start} ~ ${jpoList.jpo_deadline}</td>
						<td class="cen">${jpoList.jpo_views}</td>
						<td class="cen">
							<c:choose>
	                       		<c:when test="${jpoList.jpo_state eq '진행중'}">진행중</c:when>
	                       		<c:when test="${jpoList.jpo_state eq '마감'}">마감</c:when>
	                       	</c:choose>
	                	</td>
					</tr>
			</c:forEach>
	     		</tbody>
     		</table>
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
$(".pageInfo a").on("click", function(e){
    e.preventDefault();
        if($("#searchOption").val()=="" && $("#search").val()==""){
    		$("#moveForm").find("input[name='pageNum']").val($(this).attr("href"));
        	$("#moveForm").attr("action", "/jobPostingList.go");
    		$("#moveForm").submit();
        } else {
        	$("#form").find('input[name="pageNum"]').val($(this).attr("href"));
        	$("#form").submit();
        }
        
});



 
</script>
</html>