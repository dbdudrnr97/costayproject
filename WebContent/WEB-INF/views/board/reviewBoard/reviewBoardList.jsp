<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="board.model.vo.Board"%>
<%@page import="board.model.vo.BoardExt"%>
<%@page import="member.model.service.MemberService"%>

<%@ page import = "java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    
	List<BoardExt> reviewList = (List<BoardExt>)request.getAttribute("reviewList");
	String pageBar = (String)request.getAttribute("pageBar");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
    
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
	div#search-board {margin:0 0 10px 0; padding:3px; background-color: rgba(0, 188, 212, 0.3);}
	div#search-boardTitle {display: <%= "boardTitle".equals(searchType) || searchType == null ? "inline-block" : "none" %>;}
	div#search-boardContent {display:<%= "boardContent".equals(searchType) ? "inline-block" : "none" %>;}
	section#board-container li {list-style-type: none; float: left;}
	div#menu-container {margin:0 auto; width:800px; text-align:left;}
	

</style>
<script>
/*
* 검색타입 변경 이벤트핸들러
*/
$(function(){
$("#searchType").change(function(){
	var type = $(this).val();
	var $divSearchTypes = $(".search-type");
	
	$divSearchTypes
		.hide()
		.filter("#search-" + type)
		.css('display', 'inline-block');
	});
});


</script>

<div class="sub_title">
 <h3 style="margin-top:40px;"><strong>Review Board</strong></h3>
 <hr />
</div>
<section id="board-container">
  <div id="menu-container">
	<ul id="menu-ul">
		<li><a href="<%= request.getContextPath()%>/board/review/boardList">후기게시판&nbsp&nbsp&nbsp&nbsp&nbsp</a></li>
		<li><a href="<%= request.getContextPath()%>/board/newHost/boardList">신규호스트</a></li><br />
	</ul>
 </div>
	
<div id="searchBoard" class="board">
     <select id="searchType">
         <option value="boardTitle" <%= "boardTitle".equals(searchType) ? "selected" : "" %>>제목</option>		
         <option value="boardContent" <%= "boardContent".equals(searchType) ? "selected" : "" %>>내용</option>
         <%-- <option value="boardEnrollDate" <%= "boardEnrollDate".equals(searchType) ? "selected" : "" %>>날짜</option> --%>
     </select>
    <div id="search-boardTitle" class="search-type">
        <form action="<%=request.getContextPath()%>/board/review/boardFinder">
            <input type="hidden" name="searchType" value="boardTitle"/>
            <input 
            	type="text" name="searchKeyword"  size="25" 
            	placeholder="검색할 제목을 입력하세요."
            	value="<%= "boardTitle".equals(searchType) ? searchKeyword : "" %>"/>
            <button type="submit">검색</button>			
        </form>	
    </div>
    <div id="search-boardContent" class="search-type">
        <form action="<%=request.getContextPath()%>/board/review/boardFinder">
            <input type="hidden" name="searchType" value="boardContent"/>
            <input 
            	type="text" name="searchKeyword" size="25" 
            	placeholder="검색할 내용을 입력하세요."
            	value="<%= "boardContent".equals(searchType) ? searchKeyword : "" %>"/>
            <button type="submit">검색</button>			
        </form>	
    </div>

	
	<%--글쓰기버튼 멤버롤에따라서 보임처리 --%>  
	<table id="tbl-board" class="table table-borderless" style="margin-top:20px; margin-bottom:10px;">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th>
			<th>조회수</th>
		</tr>
		
		<%-- <% for(BoardExt board : reviewList){ 
			 BoardExt b = board; 
		%>       --%>
	</thead>
	<tbody>
	<% if(reviewList == null || reviewList.isEmpty()) { %>
			<%--조회된 결과가 없는 경우 --%>
			<tr>
				<td colspan="10" style="text-align:center;">
					조회된 결과가 없습니다.
				</td>
			</tr>
		
		<% } else { 
		for(BoardExt b : reviewList){ %> 
		<tr>
			<td>
				<%--번호 --%>
				<%=b.getBoardNo() %>
			</td>
			<td>
				<%--제목 --%>
				<a href="<%=request.getContextPath()%>/board/review/boardView?boardNo=<%=b.getBoardNo()%>">
					<%= b.getBoardTitle() + (b.getBoardCommentCount() > 0 ? "(" + b.getBoardCommentCount() + ")" :  "("+0+")")%> 
					<%-- <%= b.getBoardTitle() %> --%>
				</a>
			</td>
			<td>
				<%--작성자 --%>
				<%=b.getBoardWriter() %>
			</td>
			<td>
				<%--작성일 --%>
				<%= b.getBoardEnrollDate() %>
			</td>
			<td>
			<% if(b.getBoardOriginalFileName() != null){ %>
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/fileLogo.png" width="16px">
			<% }%>
			</td>
			<td>
				<%--조회수 --%>
				<%=b.getBoardReadCount() %>
			</td>
		</tr>
		<% } %> 
		<% } %> 
		</tbody>
	</table>
	<div style="text-align:right; margin-right:35px;">
	 <%if(memberLoggedIn != null) { %>  
	<input type="button"
		    value="글쓰기" 
		    id="write-btn"
			onclick="location.href='<%=request.getContextPath() %>/board/review/boardEnroll';"/>
	<% } %>  
	</div>
	<div id='pageBar'><%= pageBar%></div>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>