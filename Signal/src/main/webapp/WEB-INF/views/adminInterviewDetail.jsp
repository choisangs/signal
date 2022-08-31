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
<p>마이페이지 > 면접현황 <p>
<form action="" method="get" >
    <table class="dto">
        
            <tr>
                <th>채용공고제목</th>
                <td>${dto.jpo_title} </td>
            </tr>
            <tr>
                <th>기업명</th>
                <td>${dto.com_name} </td>
            </tr>
            <tr>
                <th>면접일</th>
                <td>${dto.inter_date}</td>
            </tr>
            <tr>
                <th>면접현황</th>
                <td>${dto.inter_result}</td>
            </tr>
            <tr>
                <th>평점상세내역</th>
                <td>
                	<table>
	                	<c:forEach items="${interviewDetailResultList}" var="interviewDetailResultList" >
		                    <tr>
		                      
		                        <td align="center">${interviewDetailResultList.it_que}</td>
		                        <td align="center">${interviewDetailResultList.inter_score} 점</td> 
		                    </tr>
						</c:forEach>
                	</table>
                </td>
            </tr>
            <tr>
                <th>코멘트</th>
                <td>${dto.inter_comment}</td>
           </tr>
        </table>
        <input type="button" value="목록" onclick="location.href='/adminObjectionList.go'"/>
    </form>		
    </div>
</body>
<script>
</script>
</html>