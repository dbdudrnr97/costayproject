<%@page import="product.model.vo.Play"%>
<%@page import="product.model.vo.Place"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
	/* List<Place> placeList = (List<Place>)request.getAttribute("placeList"); */
	/* System.out.println("placeList@jsp="+placeList); */
	
	Play place = (Play)request.getAttribute("place");
%>
<title>체험 상세보기</title>
<style>
	#btn-insert {
		text-align:center
		}
</style>
</head>
<script>
$(function() {
	/**
	* 로그인 하지 않고 댓글 작성할 수 없다
	*/
	$("[name=boardCommentContent]").focus(function() {
		
		<%if(memberLoggedIn == null) { %>
		   loginAlert();
		<% } %>
		
	});
	
	//폼 제출 유효성 검사
	//1. return false
	//2. event.preventDefault();
	$("[name=boardCommentFrm]").submit(function(e){
		var $boardCommentContent = $("[name=boardCommentContent]");
		if(/^(.|\n){1,}$/.test($boardCommentContent.val()) == false){
			alert("댓글 내용을 작성해주세요.");
			$boardCommentContent.focus();
			e.preventDefault();
		}
	});
	
	
});

function loginAlert() {
	alert("로그인 후 댓글 작성 가능합니다.");
	$("#memberId").focus();
}

</script>
<body>
<div id="">
  <form class="" action="<%=request.getContextPath() %>/product/place/placeViewDetails">
    <input class="" type="text" placeholder="예약하실 인원수를 입력하세요." />
    <input class="" type="text" placeholder="원하는 날짜를 입력하세요" />
    <button class="" type="submit">Search</button>
  </form>
  <br />
</div>
 
<!--호스트아이디 가져오기-->
<%-- <%if(placeList == null || placeList.isEmpty()) { %>

<% } else { %>
<%for(Place p : placeList) { %>
 <%=p.getHostMemberId() %>님의 숙소를 소개합니다!
<!--   place_note 가져오기 -->
<%=p.getPlaceNote() %>
<!-- 부대,편의 시설 ,짤라서 가져오기 -->
<%=p.getPlaceFacility() %>
<hr>
</div>
</article>
<%=p.getHostMemberId() %> 님
<% } %>
<% } %> --%>

<div id="playView">
<%-- <form action="<%=request.getContextPath()%>/product/place/placeViewDetails?placeId=<%=place.getPlaceId()%>" id="placeView" method="GET" > --%>
<form action="<%=request.getContextPath()%>/product/play/playViewDetails" id="playView" method="GET" >
<input type="hidden" name="playId" value="" /> 
<table id="playViewTbl" border="1" style="boarder:none; padding:0">
<tr>
   <table border="1">
       <tr>
       		<input type="hidden" name="boardNo" value="<%-- <%= board.getBoardNo() %> --%>" /> 
           <td><label for="playName">숙소이름</label></td>
           <td><input type="hidden" name="playName" id="playName" value="" readonly></td>                    
       </tr>
 
</tr>
</table>
</form>




</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
  