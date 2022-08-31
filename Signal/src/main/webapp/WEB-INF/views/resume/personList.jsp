<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../../resources/inc/header_jobPosting.jsp" %>
<style>
   #section {
       	width: 800px;
	    position: relative;
	    top: 30px;
	    left: 350px;
   }
   
	
	body {
	   width:1500px;
	   margin: 0 auto;
	   padding: 0;
	   font-size: 15px;
	}
	
	#searchOption {
    position: relative;
    top: 23px;
    left: 350px;
	}
	
	#gosearch {
    position: relative;
    top: 20px;
    left: 600px;
	}
	
	#searchStartAge {
    position: relative;
    top: 23px;
    left: -790px;
	}
	
	#searchEndAge {
    position: relative;
    top: 23px;
    left: -770px;
	}
	
	#between {
    position: relative;
    top: 20px;
    left: -780px;
    font-size: 30px;
	}
	
	#age {
    position: relative;
    top: 23px;
    left: 307px;
	}
	
	h3 {
	position: relative;
    top: 5px;
    left: 200px;
	}
	
	input[type="button"], button[type="button"], button {
    padding: 5px 10px;
    font-size: 12px;
    outline: none;
    border: none;
    color: #fff;
    background-color: #333;
	}
   
   
</style>

<body>
	<h3>인재채용 - 인재풀(Pull)</h3>
   <form action="personListSearch.do" method="get" id="form">
      <!-- select option 넣기 -->
         <select name="searchOption" id="searchOption">
            <option value="0" ${searchOption == 0? 'selected="selected"' : ''}>전체</option>
            <option value="1" ${searchOption == 1? 'selected="selected"' : ''}>면접평점 높은 순</option>            
         </select>
         &nbsp;<button id="gosearch" type="submit">검색</button>
         <select name="searchEndAge" id="searchEndAge" style="float:right;">
            <option value="1000" ${searchOption == 1000? 'selected="selected"' : ''}>---</option>
         </select><span  id="between" style="float:right;">~</span>
         <select name="searchStartAge" id="searchStartAge" style="float:right;">
            <option value="0" ${searchOption == 0? 'selected="selected"' : ''}>---</option>
         </select>         
         <span id="age">나이검색</span>
         <input type="hidden" name="pageNum" value="1"/>      
   </form>   
   <table id="section">
      <thead>         
         <tr>
            <th>나이</th>
            <th>성별</th>
            <th>면접평점</th>
            <th>면접코멘트수</th>
            <th>셀프평점</th>
            <th>입사제안</th>            
         </tr>
      </thead>
      <tbody id="list">
      <c:choose>
         <c:when test="${list.size()>0}">
            <c:forEach items="${list}" var="person">
               <tr>
                  <td>${person.cl_age }</td>
                  <td>${person.cl_gender }</td>
                  <td>${person.avr_inter_grade }</td>
                  <td>${person.cnt_inter }</td>
                  <td>${person.avr_st_score }</td>
                  <td><button class="jobOffer" onclick="popOpen('${person.cl_id }')">입사제안</button></td>
               </tr>
            </c:forEach>
         </c:when>
         <c:otherwise>
            <tr>
               <td colspan="6" align="center">일치하는 인재정보가 없습니다.</td>
            </tr>
         </c:otherwise>
      </c:choose>
         <tr>
         <td colspan="6">
            <div class="pageInfo_wrap">
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
            <!-- 페이지 이동 관련 form 태그 -->
            <form id="moveForm" method="get">
                  <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
              </form>
            </td>            
         </tr>
      </tbody>      
   </table>   
   <input type="hidden" id="startAgeVal" value="${searchStartAge}"/>
   <input type="hidden" id="endAgeVal" value="${searchEndAge}"/>
</body>
<script>
ageArea();
function ageArea(){
   
   var searchStartAge=$('#startAgeVal').val();
   var searchEndAge=$('#endAgeVal').val();
   console.log(searchStartAge +'/'+searchEndAge);
   
   for (var i = 18; i < 101; i++) {
      $('#searchStartAge').append('<option value="'+i+'">'+i+'</option>');
   }
   for (var i = 100; i > 17; i--) {
      $('#searchEndAge').prepend('<option value="'+i+'">'+i+'</option>');
   }
   
   if(searchStartAge =="" && searchEndAge ==""){
      $("#searchStartAge  > option[value='0']").attr("selected", "true");        
       $("#searchEndAge  > option[value='1']").attr("selected", "true");   
   } else{
      $("#searchStartAge  > option[value="+searchStartAge+"]").attr("selected", "true");        
       $("#searchEndAge  > option[value="+searchEndAge+"]").attr("selected", "true");   
   }   
}


$(".pageInfo a").on("click", function(e){
   e.preventDefault();
    if($("#searchOption").val()=="" && $("#searchStartAge").val()=="" && $("#searchEndAge").val()==""){
      $("#moveForm").find("input[name='pageNum']").val($(this).attr("href"));
       $("#moveForm").attr("action", "/personList.go");
      $("#moveForm").submit();
    } else {
       $("#form").find('input[name="pageNum"]').val($(this).attr("href"));
       $("#form").submit();
    }
    
});

//by태섭, 입사제안 클릭 시 팝업창으로 채용공고 리스트 팝업창 호출
/* function showJobPostingList() {
   var url = "jobPostingList.go"
   var name = "jpoListPopup";
   var option = "width=700, height=400, left=200, top=50, scrollbars = yes, location= no"
   
   <input type="button" value="입사제안" onclick="offer.do?re_no"'+item.re_no+'/>
   
   window.open(url, name, option);
} */

function popOpen(id){
   window.open("offer.go?cl_id="+id,"new","width=800, height=400, resizable=no, scrollbars=no, status=no, location=no, directories=no;");
}

btnShow();
function btnShow(){
	if("${comChk}"==""){		
		$('.jobOffer').hide();
	}
}

</script>
</html>